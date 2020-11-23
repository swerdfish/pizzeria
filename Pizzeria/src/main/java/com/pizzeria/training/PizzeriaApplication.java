package com.pizzeria.training;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pizzeria.training.models.Customer;
import com.pizzeria.training.models.Order;
import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.models.PizzaType;
import com.pizzeria.training.models.PizzaSize;
import com.pizzeria.training.models.Toppings;
import com.pizzeria.training.repository.CustomerRepository;
import com.pizzeria.training.repository.OrdersRepository;

@SpringBootApplication
public class PizzeriaApplication implements CommandLineRunner{
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrdersRepository	orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(PizzeriaApplication.class, args);
	}
	
	public void run(String... args) throws Exception{
		
//		Pizza p1 = new Pizza(11f,PizzaType.THIN_CRUST,Arrays.asList(Toppings.ANCHOVY,Toppings.BASIL),6.0, Size.PERSONAL);
//		Pizza p2 = new Pizza(13f,PizzaType.DEEP_DISH,Arrays.asList(Toppings.CHEESE,Toppings.BACON),8.0, Size.SMALL);
//		Pizza p3 = new Pizza(15f,PizzaType.SICILIAN,Arrays.asList(Toppings.BROCCOLI,Toppings.PINEAPPLE),10.0, Size.MEDIUM);
//		Pizza p4 = new Pizza(16f,PizzaType.STUFFED,Arrays.asList(Toppings.TOMATO,Toppings.CHEESE,Toppings.CHICKEN),11.0, Size.LARGE);
//		Pizza p5 = new Pizza(20f,PizzaType.CLASSIC,Arrays.asList(Toppings.BACON),9.0, Size.XLARGE);
//		
//		Customer c1 = new Customer(909877812, "Jeff@pizza.com", new Customer.HomeAddress("123 West st", " ", "Arlington", "Texas", "4063"), new Customer.PaymentCard(1234445, "11/22", (short)182) );
//		Customer c2 = new Customer(412312422, "Bezo@pizza.com", new Customer.HomeAddress("143 East st", "22 Santa Anna ", "San Francisco", "California", "9484"), new Customer.PaymentCard(1241242, "02/25", (short)231) );
//		Customer c3 = new Customer(235234115, "Juan@pizza.com", new Customer.HomeAddress("153 North st", " ", "Windy", "Chicago", "8912"), new Customer.PaymentCard(5674423, "12/21", (short)323) );
//		Customer c4 = new Customer(676786456, "Aaron@pizza.com", new Customer.HomeAddress("113 South st", "12 Sample ", "Arlington", "Texas", "14123"), new Customer.PaymentCard(78945345, "10/27", (short)557) );
//		Customer c5 = new Customer(456734533, "Stephanie@pizza.com", new Customer.HomeAddress("223 Alley st", " ", "Arlington", "Texas", "32134"), new Customer.PaymentCard(6743432, "01/21", (short)783) );
//		customerRepository.save(c1);
//		customerRepository.save(c2);
//		customerRepository.save(c3);
//		customerRepository.save(c4);
//		customerRepository.save(c5);
//		
//		Order o1 = new Order(Arrays.asList(p1),"example1", 12.0, 2.0, false);
//		Order o2 = new Order(Arrays.asList(p2),"example2", 15.0, 2.0, true);
//		Order o3 = new Order(Arrays.asList(p3),"example3", 18.5, 2.0, false);
//		Order o4 = new Order(Arrays.asList(p4),"example4", 20.0, 2.0, true);
//		Order o5 = new Order(Arrays.asList(p5),"example5", 22.0, 2.0, false);
//		
//		orderRepository.save(o1);
//		orderRepository.save(o2);
//		orderRepository.save(o3);
//		orderRepository.save(o4);
//		orderRepository.save(o5);
	}
	
	
}
