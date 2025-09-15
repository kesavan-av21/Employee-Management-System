package com.ems.project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "emp")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_no")
	private Long empNo;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer age;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "phone_no", nullable = false, unique = true)
	private Long phoneNo;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addressMappings;

	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private Salary salary;

	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Job job;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmpDeptDetails> empDeptDetails;
}
