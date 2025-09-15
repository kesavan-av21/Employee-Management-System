package com.ems.project.dto;

import com.ems.project.entity.Dept;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptDto {

	private Long id;

	private String deptName;

	public DeptDto(Dept dept) {
		this.id = dept.getDeptId();
		this.deptName = dept.getDeptName();
	}

}
