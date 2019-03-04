package com.acc.SpringDataJPA.controller;

import java.util.HashMap;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acc.SpringDataJPA.config.JwtTokenUtil;
import com.acc.SpringDataJPA.dto.Account;
import com.acc.SpringDataJPA.dto.Employee;
import com.acc.SpringDataJPA.dto.Member;
import com.acc.SpringDataJPA.repository.AccountRepository;
import com.acc.SpringDataJPA.repository.EmployeeRepository;
import com.acc.SpringDataJPA.repository.MemberRepository;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	AccountRepository accountrepository;

	@Autowired
	MemberRepository memberrepository;

	@Autowired
	EmployeeRepository employeerepository;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@RequestMapping("/welcome")
	public String home() {

		System.out.println("controller processed ");
		return "welcome";

	}

	/**
	 * Read all the accounts
	 */

	@GetMapping("/getAllAccount")
	public List<Account> getAllAccount() {
		
		

		List<Account> accountlist = accountrepository.findAll();
		return accountlist;

	}

	/**
	 * Read a particular account by Id
	 * 
	 * @param accountId
	 * @return
	 */
	@GetMapping("/getAccount/{accountId}")
	public Account getAccountById(@PathVariable String accountId) {

		Account account = accountrepository.findByAccountId(accountId);
		return account;

	}

	/**
	 * Read all the members
	 * 
	 * @return
	 */
	@GetMapping("/getAllMember")
	public List<Member> getAllMember() {
		
		

		List<Member> memberlist = memberrepository.findAll();
		return memberlist;

	}

	/**
	 * <p>
	 * Read a particular member by Id
	 * </p>
	 * 
	 * @param memberId
	 * @return
	 */
	@GetMapping("/getMember/{memberId}")
	public Member getMemberbyId(@PathVariable String memberId) {

		Member member = memberrepository.findByMemberId(memberId);
		return member;

	}

	/**
	 * insert a account with list of members
	 * 
	 * @param account
	 * @return
	 */
	@PostMapping("/insertAccount")
	public ResponseEntity<String> insertAccount(@RequestBody Account account) {

		accountrepository.save(account);
		return new ResponseEntity<String>("Successfully inserted", HttpStatus.OK);

	}

	/**
	 * insert list of accounts with list of members
	 * 
	 * @param accounts
	 * @return
	 */
	@PostMapping("/insertAccounts")
	public ResponseEntity<String> insertAccounts(@RequestBody List<Account> accounts) {

		accountrepository.saveAll(accounts);
		return new ResponseEntity<String>("Successfully inserted", HttpStatus.OK);

	}

	//
	/**
	 * insert a member with list of account
	 * 
	 * @param member
	 * @return
	 */
	@PostMapping("/insertMember")
	public ResponseEntity<String> insertMember(@RequestBody Member member) {

		memberrepository.save(member);
		return new ResponseEntity<String>("Successfully inserted", HttpStatus.OK);

	}

	/**
	 * insert list of members with list of accounts
	 * 
	 * @param members
	 * @return
	 */
	@PostMapping("/insertMembers")
	public ResponseEntity<String> insertMembers(@RequestBody List<Member> members) {

		memberrepository.saveAll(members);
		return new ResponseEntity<String>("Successfully inserted", HttpStatus.OK);

	}

	/**
	 * Delete a account by Id
	 * 
	 * @param accountId
	 * @return
	 */
	@PostMapping("/deleteAccount/{accountId}")
	public List<Account> deleteAccountById(@PathVariable String accountId) {

		accountrepository.deleteById(accountId);
		List<Account> account = accountrepository.findAll();
		return account;

	}

	/**
	 * Delete a member by Id
	 * 
	 * @param memberId
	 * @return
	 */
	@PostMapping("/deleteMember/{memberId}")
	public List<Member> deleteMemberById(@PathVariable String memberId) {

		memberrepository.deleteById(memberId);
		List<Member> memberlist = memberrepository.findAll();
		return memberlist;

	}

	/**
	 * Insert a Member to an existing Account
	 * 
	 * @param member
	 * @param accountId
	 * @return
	 */
	@PostMapping("/insertMemberToAnExistingAccount/{accountId}")
	@Transactional
	public ResponseEntity<Member> updatemember(@RequestBody Member member, @PathVariable String accountId) {

		Account account = accountrepository.findByAccountId(accountId);
		List<Member> memberlist = account.getMembers();
		memberlist.add(member);
		account.setMembers(memberlist);
		accountrepository.save(account);
		return new ResponseEntity<Member>(member, HttpStatus.OK);

	}

	/**
	 * Insert a Account to an existing Member
	 * 
	 * @param account
	 * @param memberId
	 * @return
	 */
	@PostMapping("/insertAccountToAnExistingMember/{memberId}")
	public ResponseEntity<Account> insertAccountToAnExistingMember(@RequestBody Account account,
			@PathVariable String memberId) {

		Member member = memberrepository.findByMemberId(memberId);
		List<Account> accountList = member.getAccounts();
		accountList.add(account);
		member.setAccounts(accountList);
		memberrepository.save(member);
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	/**
	 * Update Member Data Attributes By MemberId
	 * 
	 * @param memberId
	 * @param map
	 * @return
	 */
	@PostMapping("/updateMemberDataAttributes/{memberId}")
	public Member updateMemberDataAttributes(@PathVariable String memberId, @RequestBody HashMap<String, String> map) {

		Member member = memberrepository.findByMemberId(memberId);

		for (String key : map.keySet()) {

			if (key.equals("firstName")) {
				member.setFirstName(map.get(key));
			}
			if (key.equals("lastName")) {
				member.setLastName(map.get(key));
			}
			if (key.equals("dob")) {
				member.setDob(map.get(key));
			}
			if (key.equals("gender")) {
				member.setGender(map.get(key));
			}
		}
		memberrepository.save(member);

		Member updatedmember = memberrepository.findByMemberId(memberId);

		return updatedmember;

	}

	/**
	 * Update Account Data Attributes by AccountId
	 * 
	 * @param accountId
	 * @param map
	 * @return
	 */
	@PostMapping("/updateAccountDataAttributes/{accountId}")
	public Account updateAccountDataAttributes(@PathVariable String accountId,
			@RequestBody HashMap<String, String> map) {

		Account account = accountrepository.findByAccountId(accountId);

		for (String key : map.keySet()) {

			if (key.equals("accountName")) {
				account.setAccountName(map.get(key));
			}
			if (key.equals("State")) {
				account.setState(map.get(key));
			}
			if (key.equals("zipcode")) {
				account.setZipCode(map.get(key));
			}
			if (key.equals("effDate")) {
				account.setEffDate(map.get(key));
			}
		}
		accountrepository.save(account);

		Account updatedAccount = accountrepository.findByAccountId(accountId);

		return updatedAccount;

	}

	@PostMapping("/updateMemberAttributes")
	public String updateMemberAttributes(@RequestBody Member member) {

		memberrepository.save(member);
		return "succesfully edited";

	}

	@PostMapping("/insertEmployee")
	public String insertEmployee(@RequestBody Employee emmployee) {

		employeerepository.save(emmployee);

		return "Successfully inserted";

	}

}
