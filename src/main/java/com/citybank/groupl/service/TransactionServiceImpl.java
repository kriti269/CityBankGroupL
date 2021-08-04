package com.citybank.groupl.service;

import java.util.List;

import com.citybank.groupl.DAO.TransactionDao;
import com.citybank.groupl.bean.Transaction;

public class TransactionServiceImpl implements TransactionService {
	
	public TransactionDao transactionDao;
	

	public TransactionDao getTransactionDao() {
		return transactionDao;
	}


	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}


	public List<Transaction> viewAllTransactions(int accountId) {
		return transactionDao.viewAllTransactions(accountId);
	}

}
