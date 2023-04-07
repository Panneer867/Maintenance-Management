package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="MM_TEAM_CODE")
public class TeamCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teamCodeId;
	private String teamCode;
	private String siteEnginner;
	private String siteSuperwiser;
}
