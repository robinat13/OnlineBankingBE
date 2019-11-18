package com.cg.onlinebank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.onlinebank.bean.Transactions;

@Repository
public interface TransactionsDao extends JpaRepository<Transactions, Long> {

	@Query("from Transactions where ACCMASTER_ACCOUNT_ID=:accountId")
	List<Transactions> getTransactions(@Param("accountId") long accountId);

}
