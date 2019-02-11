package com.scaffolding.spring.model;

import java.util.Date;

public class EntityModel {

	private int entityId;
	private String entityTitle;
	private String entityDescription;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;

	public EntityModel() {

	}

	public EntityModel(int entityId, String entityTitle, String entityDescription, Date createDate, Date updateDate,
			Date deleteDate) {
		super();
		this.entityId = entityId;
		this.entityTitle = entityTitle;
		this.entityDescription = entityDescription;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.deleteDate = deleteDate;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getEntityTitle() {
		return entityTitle;
	}

	public void setEntityTitle(String entityTitle) {
		this.entityTitle = entityTitle;
	}

	public String getEntityDescription() {
		return entityDescription;
	}

	public void setEntityDescription(String entityDescription) {
		this.entityDescription = entityDescription;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

}
