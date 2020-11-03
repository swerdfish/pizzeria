package com.pizzeria.training.models;

import java.util.Date;
import java.util.List;

public class Order {
	private int orderID;
	private List<Pizza> pizzas;
	private int customerID;
	private Date date;
	private double cost;
	private double tip;
	
	public Order() {}
	
	public int getOrderID() {
		return this.orderID;
	}
	public void setOrderID(int id) {
		this.orderID = id;
	}
	
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public Order(List<Pizza> pizzas, int customerID, Date date, double cost, double tip) {
		super();
		this.pizzas = pizzas;
		this.customerID = customerID;
		this.date = date;
		this.cost = cost;
		this.tip = tip;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + customerID;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((pizzas == null) ? 0 : pizzas.hashCode());
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
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (customerID != other.customerID)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (pizzas == null) {
			if (other.pizzas != null)
				return false;
		} else if (!pizzas.equals(other.pizzas))
			return false;
		if (Double.doubleToLongBits(tip) != Double.doubleToLongBits(other.tip))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", pizzas=" + pizzas + ", customerID=" + customerID + ", date=" + date
				+ ", cost=" + cost + ", tip=" + tip + "]";
	}
	
	
	
}
