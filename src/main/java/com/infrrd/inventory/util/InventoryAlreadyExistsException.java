package com.infrrd.inventory.util;

public class InventoryAlreadyExistsException extends RuntimeException{

	public InventoryAlreadyExistsException(String inventoryId) {
		super(String.format("Inventory with id %s is already exists, please try with new inventory", inventoryId));
	}
}
