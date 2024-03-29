package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="MM_TAX_MASTER")
public class TaxMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taxMasterId;
	private String cGst;
	private String sGst;
	private String gstTotal;
}
