package com.pizzeria.training.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public class Customer {
	@Id
	public ObjectId _id;
	
	private Integer id;
	private long phoneNum;
	private String email;
	private String address;
	private long creditCard;
	private int favoriteOrder;
	
	public Customer() {}
	
	public int getFavoriteOrder() {
		return favoriteOrder;
	}

	public void setFavoriteOrder(int favoriteOrder) {
		this.favoriteOrder = favoriteOrder;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Customer(int id, long phoneNum, String email, String address, long creditCard) {
		super();
		this.id = id;
		this.phoneNum = phoneNum;
		this.email = email;
		this.address = address;
		this.creditCard = creditCard;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + (int) (creditCard ^ (creditCard >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
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
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (creditCard != other.creditCard)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (phoneNum != other.phoneNum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", phoneNum=" + phoneNum + ", email=" + email + ", address=" + address
				+ ", creditCard=" + creditCard + ", favoriteOrder=" + favoriteOrder + "]";
	}
	
	
}
