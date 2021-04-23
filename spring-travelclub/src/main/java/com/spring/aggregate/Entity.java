package com.spring.aggregate;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Entity {
	//
	protected String id;

	protected Entity() {
		//
		this.id = UUID.randomUUID().toString();
	}

	protected Entity(String id) {
		//
		this.id = id;
	}
}
