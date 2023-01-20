package com.ingroinfo.mm.entity;

import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "mm_role_master")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;

	@CreationTimestamp
	private Date dateCreated;
	
	@UpdateTimestamp
	private Date lastUpdated;

	@JsonIgnore
	@ManyToMany(mappedBy = "roles")
	private Collection<User> users;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.DETACH)
	@JoinTable(name = "mm_role_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "page_no", referencedColumnName = "pageNo"))
	private Collection<Privilege> privileges;

	public Role(String name, String description) {

		this.name = name;
		this.description = description;
	}

	public Role(Long id, String name, String description, Date dateCreated, Date lastUpdated) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
	}

}
