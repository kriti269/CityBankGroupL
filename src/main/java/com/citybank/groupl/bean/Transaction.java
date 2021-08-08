package com.citybank.groupl.bean;

import java.util.Date;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary It stores the transactions for all amounts transferred, withdrawn or
 *          deposited and maps to the transaction table in the database.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description - It
 * stores the transactions for all amounts transferred, withdrawn or deposited
 * and maps to the transaction table in the database.
 */

public class Transaction {
	private int transactionId;
	private Date txDateTime;
	private String txType;
	private Account account;
	private double amount;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTxDateTime() {
		return txDateTime;
	}

	public void setTxDateTime(Date txDateTime) {
		this.txDateTime = txDateTime;
	}

	public String getTxType() {
		return txType;
	}

	public void setTxType(String txType) {
		this.txType = txType;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
