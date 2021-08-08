package com.citybank.groupl.service;

import java.util.List;

import com.citybank.groupl.bean.Transaction;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary Interface, dedicated for transaction related services.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description -
 * Interface, dedicated for transaction related services.
 */

public interface TransactionService {
	public List<Transaction> viewAllTransactions(int userId);
}
