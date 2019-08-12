package com.metamagic.order.entities;


import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public abstract class BaseEntity implements Serializable {
 
	@JsonIgnore
	@Field(name = "auditDetails")
	private AuditDetails auditDetails; 

    @JsonIgnore
    @Field(name = "active")
	private boolean active = true;

}