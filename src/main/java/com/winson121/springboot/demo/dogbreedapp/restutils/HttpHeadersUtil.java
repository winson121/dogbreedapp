package com.winson121.springboot.demo.dogbreedapp.restutils;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class HttpHeadersUtil {
	
	public static HttpHeaders getAuthHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		return httpHeaders;
	}
	
	public static HttpEntity<?> constructHttpEntity() {
		// get http headers
		HttpHeaders httpHeaders = getAuthHeaders();
		
		// create HttpEntity 
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
		
		return httpEntity;
	}
	
	public static <T> HttpEntity<?> constructHttpEntity(T object) {
		// get http headers
		HttpHeaders httpHeaders = getAuthHeaders();
		
		// create HttpEntity with an object as the body .
		HttpEntity<?> httpEntity = new HttpEntity<>(object, httpHeaders);
		
		return httpEntity;
	}
}
