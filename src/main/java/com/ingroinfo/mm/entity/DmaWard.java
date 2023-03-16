package com.ingroinfo.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="MM_DMA_WARD")
public class DmaWard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dmaWardId;
	@Column(length = 100)
	private String dmaNumber;
	private String dmaName;
	@Column(length = 100)
	private String wardNumber;
	private String wardName;
}
