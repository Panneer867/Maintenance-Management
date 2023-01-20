package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name ="MM_ID_MASTER")
public class IdMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long masterId;		
	private String masterIdName;	
	private String statNumber;
	private String idDescription;		
}
