package com.ems.project.dto;

import com.ems.project.entity.EmpDeptDetails;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpDeptDetailsDto {

	private long id;

	private long empNo;

	private long deptNo;

	public EmpDeptDetailsDto(EmpDeptDetails empDeptDetails) {
		this.id = empDeptDetails.getId();
		if (empDeptDetails.getEmployee() != null) {
			this.empNo = empDeptDetails.getEmployee().getEmpNo();
		}
		if (empDeptDetails.getDept() != null) {
			this.deptNo = empDeptDetails.getDept().getDeptId();
		}
	}

}
