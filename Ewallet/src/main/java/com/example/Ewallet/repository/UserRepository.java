package com.example.Ewallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ewallet.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByMobileNo(Long mobile);
	
	
}
