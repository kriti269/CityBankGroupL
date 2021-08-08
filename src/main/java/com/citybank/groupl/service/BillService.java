package com.citybank.groupl.service;

import java.util.List;

import com.citybank.groupl.bean.Bill;
import com.citybank.groupl.bean.BillPayment;

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

public interface BillService {
	public String[] addBill(Bill bill, String amount, int userId);

	public int payBill(int billId, int transactionId);

	public List<BillPayment> getBillPayments(int user_id);
}
