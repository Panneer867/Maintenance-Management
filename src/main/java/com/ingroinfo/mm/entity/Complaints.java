package com.ingroinfo.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "MM_COMPLAINTS")
@Data
public class Complaints {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long complId;
	@Column(name = "COMPL_NO",length = 10)
	private String complNo;
	@Column(name = "COMPL_DATE",length = 50)
	private String complDate;
	@Column(name = "DEPARTMENT",length = 150)
	private String department;
	@Column(name = "DOCKET_NO",length = 20)
	private String docketNo;
	@Column(name = "RECIVED_BY",length = 50)
	private String recivedBy;
	@Column(name = "FIRST_NAME",length = 50)
	private String firstname;
	@Column(name = "LAST_NAME",length = 50)
	private String lastname;
	@Column(name = "HOUSE_NO",length = 20)
	private String houseNo;
	@Column(name = "CROSS",length = 50)
	private String cross;
	@Column(name = "MAIN",length = 50)
	private String main;
	@Column(name = "LANDMARK",length = 50)
	private String landMark;
	@Column(name = "ZONE",length = 50)
	private String zone;
	@Column(name = "WARD",length = 10)
	private String ward;
	@Column(name = "CITY",length = 30)
	private String city;
	@Column(name = "TARIFF",length = 10)
	private String trraif;
	@Column(name = "SERVICE_LOC",length = 100)
	private String serviceLocat;
	@Column(name = "DMA",length = 50)
	private String dma;
	@Column(name = "PINCODE",length = 10)
	private String pinCode;
	@Column(name = "GENDER",length = 50)
	private String gender;
	@Column(name = "MOBILE_NO",length = 13)
	private String mobileNo;
	@Column(name = "LANDLINE_NO",length = 13)
	private String landLineNo;
	@Column(name = "REF_NO",length = 20)
	private String refcontactNo;
	@Column(name = "EMAIL_ID",length = 30)
	private String emailid;
	@Column(name = "REG_COMPLAIN",length = 30)
	private String regOfCompl;
	@Column(name = "BISS_DEPT",length = 50)
	private String businessDept;
	@Column(name = "SUBCATEGORY",length = 50)
	private String subCategory;
	@Column(name = "COMPL_STS",length = 50)
	private String complStatus;
	@Column(name = "JOB_CODE",length = 50)
	private String jobCode;
	@Column(name = "COMPL_DTLS",length = 200)
	private String complDetails;
	@Column(name = "SLG",length = 50)
	private String slg;
	@Column(name = "SLG_DURATION",length = 50)
	private String slgDuration;
	@Column(name = "ASSIGN_DATE")
	private String assignDate;
	@Column(name = "ENG_NAME")
	private String nameOfEngg;
	@Column(name = "SERVICE_STAT")
	private String serviceStat;
	
	@Column(name = "ESCLATE_FROM")
	private String esclatedFrom;
	@Column(name = "ESCLATE_FROM_NAME")
	private String esclatedFromName;
	@Column(name = "ESCLATE_FROM_ID")
	private String esclatedFromId;		
	@Column(name = "ESCLATE_TO")
	private String esclatedTo;
	
	@Column(name = "VISITED_DATE")
	private String visitedDate;
	@Column(name = "VISIT_REMARK")
	private String fieldVisitedRemark;
	@Column(name = "JOB_DONE_REMARK")
	private String jobDoneRemark;
	@Column(name = "DON_OR_NOT_DONE")
	private String jobDoneNotDone;
	@Column(name = "ESCLATE_DATE")
	private String esclatedDate;
	@Column(name = "ESCLATE_TO_ID")
	private String esclatedToId;
	@Column(name = "ESCLATE_TO_NAME")
	private String esclatedToName;
	@Column(name = "ESCLATED_RESON")
	private String esclatedReason;
	@Column(name = "ESCLATED_TYPE")
	private String esclationType;
	@Column(name = "ESCLATED_LAVEL")
	private String esclationLavel;
}
