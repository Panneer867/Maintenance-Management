package com.ingroinfo.mm.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;

@Entity
@Data
@Table(name="MM_CANCEL_WORKORDER_VEHICLES")
public class CancelWorkOrderVehicles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicleReqId;
	private String indentNo;
	private String complNo;
	private String division;
	private String subDivision;
	private String workSite;
	private Date startDate;
	private Date endDate;
	private String contactNo;	
	private String complDtls;
	private String workPriority;
	private String vehicleType;
	private String vehicleNo;
	private String driverName;
	private String driverPhone;
	private String meterReading;
	private String stratTime;
	private Long vehicleId;
	private String departmentName;
	private String indentApproved;
	private String approvedSts;
	private String userName;
	@CreationTimestamp
	private Date createdDate;
}
