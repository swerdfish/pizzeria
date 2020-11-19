package com.pizzeria.training.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

///**
// * 
// * @author teodorojr.delson, stephen.gruver
// * @param _id 				The MongoDB document Id.
// * @param pizzas			The pizzas ordered by the customer
// * @param customerIdString	The hex string of the _id of the 
// * 							<a href="#{@link com.pizzeria.training.models.Customer}">{@link Customer}</a> 
// * 							that ordered the pizza(s).
// * @param cost 				The cost of the Order
// * @param tip				The tip paid by the customer in addition to the cost
// * @param setAsFavorite			Whether or not the Order's pizzas should be saved as the Customer's favoriteOrder. 
// */
@Document(collection ="orders")
/**
 * Order class pertaining to orders form the pizzeria
 */
public class Order {
	/** The MongoDB document Id. */
	@Id
	public ObjectId _id;
	/** list of pizzas in the order */
	private List<Pizza> pizzas;
	/** ID of customer that placed the order */
	private String customerIdString;
	/** price of order */
	private double cost;
	/** tip left by customer */
	private double tip;
	/** Whether or not the Order's pizzas should be saved as the Customer's favoriteOrder */
	private boolean setAsFavorite;	//Prevents from being added to the database
	/** current state of production of the order */
	private OrderStatus orderStatus;
	
	/**
	 * no-argument constructor
	 * Sets status as IN_PROGRESS by default
	 */
	public Order() {
		super();
		this.orderStatus = OrderStatus.IN_PROGRESS;
	}
	/**
	 * parameterized constructor
	 * @param pizzas list of pizzas in the order
	 * @param customerIdString ID of customer that placed the order
	 * @param cost price of the order
	 * @param tip tip left by customer
	 * @param setAsFavorite Whether or not the Order's pizzas should be saved as the Customer's favoriteOrder
	 */
	@PersistenceConstructor
	public Order(List<Pizza> pizzas, String customerIdString, Double cost, Double tip, Boolean setAsFavorite) {
		this();
		this.pizzas = pizzas;
		this.customerIdString = customerIdString;
		this.cost = (cost != null ? cost : pizzas.stream().map(Pizza::getCost).reduce(0.0D, (subtotal, current) -> subtotal + current));
		this.tip = (tip != null ? tip : 0.0D);
		this.setAsFavorite = (setAsFavorite != null ? setAsFavorite : false);
		this.orderStatus = OrderStatus.IN_PROGRESS;
		
	}
	
	public Order(List<Pizza> pizzas, ObjectId customerId, Double cost, Double tip, Boolean setAsFavorite) {
		this(pizzas, customerId.toHexString(), cost, tip, setAsFavorite);
	}
	/**
	 * retrieve order's ID
	 * @return mongoDB document Id of the order
	 */
	public ObjectId get_id() {
		return _id;
	}
	/**
	 * set order's ID
	 * @param _id new ID
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	/**
	 * retrieve all pizzas on the order
	 * @return list of pizzas the order contains
	 */
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	/**
	 * set/modify pizzas in the order
	 * @param pizzas new list of pizzas
	 */
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	/**
	 * retrieve ID of the customer who placed the order
	 * @return mongoDB document Id of the customer
	 */
	public String getCustomerIdString() {
		return customerIdString;
	}
	/**
	 * set ID of the customer who placed the order
	 * @param customerIdString document Id of the new customer
	 */
	public void setCustomerIdString(String customerIdString) {
		this.customerIdString = customerIdString;
	}
	/**
	 * retrieve the cost of the order
	 * @return order total price
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * set the price of the order
	 * @param cost new price
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * retrieve tip left by customer
	 * @return tip left
	 */
	public double getTip() {
		return tip;
	}
	/**
	 * set tip left by customer
	 * @param tip new tip
	 */
	public void setTip(double tip) {
		this.tip = tip;
	}
	/**
	 * retrieve whether or not the order will be saved as a favorite
	 * @return boolean value indicating to save or not
	 */
	public boolean getFavorite() {
		return setAsFavorite;
	}
	/**
	 * set whether the order will be saved as a favorite
	 * @param setAsFavorite boolean indicating to save or not
	 */
	public void setSetAsFavorite(boolean setAsFavorite) {
		this.setAsFavorite = setAsFavorite;
	}
	/**
	 * retrieve current order status
	 * @return status enum 
	 */
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	/**
	 * set current order status to completed
	 */
	public void setOrderStatus() {
		this.orderStatus = OrderStatus.COMPLETED;
	}
	/**
	 * hashCode override
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		return result;
	}
	/**
	 * equals override method
	 * @return Boolean value of whether the two compared objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}
	/**
	 * toString override method
	 * @return String representation of the order
	 */
	@Override
	public String toString() {
		return "Order [_id=" + _id + ", pizzas=" + pizzas + ", customerIdString=" + customerIdString + ", cost=" + cost
				+ ", tip=" + tip + ", setAsFavorite=" + setAsFavorite + ", orderStatus=" + orderStatus + "]";
	}

}
