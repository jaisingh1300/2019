package com.example.Ewallet.dto;

public class UserDTO {

	private Long userid;
	private String userName;
	private Long mobileNo;
	private Double amount;
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public UserDTO(Long userid, String userName, Long mobileNo, Double amount) {
		this.userid = userid;
		this.userName = userName;
		this.mobileNo = mobileNo;
		//this.password = password;
		this.amount = amount;
	}
	
	
	
}

