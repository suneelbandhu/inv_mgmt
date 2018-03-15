package com.cleartrip.inventory;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InventoryController {
    //no persistence or FS.. in memory inventory
    public static List<InventoryData> inventoryDataList = new ArrayList<>();

    //~~spring boot data repository
    InventoryDataInterface inventoryDataInterface = new InventoryDataInterface();

    @RequestMapping("/report")
    public String report() {
        InventoryApplication.logger.info("Reading inventories present in memory");
        //todo
        //pass it to thymeleaf template and render in good UI
        StringBuilder inventoryMessage = new StringBuilder();
        inventoryMessage.append("\n Here are the available inventories:::");
        inventoryDataList.forEach(eachInventory -> {
            inventoryMessage.append("\n Id=" + eachInventory.getId() + "  "
                    + "Name=" + eachInventory.getName() + "  " +
                    "Cost=" + eachInventory.getCost()
            +"Count="+ eachInventory.getCount());
        });
        InventoryApplication.logger.info("Read inventories::: " + inventoryMessage.toString());
        return inventoryMessage.toString().isEmpty() ? "no inventories present" : inventoryMessage.toString();
    }

    @RequestMapping(value = "/create/{inventoryId}/{name}/{cost}")
    public String createInventory(
            @PathVariable String inventoryId,
            @PathVariable String name,
            @PathVariable float cost
    ) {
        if (inventoryDataInterface.getInventoryDataById(inventoryId)!=null){
            return "inventory  with id "+ inventoryId+ " already exists. Please update (increase or decrease)";
        }
        InventoryData inventoryData = new InventoryData(inventoryId, name, new BigDecimal(cost));
        inventoryDataList.add(inventoryData);
        //return read page.. (make it dynamic)
        return "inventory created";
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
        InventoryApplication.logger.info(message);
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
        InventoryApplication.logger.info(message);
        return  message;
    }
}

