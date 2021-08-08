package com.citybank.groupl.bean;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary This is the Account bean class for hadnling account related 
 * details. The attributes are account Id, user bean referene, account Type
 * bean reference and balance of the account.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description - 
 * This is the Account bean class for hadnling account related 
 * details. The attributes are account Id, user bean referene, account Type
 * bean reference and balance of the account.
 */

public class Account {
	private int accountId;
	private User user;
	private AccountType accountType;
	private double balance;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
