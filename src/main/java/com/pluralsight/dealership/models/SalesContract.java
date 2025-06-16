package com.pluralsight.dealership.models;

import java.time.LocalDate;

// subclass that extends parent class
public class SalesContract extends Contract {

    // properties of a sales contract
    private double salesTaxAmount;
    private int recordingFee;
    private final int processingFee;
    private boolean financed;

    // constructor to create a sales contract
    public SalesContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold, boolean financed) {
        super(date, customerName, customerEmail, vehicleSold);
        this.financed = financed;
        this.salesTaxAmount = (0.05 * this.getVehicleSold().getPrice()); // 5% sales tax
        this.recordingFee = 100; // constant $100 recording fee
        this.processingFee = (this.getVehicleSold().getPrice() < 10000) ? 295 : 495; // if vehicle is under $10,000 then $295 processing fee else its $495
    }

    // ------------------------------------------------------------------------

    // overridden abstract methods
    @Override
    public double getTotalPrice() {

        // if vehicle is $10,000 or more has an interest rate of 4.25% for 48 months (4 years) else 5.25% for 24 months (2 years)
        double interestRate = (this.getVehicleSold().getPrice() >= 10000) ? (0.0425 * 4) : (0.0525 * 2);
        // adds all the properties and price of the vehicle
        double totalPrice = (this.getVehicleSold().getPrice() + this.salesTaxAmount + this.recordingFee + this.processingFee);

        // if financed then the interest rate applies
        if (financed) {
            return totalPrice * (1 + interestRate);
        }

        // no finance then no interest rate
        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {

        // if financed and over $10,000 then gets monthly payment by dividing price by 48 months else it's divided by 24 months
        if (financed) {
            return (this.getVehicleSold().getPrice() >= 10000) ? (this.getTotalPrice() / 48) : (this.getTotalPrice() / 24);
        }

        // no finance then no monthly payment
        return 0;
    }

    // ------------------------------------------------------------------------

    // getters and setters
    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public int getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(int recordingFee) {
        this.recordingFee = recordingFee;
    }

    public int getProcessingFee() {
        return processingFee;
    }

    public boolean isFinanced() {
        return financed;
    }

    public void setFinanced(boolean financed) {
        this.financed = financed;
    }
}
