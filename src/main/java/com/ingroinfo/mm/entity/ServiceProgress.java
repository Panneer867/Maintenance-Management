package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="MM_SERVICE_PROGRESS")
public class ServiceProgress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long servcProgressId;
	private String sevcProgress;
}
