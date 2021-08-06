package com.citybank.groupl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.citybank.groupl.DAO.AccountDao;
import com.citybank.groupl.DAO.TransactionDao;
import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.AccountType;

@Service
public class AccountServiceImpl implements AccountService{
	
	AccountDao accountDao;
	TransactionDao transactionDao;

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

	public int depositAmount(int userId, int accountId, double amount) {
		// TODO Auto-generated method stub
		transactionDao.saveTransaction(accountId, amount, "Credit");
		return accountDao.depositAmount(userId,accountId,amount);
	}
	
	public int withdrawAmount(int userId, int accountId, double amount) {
		// TODO Auto-generated method stub
		transactionDao.saveTransaction(accountId, amount, "Debit");
		return accountDao.withdrawAmount(userId,accountId,amount);
	}

	public int transferFunds(int userId, int fromAccountId, int toAccountId, double amount) {
		// TODO Auto-generated method stub
		transactionDao.saveTransaction(fromAccountId, amount, "Debit");
		if(accountDao.withdrawAmount(userId, fromAccountId, amount)==1){
			transactionDao.saveTransaction(toAccountId, amount, "Credit");
			return accountDao.depositAmount(userId, toAccountId, amount);
		}
		return 0;
	
	}
	
	public List<AccountType> getAllAccountTypes(){
		return accountDao.getAllAccountTypes();
	}

}
