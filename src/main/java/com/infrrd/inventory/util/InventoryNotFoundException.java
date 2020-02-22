package com.infrrd.inventory.util;

public class InventoryNotFoundException extends RuntimeException {

	public InventoryNotFoundException(String inventoryId) {
     super(String.format("Inventory with Id %s not found", inventoryId));		
	}
	
}
