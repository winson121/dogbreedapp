package com.winson121.demo.mydogbreedapp;

import com.winson121.demo.mydogbreedapp.service.DogBreedServiceImpl;
import com.winson121.demo.mydogbreedapp.entity.Breed;
import com.winson121.demo.mydogbreedapp.dao.api.DogBreedDAOApi;
import com.winson121.demo.mydogbreedapp.dao.db.DogBreedDAODb;
import com.winson121.demo.mydogbreedapp.service.DogBreedService;

import static org.mockito.Mockito.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class DogBreedRestControllerTest {

    private static MockHttpServletRequest request;

    @PersistenceContext
    private EntityManager entityManager;


    @SpyBean
    DogBreedServiceImpl dogBreedServiceMock;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private DogBreedDAOApi dogBreedDAOApi;

    @Autowired
    private DogBreedDAODb dogBreedDAODb;

    @Autowired
    private DogBreedService dogBreedService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${sql.script.insert.breed}")
    private String sqlInsertBreed;

    @Value("${sql.script.insert.subbreed}")
    private String sqlInsertSubbreed;
    
    @Value("${sql.script.insert.image}")
    private String sqlInsertImage;
    
    @Value("${sql.script.delete.breed}")
    private String sqlDeleteBreed;
    
    @Value("${sql.script.delete.subbreed}")
    private String sqlDeleteSubbreed;
    
    @Value("${sql.script.delete.image}")
    private String sqlDeleteImage;
    
    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;


    @BeforeAll
    public static void setup() {

        request = new MockHttpServletRequest();

    }

    @BeforeEach
    public void setupDatabase() {
        jdbc.execute(sqlInsertBreed);
        jdbc.execute(sqlInsertSubbreed);
        jdbc.execute(sqlInsertImage);
    }

    @DisplayName("Get Breeds from /api/breeds success")
    @Test
    public void getBreedsHttpRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/breeds"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));

    }

    @DisplayName("Get Breeds from /api/breeds/v2 success")
    @Test
    public void getBreedsV2HttpRequest() throws Exception {
        jdbc.execute("select * from breed");
        when(dogBreedServiceMock.getBreedsFromDb()).thenCallRealMethod();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/breeds/v2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(4)));

    }

    @DisplayName("Get Breed from /api/breed/{breedName}")
    @Test
    public void getBreedHttpRequest() throws Exception {

        String breedName = "hound";
        Optional<Breed> breed = Optional.ofNullable(dogBreedDAOApi.getBreedFromAPI(breedName));

        assertTrue(breed.isPresent());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/breed/{breedName}", breedName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.breed_name",is(breedName)));

    }

    @DisplayName("Get Breed from /api/breed/{breedName} Not found")
    @Test
    public void getBreedHttpRequestNotFound() throws Exception {

        String breedName = "notexist";
        Optional<Breed> breed = Optional.ofNullable(dogBreedDAOApi.getBreedFromAPI(breedName));

        assertFalse(breed.isPresent());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/breed/{breedName}", breedName))
                .andExpect(status().is4xxClientError());

    }

    @DisplayName("Get Breed Images success")
    @Test
    public void getBreedImagesHttpRequest() throws Exception {

        String breedName = "hound";
        Optional<Breed> breed = Optional.ofNullable(dogBreedDAOApi.getBreedFromAPI(breedName));

        assertTrue(breed.isPresent());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/breed/{breedName}/images", breedName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", Matchers.hasKey(breedName)));

    }

    @DisplayName("Get Breed image Timeout response")
    @Test
    public void getBreedImagesHttpRequestTimeOutEmptyResponse() throws Exception {

        String breedName = "randobreed";
        Optional<Breed> breed = Optional.ofNullable(dogBreedDAOApi.getBreedFromAPI(breedName));

        assertTrue(!breed.isPresent());
        when(dogBreedServiceMock.getBreedImages(breedName)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/breed/{breedName}/images", breedName))
                .andExpect(status().is4xxClientError());
        verify(dogBreedServiceMock, times(1)).getBreedImages(breedName);
    }

    @DisplayName("Post Breed to /api/breeds/v2")
    @Test
    public void postBreedHttpRequest() throws Exception {
        String breedName = "newBreed";
        Breed breed = new Breed(breedName, new ArrayList<>());
        mockMvc.perform(post("/api/breeds/v2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(breed)))
                .andExpect(status().isOk());

        Breed verifyBreed = dogBreedDAODb.getBreedFromDb(breedName);
        assertNotNull(verifyBreed, "breed inserted to db.");
    }

    @DisplayName("Post Breed to /api/breeds/v2 but null value")
    @Test
    public void postBreedHttpRequestNullValue() throws Exception {
        mockMvc.perform(post("/api/breeds/v2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());

    }

    @DisplayName("Put Breed to /api/breeds/v2")
    @Test
    public void putBreedHttpRequest() throws Exception {
        String breedName = "akita";
        Breed breed = dogBreedDAODb.getBreedFromDb("alpha");
        breed.setBreedName(breedName);
        int breedId = breed.getId();
        mockMvc.perform(put("/api/breeds/v2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(breed)));

        Breed verifyBreed = dogBreedDAODb.getBreedFromDb(breedName);
        assertEquals(verifyBreed.getId(), breedId, "breed id is the same befor and after updated");
        assertEquals(verifyBreed.getBreedName(), breedName, "breed name updated to db.");
    }

    @DisplayName("Put Breed to /api/breeds/v2 but null value")
    @Test
    public void putBreedHttpRequestNullValue() throws Exception {
        mockMvc.perform(post("/api/breeds/v2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }

    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute(sqlDeleteBreed);
        jdbc.execute(sqlDeleteSubbreed);
        jdbc.execute(sqlDeleteImage);
    }
  
}
