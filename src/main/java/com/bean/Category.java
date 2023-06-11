package com.bean;

public class Category {
    private String cId;
    private String cName;

    public Category() {
    }

    public Category(String cId, String cName) {
        this.cId = cId;
        this.cName = cName;
    }

    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }
}
