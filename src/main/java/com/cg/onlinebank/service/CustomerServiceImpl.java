package com.cg.onlinebank.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlinebank.bean.AccountMaster;
import com.cg.onlinebank.bean.Customer;
import com.cg.onlinebank.bean.FundTransfer;
import com.cg.onlinebank.bean.Transactions;
import com.cg.onlinebank.bean.UserDetails;
import com.cg.onlinebank.dao.AccountMasterDao;
import com.cg.onlinebank.dao.CustomerDao;
import com.cg.onlinebank.dao.FundTransferDao;
import com.cg.onlinebank.dao.PayeeDao;
import com.cg.onlinebank.dao.TransactionsDao;
import com.cg.onlinebank.dao.UserDetailsDao;
import com.cg.onlinebank.exception.OnlineBankException;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
	
	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	@Autowired AccountMasterDao accmasterDao;
	@Autowired CustomerDao custDao;
	@Autowired UserDetailsDao userDao;
	@Autowired TransactionsDao transDao;
	@Autowired FundTransferDao fundDao;
	@Autowired PayeeDao payeeDao;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	
	public Customer addCustomer(Customer customer) throws OnlineBankException {
		try {
			customer.getUser().setLockStatus("UNLOCKED");
			customer.getUser().setRole("ROLE_USER");
		return custDao.save(customer);		
		}
		catch(Exception ex) {
			throw new OnlineBankException(ex.getLocalizedMessage());
		}
	}
	
	public AccountMaster addAccount(AccountMaster account, Long customerid) throws OnlineBankException {
		try {
		account.setCustomer(custDao.findById(customerid).get());
		account.setOpenDate(formatter.format(new Date()));
	
		return accmasterDao.save(account);
		}
		catch(Exception ex) {
			throw new OnlineBankException(account.toString());
		}
		
	}
	
	public double depositAmount(long accountId,double balance) throws OnlineBankException {
		try {
			if(balance<=0) {
				throw new OnlineBankException("Invalid Amount : Amount negative or 0");
			}
		AccountMaster account = accmasterDao.findById(accountId).get();
		double updateBalance =  (account.getAccountBalance()+balance);
		account.setAccountBalance(updateBalance);
		accmasterDao.save(account);
		addTransaction("Deposited "+balance+" to account with ID : "+accountId, "DEPOSIT", balance, account);
		return updateBalance;
		}
		catch(Exception ex) {
			throw new OnlineBankException(ex.getLocalizedMessage());
		}
	}
	
	
	public double withdrawAmount(long accountId,double balance) throws OnlineBankException {
		try {
			if(balance<=0) {
				throw new OnlineBankException("Invalid Amount : Amount negative or 0");
			}
		AccountMaster account = accmasterDao.findById(accountId).get();
		try {
			if(account.getAccountBalance()<balance) {
				throw new OnlineBankException("Insufficient Balance");
			}
		}
		catch(Exception ex) {
			throw new OnlineBankException(ex.getLocalizedMessage());
		}
		double updateBalance = (account.getAccountBalance()-balance);
		account.setAccountBalance(updateBalance);
		accmasterDao.save(account);
		addTransaction("Deducted "+balance+" from account with ID : "+accountId, "WITHDRAW", balance, account);
		return updateBalance;
		}
		catch(Exception ex) {
			throw new OnlineBankException(ex.getLocalizedMessage());
		}
	}
	
	
	public void addTransaction(String tranDescription, String tranType, double tranAmount, AccountMaster account) throws OnlineBankException {
		try {
		Transactions transaction = new Transactions();
		transaction.setAccmaster(account);
		transaction.setTranAmount(tranAmount);
		transaction.setTranDescription(tranDescription);
		transaction.setTranType(tranType);
		transaction.setTranDate(formatter.format(new Date()));
		transDao.save(transaction);
		}
		catch(Exception ex) {
			throw new OnlineBankException(ex.getLocalizedMessage());
		}
	}
	
	public double fundTransfer(FundTransfer fundTransfer) throws OnlineBankException {
		try {
			if(fundTransfer.getTransferAmount()<=0) {
				throw new OnlineBankException("Invalid Amount : Amount negative or 0");
			}
		AccountMaster payee;
		AccountMaster account = accmasterDao.findById(fundTransfer.getAccountId()).get();
		try {
		payee = accmasterDao.findById(fundTransfer.getPayeeAccountId()).get();
		}
		catch(Exception ex) {
			throw new OnlineBankException(": Partner account does not exist");
		}
		
		double updatedBalance = withdrawAmount(fundTransfer.getAccountId(), fundTransfer.getTransferAmount());
		depositAmount(fundTransfer.getPayeeAccountId(), fundTransfer.getTransferAmount());
		fundTransfer.setDateOfTransfer(formatter.format(new Date()));
		fundDao.save(fundTransfer);
		accmasterDao.save(account);
		accmasterDao.save(payee);
		return updatedBalance;
		}
		catch(Exception ex) {
			throw new OnlineBankException(ex.getLocalizedMessage());
		}
	}
	
	
	public double showBalance(long accountId) throws OnlineBankException {
		try {
		return(accmasterDao.findById(accountId).get().getAccountBalance());
		}
		catch(Exception ex) {
			throw new OnlineBankException("Account not found");
		}
	}
	

	
	
	public List<AccountMaster> getAccountsByCustomerId(long custId) throws OnlineBankException {
		try {
		return accmasterDao.getAccountsByCustomerId(custId);
		}
		catch(Exception ex) {
			throw new OnlineBankException(ex.getLocalizedMessage());
		}
	}

	
	public Customer loginByUsername(String username, String password) throws OnlineBankException {
		
			 UserDetails temp =  userDao.getCustomerByUsername(username);
			
			if(temp==null) {
				throw new OnlineBankException("User does not exist");
			}
			if(PASSWORD_ENCODER.matches(password, temp.getPassword()))
			{
				return custDao.findById(temp.getUserId()).get();
			}
			else {
				throw new OnlineBankException( "Password Incorrect");
			}
		
	}

	public List<Transactions> getTransactions(long accountId) throws OnlineBankException {
		try {
		return transDao.getTransactions(accountId);
		}
		catch(Exception ex) {
			throw new OnlineBankException(ex.getLocalizedMessage());
		}
	}

//	public Customer getCustomerById(long custId) {
//		return custDao.findById(custId).get();
//	}


}
