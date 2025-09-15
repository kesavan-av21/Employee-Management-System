package com.ems.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ems.project.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	List<Address> findByEmployeeEmpNo(Long empNo);

	List<Address> findByAddressType(String addressType);

	Page<Address> findByAddressTypeIgnoreCase(String field, Pageable pageable);

	Optional<Address> findByEmployee_EmpNoAndAddressType(long empNo, String addressType);

	List<Address> findByEmployeeEmpNo(long empNo);

	Address findByEmployeeEmpNoAndAddressType(long empNo, String addressType);

	@Query(value = "SELECT * FROM address WHERE emp_no IN (SELECT emp_no "
			+ "FROM address GROUP BY emp_no HAVING COUNT(*) >= :count)", nativeQuery = true)
	List<Address> findByEmployeeWithAddressCount(@Param("count") int count);

}
