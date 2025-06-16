package com.pluralsight.dealership.dao;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {

    private BasicDataSource inventoryDataSource;

    public InventoryDao(BasicDataSource inventoryDataSource) {
        this.inventoryDataSource = inventoryDataSource;
    }

    public void addVehicleToInventory(int id, String vin) {

        try (
                Connection connection = inventoryDataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        INSERT INTO `inventory` (`Dealership_ID`, `VIN`)
                        VALUES
                        (?, ?);
                        """);
        ) {
            // sets all parameters from sales contract object
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, vin);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error creating sale: " + e.getMessage());
        }
    }
}
