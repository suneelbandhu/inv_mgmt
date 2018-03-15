package com.cleartrip.inventory;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InventoryController {
    //no persistence or FS.. in memory inventory
    public static List<InventoryData> inventoryDataList = new ArrayList<>();

    //~~spring boot data repository
    InventoryDataInterface inventoryDataInterface = new InventoryDataInterface();

    @RequestMapping("/read")
    public String index() {
        InventoryApplication.logger.info("Reading inventories present in memory");
        //todo
        //pass it to thymeleaf template and render in good UI
        StringBuilder inventoryMessage = new StringBuilder();
        inventoryMessage.append("\n Here are the available inventories:::");
        inventoryDataList.forEach(eachInventory -> {
            inventoryMessage.append("\n Id=" + eachInventory.getId() + "  "
                    + "Name=" + eachInventory.getName() + "  " +
                    "Cost=" + eachInventory.getCost());
        });
        InventoryApplication.logger.info("Read inventories::: " + inventoryMessage.toString());
        return inventoryMessage.toString().isEmpty() ? "no inventories present" : inventoryMessage.toString();
    }

    @RequestMapping(value = "/create")
    public String createInventory() {
        //get params logic
        String id = "123";
        String name = "something";
        BigDecimal cost = new BigDecimal(123.45);
        InventoryData inventoryData = new InventoryData(id, name, cost);
        inventoryDataList.add(inventoryData);
        return "inventory created and added in-memory";
    }

    @RequestMapping(value = "/increase/{inventoryId}")
    public String inventoryIncrease(
            @PathVariable String inventoryId
    ) {
        String message = "";
        synchronized (InventoryController.class) {

            InventoryData inventoryData =inventoryDataInterface.getInventoryDataById(inventoryId);
            if ( inventoryData!= null) {
                //increase count-stock value
                inventoryData.setCount(inventoryData.getCount()+1);
                message="inventory increased for id="+inventoryId+" New stock available is "+ inventoryData.getCount();
            } else {
                message = "inventory with id " + inventoryId + " is not present in memory";
                InventoryApplication.logger.info(message);
            }
        }
        return  message;
    }

    @RequestMapping(value = "/decrease/{inventoryId}")
    public String inventoryDecrease(
            @PathVariable String inventoryId
    ) {
        String message = "";
        synchronized (InventoryController.class) {
            InventoryData inventoryData =inventoryDataInterface.getInventoryDataById(inventoryId);
            if ( inventoryData!= null) {
                //deccrease count-stock value
                if (inventoryData.getCount()<1){
                    message="inventory with id "+ inventoryId +" has run out of stock";
                }else{
                    inventoryData.setCount(inventoryData.getCount()-1);
                    message="inventory deccreased for id="+inventoryId+" New stock available is "+ inventoryData.getCount();
                }
            } else {
                message = "inventory with id " + inventoryId + " is not present in memory";
                InventoryApplication.logger.info(message);
            }
        }
        return  message;
    }



}

