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
		curSlot.remove(curSlot.size() - 1);
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
		return money - snack.getPrice();	
	}

	public Bucket collectSnackAndChange(int snackCode, double money) {
		Snack snack = collectSnack(snackCode);
		double change = collectChange(snack, money);
		return new Bucket(snack, change);
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

