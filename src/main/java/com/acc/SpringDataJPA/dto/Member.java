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

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "Member")
public class Member implements Serializable {

	@Id
	@Column(name = "memberId")
	private String memberId;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "DateofBirth")
	private String dob;

	@Column(name = "gender")
	private String gender;
	
	public Member() {
	}

	public Member(String memberId, String firstName, String lastName, String dob, String gender
		) {
		super();
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		
	}

	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {
					CascadeType.MERGE
			}
			)
	@JoinTable(name="account_member" , joinColumns= {@JoinColumn(name="memberId",referencedColumnName="memberId")},
	inverseJoinColumns= {@JoinColumn(name="accountId",referencedColumnName="accountId")})
	@JsonBackReference
	private List<Account> accounts = new ArrayList<>();

	public String getMemberId() {
		return memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDob() {
		return dob;
	}

	public String getGender() {
		return gender;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Member [MemberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", gender=" + gender + "]";
	}

}
