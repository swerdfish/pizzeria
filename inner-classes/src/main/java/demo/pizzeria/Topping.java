package demo.pizzeria;

public enum Topping {
	
	BACON("bacon", true),
	BEEF("beef", true),
	CHICKEN("chicken", true),
	HAM("ham", true),
	PEPPERONI("pepperoni", true),
	SALAMI("salami", true),
	SAUSAGE("sausage", true),
	STEAK("steak", true),
	
	BANANA_PEPPERS("banana peppers", false),
	BLACK_OLIVES("black olives", false),
	GREEN_OLIVES("green olives", false),
	GREEN_PEPPERS("green peppers", false),
	MUSHROOMS("mushrooms", false),
	ONIONS("onions", false),
	PINEAPPLE("pineapple", false),
	SPINACH("spinach", false),
	TOMATOES("tomatoes", false);
	
	private final boolean meat;
	private final String label;
	
	private Topping(String label, boolean meat) {
		this.label = label;
		this.meat = meat;
	}

	public static Topping strToTopping(String tps) {
		switch(tps.toLowerCase()) {
		case "bac":
		case "bacon":
			return Topping.BACON;
		case "beef":
		case "ground beef":
			return Topping.BEEF;
		case "chicken":
			return Topping.CHICKEN;
		case "ham":
			return Topping.HAM;
		case "pep":
		case "pepperoni":
			return Topping.PEPPERONI;
		case "salami":
			return Topping.SALAMI;
		case "sausage":
		case "italian sausage":
			return Topping.SAUSAGE;
		case "steak":
		case "philly steak":
			return Topping.STEAK;
		case "banana pepper":
		case "banana peppers":
		case "yellow pepper":
		case "yellow peppers":
			return Topping.BANANA_PEPPERS;
		case "olive":
		case "olives":
		case "black olive":
		case "black olives":
		case "kalamata olive":
		case "kalamata olives":
			return Topping.BLACK_OLIVES;
		case "green olive":
		case "green olives":
			return Topping.GREEN_OLIVES;
		case "pepper":
		case "peppers":
		case "green pepper":
		case "green peppers":
			return Topping.GREEN_PEPPERS;
		case "mushroom":
		case "mushrooms":
			return Topping.MUSHROOMS;
		case "onion":
		case "onions":
			return Topping.ONIONS;
		case "pineapple":
			return Topping.PINEAPPLE;
		case "spinach":
			return Topping.SPINACH;
		case "tomato":
		case "tomatoes":
			return Topping.TOMATOES;
		default:
			throw new IllegalArgumentException("Input \""+tps+"\" does not correspond to a topping");
		}
	}

	public boolean isMeat() {
		return meat;
	}

	public String getLabel() {
		return label;
	}

}
