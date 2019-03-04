package com.acc.SpringDataJPA.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "Account")
public class Account implements Serializable{

	@Id
	@Column(name = "accountId")
	private String accountId;

	@Column(name = "AccountName")
	private String accountName;

	@Column(name = "EffectiveDate")
	private String effDate;

	@Column(name = "State")
	private String state;

	@Column(name = "ZipCode")
	private String zipCode;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
	@JoinTable(name = "account_member", joinColumns = { @JoinColumn(name = "accountId",referencedColumnName="accountId") }, inverseJoinColumns = {
			@JoinColumn(name = "memberId",referencedColumnName="memberId") })
	@JsonManagedReference
	private List<Member> members = new ArrayList<>();

	@Override
	public String toString() {
		return "Account [AccountId=" + accountId + ", accountName=" + accountName + ", EffDate=" + effDate + ", state="
				+ state + ", zipCode=" + zipCode + "]";
	}

	public String getAccountId() {
		return accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getEffDate() {
		return effDate;
	}

	public String getState() {
		return state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Account(String accountId, String accountName, String effDate, String state, String zipCode) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.effDate = effDate;
		this.state = state;
		this.zipCode = zipCode;

	}

	public Account() {
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

    public void setMembers(List<Member> members) {
		this.members = members;
	}

}
