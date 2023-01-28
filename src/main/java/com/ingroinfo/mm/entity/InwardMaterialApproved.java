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
@Table(name = "mm_approved_inward_materials")
public class InwardMaterialApproved {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long materialId;
	private Long itemId;
	private String itemName;
	private String aliasName;
	private String categoryName;
	private String brand;
	private String hsnCode;
	private String unitOfMeasure;
	
	@Column(name = "quantity")
	private String totalQuantity;
	private String supplier;
	private String suppliedOn;
	private String gstType;
	private String invoiceNo;
	private String receivedBy;
	private String receivedDate;
	private String entryDate;
	private String approvedBy;
	private String username;
	private String imagePath;
	private String description;
	private String materialImage;
	private Double costRate;
	private Double mrp;
	private Double igst;
	private Double sgst;
	private Double cgst;
	private Double grandTotal;
	private Double subTotal;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

}
