package com.example.Ewallet.service;

import java.util.List;

import com.example.Ewallet.dto.UserDTO;
import com.example.Ewallet.entity.Transaction;
import com.example.Ewallet.entity.User;
import com.example.Ewallet.exception.EwalletCustomException;
import com.example.Ewallet.request.Transactionrequest;

public interface UserService {
	
	UserDTO adduser(User user) throws EwalletCustomException;

	UserDTO getuser(Long mobile) throws EwalletCustomException;

	Transaction addMoneyToWallet(Transactionrequest transactionrequest);
	
	//Transaction transferMoney(Transaction transaction);
	
	List<Transaction> getTransactions(Long mobileNo);

	Transaction transferMoney(Transactionrequest transactionrequest);
}
