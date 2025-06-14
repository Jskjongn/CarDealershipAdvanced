package com.pluralsight.dealership.dao;

import org.apache.commons.dbcp2.BasicDataSource;

public class DealershipDao {

    private BasicDataSource dealershipDataSource;

    public DealershipDao(BasicDataSource dealershipDataSource) {
        this.dealershipDataSource = dealershipDataSource;
    }
}
