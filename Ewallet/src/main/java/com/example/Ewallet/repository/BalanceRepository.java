package com.example.Ewallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.Ewallet.entity.Balance;

@Transactional
public interface BalanceRepository extends JpaRepository<Balance,Long>{

	Balance findByUserid(Long user_id);
	
	@Modifying
	@Query("update Balance b set b.amount= :amount where b.userid= :userid")
	public void updateAmount(@Param("amount") Double amount,@Param("userid") Long userid);
}
