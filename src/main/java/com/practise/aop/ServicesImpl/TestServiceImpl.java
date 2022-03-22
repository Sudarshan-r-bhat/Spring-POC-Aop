package com.practise.aop.ServicesImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import com.practise.aop.models.Person;



@Service
public class TestServiceImpl {
	private final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Autowired
	NamedParameterJdbcOperations jdbcTemplate;
	
	public List<Person> findAllPerson() {
		
		String query = "SELECT * FROM PERSON";
		Map<String, Object> paramMap = new HashMap<>();
		
		List<Person> people = jdbcTemplate.query(query, paramMap, (rs) -> {
				
			List<Person> result = new ArrayList<>();
			while(rs.next()) {
				
				Person p = new Person();
				p.setId(rs.getLong("ID"));
				p.setFirstName(rs.getString("FIRSTNAME"));
				p.setLastName(rs.getString("LASTNAME"));
				p.setAddress(rs.getString("ADDRESS"));
				String contact = rs.getString("CONTACTNUMBER");
				p.setContactNumber(contact.length() > 0 ? Long.parseLong(contact): -1);
				
				result.add(p);
			}
		return result;
	});
		return people;
	}
}
