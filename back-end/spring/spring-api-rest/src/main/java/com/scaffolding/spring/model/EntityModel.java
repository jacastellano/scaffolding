package com.scaffolding.spring.model;

import lombok.Data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class EntityModel {

	private @Id @GeneratedValue Long entityId;
	private String entityTitle;
	private String entityDescription;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;

	public EntityModel() {

	}

	public EntityModel(String entityTitle, String entityDescription) {
		this.entityTitle = entityTitle;
		this.entityDescription = entityDescription;
		this.createDate = new Date();
	}

}
