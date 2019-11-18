package com.cg.onlinebank.service;

import java.util.List;

import com.cg.onlinebank.bean.AccountMaster;
import com.cg.onlinebank.bean.Customer;
import com.cg.onlinebank.exception.OnlineBankException;

public interface AdminService {
	
	public List<Customer> allCustomers() throws OnlineBankException;
	
	public List<AccountMaster> getAccountRequests(String status) throws OnlineBankException;
	
	public List<AccountMaster> rejectAccount(long accountId) throws OnlineBankException;

	public List<AccountMaster> activateAccount(long accountId) throws OnlineBankException;
	
}
