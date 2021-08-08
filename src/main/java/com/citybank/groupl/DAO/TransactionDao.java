package com.citybank.groupl.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.citybank.groupl.bean.Account;
import com.citybank.groupl.bean.AccountType;
import com.citybank.groupl.bean.Transaction;

/**
 * @since 11-07-2021
 * @author Group L - Kriti, Jatin, Varun, Sonia
 * @serial 1.0
 * @summary Database operations related to transaction table are performed in
 *          Transaction Dao.
 */

/*
 * Date - 11-07-2021 Author - Kriti, Jatin, Varun, Sonia Description - Database
 * operations related to transaction table are performed in Transaction Dao.
 */

public class TransactionDao {

	public JdbcTemplate jdbcTemplate;

	// getter for jdbc template
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	// setter for jdbc template
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// Inserts a transaction into transaction table with
	// provided values returns the transactionId
	public int saveTransaction(int accountId, double amount, String txType) {
		String sql = "insert into transaction(account_id, tx_type, amount) values(?,?,?);";
		int result = 0;
		try {
			List<SqlParameter> listParams = Arrays.asList(new SqlParameter(Types.INTEGER, "account_id"),
					new SqlParameter(Types.VARCHAR, "tx_type"), new SqlParameter(Types.DOUBLE, "amount"));

			PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sql, listParams) {
				{
					setReturnGeneratedKeys(true);
					setGeneratedKeysColumnNames("transaction_id");
				}
			};

			@SuppressWarnings("unchecked")
			List<? extends Object> txParams = Arrays.asList(accountId, txType, amount);
			PreparedStatementCreator psc = pscf.newPreparedStatementCreator(txParams);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(psc, keyHolder);
			return Integer.parseInt(keyHolder.getKey().toString());
		} catch (Exception ex) {
			System.out.println("Error occurred while saving transaction: " + ex.getMessage());
		}
		return result;
	}

	// Gets all transaction details based on user id
	public List<Transaction> viewAllTransactions(int userId) {
		String sql = "select * from banking_system.transaction t join account a on t.account_id=a.account_id "
				+ "join account_type at on a.account_type_id=at.account_type_id "
				+ "where t.account_id in (select account_id from account where user_id=?) "
				+ " order by t.tx_date_time desc";
		List<Transaction> allTransactions = null;
		try {
			allTransactions = jdbcTemplate.query(sql, new Object[] { userId }, new int[] { java.sql.Types.INTEGER },
					new TransactionRowMapper());
		} catch (Exception ex) {
			System.out.println("Error occurred while saving transaction: " + ex.getMessage());
		}
		return allTransactions;
	}

	// implements mapRow function of RowMapper and returns
	// Transaction row after setting nested Account and AccountType objects
	public class TransactionRowMapper implements RowMapper<Transaction> {
		public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
			Transaction transaction = (new BeanPropertyRowMapper<Transaction>(Transaction.class)).mapRow(rs, rowNum);
			Account account = (new BeanPropertyRowMapper<Account>(Account.class)).mapRow(rs, rowNum);
			AccountType accountType = (new BeanPropertyRowMapper<AccountType>(AccountType.class)).mapRow(rs, rowNum);
			// setting acount type for an account
			account.setAccountType(accountType);
			// setting account for a transaction
			transaction.setAccount(account);
			return transaction;
		}
	}

}
