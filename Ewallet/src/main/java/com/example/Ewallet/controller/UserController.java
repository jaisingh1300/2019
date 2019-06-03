package com.example.Ewallet.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Ewallet.dto.UserDTO;
import com.example.Ewallet.entity.Transaction;
import com.example.Ewallet.entity.User;
import com.example.Ewallet.exception.EwalletCustomException;
import com.example.Ewallet.request.Transactionrequest;
import com.example.Ewallet.service.UserService;



@Controller
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@PostMapping("/adduser")
	public ResponseEntity<UserDTO> addUser(@RequestBody User user) throws Exception {
		final String METHODNAME = "addUser";
		logger.info("Entered {} with {}",METHODNAME,user);
		
		UserDTO userdto;
			userdto = userService.adduser(user);
			
			logger.info("Exiting {} with {}",METHODNAME,userdto);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userdto);
	}
	
	@GetMapping("/get/{mobile}")
	public ResponseEntity<UserDTO> getuser(@PathVariable("mobile") Long mobile) throws Exception {
		final String METHODNAME = "getuser";
		logger.info("Entered {} with {}",METHODNAME,mobile);
		
		UserDTO user1;

			user1 = userService.getuser(mobile);
			
			logger.info("Exiting {} with {}",METHODNAME,user1);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(user1);

	}

	@PostMapping("/addmoney")
	public ResponseEntity<Transaction> addmoney(@RequestBody Transactionrequest transactionrequest) {
		final String METHODNAME = "addUser";
		logger.info("Entered {} with {}",METHODNAME,transactionrequest);
		
		Transaction newtransaction = userService.addMoneyToWallet(transactionrequest);
		
		logger.info("Exiting {} with {}",METHODNAME,newtransaction);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(newtransaction);
	}
	
	
	@PostMapping("/transfermoney")
	public ResponseEntity<Transaction> transferMoney(@RequestBody Transactionrequest transactionrequest) {
		final String METHODNAME = "transferMoney";
		logger.info("Entered {} with {}",METHODNAME,transactionrequest);
		
		Transaction newtransaction = userService.transferMoney(transactionrequest);
		
		logger.info("Exiting {} with {}",METHODNAME,newtransaction);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(newtransaction);
	}
	
	@GetMapping("/showtransactions/{mobileNo}")
	public ResponseEntity<List<Transaction>> getTransactions(@PathVariable("mobileNo") Long mobile) {
		final String METHODNAME = "getTransactions";
		logger.info("Entered {} with {}",METHODNAME,mobile);
		
		List<Transaction> list = userService.getTransactions(mobile);
		
		logger.info("Exiting {} with list size",METHODNAME,list.size());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
	}
	
}

