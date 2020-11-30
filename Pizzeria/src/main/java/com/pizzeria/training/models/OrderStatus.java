package com.pizzeria.training.models;
/**
 * Enum denoting the current state of an order
 */
public enum OrderStatus {
	/** order has been received but not started */
	PENDING,
	/** order is being made/delivered */
	COOKING,
	/** order has been made but has yet to be picked up/delivered */
	READY,
	/** order is being delivered */
	DELIVERING,
	/** order has been fulfilled */
	COMPLETED,
}
