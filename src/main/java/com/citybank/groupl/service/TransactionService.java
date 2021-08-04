package com.citybank.groupl.service;

import java.util.List;

import com.citybank.groupl.bean.Transaction;

public interface TransactionService {
	public List<Transaction> viewAllTransactions(int accountId);
}
