package com.pizzeria.training.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.training.models.Customer;
import com.pizzeria.training.models.Employee;
import com.pizzeria.training.repository.EmployeeRepoistory;

@Service
public class EmployeeService {
	private EmployeeRepoistory er;
	
	public EmployeeService() {
		
	}
	
	@Autowired
	public EmployeeService(EmployeeRepoistory empRepo) {
		this.er = empRepo;
	}
	
	public List<Employee> findAll() {
		return er.findAll();
	}
	
	public Employee save(Employee e) {
		return er.save(e);
	}
	
	public void delete(Employee employee) {
		er.delete(employee);
	}
	
	public Employee getEmployeeBy_id(ObjectId _id) {
		return er.findBy_id(_id);
	}
	
	
}
