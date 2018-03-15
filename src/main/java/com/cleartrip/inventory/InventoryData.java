package com.cleartrip.inventory;

import java.math.BigDecimal;

public class InventoryData implements Cloneable {
    private  String Id;
    private String name;
    private BigDecimal cost;
    private int count;

    public InventoryData(String id, String name, BigDecimal cost) {
        Id = id;
        this.name = name;
        this.cost = cost;
        this.count=1;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
