package com.example.Ewallet.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Ewallet.controller.UserController;
import com.example.Ewallet.dto.UserDTO;
import com.example.Ewallet.entity.Balance;
import com.example.Ewallet.entity.Transaction;
import com.example.Ewallet.entity.User;
import com.example.Ewallet.exception.EwalletCustomException;
import com.example.Ewallet.repository.BalanceRepository;
import com.example.Ewallet.repository.TransactionRepository;
import com.example.Ewallet.repository.UserRepository;
import com.example.Ewallet.request.Transactionrequest;
import com.example.Ewallet.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	public Long SYSTEMSENDERMOBILENO=1000000000L;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	BalanceRepository balanceRepository;
	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public UserDTO adduser(User user) throws EwalletCustomException {
		final String METHODNAME = "addUser";
		logger.info("Entered {} with {}",METHODNAME,user);
		
	if(user.getMobileNo() == null) {
		throw new EwalletCustomException("mobile number cannot be blank in request");
	}
	User existuser = userRepository.findByMobileNo(user.getMobileNo());
	if(existuser != null) {
		throw new EwalletCustomException("user already exist with mobile number "+user.getMobileNo());
	}
	User useradded = userRepository.save(user);
	
	Balance balance = new Balance(useradded.getUserid(), 0.0);
	
	Balance balanceadded = balanceRepository.save(balance);
	
	UserDTO  userdto = new UserDTO(useradded.getUserid(), useradded.getUserName(), useradded.getMobileNo(), balanceadded.getAmount());
	
	logger.info("Exiting {} with {}",METHODNAME,userdto);
	return userdto;
	
	}	
	
	@Override
	public UserDTO getuser(Long mobile) throws EwalletCustomException {
		final String METHODNAME = "getuser";
		logger.info("Entered {} with mobileno {}",METHODNAME,mobile);
		
		User user = userRepository.findByMobileNo(mobile);
		if(user == null) {
			throw new EwalletCustomException("user dosn't not exist with mobile number " + mobile);
		}
		Balance balance = balanceRepository.findByUserid(user.getUserid());
		UserDTO userdto = new UserDTO(user.getUserid(), user.getUserName(), user.getMobileNo(),balance.getAmount());
		
		logger.info("Exiting {} with {}",METHODNAME,userdto);
		return userdto;
	}
	
	
	@Override
	public Transaction addMoneyToWallet(Transactionrequest transactionrequest) {
		final String METHODNAME = "addMoneyToWallet";
		logger.info("Entered {} with {}",METHODNAME,transactionrequest);
		
		if(transactionrequest.getReceiverMobileNo() == null) {
			throw new EwalletCustomException("mobile number cannot be blank in request");
		}
		if(transactionrequest.getSenderpassword() == null) {
			throw new EwalletCustomException("password cannot be blank in request");
		}
		
		
		User existuser = userRepository.findByMobileNo(transactionrequest.getReceiverMobileNo());
		if(existuser == null) {
			throw new EwalletCustomException("user dosn't not exist with mobile number " + transactionrequest.getReceiverMobileNo());
		}
		if(!transactionrequest.getSenderpassword().equals(existuser.getPassword())) {
			throw new EwalletCustomException("password is incorrect of mobile number " + transactionrequest.getReceiverMobileNo());
		}
		
		Balance existbalance = balanceRepository.findByUserid(existuser.getUserid());
		if(existbalance == null) {
			throw new EwalletCustomException("wallet dosn't not associated with mobile number " + transactionrequest.getReceiverMobileNo());
		}
		
		Double amount = existbalance.getAmount()+transactionrequest.getAmount();
		
		logger.info("{} :Existing receiver {}, balance : {}",METHODNAME,existuser,existbalance.getAmount());
		
		balanceRepository.updateAmount(amount, existuser.getUserid());
		
		Transaction transaction =new Transaction(SYSTEMSENDERMOBILENO,transactionrequest.getReceiverMobileNo(),transactionrequest.getAmount(),"200");

		Transaction transactionadded = transactionRepository.save(transaction);
		
		logger.info("Exiting {} with {}",METHODNAME,transactionadded);
		
		return transactionadded;
	}

	@Override
	public Transaction transferMoney(Transactionrequest transactionrequest) {
		final String METHODNAME = "transferMoney";
		logger.info("Entered {} with {}",METHODNAME,transactionrequest);
		
		
		if(transactionrequest.getSendermobileNo() == null) {
			throw new EwalletCustomException("sender mobile number cannot be blank in request");
		}
		if(transactionrequest.getSenderpassword() == null) {
			throw new EwalletCustomException("sender password cannot be blank in request");
		}

		User existsenderuser = userRepository.findByMobileNo(transactionrequest.getReceiverMobileNo());
		if(existsenderuser == null) {
			throw new EwalletCustomException("user dosn't not exist with mobile number " + transactionrequest.getReceiverMobileNo());
		}
		
		if(!transactionrequest.getSenderpassword().equals(existsenderuser.getPassword())) {
			throw new EwalletCustomException("password is incorrect of sender mobile number " + transactionrequest.getReceiverMobileNo());
		}
		
		if(transactionrequest.getReceiverMobileNo() == null) {
			throw new EwalletCustomException("receiver mobile number cannot be blank in request");
		}
		User existreceiveruser = userRepository.findByMobileNo(transactionrequest.getReceiverMobileNo());
		
		if(existreceiveruser == null) {
			throw new EwalletCustomException("user dosn't not exist with receiver mobile number " + transactionrequest.getReceiverMobileNo());
		}
		
		Balance existsenderbalance = balanceRepository.findByUserid(existsenderuser.getUserid());
		if(existsenderbalance == null && existsenderbalance.getAmount()-transactionrequest.getAmount() < 0) {
			throw new EwalletCustomException("sender "+ transactionrequest.getReceiverMobileNo()+" doesn't have sufficient balance. current balance is " + existsenderbalance.getAmount());
		}
		
		Balance existreceiverbalance = balanceRepository.findByUserid(existreceiveruser.getUserid());
		if(existreceiverbalance == null) {
			throw new EwalletCustomException("wallet dosn't not associated with mobile number " + transactionrequest.getReceiverMobileNo());
		}
		
		logger.info("{} :Existing sender {} , balance : {}",METHODNAME,existsenderuser,existsenderbalance.getAmount());
		logger.info("{} :Existing receiver {}, balance : {}",METHODNAME,existreceiveruser,existreceiverbalance.getAmount());
		
		Double senderUpdatedBalance = existsenderbalance.getAmount() + transactionrequest.getAmount();
		Double receiverUpdatedBalance = existreceiverbalance.getAmount() + transactionrequest.getAmount();
		
		balanceRepository.updateAmount(senderUpdatedBalance, existsenderuser.getUserid());
		balanceRepository.updateAmount(receiverUpdatedBalance, existreceiveruser.getUserid());
		
		Transaction transaction =new Transaction(transactionrequest.getSendermobileNo(),transactionrequest.getReceiverMobileNo(),transactionrequest.getAmount(),"200");

		Transaction transactionadded = transactionRepository.save(transaction);
		
		logger.info("Exiting {} with {}",METHODNAME,transactionadded);
		return transactionadded;
	}

	@Override
	public List<Transaction> getTransactions(Long mobileNo) {
		final String METHODNAME = "getTransactions";
		logger.info("Entered {} with mobile no {}",METHODNAME,mobileNo);
		
		if(mobileNo == null) {
			throw new EwalletCustomException("mobile number cannot be blank in reques");
		}
		
		User existuser = userRepository.findByMobileNo(mobileNo);
		if(existuser == null) {
			throw new EwalletCustomException("user dosn't not exist with mobile number " + mobileNo);
		}
		List<Transaction> list =transactionRepository.listtransactions(mobileNo, mobileNo);
		logger.info("Exiting {} with list size {}",METHODNAME,list.size());
		return list;
	
	}

}
