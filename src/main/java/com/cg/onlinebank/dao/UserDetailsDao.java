package com.cg.onlinebank.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.onlinebank.bean.UserDetails;

@Repository
public interface UserDetailsDao extends JpaRepository<UserDetails, Long> {

	@Query("from UserDetails where userName=:u")
	UserDetails getCustomerByUsername(@Param("u") String username);
	
	Optional<UserDetails> findByUserName(String userName); 

}
