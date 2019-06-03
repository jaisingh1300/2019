package com.example.Ewallet.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="balance")
public class Balance {

	@Id
	private Long userid;
	private Double amount;
	public Long getUser_id() {
		return userid;
	}
	public void setUser_id(Long user_id) {
		this.userid = user_id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Balance [user_id=" + userid + ", amount=" + amount + "]";
	}
	public Balance(Long user_id, Double amount) {
		super();
		this.userid = user_id;
		this.amount = amount;
	}
	
	public Balance() {
		// TODO Auto-generated constructor stub
	}
	
}
