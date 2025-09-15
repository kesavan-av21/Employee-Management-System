package com.ems.project.dto;

import com.ems.project.entity.Salary;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalaryDto {

	private long id;

	private long empNo;

	private double salary;

	public SalaryDto(Salary sal) {

		this.id = sal.getId();
		this.empNo = sal.getEmployee().getEmpNo();
		this.salary = sal.getSalary();

	}

}
