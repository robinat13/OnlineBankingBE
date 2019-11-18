package com.cg.onlinebank.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class UserDetails {
	
	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name="user_generator", sequenceName = "user_seq", initialValue = 100)	
	private long userId;
	@Column(unique = true)
	private String userName;
	@Setter(AccessLevel.NONE)
	private String password;
	@Pattern(regexp = "(ROLE_ADMIN|ROLE_USER)",message = "Given role not allowed")
	private String role;
	private String secretQuestion;
	private String transactionPassword;
	private String lockStatus;
	
	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}
	
	
	
	
}
