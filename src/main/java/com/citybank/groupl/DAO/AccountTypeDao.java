package com.citybank.groupl.DAO;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.citybank.groupl.bean.AccountType;

public class AccountTypeDao {
	public JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<AccountType> getAllAccountTypes(){
		String sql = "select * from account_type";
		List<AccountType> accountTypes = null;
		try {
			accountTypes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<AccountType>(AccountType.class));
		}
		catch(Exception ex) {
			// prints any exception occurred
			System.out.println("Unable to get all account types: " + ex.getMessage());
		}
		return accountTypes;
	}
	
}
