package com.scaffolding.spring.main.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.scaffolding.spring.main.domain.EntityDTO;
import com.scaffolding.spring.main.persistence.domain.EntityDAO;
import com.scaffolding.spring.main.persistence.repository.EntityRepository;
import com.scaffolding.spring.main.service.EntityService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EntityService.class)
public class EntityServiceTest {

	@MockBean
	private EntityRepository repositoryMock;

	@Inject
	private EntityService service;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindEntityById() {

		// Prepare data
		Date createDate = new Date();
		Date updateDate = new Date();
		Date deleteDate = new Date();
		EntityDAO entityMock = new EntityDAO(1L, "title", "description", createDate, updateDate, deleteDate);

		// Mock service
		when(repositoryMock.findById(1L)).thenReturn(Optional.of(entityMock));

		// Invoke service
		EntityDTO entityResult = service.findEntityById(1L);

		// Assertions
		assertNotNull(entityResult);
		assertEquals(1L, entityResult.getEntityId().longValue());
		assertEquals("title", entityResult.getEntityTitle());
		assertEquals("description", entityResult.getEntityDescription());
		assertEquals(createDate, entityResult.getCreateDate());
		assertEquals(updateDate, entityResult.getUpdateDate());
		assertEquals(deleteDate, entityResult.getDeleteDate());

	}

	@Test
	public void testFindEntityById_NotFound() {

		// Mock service
		when(repositoryMock.findById(1L)).thenReturn(Optional.empty());

		// Invoke service
		EntityDTO entityResult = service.findEntityById(1L);

		// Assertions
		assertNull(entityResult);

	}

	@Test
	public void testFindAllEntities() {

		// Prepare data
		List<EntityDAO> entityListMock = new ArrayList<EntityDAO>();
		entityListMock.add(new EntityDAO(1L, "title", "description", new Date(), null, null));
		entityListMock.add(new EntityDAO(2L, "title", "description", new Date(), null, null));
		entityListMock.add(new EntityDAO(3L, "title", "description", new Date(), null, null));

		// Mock service
		when(repositoryMock.findAll()).thenReturn(entityListMock);

		// Invoke service
		List<EntityDTO> resultList = service.findAllEntities();

		// Assertions
		assertNotNull(resultList);
		assertEquals(3, resultList.size());

	}

	@Test
	public void testFindAllEntities_NoResults() {

		// Mock service
		when(repositoryMock.findAll()).thenReturn(new ArrayList<EntityDAO>());

		// Invoke service
		List<EntityDTO> resultList = service.findAllEntities();

		// Assertions
		assertNotNull(resultList);
		assertEquals(0, resultList.size());

	}

	@Test
	public void testCreateEntity() {

		// Mock service
		when(repositoryMock.save(Mockito.any(EntityDAO.class))).thenAnswer(new Answer<EntityDAO>() {
			@Override
			public EntityDAO answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				if (arguments != null && arguments.length > 0 && arguments[0] != null) {
					EntityDAO entity = (EntityDAO) arguments[0];
					entity.setEntityId(1L);
					return entity;
				}
				return null;
			}
		});

		// Invoke service
		EntityDTO newEntity = new EntityDTO();
		newEntity.setEntityTitle("title");
		newEntity.setEntityDescription("description");
		EntityDTO entityResult = service.createEntity(newEntity);

		// Assertions
		assertNotNull(entityResult);
		assertNotNull(entityResult.getEntityId());
		assertEquals("title", entityResult.getEntityTitle());
		assertEquals("description", entityResult.getEntityDescription());
		assertNotNull(entityResult.getCreateDate());
		assertNull(entityResult.getUpdateDate());
		assertNull(entityResult.getDeleteDate());

	}

	@Test
	public void testUpdateEntity() {

		// Prepare data
		EntityDAO entityMock = new EntityDAO(1L, "title", "description", new Date(), null, null);

		// Mock services
		when(repositoryMock.findById(1L)).thenReturn(Optional.of(entityMock));

		when(repositoryMock.save(Mockito.any(EntityDAO.class))).thenAnswer(new Answer<EntityDAO>() {
			@Override
			public EntityDAO answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				if (arguments != null && arguments.length > 0 && arguments[0] != null) {
					EntityDAO entity = (EntityDAO) arguments[0];
					return entity;
				}
				return null;
			}
		});

		// Invoke service
		EntityDTO updateEntity = new EntityDTO();
		updateEntity.setEntityTitle("title");
		updateEntity.setEntityDescription("description");
		EntityDTO entityResult = service.updateEntity(updateEntity, 1L);

		// Assertions
		assertNotNull(entityResult);
		assertEquals(1L, entityResult.getEntityId().longValue());
		assertEquals("title", entityResult.getEntityTitle());
		assertEquals("description", entityResult.getEntityDescription());
		assertNotNull(entityResult.getCreateDate());
		assertNotNull(entityResult.getUpdateDate());
		assertNull(entityResult.getDeleteDate());

	}

	@Test
	public void testUpdateEntity_NotFound() {

		// Mock service
		when(repositoryMock.findById(1L)).thenReturn(Optional.empty());

		// Invoke service
		EntityDTO updateEntity = new EntityDTO();
		updateEntity.setEntityTitle("title");
		updateEntity.setEntityDescription("description");
		EntityDTO entityResult = service.updateEntity(updateEntity, 1L);

		// Assertions
		assertNull(entityResult);

	}

	@Test
	public void testDeleteEntity() {

		// Prepare data
		EntityDAO entityMock = new EntityDAO();
		entityMock.setEntityId(1L);

		// Mock services
		when(repositoryMock.findById(1L)).thenReturn(Optional.of(entityMock));

		when(repositoryMock.save(Mockito.any(EntityDAO.class))).thenAnswer(new Answer<EntityDAO>() {
			@Override
			public EntityDAO answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				if (arguments != null && arguments.length > 0 && arguments[0] != null) {
					EntityDAO entity = (EntityDAO) arguments[0];
					return entity;
				}
				return null;
			}
		});

		// Assertions
		EntityDTO entityResult = service.deleteEntity(1L);
		assertNotNull(entityResult);
		assertEquals(1L, entityResult.getEntityId().longValue());
		assertNotNull(entityResult.getDeleteDate());

	}

	@Test
	public void testDeleteEntity_NotFound() {

		// Mock services
		when(repositoryMock.findById(1L)).thenReturn(Optional.empty());

		// Invoke services
		EntityDTO entityResult = service.deleteEntity(1L);

		// Assertions
		assertNull(entityResult);
	}

}
