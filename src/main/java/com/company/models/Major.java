package com.company.models;

public class Major extends Model {
    private String name;

    public Major(int id,String name) {
        super.setId(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
