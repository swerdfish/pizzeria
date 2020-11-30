package com.pizzeria.training.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
/**
 * Customer class.
 * Contains a nested payment card class.
 */
public class Customer {
	/**
	 * Payment card class
	 */
	public static class PaymentCard {
		// TODO Implement hashing or some other kind of security for this information 
		/** card number*/
		private Long cardNumber;
		/** card expiration date */
		private String expiration;
		/** card security code */
		private Short securityCode;
		private Address billingAddress;
		
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
		public PaymentCard(Long cardNumber, String expiration, Short securityCode, Address billingAddress) {
			super();
			this.cardNumber = cardNumber;
			this.expiration = expiration;
			this.securityCode = securityCode;
			this.billingAddress = billingAddress;
		}

		/**
		 * retrieve card number
		 * @return card number
		 */
		public Long getCardNumber() {
			return cardNumber;
		}

		/**
		 * set card number
		 * @param cardNumber long: new card number
		 */
		public void setCardNumber(Long cardNumber) {
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
		public Short getSecurityCode() {
			return securityCode;
		}
		/**
		 * set card security code
		 * @param securityCode short: new card security code
		 */
		public void setSecurityCode(Short securityCode) {
			this.securityCode = securityCode;
		}

		public Address getBillingAddress() {
			return billingAddress;
		}

		public void setBillingAddress(Address billingAddress) {
			this.billingAddress = billingAddress;
		}

		/**
		 * hashCode override method
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((billingAddress == null) ? 0 : billingAddress.hashCode());
			result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
			result = prime * result + ((expiration == null) ? 0 : expiration.hashCode());
			result = prime * result + ((securityCode == null) ? 0 : securityCode.hashCode());
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
			if (billingAddress == null) {
				if (other.billingAddress != null)
					return false;
			} else if (!billingAddress.equals(other.billingAddress))
				return false;
			if (cardNumber == null) {
				if (other.cardNumber != null)
					return false;
			} else if (!cardNumber.equals(other.cardNumber))
				return false;
			if (expiration == null) {
				if (other.expiration != null)
					return false;
			} else if (!expiration.equals(other.expiration))
				return false;
			if (securityCode == null) {
				if (other.securityCode != null)
					return false;
			} else if (!securityCode.equals(other.securityCode))
				return false;
			return true;
		}
		/**
		 * toString override method
		 *
		 */
		@Override
		public String toString() {
			return "PaymentCard [cardNumber=" + cardNumber + ", expiration=" + expiration + ", securityCode="
					+ securityCode + ", billingAddress=" + billingAddress + "]";
		}
	}
	
	/**unique ID*/
	@Id
	public ObjectId _id;
	
	/**Email*/
	@Indexed(unique = true)
	private String email;
	private String password;
	
	private String firstName;
	private String lastName;
	/**Phone number*/
	private String phoneNum;
	/**home address*/
	private Address homeAddress;
	/**credit card*/
	private PaymentCard card;
	/**list of favorite orders*/
	private List<Pizza> favoriteOrder;
	
	/**
	 * Customer default constructor
	 */
	public Customer() {}
	
	/**
	 * Customer parameterized constructor
	 * @param phoneNum phone number
	 * @param email email address
	 * @param homeAddress home address object
	 * @param card payment card object
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param favoriteOrder
	 */
	public Customer(String email, String password, String firstName, String lastName, String phoneNum, Address homeAddress,
			PaymentCard card, List<Pizza> favoriteOrder) {
		super();
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNum = phoneNum;
		this.email = email;
		this.homeAddress = homeAddress;
		this.card = card;
		this.favoriteOrder = favoriteOrder;
	}
	
	/**
	 * Customer parameterized constructor
	 * @param _id
	 * @param phoneNum phone number
	 * @param email email address
	 * @param homeAddress home address object
	 * @param card payment card object
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param favoriteOrder
	 */
	public Customer(ObjectId _id, String email, String password, String firstName, String lastName, String phoneNum, Address homeAddress,
			PaymentCard card, List<Pizza> favoriteOrder) {
		this(email, password, firstName, lastName, phoneNum, homeAddress, card, favoriteOrder);
		set_id(_id);
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * retrieve customer phone number
	 * @return customer's phone number
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * set customer phone number
	 * @param phoneNum long: new phone number
	 */
	public void setPhoneNum(String phoneNum) {
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
	public Address getHomeAddress() {
		return homeAddress;
	}

	/**
	 * set customer address
	 * @param address HomeAddress: new address
	 */
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
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
	 * hashCode override method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((card == null) ? 0 : card.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((favoriteOrder == null) ? 0 : favoriteOrder.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((homeAddress == null) ? 0 : homeAddress.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNum == null) ? 0 : phoneNum.hashCode());
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
		if (card == null) {
			if (other.card != null)
				return false;
		} else if (!card.equals(other.card))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (favoriteOrder == null) {
			if (other.favoriteOrder != null)
				return false;
		} else if (!favoriteOrder.equals(other.favoriteOrder))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (homeAddress == null) {
			if (other.homeAddress != null)
				return false;
		} else if (!homeAddress.equals(other.homeAddress))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNum == null) {
			if (other.phoneNum != null)
				return false;
		} else if (!phoneNum.equals(other.phoneNum))
			return false;
		return true;
	}
	/**
	 * toString override method
	 * 
	 */
	@Override
	public String toString() {
		return "Customer [_id=" + _id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNum=" + phoneNum + ", homeAddress=" + homeAddress + ", card="
				+ card + ", favoriteOrder=" + favoriteOrder + "]";
	}
}
/*

{
	"email":"mail@mail.com",
	"password":"password",
	"firstName":"first",
	"lastName":"last",
	"phoneNum":1231231234,
	"homeAddress": {
		"streetAddress":"1 home",
		"streetAddressLine2":"2 home",
		"city":"city",
		"state":"state",
		"postal":"postal"
	},
	"card": {
		"cardNumber":1234123412341234,
		"expiration":"MM/YY",
		"securityCode":123,
		"billingAddress": {
			"streetAddress":"1 billing",
			"streetAddressLine2":"2 billing",
			"city":"city",
			"state":"state",
			"postal":"postal"
		}
	},
	"favoriteOrder": [
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
	]
}

 */
