package com.ingroinfo.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="MM_SERVICE_PROVIDER")
public class ServiceProvider {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long servProvId;
	@Column(name="SERVICE_PROVIDER_ID",length = 10)
	private String serviceProviderId;
	@Column(name="NATURE_OF_WORK",length = 50)
	private String natureOfWork;
	@Column(name="REGISTR_NO",length = 30)
	private String registerNo;
	@Column(name="STATUS",length = 50)
	private String status;
	@Column(name="CONTACT_NO",length = 13)
	private String contactNo;
	@Column(name="SERVICE_PROVIDER_NAME",length = 150)
	private String serviceProvdName;
	@Column(name="SERVICE_PROVIDER_ADD",length = 150)
	private String serviceProvdAdd;
	
}
