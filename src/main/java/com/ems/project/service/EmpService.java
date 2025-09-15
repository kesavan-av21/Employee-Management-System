package com.ems.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ems.project.dto.EmpDto;
import com.ems.project.dto.EmpMainDetailsDto;
import com.ems.project.entity.Employee;
import com.ems.project.exception.EmployeeDetailsAlreadyExistException;
import com.ems.project.exception.ResourceNotFoundException;
import com.ems.project.repository.EmployeeRepository;

@Service
public class EmpService {

	private final EmployeeRepository employeeRepository;

	public EmpService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	// Adding Emp Details
	public EmpMainDetailsDto saveAll(EmpMainDetailsDto dto) {

		if (employeeRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new EmployeeDetailsAlreadyExistException("Employee details already exist with this EmailID");
		}

		if (employeeRepository.findByPhoneNo(dto.getPhoneNo()).isPresent()) {

			throw new EmployeeDetailsAlreadyExistException("Employee details already exist with this Pno Number");
		}

		Employee emp = new Employee();

		emp.setName(dto.getName());
		emp.setAge(dto.getAge());
		emp.setEmail(dto.getEmail());
		emp.setPhoneNo(dto.getPhoneNo());

		employeeRepository.save(emp);

		return new EmpMainDetailsDto(emp);

	}

	// Getting Emp All details
	public EmpDto getEmpById(long id) {

		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found"));

		return new EmpDto(emp, true);
	}

	// Getting Emp EMpTable details only
	public EmpDto getEmpEmpTableDetails(long id) {

		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found"));

		return new EmpDto(emp);
	}

	// Updating Emp details
	public EmpDto updateEmpById(long id, EmpDto empDto) {

		Employee optEmp = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		if (empDto.getName() != null)
			optEmp.setName(empDto.getName());
		if (empDto.getAge() != null)
			optEmp.setAge(empDto.getAge());
		if (empDto.getEmail() != null)
			optEmp.setEmail(empDto.getEmail());
		if (empDto.getPhoneNo() != null)
			optEmp.setPhoneNo(empDto.getPhoneNo());

		Employee emp = employeeRepository.save(optEmp);

		return new EmpDto(emp);

	}

	public Page<EmpDto> sortingEmp(String field, String direction, int pageNo, int empCount) {

		Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(Direction.ASC, field) : Sort.by(Direction.DESC, field);

		org.springframework.data.domain.Pageable pageable = PageRequest.of(pageNo, empCount, sort);

		Page<Employee> list = employeeRepository.findAll(pageable);

		return list.map(EmpDto::new);

	}

	public List<EmpDto> sortEmpByAgeGreaterThanAgeByDirection(Long age, String direction) {

		Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, "age")
				: Sort.by(Sort.Direction.DESC, "age");

		List<Employee> emp = employeeRepository.findByAgeGreaterThanEqual(age, sort);

		return emp.stream().map(EmpDto::new).toList();
	}

	public ResponseEntity<String> deleteById(Long id) {

		if (employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
			return ResponseEntity.ok("Employee details deleted");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with empNO " + id);
	}

	public Page<EmpDto> findByAgeLessThanEqual(Long age, String direction, int pageNo, int empCount) {
		Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, "age")
				: Sort.by(Sort.Direction.DESC, "age");
		Pageable pageable = PageRequest.of(pageNo, empCount, sort);

		Page<Employee> emp = employeeRepository.findByAgeLessThanEqual(age, pageable);

		return emp.map(EmpDto::new);

	}

	public List<EmpDto> getAllEmpDetails() {
		List<Employee> list = employeeRepository.findAll();

		if (list.isEmpty())
			throw new ResourceNotFoundException("No employee data available");

		return list.stream().map(EmpDto::new).collect(Collectors.toList());
	}

	public void deleteAllEmployees() {

		if (employeeRepository.count() == 0) {
			throw new ResourceNotFoundException("No employees found to delete");
		}
		employeeRepository.deleteAll();
	}

}
