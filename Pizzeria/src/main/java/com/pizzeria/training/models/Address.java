package com.pizzeria.training.models;

/**
 * Home Address class.
 */
public class Address {

	private String streetAddress;
	/** line 2 of street address */
	private String streetAddressLine2;
	/** city of residence */
	private String city;
	/** state of residence */
	private String state;
	/** postal/zip code */
	private String postal;

	/**
	 * Home address default constructor
	 */
	public Address() {
	}

	/**
	 * Home address parameterized constructor
	 * 
	 * @param streetAddress      street address line 1
	 * @param streetAddressLine2 street address line 2
	 * @param city               city of the address
	 * @param state              state of the address
	 * @param postal             postal/zip code
	 */
	public Address(String streetAddress, String streetAddressLine2, String city, String state, String postal) {
		super();
		this.streetAddress = streetAddress;
		this.streetAddressLine2 = streetAddressLine2;
		this.city = city;
		this.state = state;
		this.postal = postal;
	}

	/**
	 * retrieve street address
	 * 
	 * @return street address line 1
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * set street address
	 * 
	 * @param streetAddress String: new street address line 1
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * retrieve street address line 2
	 * 
	 * @return street address line 2
	 */
	public String getStreetAddressLine2() {
		return streetAddressLine2;
	}

	/**
	 * set street address line 2
	 * 
	 * @param streetAddressLine2 String: new street address line 2
	 */
	public void setStreetAddressLine2(String streetAddressLine2) {
		this.streetAddressLine2 = streetAddressLine2;
	}

	/**
	 * retrieve city
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * set city
	 * 
	 * @param city String: new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * retrieve state
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * set state
	 * 
	 * @param state String: new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Postal code getter method
	 * 
	 * @return postal code
	 */
	public String getPostal() {
		return postal;
	}

	/**
	 * Postal code setter method
	 * 
	 * @param postal: new postal code
	 */
	public void setPostal(String postal) {
		this.postal = postal;
	}

	/**
	 * toString override method
	 * 
	 */
	@Override
	public String toString() {
		return "Address [\n\tstreetAddress=" + streetAddress + "\n\tstreetAddressLine2=" + streetAddressLine2
				+ "\n\tcity=" + city + "\n\tstate=" + state + "\n\tpostal=" + postal + "\n]";
	}

	/**
	 * hashCode override method
	 */
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