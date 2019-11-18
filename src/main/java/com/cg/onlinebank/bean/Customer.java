package com.cg.onlinebank.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
public class Customer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cus_generator")
	@SequenceGenerator(name="cus_generator", sequenceName = "cus_seq", initialValue = 100)	
	private long customerId;
	
	private String customerName;
	@Email
	@Column(unique = true)
	private String email;
	private String address;
	private long panCard;
	private String gender;
	private String mobile;
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<AccountMaster> accmaster;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserDetails user;
	
	
	
}
