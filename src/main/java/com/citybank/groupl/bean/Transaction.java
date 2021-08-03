package com.citybank.groupl.bean;

import java.util.Date;

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
