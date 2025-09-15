package com.ems.project.dto;

import com.ems.project.entity.Job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {

	private long id;

	private long empNo;

	private String job;

	public JobDto(Job jobName) {
		this.id = jobName.getId();
		this.empNo = jobName.getEmployee().getEmpNo();
		this.job = jobName.getJob();
	}
}
