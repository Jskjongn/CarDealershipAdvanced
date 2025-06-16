package com.pluralsight.dealership.models;

import java.util.ArrayList;

public class Dealership {

    // properties of a dealership
    private int id;
    private String name;
    private String address;
    private String phone;

    // constructor
    public Dealership(int id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    // ------------------------------------------------------------------------

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("""
                
                ID: %d
                Name: %s
                Address: %s
                Phone: %s
                """, this.id, this.name, this.address, this.phone);
    }
}
