package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.modal.Department;
import com.masai.modal.Employee;
import com.masai.service.EmpServiceImp;

@RestController
public class EmployeeController {

	@Autowired
	EmpServiceImp empServiceImp;
	
	
	@PostMapping("/department")
	public Department addDepartment(@RequestBody Department dept) {
		
		return empServiceImp.addDepartment(dept);
	}
	
	
	@GetMapping("/employees")
	public List<Employee> getAllEmp(){
		
		return empServiceImp.getAllEmployee();
	}
	
	
	@PostMapping("/employee/{deptId}")
	public Employee addEmployee(@RequestBody Employee emp, @PathVariable Integer deptId) {
		
		return empServiceImp.addEmployee(emp, deptId);
	}
	
	@PutMapping("employee")
	public Employee updateEmployee(@RequestBody Employee emp) {
		
		return empServiceImp.updateEmployee(emp);
	}
	
	
	@DeleteMapping("/employee")
	public Employee deletEmployee(@RequestBody Employee emp) {
		
		return empServiceImp.deletEmployee(emp);
	}
	
	
	@GetMapping("/employees/{deptName}/{depId}")
	public List<Object[]> getEmployeesByDepartmentNmae(@PathVariable String deptName,@PathVariable Integer depId){
		
		for(Object[] obj:empServiceImp.getEmployeesByDepartmentNmae(deptName,depId)) {
			
			System.out.println(obj[0]);
			Integer num=(Integer)obj[1];
			System.out.println(num+100);
		}
		return empServiceImp.getEmployeesByDepartmentNmae(deptName,depId);
	}
	
}
