package com.pizzeria.training.models;

public class Address {
	private String streetAddress;
	private String streetAddressLine2;
	private String city;
	private String state;
	private String postal;
	
	public Address() {
	}
	
	public Address(String streetAddress, String streetAddressLine2, String city, String state, String postal) {
		super();
		this.streetAddress = streetAddress;
		this.streetAddressLine2 = streetAddressLine2;
		this.city = city;
		this.state = state;
		this.postal = postal;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getStreetAddressLine2() {
		return streetAddressLine2;
	}

	public void setStreetAddressLine2(String streetAddressLine2) {
		this.streetAddressLine2 = streetAddressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	@Override
	public String toString() {
		return "Address [\n\tstreetAddress=" + streetAddress + "\n\tstreetAddressLine2=" + streetAddressLine2 + "\n\tcity="
				+ city + "\n\tstate=" + state + "\n\tpostal=" + postal + "\n]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((postal == null) ? 0 : postal.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((streetAddress == null) ? 0 : streetAddress.hashCode());
		result = prime * result + ((streetAddressLine2 == null) ? 0 : streetAddressLine2.hashCode());
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
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (postal == null) {
			if (other.postal != null)
				return false;
		} else if (!postal.equals(other.postal))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (streetAddress == null) {
			if (other.streetAddress != null)
				return false;
		} else if (!streetAddress.equals(other.streetAddress))
			return false;
		if (streetAddressLine2 == null) {
			if (other.streetAddressLine2 != null)
				return false;
		} else if (!streetAddressLine2.equals(other.streetAddressLine2))
			return false;
		return true;
	}
}
