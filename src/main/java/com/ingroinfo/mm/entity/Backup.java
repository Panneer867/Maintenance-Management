package com.ingroinfo.mm.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mm_backup_scheduler")
public class Backup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int backupId;
	private String schedule;
	private String time;
	private String drive;
	private String path;
	
}
