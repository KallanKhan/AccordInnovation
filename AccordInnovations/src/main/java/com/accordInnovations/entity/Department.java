package com.accordInnovations.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

	@Id
	@Column(name="department_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int department_ID;
	private String short_Name;
	private String department_Name;
}