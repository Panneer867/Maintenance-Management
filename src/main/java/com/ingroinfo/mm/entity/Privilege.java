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
@Table(name = "privilege_master")
public class Privilege {

	@Id
	private int pageNo;
	private String name;	
	private String httpMethod;

	@ManyToMany(mappedBy = "privileges")
	private Collection<Role> roles;

	public Privilege(int pageNo,String name,String httpMethod) {
		super();
		this.pageNo = pageNo;
		this.name = name;
		this.httpMethod = httpMethod;
	}
}
