package com.accordInnovations.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accordInnovations.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.id = :id")
	Optional<Employee> findEmployeeWithDepartmentById(@Param("id") int id);
}