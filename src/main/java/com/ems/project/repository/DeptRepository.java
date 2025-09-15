package com.ems.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.project.entity.Dept;

import jakarta.transaction.Transactional;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Long> {

	@Transactional
	Optional<Dept> findByDeptName(String deptName);

}
