package com.cg.onlinebank.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tran_generator")
	@SequenceGenerator(name="tran_generator", sequenceName = "tran_seq", initialValue = 100)	
	private long tranId;
	private String tranDescription;
	private String tranDate;
	private String tranType;
	private double tranAmount;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private AccountMaster accmaster;
	
}
