package com.cg.onlinebank.service;

import java.util.List;

import com.cg.onlinebank.bean.AccountMaster;
import com.cg.onlinebank.bean.Customer;
import com.cg.onlinebank.bean.FundTransfer;
import com.cg.onlinebank.bean.Transactions;
import com.cg.onlinebank.exception.OnlineBankException;

public interface CustomerService {
	
	public Customer addCustomer(Customer customer) throws OnlineBankException;
	
	public AccountMaster addAccount(AccountMaster account, Long customerid) throws OnlineBankException;

	public double depositAmount(long accountId,double balance) throws OnlineBankException;
	
	public double withdrawAmount(long accountId,double balance) throws OnlineBankException;
	
	public void addTransaction(String tranDescription, String tranType, double tranAmount, AccountMaster account) throws OnlineBankException;

	public double fundTransfer(FundTransfer fundTransfer) throws OnlineBankException;
	
	public double showBalance(long accountId) throws OnlineBankException;
	
	public List<AccountMaster> getAccountsByCustomerId(long custId) throws OnlineBankException;
	
	public Customer loginByUsername(String username, String password) throws OnlineBankException;
	
	public List<Transactions> getTransactions(long accountId) throws OnlineBankException;

}
