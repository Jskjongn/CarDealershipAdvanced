package com.pluralsight.dealership.models;

// abstract so a generic contract can't be made
public abstract class Contract {

    // properties of a contract
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;

    // constructor to create a contract
    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    // ------------------------------------------------------------------------

    // getters and setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    // ------------------------------------------------------------------------

    // abstract methods for different contracts to calculate
    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();
}
