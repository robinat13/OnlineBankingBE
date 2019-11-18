//package com.cg.onlinebank;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import com.cg.onlinebank.bean.AccountMaster;
//import com.cg.onlinebank.bean.Customer;
//import com.cg.onlinebank.bean.Transactions;
//import com.cg.onlinebank.bean.UserDetails;
//import com.cg.onlinebank.dao.AccountMasterDao;
//import com.cg.onlinebank.dao.CustomerDao;
//import com.cg.onlinebank.dao.FundTransferDao;
//import com.cg.onlinebank.dao.TransactionsDao;
//import com.cg.onlinebank.dao.UserDetailsDao;
//import com.cg.onlinebank.exception.OnlineBankException;
//import com.cg.onlinebank.service.AdminServiceImpl;
//import com.cg.onlinebank.service.CustomerServiceImpl;
//
//@RunWith(MockitoJUnitRunner.class)
//public class OnlineBankTests {
//
//	@InjectMocks
//	private AdminServiceImpl adminService;
//	@InjectMocks
//	private CustomerServiceImpl custService;
//	
//	@Mock
//	private AccountMasterDao accDao;
//	@Mock
//	private CustomerDao custDao;
//	@Mock
//	private FundTransferDao fundDao;
//	@Mock
//	private TransactionsDao tranDao;
//	@Mock
//	private UserDetailsDao userDao;
//	
//	@Test
//	public void addCustomerTest() throws OnlineBankException {
//		UserDetails user = new UserDetails();
//		Customer cust = new Customer();
//		cust.setCustomerId(1);
//		cust.setCustomerName("Robin");
//		cust.setEmail("robin@gmail.com");
//		cust.setAddress("Huehuehue");
//		cust.setPanCard(1234);
//		cust.setGender("MALE");
//		cust.setMobile("9639673979");
//		
//		user.setUserId(1);
//		user.setUserName("robinat13");
//		user.setPassword("1234");
//		user.setRole("ROLE_USER");
//		user.setSecretQuestion("Huehuehue");
//		user.setTransactionPassword("1234");
//		user.setLockStatus("UNLOCKED");
//		
//		cust.setUser(user);
//		
//		when(custDao.save(cust)).thenReturn(cust);
//		Customer expectedCustomer = custService.addCustomer(cust);
//		System.out.println("--"+ cust.toString());
//		System.out.println("--"+expectedCustomer.toString());
//		assertEquals(expectedCustomer, cust);
//		
//	}
//	
//	
//		
//		public void addAccountTest() throws OnlineBankException{
//		AccountMaster acc = new AccountMaster();
//		Customer cust = new Customer();
//		UserDetails user = new UserDetails();
//		List<Transactions> tran = new ArrayList<Transactions>();
//		
//		cust.setCustomerId(1);
//		cust.setCustomerName("Robin");
//		cust.setEmail("robin@gmail.com");
//		cust.setAddress("Huehuehue");
//		cust.setPanCard(1234);
//		cust.setGender("MALE");
//		cust.setMobile("9639673979");
//		
//		user.setUserId(6451);
//		user.setUserName("robinat13");
//		user.setPassword("1234");
//		user.setRole("ROLE_USER");
//		user.setSecretQuestion("Huehuehue");
//		user.setTransactionPassword("1234");
//		user.setLockStatus("UNLOCKED");
//		
//		cust.setUser(user);
//		custDao.save(cust);
//		
//		acc.setCustomer(cust);
//		acc.setAccountBalance(500);
//		acc.setAccountId(1);
//		acc.setAccountType("SAVING");
//		acc.setActivation("PENDING");
//		acc.setOpenDate("");
//		//acc.setTransactions(tran);
//		List<AccountMaster> am = new ArrayList<AccountMaster>();
//		am.add(acc);
//		acc.getCustomer().setAccmaster(am);
//		
//		
//		when(accDao.save(acc)).thenReturn(acc);
//		long accid =1;
//		AccountMaster expectedAccountMaster = custService.addAccount(acc, accid);
//		expectedAccountMaster.setOpenDate("");
//		
//		//System.out.println(acc.toString());
//		//System.out.println(expectedAccountMaster.toString());
//		assertEquals(expectedAccountMaster, acc);
//	}
//	
//		
//
//}
