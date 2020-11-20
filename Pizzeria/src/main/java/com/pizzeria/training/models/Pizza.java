package com.pizzeria.training.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Pizza class for use by Pizzeria restaurant
 * Covers details about size, type, toppings, price, as well as a unique identifier in the mongoDB database
 */
@Document(collection="pizza")
public class Pizza {
	/** mongoDB document Id */
	@Id
	public ObjectId _id;
	/** height of the dish cooked in */
	private float height;
	/** Enum specifying style of pizza crust */
	private PizzaType type;
	/** List of topping Enums on the pizza */
	private List<Toppings> toppings;
	/** price of the pizza */
	private double cost;
	/** Enum specifying a diameter for the pizza */
	private Size size;
	/** No argument constructor */
	public Pizza() {}
	/**
	 * Parameterized constructor
	 * @param height Height of the dish cooked in
	 * @param type Style of pizza crust
	 * @param toppings Toppings to put on the pizza
	 * @param cost Price of the pizza
	 * @param size Diameter of the pizza
	 */
	public Pizza(float height, PizzaType type, List<Toppings> toppings, double cost, Size size) {
		super();
		this.height = height;
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
		return _id;
	}
	/**
	 * Update mongoDB document Id
	 * @param _id new object Id
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	/**
	 * Return height of the pizza dish
	 * @return height 
	 */
	public float getHeight() {
		return height;
	}
	/**
	 * Update height of the pizza dish
	 * @param height new desired height
	 */
	public void setHeight(float height) {
		this.height = height;
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
	 * Return a list of toppings on the pizza
	 * @return Current toppings
	 */
	public List<Toppings> getToppings() {
		return toppings;
	}
	/**
	 * Update toppings on the pizza
	 * @param toppings new list of toppings 
	 */
	public void setToppings(List<Toppings> toppings) {
		this.toppings = toppings;
	}
	/**
	 * Return the pizza's price
	 * @return Current price
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * Update the pizza's price
	 * @param cost New price 
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * Return the pizza's diameter
	 * @return diameter 
	 */
	public Size getSize() {
		return size;
	}
	/**
	 * Update the pizza's diameter
	 * @param size new desired diameter
	 */
	public void setSize(Size size) {
		this.size = size;
	}
	/**
	 * hashCode override method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(height);
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
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height))
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
	 * @return Total price calculated
	 */
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
		return "Pizza [height=" + height + ", type=" + type + ", toppings=" + toppings + ", cost=" + cost + ", size="
				+ size + "]";
	}
	
}
