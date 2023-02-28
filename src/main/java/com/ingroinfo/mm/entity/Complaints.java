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
	@Column(name = "COMPL_DATE",length = 10)
	private String complDate;
	@Column(name = "DEPARTMENT",length = 150)
	private String department;
	@Column(name = "DOCKET_NO",length = 20)
	private String docketNo;
	@Column(name = "RECIVED_BY",length = 50)
	private String recivedBy;
	@Column(name = "FIRST_NAME")
	private String firstname;
	@Column(name = "LAST_NAME")
	private String lastname;
	@Column(name = "HOUSE_NO")
	private String houseNo;
	@Column(name = "CROSS")
	private String cross;
	@Column(name = "MAIN")
	private String main;
	@Column(name = "LANDMARK")
	private String landMark;
	@Column(name = "ZONE")
	private String zone;
	@Column(name = "WARD")
	private String ward;
	@Column(name = "CITY")
	private String city;
	@Column(name = "TARIFF")
	private String trraif;
	@Column(name = "SERVICE_LOC")
	private String serviceLocat;
	@Column(name = "DMA")
	private String dma;
	@Column(name = "PINCODE")
	private String pinCode;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	@Column(name = "LANDLINE_NO")
	private String landLineNo;
	@Column(name = "REF_NO")
	private String refcontactNo;
	@Column(name = "EMAIL_ID")
	private String emailid;
	@Column(name = "REG_COMPLAIN")
	private String regOfCompl;
	@Column(name = "BISS_DEPT")
	private String businessDept;
	@Column(name = "SUBCATEGORY")
	private String subCategory;
	@Column(name = "COMPL_STS")
	private String complStatus;
	@Column(name = "JOB_CODE")
	private String jobCode;
	@Column(name = "COMPL_DTLS")
	private String complDetails;
	@Column(name = "SLG")
	private String slg;
	@Column(name = "SLG_DURATION")
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
