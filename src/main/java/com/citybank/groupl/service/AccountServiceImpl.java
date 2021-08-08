package com.citybank.groupl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.citybank.groupl.DAO.AccountDao;
import com.citybank.groupl.DAO.AccountTypeDao;
import com.citybank.groupl.DAO.TransactionDao;
import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.AccountType;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary Interface, dedicated for billing operations, that declares the
 *          methods to interact with various Dao classes – BillDao,
 *          BillPaymentDao, TransactionDao, AccountDao.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description -
 * Interface, dedicated for billing operations, that declares the methods to
 * interact with various Dao classes – BillDao, BillPaymentDao, TransactionDao,
 * AccountDao.
 */

@Service
public class AccountServiceImpl implements AccountService {

	AccountDao accountDao;
	TransactionDao transactionDao;
	AccountTypeDao accountTypeDao;

	// Getter for account type dao
	public AccountTypeDao getAccountTypeDao() {
		return accountTypeDao;
	}

	// Setter for account type dao
	public void setAccountTypeDao(AccountTypeDao accountTypeDao) {
		this.accountTypeDao = accountTypeDao;
	}

	// Getter for transaction dao
	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	// Setter for transaction dao
	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	// Getter for account dao
	public AccountDao getAccountDao() {
		return accountDao;
	}

	// Setter for account dao
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	// method to get all user accounts
	public List<Account> getAllUserAccounts(int userId) {
		return accountDao.getAllUserAccounts(userId);
	}

	// method to get user account by user id and account type id
	public Account getUserAccount(int userId, int accountTypeId) {
		return accountDao.getUserAccount(userId, accountTypeId);
	}

	// method to add account
	public int addAccount(int userId, int accountTypeId) {
		Account account = getUserAccount(userId, accountTypeId);
		if (account == null)
			return accountDao.addAccount(userId, accountTypeId);
		return 0;
	}

	// method to deposit amount to an account
	public String[] depositAmount(int userId, int accountId, double amount) {
		int result = accountDao.depositAmount(userId, accountId, amount);
		String[] response = new String[2];
		if (result != 0) {
			transactionDao.saveTransaction(accountId, amount, "Credit");
			response[0] = "true";
			response[1] = "Amount successfully deposited.";
		} else {
			response[0] = "false";
			response[1] = "Unable to deposit amount.";
		}
		return response;
	}

	// method to withdraw amount from an account
	public String[] withdrawAmount(int userId, int accountId, double amount) {
		String[] response = new String[2];
		response = checkValidWithdrawal(amount, accountId);
		if (response[0].equals("false")) {
			return response;
		}
		int result = accountDao.withdrawAmount(userId, accountId, amount);
		if (result != 0) {
			transactionDao.saveTransaction(accountId, amount, "Debit");
			response[0] = "true";
			response[1] = "Amount successfully withdrawn.";
		} else {
			response[0] = "false";
			response[1] = "Unable to withdraw amount.";
		}
		return response;
	}

	// method to transfer funds from one account to another
	public String[] transferFunds(int userId, int fromAccountId, int toAccountId, double amount) {
		String[] response = new String[2];
		response = checkValidWithdrawal(amount, fromAccountId);
		if (response[0].equals("false")) {
			return response;
		}
		int result = accountDao.withdrawAmount(userId, fromAccountId, amount);

		if (result != 0) {
			transactionDao.saveTransaction(toAccountId, amount, "Credit");
			result = accountDao.depositAmount(userId, toAccountId, amount);
			if (result != 0) {
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

	// method to get all account types
	public List<AccountType> getAllAccountTypes() {
		return accountTypeDao.getAllAccountTypes();
	}

	// method to check valid Withdrawal from account balance
	private String[] checkValidWithdrawal(double amount_val, int account_id_val) {
		String[] response = new String[2];
		Account userAccount = accountDao.getAccount(account_id_val);
		if (userAccount.getBalance() < amount_val) {
			response[0] = "false";
			response[1] = "Unable to withdraw amount. Check Balance!";
		} else {
			response[0] = "true";
		}
		return response;
	}

}
