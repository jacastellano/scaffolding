package com.scaffolding.spring.main.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaffolding.spring.main.domain.EntityDTO;
import com.scaffolding.spring.main.service.EntityService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EntityControllerTest {

	@Mock
	private EntityService serviceMock;

	@InjectMocks
	private EntityController controller;

	private MockMvc mockMvc;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testFindEntityById() throws Exception {

		// Prepare data
		EntityDTO mockEntity = new EntityDTO();
		mockEntity.setEntityId(1L);
		mockEntity.setEntityCode("0001/2019");
		mockEntity.setEntityTitle("title");
		mockEntity.setEntityDescription("description");
		mockEntity.setEntityTypeId(2L);
		mockEntity.setCreateDate(new Date());
		mockEntity.setCreateUser(3L);
		mockEntity.setUpdateDate(new Date());
		mockEntity.setUpdateUser(3L);

		// Mock service
		when(serviceMock.findEntityById(1L)).thenReturn(mockEntity);

		// Invoke controller and expect
		mockMvc.perform(get("/entities/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.entityId", is(1)))
				.andExpect(jsonPath("$.entityCode", is("0001/2019")))
				.andExpect(jsonPath("$.entityTitle", is("title")))
				.andExpect(jsonPath("$.entityDescription", is("description")))
				.andExpect(jsonPath("$.entityTypeId", is(2)))
				.andExpect(jsonPath("$.createDate").exists())
				.andExpect(jsonPath("$.createUser", is(3)))
				.andExpect(jsonPath("$.updateDate").exists())
				.andExpect(jsonPath("$.updateUser", is(3)))
				.andExpect(jsonPath("$.deleteDate").doesNotExist());

		// Verify
		verify(serviceMock, times(1)).findEntityById(1L);
		verifyNoMoreInteractions(serviceMock);
	}
	
	@Test
	public void testFindEntityById_NotFound() throws Exception {

		// Mock service
		when(serviceMock.findEntityById(1L)).thenReturn(null);

		// Invoke controller and expect
		mockMvc.perform(get("/entities/{id}", 1))
				.andExpect(status().isNotFound());

		// Verify
		verify(serviceMock, times(1)).findEntityById(1L);
		verifyNoMoreInteractions(serviceMock);
	}

	@Test
	public void testFindAllEntities() throws Exception {

		// Prepare data
		EntityDTO mockEntityA = new EntityDTO();
		mockEntityA.setEntityId(1L);
		mockEntityA.setEntityCode("0001/2019");
		mockEntityA.setEntityTitle("mock entity A");
		mockEntityA.setCreateDate(new Date());
		mockEntityA.setCreateUser(3L);
		mockEntityA.setUpdateDate(new Date());
		mockEntityA.setUpdateUser(4L);
		
		EntityDTO mockEntityB = new EntityDTO();
		mockEntityB.setEntityId(2L);
		mockEntityB.setEntityCode("0002/2019");
		mockEntityB.setEntityTitle("mock entity B");
		mockEntityB.setCreateDate(new Date());
		mockEntityB.setCreateUser(3L);
		mockEntityB.setUpdateDate(new Date());
		mockEntityB.setUpdateUser(4L);
		
		List<EntityDTO> mockEntityList = Arrays.asList(mockEntityA, mockEntityB);

		// Mock service
		when(serviceMock.findAllEntities()).thenReturn(mockEntityList);

		// Invoke controller and expect
		mockMvc.perform(get("/entities")).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].entityId", is(1)))
			.andExpect(jsonPath("$[0].entityCode", is("0001/2019")))
			.andExpect(jsonPath("$[0].entityTitle", is("mock entity A")))
			.andExpect(jsonPath("$[0].createDate").exists())
			.andExpect(jsonPath("$[0].createUser", is(3)))
			.andExpect(jsonPath("$[0].updateDate").exists())
			.andExpect(jsonPath("$[0].updateUser", is(4)))
			.andExpect(jsonPath("$[0].deleteDate").doesNotExist())
			.andExpect(jsonPath("$[1].entityId", is(2)))
			.andExpect(jsonPath("$[1].entityCode", is("0002/2019")))
			.andExpect(jsonPath("$[1].entityTitle", is("mock entity B")))
			.andExpect(jsonPath("$[1].createDate").exists())
			.andExpect(jsonPath("$[1].createUser", is(3)))
			.andExpect(jsonPath("$[1].updateDate").exists())
			.andExpect(jsonPath("$[1].updateUser", is(4)))
			.andExpect(jsonPath("$[1].deleteDate").doesNotExist());

		// Verify
		verify(serviceMock, times(1)).findAllEntities();
		verifyNoMoreInteractions(serviceMock);
	}
	
	@Test
	public void testFindAllEntities_NoResults() throws Exception {

		// Mock service
		when(serviceMock.findAllEntities()).thenReturn(new ArrayList<EntityDTO>());

		// Invoke controller and expect
		mockMvc.perform(get("/entities")).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$", hasSize(0)));

		// Verify
		verify(serviceMock, times(1)).findAllEntities();
		verifyNoMoreInteractions(serviceMock);
	}

	@Test
	public void testCreateEntity() throws Exception {

		// Mock service
		when(serviceMock.createEntity(Mockito.any(EntityDTO.class))).thenAnswer(new Answer<EntityDTO>() {

			@Override
			public EntityDTO answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				if (arguments != null && arguments.length > 0 && arguments[0] != null) {
					EntityDTO entity = (EntityDTO) arguments[0];
					EntityDTO result = new EntityDTO();
					result.setEntityId(1L);
					result.setEntityCode(entity.getEntityCode());
					result.setEntityTitle(entity.getEntityTitle());
					result.setEntityDescription(entity.getEntityDescription());
					result.setEntityTypeId(entity.getEntityTypeId());
					result.setCreateDate(new Date());
					result.setCreateUser(entity.getCreateUser());
					result.setUpdateDate(new Date());
					result.setUpdateUser(entity.getCreateUser());
					return result; 
				}
				return null;
			}
		});

		// Prepare data
		EntityDTO entity = new EntityDTO();
		entity.setEntityCode("0001/2019");
		entity.setEntityTitle("title");
		entity.setEntityDescription("description");
		entity.setEntityTypeId(2L);
		entity.setCreateUser(3L);

		// Invoke controller method and expect
		mockMvc.perform(post("/entities")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(entity)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.entityId", is(1)))
				.andExpect(jsonPath("$.entityCode", is("0001/2019")))
				.andExpect(jsonPath("$.entityTitle", is("title")))
				.andExpect(jsonPath("$.entityDescription", is("description")))
				.andExpect(jsonPath("$.entityTypeId", is(2)))
				.andExpect(jsonPath("$.createDate").exists())
				.andExpect(jsonPath("$.createUser", is(3)))
				.andExpect(jsonPath("$.updateDate").exists())
				.andExpect(jsonPath("$.updateUser", is(3)))
				.andExpect(jsonPath("$.deleteDate").doesNotExist());

		// Verify
		verify(serviceMock, times(1)).createEntity(entity);
		verifyNoMoreInteractions(serviceMock);
	}

	@Test
	public void testUpdateEntity() throws Exception {
		
		// Prepare data
		EntityDTO entity = new EntityDTO();
		entity.setEntityId(1L);
		entity.setEntityCode("0001/2019");
		entity.setEntityTitle("title");
		entity.setEntityDescription("description");
		entity.setEntityTypeId(2L);
		entity.setUpdateUser(3L);
		
		// Mock service
	    when(serviceMock.updateEntity(entity, entity.getEntityId())).thenAnswer(new Answer<EntityDTO>() {

			@Override
			public EntityDTO answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				if (arguments != null && arguments.length > 0 && arguments[0] != null) {
					EntityDTO entity = (EntityDTO) arguments[0];
					EntityDTO result = new EntityDTO();
					result.setEntityId(entity.getEntityId());
					result.setEntityCode(entity.getEntityCode());
					result.setEntityTitle(entity.getEntityTitle());
					result.setEntityDescription(entity.getEntityDescription());
					result.setEntityTypeId(entity.getEntityTypeId());
					result.setUpdateDate(new Date());
					result.setUpdateUser(entity.getUpdateUser());
					return result;
				}
				return null;
			}
		});
	    
	    // Invoke controller method and expect
	    mockMvc.perform(put("/entities/{id}", entity.getEntityId())
	    		.contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(entity)))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$.entityId", is(1)))
				.andExpect(jsonPath("$.entityCode", is("0001/2019")))
				.andExpect(jsonPath("$.entityTitle", is("title")))
				.andExpect(jsonPath("$.entityDescription", is("description")))
				.andExpect(jsonPath("$.entityTypeId", is(2)))
				.andExpect(jsonPath("$.updateDate").exists())
				.andExpect(jsonPath("$.updateUser", is(3)))
				.andExpect(jsonPath("$.deleteDate").doesNotExist());
	            
	    
	    // Verify
	    verify(serviceMock, times(1)).updateEntity(entity, entity.getEntityId());
	    verifyNoMoreInteractions(serviceMock);
	}
	
	@Test
	public void testUpdateEntity_NotFound() throws Exception {
		
		// Prepare data
		EntityDTO entity = new EntityDTO();
		entity.setEntityId(1L);
		entity.setEntityCode("0001/2019");
		entity.setEntityTitle("title");
		
		// Mock service
	    when(serviceMock.updateEntity(entity, entity.getEntityId())).thenReturn(null);
	    
	    // Invoke controller method and expect
	    mockMvc.perform(put("/entities/{id}", entity.getEntityId())
	    		.contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(entity)))
	            .andExpect(status().isNotFound());
	    
	    // Verify
	    verify(serviceMock, times(1)).updateEntity(entity, entity.getEntityId());
	    verifyNoMoreInteractions(serviceMock);
	}

	@Test
	public void testDeleteEntity() throws Exception {
		
		// Prepare data
		EntityDTO entity = new EntityDTO();
		entity.setEntityId(1L);
		entity.setEntityCode("0001/2019");
		entity.setEntityTitle("title");
		entity.setUpdateUser(3L);

		// Mock service
		when(serviceMock.deleteEntity(entity.getEntityId())).thenAnswer(new Answer<EntityDTO>() {

			@Override
			public EntityDTO answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				if (arguments != null && arguments.length > 0 && arguments[0] != null) {
					Long entityId = (Long) arguments[0];
					EntityDTO result = new EntityDTO();
					result.setEntityId(entityId);
					result.setDeleteDate(new Date());
					return result;
				}
				return null;
			}
		});

		// Invoke controller method and expect
		mockMvc.perform(delete("/entities/{id}", entity.getEntityId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.entityId", is(1)))
				.andExpect(jsonPath("$.deleteDate").exists());

		// Verify
		verify(serviceMock, times(1)).deleteEntity(entity.getEntityId());
		verifyNoMoreInteractions(serviceMock);
	}
	
	@Test
	public void testDeleteEntity_NotFound() throws Exception {
		
		// Prepare data
		EntityDTO entity = new EntityDTO();
		entity.setEntityId(1L);
		entity.setEntityCode("0001/2019");
		entity.setEntityTitle("title");
		entity.setUpdateUser(4L);

		// Mock service
		when(serviceMock.deleteEntity(entity.getEntityId())).thenReturn(null);

		// Invoke controller method and expect
		mockMvc.perform(delete("/entities/{id}", entity.getEntityId()))
				.andExpect(status().isNotFound());

		// Verify
		verify(serviceMock, times(1)).deleteEntity(entity.getEntityId());
		verifyNoMoreInteractions(serviceMock);
	}

	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
