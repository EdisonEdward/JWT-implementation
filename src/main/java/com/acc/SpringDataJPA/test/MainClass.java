package com.acc.SpringDataJPA.test;

import java.util.function.Function;

import org.springframework.web.client.RestTemplate;

import com.acc.SpringDataJPA.dto.Employee;

public class MainClass {
	
	
	
	public static void main (String[] args) {
		
		System.out.println("----------");
		
		
		Employee emp = new Employee(1234,"Edison","24","2100","aero");
		
		MainClass obj = new MainClass();
		
		System.out.println(obj.getattributes(emp, Employee::getName));
		
		

	}
	
	
	public <T> T getattributes(Employee emp,Function<Employee,T> empfunction){
		
		
	return	empfunction.apply(emp);
		
	}

}
