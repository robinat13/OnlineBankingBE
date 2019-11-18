package com.cg.onlinebank.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class Payee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payee_generator")
	@SequenceGenerator(name="payee_generator", sequenceName = "payee_seq", initialValue = 100)	
	private long id;
	private long payeeAccountId;
	private String nickName;
	
}
