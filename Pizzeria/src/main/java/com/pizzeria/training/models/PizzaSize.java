package com.pizzeria.training.models;

/**
 * Enum denoting pizza size, measured by diameter
 */
public enum PizzaSize {
	/**single slice*/
	SLICE	(0, 4), 
	/**personal size*/
	PERSONAL(1, 8),
	/**small*/
	SMALL	(2, 10),
	/**medium*/
	MEDIUM	(3, 12),
	/**large*/
	LARGE	(4, 14),
	/**extra large*/
	XLARGE	(5, 16);
	/**Index for easy reference*/
	private int index;
	/**diameter of the pizza*/
	private int diameter;
	
	/**
	 * Constructor for Size object
	 * @param index Index for reference
	 * @param diameter Pizza diameter
	 */
	PizzaSize(int index, int diameter){
		this.index = index;
		this.diameter = diameter;
	}
	/**
	 * Returns the current index
	 * @return Current index
	 */
	public int getIndex() {
		return this.index;
	}
	/**
	 * Returns the current diameter
	 * @return Current diameter
	 */
	public int getDiameter() {
		return this.diameter;
	}
}
