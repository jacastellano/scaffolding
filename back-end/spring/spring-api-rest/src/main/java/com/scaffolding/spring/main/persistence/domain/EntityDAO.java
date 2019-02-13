package com.scaffolding.spring.main.persistence.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EntityDAO {

	private @Id @GeneratedValue Long entityId;
	private String entityTitle;
	private String entityDescription;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;

	public EntityDAO() {

	}

	public EntityDAO(String entityTitle, String entityDescription) {
		this.entityTitle = entityTitle;
		this.entityDescription = entityDescription;
		this.createDate = new Date();
	}

}
