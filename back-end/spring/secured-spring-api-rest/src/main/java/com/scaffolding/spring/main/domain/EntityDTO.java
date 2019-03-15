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
	private String entityDescription;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;

}
