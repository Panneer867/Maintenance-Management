package com.ingroinfo.mm.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "state")
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "state_name")
	private String name;

	@OneToMany
	@JoinColumn(name = "state_id")
	private Set<City> cities;
	
	@CreationTimestamp
	private Date dateCreated;
	@UpdateTimestamp
	private Date lastUpdated;
	
	public State(String name) {
		super();
		this.name = name;
	}
	
	
	
}
