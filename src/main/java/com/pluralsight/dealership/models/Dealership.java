package com.pluralsight.dealership.models;

import java.util.ArrayList;

public class Dealership {

    // properties of a dealership
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    // constructor
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        inventory = new ArrayList<>();
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

    // ------------------------------------------------------------------------

    public Vehicle getVehicleByVin(String vin) {

        // loops through inventory and stores it
        for (int i = 0; i < inventory.size(); i++) {
            Vehicle vehicle = inventory.get(i);
            // if vehicle vin matches user input vin, it removes vehicle and returns it
            if (vehicle.getVin().equals(vin)) {
                removeVehicle(vehicle);
                return vehicle;
            }
        }

        // if no vin is found
        return null;
    }
}
