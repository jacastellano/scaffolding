package com.scaffolding.spring;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.scaffolding.spring.main.domain.EntityDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApiRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringApiRestApplicationTests {

	@Inject
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testFindEntityById_Found() {

		// Request
		ResponseEntity<EntityDTO> response = restTemplate.getForEntity(getRootUrl() + "/entities/1",
				EntityDTO.class);

		// Assertions
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void testFindEntityById_NotFound() {

		// Request
		ResponseEntity<EntityDTO> response = restTemplate.getForEntity(getRootUrl() + "/entities/1000000",
				EntityDTO.class);

		// Assertions
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

	}

	@Test
	public void testFindAllEntities() {

		// Prepare request
		ParameterizedTypeReference<List<EntityDTO>> responseType = new ParameterizedTypeReference<List<EntityDTO>>() {
		};

		// Request
		ResponseEntity<List<EntityDTO>> response = restTemplate.exchange(getRootUrl() + "/entities", HttpMethod.GET,
				null, responseType);

		// Assertions
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void testCreateEntity() {

		// Prepare mock data
		EntityDTO mockEntity = new EntityDTO();
		mockEntity.setEntityTitle("Entity for testing");
		mockEntity.setEntityDescription("Creating an entity for testing...");

		// Prepare request
		ResponseEntity<EntityDTO> response = restTemplate.postForEntity(getRootUrl() + "/entities", mockEntity,
				EntityDTO.class);

		// Response
		EntityDTO createdEntity = response.getBody();

		// Assertions
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals(mockEntity.getEntityTitle(), createdEntity.getEntityTitle());
		Assert.assertEquals(mockEntity.getEntityDescription(), createdEntity.getEntityDescription());
		Assert.assertNotNull(createdEntity.getEntityId());
		Assert.assertNotNull(createdEntity.getCreateDate());
		Assert.assertNull(createdEntity.getUpdateDate());
		Assert.assertNull(createdEntity.getDeleteDate());
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}

	@Test
	public void testCreateEntity_WithoutMandatoryField() {

		// Prepare mock data
		EntityDTO mockEntity = new EntityDTO();
		mockEntity.setEntityDescription("Creating an entity for testing...");

		// Prepare request
		ResponseEntity<EntityDTO> response = restTemplate.postForEntity(getRootUrl() + "/entities", mockEntity,
				EntityDTO.class);

		// Assertions
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}

	@Test
	public void testUpdateEntity_Found() throws JsonProcessingException {

		// Prepare mock data
		EntityDTO mockEntity = new EntityDTO();
		mockEntity.setEntityTitle("Entity for testing");
		mockEntity.setEntityDescription("Updating an entity for testing...");

		// Prepare request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String requestBody = ow.writeValueAsString(mockEntity);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);

		// Request
		ResponseEntity<EntityDTO> response = restTemplate.exchange(getRootUrl() + "/entities/2", HttpMethod.PUT,
				httpEntity, EntityDTO.class);

		// Response
		EntityDTO updatedEntity = response.getBody();

		// Assertions
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals("Entity for testing", updatedEntity.getEntityTitle());
		Assert.assertEquals("Updating an entity for testing...", updatedEntity.getEntityDescription());
		Assert.assertNotNull(updatedEntity.getEntityId());
		Assert.assertNotNull(updatedEntity.getCreateDate());
		Assert.assertNotNull(updatedEntity.getUpdateDate());
		Assert.assertNull(updatedEntity.getDeleteDate());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void testUpdateEntity_NotFound() throws JsonProcessingException {

		// Prepare mock data
		EntityDTO mockEntity = new EntityDTO();
		mockEntity.setEntityTitle("Entity for testing");
		mockEntity.setEntityDescription("Updating an entity for testing...");

		// Prepare request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String requestBody = ow.writeValueAsString(mockEntity);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);

		// Request
		ResponseEntity<EntityDTO> response = restTemplate.exchange(getRootUrl() + "/entities/1000000", HttpMethod.PUT,
				httpEntity, EntityDTO.class);

		// Assertions
		Assert.assertNotNull(response);
		Assert.assertNull(response.getBody());
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

	}

	@Test
	public void testUpdateEntity_WithoutMandatoryField() throws JsonProcessingException {

		// Prepare mock data
		EntityDTO mockEntity = new EntityDTO();
		mockEntity.setEntityTitle("");
		mockEntity.setEntityDescription("Updating an entity for testing...");

		// Prepare request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String requestBody = ow.writeValueAsString(mockEntity);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);

		// Request
		ResponseEntity<EntityDTO> response = restTemplate.exchange(getRootUrl() + "/entities/2", HttpMethod.PUT,
				httpEntity, EntityDTO.class);

		// Assertions
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}

	@Test
	public void testDeleteEntity_Found() {

		// Request
		ResponseEntity<EntityDTO> response = restTemplate.exchange(getRootUrl() + "/entities/3", HttpMethod.DELETE,
				null, EntityDTO.class);

		// Response
		EntityDTO deletedEntity = response.getBody();

		// Assertions
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getBody());
		Assert.assertNotNull(deletedEntity.getDeleteDate());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void testDeleteEntity_NotFound() {

		// Request
		ResponseEntity<EntityDTO> response = restTemplate.exchange(getRootUrl() + "/entities/1000000",
				HttpMethod.DELETE, null, EntityDTO.class);

		// Assertions
		Assert.assertNotNull(response);
		Assert.assertNull(response.getBody());
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

}
