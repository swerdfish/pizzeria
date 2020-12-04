package com.pizzeria.training.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.training.models.Employee;
import com.pizzeria.training.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employees")
public class EmployeeController {
	private EmployeeService es;
	
	public EmployeeController() {
		
	}
	
	@Autowired
	public EmployeeController(EmployeeService es) {
		super();
		this.es = es;
	}
	
	@PostMapping
	public Employee createEmployee(@RequestBody Employee emp) {
		return es.save(emp);
	}
	
	@GetMapping("all")
	public List<Employee> findAll() {
		return es.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee findById(@PathVariable ObjectId id) {
		return es.getEmployeeBy_id(id);
	}
	
	@PutMapping("/update")
	public Employee updateEmployee(@RequestBody Employee emp) {
		return es.save(emp);
	}
	
	@DeleteMapping("/delete/{id}")
	public Employee deleteById(@PathVariable ObjectId id) {
		Employee emp = es.getEmployeeBy_id(id);
		es.delete(emp);
		return emp;
		
	}

}
