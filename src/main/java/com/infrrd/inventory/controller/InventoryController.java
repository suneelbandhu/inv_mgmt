package com.infrrd.inventory.controller;

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

@RestController
public class InventoryController {

	@Autowired
    InventoryService inventoryService;

	public Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
    @PostMapping("/inventory")
    public Map<String,Object> createInventory(@RequestBody InventoryData request) throws Exception {
    	try {
    	return inventoryService.addInventory(request);
    	}catch(	Exception ex) {
    		ex.printStackTrace();
    		throw ex;
    	}
    }
    
    @GetMapping("/inventory/{inventoyId}")
    public Object getInventory(@PathVariable String inventoyId) throws Exception {
    	try {
    	return inventoryService.getInventory(inventoyId);
    	}catch(	Exception ex) {
    		ex.printStackTrace();
    		throw ex;
    	}
    }
    
    @DeleteMapping("/inventory/{inventoyId}")
    public Map<String,Object> deleteInventory(@PathVariable String inventoyId) throws Exception {
    	try {
    	return inventoryService.deleteInventory(inventoyId);
    	}catch(	Exception ex) {
    		ex.printStackTrace();
    		throw ex;
    	}
    }
    
    @PostMapping("/inventory/report")
    public List<InventoryData> listInventory(@RequestBody Map<String,Object> request) throws Exception{
    	try {
        	return inventoryService.listInventory(request);
        	}catch(	Exception ex) {
        		ex.printStackTrace();
        		throw ex;
        	}
    }   
    
    
}

