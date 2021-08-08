package com.citybank.groupl.DAO;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.citybank.groupl.bean.Address;

/**
 * @since 11-07-2021
 * @author Group L - Kriti, Jatin, Varun, Sonia
 * @serial 1.0
 * @summary Database operations related to address table are performed in
 *          Address dao
 */

/*
 * Date - 11-07-2021 Author - Kriti, Jatin, Varun, Sonia Description - Database
 * operations related to address table are performed in Address dao
 */

@Component
public class AddressDao {
	JdbcTemplate jdbcTemplate;

	// getter for jdbc template
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	// setter for jdbc template
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// Inserts new address into the address table, sets the
	// generated addressId in the object and returns the object
	public Address saveAddress(final Address address) {
		final String sql = "insert into address(line_1,line_2,city,state,country,zip) values(?,?,?,?,?,?)";

		try {
			// insert the row by using PreparedStatementSetter object to set model
			// attributes inside SQL query
			List<SqlParameter> listParams = Arrays.asList(new SqlParameter(Types.VARCHAR, "line_1"),
					new SqlParameter(Types.VARCHAR, "line_2"), new SqlParameter(Types.VARCHAR, "city"),
					new SqlParameter(Types.VARCHAR, "state"), new SqlParameter(Types.VARCHAR, "country"),
					new SqlParameter(Types.VARCHAR, "zip"));

			PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sql, listParams) {
				{
					setReturnGeneratedKeys(true);
					setGeneratedKeysColumnNames("address_id");
				}
			};

			// list of string for address params
			List<String> addressParams = Arrays.asList(address.getLine1(), address.getLine2(), address.getCity(),
					address.getState(), address.getCountry(), address.getZip());
			PreparedStatementCreator psc = pscf.newPreparedStatementCreator(addressParams);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(psc, keyHolder);

			// set address id
			address.setAddressId(Integer.parseInt(keyHolder.getKey().toString()));
			return address;

		} catch (Exception e) {
			// prints if any exception occurs
			System.out.println("Error occurred while saving address. " + e.getMessage());
		}
		// returns number of rows affected as either 0 or 1
		return null;
	}

}