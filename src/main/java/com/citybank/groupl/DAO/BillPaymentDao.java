package com.citybank.groupl.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.AccountType;
import com.citybank.groupl.bean.Bill;
import com.citybank.groupl.bean.BillPayment;
import com.citybank.groupl.bean.Transaction;

public class BillPaymentDao {
	
	public JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int payBill(int billId, int transactionId) {
		String sql = "insert into bill_payment(bill_id, transaction_id) values(?,?)";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {billId, transactionId},
					new int[] {java.sql.Types.INTEGER, java.sql.Types.INTEGER});
		}
		catch(Exception ex) {
			System.out.println("Failed to pay bill "+ ex.getMessage());
		}
		return result;
	}
	
	public List<BillPayment> getBillPayments(int user_id){
		String sql = "select * from bill_payment bp join bill b on b.bill_id=bp.bill_id join "
				+ "transaction t on t.transaction_id=bp.transaction_id join account "
				+ "a on a.account_id=t.account_id join account_type at on a.account_type_id=at.account_type_id"
				+ " where b.account_id in(select account_id "
				+ "from account where user_id=?)";
		List<BillPayment> billPaymentsList = null;
		try {
			billPaymentsList = jdbcTemplate.query(sql, new Object[] {user_id},
					new int[] {java.sql.Types.INTEGER}, new BillPaymentRowMapper());
		}
		catch(Exception ex) {
			System.out.println("Error while getting bill payment details " + ex.getMessage());
		}
		return billPaymentsList;
	}
	
	public class BillPaymentRowMapper implements RowMapper<BillPayment> {
	    public BillPayment mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	BillPayment billPayment = (new BeanPropertyRowMapper<BillPayment>(BillPayment.class)).mapRow(rs,rowNum);
	        Bill bill = (new BeanPropertyRowMapper<Bill>(Bill.class)).mapRow(rs,rowNum);
	        Transaction transaction = (new BeanPropertyRowMapper<Transaction>(Transaction.class)).mapRow(rs,rowNum);
	        Account account = (new BeanPropertyRowMapper<Account>(Account.class)).mapRow(rs,rowNum);
	        AccountType accounttype = (new BeanPropertyRowMapper<AccountType>(AccountType.class)).mapRow(rs,rowNum);
	        account.setAccountType(accounttype);
	        bill.setAccount(account);
	        billPayment.setBill(bill);
	        billPayment.setTransaction(transaction);
	        return billPayment;
	    }
	}

}
