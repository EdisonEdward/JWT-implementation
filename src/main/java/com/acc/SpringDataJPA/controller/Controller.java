package com.acc.SpringDataJPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.acc.SpringDataJPA.dto.Account;
import com.acc.SpringDataJPA.dto.Member;
import com.acc.SpringDataJPA.repository.AccountRepository;

@org.springframework.stereotype.Controller
public class Controller {
	@Autowired
	AccountRepository accountrepository;
	
	@RequestMapping("/index")
	public ModelAndView home() {

		System.out.println("controller processed ");

		return new ModelAndView("index");

	}
	
	@RequestMapping("/indexjar")
	public ModelAndView welcomehome() {

		System.out.println("controller processed2 ");

		return new ModelAndView("welcome");

	}
	@PostMapping("/insertAccountByMethod")
	public List<Account> getrecords(){
		
		
		Account account = new Account("1200101", "Shift Corps", "12/01/2018", "TX", "4501");
		
		Account account1 = new Account("1345617", "Walmart", "12/07/2018", "IL", "6791");
		
		
		Member member = new Member("678101", "Lousi", "admas", "08/12/1976", "Male");
		
		Member member1 = new Member("678102", "henry", "Williams", "05/06/1979", "Male");
		
		
		account.getMembers().add(member);
		account.getMembers().add(member1);
		
		
		
		member.getAccounts().add(account);
		member1.getAccounts().add(account);
		
		
		accountrepository.save(account);
		
		account1.getMembers().add(member);
		account1.getMembers().add(member1);
		
		member.getAccounts().add(account1);
		member1.getAccounts().add(account1);
		
		accountrepository.save(account1);
		
		List<Account> accountlist = accountrepository.findAll();
		
		
		return accountlist;
		
		
		
	}
	
}
