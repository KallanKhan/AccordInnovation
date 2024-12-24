package com.accordInnovations.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.accordInnovations.entity.Department;
import com.accordInnovations.repository.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	// fetching all departments with pagination
	public Page<Department> getAllDepartments(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return departmentRepository.findAll(pageable);
	}

	// fetching department by id
	public Optional<Department> getDepartment(int id) {
		return departmentRepository.findById(id);
	}

	// inserting department
	public void addDepartment(Department d) {
		departmentRepository.save(d);
	}

	// updating department by id
	public void updateDepartment(Department dep, int id) {
		if (id == dep.getDepartment_ID()) {
			departmentRepository.save(dep);
		}
	}

	// deleting all departments
	public void deleteAllDepartments() {
		departmentRepository.deleteAll();
	}

	// deleting department by id
	public void deleteDepartmentByID(int id) {
		departmentRepository.deleteById(id);
	}

	// patching/updating department by id
	public void patchDepartment(Department dep, int id) {
		if (id == dep.getDepartment_ID()) {
			departmentRepository.save(dep);
		}
	}

	public Page<Department> findAll(Pageable ofSize) {
		// TODO Auto-generated method stub
		return departmentRepository.findAll(ofSize);
	}
}