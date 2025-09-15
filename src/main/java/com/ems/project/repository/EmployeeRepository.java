package com.ems.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ems.project.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);

	Optional<Employee> findByPhoneNo(Long phoneNo);

	List<Employee> findByAgeGreaterThanEqual(Long age, Sort sort);

	Page<Employee> findByAgeLessThanEqual(Long age, Pageable pageable);

}
