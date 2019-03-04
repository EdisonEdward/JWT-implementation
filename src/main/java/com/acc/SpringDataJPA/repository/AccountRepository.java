package com.acc.SpringDataJPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acc.SpringDataJPA.dto.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	
	
	
	Account findByAccountId(String accountId);
	
	

}
