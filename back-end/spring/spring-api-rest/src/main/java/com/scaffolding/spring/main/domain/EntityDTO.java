package com.scaffolding.spring.main.domain;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EntityDTO {

	private Long entityId;
	@NotNull
	@NotEmpty
	private String entityTitle;
	@NotNull
	@NotEmpty
	private String entityCode;
	private String entityDescription;
	private Date createDate;
	@NotNull
	private Long createUser;
	private Date updateDate;
	@NotNull
	private Long updateUser;
	private Date deleteDate;

}
