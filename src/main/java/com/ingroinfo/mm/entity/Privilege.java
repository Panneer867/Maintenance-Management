package com.ingroinfo.mm.entity;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;	
	private int pageno;
	private String httpMethod;

	@ManyToMany(mappedBy = "privileges")
	private Collection<Role> roles;

	public Privilege(int pageno,String name,String httpMethod) {
		super();
		this.pageno = pageno;
		this.name = name;
		this.httpMethod = httpMethod;
	}
}
