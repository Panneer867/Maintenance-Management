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
@Table(name="MM_METER_MANUFACTURE")
public class MeterManufacture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mtrmanufacId;
	@Column(name="METER_ID",length = 20)
	private String meterId;
	@Column(name="METER_MANUFACTURE",length = 150)
	private String meterManufacture;
	@Column(name="METER_TYPE",length = 50)
	private String meterType;
	@Column(name="METER_DIGIT",length = 30)
	private String digit;
	@Column(name="RECEIVED_DATE",length = 20)
	private String receivedDate;
	@Column(name="METER_SL_NO",length = 20)
	private String meterSlNo;
	@Column(name="WARRANTY",length = 20)
	private String warranty;
	@Column(name="CONDITION",length = 20)
	private String condition;
}
