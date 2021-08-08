package com.citybank.groupl.service;

import com.citybank.groupl.DAO.AccountDao;
import java.util.List;

import com.citybank.groupl.DAO.BillDao;
import com.citybank.groupl.DAO.BillPaymentDao;
import com.citybank.groupl.DAO.TransactionDao;
import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.Bill;
import com.citybank.groupl.bean.BillPayment;

/**
 * @since 2021-08-07
 * @author Jatin, Kriti, Varun, Sonia
 * @serial 1.0
 * @summary Implements BillService interface and provides definition for add
 *          bill, pay bill and get bill payments methods.
 */

/*
 * Date - 07-Aug-2021 Author - Jatin, Kriti, Varun, Sonia Description -
 * Implements BillService interface and provides definition for add bill, pay
 * bill and get bill payments methods.
 */

public class BillServiceImpl implements BillService {

	BillDao billDao;
	BillPaymentDao billPaymentDao;
	TransactionDao transactionDao;
	AccountDao accountDao;

	// getter for transaction dao
	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	// setter for transaction dao
	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	// getter for bill dao
	public BillDao getBillDao() {
		return billDao;
	}

	// setter for bill dao
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	// getter for bill payment dao
	public BillPaymentDao getBillPaymentDao() {
		return billPaymentDao;
	}

	// setter for bill payment dao
	public void setBillPaymentDao(BillPaymentDao billPaymentDao) {
		this.billPaymentDao = billPaymentDao;
	}

	// getter for account dao
	public AccountDao getAccountDao() {
		return accountDao;
	}

	// getter for account dao
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	// method to add bill details
	public String[] addBill(Bill bill, String amountVal, int userId) {
		String accountVal = "";
		// check if bill account mapping is there
		if (bill.getAccount() != null) {
			accountVal = String.valueOf(bill.getAccount().getAccountId());
		}
		// check valid bill payment details
		String[] response = checkValidBillPayment(amountVal, accountVal, userId);
		if (response[0].equals("false")) {
			return response;
		}
		// get account id from bill object
		int accountId = bill.getAccount().getAccountId();
		double amount = Double.parseDouble(amountVal);
		// call bill dao add bill method
		bill = billDao.addBill(bill);
		// withdraw amount on successful bill payment
		int result = accountDao.withdrawAmount(userId, accountId, amount);
		if (result == 0) {
			response[0] = "false";
			response[1] = "Unable to pay bill. Error occurred.";
			return response;
		}
		int id = 0;
		// save transaction in database of amount deduction
		id = transactionDao.saveTransaction(accountId, amount, "Debit");
		// pay bill method called to save mapping
		payBill(bill.getBillId(), id);
		response[0] = String.valueOf(bill.getBillId());
		return response;
	}

	// pay bill method to add mapping of bill payments
	public int payBill(int billId, int transactionId) {
		// add mapping of bill with transaction
		return billPaymentDao.payBill(billId, transactionId);
	}

	// get all bill payments method
	public List<BillPayment> getBillPayments(int user_id) {
		// get all bill payments from bill payments dao
		return billPaymentDao.getBillPayments(user_id);
	}

	private String[] checkValidBillPayment(String amountVal, String accountIdVal, int userId) {
		String[] response = new String[2];
		response[0] = "true";

		// check that account id value is not empty
		if (accountIdVal.equals("")) {
			response[0] = "false";
			response[1] = "Unable to pay bill. Invalid Account Selection.";
			return response;
		}

		try {
			// check that amount and account id are valid inputs
			double amount_val = Double.parseDouble(amountVal);
			int account_id_val = Integer.parseInt(accountIdVal);
			if (amount_val > 0 && account_id_val != 0) {
				// check withdrawal capacity of bank account
				response = checkValidWithdrawal(amount_val, account_id_val);
			} else {
				response[0] = "false";
				response[1] = "Unable to pay bill. Invalid amount value.";
			}
		} catch (NumberFormatException e) {
			response[0] = "false";
			response[1] = "Unable to pay bill. Invalid amount value.";
		}

		return response;
	}

	private String[] checkValidWithdrawal(double amount_val, int account_id_val) {
		String[] response = new String[2];
		// getting account details from account id
		Account userAccount = accountDao.getAccount(account_id_val);
		// validate that balance is more that required payment
		if (userAccount.getBalance() < amount_val) {
			response[0] = "false";
			response[1] = "Unable to pay bill. Check Balance!";
		} else {
			response[0] = "true";
		}
		return response;
	}
}
