package com.playground.controller;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.playground.model.Greeting;

@RestController
public class GreetingController {
	
	private static BigInteger nextId;
	private static Map<BigInteger,Greeting> greetingMap;
	
	private static Greeting save(Greeting greeting) {
		if(greetingMap == null) {
			greetingMap = new HashMap<BigInteger, Greeting>();
			nextId = BigInteger.ONE;
		}
		//In case of update
		if(greeting.getId() != null ){
			Greeting updateGreeting = greetingMap.get(greeting.getId());
			if(updateGreeting == null) {
				return null;
			}
			greetingMap.remove(greeting.getId());
			greetingMap.put(greeting.getId(), greeting);
			return greeting;
		}
		greeting.setId(nextId);
		nextId = nextId.add(BigInteger.ONE);
		greetingMap.put(greeting.getId(), greeting);
		return greeting;
	}
	
	static {
		Greeting g1 =  new Greeting();
		g1.setText("Hello world!");
		save(g1);
		
		Greeting g2 = new Greeting();
		g2.setText("Namaskara");
		save(g2);
	}
	
	@RequestMapping(
			value="/greetings",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() {
		
		Collection<Greeting> greetings = greetingMap.values();
		return new ResponseEntity<Collection<Greeting>>(greetings,HttpStatus.OK);
		
	}
	
	@RequestMapping(
			value="/greeting/{id}",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id")BigInteger id) {
		Greeting greeting = greetingMap.get(id);
		if(greeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Greeting>(greeting,HttpStatus.OK);
	}
	
	@RequestMapping(value="/greeting",
				method=RequestMethod.POST,
				produces=MediaType.APPLICATION_JSON_VALUE,
				consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> greeting(@RequestBody Greeting greeting) {
		Greeting saveGreeting = save(greeting);
		return new ResponseEntity<Greeting>(saveGreeting,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/greeting",
				method = RequestMethod.PUT,
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) {
		Greeting ug = save(greeting);
		if(ug == null) {
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Greeting>(ug,HttpStatus.OK);
	}
}
