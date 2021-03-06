package com.infrrd.inventory.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.infrrd.inventory.model.InventoryData;
import com.infrrd.inventory.util.InventoryAlreadyExistsException;
import com.infrrd.inventory.util.InventoryNotFoundException;

/**
 * service to get inventory data stored in memory
 */
@Component
public class InventoryService {

	public Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public static List<InventoryData> inventoryList = new ArrayList<InventoryData>();

	public Map<String, Object> addInventory(InventoryData request) throws InventoryAlreadyExistsException {

		// checking the uniqueness of inventory id in the list
		if (request != null && request.getInventoryId() != null && inventoryList.size() > 0) {
			// InventoryData inventory = inventoryList.stream().filter(t ->
			// t.getInventoryId().equals(request.getInventoryId())).findAny().get();
			for (InventoryData inventory : inventoryList) {
				if (inventory.getInventoryId().equals(request.getInventoryId()) && inventory.getStatus() == true) {
					throw new InventoryAlreadyExistsException(request.getInventoryId());
				}
			}
		}

		HashMap<String, Object> response = new HashMap<String, Object>();

		inventoryList.add(request);
		response.put("statusCode", 200);
		response.put("statusMessage", "success");
		return response;
	}

	/**
	 * Service used to get the inventory details of the given inventory id if exists and status is true
	 * @param inventoryId
	 * @return InventoryData inventory
	 * @throws InventoryNotFoundException
	 */
	public InventoryData getInventory(String inventoryId) throws InventoryNotFoundException {
		// return inventoryList.stream().filter(t
		// ->t.getInventoryId().equals(inventoyId)).findAny().get();
		for (InventoryData inventory : inventoryList) {
			if (inventory.getInventoryId().equals(inventoryId) && inventory.getStatus() != false) {
				return inventory;
			}
		}
		throw new InventoryNotFoundException(inventoryId);
	}
	
/**
 * Service used to change the status to false and update the updatedOn with the current date-time
 * @param inventoryId
 * @return Map<String, Object> response
 */
	public Map<String, Object> deleteInventory(String inventoryId) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		for (int i = 0; i < inventoryList.size(); i++) {
			InventoryData inventory = inventoryList.get(i);
			if (inventory.getInventoryId().equals(inventoryId) && inventory.getStatus() == true) {
				inventory.setStatus(false);
				inventory.setUpdatedOn(new Date());
				response.put("statusCode", 200);
				response.put("statusMessage", "success");
				return response;
			}
		}
		throw new InventoryNotFoundException(inventoryId);
	}

	/**
	 * Service used to get the list of inventory between the given range fromDate and toDate
	 * @param fromDate
	 * @param toDate
	 * @return List<InventoryData> response
	 * @throws Exception
	 */
	public List<InventoryData> listInventory(Date fromDate, Date toDate) throws Exception {
		List<InventoryData> response = new ArrayList<InventoryData>();
		LOGGER.info(" inventoryList  =", inventoryList);
		for (InventoryData inventory: inventoryList) {
			if (fromDate != null && toDate != null && inventory.getCreatedOn() != null
					&& inventory.getUpdatedOn() != null && inventory.getCreatedOn().compareTo(fromDate) >= 0
					&& toDate.compareTo(inventory.getUpdatedOn()) >= 0) {
				response.add(inventory);
			} else if (fromDate != null && toDate == null && inventory.getCreatedOn() != null
					&& inventory.getCreatedOn().compareTo(fromDate) >= 0) {
				response.add(inventory);
			} else if (toDate != null && fromDate == null && inventory.getUpdatedOn() != null
					&& toDate.compareTo(inventory.getUpdatedOn()) >= 0) {
				response.add(inventory);
			} else if(fromDate == null && toDate == null) { // if both fromDate and toDate as null return the whole list
				response.add(inventory);
			}
		}
		return response;
	}
}
