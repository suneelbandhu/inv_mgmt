package com.infrrd.inventory.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.infrrd.inventory.model.InventoryData;
import com.infrrd.inventory.util.InventoryConstant;
import com.infrrd.inventory.util.InventoryNotFoundException;

/**
 * service to get inventory data stored in memory
 */
@Component
public class InventoryService {

	public Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
    public static List<InventoryData> inventoryList = new ArrayList<>();
	
    public void getInventoryDataById(String inventoryId) {
//        return InventoryController.inventoryDataList.stream().filter(inventory ->
//                inventory.getId().equals(inventoryId)).findFirst().orElse(null);
    }

    public InventoryService() {
    }

	public Map<String,Object> addInventory(InventoryData request) {
		HashMap<String,Object> response = new  HashMap<String,Object>();
		request.setCreatedOn();
		request.setStatus(true);
		request.setUpdatedOn(LocalDateTime.now());
		inventoryList.add(request);
		System.out.println("add " + inventoryList.toString());
		response.put("statusCode", 200);
		response.put("statusMessage", "success");
	 	return response;
	}

	public InventoryData getInventory(String inventoryId) {
		System.out.println(inventoryList.toString());
		//return inventoryList.stream().filter(t ->t.getInventoryId().equals(inventoyId)).findAny().get();
		for(int i=0; i< inventoryList.size(); i++){
			InventoryData inventory = inventoryList.get(i);		
			if(inventory.getInventoryId().equals(inventoryId) && inventory.getStatus() != false) {
				return inventory;
			}
		}
		throw new InventoryNotFoundException(inventoryId);
	}

	public Map<String,Object> deleteInventory(String inventoryId) {
		HashMap<String,Object> response = new  HashMap<String,Object>();
		for(int i=0; i< inventoryList.size(); i++){
			InventoryData inventory = inventoryList.get(i);		
			if(inventory.getInventoryId().equals(inventoryId)) {
				inventory.setStatus(false);
				inventory.setUpdatedOn(LocalDateTime.now());
				response.put("statusCode", 200);
				response.put("statusMessage", "success");
				return response;
			}
		}
		throw new InventoryNotFoundException(inventoryId);
	}

	public List<InventoryData> listInventory(Map<String, Object> request) throws Exception{
		List<InventoryData> response = new ArrayList<InventoryData>();
		String fromDate = (String)request.get(InventoryConstant.FROM_DATE);
		String toDate = (String)request.get(InventoryConstant.TO_DATE);
		
		//String str = "yyyy-MM-dd HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(InventoryConstant.DATE_TIME_PATTERN);
		LocalDateTime fromDateTime = LocalDateTime.parse(fromDate, formatter);
		LocalDateTime toDateTime = LocalDateTime.parse(toDate, formatter);
       LOGGER.info(" fromDateTime " + fromDateTime);
       LOGGER.info(" toDateTime " + toDateTime);
		for (int i = 0; i < inventoryList.size(); i++) {
			InventoryData inventory = inventoryList.get(i);
			LOGGER.info(" inventory " + inventory + " inventory.getCreatedOn().withNano(0) " + inventory.getCreatedOn().withNano(0));
			System.out.println(" suneel " + inventory.getCreatedOn().withNano(0).compareTo(fromDateTime));
			if (inventory.getCreatedOn() != null && inventory.getCreatedOn().withNano(0).compareTo(fromDateTime) >= 0 && inventory.getUpdatedOn()!= null && toDateTime.compareTo(inventory.getUpdatedOn().withNano(0)) >= 0) {
				System.out.println(" 1 "); 
				response.add(inventory);
			} else if (inventory.getCreatedOn() != null && inventory.getCreatedOn().withNano(0).compareTo(fromDateTime) >= 0) {
				System.out.println(" 2 ");
				response.add(inventory);
			}else if (inventory.getUpdatedOn()!= null && toDateTime.compareTo(inventory.getUpdatedOn().withNano(0)) >= 0) {
				System.out.println(" 3 ");
				response.add(inventory);
			}
		}
		return response;
	}
}
