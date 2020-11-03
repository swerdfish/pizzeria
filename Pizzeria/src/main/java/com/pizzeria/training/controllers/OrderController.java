package com.pizzeria.training.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@GetMapping("/getAll")
	public @ResponseBody String getAllOrders(){
		
		return "Endpoint works";
	}
}
