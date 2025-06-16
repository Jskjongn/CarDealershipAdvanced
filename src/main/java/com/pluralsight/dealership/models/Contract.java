package com.pluralsight.dealership.models;

import java.time.LocalDate;

// abstract so a generic contract can't be made
public abstract class Contract {

    // properties of a contract
    private LocalDate date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;

    // constructor to create a contract
    public Contract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    // ------------------------------------------------------------------------

    // getters and setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
