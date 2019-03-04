package com.acc.SpringDataJPA.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name ="Employee")
public class Employee {
	
	@Id
	@Column(name="employeeId")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="age")
	private String age;
	
	@Column(name="salary")
	private String salary;
	
	@Column(name ="dept" ,updatable=false)
	private String dept;

	public Integer getId() {
		return id;
	}

	public Employee(Integer id, String name, String age, String salary, String dept) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.dept = dept;
	}

	public Employee() {
	}

	public String getName() {
		return name;
	}

	public String getAge() {
		return age;
	}

	public String getSalary() {
		return salary;
	}

	public String getDept() {
		return dept;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + ", dept=" + dept + "]";
	}

}
