package com.masai.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masai.modal.Department;

@Repository
public interface DeptRepo extends JpaRepository<Department, Integer> {

	@Query("select d.deptName,d.deptId from Department d where d.deptName=:dn and d.deptId=:di")
	public List<Object[]> getDepByName(@Param("dn") String depName,@Param("di") Integer depid);
	
}
