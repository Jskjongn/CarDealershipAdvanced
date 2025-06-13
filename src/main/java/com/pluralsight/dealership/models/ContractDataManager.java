package com.pluralsight.dealership.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {

    private static final String FILE_PATH = "src/main/resources/contracts.csv";

    public void saveContract(Contract contract) {
        try {
            // creates file and buffered writer and passes in the file path and appends
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));

            // gets the vehicle from the contract and sets it to a vehicle
            Vehicle vehicle = contract.getVehicleSold();

            // if the contract is an instance of a sales contract then the line starts with sale
            if (contract instanceof SalesContract salesContract) {
                // concatenates the sales contract details and the vehicle being sold details
                String line = "SALE|" +
                        salesContract.getDate() + "|" + salesContract.getCustomerName() + "|" + salesContract.getCustomerEmail() + "|" +
                        vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice() + "|" +
                        salesContract.getSalesTaxAmount() + "|" + salesContract.getRecordingFee() + "|" + salesContract.getProcessingFee() + "|" +
                        salesContract.getTotalPrice() + "|" + salesContract.isFinanced() + "|" + salesContract.getMonthlyPayment();

                // writes the details in one line and a newline
                writer.write(line);
                writer.newLine();

                // if the contract is an instance of a lease contract then the line starts with sale
            } else if (contract instanceof LeaseContract leaseContract) {
                String line = "LEASE|" + leaseContract.getDate() + "|" + leaseContract.getCustomerName() + "|" + leaseContract.getCustomerEmail() + "|" +
                        vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice() + "|" +
                        leaseContract.getEndingValue() + "|" + leaseContract.getLeaseFee() + "|" +
                        leaseContract.getTotalPrice() + "|" + leaseContract.getMonthlyPayment();

                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing contracts file: " + e.getMessage());
        }
    }
}
