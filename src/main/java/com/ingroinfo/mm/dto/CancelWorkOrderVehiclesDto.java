package com.ingroinfo.mm.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class CancelWorkOrderVehiclesDto {

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
	private Date createdDate;
}
