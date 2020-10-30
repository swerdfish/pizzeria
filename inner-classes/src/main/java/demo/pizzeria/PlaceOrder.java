package demo.pizzeria;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlaceOrder {
	
	public static void main(String[] args) {
		
		List<Pizza.Classic> classicPizzas = Arrays.asList(new Pizza.Classic(), new Pizza.Classic("pepperoni"), new Pizza.Classic("ham, pineapple"));
		List<Pizza.ThinCrust> thinCrustPizzas = Arrays.asList(new Pizza.ThinCrust("bacon"), new Pizza.ThinCrust("spinach, tomatoes"), new Pizza.ThinCrust("green olives"));
		List<Pizza.DeepDish> deepDishPizzas = Arrays.asList(new Pizza.DeepDish("sausage"), new Pizza.DeepDish("onions, olives, mushrooms, green peppers, spinach"));
		
		List<Pizza> pizzas = Stream.of(classicPizzas, thinCrustPizzas, deepDishPizzas).flatMap(Collection::stream).collect(Collectors.toList());
		
		System.out.println("Placing an order for the following pizzas:\n");
		for (Pizza pizza : pizzas) {
			System.out.println(pizza);
			System.out.println("Construction: "+Arrays.toString(pizza.getConstruction()));
		}
		
	}

}
