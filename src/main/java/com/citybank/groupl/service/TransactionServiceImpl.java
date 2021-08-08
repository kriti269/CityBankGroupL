package com.citybank.groupl.service;

import java.util.List;

import com.citybank.groupl.DAO.TransactionDao;
import com.citybank.groupl.bean.Transaction;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary Implements all methods of TransactionService interface.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description -
 * Implements all methods of TransactionService interface.
 */

public class TransactionServiceImpl implements TransactionService {

	public TransactionDao transactionDao;

	// getter for transaction dao
	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	// setter for transaction dao
	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	// method to view all transactions
	public List<Transaction> viewAllTransactions(int userId) {
		// transaction dao method call to view all transactions
		// based on user id
		return transactionDao.viewAllTransactions(userId);
	}

}
