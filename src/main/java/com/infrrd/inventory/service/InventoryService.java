package com.infrrd.inventory.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		
		//checking the uniqueness of inventory id in the list
		if(request != null && request.getInventoryId() !=null && inventoryList.size()>0) {
			InventoryData inventory = inventoryList.stream().filter(t -> t.getInventoryId().equals(request.getInventoryId())).findAny().get();
				if(inventory !=null) {
					throw new InventoryAlreadyExistsException(request.getInventoryId());
				}
		}
		
		HashMap<String,Object> response = new  HashMap<String,Object>();
		request.setCreatedOn();
		request.setStatus(true);
		request.setUpdatedOn(new Date());
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
			if(inventory.getInventoryId().equals(inventoryId) && inventory.getStatus() == true) {
				inventory.setStatus(false);
				inventory.setUpdatedOn(new Date());
				response.put("statusCode", 200);
				response.put("statusMessage", "success");
				return response;
			}
		}
		throw new InventoryNotFoundException(inventoryId);
	}

	public List<InventoryData> listInventory(Date fromDate,Date toDate) throws Exception{
		List<InventoryData> response = new ArrayList<InventoryData>();
		
		System.out.println(" fromDate =" + fromDate + " toDate = "+ toDate);
		for (int i = 0; i < inventoryList.size(); i++) {
			InventoryData inventory = inventoryList.get(i);
			if (fromDate != null && toDate != null && inventory.getCreatedOn() != null
					&& inventory.getUpdatedOn() != null && inventory.getCreatedOn().compareTo(fromDate) >= 0
					&& toDate.compareTo(inventory.getUpdatedOn()) >= 0) {
				System.out.println("1");
				response.add(inventory);
			}else if(fromDate != null && inventory.getCreatedOn() != null && inventory.getCreatedOn().compareTo(fromDate) >= 0) {
				System.out.println("2");
				response.add(inventory);
			}else if(toDate != null && inventory.getUpdatedOn() != null && toDate.compareTo(inventory.getUpdatedOn()) >= 0) {
				System.out.println("3");
				response.add(inventory);
			}
		}
		return response;
	}
}
