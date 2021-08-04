package com.citybank.groupl.service;

import com.citybank.groupl.DAO.BillDao;
import com.citybank.groupl.DAO.BillPaymentDao;
import com.citybank.groupl.DAO.TransactionDao;
import com.citybank.groupl.bean.Bill;

public class BillServiceImpl implements BillService{
	
	BillDao billDao;
	BillPaymentDao billPaymentDao;
	TransactionDao transactionDao;
	
		
	
	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	public BillDao getBillDao() {
		return billDao;
	}

	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	public BillPaymentDao getBillPaymentDao() {
		return billPaymentDao;
	}

	public void setBillPaymentDao(BillPaymentDao billPaymentDao) {
		this.billPaymentDao = billPaymentDao;
	}


	public Bill addBill(Bill bill, double amount) {
		bill = billDao.addBill(bill);
		int id = transactionDao.saveTransaction(bill.getUserAccount().getAccountId(), amount, "Debit");
		payBill(bill.getBillId(), id);
		return bill;
	}

	public int payBill(int billId, int transactionId) {
		return billPaymentDao.payBill(billId, transactionId);
	}

	

}
