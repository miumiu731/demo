package com.hperson.demo.domain;

import java.io.Serializable;

public class InfoPojo implements Serializable{

	private static final long serialVersionUID = 1992934911512692010L;
	
	private String companyName;
	private String bankName;
	private String bankAccount;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	
}
