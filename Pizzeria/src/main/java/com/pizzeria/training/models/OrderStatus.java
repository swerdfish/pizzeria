package com.pizzeria.training.models;
/**
 * Enum denoting the current state of an order
 */
public enum OrderStatus {
	/** order is being made/delivered */
	IN_PROGRESS		("IN_PROGRESS"),
	/** order has been fulfilled */
	COMPLETED		("COMPLETED");
	/** string representation of Enum value */
	private String status;
	
	/**
	 * constructor for new status Enum
	 * @param status the status to initialize with
	 */
	OrderStatus(String status) {
		this.status = status;
	}

	/** Returns current status 
	 * @return current status as a string
	 */
	public String getStatus() {
		return status;
	}
	
	
	
}
