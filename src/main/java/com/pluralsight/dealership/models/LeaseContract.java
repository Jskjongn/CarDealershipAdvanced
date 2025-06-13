package com.pluralsight.dealership.models;

// subclass that extends parent class
public class LeaseContract extends Contract {

    // properties of a lease
    private double endingValue;
    private double leaseFee;

    // constructor to create a lease
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        this.endingValue = this.getVehicleSold().getPrice() / 2; // half the value of the vehicle price
        this.leaseFee = this.getVehicleSold().getPrice() * 0.07; // lease fee of 7% of the vehicle price
    }

    // ------------------------------------------------------------------------

    // overridden abstract methods
    @Override
    public double getTotalPrice() {

        // multiples the ending value by 4% interest of 36 months (3 years) plus the lease fee
        return this.endingValue * (1 + 0.04 * 3) + this.leaseFee;
    }

    @Override
    public double getMonthlyPayment() {

        // price divided by number of months
        return this.getTotalPrice() / 36;
    }

    // ------------------------------------------------------------------------

    // getters and setters
    public double getEndingValue() {
        return endingValue;
    }

    public void setEndingValue(double endingValue) {
        this.endingValue = endingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }
}
