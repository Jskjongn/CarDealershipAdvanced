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

    // derived getters
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max){

        // creates new list to hold vehicles within a price range
        ArrayList<Vehicle> priceRangeList = new ArrayList<>();

        // for each vehicle in the inventory list
        for (Vehicle vehicle : inventory) {
            // gets vehicle price and compares it to user input min and max
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                // adds vehicle to new list
                priceRangeList.add(vehicle);
            }
        }
        // if the list is empty then it displays no matches made
        if (priceRangeList.isEmpty()){
            System.out.println("\nNo matches found in your price range!");
        }
        // returns new list of vehicles that match user input
        return priceRangeList;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model){

        ArrayList<Vehicle> makeModelList = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equals(make) && vehicle.getModel().equals(model)) {
                makeModelList.add(vehicle);
            }
        }

        if (makeModelList.isEmpty()){
            System.out.println("\nNo matches found for make and model!");
        }

        return makeModelList;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max){

        ArrayList<Vehicle> yearRangeList = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                yearRangeList.add(vehicle);
            }
        }

        if (yearRangeList.isEmpty()){
            System.out.println("\nNo matches found in your year range!");
        }

        return yearRangeList;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color){

        ArrayList<Vehicle> colorList = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equals(color)) {
                colorList.add(vehicle);
            }
        }

        if (colorList.isEmpty()){
            System.out.println("\nNo matches found for your color!");
        }

        return colorList;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max){

        ArrayList<Vehicle> mileageRangeList = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                mileageRangeList.add(vehicle);
            }
        }

        if (mileageRangeList.isEmpty()){
            System.out.println("\nNo matches found in your mileage range!");
        }

        return mileageRangeList;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType){

        ArrayList<Vehicle> vehicleTypeList = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equals(vehicleType)) {
                vehicleTypeList.add(vehicle);
            }
        }

        if (vehicleTypeList.isEmpty()){
            System.out.println("\nNo matches found for your type!");
        }

        return vehicleTypeList;
    }

    public Vehicle getVehicleByVin(int vin) {

        // loops through inventory and stores it
        for (int i = 0; i < inventory.size(); i++) {
            Vehicle vehicle = inventory.get(i);
            // if vehicle vin matches user input vin, it removes vehicle and returns it
            if (vehicle.getVin() == vin) {
                removeVehicle(vehicle);
                return vehicle;
            }
        }

        // if no vin is found
        return null;
    }

    // returns the whole inventory
    public ArrayList<Vehicle> getAllVehicles(){
        return inventory;
    }

    // ------------------------------------------------------------------------

    // other methods
    // adds a vehicle to the inventory
    public void addVehicle(Vehicle vehicle){

        inventory.add(vehicle);
    }

    // removes a vehicle from inventory
    public void removeVehicle(Vehicle vehicle){

        inventory.remove(vehicle);
    }
}
