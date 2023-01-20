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
@Table(name="MM_SPARE_EQUIPMENT")
public class SpareEquipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long spareequiId;
	@Column(name="SPARE_EQUIPMENT_ID")
	private String spareEquipmentId;
	@Column(name="SPARE_eQUIPMENT_NAME")
	private String spareEquipmentName;
}
