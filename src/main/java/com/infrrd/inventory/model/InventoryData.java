package com.infrrd.inventory.model;

import java.math.BigDecimal;
import java.util.Date;

public class InventoryData {

	private String inventoryId;
    private String name;
    private BigDecimal cost;
    private int count;
    private Date createdOn;
    private Date updatedOn;
    private boolean status;


public InventoryData() {
	
	}

public InventoryData(String inventoryId, String name, BigDecimal cost, int count, Date createdOn,
		Date updatedOn, boolean status) {
	super();
	this.inventoryId = inventoryId;
	this.name = name;
	this.cost = cost;
	this.count = count;
	this.createdOn = createdOn;
	this.updatedOn = updatedOn;
	this.status = status;
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


public Date getCreatedOn() {
	return createdOn;
}


public void setCreatedOn(Date createdOn) {
	this.createdOn = createdOn;
}


public Date getUpdatedOn() {
	return updatedOn;
}


public void setUpdatedOn(Date updatedOn) {
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
