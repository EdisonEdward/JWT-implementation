package com.acc.SpringDataJPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acc.SpringDataJPA.dto.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	
	
	Member findByMemberId(String memberId);
	
	
	Member findByFirstName(String firstName);

}
