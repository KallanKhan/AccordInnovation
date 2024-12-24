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

import com.accordInnovations.entity.Department;
import com.accordInnovations.service.DepartmentService;


@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
	
	static final Logger logger  = LogManager.getLogger(DepartmentController.class.getName());

	@Autowired
	private DepartmentService departmentService;

	// displaying list of all department
	@GetMapping("/departments")
	public ResponseEntity<Page<Department>> getAllDepartments(Pageable pageable) {
        // Paginated response
        Page<Department> departmentPage = departmentService.findAll(pageable);
        return ResponseEntity.ok(departmentPage);
    }

	// displaying department by id
	@GetMapping("/departments/{id}")
	public ResponseEntity<Optional<Department>> getDepartment(@PathVariable int id) {
	    Optional<Department> department = departmentService.getDepartment(id);
	    return department.isPresent()
	            ? new ResponseEntity<>(department, HttpStatus.OK)
	            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// inserting department
	@PostMapping("/departments")
	public ResponseEntity<String> addDepartment(@RequestBody Department department) {
	    departmentService.addDepartment(department);
	    return new ResponseEntity<>("Department added successfully", HttpStatus.CREATED);
	}

	// updating department by id
	@PutMapping("/departments/{id}")
	public ResponseEntity<String> updateDepartment(@RequestBody Department d, @PathVariable int id) {
	    departmentService.updateDepartment(d, id);
	    return new ResponseEntity<>("Department updated successfully", HttpStatus.OK);
	}

	// deleting department by id
	@DeleteMapping("departments/{id}")
	public ResponseEntity<String> deleteDepartmentByID(@PathVariable int id) {
	    departmentService.deleteDepartmentByID(id);
	    return new ResponseEntity<>("Department deleted successfully", HttpStatus.OK);
	}

	// updating/patching department by id
	@PatchMapping("departments/{id}")
	public ResponseEntity<String> patchDepartmentByID(@RequestBody Department d, @PathVariable int id) {
	    departmentService.patchDepartment(d, id);
	    return new ResponseEntity<>("Department patched successfully", HttpStatus.OK);
	}
}