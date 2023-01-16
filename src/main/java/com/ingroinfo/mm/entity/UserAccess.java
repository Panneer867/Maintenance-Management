package com.ingroinfo.mm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_access")
public class UserAccess {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accessId;
	private String userAccess;
	private int userId;
	private int roleId;
	
	
	@OneToMany
	@JoinColumn(name = "uri_id")
	private List<ModuleEndpoints> moduleEndpoints;
	

}
