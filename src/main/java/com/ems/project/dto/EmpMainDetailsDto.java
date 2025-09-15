package com.ems.project.dto;

import com.ems.project.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpMainDetailsDto {

	private Long empNo;

	private String name;

	private Integer age;

	private String email;

	private Long phoneNo;

	public EmpMainDetailsDto(Employee emp) {
		this.empNo = emp.getEmpNo();
		this.name = emp.getName();
		this.age = emp.getAge();
		this.email = emp.getEmail();
		this.phoneNo = emp.getPhoneNo();
	}

}
