package com.practise.aop.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practise.aop.ServicesImpl.TestServiceImpl;
import com.practise.aop.models.Person;

@RestController
@CrossOrigin(value= {"*"})
@RequestMapping(value="app/aop/api/", name="aop test controller")
public class AopTestController {
	private Logger logger = LoggerFactory.getLogger(AopTestController.class);
	@Autowired TestServiceImpl service;
	
	@GetMapping(value="/allPerson") public ResponseEntity getPeople() {
		
		List<Person> result = service.findAllPerson();
		logger.info("Fetched {} rows", result.size());
		
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/test", name ="test for arguments capture")
	public ResponseEntity<?> testForArguments(@RequestBody List<String> values) {
		logger.info("{}", values.get(2));
		return ResponseEntity.ok().build();
	}

}
