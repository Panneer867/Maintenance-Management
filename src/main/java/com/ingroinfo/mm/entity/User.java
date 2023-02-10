package com.ingroinfo.mm.entity;

import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mm_user_management")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String name;
	private String username;
	private String password;
	private String email;
	private String mobile;
	private String userType;
	private String designation;	
	private String remarks;
	private Long ubarmsUserId;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branch;

	@CreationTimestamp
	private Date dateCreated;

	@UpdateTimestamp
	private Date lastUpdated;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinTable(name = "mm_users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

	public User(String name, String username, String password, String email, String mobile, String userType,
			String designation, Long ubarmsUserId) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.userType = userType;
		this.designation = designation;
		this.ubarmsUserId = ubarmsUserId;
	}

}
