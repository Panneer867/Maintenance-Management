package com.ingroinfo.mm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mm_approved_stocks_return")
public class ApprovedStocksReturn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recordId;
	private Long workOrderNo;
	private String itemId;
	private String itemName;	
	private String unitOfMeasure;
	private String category;
	private Double mrpRate;
	private Double orderTotalCost;
	private Double returnTotalCost;
	private Double igst;
	private Double sgst;
	private Double cgst;
	private int orderQuantity;
	private int returnQuantity;
	private String returnReason;
	private String stockType;
	private String description;
	private String itemImage;
	private String imagePath;
	private String username;
	private String invoiceNo;
	private String returnEntryDate;
	
	private String indentNo;
	private String complNo;
	private String departmentName;
		
	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

}
