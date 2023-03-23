package com.ingroinfo.mm.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
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
	@CreationTimestamp
	private Date createdDate;
}
