package com.laioffer.vending;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class VendingMachine {
	public static final int DEFAULT_SLOTSIZE = 12;
	public static final int DEFAULT_MAXSLOTS = 9;
	
	private Map<Integer, List<Snack>> inventory;
	private int slotSize;
	private int maxSlots;
	
	public VendingMachine() {
		this(DEFAULT_SLOTSIZE, DEFAULT_MAXSLOTS);
	}
	
	public VendingMachine(int slotSize, int maxSlots) {
		inventory = new HashMap<Integer, List<Snack>>();
		this.slotSize = slotSize;
		this.maxSlots = maxSlots;
		initialize();
	}
	
	private void initialize() {
		for (int i = 1; i <= maxSlots; i++) {
			inventory.put(i, new ArrayList<Snack>());
		}
	}
	
	private void addOneSnack(int snackCode, Snack snack) {
		List<Snack> curSlot = inventory.get(snackCode);
		if (curSlot == null) {
			System.out.println("Short of slots!");
		} else {
			if (curSlot.size() < slotSize) {
				curSlot.add(snack);
			} else {
				System.out.println("Full slot!");
			}
		}
	}
	
	public void refillVendingMachine(List<Snack> supplement) {
		for (Snack snack : supplement) {
			addOneSnack(snack.getCode(), snack);
		}
	}
	
	private Snack collectSnack(int snackCode) {
		if (!inventory.containsKey(snackCode)) {
			System.out.println("Wrong code input!");
			return null;
		}
		
		List<Snack> curSlot = inventory.get(snackCode);
		if (curSlot.size() == 0) {
			System.out.println("Short of snacks!");
			return null;
		}
		
		Snack item = curSlot.get(curSlot.size() - 1);
		return item;
	}
	
	private double collectChange(Snack snack, double money) {
		if (snack == null) {
			return money;
		}
		
		if (money < snack.getPrice()) {
			System.out.println("Not enough fund!");
			return money;
		}
		
		List<Snack> curSlot = inventory.get(snack.getCode());
		curSlot.remove(curSlot.size() - 1);
		return money - snack.getPrice();	
	}

	public Bucket collectSnackAndChange(int snackCode, double money) {
		Snack snack = collectSnack(snackCode);
		double change = collectChange(snack, money);
		return new Bucket(snack, change);
	}
	
	public static void main(String[] args) {
		VendingMachine myVending = new VendingMachine();
		List<Snack> supplement = new ArrayList<Snack>();
		supplement.add(new Popcorn(1, 0.5));
		supplement.add(new Popcorn(1, 0.5));
		supplement.add(new Popcorn(1, 1.5));
		supplement.add(new Popcorn(1, 3.5));
		supplement.add(new Popcorn(1, 4.0));
		supplement.add(new Biscuits(2, 4.5));
		supplement.add(new Biscuits(2, 4.5));
		supplement.add(new Biscuits(2, 3.5));
		supplement.add(new Biscuits(2, 3.5));
		supplement.add(new Biscuits(2, 1.5));
		myVending.refillVendingMachine(supplement);
				
		//Test1 - empty slot
		Bucket b1 = myVending.collectSnackAndChange(3, 2);
		
		//Test2 - wrong code
		Bucket b2 = myVending.collectSnackAndChange(10, 2);
		
		//Test3 - short of slots
		List<Snack> test = new ArrayList<Snack>();
		test.add(new Popcorn(10, 1.5));
		myVending.refillVendingMachine(test);
		
		//Test4 - Full slot
		List<Snack> test1 = new ArrayList<Snack>();
		for (int i = 1; i <= 13; i++) {
			test1.add(new Popcorn(3, 1.0));
		}
		myVending.refillVendingMachine(test1);
		
		//Test5 - Not enough fund
		myVending.collectSnackAndChange(1, 2);		
		
		//Test6 - regular purchase
		//Expected: get $4.0 Popcorn with change: 0.0
		Bucket b3 = myVending.collectSnackAndChange(1, 4);
		System.out.println("get $" + b3.getSnack().getPrice() + " " + 
						             b3.getSnack().getName() + " with change: " + b3.getChange());				
	}
}

class Bucket {
	Snack snack;
	double change;
	
	Bucket(Snack snack, double change) {
		this.snack = snack;
		this.change = change;
	}
	
	public Snack getSnack() {
		return snack;
	}
	
	public double getChange() {
		return change;
	}
}

