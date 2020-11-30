package com.pizzeria.training.models;
/**
 * Enum denoting pizza crust type, distinguished by height
 */
public enum PizzaType {
	/** classic crust */
	CLASSIC		(0, 0.75D),
	/**thin crust*/
	THIN_CRUST	(1, 0.5D),
	/**deep dish */
	DEEP_DISH	(2, 3.0D),
	/**sicilian style*/
	SICILIAN	(3, 1.5D),
	/**stuffed crust*/
	STUFFED		(4, 2.0D),
	/**gluten free crust*/
	GLUTEN_FREE	(5, 0.5D);
	/**index for easy reference*/
	private int index;
	/**crust height*/
	private double height;
	/**
	 * Constructor for PizzaType object
	 * @param index Index for reference
	 * @param height Height of the crust
	 */
	PizzaType(int index, double height) {
		this.index = index;
		this.height = height;
	}
	/**
	 * Return current type's index
	 * @return Current index
	 */
	public int getIndex() {
		return this.index;
	}
	/**
	 * Return current type's crust height
	 * @return Current height
	 */
	public double getHeight() {
		return this.height;
	}
}
