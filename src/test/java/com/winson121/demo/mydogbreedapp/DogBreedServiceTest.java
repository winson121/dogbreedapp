package com.winson121.demo.mydogbreedapp;

import com.winson121.demo.mydogbreedapp.dao.api.DogBreedDAOApi;
import com.winson121.demo.mydogbreedapp.dao.db.DogBreedDAODb;
import com.winson121.demo.mydogbreedapp.dto.BreedDTO;
import com.winson121.demo.mydogbreedapp.dto.SubbreedDTO;
import com.winson121.demo.mydogbreedapp.entity.Breed;
import com.winson121.demo.mydogbreedapp.mapper.Mapper;
import com.winson121.demo.mydogbreedapp.service.DogBreedService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class DogBreedServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private Mapper mapper;

    @SpyBean
    private DogBreedDAOApi dogBreedDAOApi;

    @SpyBean
    private DogBreedDAODb dogBreedDAODb;

    @Autowired
    private DogBreedService dogBreedService;

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


    @BeforeEach
    public void setupDatabase() {
        jdbc.execute(sqlInsertBreed);
        jdbc.execute(sqlInsertSubbreed);
        jdbc.execute(sqlInsertImage);
    }

    @DisplayName("Save breed to db test")
    @Test
    public void saveBreed() {
        BreedDTO breedDto = new BreedDTO("akita", new ArrayList<>());
        dogBreedService.saveBreed(breedDto);

        Breed breed = dogBreedDAODb.getBreedFromDb("akita");

        assertEquals("akita", breed.getBreedName(), "find by breed");
    }

    @DisplayName("Delete breed from db test")
    @Test
    public void deleteBreedService() {
        int alphaBreedId = 10003;
        Optional<Breed> deletedBreed = Optional.ofNullable(dogBreedDAODb.getBreedFromDb("alpha"));

        assertTrue(deletedBreed.isPresent(), "return true");

        dogBreedService.deleteBreed(alphaBreedId);

        deletedBreed = Optional.ofNullable(dogBreedDAODb.getBreedFromDb("alpha"));

        assertFalse(deletedBreed.isPresent(), "return false");
    }

    @DisplayName("Update breed from db")
    @Test
    @Transactional
    public void updateBreedFromDb() {
        String newBreedName = "akita";
        int alphaBreedId = 10003;

        BreedDTO breedDto = new BreedDTO(alphaBreedId, newBreedName, new ArrayList<>());
        dogBreedService.updateBreed(breedDto);

        Breed newBreed = dogBreedDAODb.getBreedFromDb("akita");
        assertEquals(newBreed.getId(), alphaBreedId, "updated breed for breed Id: " + alphaBreedId);
        assertNotEquals(newBreed.getBreedName(), "alpha", "updated breed name");
    }
//
//
//    @Test
//    public void studentInformationService() {
//        GradebookCollegeStudent gradebookCollegeStudentTest = studentService.studentInformation(1);
//
//        assertNotNull(gradebookCollegeStudentTest);
//        assertEquals(1, gradebookCollegeStudentTest.getId());
//        assertNotNull(gradebookCollegeStudentTest.getFirstname());
//        assertNotNull(gradebookCollegeStudentTest.getLastname());
//        assertNotNull(gradebookCollegeStudentTest.getEmailAddress());
//        assertNotNull(gradebookCollegeStudentTest.getStudentGrades().getMathGradeResults());
//        assertNotNull(gradebookCollegeStudentTest.getStudentGrades().getScienceGradeResults());
//        assertNotNull(gradebookCollegeStudentTest.getStudentGrades().getHistoryGradeResults());
//
//        assertEquals("Eric", gradebookCollegeStudentTest.getFirstname());
//        assertEquals("Roby", gradebookCollegeStudentTest.getLastname());
//        assertEquals("eric.roby@luv2code_school.com", gradebookCollegeStudentTest.getEmailAddress());
//
//    }
//
//    @Test
//    public void isGradeNullCheck() {
//
//        assertTrue(studentService.checkIfGradeIsNull(1, "math"),
//                "@BeforeTransaction creates student : return true");
//
//        assertTrue(studentService.checkIfGradeIsNull(1, "science"),
//                "@BeforeTransaction creates student : return true");
//
//        assertTrue(studentService.checkIfGradeIsNull(1, "history"),
//                "@BeforeTransaction creates student : return true");
//
//        assertFalse(studentService.checkIfGradeIsNull(0, "science"),
//                "No student should have 0 id : return false");
//
//        assertFalse(studentService.checkIfGradeIsNull(0, "Literature"),
//                "No student should have 0 id : return false");
//    }
//
//    @Test
//    public void deleteGradeService() {
//
//        assertEquals(1, studentService.deleteGrade(1, "math"),
//                "@BeforeTransaction creates student : return true");
//
//        assertEquals(1, studentService.deleteGrade(1, "science"),
//                "@BeforeTransaction creates student : return true");
//
//        assertEquals(1, studentService.deleteGrade(1, "history"),
//                "@BeforeTransaction creates student : return true");
//
//        assertEquals(0, studentService.deleteGrade(0, "science"),
//                "No student should have 0 id : return false");
//
//        assertEquals(0, studentService.deleteGrade(1, "literature"),
//                "No student should have 0 id : return false");
//    }
//
//    @Test
//    public void createGradeService() {
//
//        studentService.createGrade(80.50, 2, "math");
//        studentService.createGrade(80.50, 2, "science");
//        studentService.createGrade(80.50, 2, "history");
//        studentService.createGrade(80.50, 2, "literature");
//
//        Iterable<MathGrade> mathGrades  = mathGradeDao.findGradeByStudentId(2);
//
//        Iterable<ScienceGrade> scienceGrades  = scienceGradeDao.findGradeByStudentId(2);
//
//        Iterable<HistoryGrade> historyGrades  = historyGradeDao.findGradeByStudentId(2);
//
//        assertTrue(mathGrades.iterator().hasNext(),
//                "Student Service creates the grade: return true");
//        assertTrue(scienceGrades.iterator().hasNext(),
//                "Student Service creates the grade: return true");
//        assertTrue(historyGrades.iterator().hasNext(),
//                "Student Service creates the grade: return true");
//
//    }
//
//    @SqlGroup({ @Sql(scripts = "/insertData.sql", config = @SqlConfig(commentPrefix = "`")),
//            @Sql("/overrideData.sql"),
//            @Sql("/insertGrade.sql")})
//    @Test
//    public void getGradebookService() {
//
//        Gradebook gradebook = studentService.getGradebook();
//
//        Gradebook gradebookTest = new Gradebook();
//
//        for (GradebookCollegeStudent student : gradebook.getStudents()) {
//            if (student.getId() > 10) {
//                gradebookTest.getStudents().add(student);
//            }
//        }
//
//        assertEquals(4, gradebookTest.getStudents().size());
//        assertTrue(gradebookTest.getStudents().get(0).getStudentGrades().getHistoryGradeResults() != null);
//        assertTrue(gradebookTest.getStudents().get(0).getStudentGrades().getScienceGradeResults() != null);
//        assertTrue(gradebookTest.getStudents().get(0).getStudentGrades().getMathGradeResults() != null);
//    }
//
//
    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute(sqlDeleteBreed);
        jdbc.execute(sqlDeleteSubbreed);
        jdbc.execute(sqlDeleteImage);
    }
}
