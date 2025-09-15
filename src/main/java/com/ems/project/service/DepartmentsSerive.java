package com.ems.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ems.project.dto.*;
import com.ems.project.entity.Dept;
import com.ems.project.exception.DepartmentDetailsAlreadyExistsException;
import com.ems.project.exception.ResourceNotFoundException;
import com.ems.project.repository.DeptRepository;

@Service
public class DepartmentsSerive {

	private final DeptRepository deptRepository;

	public DepartmentsSerive(DeptRepository deptRepository) {
		this.deptRepository = deptRepository;
	}

	public Dept addDepts(DeptDto deptDto) {

		if (deptRepository.findByDeptName(deptDto.getDeptName()).isEmpty()) {
			Dept dept = new Dept();
			dept.setDeptName(deptDto.getDeptName());
			deptRepository.save(dept);
			return dept;
		}

		throw new DepartmentDetailsAlreadyExistsException("Department already exists with this name");
	}

	public Dept updateDepts(DeptUpdateDto deptDto) {
		Dept dept = deptRepository.findByDeptName(deptDto.getOldDeptName())
				.orElseThrow(() -> new ResourceNotFoundException("Department not found: " + deptDto.getOldDeptName()));
		dept.setDeptName(deptDto.getNewDeptName());
		return deptRepository.save(dept);

	}

	public List<DeptDto> findAll() {
		List<Dept> dept = deptRepository.findAll();
		if (dept.isEmpty()) {
			return null;
		}

		return dept.stream().map(DeptDto::new).toList();
	}

	public DeptDto findById(long id) {

		Dept dept = deptRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with this ID"));

		return new DeptDto(dept);
	}

	public void deleteById(long id) {

		Dept dept = deptRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with this ID"));
		if (dept != null)
			deptRepository.deleteById(id);
	}

}
