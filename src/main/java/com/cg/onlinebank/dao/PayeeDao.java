package com.cg.onlinebank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlinebank.bean.Payee;
@Repository
public interface PayeeDao extends JpaRepository<Payee, Long>{

}
