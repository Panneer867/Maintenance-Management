package com.ingroinfo.mm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mm_rejected_stockorders")
public class RejectedStockOrders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private String billedOn;
	private String gstType;
	private Double igst;
	private Double sgst;
	private Double cgst;
	private Double subTotal;
	private Double grandTotal;
	private Long stockOrderNo;
	private String indentNo;
	private String complNo;
	private String departmentName;
	private String username;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
	private List<RejectedStockOrderItems> workOrderItems;

}
