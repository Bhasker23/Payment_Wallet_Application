package com.masai.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.modal.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer> {

	public List<Employee> findByEmpNameAndEmpId(String empName,Integer empId);
}
