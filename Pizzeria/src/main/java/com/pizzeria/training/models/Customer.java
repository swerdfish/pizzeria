package com.pizzeria.training.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
/**
 * Customer class.
 * Contains a nested home address and payment card class.
 */
public class Customer {
	/**
	 * Home Address class.
	 */
	//private static class HomeAddress {
		/** line 1 of street address */
	//private : made public for dummy data - change soon to private if we don't need it in the future
	public static class HomeAddress {

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
		public HomeAddress() {
		}
		/**
		 * Home address parameterized constructor
		 * @param streetAddress street address line 1
		 * @param streetAddressLine2 street address line 2
		 * @param city city of the address
		 * @param state state of the address
		 * @param postal postal/zip code
		 */
		public HomeAddress(String streetAddress, String streetAddressLine2, String city, String state, String postal) {
			super();
			this.streetAddress = streetAddress;
			this.streetAddressLine2 = streetAddressLine2;
			this.city = city;
			this.state = state;
			this.postal = postal;
		}
		/**
		 * retrieve street address
		 * @return street address line 1
		 */
		public String getStreetAddress() {
			return streetAddress;
		}
		/**
		 * set street address
		 * @param streetAddress String: new street address line 1
		 */
		public void setStreetAddress(String streetAddress) {
			this.streetAddress = streetAddress;
		}
		/**
		 * retrieve street address line 2
		 * @return street address line 2
		 */
		public String getStreetAddressLine2() {
			return streetAddressLine2;
		}
		/**
		 * set street address line 2
		 * @param streetAddressLine2 String: new street address line 2
		 */
		public void setStreetAddressLine2(String streetAddressLine2) {
			this.streetAddressLine2 = streetAddressLine2;
		}
		/**
		 * retrieve city 
		 * @return city
		 */
		public String getCity() {
			return city;
		}
		/**
		 * set city
		 * @param city String: new city
		 */
		public void setCity(String city) {
			this.city = city;
		}
		/**
		 * retrieve state
		 * @return state
		 */
		public String getState() {
			return state;
		}
		/**
		 * set state
		 * @param state String: new state
		 */
		public void setState(String state) {
			this.state = state;
		}
		/**
		 * Postal code getter method
		 * @return postal code
		 */
		public String getPostal() {
			return postal;
		}
		/**
		 * Postal code setter method
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
			return "Address [\n\tstreetAddress=" + streetAddress + "\n\tstreetAddressLine2=" + streetAddressLine2 + "\n\tcity="
					+ city + "\n\tstate=" + state + "\n\tpostal=" + postal + "\n]";
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
			HomeAddress other = (HomeAddress) obj;
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
	/**
	 * Payment card class
	 */
	//private static class PaymentCard {

	//private : made public for dummy data : to be changed to private if we no longer need dummy data.
	public static class PaymentCard {

