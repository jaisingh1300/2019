package com.example.Ewallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Ewallet.entity.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{
	
	@Query("from Transaction t where t.sendermobileNo=:sendermobileNo OR t.receiverMobileNo=:receiverMobileNo")
	public List<Transaction> listtransactions(@Param("sendermobileNo") Long mobileNo,@Param("receiverMobileNo") Long mobileNo1);

}
