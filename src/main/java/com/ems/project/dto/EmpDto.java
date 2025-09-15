package com.ems.project.dto;

import java.util.List;
import java.util.Optional;

import com.ems.project.entity.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpDto {

	private Long empNo;

	private String name;
	
	@Min(18)
	@Max(65)
	private Integer age;

	@Email( message = "Email should be valid")
	private String email;

	private Long phoneNo;

	private List<AddressDto> addressDto;

	private SalaryDto salaryDto;

	private JobDto jobDto;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<EmpDeptDetailsDto> empDeptDetailsDto;

	public EmpDto(Employee emp) {
		this.empNo = emp.getEmpNo();
		this.name = emp.getName();
		this.age = emp.getAge();
		this.email = emp.getEmail();
		this.phoneNo = emp.getPhoneNo();
	}

	public EmpDto(Employee emp, boolean includeRelation) {
		this(emp);

		if (includeRelation) {
			// Map Address list → AddressDto list
			if (emp.getAddressMappings() != null) {
				this.addressDto = Optional.ofNullable(emp.getAddressMappings()).orElse(List.of()).stream()
						.map(AddressDto::new).toList();
			}

			// Map Salary → SalaryDto
			if (emp.getSalary() != null) {
				this.salaryDto = new SalaryDto(emp.getSalary());
			}

			// Map Job → JobDto
			if (emp.getJob() != null) {
				this.jobDto = new JobDto(emp.getJob());
			}

			if (emp.getEmpDeptDetails() != null) {
				this.empDeptDetailsDto = Optional.ofNullable(emp.getEmpDeptDetails()).orElse(List.of()).stream()
						.map(EmpDeptDetailsDto::new).toList();

			}

		}
	}

}
