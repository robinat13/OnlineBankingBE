package com.cg.onlinebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinebank.bean.AccountMaster;
import com.cg.onlinebank.bean.Customer;
import com.cg.onlinebank.bean.Transactions;
import com.cg.onlinebank.bean.UserDetails;
import com.cg.onlinebank.dao.AccountMasterDao;
import com.cg.onlinebank.dao.CustomerDao;
import com.cg.onlinebank.dao.TransactionsDao;
import com.cg.onlinebank.exception.OnlineBankException;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired AccountMasterDao accmasterDao;
	@Autowired CustomerDao custDao;
	@Autowired TransactionsDao transDao;
	
	@Override
	public List<Customer> allCustomers() throws OnlineBankException {
		try {
			return custDao.findAll();
		}catch(Exception ex) {
			throw new OnlineBankException(ex.getLocalizedMessage());
		}
	}

	@Override
	public List<AccountMaster> getAccountRequests(String status) throws OnlineBankException{
		try{
			return accmasterDao.getAccountRequests(status);
		}
		catch(Exception e) {
			throw new OnlineBankException(e.getLocalizedMessage());
		}
	}

	public List<AccountMaster> rejectAccount(long accountId) throws OnlineBankException {
		try {
		accmasterDao.rejectAccount(accountId);
		return accmasterDao.getAccountRequests("PENDING");
		}
		catch(Exception ex) {
		throw new OnlineBankException(ex.getLocalizedMessage());
		}	
	}
	
	public List<AccountMaster> activateAccount(long accountId) throws OnlineBankException {
		try {
		accmasterDao.activateAccount(accountId);
		return accmasterDao.getAccountRequests("PENDING");
		}
		catch(Exception ex) {
			throw new OnlineBankException(ex.getLocalizedMessage());
		}
	}

	public String configureSuperAdmin() throws OnlineBankException {
		UserDetails user = new UserDetails();
		Customer customer = new Customer();
		user.setLockStatus("UNLOCKED");
		user.setPassword("1234");
		user.setRole("ROLE_ADMIN");
		user.setSecretQuestion("Hue");
		user.setTransactionPassword("Hue");
		user.setUserName("robinat13");
		
		customer.setAddress("Somewhere safe");
		customer.setCustomerName("Robin Chauhan");
		customer.setEmail("robinthehood13@gmail.com");
		customer.setGender("Male");
		customer.setMobile("9639673979");
		customer.setPanCard(1234);
		customer.setUser(user);
		try {
		custDao.save(customer);
		}
		catch(Exception ex) {
			throw new OnlineBankException("Already Configured");
		}
		return "Configuration success, Superadmin ID - 'robinat13' , Password - '1234'";
		
		
	}

	public List<Transactions> getAllTransactions() {
		return transDao.findAll();
		
	}





}
