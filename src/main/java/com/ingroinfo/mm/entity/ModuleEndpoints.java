package com.ingroinfo.mm.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "module_endpoints")
public class ModuleEndpoints {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer endpointId;
	private Integer pageno;
	private String endpoint;
	private String httpMethod;
	
	
	@ManyToOne
	@JoinColumn(name = "user_access_id")
	private UserAccess userAccess;
	
	public ModuleEndpoints(Integer pageno, String endpoint,String httpMethod) {
		super();
		this.pageno = pageno;
		this.endpoint = endpoint;
		this.httpMethod = httpMethod;
	}
	
}
