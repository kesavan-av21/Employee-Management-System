package com.ems.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.project.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

	Optional<Job> findByEmployeeEmpNo(Long empNo);

}
