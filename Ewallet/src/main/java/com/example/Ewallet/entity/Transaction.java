package com.example.Ewallet.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="transactions")
public class Transaction {

	@Id
	@GeneratedValue
	private Long transactionid;
	private Long sendermobileNo;
	private Long receiverMobileNo;
	private Double amount;
	private String status;
	
	public Transaction() {
		
	}
	
	public Transaction(Long sendermobileNo, Long receiverMobileNo, Double amount, String status) {
		super();
		this.sendermobileNo = sendermobileNo;
		this.receiverMobileNo = receiverMobileNo;
		this.amount = amount;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public Long getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(Long transactionid) {
		this.transactionid = transactionid;
	}
	public Long getSendermobileNo() {
		return sendermobileNo;
	}
	public void setSendermobileNo(Long sendermobileNo) {
		this.sendermobileNo = sendermobileNo;
	}
	public Long getReceiverMobileNo() {
		return receiverMobileNo;
	}
	public void setReceiverMobileNo(Long receiverMobileNo) {
		this.receiverMobileNo = receiverMobileNo;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
