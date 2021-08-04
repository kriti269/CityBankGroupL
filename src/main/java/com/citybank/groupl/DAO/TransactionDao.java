package com.citybank.groupl.DAO;

import java.sql.Date;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.citybank.groupl.bean.Transaction;

public class TransactionDao {

	public JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int saveTransaction(int accountId, double amount, String txType) {
		String sql = "insert into transaction(account_id, tx_date_time, tx_type, amount) values(?,?,?,?);";
		int result = 0;
		try {
			List<SqlParameter> listParams = Arrays.asList(new SqlParameter(Types.INTEGER, "account_id"),
					new SqlParameter(Types.DATE, "tx_date_time"), 
					new SqlParameter(Types.VARCHAR, "tx_type"),
					new SqlParameter(Types.DOUBLE, "amount"));

			PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sql, listParams) {
				{
					setReturnGeneratedKeys(true);
					setGeneratedKeysColumnNames("transaction_id");
				}
			};

			List txParams = Arrays.asList(accountId,new Date(System.currentTimeMillis()),txType, amount);
			PreparedStatementCreator psc = pscf.newPreparedStatementCreator(txParams);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(psc, keyHolder);
			return Integer.parseInt(keyHolder.getKey().toString());
		}
		catch(Exception ex) {
			System.out.println("Error occurred while saving transaction: "+ ex.getMessage());
		}
		return result;
	}
	
	public List<Transaction> viewAllTransactions(int accountId){
		String sql = "select * from transaction where account_id=?";
		List<Transaction>  allTransactions = null;
		try {
			allTransactions = jdbcTemplate.query(sql, new Object[] {accountId},
					new int[] {java.sql.Types.INTEGER}, new BeanPropertyRowMapper<Transaction>());
		}
		catch(Exception ex) {
			System.out.println("Error occurred while saving transaction: "+ ex.getMessage());
		}
		return allTransactions;
	}

}
