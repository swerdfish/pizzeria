package com.pizzeria.training.models;

import java.util.Set;

import org.bson.types.ObjectId;

public class Pizza {
	
	private ObjectId pizzaId;
	private PizzaType type;
	private Set<Toppings> toppings;
	private Double cost;
	private PizzaSize size;
	
	public Pizza() {}
	
	public Pizza(PizzaType type, Set<Toppings> toppings, Double cost, PizzaSize size) {
		super();
		this.pizzaId = new ObjectId();
		this.type = type;
		this.toppings = toppings;
		this.cost = cost;
		this.size = size;
	}
	
	public ObjectId getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(ObjectId pizzaId) {
		this.pizzaId = pizzaId;
	}

	public PizzaType getType() {
		return type;
	}
	public void setType(PizzaType type) {
		this.type = type;
	}
	public Set<Toppings> getToppings() {
		return toppings;
	}
	public void setToppings(Set<Toppings> toppings) {
		this.toppings = toppings;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public PizzaSize getSize() {
		return size;
	}
	public void setSize(PizzaSize size) {
		this.size = size;
	}
	
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

	@Override
	public String toString() {
		return "Pizza [pizzaId=" + pizzaId + ", type=" + type + ", toppings=" + toppings + ", cost=" + cost + ", size="
				+ size + "]";
	}
	
}
