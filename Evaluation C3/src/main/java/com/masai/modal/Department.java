package com.masai.modal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer deptId;
	private String deptName;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "dept")
	List<Employee> employees= new ArrayList<Employee>();
}