		// TODO Implement hashing or some other kind of security for this information 
		/** card number*/
		private long cardNumber;
		/** card expiration date */
		private String expiration;
		/** card security code */
		private short securityCode;
		/**
		 * Payment card default constructor
		 */
		public PaymentCard() {
		}
		/**
		 * Payment card parameterized constructor
		 * @param cardNumber card number
		 * @param expiration card expiration date
		 * @param securityCode card security code
		 */
		public PaymentCard(long cardNumber, String expiration, short securityCode) {
			super();
			this.cardNumber = cardNumber;
			this.expiration = expiration;
			this.securityCode = securityCode;
		}
		/**
		 * retrieve card number
		 * @return card number
		 */
		public long getCardNumber() {
			return cardNumber;
		}
		/**
		 * set card number
		 * @param cardNumber long: new card number
		 */
		public void setCardNumber(long cardNumber) {
			this.cardNumber = cardNumber;
		}
		/**
		 * retrieve card expiration date getter method
		 * @return card expiration date
		 */
		public String getExpiration() {
			return expiration;
		}
		/**
		 * set card expiration date
		 * @param expiration String: new card expiration date
		 */
		public void setExpiration(String expiration) {
			this.expiration = expiration;
		}
		/**
		 * retrieve card security code
		 * @return card security code
		 */
		public short getSecurityCode() {
			return securityCode;
		}
		/**
		 * set card security code
		 * @param securityCode short: new card security code
		 */
		public void setSecurityCode(short securityCode) {
			this.securityCode = securityCode;
		}
		/**
		 * hashCode override method
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (cardNumber ^ (cardNumber >>> 32));
			result = prime * result + ((expiration == null) ? 0 : expiration.hashCode());
			result = prime * result + securityCode;
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
			PaymentCard other = (PaymentCard) obj;
			if (cardNumber != other.cardNumber)
				return false;
			if (expiration == null) {
				if (other.expiration != null)
					return false;
			} else if (!expiration.equals(other.expiration))
				return false;
			if (securityCode != other.securityCode)
				return false;
			return true;
		}
		/**
		 * toString override method
		 *
		 */
		@Override
		public String toString() {
			return "PaymentCard [\n\tcardNumber=" + cardNumber + "\n\texpiration=" + expiration + "\n\tsecurityCode="
					+ securityCode + "\n]";
		}
	}
	
	/**unique ID*/
	@Id
	public ObjectId _id; 
	/**Phone number*/
	private long phoneNum; 
	/**Email*/
	private String email; 
	/**home address*/
	private HomeAddress address; 
	/**credit card*/
	private PaymentCard card; 
	/**list of favorite orders*/
	private List<Pizza> favoriteOrder; 
	/**
	 * Customer default constructor
	 */
	public Customer() {}
	/**
	 * Retrieve customer favorite orders
	 * @return list of the customer's favorite orders
	 */
	public List<Pizza> getFavoriteOrder() {
		return favoriteOrder;
	}
	/**
	 * set customer favorite orders
	 * @param favoriteOrder List(Pizza): new favorite orders
	 */
	public void setFavoriteOrder(List<Pizza> favoriteOrder) {
		this.favoriteOrder = favoriteOrder;
	}
	/**
	 * retrieve customer unique ID
	 * @return customer's unique ID
	 */
	public ObjectId get_id() {
		return _id;
	}
	/**
	 * set customer unique ID
	 * @param _id ObjectId: new customer ID
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	/**
	 * retrieve customer phone number
	 * @return customer's phone number
	 */
	public long getPhoneNum() {
		return phoneNum;
	}
	/**
	 * set customer phone number
	 * @param phoneNum long: new phone number
	 */
	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * retrieve customer email address
	 * @return customer's email address
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * set customer email address
	 * @param email String: new email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * retrieve customer address
	 * @return Customer's home address object
	 */
	public HomeAddress getAddress() {
		return address;
	}
	/**
	 * set customer address
	 * @param address HomeAddress: new address
	 */
	public void setAddress(HomeAddress address) {
		this.address = address;
	}
	/**
	 * retrieve customer card
	 * @return Customer's payment card object
	 */
	public PaymentCard getCard() {
		return card;
	}
	/**
	 * set customer card
	 * @param card PaymentCard: new payment card info
	 */
	public void setCard(PaymentCard card) {
		this.card = card;
	}
	/**
	 * Customer parameterized constructor
	 * @param phoneNum phone number
	 * @param email email address
	 * @param address home address object
	 * @param card payment card object
	 */
	public Customer(long phoneNum, String email, HomeAddress address, PaymentCard card) {
		super();
		this.phoneNum = phoneNum;
		this.email = email;
		this.address = address;
		this.card = card;
	}
	/**
	 * hashCode override method
	 */
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
	/**
	 * toString override method
	 * 
	 */
	@Override
	public String toString() {
		return "Customer [_id=" + _id + ", phoneNum=" + phoneNum + ", email=" + email + ", address=" + address
				+ ", creditCard=" + card + ", favoriteOrder=" + favoriteOrder + "]";
	}
	
	
}
