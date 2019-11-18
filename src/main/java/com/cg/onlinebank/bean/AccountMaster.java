package com.cg.onlinebank.bean;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@Table(name = "AccountMaster")
public class AccountMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_generator")
	@SequenceGenerator(name="acc_generator", sequenceName = "acc_seq", initialValue = 100)	
	private long accountId;
	@Pattern(regexp = "(SAVING|CURRENT|CREDIT)",message = "Given account type not allowed")
	private String accountType;
	@Min(value = 0,message = "Balance cannot be in negative")
	private double accountBalance;
	private String openDate;
	@Pattern(regexp = "(PENDING|ACTIVATED|REJECTED)",message = "Unknown status code")
	private String activation="PENDING";
	//@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	private Customer customer;
	
	//@JsonIgnore
	@OneToMany(mappedBy = "accmaster")
	private List<Transactions> transactions;
	
}
