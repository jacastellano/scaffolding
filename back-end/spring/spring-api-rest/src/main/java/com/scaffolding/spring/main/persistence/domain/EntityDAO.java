package com.scaffolding.spring.main.persistence.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "ENTITY", schema = "entity")
public class EntityDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long entityId;

	@NotNull
	@NotEmpty
	@Column(name = "CODE")
	private String entityCode;

	@NotNull
	@NotEmpty
	@Column(name = "TITLE")
	private String entityTitle;

	@Column(name = "DESCRIPTION")
	private String entityDescription;

	@NotNull
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@NotNull
	@Column(name = "CREATE_USER")
	private Long createUser;

	@NotNull
	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	@NotNull
	@Column(name = "UPDATE_USER")
	private Long updateUser;

	@Column(name = "DELETE_DATE")
	private Date deleteDate;

	public EntityDAO() {

	}

	public EntityDAO(@NotNull @NotEmpty String entityCode, @NotNull @NotEmpty String entityTitle,
			String entityDescription, @NotNull @NotEmpty Long createUser) {
		super();
		Date currentDate = new Date();
		this.entityCode = entityCode;
		this.entityTitle = entityTitle;
		this.entityDescription = entityDescription;
		this.createUser = createUser;
		this.createDate = currentDate;
		this.updateUser = createUser;
		this.updateDate = currentDate;
	}

}
