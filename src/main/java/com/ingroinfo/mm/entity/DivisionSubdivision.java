package com.ingroinfo.mm.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="MM_DIVISION_SUBDIVISION")
public class DivisionSubdivision {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long divsubId;
	private String division;
	private String subdivision;
	private String serviceStation;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
}
