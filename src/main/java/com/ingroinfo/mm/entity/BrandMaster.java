package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "MM_BRAND_MASTER")
public class BrandMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long brandMasterId;
	private String brandName;
	private String brandDtls;
}
