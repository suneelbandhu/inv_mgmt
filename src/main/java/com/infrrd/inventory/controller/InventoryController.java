package com.infrrd.inventory.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infrrd.inventory.model.InventoryData;
import com.infrrd.inventory.service.InventoryService;
import com.infrrd.inventory.util.InventoryAlreadyExistsException;
import com.infrrd.inventory.util.InventoryNotFoundException;

@RestController
public class InventoryController {

	@Autowired
    InventoryService inventoryService;

	public Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * API to create a new inventory
	 * @param InventoryData request
	 * @return Map<String,Object> response
	 * @throws InventoryAlreadyExistsException
	 * @see InventoryAlreadyExistsException
	 * @author Suneel Bandhu Patel
	 * @since 2020-02-23
	 */
    @PostMapping("/inventory")
    public Map<String,Object> createInventory(@RequestBody InventoryData request) throws InventoryAlreadyExistsException {
    	try {
    	return inventoryService.addInventory(request);
    	}catch(	Exception ex) {
    		ex.printStackTrace();
    		throw ex;
    	}
    }
    
    /** 
     * API to get the details of an inventory if exists
     * @param inventoyId
     * @return InventoryData inventoryData
     * @throws InventoryNotFoundException ex
     * @see InventoryNotFoundException
     * @author Suneel Bandhu Patel
     * @since 2020-02-23
     */
    @GetMapping("/inventory/{inventoyId}")
    public InventoryData getInventory(@PathVariable String inventoyId) throws InventoryNotFoundException {
    	try {
    	return inventoryService.getInventory(inventoyId);
    	}catch(	Exception ex) {
    		ex.printStackTrace();
    		throw ex;
    	}
    }
    
    /**
     * API to delete an inventory if exists in the given data set 
     * @param inventoyId
     * @return Map<String,Object> response
     * @throws InventoryNotFoundException ex
     * @see InventoryNotFoundException
     * @author Suneel Bandhu Patel
     * @since 2020-02-23
     */
    @DeleteMapping("/inventory/{inventoyId}")
    public Map<String,Object> deleteInventory(@PathVariable String inventoyId) throws InventoryNotFoundException {
    	try {
    	return inventoryService.deleteInventory(inventoyId);
    	}catch(	Exception ex) {
    		ex.printStackTrace();
    		throw ex;
    	}
    }
    
    /**
     * API to list the given inventory created/deleted on between the given range date
     * @param fromDate
     * @param toDate
     * @return List<InventoryData> response
     * @throws Exception
     * @author Suneel Bandhu Patel
     * @since 2020-02-23
     * @see InventoryData
     */
    @PostMapping("/inventory/report/{fromDate}/{toDate}")
    public List<InventoryData> listInventory(@PathVariable Date fromDate, @PathVariable Date toDate) throws Exception{
    	try {
        	return inventoryService.listInventory(fromDate,toDate);
        	}catch(	Exception ex) {
        		ex.printStackTrace();
        		throw ex;
        	}
    }   
    
    
}

