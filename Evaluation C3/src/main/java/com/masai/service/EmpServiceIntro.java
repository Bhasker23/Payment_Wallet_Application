package com.masai.service;

import java.util.List;

import com.masai.modal.Department;
import com.masai.modal.Employee;

public interface EmpServiceIntro {

	public Department addDepartment(Department dept);
	
	public List<Employee> getAllEmployee();
	
	public Employee addEmployee(Employee emp, Integer deptId);
	
	public Employee updateEmployee(Employee emp);
	
	public Employee deletEmployee(Employee emp);
	
	public List<Object[]> getEmployeesByDepartmentNmae(String deptName,Integer depid);
}
