package com.pizzeria.training.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pizzeria.training.util.PriceCalculator;

/**
* Class corresponding to documents in the Orders collection. Used for storing data related to pizza orders.
* 
* @author teodorojr.delson, stephen.gruver
*/
@Document(collection ="orders")
public class Order {
/** The MongoDB document Id. */
	@Id private ObjectId _id;
	/** customer that placed the order */
	@DBRef private Customer customer;
	@DBRef private ObjectId pizzeriaId;
	
	/** list of pizzas in the order */
	private List<Pizza> pizzas;
	/** price of order */
	private Double cost;
	/** tip left by customer */
	private Double tip;
	/** current state of production of the order */
	private OrderStatus status;
	private OrderType type;
	private Address deliveryAddress;
	
	/**
	 * no-argument constructor
	 * Sets status as IN_PROGRESS by default
	 */
	public Order() {
		super();
	}

	/**
	 * parameterized constructor
	 * @param pizzas list of pizzas in the order
	 * @param customer
	 * @param pizzeriaId
	 * @param pizzas
	 * @param cost price of the order	
	 * @param tip tip left by customer
	 * @param type
	 * @param deliveryAddress
	 */
	public Order(Customer customer, ObjectId pizzeriaId, List<Pizza> pizzas, Double cost, Double tip, OrderStatus status, OrderType type, Address deliveryAddress) {
		this();
		this.customer = customer;
		this.pizzeriaId = pizzeriaId;
		this.pizzas = pizzas;
		this.cost = cost;
		this.tip = (tip != null ? tip : 0.0D);
		this.status = status;
		this.type = type;
		this.deliveryAddress = deliveryAddress;
		
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
	 * retrieve the customer who placed the order
	 * @return mongoDB document of the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * set the customer who placed the order
	 * @param customer document of the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	 * retrieve the cost of the order
	 * @return order total price
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 * set the price of the order
	 * @param cost new price
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}

	/**
	 * retrieve tip left by customer
	 * @return tip left
	 */
	public Double getTip() {
		return tip;
	}

	/**
	 * set tip left by customer
	 * @param tip new tip
	 */
	public void setTip(Double tip) {
		this.tip = tip;
	}

	/**
	 * retrieve current order status
	 * @return status enum 
	 */
	public OrderStatus getStatus() {
		return status;
	}
	
	/**
	 * set current order status to completed
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}	


	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}

	public ObjectId getPizzeriaId() {
		return pizzeriaId;
	}

	public void setPizzeriaId(ObjectId pizzeriaId) {
		this.pizzeriaId = pizzeriaId;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * hashCode override method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((deliveryAddress == null) ? 0 : deliveryAddress.hashCode());
		result = prime * result + ((pizzas == null) ? 0 : pizzas.hashCode());
		result = prime * result + ((pizzeriaId == null) ? 0 : pizzeriaId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tip == null) ? 0 : tip.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	/**
	 * equals override method
	 * 
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
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (deliveryAddress == null) {
			if (other.deliveryAddress != null)
				return false;
		} else if (!deliveryAddress.equals(other.deliveryAddress))
			return false;
		if (pizzas == null) {
			if (other.pizzas != null)
				return false;
		} else if (!pizzas.equals(other.pizzas))
			return false;
		if (pizzeriaId == null) {
			if (other.pizzeriaId != null)
				return false;
		} else if (!pizzeriaId.equals(other.pizzeriaId))
			return false;
		if (status != other.status)
			return false;
		if (tip == null) {
			if (other.tip != null)
				return false;
		} else if (!tip.equals(other.tip))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	/**
	 * toString override method
	 *
	 */
	@Override
	public String toString() {
		return "Order [_id=" + _id + ", customer=" + customer + ", pizzeriaId=" + pizzeriaId + ", pizzas=" + pizzas
				+ ", cost=" + cost + ", tip=" + tip + ", status=" + status + ", type=" + type + ", deliveryAddress="
				+ deliveryAddress + "]";
	}

}
/*

	 {
		"customer":{
			"_id":"5fbc3c9d600ae701ef07d947"
		},
		"pizzeriaId":null,
		"pizzas":[
			{
				"type": "CLASSIC",
				"toppings": [
				"PEPPERONI", "SAUSAGE"
				],
				"cost": 10.0,
				"size": "LARGE"
			},
			{
				"type": "DEEP_DISH",
				"toppings": [
					"SPINACH", "CHEESE"
				],
				"cost": 20.0,
				"size": "SMALL"
			}
		],
		"cost":30.0,
		"tip":5.0,
		"status":"COOKING",
		"type":"PICKUP",
		"deliveryAddress":{
			"streetAddress":"1 delivery",
			"streetAddressLine2":"2 delivery",
			"city":"city",
			"state":"state",
			"postal":"postal"
		}
	}

 */