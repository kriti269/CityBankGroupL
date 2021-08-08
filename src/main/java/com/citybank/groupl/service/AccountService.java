package com.citybank.groupl.service;

import java.util.List;

import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.AccountType;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary Interface, dedicated for account related operations, that provides
 *          methods to interact with DAO classes.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description -
 * Interface, dedicated for account related operations, that provides methods to
 * interact with DAO classes.
 */

public interface AccountService {
	public List<Account> getAllUserAccounts(int userId);

	public Account getUserAccount(int userId, int accountTypeId);

	public int addAccount(int userId, int accountTypeId);

	public String[] depositAmount(int userId, int accountId, double amount);

	public String[] withdrawAmount(int userId, int accountId, double amount);

	public String[] transferFunds(int userId, int fromAccountId, int toAccountId, double amount);

	public List<AccountType> getAllAccountTypes();
}
