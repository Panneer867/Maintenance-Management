package com.ingroinfo.mm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bank")
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "bank_name")
	private String name;
	
	@CreationTimestamp
	private Date dateCreated;
	@UpdateTimestamp
	private Date lastUpdated;
	
	public Bank(String name) {
		super();
		this.name = name;
	}
	
	
}
