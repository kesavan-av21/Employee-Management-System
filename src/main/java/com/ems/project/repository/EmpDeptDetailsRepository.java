package com.ems.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ems.project.entity.EmpDeptDetails;

@Repository
public interface EmpDeptDetailsRepository extends JpaRepository<EmpDeptDetails, Long> {

	List<EmpDeptDetails> findByEmployeeEmpNo(long empNo);

	Optional<EmpDeptDetails> findByEmployee_EmpNoAndDept_DeptId(long empNo, long deptNo);

	@Modifying
	@Transactional
	int deleteByEmployee_EmpNoAndDept_DeptId(long empNo, long deptNo);

	List<EmpDeptDetails> findByDeptDeptId(long deptId);

}
