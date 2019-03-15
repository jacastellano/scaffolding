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
		entityDTO.setEntityTitle(entityDAO.getEntityTitle());
		entityDTO.setEntityDescription(entityDAO.getEntityDescription());
		entityDTO.setCreateDate(entityDAO.getCreateDate());
		entityDTO.setUpdateDate(entityDAO.getUpdateDate());
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
		entityDAO.setEntityTitle(entityDTO.getEntityTitle());
		entityDAO.setEntityDescription(entityDTO.getEntityDescription());
		entityDAO.setCreateDate(entityDTO.getCreateDate());
		entityDAO.setUpdateDate(entityDTO.getUpdateDate());
		entityDAO.setDeleteDate(entityDTO.getDeleteDate());
		return entityDAO;
	}

}
