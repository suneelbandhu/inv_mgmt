package com.cleartrip.inventory;

/**
 * util methods to get inventory data stored in memory
 */
public class InventoryDataInterface {

    public InventoryData getInventoryDataById(String inventoryId) {
        return InventoryController.inventoryDataList.stream().filter(inventory ->
                inventory.getId().equals(inventoryId)).findFirst().orElse(null);
    }

    public InventoryDataInterface() {
    }
}
