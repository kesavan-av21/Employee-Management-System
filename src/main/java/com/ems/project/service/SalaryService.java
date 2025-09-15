package com.ems.project.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.project.dto.SalaryDto;
import com.ems.project.entity.Salary;
import com.ems.project.exception.ResourceNotFoundException;
import com.ems.project.entity.Employee;
import com.ems.project.repository.EmployeeRepository;
import com.ems.project.repository.SalaryRepository;

@Service
public class SalaryService {
	
    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;

    public SalaryService(SalaryRepository salaryRepository, EmployeeRepository employeeRepository) {
        this.salaryRepository = salaryRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public ResponseEntity<SalaryDto> addEmpSalaryUsingEmpNo(Long empNo, SalaryDto salDto) {
        Employee emp = employeeRepository.findById(empNo)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found"));

        Salary sal = salaryRepository.findByEmployeeEmpNo(empNo).orElse(new Salary());
        sal.setEmployee(emp);
        sal.setSalary(salDto.getSalary());

        Salary savedSalary = salaryRepository.save(sal);
        return ResponseEntity.ok(new SalaryDto(savedSalary));
    }

    public ResponseEntity<List<SalaryDto>> findEmpDetailsSalGreaterthan(double salary) {
        List<Salary> salaries = salaryRepository.findBySalaryGreaterThan(salary);

        if (salaries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(salaries.stream().map(sal -> {
            SalaryDto dto = new SalaryDto();
            dto.setId(sal.getId());
            dto.setSalary(sal.getSalary());
            dto.setEmpNo(sal.getEmployee().getEmpNo());
            return dto;
        }).toList());
    }

    public ResponseEntity<Integer> SalaryCountGreaterThan(double salary) {
        int count = salaryRepository.SalaryCountGreaterThan(salary);
        if (count > 0) return ResponseEntity.ok(count);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Transactional
    public SalaryDto updateSalary(long empNo, SalaryDto dto) {
        Salary salary = salaryRepository.findByEmployeeEmpNo(empNo)
                .orElseThrow(() -> new ResourceNotFoundException("Salary details not present with this employee number"));
        salary.setSalary(dto.getSalary());

        Salary updatedSalary = salaryRepository.save(salary);
        return new SalaryDto(updatedSalary);
    }

    public SalaryDto getEmpSalaryUsingEmpNo(long empNo) {
        Salary sal = salaryRepository.findByEmployeeEmpNo(empNo)
                .orElseThrow(() -> new ResourceNotFoundException("Salary detail not present with this employee number"));
        return new SalaryDto(sal);
    }

    
    public List<SalaryDto> getAllSalary() {
        return salaryRepository.findAll().stream().map(SalaryDto::new).toList();
    }

    @Transactional
    public void deleteEmpSalaryUsingEmpNo(long empNo) {
        Salary sal = salaryRepository.findByEmployeeEmpNo(empNo)
                .orElseThrow(() -> new ResourceNotFoundException("Salary detail not present with this employee number"));

        Employee emp = sal.getEmployee();
        emp.setSalary(null); // breaks link if bidirectional

        salaryRepository.delete(sal);
    }
}
