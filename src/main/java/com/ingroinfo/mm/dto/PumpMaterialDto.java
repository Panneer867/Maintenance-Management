package com.ingroinfo.mm.dto;

import java.sql.Date;
import com.ingroinfo.mm.entity.VehicleDtls;

import lombok.Data;

@Data
public class PumpMaterialDto {

	private Long pumMaterialId;
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
	private String categoryName;
	private String itemName;
	private String unitOfMesure;
	private String hsnCode;
	private String quantity;
	private String stockType;
	private String indentType;
	private String empCategory;
	private String members;
	private String daysRequired;
	private String timeRequired;
	private String vehicleType;
	private String vehicleNo;
	private String driverName;
	private String driverPhone;
	private String meterReading;
	private String stratTime;
		
	private VehicleDtls vehicle;
}
