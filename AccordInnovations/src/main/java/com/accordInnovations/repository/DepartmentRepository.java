package com.accordInnovations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accordInnovations.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}