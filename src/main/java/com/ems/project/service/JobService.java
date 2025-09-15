package com.ems.project.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ems.project.dto.JobDto;
import com.ems.project.entity.Employee;
import com.ems.project.entity.Job;
import com.ems.project.exception.ResourceNotFoundException;
import com.ems.project.repository.EmployeeRepository;
import com.ems.project.repository.JobRepository;

@Service
public class JobService {

	private final EmployeeRepository employeeRepository;

	private final JobRepository jobRepository;

	public JobService(EmployeeRepository employeeRepository, JobRepository jobRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.jobRepository = jobRepository;
	}

	public ResponseEntity<JobDto> addJobDetails(JobDto jobDto) {

		Job job = new Job();

		job.setJob(jobDto.getJob());

		Employee employee = employeeRepository.findById(jobDto.getEmpNo()).orElseThrow(
				() -> new ResourceNotFoundException("No Employee found in this empNo " + jobDto.getEmpNo()));

		job.setEmployee(employee);

		Job savedJob = jobRepository.save(job);
		JobDto jDto = new JobDto();
		jDto.setId(savedJob.getId());
		jDto.setEmpNo(savedJob.getEmployee().getEmpNo());
		jDto.setJob(savedJob.getJob());
		return ResponseEntity.ok(jDto);
	}

	public void deleteAll() {

		jobRepository.deleteAll();

	}

	public ResponseEntity<JobDto> getEmpJobDetailsUsingEmpNo(Long empNo) {

		Job job = jobRepository.findByEmployeeEmpNo(empNo)
				.orElseThrow(() -> new ResourceNotFoundException("No Employee Details found"));
		return ResponseEntity.ok(new JobDto(job));
	}

	public List<JobDto> getAllJobs() {

		List<Job> jobs = jobRepository.findAll();

		if (jobs.isEmpty()) {

			throw new ResourceNotFoundException("No job details present in this table");
		}

		return jobs.stream().map(JobDto::new).toList();
	}

	public void deleteJobByEmpNo(long empNo) {

		Job job = jobRepository.findByEmployeeEmpNo(empNo)
				.orElseThrow(() -> new ResourceNotFoundException("No job present with this employee number"));

		Employee emp = job.getEmployee();
		emp.setJob(null);

		jobRepository.delete(job);
	}

}
