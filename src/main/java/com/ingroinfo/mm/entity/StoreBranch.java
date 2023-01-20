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
@Table(name="MM_STORE_BRANCH")
public class StoreBranch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long strbraNameId;
	@Column(name="COMPANY_DIVISION")
	private String companyDivision;
	@Column(name="STORE_BRANCH_ID")
	private String storeBranchId;
	@Column(name="STORE_BRANCH_NAME")
	private String stroreBranchName;
	@Column(name="STORE_BRANCH_ADDRESS")
	private String storeBranchAdd;
	@Column(name="STORE_BRANCH_CONTACT_NO")
	private String storeBranchContactNo;
}
