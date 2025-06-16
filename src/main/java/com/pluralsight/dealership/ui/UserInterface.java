package com.pluralsight.dealership.ui;

import com.pluralsight.dealership.dao.SalesContractDao;
import com.pluralsight.dealership.dao.VehicleDao;
import com.pluralsight.dealership.models.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    static Scanner userInput = new Scanner(System.in);
    static BasicDataSource dataSource = null;
    static VehicleDao vehicleDao = null;
    static SalesContractDao salesContractDao = null;

    // private constructor so this class can't be instantiated
    private UserInterface(){
    }

    // displays options to user
    public static void display(){

        // creates datasource
        openDataSource();

        // creates data managers
        vehicleDao = new VehicleDao(dataSource);
        salesContractDao = new SalesContractDao(dataSource);

        // loop for home menu
        boolean menu = true;
        while (menu){

            // menu list
            System.out.print("""
                    ---------------------------------------------------
                    1 - Find vehicles within a price range
                    2 - Find vehicles by make / model
                    3 - Find vehicles by year range
                    4 - Find vehicles by color
                    5 - Find vehicles by mileage range
                    6 - Find vehicles by vehicle type
                    7 - List ALL vehicles
                    8 - Add a vehicle
                    9 - Remove a vehicle
                    10 - Sell/Lease a vehicle
                    99 - Quit
                    ---------------------------------------------------
                    """);

            // asks user to choose and stores input for switch statement
            System.out.print("Enter how you would like to view vehicles: ");
            int menuChoice = userInput.nextInt();
            userInput.nextLine();

            switch (menuChoice) {
                // finds by price range
                case 1:
                    processGetByPriceRequest();
                    break;
                // finds my make and model
                case 2:
                    processGetByMakeModelRequest();
                    break;
                // finds by year
                case 3:
                    processGetByYearRequest();
                    break;
                // finds by color
                case 4:
                    processGetByColorRequest();
                    break;
                // find by mileage
                case 5:
                    processGetByMileageRequest();
                    break;
                // find by type
                case 6:
                    processGetByVehicleRequest();
                    break;
                // lists all
                case 7:
                    processGetByAllVehiclesRequest();
                    break;
                // add vehicle
                case 8:
                    processAddVehicleRequest();
                    break;
                // remove vehicle
                case 9:
                    processRemoveVehicleRequest();
                    break;
                // sell or lease vehicle
                case 10:
                    processSellOrLeaseVehicleRequest();
                    break;
                // quits app
                case 99:
                    System.out.println("\nNow stopping application, thank you.");
                    closeDataSource();
                    menu = false;
                    break;
                // when user doesn't choose a valid option
                default:
                    System.out.println("\nNot a valid choice - Please enter 1-9 or 99 to quit");
                    break;
            }
        }
    }

    // ------------------------------------------------------------------------

    // finds vehicle by price range
    public static void processGetByPriceRequest(){

        // default values
        double min = 0;
        double max = 0;
        boolean validated = false;

        // prompts user question
        System.out.println("\nEnter the vehicle price");

        // loop to keep asking until input is valid
        while (!validated) {

            // won't store user input unless it's a double
            System.out.print("Min: ");
            if (userInput.hasNextDouble()) {
                min = userInput.nextDouble();
            } else {
                System.out.println("\nPlease enter a numeric number!");
                userInput.nextLine();
                continue;
            }

            // won't store user input unless it's a double
            System.out.print("Max: ");
            if (userInput.hasNextDouble()) {
                max = userInput.nextDouble();
            } else {
                System.out.println("\nPlease enter a numeric number!");
                userInput.nextLine();
                continue;
            }

            // checks if min is not greater than max
            if (min > max) {
                System.out.println("Minimum price must not be greater than maximum price!");
            } else {
                // breaks loop when all input is valid
                validated = true;
            }

        }

        // displays user's price range
        System.out.printf("Here are vehicles listed from $%.2f to $%.2f\n\n", min, max);

        // calls filter method to filter through using user input and puts into new list
        List<Vehicle> priceRangeList = vehicleDao.getVehiclesByPrice(min, max);

        // displays vehicles by user's price range by looping through new list
        displayVehicles(priceRangeList);
    }

    // finds vehicle by make and model
    public static void processGetByMakeModelRequest(){

        // default values
        String make = "";
        String model = "";

        // prompts user a question
        System.out.println("\nEnter the vehicle make and model");

        while (true) {

            // stores user's make input if not empty
            System.out.print("\nMake: ");
            make = userInput.nextLine().trim();

            if (make.isEmpty()) {
                System.out.println("\nMake is empty, please enter a make!");
                continue;
            }

            // stores user's model input if not empty
            System.out.print("Model: ");
            model = userInput.nextLine().trim();
            if (model.isEmpty()) {
                System.out.println("\nModel is empty, please enter a model!");
                continue;
            }

            // checks if the make and model are not the same
            if (make.equalsIgnoreCase(model)) {
                System.out.println("\nMake and model cannot be the same!");
            } else {
                // takes user input and makes first letter at index 0 capital and the rest lowercase
                make = make.substring(0, 1).toUpperCase() + make.substring(1).toLowerCase();
                model = model.substring(0, 1).toUpperCase() + model.substring(1).toLowerCase();
                break;
            }

        }

        // displays user's make and model
        System.out.printf("Here are vehicles listed as %s %s", make, model + ":\n\n");

        // calls filter method to filter through using user input and puts into new list
        List<Vehicle> makeModelList = vehicleDao.getVehiclesByMakeModel(make, model);

        // displays vehicles by user's make and model by looping through new list
        displayVehicles(makeModelList);
    }

    // finds vehicle by year
    public static void processGetByYearRequest(){

        int min = 0;
        int max = 0;
        boolean validated = false;

        System.out.println("\nEnter the vehicle years");

        while (!validated) {

            System.out.print("Min: ");
            if (userInput.hasNextInt()) {
                min = userInput.nextInt();
            } else {
                System.out.println("\nPlease enter a numeric number!");
                userInput.nextLine();
                continue;
            }

            System.out.print("Max: ");
            if (userInput.hasNextInt()) {
                max = userInput.nextInt();
            } else {
                System.out.println("\nPlease enter a numeric number!");
                userInput.nextLine();
                continue;
            }

            if (min > max) {
                System.out.println("Minimum year must not be greater than maximum year!");
            } else {
                validated = true;
            }

        }

        // displays user's price range
        System.out.printf("Here are vehicles listed from %d to %d\n\n", min, max);

        // calls filter method to filter through using user input and puts into new list
        List<Vehicle> yearRangeList = vehicleDao.getVehiclesByYear(min, max);

        // displays vehicles by user's year range by looping through new list
        displayVehicles(yearRangeList);

    }

    // finds vehicle by color
    public static void processGetByColorRequest(){

        // default values
        String color = "";

        // prompts user a question
        System.out.println("\nEnter the vehicle make and model");

        while (true) {

            // stores user's color input if not empty
            System.out.print("\nColor: ");
            color = userInput.nextLine().trim();
            if (color.isEmpty()) {
                System.out.println("\nColor is empty, please enter a color!");
            } else {
                // takes user input and capitalizes the first letter
                color = color.substring(0, 1).toUpperCase() + color.substring(1).toLowerCase();
                break;
            }
        }

        // displays user's color
        System.out.printf("Here are vehicles with the color %s\n\n", color);

        // calls filter method to filter through using user input and puts into new list
        List<Vehicle> colorList = vehicleDao.getVehiclesByColor(color);

        // displays vehicles by user's color by looping through new list
        displayVehicles(colorList);
    }

    // finds vehicle by mileage
    public static void processGetByMileageRequest(){

        int min = 0;
        int max = 0;
        boolean validated = false;

        System.out.println("\nEnter the vehicle mileage range");

        while (!validated) {

            System.out.print("Min: ");
            if (userInput.hasNextInt()) {
                min = userInput.nextInt();
            } else {
                System.out.println("\nPlease enter a numeric number!");
                userInput.nextLine();
                continue;
            }

            System.out.print("Max: ");
            if (userInput.hasNextInt()) {
                max = userInput.nextInt();
            } else {
                System.out.println("\nPlease enter a numeric number!");
                userInput.nextLine();
                continue;
            }

            if (min > max) {
                System.out.println("Minimum mileage must not be greater than maximum mileage!");
            } else {
                validated = true;
            }

        }

        // displays user's price range
        System.out.printf("Here are vehicles listed from %d to %d miles\n\n", min, max);

        // calls filter method to filter through using user input and puts into new list
        List<Vehicle> mileageList = vehicleDao.getVehiclesByMileage(min, max);

        // displays vehicles by user's year range by looping through new list
        displayVehicles(mileageList);
    }

    // finds vehicle by vehicle type
    public static void processGetByVehicleRequest(){

        // default values
        String type = "";

        // prompts user a question
        System.out.println("\nEnter the vehicle body style (sedan, coupe, crossover, truck, van)");

        while (true) {

            // stores user's type input if not empty
            System.out.print("\nType: ");
            type = userInput.nextLine().trim();
            if (type.isEmpty()) {
                System.out.println("\nType is empty, please enter a type!");
            } else {
                if (type.equalsIgnoreCase("SUV")){
                    type = type.toUpperCase();
                }
                break;
            }
        }

        // displays user's vehicle type
        System.out.printf("Here are vehicles with the body type %s\n\n", type);

        // calls filter method to filter through using user input and puts into new list
        List<Vehicle> vehicleTypeList = vehicleDao.getVehiclesByType(type);

        // displays vehicles by user's type by looping through new list
        displayVehicles(vehicleTypeList);
    }

    // finds all vehicles
    public static void processGetByAllVehiclesRequest(){

        System.out.print("\n");

        // calls method of grabbing all vehicles and stores it into new list
        List<Vehicle> allVehiclesList = vehicleDao.getAllVehicles();

        // displays all vehicles by looping through new list
        displayVehicles(allVehiclesList);
    }

    // adds a vehicle
    public static void processAddVehicleRequest(){

        // prompts user for details of new vehicle to add
        System.out.println("Enter new vehicle details to add to inventory");

        System.out.print("VIN: ");
        String vin = userInput.nextLine();

        System.out.print("Year: ");
        int year = userInput.nextInt();
        userInput.nextLine();

        System.out.print("Make: ");
        String make = userInput.nextLine();

        System.out.print("Model: ");
        String model = userInput.nextLine();

        System.out.print("Type: ");
        String type = userInput.nextLine();

        System.out.print("Color: ");
        String color = userInput.nextLine();

        System.out.print("Odometer: ");
        int odometer = userInput.nextInt();
        userInput.nextLine();

        System.out.print("Price: ");
        double price = userInput.nextDouble();
        userInput.nextLine();

        // adds vehicle
        vehicleDao.addVehicle(vin, year, make, model, type, color, odometer, price);

        // displays the vehicle user added
        System.out.printf("New vehicle: %s|%d|%s|%s|%s|%s|%d|$%.2f added!\n",
                vin, year, make, model, type, color, odometer, price);
    }

    // removes a vehicle
    public static void processRemoveVehicleRequest(){

        // prompts user for vehicle to remove
        System.out.println("Enter vehicle VIN to remove from inventory");

        System.out.print("VIN: ");
        String vin = userInput.nextLine();

        // removes vehicle
        vehicleDao.removeVehicle(vin);

        // displays the vehicle was removed
        System.out.printf("Vehicle with VIN %s was removed!\n", vin);
    }

    // sell or lease a vehicle
    public static void processSellOrLeaseVehicleRequest() {

        // prompts user to either sell or lease a vehicle
        System.out.print("Would you like to Sell or Lease a vehicle: ");
        String choice = userInput.nextLine().trim().toUpperCase();

        // uses user input for switch
        switch (choice) {
            case "SELL":

                // if user chose sell then user enters customer details
                System.out.println("Enter customer details for this Sale: ");

                // gets current date of sale
                LocalDate currentDate = LocalDate.now();

                System.out.print("Customer Name: ");
                String sellName = userInput.nextLine().trim();

                System.out.print("Customer Email: ");
                String sellEmail = userInput.nextLine().trim();

                // user enters vin of vehicle and gets stored and then calls dealership method
                System.out.print("Vehicle VIN: ");
                String sellVinInput = userInput.nextLine().trim();
                // finds the vin and stores it in a vehicle class variable
                Vehicle sellVin = vehicleDao.getVehicleByVin(sellVinInput);

                // prompts user if its financed or not
                System.out.print("Financed (YES/NO): ");
                String financed = userInput.nextLine().trim().toUpperCase();

                // defaults to no but becomes true if yes
                boolean isFinanced = financed.equals("YES");

                SalesContract newSale = new SalesContract(currentDate, sellName, sellEmail, sellVin, isFinanced);

                // creates new sales contract from user input
                salesContractDao.newSalesContract(newSale, sellVinInput);

                // displays vehicle sold
                System.out.print("Vehicle: " + sellVin + " was sold successfully!\n");

                break;
            case "LEASE":

                // if user chose lease then user enters customer details
                System.out.println("Enter customer details for this Lease: ");
                System.out.print("Date (yyyy-MM-DD): ");
                String leaseDate = userInput.nextLine();

                System.out.print("Customer Name: ");
                String leaseName = userInput.nextLine();

                System.out.print("Customer Email: ");
                String leaseEmail = userInput.nextLine();

                // user enters vin of vehicle and gets stored and then calls dealership method
                System.out.print("Vehicle VIN: ");
                String leaseVinInput = userInput.nextLine();
//                // finds the vin and stores it in a vehicle class variable
//                Vehicle leaseVin = dealership.getVehicleByVin(leaseVinInput);
//
//                // if no vin is found
//                if (leaseVin == null) {
//                    System.out.println("Vehicle with VIN: " + leaseVinInput + " was not found.");
//                    break;
//                }
//
//                // creates new lease contract from user input
//                LeaseContract newLease = new LeaseContract(leaseDate, leaseName, leaseEmail, leaseVin);
//
//                // displays vehicle leased
//                System.out.print("Vehicle: " + leaseVin + " was leased successfully!\n");

                break;
            default:
                // prompts user to either enter sell or lease
                System.out.println("Please enter either Sell or Lease!");
        }
    }

    // ------------------------------------------------------------------------

    // other methods
    private static void openDataSource(){
        // prompts user for password to database
        System.out.print("Username: root\nPassword: ");
        String password = userInput.nextLine().trim();

        // creates the datasource
        dataSource = new BasicDataSource();

        // sets url with username and password
        dataSource.setUrl("jdbc:mysql://localhost:3306/cardealership");
        dataSource.setUsername("root");
        dataSource.setPassword(password);
    }

    // closes datasource
    public static void closeDataSource() {
        try {
            dataSource.close();
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

    // utility to use in other methods for displaying the vehicles list
    private static void displayVehicles(List<Vehicle> vehicleList){

        // creates list and assigns to a variable to display the list
        for (Vehicle filteredVehicle : vehicleList) {
            System.out.println(filteredVehicle);
        }
    }
}
