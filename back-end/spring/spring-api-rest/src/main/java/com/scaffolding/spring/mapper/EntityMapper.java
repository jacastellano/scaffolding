package com.scaffolding.spring.mapper;

import com.scaffolding.spring.main.domain.EntityDTO;
import com.scaffolding.spring.main.persistence.domain.EntityDAO;

public class EntityMapper {

	private EntityMapper() {
	}

	/**
	 * 
	 * @param entityDAO
	 * @return
	 */
	public static EntityDTO convert(EntityDAO entityDAO) {
		EntityDTO entityDTO = new EntityDTO();
		entityDTO.setEntityId(entityDAO.getEntityId());
		entityDTO.setEntityCode(entityDAO.getEntityCode());
		entityDTO.setEntityTitle(entityDAO.getEntityTitle());
		entityDTO.setEntityDescription(entityDAO.getEntityDescription());
		entityDTO.setEntityTypeId(entityDAO.getEntityTypeId());
		entityDTO.setCreateDate(entityDAO.getCreateDate());
		entityDTO.setCreateUser(entityDAO.getCreateUser());
		entityDTO.setUpdateDate(entityDAO.getUpdateDate());
		entityDTO.setUpdateUser(entityDAO.getUpdateUser());
		entityDTO.setDeleteDate(entityDAO.getDeleteDate());
		return entityDTO;
	}

	/**
	 * 
	 * @param entityDTO
	 * @return
	 */
	public static EntityDAO convert(EntityDTO entityDTO) {
		EntityDAO entityDAO = new EntityDAO();
		entityDAO.setEntityId(entityDTO.getEntityId());
		entityDAO.setEntityCode(entityDTO.getEntityCode());
		entityDAO.setEntityTitle(entityDTO.getEntityTitle());
		entityDAO.setEntityDescription(entityDTO.getEntityDescription());
		entityDAO.setEntityTypeId(entityDTO.getEntityTypeId());
		entityDAO.setCreateDate(entityDTO.getCreateDate());
		entityDAO.setCreateUser(entityDTO.getCreateUser());
		entityDAO.setUpdateDate(entityDTO.getUpdateDate());
		entityDAO.setUpdateUser(entityDTO.getUpdateUser());
		entityDAO.setDeleteDate(entityDTO.getDeleteDate());
		return entityDAO;
	}

}
