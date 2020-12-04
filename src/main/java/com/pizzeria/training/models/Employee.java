package com.pizzeria.training.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


public class Employee {
	@Id
	public ObjectId _id;
	private long phoneNumber;
	private String email;
	private HomeAddress address;
	private long employeeId;
	
	
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Employee(ObjectId _id, long phoneNumber, String email, HomeAddress address, long employeeId) {
		super();
		this._id = _id;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.employeeId = employeeId;
	}




	@Override
	public String toString() {
		return "Employee [_id=" + _id + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address
				+ ", employeeId=" + employeeId + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (employeeId ^ (employeeId >>> 32));
		result = prime * result + (int) (phoneNumber ^ (phoneNumber >>> 32));
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
		Employee other = (Employee) obj;
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
		if (employeeId != other.employeeId)
			return false;
		if (phoneNumber != other.phoneNumber)
			return false;
		return true;
	}




	public ObjectId get_id() {
		return _id;
	}




	public void set_id(ObjectId _id) {
		this._id = _id;
	}




	public long getPhoneNumber() {
		return phoneNumber;
	}




	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public HomeAddress getAddress() {
		return address;
	}




	public void setAddress(HomeAddress address) {
		this.address = address;
	}




	public long getEmployeeId() {
		return employeeId;
	}




	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}




	private static class HomeAddress {
		private String streetAddress;
		private String streetAddressLine2;
		private String city;
		private String state;
		private String postal;
		
		public HomeAddress() {
		}
		
		public HomeAddress(String streetAddress, String streetAddressLine2, String city, String state, String postal) {
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
}
