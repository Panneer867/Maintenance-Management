package com.ingroinfo.mm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mm_inward_material_bundles")
public class InwardMaterialBundles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bundleId;
	private String supplierName;
	private String suppliedOn;
	private String gstType;
	private Double igst;
	private Double sgst;
	private Double cgst;
	private Double subTotal;
	private Double grandTotal;
	private String invoiceNo;
	private String receivedBy;
	private String receivedDate;
	private String username;
	private int noOfMaterials;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "materialBundle")
	private List<InwardMaterials> inwardMaterials;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

}