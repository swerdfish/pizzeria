package com.pizzeria.training.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public class Customer {
	//private : made public for dummy data : to be changed to private if we no longer need dummy data.
	public static class PaymentCard {
		// TODO Implement hashing or some other kind of security for this information 
		private Long cardNumber;
		private String expiration;
		private Short securityCode;
		private Address billingAddress;
		
		public PaymentCard() {
		}
		
		public PaymentCard(Long cardNumber, String expiration, Short securityCode, Address billingAddress) {
			super();
			this.cardNumber = cardNumber;
			this.expiration = expiration;
			this.securityCode = securityCode;
			this.billingAddress = billingAddress;
		}

		public Long getCardNumber() {
			return cardNumber;
		}

		public void setCardNumber(Long cardNumber) {
			this.cardNumber = cardNumber;
		}

		public String getExpiration() {
			return expiration;
		}

		public void setExpiration(String expiration) {
			this.expiration = expiration;
		}

		public Short getSecurityCode() {
			return securityCode;
		}

		public void setSecurityCode(Short securityCode) {
			this.securityCode = securityCode;
		}

		public Address getBillingAddress() {
			return billingAddress;
		}

		public void setBillingAddress(Address billingAddress) {
			this.billingAddress = billingAddress;
		}

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

		@Override
		public String toString() {
			return "PaymentCard [cardNumber=" + cardNumber + ", expiration=" + expiration + ", securityCode="
					+ securityCode + ", billingAddress=" + billingAddress + "]";
		}
	}
	
	@Id
	public ObjectId _id;
	
	@Indexed(unique = true)
	private String email;
	private String password;
	
	private String firstName;
	private String lastName;
	private Long phoneNum;
	private Address homeAddress;
	private PaymentCard card;
	
	private List<Pizza> favoriteOrder;
	
	public Customer() {}
	
	public Customer(ObjectId _id, String email, String password, String firstName, String lastName, Long phoneNum, Address homeAddress,
			PaymentCard card, List<Pizza> favoriteOrder) {
		super();
		this._id = _id;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNum = phoneNum;
		this.email = email;
		this.homeAddress = homeAddress;
		this.card = card;
		this.favoriteOrder = favoriteOrder;
	}

	public ObjectId get_id() {
		return _id;
	}

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

	public Long getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(Long phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public PaymentCard getCard() {
		return card;
	}

	public void setCard(PaymentCard card) {
		this.card = card;
	}

	public List<Pizza> getFavoriteOrder() {
		return favoriteOrder;
	}

	public void setFavoriteOrder(List<Pizza> favoriteOrder) {
		this.favoriteOrder = favoriteOrder;
	}

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
