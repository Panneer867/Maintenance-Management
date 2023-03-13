package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="MM_HSN_CODE")
public class HsnCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hsnCodeId;
	private String categoryName;	
	private String hsnCode;
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category category;
}
