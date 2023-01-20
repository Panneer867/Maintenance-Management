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
@Table(name="MM_PIPE_MANUFACTURE")
public class PipeManufacture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pipemanufId;
	@Column(name="MANUFACTURE_ID",length = 10)
	private String manufactureId;
	@Column(name="PIPE_TYPE",length = 50)
	private String pipeType;
	@Column(name="MANUFACTURE_NAME",length = 100)
	private String manufactureName;
	@Column(name="PIPE_SIZE",length = 10)
	private String size;
	@Column(name="CONTACTNO",length = 13)
	private String contactNo;
	@Column(name="MANUFACTURE_ADD",length = 100)
	private String manufactAddress;
}
