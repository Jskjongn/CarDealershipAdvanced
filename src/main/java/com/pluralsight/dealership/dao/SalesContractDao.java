package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.SalesContract;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesContractDao {

    private BasicDataSource salesDataSource;

    public SalesContractDao(BasicDataSource salesDataSource) {
        this.salesDataSource = salesDataSource;
    }

    public void newSalesContract(SalesContract newSale, String vin) {

        try (
                Connection connection = salesDataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        INSERT INTO
                        `sales_contracts` (`Date`, `Customer_Name`, `Customer_Email`, `VIN`, `Sales_Tax`, `Recording_Fee`, `Processing_Fee`, `Total_Price`, `Financed`, `Monthly_Payment`)\s
                        VALUES
                        (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """);
        ) {
            // sets all parameters from sales contract object
            preparedStatement.setDate(1, Date.valueOf(newSale.getDate()));
            preparedStatement.setString(2, newSale.getCustomerName());
            preparedStatement.setString(3, newSale.getCustomerEmail());
            preparedStatement.setString(4, vin);
            preparedStatement.setDouble(5, newSale.getSalesTaxAmount());
            preparedStatement.setDouble(6, newSale.getRecordingFee());
            preparedStatement.setDouble(7, newSale.getProcessingFee());
            preparedStatement.setDouble(8, newSale.getTotalPrice());
            preparedStatement.setBoolean(9, newSale.isFinanced());
            preparedStatement.setDouble(10, newSale.getMonthlyPayment());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error creating sale: " + e.getMessage());
        }
    }
}
