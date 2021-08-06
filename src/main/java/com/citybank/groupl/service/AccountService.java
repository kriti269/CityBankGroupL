package com.citybank.groupl.service;

import java.util.List;

import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.AccountType;


public interface AccountService {
	public List<Account> getAllUserAccounts(int userId);
	public Account getUserAccount(int userId, int accountTypeId);
	public int addAccount(int userId, int accountTypeId);
	public int depositAmount(int userId, int accountId, double amount);
	public int withdrawAmount(int userId, int accountId, double amount);
	public int transferFunds(int userId, int fromAccountId, int toAccountId, double amount);
	public List<AccountType> getAllAccountTypes();
}
