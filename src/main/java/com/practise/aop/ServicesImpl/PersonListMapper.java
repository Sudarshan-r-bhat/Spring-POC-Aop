package com.practise.aop.ServicesImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.practise.aop.models.Person;

public class PersonListMapper implements ResultSetExtractor<List<Person>>{

	@Override
	public List<Person> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Person> result = new ArrayList<>();	
		while(rs.next()) {
			Person p = new Person();
			p.setId(rs.getLong("ID"));
			p.setFirstName(rs.getString("FIRSTNAME"));
			p.setLastName(rs.getString("LASTNAME"));
			p.setAddress(rs.getString("ADDRESS"));
			p.setContactNumber(rs.getLong("CONTACTNUMBER"));
			
			result.add(p);
		}
		return result;
	}
}
