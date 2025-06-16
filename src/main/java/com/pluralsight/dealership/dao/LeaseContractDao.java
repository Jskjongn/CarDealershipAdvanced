package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.LeaseContract;
import com.pluralsight.dealership.models.SalesContract;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseContractDao {

    private BasicDataSource leaseDataSource;

    public LeaseContractDao(BasicDataSource leaseDataSource) {
        this.leaseDataSource = leaseDataSource;
    }

    public void newLeaseContract(LeaseContract newLease, String vin) {

        try (
                Connection connection = leaseDataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        INSERT INTO `lease_contracts` (`Date`, `Customer_Name`, `Customer_Email`, `VIN`, `Ending_Value`, `Lease_Fee`, `Total_Price`, `Monthly_Payment`)
                        VALUES
                        (?, ?, ?, ?, ?, ?, ?, ?)
                        """);
        ) {
            // sets all parameters from lease contract object
            preparedStatement.setDate(1, Date.valueOf(newLease.getDate()));
            preparedStatement.setString(2, newLease.getCustomerName());
            preparedStatement.setString(3, newLease.getCustomerEmail());
            preparedStatement.setString(4, vin);
            preparedStatement.setDouble(5, newLease.getEndingValue());
            preparedStatement.setDouble(6, newLease.getLeaseFee());
            preparedStatement.setDouble(7, newLease.getTotalPrice());
            preparedStatement.setDouble(8, newLease.getMonthlyPayment());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error creating sale: " + e.getMessage());
        }
    }
}
