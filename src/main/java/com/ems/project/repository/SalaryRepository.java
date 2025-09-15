package com.ems.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ems.project.entity.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

	Optional<Salary> findByEmployeeEmpNo(Long empNo);

	List<Salary> findBySalaryGreaterThan(double salary);

	@Query(value = "select count(*) from salary where salary>=:amount", nativeQuery = true)
	int SalaryCountGreaterThan(@Param("amount") double salary);

	@Modifying
	@Transactional
	int deleteByEmployeeEmpNo(long empNo);

}
