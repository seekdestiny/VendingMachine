package com.laioffer.vending;

public abstract class Snack {
	public abstract double getPrice();
	public abstract void setPrice(double price);
	public abstract int getCode();
	public abstract String getName();
}

class Popcorn extends Snack {
	private double price;
	private int snackCode;
	private final String snackName;
	
	public Popcorn(int snackCode, double price) {
		this.price = price;
		this.snackCode = snackCode;
		this.snackName = "Popcorn";
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getCode() {
		return snackCode;
	}
	
	public String getName() {
		return snackName;
	}
}

class Biscuits extends Snack {
	private double price;
	private int snackCode;
	private final String snackName;
	
	public Biscuits(int snackCode, double price) {
		this.price = price;
		this.snackCode = snackCode;
		this.snackName = "Biscuits";
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getCode() {
		return snackCode;
	}
	
	public String getName() {
		return snackName;
	}
}
