package com.cg.onlinebank.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
public class FundTransfer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fun_generator")
	@SequenceGenerator(name="fun_generator", sequenceName = "fun_seq", initialValue = 100)	
	private long fundTransferId;
	private long accountId;
	private long payeeAccountId;
	private String dateOfTransfer;
	@Min(value=0,message="Negative amount not allowed")
	private double transferAmount;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Payee payee;
	
	
}
