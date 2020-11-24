package com.pizzeria.training.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author teodorojr.delson, stephen.gruver
 * @param _id 				The MongoDB document Id.
 * @param pizzas			The pizzas ordered by the customer
 * @param customerIdString	The hex string of the _id of the 
 * 							<a href="#{@link com.pizzeria.training.models.Customer}">{@link Customer}</a> 
 * 							that ordered the pizza(s).
 * @param cost 				The cost of the Order
 * @param tip				The tip paid by the customer in addition to the cost
 * @param setAsFavorite			Whether or not the Order's pizzas should be saved as the Customer's favoriteOrder. 
 */
@Document(collection ="orders")
public class Order {
	@Id private ObjectId _id;
	@DBRef private Customer customer;
	@DBRef private ObjectId pizzeriaId;
	
	private List<Pizza> pizzas;
	private Double cost;
	private Double tip;
	private OrderStatus status;
	private OrderType type;
	private Address deliveryAddress;
	
	public Order() {
		super();
		this.status = OrderStatus.PENDING;
	}
	
	public Order(Customer customer, ObjectId pizzeriaId, List<Pizza> pizzas, Double cost, Double tip, OrderType type, Address deliveryAddress) {
		this();
		this.customer = customer;
		this.pizzeriaId = pizzeriaId;
		this.pizzas = pizzas;
		this.cost = (cost != null ? cost : pizzas.stream().map(Pizza::getCost).reduce(0.0D, (subtotal, current) -> subtotal + current));
		this.tip = (tip != null ? tip : 0.0D);
		this.status = OrderStatus.PENDING;
		this.type = type;
		this.deliveryAddress = deliveryAddress;
		
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getTip() {
		return tip;
	}

	public void setTip(Double tip) {
		this.tip = tip;
	}

	public OrderStatus getStatus() {
		return status;
	}
	
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