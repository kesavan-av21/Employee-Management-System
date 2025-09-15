package com.ems.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private double salary;

	@OneToOne
	@JoinColumn(name = "emp_no")
	private Employee employee;
}
