package com.pluralsight.dealership.models;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {

    private static final String FILE_PATH = "src/main/resources/inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = null;

        try {
            // creates file and buffered reader and passes in the file path
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line = reader.readLine();

            // if the first line is not null
            if (line != null) {
                // splits the dealership details at the pipe and assigns the indexes to create a new dealership
                String[] dealershipParts = line.split("\\|");
                String name = dealershipParts[0];
                String address = dealershipParts[1];
                String phone = dealershipParts[2];
                dealership = new Dealership(name, address, phone);
            }

            // reads the rest of the file until null
            while ((line = reader.readLine()) != null) {
                // splits the parts of the file at the pipe and assigns the index to vehicle details
                String[] vehicleParts = line.split("\\|");
                int vin = Integer.parseInt(vehicleParts[0]);
                int year = Integer.parseInt(vehicleParts[1]);
                String make = vehicleParts[2];
                String model = vehicleParts[3];
                String type = vehicleParts[4];
                String color = vehicleParts[5];
                int odometer = Integer.parseInt(vehicleParts[6]);
                double price = Double.parseDouble(vehicleParts[7]);

                // takes the parts of the vehicle from the file and puts them together creating a new vehicle
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);

                // adds the vehicle to the dealership inventory
                dealership.addVehicle(vehicle);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading dealership file: " + e.getMessage());
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try {
            // creates file and buffered writer and passes in the file path
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

            // writes file's header row using the dealership properties
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            // writes a new line
            writer.newLine();

            // gets all the vehicles from the inventory list and stores them
            ArrayList<Vehicle> inventory = dealership.getAllVehicles();

            // loops through new list and writes each vehicle to the file
            for (Vehicle vehicle : inventory) {
                String line = vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|" + vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer() + "|" + vehicle.getPrice();

                // writes the line with all the vehicle details and then writes a new line for the next vehicle
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing dealership file: " + e.getMessage());
        }
    }
}
