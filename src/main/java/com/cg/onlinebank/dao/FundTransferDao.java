package com.cg.onlinebank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlinebank.bean.FundTransfer;
@Repository
public interface FundTransferDao extends JpaRepository<FundTransfer, Long>{

}
