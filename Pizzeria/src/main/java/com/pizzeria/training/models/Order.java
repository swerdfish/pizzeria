package com.pizzeria.training.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
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
	@Id
	public ObjectId _id;
	
	private List<Pizza> pizzas;
	private String customerIdString;
	private Double cost;
	private Double tip;
	private Boolean setAsFavorite;	//Prevents from being added to the database
	
	public Order() {
		super();
	}
	
	@PersistenceConstructor
	public Order(List<Pizza> pizzas, String customerIdString, Double cost, Double tip, Boolean setAsFavorite) {
		this();
		this.pizzas = pizzas;
		this.customerIdString = customerIdString;
		this.cost = (cost != null ? cost : pizzas.stream().map(Pizza::getCost).reduce(0.0D, (subtotal, current) -> subtotal + current));
		this.tip = (tip != null ? tip : 0.0D);
		this.setAsFavorite = (setAsFavorite != null ? setAsFavorite : false);
		
	}
	
	public Order(List<Pizza> pizzas, ObjectId customerId, Double cost, Double tip, Boolean setAsFavorite) {
		this(pizzas, customerId.toHexString(), cost, tip, setAsFavorite);
	}
	
	public ObjectId get_id() {
		return _id;
	}
	
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public String getCustomerIdString() {
		return customerIdString;
	}

	public void setCustomerIdString(String customerIDString) {
		this.customerIdString = customerIDString;
	}

	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public double getTip() {
		return tip;
	}
	
	public void setTip(double tip) {
		this.tip = tip;
	}
	
	public boolean getFavorite() {
		return setAsFavorite;
	}
	
	public void setFavorite(boolean favorite) {
		this.setAsFavorite = favorite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((customerIdString == null) ? 0 : customerIdString.hashCode());
		result = prime * result + ((pizzas == null) ? 0 : pizzas.hashCode());
		result = prime * result + (setAsFavorite ? 1231 : 1237);
		temp = Double.doubleToLongBits(tip);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (customerIdString == null) {
			if (other.customerIdString != null)
				return false;
		} else if (!customerIdString.equals(other.customerIdString))
			return false;
		if (pizzas == null) {
			if (other.pizzas != null)
				return false;
		} else if (!pizzas.equals(other.pizzas))
			return false;
		if (setAsFavorite != other.setAsFavorite)
			return false;
		if (Double.doubleToLongBits(tip) != Double.doubleToLongBits(other.tip))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [_id=" + _id + ", pizzas=" + pizzas + ", customerID=" + customerIdString + ", cost=" + cost + ", tip="
				+ tip + ", setAsFavorite=" + setAsFavorite + "]";
	}
}
