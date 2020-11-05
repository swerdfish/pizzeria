package com.pizzeria.training.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public class Customer {
	@Id
	public ObjectId _id;
	
	private long phoneNum;
	private String email;
	private String address;
	private long creditCard;
	private List<Pizza> favoriteOrder;
	
	public Customer() {}

	public List<Pizza> getFavoriteOrder() {
		return favoriteOrder;
	}

	public void setFavoriteOrder(List<Pizza> favoriteOrder) {
		this.favoriteOrder = favoriteOrder;
	}

	public long getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public long getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(long creditCard) {
		this.creditCard = creditCard;
	}
	
	public Customer(long phoneNum, String email, String address, long creditCard) {
		super();
		this.phoneNum = phoneNum;
		this.email = email;
		this.address = address;
		this.creditCard = creditCard;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (phoneNum ^ (phoneNum >>> 32));
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
		Customer other = (Customer) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (phoneNum != other.phoneNum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [_id=" + _id + ", phoneNum=" + phoneNum + ", email=" + email + ", address=" + address
				+ ", creditCard=" + creditCard + ", favoriteOrder=" + favoriteOrder + "]";
	}
	
	
}
