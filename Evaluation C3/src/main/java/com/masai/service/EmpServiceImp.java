package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.DepartmentNotExist;
import com.masai.Exception.EmployeeNotExist;
import com.masai.modal.Department;
import com.masai.modal.Employee;
import com.masai.repo.DeptRepo;
import com.masai.repo.EmpRepo;

@Service
public class EmpServiceImp implements EmpServiceIntro {

	@Autowired
	EmpRepo empRepo;

	@Autowired
	DeptRepo deptRepo;
	
	
	
	@Override
	public Department addDepartment(Department dept) {
		
		
		return deptRepo.save(dept);

	}
	

	@Override
	public List<Employee> getAllEmployee() {

		return empRepo.findAll();
	}

	@Override
	public Employee addEmployee(Employee emp, Integer deptId) {

//		System.out.println("11111");
		
		Optional<Department> opt = deptRepo.findById(deptId);

		if (!opt.isPresent()) {

			throw new DepartmentNotExist("department not fund by id " + deptId);
		}

		Department dept = opt.get();
		
//		System.out.println("2222");
		
		emp.setDept(dept);
//		System.out.println("33333");
//		dept.getEmployees().add(emp);
		
//		System.out.println("44444");
//		deptRepo.save(dept);
		
//		System.out.println("555555");
		return empRepo.save(emp);
	}

	@Override
	public Employee updateEmployee(Employee emp) {

		Optional<Employee> opt = empRepo.findById(emp.getEmpId());

		if (!opt.isPresent()) {
			throw new EmployeeNotExist("Employee does not exist by id " + emp.getEmpId());
		}

		Employee empFind = opt.get();

		empFind.setEmpName(emp.getEmpName());

		empRepo.save(empFind);

		return emp;
	}

	@Override
	public Employee deletEmployee(Employee emp) {

		Optional<Employee> opt = empRepo.findById(emp.getEmpId());

		if (!opt.isPresent()) {
			throw new EmployeeNotExist("Employee does not exist by id " + emp.getEmpId());
		}

//		empRepo.delete(opt.get());;
		
//		Optional<Department> depOpt=deptRepo.findById(opt.get().getDept().getDeptId());
//		
//		Department dept= depOpt.get();
//		
//		dept.getEmployees().remove(opt.get());
//		
//		deptRepo.save(dept);
//		
		empRepo.deleteById(emp.getEmpId());
		
		return opt.get();
	}

	@Override
	public List<Object[]> getEmployeesByDepartmentNmae(String deptName,Integer depid) {
		
		List<Object[]> dep=deptRepo.getDepByName(deptName,depid);
		return dep;
	}



}
