package com.accordInnovations.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accordInnovations.entity.Employee;
import com.accordInnovations.service.EmployeeService;



@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	static final Logger logger  = LogManager.getLogger(EmployeeController.class.getName());

	@Autowired
	private EmployeeService employeeService;
	
	// displaying list of all employees with pagination
//	@GetMapping("/employees")
//	public ResponseEntity<List<Employee>> getAllEmployee(
//			@RequestParam(defaultValue = "0") int page, 
//			@RequestParam(defaultValue = "10") int size) {
//		List<Employee> employees = (List<Employee>) employeeService.getAllEmployees(page, size);
//		return new ResponseEntity<>(employees, HttpStatus.OK);
//	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees(Pageable pageable) {
	    Page<Employee> employeePage = employeeService.findAll(pageable);
	    List<Employee> employees = employeePage.getContent(); // Extract the list of employees
	    return ResponseEntity.ok(employees);
	}

	// displaying employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable int id) {
		Optional<Employee> employee = employeeService.getEmployee(id);
		return employee.isPresent() 
				? new ResponseEntity<>(employee, HttpStatus.OK) 
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// inserting employee
	@PostMapping("/employees")
	public ResponseEntity<String> addEmployees(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
		return new ResponseEntity<>("Employee added successfully", HttpStatus.CREATED);
	}

	// updating employee by id
	@PutMapping("/employees/{id}")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee e, @PathVariable int id) {
		employeeService.updateEmployee(e, id);
		return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
	}

	// deleting employee by id
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteEmployeeByID(@PathVariable int id) {
		employeeService.deleteEmployeeByID(id);
		return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
	}

	// updating/patching employee by id
	@PatchMapping("/employees/{id}")
	public ResponseEntity<String> patchEmployeeByID(@RequestBody Employee e, @PathVariable int id) {
		employeeService.patchEmployee(e, id);
		return new ResponseEntity<>("Employee patched successfully", HttpStatus.OK);
	}

	// calling external API
	@GetMapping("/external")
	public ResponseEntity<String> callExternalApi() {
		logger.info("Calling external API");
		String response = employeeService.callGoogle();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
