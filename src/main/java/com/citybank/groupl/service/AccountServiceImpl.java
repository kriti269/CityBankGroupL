package com.citybank.groupl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.citybank.groupl.DAO.AccountDao;
import com.citybank.groupl.DAO.AccountTypeDao;
import com.citybank.groupl.DAO.TransactionDao;
import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.AccountType;

@Service
public class AccountServiceImpl implements AccountService{
	
	AccountDao accountDao;
	TransactionDao transactionDao;
	AccountTypeDao accountTypeDao;
	
	

	public AccountTypeDao getAccountTypeDao() {
		return accountTypeDao;
	}

	public void setAccountTypeDao(AccountTypeDao accountTypeDao) {
		this.accountTypeDao = accountTypeDao;
	}

	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public List<Account> getAllUserAccounts(int userId) {
		// TODO Auto-generated method stub
		return accountDao.getAllUserAccounts(userId);
	}

	public Account getUserAccount(int userId, int accountTypeId) {
		// TODO Auto-generated method stub
		return accountDao.getUserAccount(userId, accountTypeId);
	}

	public int addAccount(int userId, int accountTypeId) {
		// TODO Auto-generated method stub
		Account account = getUserAccount(userId, accountTypeId);
		if(account==null)
			return accountDao.addAccount(userId, accountTypeId);
		return 0;
	}

	public String[] depositAmount(int userId, int accountId, double amount) {
		int result = accountDao.depositAmount(userId,accountId,amount);
		String[] response = new String[2];
		if(result != 0) {
			transactionDao.saveTransaction(accountId, amount, "Credit");
			response[0] = "true";
			response[1] = "Amount successfully deposited.";
		} else {
			response[0] = "false";
			response[1] = "Unable to deposit amount.";
		}
		return response;
	}
	
	public String[] withdrawAmount(int userId, int accountId, double amount) {
		String[] response = new String[2];
		response = checkValidWithdrawal(amount, accountId);
		if(response[0].equals("false")) {
			return response;
		}
		int result = accountDao.withdrawAmount(userId,accountId,amount);
		if(result != 0) {
			transactionDao.saveTransaction(accountId, amount, "Debit");
			response[0] = "true";
			response[1] = "Amount successfully withdrawn.";
		} else {
			response[0] = "false";
			response[1] = "Unable to withdraw amount.";
		}
		return response;
	}

	public String[] transferFunds(int userId, int fromAccountId, int toAccountId, double amount) {
		String[] response = new String[2];
		response = checkValidWithdrawal(amount, fromAccountId);
		if(response[0].equals("false")) {
			return response;
		}
		int result = accountDao.withdrawAmount(userId, fromAccountId, amount);
		
		if(result != 0) {
			transactionDao.saveTransaction(toAccountId, amount, "Credit");
			result = accountDao.depositAmount(userId, toAccountId, amount);
			if(result != 0) {
				transactionDao.saveTransaction(fromAccountId, amount, "Debit");
				response[0] = "true";
				response[1] = "Amount successfully transferred.";
			} else {
				response[0] = "false";
				response[1] = "Unable to transfer funds.";
			}
			
		} else {
			response[0] = "false";
			response[1] = "Unable to transfer funds.";
		}
		return response;
	
	}
	
	public List<AccountType> getAllAccountTypes(){
		return accountTypeDao.getAllAccountTypes();
	}
	
	private String[] checkValidWithdrawal(double amount_val, int account_id_val) {
		String[] response = new String[2];
		Account userAccount = accountDao.getAccount(account_id_val);
		  if(userAccount.getBalance() < amount_val) {
			  response[0] = "false";
			  response[1] = "Unable to withdraw amount. Check Balance!";
		  } else {
			  response[0] = "true";
		  }
		  return response;
	}

}
