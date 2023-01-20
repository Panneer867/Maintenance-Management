package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="MM_UNIT_MEASURE")
public class UnitMeasure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long unitMeasureId;
	private String unitType;
	private String description;
}
