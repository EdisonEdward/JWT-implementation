package com.acc.SpringDataJPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acc.SpringDataJPA.dto.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
