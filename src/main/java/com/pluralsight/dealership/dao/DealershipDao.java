package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.Dealership;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipDao {

    private BasicDataSource dealershipDataSource;

    public DealershipDao(BasicDataSource dealershipDataSource) {
        this.dealershipDataSource = dealershipDataSource;
    }

    public List<Dealership> getAllDealerships() {

        // creates new list to hold vehicles that match a color
        List<Dealership> allDealershipsList = new ArrayList<>();

        try (
                Connection connection = dealershipDataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT
                            *
                        FROM
                        	dealerships D;
                        """);

                ResultSet resultSet = preparedStatement.executeQuery();
        ) {

            while (resultSet.next()) {
                int dealershipId = resultSet.getInt("Dealership_ID");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                String phone = resultSet.getString("Phone");

                allDealershipsList.add(new Dealership(dealershipId, name, address, phone));
            }

        } catch (SQLException e) {
            System.out.println("Error searching all vehicles: " + e.getMessage());
        }

        return allDealershipsList;
    }
}
