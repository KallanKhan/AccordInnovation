package com.accordInnovations.service;


import java.util.Optional;
import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accordInnovations.controller.EmployeeController;
import com.accordInnovations.entity.Department;
import com.accordInnovations.entity.Employee;
import com.accordInnovations.repository.EmployeeRepository;

// employee service class
@Service
public class EmployeeService {


	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private EmployeeRepository employeeRepository;

// fetching all employees with pagination
	public Page<Employee> getAllEmployees(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return employeeRepository.findAll(pageable);
	}

	
// fetching employee by id
	public Optional<Employee> getEmployee(int id) {
		return employeeRepository.findById(id);
	}

// fetching employee with department by id
	public Optional<Employee> getEmployeeWithDepartment(int id) {
		return employeeRepository.findEmployeeWithDepartmentById(id);
	}

// inserting employee
	public void addEmployee(Employee e) {
		employeeRepository.save(e);
	}

// updating employee by id
	public void updateEmployee(Employee emp, int id) {
		if (id == emp.getEmployeeID()) {
			employeeRepository.save(emp);
		}
	}

// deleting all employees
	public void deleteAllEmployees() {
		employeeRepository.deleteAll();
	}

// deleting employee by id
	public void deleteEmployeeByID(int id) {
		employeeRepository.deleteById(id);
	}

// patching/updating employee by id
	public void patchEmployee(Employee emp, int id) {
		if (id == emp.getEmployeeID()) {
			employeeRepository.save(emp);
		}
	}

	public String callGoogle() {
		
		return restTemplate.getForObject("https://www.google.com", String.class);
	}


	public Page<Employee> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return employeeRepository.findAll(pageable);
	}}
