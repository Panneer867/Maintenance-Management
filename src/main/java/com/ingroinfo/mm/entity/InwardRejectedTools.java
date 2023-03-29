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
@Table(name = "mm_inward_rejected_tools")
public class InwardRejectedTools {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rejectToolId;
	private Long toolId;
	private String itemId;
	private String itemName;
	private String aliasName;
	private String itemImage;
	private String imagePath;
	private String category;
	private String brand;
	private String hsnCode;
	private String unitOfMeasure;
	private int quantity;
	private Double costRate;
	private Double mrpRate;
	private String entryDate;
	private String description;
	private String supplier;
	private String suppliedOn;
	private String gstType;
	private Double igst;
	private Double sgst;
	private Double cgst;
	private Double subTotal;
	private Double grandTotal;
	private String invoice;
	private String receivedBy;
	private String receivedDate;
	private String username;
	private String stockType;
	private String approvalStatus;
	
	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

}
