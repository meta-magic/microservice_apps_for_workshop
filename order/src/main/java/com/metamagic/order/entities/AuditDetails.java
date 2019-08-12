package com.metamagic.order.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditDetails {

	private Integer version;

	private String createdBy;

	private Date createdDate;

	private String updatedBy;

	private Date updatedDate;
	
}
