package com.infrrd.inventory.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InventoryData implements Cloneable {
    private String inventoryId;
    private String name;
    private BigDecimal cost;
    private int count;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private boolean status;


public InventoryData() {
	
	}


public String getInventoryId() {
	return inventoryId;
}


public void setInventoryId(String inventoryId) {
	this.inventoryId = inventoryId;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public BigDecimal getCost() {
	return cost;
}


public void setCost(BigDecimal cost) {
	this.cost = cost;
}


public int getCount() {
	return count;
}


public void setCount(int count) {
	this.count = count;
}


public LocalDateTime getCreatedOn() {
	return createdOn;
}


public void setCreatedOn() {
	this.createdOn = LocalDateTime.now();
}


public LocalDateTime getUpdatedOn() {
	return updatedOn;
}


public void setUpdatedOn(LocalDateTime updatedOn) {
	this.updatedOn = updatedOn;
}


public boolean getStatus() {
	return status;
}


public void setStatus(boolean status) {
	this.status = status;
}


@Override
public String toString() {
	return "InventoryData [inventoryId=" + inventoryId + ", name=" + name + ", cost=" + cost + ", count=" + count
			+ ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", status=" + status + "]";
}



}
