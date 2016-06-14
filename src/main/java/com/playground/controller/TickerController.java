package com.playground.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.playground.service.DatabaseService;

@RestController
public class TickerController {
	


	@RequestMapping(
			value = "/tickers",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<String>> getTickers() throws SQLException {
		DatabaseService databaseService = new DatabaseService();
		databaseService.setUp();
		List<String> tickerList = databaseService.getStocks();
		if(tickerList.isEmpty()) {
			return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<String>>(tickerList,HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/upload",
			method=RequestMethod.PUT,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<String> uploadData()
	{
		return null;
	}
}
