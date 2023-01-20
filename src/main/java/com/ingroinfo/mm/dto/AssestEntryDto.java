package com.ingroinfo.mm.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class AssestEntryDto {
		
	private Long assestEntryId;
	private String division ;
	private String subDivision;
	private String assestsName;
	private String assetId;
	private String category;
	private String subCategory;
	private String model;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date purchaseDate;
	private String assestType;
	private String assignedTo;
	private String barCode;
	private String hsnCode;
	private String cost;
	private String assestsLocation;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date retunedDate;
	private String storeName;

}
