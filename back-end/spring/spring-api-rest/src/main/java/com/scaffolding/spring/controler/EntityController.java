package com.scaffolding.spring.controler;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scaffolding.spring.model.EntityModel;

@RestController
public class EntityController {

	@RequestMapping("/entity")
	public EntityModel getEntity(@RequestParam(value = "entityId") int entityId) {

		EntityModel entity = new EntityModel();
		entity.setEntityId(entityId);
		entity.setEntityTitle("RestController");
		entity.setEntityDescription("Rest Controller Example");
		entity.setCreateDate(new Date());
		return entity;
	}

}
