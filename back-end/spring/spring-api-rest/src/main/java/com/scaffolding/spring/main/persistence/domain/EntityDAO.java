package com.scaffolding.spring.main.persistence.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ENTITY", schema = "entity")
public class EntityDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long entityId;

	@Column(name = "CODE")
	private String entityCode;

	@Column(name = "TITLE")
	private String entityTitle;

	@Column(name = "DESCRIPTION")
	private String entityDescription;

	@Column(name = "ENTITY_TYPE_ID")
	private Long entityTypeId;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "CREATE_USER")
	private Long createUser;

	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	@Column(name = "UPDATE_USER")
	private Long updateUser;

	@Column(name = "DELETE_DATE")
	private Date deleteDate;

	public EntityDAO() {

	}

	public EntityDAO(String entityCode, String entityTitle, String entityDescription, Long createUser, Long updateUser) {
		super();
		Date currentDate = new Date();
		this.entityCode = entityCode;
		this.entityTitle = entityTitle;
		this.entityDescription = entityDescription;
		this.createDate = currentDate;
		this.createUser = createUser;
		this.updateDate = currentDate;
		this.updateUser = updateUser;
	}

}
