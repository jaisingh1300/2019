package com.example.Ewallet.request;

import org.springframework.stereotype.Component;

@Component
public class Transactionrequest {

	private Long sendermobileNo;
	private Long receiverMobileNo;
	private Double amount;
	private String senderpassword;
	private String receiverpassword;
	
	public String getReceiverpassword() {
		return receiverpassword;
	}
	public void setReceiverpassword(String receiverpassword) {
		this.receiverpassword = receiverpassword;
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
	public String getSenderpassword() {
		return senderpassword;
	}
	public void setSenderpassword(String senderpassword) {
		this.senderpassword = senderpassword;
	}
	
}
