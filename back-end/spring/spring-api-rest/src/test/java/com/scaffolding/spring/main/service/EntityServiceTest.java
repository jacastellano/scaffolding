package com.scaffolding.spring.main.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		EntityDAO entityMock = new EntityDAO();
		entityMock.setEntityId(1L);
		entityMock.setEntityCode("0001/2019");
		entityMock.setEntityTitle("title");
		entityMock.setEntityDescription("description");
		entityMock.setEntityTypeId(2L);
		entityMock.setCreateDate(createDate);
		entityMock.setCreateUser(3L);
		entityMock.setUpdateDate(updateDate);
		entityMock.setUpdateUser(4L);

		// Mock service
		when(repositoryMock.findById(1L)).thenReturn(Optional.of(entityMock));

		// Invoke service
		EntityDTO entityResult = service.findEntityById(1L);

		// Assertions
		assertNotNull(entityResult);
		assertEquals(1L, entityResult.getEntityId().longValue());
		assertEquals("0001/2019", entityResult.getEntityCode());
		assertEquals("title", entityResult.getEntityTitle());
		assertEquals("description", entityResult.getEntityDescription());
		assertEquals(2L, entityResult.getEntityTypeId().longValue());
		assertEquals(createDate, entityResult.getCreateDate());
		assertEquals(3L, entityResult.getCreateUser().longValue());
		assertEquals(updateDate, entityResult.getUpdateDate());
		assertEquals(4L, entityResult.getUpdateUser().longValue());

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

		// Prepare data entity 1
		Date createDate1 = new Date();
		Date updateDate1 = new Date();
		EntityDAO entityMock1 = new EntityDAO();
		entityMock1.setEntityId(1L);
		entityMock1.setEntityCode("0001/2019");
		entityMock1.setEntityTitle("title");
		entityMock1.setEntityDescription("description");
		entityMock1.setEntityTypeId(2L);
		entityMock1.setCreateDate(createDate1);
		entityMock1.setCreateUser(3L);
		entityMock1.setUpdateDate(updateDate1);
		entityMock1.setUpdateUser(4L);
		
		// Prepare data entity 2
		Date createDate2 = new Date();
		Date updateDate2 = new Date();
		EntityDAO entityMock2 = new EntityDAO();
		entityMock2.setEntityId(1L);
		entityMock2.setEntityCode("0001/2019");
		entityMock2.setEntityTitle("title");
		entityMock2.setEntityDescription("description");
		entityMock2.setEntityTypeId(2L);
		entityMock2.setCreateDate(createDate2);
		entityMock2.setCreateUser(3L);
		entityMock2.setUpdateDate(updateDate2);
		entityMock2.setUpdateUser(4L);
		
		// Prepare mock list
		List<EntityDAO> entityListMock = new ArrayList<EntityDAO>();
		entityListMock.add(entityMock1);
		entityListMock.add(entityMock2);

		// Mock service
		when(repositoryMock.findAll()).thenReturn(entityListMock);

		// Invoke service
		List<EntityDTO> resultList = service.findAllEntities();

		// Assertions
		assertNotNull(resultList);
		assertEquals(2, resultList.size());

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
		newEntity.setEntityCode("0001/2019");
		newEntity.setEntityTitle("title");
		newEntity.setEntityDescription("description");
		newEntity.setEntityTypeId(2L);
		newEntity.setCreateUser(3L);
		EntityDTO entityResult = service.createEntity(newEntity);

		// Assertions
		assertNotNull(entityResult);
		assertNotNull(entityResult.getEntityId());
		assertEquals("0001/2019", entityResult.getEntityCode());
		assertEquals("title", entityResult.getEntityTitle());
		assertEquals("description", entityResult.getEntityDescription());
		assertEquals(2L, entityResult.getEntityTypeId().longValue());
		assertNotNull(entityResult.getCreateDate());
		assertEquals(3L, entityResult.getCreateUser().longValue());
		assertNotNull(entityResult.getUpdateDate());
		assertEquals(3L, entityResult.getUpdateUser().longValue());
		assertEquals(entityResult.getCreateDate(), entityResult.getUpdateDate());
		assertNull(entityResult.getDeleteDate());

	}

	@Test
	public void testUpdateEntity() throws ParseException {

		// Prepare data
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date createDate = simpleDateFormat.parse("2018-09-09");
		EntityDAO entityMock = new EntityDAO();
		entityMock.setEntityId(1L);
		entityMock.setEntityCode("0001/2019");
		entityMock.setEntityTitle("title");
		entityMock.setEntityDescription("description");
		entityMock.setEntityTypeId(2L);
		entityMock.setCreateDate(createDate);
		entityMock.setCreateUser(3L);
		entityMock.setUpdateDate(createDate);
		entityMock.setUpdateUser(3L);

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
		updateEntity.setEntityCode("0002/2019");
		updateEntity.setEntityTitle("update title");
		updateEntity.setEntityDescription("update description");
		updateEntity.setEntityTypeId(20L);
		updateEntity.setUpdateUser(4L);
		EntityDTO entityResult = service.updateEntity(updateEntity, 1L);

		// Assertions
		assertNotNull(entityResult);
		assertEquals(1L, entityResult.getEntityId().longValue());
		assertEquals("0002/2019", entityResult.getEntityCode());
		assertEquals("update title", entityResult.getEntityTitle());
		assertEquals("update description", entityResult.getEntityDescription());
		assertEquals(20L, entityResult.getEntityTypeId().longValue());
		assertEquals(createDate, entityResult.getCreateDate());
		assertEquals(3L, entityResult.getCreateUser().longValue());
		assertNotNull(entityResult.getUpdateDate());
		assertNotEquals(entityResult.getCreateDate(), entityResult.getUpdateDate());
		assertEquals(4L, entityResult.getUpdateUser().longValue());
		assertNull(entityResult.getDeleteDate());

	}

	@Test
	public void testUpdateEntity_NotFound() {

		// Mock service
		when(repositoryMock.findById(1L)).thenReturn(Optional.empty());

		// Invoke service
		EntityDTO updateEntity = new EntityDTO();
		updateEntity.setEntityCode("0001/2019");
		updateEntity.setEntityTitle("title");
		updateEntity.setEntityDescription("description");
		updateEntity.setEntityTypeId(2L);
		updateEntity.setUpdateUser(4L);
		EntityDTO entityResult = service.updateEntity(updateEntity, 1L);

		// Assertions
		assertNull(entityResult);

	}

	@Test
	public void testDeleteEntity() throws ParseException {

		// Prepare data
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = simpleDateFormat.parse("2018-09-09");
		EntityDAO entityMock = new EntityDAO();
		entityMock.setEntityId(1L);
		entityMock.setEntityCode("0001/2019");
		entityMock.setEntityTitle("title");
		entityMock.setEntityDescription("description");
		entityMock.setEntityTypeId(2L);
		entityMock.setCreateDate(date);
		entityMock.setCreateUser(3L);
		entityMock.setUpdateDate(date);
		entityMock.setUpdateUser(3L);
		

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
		EntityDTO deleteEntity = new EntityDTO();
		deleteEntity.setEntityCode("0001/2019");
		deleteEntity.setEntityTitle("title");
		deleteEntity.setUpdateUser(2L);
		EntityDTO entityResult = service.deleteEntity(deleteEntity, 1L);
		
		// Assertions
		assertNotNull(entityResult);
		assertEquals(1L, entityResult.getEntityId().longValue());
		assertNotEquals(date, entityResult.getUpdateDate());
		assertEquals(2L, entityResult.getUpdateUser().longValue());
		assertNotNull(entityResult.getDeleteDate());

	}

	@Test
	public void testDeleteEntity_NotFound() {

		// Mock services
		when(repositoryMock.findById(1L)).thenReturn(Optional.empty());

		// Invoke service
		// Invoke service
		EntityDTO deleteEntity = new EntityDTO();
		deleteEntity.setEntityCode("0001/2019");
		deleteEntity.setEntityTitle("title");
		deleteEntity.setUpdateUser(2L);
		EntityDTO entityResult = service.deleteEntity(deleteEntity, 1L);

		// Assertions
		assertNull(entityResult);
	}

}
