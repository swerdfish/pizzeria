package com.pizzeria.training;

import java.util.Arrays;
import java.util.HashSet;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pizzeria.training.models.Address;
import com.pizzeria.training.models.Customer;
import com.pizzeria.training.models.Order;
import com.pizzeria.training.models.OrderStatus;
import com.pizzeria.training.models.OrderType;
import com.pizzeria.training.models.Pizza;
import com.pizzeria.training.models.PizzaSize;
import com.pizzeria.training.models.PizzaType;
import com.pizzeria.training.models.Toppings;
import com.pizzeria.training.repository.CustomerRepository;
import com.pizzeria.training.repository.OrdersRepository;

@SpringBootApplication
public class PizzeriaApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrdersRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(PizzeriaApplication.class, args);
	}

	public void run(String... args) throws Exception {
		fillDatabase();
	}

	private void fillDatabase() {
		if (orderRepository.findAll().isEmpty() || customerRepository.findAll().isEmpty()) {
			orderRepository.deleteAll();
			customerRepository.deleteAll();

			Pizza p1 = new Pizza(PizzaType.THIN_CRUST,
					new HashSet<Toppings>(Arrays.asList(Toppings.ANCHOVY, Toppings.BASIL)), 6.0, PizzaSize.PERSONAL);
			Pizza p2 = new Pizza(PizzaType.DEEP_DISH,
					new HashSet<Toppings>(Arrays.asList(Toppings.CHEESE, Toppings.BACON)), 8.0, PizzaSize.SMALL);
			Pizza p3 = new Pizza(PizzaType.SICILIAN,
					new HashSet<Toppings>(Arrays.asList(Toppings.BROCCOLI, Toppings.PINEAPPLE)), 10.0,
					PizzaSize.MEDIUM);
			Pizza p4 = new Pizza(PizzaType.STUFFED,
					new HashSet<Toppings>(Arrays.asList(Toppings.TOMATO, Toppings.CHEESE, Toppings.CHICKEN)), 11.0,
					PizzaSize.LARGE);
			Pizza p5 = new Pizza(PizzaType.CLASSIC, new HashSet<Toppings>(Arrays.asList(Toppings.BACON)), 9.0,
					PizzaSize.XLARGE);

			Address a1 = new Address("123 West st", " ", "Arlington", "Texas", "4063");
			Address a2 = new Address("143 East st", "22 Santa Anna ", "San Francisco", "California", "9484");
			Address a3 = new Address("153 North st", " ", "Windy", "Chicago", "8912");
			Address a4 = new Address("113 South st", "12 Sample ", "Arlington", "Texas", "14123");
			Address a5 = new Address("223 Alley st", " ", "Arlington", "Texas", "32134");
			Customer c1 = new Customer(new ObjectId(), "Jeff@pizza.com", "Jeff password", "Jeff", "Jeffson",
					"+11231231234", a1, new Customer.PaymentCard(1234445L, "11/22", (short) 182, a1),
					Arrays.asList(p1, p2));
			Customer c2 = new Customer(new ObjectId(), "Bezo@pizza.com", "Bezo password", "Bezo", "Jeffs",
					"+11231231235", a2, new Customer.PaymentCard(1241242L, "02/25", (short) 231, a2),
					Arrays.asList(p2, p3));
			Customer c3 = new Customer(new ObjectId(), "Juan@pizza.com", "Juan password", "Juan", "Gomez",
					"+11231231236", a3, new Customer.PaymentCard(5674423L, "12/21", (short) 323, a3),
					Arrays.asList(p3, p4));
			Customer c4 = new Customer(new ObjectId(), "Aaron@pizza.com", "Aaron password", "Aaron", "Rodgers",
					"+1123123123457", a4, new Customer.PaymentCard(78945345L, "10/27", (short) 557, a4),
					Arrays.asList(p4, p5));
			Customer c5 = new Customer(new ObjectId(), "Stephanie@pizza.com", "Stephanie password", "Stephanie",
					"Gruverie", "+11231231238", a5, new Customer.PaymentCard(6743432L, "01/21", (short) 783, a5),
					Arrays.asList(p1, p5));
			customerRepository.save(c1);
			customerRepository.save(c2);
			customerRepository.save(c3);
			customerRepository.save(c4);
			customerRepository.save(c5);

			Order o1 = new Order(c1, null, Arrays.asList(p1, p2), 12.0D, 2.0D, OrderType.CATERING, null);
			o1.setStatus(OrderStatus.PENDING);
			Order o2 = new Order(c2, null, Arrays.asList(p2, p3), 15.0D, 3.0D, OrderType.DELIVERY, a2);
			o2.setStatus(OrderStatus.COOKING);
			Order o3 = new Order(c3, null, Arrays.asList(p3, p4), 18.5D, 4.5D, OrderType.DINE_IN, null);
			o3.setStatus(OrderStatus.READY);
			Order o4 = new Order(c4, null, Arrays.asList(p4, p5), 20.0D, 5.0D, OrderType.PICKUP, null);
			o4.setStatus(OrderStatus.COMPLETED);
			Order o5 = new Order(c5, null, Arrays.asList(p1, p5), 22.0D, 6.5D, OrderType.DELIVERY, a5);
			o5.setStatus(OrderStatus.DELIVERING);
			orderRepository.save(o1);
			orderRepository.save(o2);
			orderRepository.save(o3);
			orderRepository.save(o4);
			orderRepository.save(o5);
		}
	}
}
