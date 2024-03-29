package com.ingroinfo.mm.entity;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "mm_privilege_master")
public class Privilege {

	@Id
	private int pageNo;
	private String name;	

	@ManyToMany(mappedBy = "privileges")
	private Collection<Role> roles;

	public Privilege(int pageNo,String name) {
		super();
		this.pageNo = pageNo;
		this.name = name;
	
	}
}
