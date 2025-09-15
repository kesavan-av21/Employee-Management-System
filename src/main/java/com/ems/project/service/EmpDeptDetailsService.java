package com.ems.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.project.dto.EmpDeptDetailsDto;
import com.ems.project.entity.Dept;
import com.ems.project.entity.EmpDeptDetails;
import com.ems.project.entity.Employee;
import com.ems.project.exception.ResourceNotFoundException;
import com.ems.project.repository.DeptRepository;
import com.ems.project.repository.EmpDeptDetailsRepository;
import com.ems.project.repository.EmployeeRepository;

@Service
public class EmpDeptDetailsService {

	private final EmployeeRepository employeeRepository;

	private final DeptRepository deptRepository;

	private final EmpDeptDetailsRepository empDeptDetailsRepository;

	public EmpDeptDetailsService(EmployeeRepository employeeRepository, DeptRepository deptRepository,
			EmpDeptDetailsRepository empDeptDetailsRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.deptRepository = deptRepository;
		this.empDeptDetailsRepository = empDeptDetailsRepository;
	}

	public EmpDeptDetailsDto addEmpDeptDetails(EmpDeptDetailsDto dto) {

		Employee emp = employeeRepository.findById(dto.getEmpNo()).orElseThrow(
				() -> new ResourceNotFoundException("No employee present in this EmpNo : " + dto.getEmpNo()));

		EmpDeptDetails empDeptDetails = new EmpDeptDetails();

		empDeptDetails.setEmployee(emp);

		Dept dept = deptRepository.findById(dto.getDeptNo()).orElseThrow(
				() -> new ResourceNotFoundException("No Department present in this deptNo : " + dto.getEmpNo()));

		empDeptDetails.setDept(dept);

		EmpDeptDetails updatEmpDeptDetails = empDeptDetailsRepository.save(empDeptDetails);

		return new EmpDeptDetailsDto(updatEmpDeptDetails);
	}

	public List<EmpDeptDetailsDto> getDeptDetails(long empNo) {

		List<EmpDeptDetails> details = empDeptDetailsRepository.findByEmployeeEmpNo(empNo);

		if (details.isEmpty())
			throw new ResourceNotFoundException("No employee present with this employee number");

		return details.stream().map(EmpDeptDetailsDto::new).toList();
	}

	@Transactional
	public void deleteDeptDetails(long empNo, long deptId) {

		empDeptDetailsRepository.findByEmployee_EmpNoAndDept_DeptId(empNo, deptId).orElseThrow(
				() -> new ResourceNotFoundException("No mapping found for empNo " + empNo + " and deptNo " + deptId));
		int deleted = empDeptDetailsRepository.deleteByEmployee_EmpNoAndDept_DeptId(empNo, deptId);
		if (deleted == 0) {
			throw new RuntimeException("Failed to delete mapping for empNo " + empNo + " and deptNo " + deptId);
		}
	}

	public List<EmpDeptDetailsDto> getAllMapping() {

		List<EmpDeptDetails> details = empDeptDetailsRepository.findAll();

		if (details.isEmpty())
			throw new ResourceNotFoundException("No employee departments details found");

		return details.stream().map(EmpDeptDetailsDto::new).toList();
	}

	public List<EmpDeptDetailsDto> getEmployeesByDept(long deptId) {

		List<EmpDeptDetails> details = empDeptDetailsRepository.findByDeptDeptId(deptId);

		if (details.isEmpty())
			throw new ResourceNotFoundException("No employee present in this department id");

		return details.stream().map(EmpDeptDetailsDto::new).toList();
	}

}
