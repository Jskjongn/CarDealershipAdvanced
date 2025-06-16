package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private static BasicDataSource vehicleDataSource;

    public VehicleDao(BasicDataSource vehicleDataSource) {
        this.vehicleDataSource = vehicleDataSource;
    }

    public static List<Vehicle> getVehiclesByPrice(double min, double max) {

        // creates new list to hold vehicles within a price range
        List<Vehicle> priceRangeList = new ArrayList<>();

        try (
                Connection connection = vehicleDataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT
                            VIN
                            , Year
                            , Make
                            , Model
                            , Vehicle_Type
                            , Color
                            , Odometer
                            , Price
                        FROM
                            vehicles
                        WHERE
                            (Price BETWEEN ? AND ?) AND (SOLD = 'NOT SOLD')
                        ORDER BY
                            Price;
                        """)
        ) {
            preparedStatement.setDouble(1, min);
            preparedStatement.setDouble(2, max);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String vin = resultSet.getString("VIN");
                    int year = resultSet.getInt("Year");
                    String make = resultSet.getString("Make");
                    String model = resultSet.getString("Model");
                    String vehicleType = resultSet.getString("Vehicle_Type");
                    String color = resultSet.getString("Color");
                    int odometer = resultSet.getInt("Odometer");
                    double price = resultSet.getDouble("Price");

                    priceRangeList.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }

            }
        } catch (SQLException e) {
            System.out.println("Error searching by price: " + e.getMessage());
        }

        // if the list is empty then it displays no matches made
        if (priceRangeList.isEmpty()) {
            System.out.printf("\nNo matches found in %.2f - %.2f range!\n", min, max);
        }

        return priceRangeList;
    }

    public static List<Vehicle> getVehiclesByMakeModel(String vehicleMake, String vehicleModel) {

        // creates new list to hold vehicles that match a make and model
        List<Vehicle> makeModelList = new ArrayList<>();

        try (
                Connection connection = vehicleDataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT
                            VIN
                            , Year
                            , Make
                            , Model
                            , Vehicle_Type
                            , Color
                            , Odometer
                            , Price
                        FROM
                            vehicles
                        WHERE
                            (Make LIKE ? AND Model LIKE ?) AND (SOLD = 'NOT SOLD');
                        """)
        ) {
            preparedStatement.setString(1, vehicleMake + "%");
            preparedStatement.setString(2, vehicleModel + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String vin = resultSet.getString("VIN");
                    int year = resultSet.getInt("Year");
                    String make = resultSet.getString("Make");
                    String model = resultSet.getString("Model");
                    String vehicleType = resultSet.getString("Vehicle_Type");
                    String color = resultSet.getString("Color");
                    int odometer = resultSet.getInt("Odometer");
                    double price = resultSet.getDouble("Price");

                    makeModelList.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }

            }
        } catch (SQLException e) {
            System.out.println("Error searching by make and model: " + e.getMessage());
        }

        // if the list is empty then it displays no matches made
        if (makeModelList.isEmpty()) {
            System.out.println("\nNo matches found for " + vehicleMake + " " + vehicleModel + "!");
        }

        return makeModelList;
    }

    public static List<Vehicle> getVehiclesByYear(int min, int max) {

        // creates new list to hold vehicles within a year range
        List<Vehicle> yearRangeList = new ArrayList<>();

        try (
                Connection connection = vehicleDataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT
                            VIN
                            , Year
                            , Make
                            , Model
                            , Vehicle_Type
                            , Color
                            , Odometer
                            , Price
                        FROM
                            vehicles
                        WHERE
                            (Year BETWEEN ? AND ?) AND (SOLD = 'NOT SOLD')
                        ORDER BY
                            Year;
                        """)
        ) {
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String vin = resultSet.getString("VIN");
                    int year = resultSet.getInt("Year");
                    String make = resultSet.getString("Make");
                    String model = resultSet.getString("Model");
                    String vehicleType = resultSet.getString("Vehicle_Type");
                    String color = resultSet.getString("Color");
                    int odometer = resultSet.getInt("Odometer");
                    double price = resultSet.getDouble("Price");

                    yearRangeList.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }

            }
        } catch (SQLException e) {
            System.out.println("Error searching by year: " + e.getMessage());
        }

        // if the list is empty then it displays no matches made
        if (yearRangeList.isEmpty()) {
            System.out.println("\nNo matches found in year " + min + " - " + max + " range!");
        }

        return yearRangeList;
    }

    public static List<Vehicle> getVehiclesByColor(String vehicleColor) {

        // creates new list to hold vehicles that match a color
        List<Vehicle> colorList = new ArrayList<>();

        try (
                Connection connection = vehicleDataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT
                            VIN
                            , Year
                            , Make
                            , Model
                            , Vehicle_Type
                            , Color
                            , Odometer
                            , Price
                        FROM
                            vehicles
                        WHERE
                            (Color LIKE ?) AND (SOLD = 'NOT SOLD');
                        """)
        ) {
            preparedStatement.setString(1, vehicleColor + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String vin = resultSet.getString("VIN");
                    int year = resultSet.getInt("Year");
                    String make = resultSet.getString("Make");
                    String model = resultSet.getString("Model");
                    String vehicleType = resultSet.getString("Vehicle_Type");
                    String color = resultSet.getString("Color");
                    int odometer = resultSet.getInt("Odometer");
                    double price = resultSet.getDouble("Price");

                    colorList.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }

            }
        } catch (SQLException e) {
            System.out.println("Error searching by color: " + e.getMessage());
        }

        // if the list is empty then it displays no matches made
        if (colorList.isEmpty()) {
            System.out.println("\nNo matches found for " + vehicleColor + "!");
        }

        return colorList;
    }

    public static List<Vehicle> getVehiclesByMileage(int min, int max) {

        // creates new list to hold vehicles within a mileage range
        List<Vehicle> mileageRangeList = new ArrayList<>();

        try (
                Connection connection = vehicleDataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT
                            VIN
                            , Year
                            , Make
                            , Model
                            , Vehicle_Type
                            , Color
                            , Odometer
                            , Price
                        FROM
                            vehicles
                        WHERE
                            (Odometer BETWEEN ? AND ?) AND (SOLD = 'NOT SOLD')
                        ORDER BY
                            Odometer;
                        """)
        ) {
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String vin = resultSet.getString("VIN");
                    int year = resultSet.getInt("Year");
                    String make = resultSet.getString("Make");
                    String model = resultSet.getString("Model");
                    String vehicleType = resultSet.getString("Vehicle_Type");
                    String color = resultSet.getString("Color");
                    int odometer = resultSet.getInt("Odometer");
                    double price = resultSet.getDouble("Price");

                    mileageRangeList.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }

            }
        } catch (SQLException e) {
            System.out.println("Error searching by mileage: " + e.getMessage());
        }

        // if the list is empty then it displays no matches made
        if (mileageRangeList.isEmpty()) {
            System.out.println("\nNo matches found in mileage " + min + " - " + max + " range!");
        }

        return mileageRangeList;
    }

    public static List<Vehicle> getVehiclesByType(String type) {

        // creates new list to hold vehicles that match a color
        List<Vehicle> vehicleTypeList = new ArrayList<>();

        try (
                Connection connection = vehicleDataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT
                            VIN
                            , Year
                            , Make
                            , Model
                            , Vehicle_Type
                            , Color
                            , Odometer
                            , Price
                        FROM
                            vehicles
                        WHERE
                            (Vehicle_Type LIKE ?) AND (SOLD = 'NOT SOLD');
                        """)
        ) {
            preparedStatement.setString(1, type + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String vin = resultSet.getString("VIN");
                    int year = resultSet.getInt("Year");
                    String make = resultSet.getString("Make");
                    String model = resultSet.getString("Model");
                    String vehicleType = resultSet.getString("Vehicle_Type");
                    String color = resultSet.getString("Color");
                    int odometer = resultSet.getInt("Odometer");
                    double price = resultSet.getDouble("Price");

                    vehicleTypeList.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }

            }
        } catch (SQLException e) {
            System.out.println("Error searching by type: " + e.getMessage());
        }

        // if the list is empty then it displays no matches made
        if (vehicleTypeList.isEmpty()) {
            System.out.println("\nNo matches found for " + vehicleTypeList + "!");
        }

        return vehicleTypeList;
    }

    public static List<Vehicle> getAllVehicles() {

        // creates new list to hold vehicles that match a color
        List<Vehicle> allVehiclesList = new ArrayList<>();

        try (
                Connection connection = vehicleDataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT
                            *
                        FROM
                            vehicles
                        WHERE
                            SOLD = 'NOT SOLD'
                        ORDER BY
                            Year;
                        """);

                ResultSet resultSet = preparedStatement.executeQuery();
        ) {

            while (resultSet.next()) {
                String vin = resultSet.getString("VIN");
                int year = resultSet.getInt("Year");
                String make = resultSet.getString("Make");
                String model = resultSet.getString("Model");
                String vehicleType = resultSet.getString("Vehicle_Type");
                String color = resultSet.getString("Color");
                int odometer = resultSet.getInt("Odometer");
                double price = resultSet.getDouble("Price");

                allVehiclesList.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
            }

        } catch (SQLException e) {
            System.out.println("Error searching all vehicles: " + e.getMessage());
        }

        return allVehiclesList;
    }

    public void addVehicle(String vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {

        try (
                Connection connection = vehicleDataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        INSERT INTO `vehicles` (`VIN`, `Year`, `Make`, `Model`, `Vehicle_Type`, `Color`, `Odometer`, `Price`)
                        VALUES
                        (?, ?, ?, ?, ?, ?, ?, ?);
                        """);
        ) {
            preparedStatement.setString(1, vin);
            preparedStatement.setInt(2, year);
            preparedStatement.setString(3, make);
            preparedStatement.setString(4, model);
            preparedStatement.setString(5, vehicleType);
            preparedStatement.setString(6, color);
            preparedStatement.setInt(7, odometer);
            preparedStatement.setDouble(8, price);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding vehicle: " + e.getMessage());
        }
    }

    public void removeVehicle(String vin) {

        try (
                Connection connection = vehicleDataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        DELETE FROM vehicles
                        WHERE VIN = ?;
                        """);
        ) {
            preparedStatement.setString(1, vin);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error removing vehicle: " + e.getMessage());
        }
    }

    public Vehicle getVehicleByVin(String vin) {

        // loops through all vehicles and stores it
        for (int i = 0; i < getAllVehicles().size(); i++) {
            Vehicle vehicle = getAllVehicles().get(i);
            // if vehicle vin matches user input vin, returns vehicle object
            if (vehicle.getVin().equals(vin)) {
                return vehicle;
            }
        }
        // if no vin is found
        return null;
    }
}
