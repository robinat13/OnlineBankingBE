package com.cg.onlinebank.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinebank.bean.AccountMaster;
import com.cg.onlinebank.bean.Customer;
import com.cg.onlinebank.bean.FundTransfer;
import com.cg.onlinebank.bean.Transactions;
import com.cg.onlinebank.exception.OnlineBankException;
import com.cg.onlinebank.service.AdminServiceImpl;
import com.cg.onlinebank.service.CustomerServiceImpl;

/***
* Author : Robin Singh Chauhan
* Class Name : OnlinebankController 
* Purpose : Main Controller class
*/
@CrossOrigin(origins="*")
@RestController
public class OnlinebankController {

	@Autowired
	CustomerServiceImpl custService;
	@Autowired
	AdminServiceImpl adminService;
	
	//Customer Related End Points
	
	/***
	* Author : Robin Singh Chauhan
	* Method Name : addCustomer
	* Parameters : Parameter of type Customer object
	* Return Value : Customer object
	* Purpose : To save the customer details into Customer table
	* @throws OnlineBankException 
	***/
	@PostMapping(value="/addcustomer")
	public Customer addCustomer(@RequestBody Customer customer) throws OnlineBankException{
		return custService.addCustomer(customer);
		
	}
	
	@GetMapping("/test")
	public String test() {
		return "API up and running";
	}
	
	/***
	* Author : Robin Singh Chauhan
	* Method Name : addAccount
	* Parameters : Parameter of type AccountMaster object, parameter 'customerid' of type Long 
	* Return Value : Account object
	* Purpose : Add an account for a customer
	* @throws OnlineBankException 
	***/
	@PostMapping("/customer/addaccount/{customerid}")
	public AccountMaster addAccount(@RequestBody AccountMaster account, @PathVariable Long customerid) throws OnlineBankException{
		return custService.addAccount(account,customerid);
	}
	
	
	/***
	* Author : Robin Singh Chauhan
	* Method Name : depositAmount
	* Parameters : Parameter 'accountId' of type Long, parameter 'amount' of type Double
	* Return Value : Updated value of Balance
	* Purpose : Deposit given amount into given account
	* @throws OnlineBankException 
	***/
	@PutMapping("/customer/deposit/{accountId}/{amount}")
	public double depositAmount(@PathVariable Long accountId, @PathVariable double amount) throws OnlineBankException {
		return custService.depositAmount(accountId,amount);
	}
	
	/***
	* Author : Robin Singh Chauhan
	* Method Name : withdrawAmount
	* Parameters : Parameter 'accountId' of type Long, parameter 'amount' of type Double
	* Return Value : Updated value of Balance
	* Purpose : Withdraw given amount from given account
	* @throws OnlineBankException 
	***/
	@PutMapping("/customer/withdraw/{accountId}/{amount}")
	public double withdrawAmount(@PathVariable Long accountId, @PathVariable double amount) throws OnlineBankException {
		return custService.withdrawAmount(accountId,amount);
	}
	
	
	/***
	* Author : Robin Singh Chauhan
	* Method Name : withdrawAmount
	* Parameters : Parameter 'accountId' of type Long, parameter 'amount' of type Double
	* Return Value : Updated value of Balance
	* Purpose : Withdraw given amount from given account
	* @throws OnlineBankException 
	***/
	@PostMapping("/customer/fundtransfer")
	public double fundTransfer(@RequestBody FundTransfer fundTransfer) throws OnlineBankException {
		return custService.fundTransfer(fundTransfer);
	}
	
	
	/***
	* Author : Robin Singh Chauhan
	* Method Name : showBalance
	* Parameters : Parameter 'accountId' of type Long
	* Return Value : Current balance in the account
	* Purpose : To show current balance of a given account
	* @throws OnlineBankException 
	***/
	@GetMapping("/customer/showbalance/{accountId}")
	public double showBalance(@PathVariable long accountId) throws OnlineBankException {
		return custService.showBalance(accountId);
	}
	
	
	/***
	* Author : Robin Singh Chauhan
	* Method Name : getAccountsByCustomerId
	* Parameters : Parameter 'custId' of type Long
	* Return Value : List of 'activated' accounts of a given customer
	* Purpose : Gives a list of all 'activated' accounts of a Customer
	* @throws OnlineBankException 
	***/
	@GetMapping("/customer/accounts/{custId}")
	public List<AccountMaster> getAccountsByCustomerId(@PathVariable long custId) throws OnlineBankException{
		return custService.getAccountsByCustomerId(custId);
	}
	
	
	/***
	* Author : Robin Singh Chauhan
	* Method Name : userLogin
	* Parameters : Parameter 'username' of type String, parameter 'username' of type String
	* Return Value : Customer object
	* Purpose : Authenticates user 
	* @throws OnlineBankException 
	***/
	@GetMapping("/login/{username}/{password}")
	public Customer userLogin(@PathVariable("username") String username, @PathVariable("password") String password) throws OnlineBankException {
		return custService.loginByUsername(username, password);
	}
	
	
	/***
	* Author : Robin Singh Chauhan
	* Method Name : getTransactions
	* Parameters : Parameter 'accountId' of type Long
	* Return Value : List of Transaction object
	* Purpose : Get all transactions of a given account
	* @throws OnlineBankException 
	***/
	@GetMapping("/customer/transactions/{accountId}")
	public List<Transactions> getTransactions(@PathVariable("accountId") long accountId) throws OnlineBankException{
		return custService.getTransactions(accountId);
	}
	
