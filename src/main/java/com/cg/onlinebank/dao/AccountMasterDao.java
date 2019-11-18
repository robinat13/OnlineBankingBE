package com.cg.onlinebank.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlinebank.bean.AccountMaster;

@Transactional
@Repository
public interface AccountMasterDao extends JpaRepository<AccountMaster, Long> {

	@Query("from AccountMaster where activation=:status")
	public List<AccountMaster> getAccountRequests(@Param("status") String status);
	
	
	@Modifying
	@Query("update AccountMaster am set am.activation='REJECTED' where am.accountId=:id")
	public void rejectAccount(@Param("id") long accountId);


	@Modifying
	@Query("update AccountMaster am set am.activation='ACTIVATED' where am.accountId=:id")
	public void activateAccount(@Param("id") long accountId);


	@Query("from AccountMaster where activation='ACTIVATED' and CUSTOMER_CUSTOMER_ID=:custId")
	public List<AccountMaster> getAccountsByCustomerId(@Param("custId") long custId);

	

}
