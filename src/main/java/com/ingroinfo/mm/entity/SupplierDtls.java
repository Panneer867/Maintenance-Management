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
@Table(name="MM_SUPPLIER_DETAILS")
public class SupplierDtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long suplyDtlsId;
	@Column(name="SUPPLIER_ID",length = 10)
	private String supplierId;
	@Column(name = "RECIPT_NO", length = 20)
	private String reciptNo;
	@Column(name = "SUPPLIER_NAME")
	private String supplierName;
	@Column(name = "GST_TYPE")
	private String gstType;
	@Column(name = "CONTACT_NO",length = 13)
	private String contactNo;
	@Column(name = "SUPPLIER_ADDESS")
	private String supplierAdd;
	@Column(name = "EMAIL_ID",length = 50)
	private String emailId;
	@Column(name = "GSTIN")
	private String gstIn;
	@Column(name = "PHONE_NO",length = 13)
	private String phoneNo;
	@Column(name = "STATE",length = 50)
	private String state;
	@Column(name = "CITY",length = 50)
	private String city;
	@Column(name = "PINCODE",length = 10)
	private String pincode;
}