	/*************************************************************************************
	* 							Admin related end points								 *
    /*************************************************************************************
	 
	
	/***
	* Author : Robin Singh Chauhan
	* Method Name : getTransactions
	* Parameters : Parameter 'accountId' of type Long
	* Return Value : List of Transaction object
	* Purpose : Get all transactions of a given account
	* @throws OnlineBankException 
	***/
	@GetMapping("/admin/allcustomers")
	public List<Customer> allCustomers() throws OnlineBankException{
		return adminService.allCustomers();
	}
	
	
	/***
	* Author : Robin Singh Chauhan
	* Method Name : getAccountRequests
	* Parameters : Parameter 'status' of type String
	* Return Value : List of AccountMaster object
	* Purpose : Get all Accounts depending upon value of 'status'
	* @throws OnlineBankException 
	***/
	@GetMapping("/admin/accountrequests/{status}")
	public List<AccountMaster> getAccountRequests(@PathVariable String status) throws OnlineBankException{
		return adminService.getAccountRequests(status);
	}
	
	
	/***
	* Author : Robin Singh Chauhan
	* Method Name : rejectAccount
	* Parameters : Parameter 'accountId' of type Long
	* Return Value : AccountMaster object
	* Purpose : Set status as 'REJECTED' for an account
	* @throws OnlineBankException 
	***/
	@PutMapping("/admin/rejectaccount/{accountId}")
	public List<AccountMaster> rejectAccount(@PathVariable long accountId) throws OnlineBankException{
		return adminService.rejectAccount(accountId);
	}
	
	
	/***
	* Author : Robin Singh Chauhan
	* Method Name : activateAccount
	* Parameters : Parameter 'accountId' of type Long
	* Return Value : AccountMaster object
	* Purpose : Set status as 'ACTIVATED' for an account
	* @throws OnlineBankException 
	***/
	@PutMapping("/admin/activateaccount/{accountId}")
	public List<AccountMaster> activateAccount(@PathVariable long accountId) throws OnlineBankException{
		return adminService.activateAccount(accountId);
	}
	
	
	/***
	* Author : Robin Singh Chauhan
	* Method Name : handleErrors
	* Parameters : Parameter 'ex' of type Exception
	* Return Value : Response Entity
	* Purpose : Handle errors
	* @throws OnlineBankException 
	***/
	@ExceptionHandler({OnlineBankException.class})
	public ResponseEntity<String> handleErrors(Exception ex){
		return new ResponseEntity<String>("An Error occurred "+ex.getMessage(),HttpStatus.CONFLICT);
	}
	
	
	@GetMapping("/admin/alltransactions")
	public List<Transactions> getAllTransactions(){
		return adminService.getAllTransactions();
	}

	
	//SUPER ADMIN
	
	@GetMapping("/initialrun/configuration/configuresuperadmin")
	public String configureSuperAdmin() throws OnlineBankException {
		return adminService.configureSuperAdmin();
	}
	
	
	
//	@GetMapping("/customer/customerdetails/{custId}")
//	public Customer getCustomerById(@PathVariable long custId) {
//		return custService.getCustomerById(custId);
//	}
	

}
