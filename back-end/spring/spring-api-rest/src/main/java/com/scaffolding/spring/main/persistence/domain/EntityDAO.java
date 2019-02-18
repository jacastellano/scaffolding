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

	@Column(name = "TITLE")
	private String entityTitle;

	@Column(name = "DESCRIPTION")
	private String entityDescription;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	@Column(name = "DELETE_DATE")
	private Date deleteDate;

	public EntityDAO() {

	}

	public EntityDAO(String entityTitle, String entityDescription) {
		this.entityTitle = entityTitle;
		this.entityDescription = entityDescription;
		this.createDate = new Date();
	}

	public EntityDAO(Long entityId, String entityTitle, String entityDescription, Date createDate, Date updateDate,
			Date deleteDate) {
		this.entityId = entityId;
		this.entityTitle = entityTitle;
		this.entityDescription = entityDescription;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.deleteDate = deleteDate;
	}

}
