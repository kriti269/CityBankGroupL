package com.citybank.groupl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.citybank.groupl.DAO.AccountDao;
import com.citybank.groupl.bean.Account;

@Service
public class AccountServiceImpl implements AccountService{
	
	AccountDao accountDao;

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
		return accountDao.depositAmount(userId,accountId,amount);
	}
	
	public int withdrawAmount(int userId, int accountId, double amount) {
		// TODO Auto-generated method stub
		return accountDao.withdrawAmount(userId,accountId,amount);
	}

}
