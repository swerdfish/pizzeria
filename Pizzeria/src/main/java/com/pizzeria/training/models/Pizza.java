package com.pizzeria.training.models;

import java.util.Set;

import org.bson.types.ObjectId;

/**
 * Pizza class for use by Pizzeria restaurant
 * Covers details about size, type, toppings, price, etc.
 */
public class Pizza {
	
	private ObjectId pizzaId;
	/** Enum specifying style of pizza crust */
	private PizzaType type;
	/** Set of topping Enums on the pizza */
	private Set<Toppings> toppings;
	/** price of the pizza */
	private Double cost;
	/** Enum specifying a diameter for the pizza */
	private PizzaSize size;
	
	/** No argument constructor */
	public Pizza() {}
	
	/**
	 * Parameterized constructor
	 * @param type Style of pizza crust
	 * @param toppings Toppings to put on the pizza
	 * @param cost Price of the pizza
	 * @param size Diameter of the pizza
	 */
	public Pizza(PizzaType type, Set<Toppings> toppings, Double cost, PizzaSize size) {
		super();
		this.pizzaId = new ObjectId();
		this.type = type;
		this.toppings = toppings;
		this.cost = cost;
		this.size = size;
	}

	/**
	 * Return mongoDB document Id
	 * @return object ID
	 */
	public ObjectId get_id() {
		return pizzaId;
	}
	/**
	 * Update mongoDB document Id
	 * @param _id new object Id
	 */
	public void set_id(ObjectId _id) {
		this.pizzaId = _id;
	}

	/**
	 * Return pizza's crust type
	 * @return type Enum
	 */
	public PizzaType getType() {
		return type;
	}
	/**
	 * Update pizza's crust type
	 * @param type new desired type
	 */
	public void setType(PizzaType type) {
		this.type = type;
	}

	/**
	 * Return a set of toppings on the pizza
	 * @return Current toppings
	 */
	public Set<Toppings> getToppings() {
		return toppings;
	}
	/**
	 * Update toppings on the pizza
	 * @param toppings new set of toppings 
	 */
	public void setToppings(Set<Toppings> toppings) {
		this.toppings = toppings;
	}
	/**
	 * Return the pizza's price
	 * @return Current price
	 */
	public Double getCost() {
		return cost;
	}
	/**
	 * Update the pizza's price
	 * @param cost New price 
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}
	/**
	 * Return the pizza's diameter
	 * @return diameter 
	 */
	public PizzaSize getSize() {
		return size;
	}
	/**
	 * Update the pizza's diameter
	 * @param size new desired diameter
	 */
	public void setSize(PizzaSize size) {
		this.size = size;
	}
	/**
	 * hashCode override method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((pizzaId == null) ? 0 : pizzaId.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((toppings == null) ? 0 : toppings.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	/**
	 * equals override method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (pizzaId == null) {
			if (other.pizzaId != null)
				return false;
		} else if (!pizzaId.equals(other.pizzaId))
			return false;
		if (size != other.size)
			return false;
		if (toppings == null) {
			if (other.toppings != null)
				return false;
		} else if (!toppings.equals(other.toppings))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	/**
	 * Determine price of the pizza based on size, toppings and type
	 * @deprecated Use PriceCalculator class instead
	 * @return Total price calculated
	 */
	@Deprecated
	public double calculateCost() {
		double totalPrice = 0.0d;
		double sizePrice = 0d;
		double typePrice = 0;
		double topPrice = this.toppings.size() * 0.5;
		switch(this.type) {
			case DEEP_DISH:
				typePrice = 5;
				break;
			case THIN_CRUST:
			case SICILIAN:
			case STUFFED:
				typePrice = 2;
				break;
			case GLUTEN_FREE:
				typePrice = 3;
				break;
			default:
				break;
		}
		switch(this.size) {
			case SLICE:
				sizePrice = 2.0;
				break;
			case PERSONAL:
				sizePrice = 6.0;
			case SMALL:
				sizePrice = 12.0;
				break;
			case MEDIUM:
				sizePrice = 15.0;
				break;
			case LARGE:
				sizePrice = 18.0;
				break;
			case XLARGE:
				sizePrice = 22.0;
				break;
		}
		totalPrice = typePrice + sizePrice + topPrice;
		return totalPrice;
	}
	/**
	 * toString override method
	 */
	@Override
	public String toString() {
		return "Pizza [pizzaId=" + pizzaId + ", type=" + type + ", toppings=" + toppings + ", cost=" + cost + ", size="
				+ size + "]";
	}
	
}
