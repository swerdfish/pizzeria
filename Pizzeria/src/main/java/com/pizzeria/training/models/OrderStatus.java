package com.pizzeria.training.models;

public enum OrderStatus {
	IN_PROGRESS		("IN_PROGRESS"),
	COMPLETED		("COMPLETED");
	
	private String status;
	

	OrderStatus(String status) {
		this.status = status;
	}


	public String getStatus() {
		return status;
	}
	
	
	
}
