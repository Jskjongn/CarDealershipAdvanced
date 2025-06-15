# ---------------------------------------------------------------------- #
# Target DBMS:           MySQL                                           #
# Project name:          CarDealershipDatabase                           #
# ---------------------------------------------------------------------- #

DROP DATABASE IF EXISTS cardealership;

CREATE DATABASE IF NOT EXISTS cardealership;

USE cardealership;

# ---------------------------------------------------------------------- #
# Tables                                                                 #
# ---------------------------------------------------------------------- #
# ---------------------------------------------------------------------- #
# Add table "dealerships"                                                #
# ---------------------------------------------------------------------- #

CREATE TABLE `dealerships` (
	`Dealership_ID` INT NOT NULL AUTO_INCREMENT, -- Primary Key
    `Name` VARCHAR(50) NOT NULL,
    `Address` VARCHAR(50) NOT NULL,
    `Phone` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`Dealership_ID`)
);

# ---------------------------------------------------------------------- #
# Add table "vehicles"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `vehicles` (
	`VIN` VARCHAR(17) NOT NULL,
    `Year` INT NOT NULL,
    `Make` VARCHAR(25) NOT NULL,
    `Model` VARCHAR(25) NOT NULL,
    `Vehicle_Type` VARCHAR(20) NOT NULL,
    `Color` VARCHAR(20) NOT NULL,
    `Odometer` INTEGER NOT NULL,
    `Price` DECIMAL(10,2) NOT NULL,
    `SOLD` ENUM('SOLD', 'NOT SOLD') DEFAULT 'NOT SOLD',
    PRIMARY KEY (`VIN`)
);

# ---------------------------------------------------------------------- #
# Add table "inventory"                                                  #
# ---------------------------------------------------------------------- #

CREATE TABLE `inventory` (
	`Dealership_ID` INT NOT NULL,
    `VIN` VARCHAR(17) NOT NULL,
    PRIMARY KEY (`Dealership_ID`, `VIN`),
    FOREIGN KEY (`Dealership_ID`) REFERENCES `dealerships` (`Dealership_ID`) ON DELETE CASCADE,
    FOREIGN KEY (`VIN`) REFERENCES `vehicles` (`VIN`) ON DELETE CASCADE
);

# ---------------------------------------------------------------------- #
# Add table "sales_contracts"                                            #
# ---------------------------------------------------------------------- #

CREATE TABLE `sales_contracts` (
	`Sales_Contract_ID` INTEGER NOT NULL AUTO_INCREMENT,
	`Date` DATE,
    `Customer_Name` VARCHAR(50) NOT NULL,
    `Customer_Email` VARCHAR(50) NOT NULL,
    `VIN` VARCHAR(17) NOT NULL,
    `Sales_Tax` DECIMAL(10,2) NOT NULL,
    `Recording_Fee` DECIMAL(10,2) NOT NULL DEFAULT 100.00,
    `Processing_Fee` DECIMAL(10,2) NOT NULL,
    `Total_Price` DECIMAL(10,2) NOT NULL,
    `Financed` ENUM('YES', 'NO') DEFAULT 'NO',
    `Monthly_Payment` DECIMAL(10,2) NOT NULL,
    CONSTRAINT `PK_Sales_Contract_ID` PRIMARY KEY (`Sales_Contract_ID`),
    FOREIGN KEY (`VIN`) REFERENCES `vehicles` (`VIN`)
);

# ---------------------------------------------------------------------- #
# Add table "lease_contracts"                                            #
# ---------------------------------------------------------------------- #

CREATE TABLE `lease_contracts` (
	`Lease_Contract_ID` INTEGER NOT NULL AUTO_INCREMENT,
	`Date` DATE,
    `Customer_Name` VARCHAR(50) NOT NULL,
    `Customer_Email` VARCHAR(50) NOT NULL,
    `VIN` VARCHAR(17) NOT NULL,
    `Ending_Value` DECIMAL(10,2) NOT NULL,
    `Lease_Fee` DECIMAL(10,2) NOT NULL,
    `Total_Price` DECIMAL(10,2) NOT NULL,
    `Financed` ENUM('YES', 'NO') DEFAULT 'NO',
    `Monthly_Payment` DECIMAL(10,2) NOT NULL,
    CONSTRAINT `PK_Lease_Contract_ID` PRIMARY KEY (`Lease_Contract_ID`),
    FOREIGN KEY (`VIN`) REFERENCES `vehicles` (`VIN`)
);

# ---------------------------------------------------------------------- #
# Add info into "dealerships"                                            #
# ---------------------------------------------------------------------- #

INSERT INTO `dealerships` (`Name`, `Address`, `Phone`) 
VALUES
('Lone Star Motors', '1234 Main St, Dallas, TX 75201', '214-555-1234'),
('Hill Country Auto', '5678 Ranch Rd, Austin, TX 78701', '512-555-5678'),
('Gulf Coast Cars', '9101 Seawall Blvd, Galveston, TX 77550', '409-555-9101');

# ---------------------------------------------------------------------- #
# Add info into "vehicles"                                               #
# ---------------------------------------------------------------------- #

-- Dealership 1 
INSERT INTO `vehicles` (`VIN`, `Year`, `Make`, `Model`, `Vehicle_Type`, `Color`, `Odometer`, `Price`, `SOLD`) 
VALUES
('LLAO8EZ1X4P34K856', 2021, 'Ford', 'Explorer', 'Crossover', 'Black', 119606, 33796.43, 'SOLD'),
('4L6UIO5I8X272SPM6', 2022, 'Chevrolet', 'Malibu', 'Coupe', 'Blue', 39631, 29605.99, 'NOT SOLD'),
('RXRXGL13CM6EXFSI1', 2020, 'Toyota', 'Tacoma', 'Truck', 'Blue', 129028, 18892.08, 'SOLD'),
('QHNL56D861OHUNU55', 2015, 'Nissan', 'Sentra', 'Sedan', 'Red', 89621, 10042.20, 'SOLD'),
('HQMQY76K55C334L1Y', 2024, 'Honda', 'Accord', 'Coupe', 'Red', 108690, 14166.42, 'NOT SOLD'),
('Y8DKU77B5IQX0RHPN', 2016, 'Ford', 'Fusion', 'Coupe', 'Silver', 121425, 31646.63, 'NOT SOLD'),
('OUOQ4ZPPZN3LVHIO5', 2022, 'Chevrolet', 'Silverado', 'Truck', 'Blue', 43287, 15265.11, 'NOT SOLD'),
('A9WTEJAC88CUK8V0K', 2020, 'Nissan', 'Frontier', 'Coupe', 'Gray', 147791, 30742.71, 'SOLD'),
('GYW02JP3VJ85ZQ8E5', 2019, 'Ford', 'Explorer', 'Sedan', 'Red', 59811, 41707.41, 'NOT SOLD'),
('Q8ZXAEX7R1EYJ9CL7', 2024, 'Toyota', 'Tacoma', 'Truck', 'White', 130332, 25671.85, 'SOLD'),
('94XLMVX8GVTLRI9J2', 2016, 'Toyota', 'Tacoma', 'Truck', 'Silver', 62246, 25197.74, 'NOT SOLD'),
('U65SGEGT8HZIT2LU0', 2022, 'Toyota', 'Tacoma', 'Truck', 'Black', 108479, 28746.61, 'NOT SOLD'),
('BOPUODK7GWH2Z72XW', 2020, 'Nissan', 'Sentra', 'Crossover', 'Blue', 124373, 43521.01, 'SOLD'),
('UQG1BRJ89CILOS5OJ', 2021, 'Chevrolet', 'Malibu', 'Sedan', 'Gray', 149179, 26071.36, 'NOT SOLD'),
('KPK3TV7P7OYWX0Z9F', 2023, 'Nissan', 'Altima', 'Sedan', 'White', 40879, 23291.80, 'NOT SOLD'),
('2M1HHSVA8CXXYPSFY', 2023, 'Ford', 'Escape', 'Van', 'Silver', 88299, 36173.90, 'NOT SOLD'),
('T6BGISB44YDF04XHD', 2016, 'Toyota', 'Tacoma', 'Truck', 'Gray', 112022, 41433.56, 'SOLD'),
('KWDSR7AUQ2CZ2Z72E', 2015, 'Chevrolet', 'Equinox', 'Crossover', 'Silver', 50908, 43045.93, 'NOT SOLD'),
('3XJEM8L1HH2PIU7TA', 2023, 'Toyota', 'Tacoma', 'Truck', 'Gray', 111255, 11261.82, 'NOT SOLD'),
('R09CCIZQKIDTDS0C8', 2019, 'Honda', 'Civic', 'Sedan', 'White', 54700, 31823.30, 'NOT SOLD');

-- Dealership 2
INSERT INTO `vehicles` (`VIN`, `Year`, `Make`, `Model`, `Vehicle_Type`, `Color`, `Odometer`, `Price`, `SOLD`) 
VALUES
('2OWA2YZJLO5L3Q1KJ', 2023, 'Toyota', 'Corolla', 'Sedan', 'Silver', 105632, 39731.82, 'NOT SOLD'),
('03CX1G8B6XWRSR2LD', 2023, 'Honda', 'CR-V', 'Coupe', 'Black', 124264, 41951.96, 'SOLD'),
('Y7TKHV7Z3HRW1QMPM', 2017, 'Toyota', 'Camry', 'Sedan', 'Red', 43247, 16460.94, 'SOLD'),
('9QYWSH9LZ37ZUTYLR', 2024, 'Ford', 'Fusion', 'Sedan', 'Black', 76074, 34750.55, 'SOLD'),
('INCB7B8TD5LJ6S4RS', 2024, 'Ford', 'Fusion', 'Sedan', 'White', 114348, 15209.67, 'SOLD'),
('FVZXYV14RR1IULMT8', 2024, 'Honda', 'Accord', 'Coupe', 'Blue', 63482, 35164.83, 'SOLD'),
('8WHU48AL0RE9AMMGN', 2017, 'Ford', 'Fusion', 'Sedan', 'Blue', 129052, 29204.78, 'NOT SOLD'),
('WX6XK72MUPF54U3V8', 2021, 'Chevrolet', 'Equinox', 'Sedan', 'Red', 77291, 10195.95, 'SOLD'),
('OH6H23L08VXGNRGSZ', 2023, 'Chevrolet', 'Silverado', 'Truck', 'White', 137219, 31167.46, 'NOT SOLD'),
('9RQ8VGEV93ABXRA49', 2015, 'Toyota', 'Corolla', 'Sedan', 'Silver', 69523, 42556.08, 'SOLD'),
('JGE75EVYUSCETOVYU', 2015, 'Ford', 'Escape', 'Crossover', 'Gray', 83268, 11892.72, 'NOT SOLD'),
('1K2Q2M5F8ZUR3XKZF', 2023, 'Ford', 'Fusion', 'Sedan', 'Black', 147875, 18346.49, 'SOLD'),
('R3JH41CXLHWISNS8E', 2018, 'Nissan', 'Altima', 'Sedan', 'Silver', 47938, 32877.44, 'NOT SOLD'),
('Z5Q4S8WY61QY5M7F5', 2022, 'Toyota', 'Corolla', 'Sedan', 'Red', 44549, 43311.24, 'NOT SOLD'),
('4LP54IXTZ6S0FY1O1', 2023, 'Toyota', 'Corolla', 'Sedan', 'Blue', 45616, 44189.90, 'SOLD'),
('97EDSB6ZPQ51QLD2V', 2018, 'Chevrolet', 'Impala', 'Crossover', 'Black', 114216, 14585.68, 'SOLD'),
('D4MSZFGKGSR1SY7N5', 2021, 'Ford', 'F-150', 'Truck', 'Red', 121422, 38930.26, 'SOLD'),
('A9BW1MM88PXK2ZGKM', 2015, 'Chevrolet', 'Silverado', 'Truck', 'White', 52033, 30667.49, 'SOLD'),
('W7WZ19JKD14BAXNN6', 2024, 'Honda', 'Civic', 'Sedan', 'Red', 44499, 19015.91, 'NOT SOLD'),
('F1MNX0QJJTLECLT82', 2023, 'Chevrolet', 'Equinox', 'Sedan', 'Silver', 81426, 44479.04, 'NOT SOLD');

-- Dealership 3
INSERT INTO `vehicles` (`VIN`, `Year`, `Make`, `Model`, `Vehicle_Type`, `Color`, `Odometer`, `Price`, `SOLD`) 
VALUES
('E9U92I3V2UHFQK0XC', 2017, 'Nissan', 'Altima', 'Sedan', 'Red', 117501, 30596.89, 'SOLD'),
('KFWY2OGZX92I4IU3U', 2015, 'Honda', 'Ridgeline', 'Truck', 'Silver', 93151, 14692.49, 'SOLD'),
('T0ROKQKTVHQZZAWO7', 2022, 'Nissan', 'Altima', 'Sedan', 'White', 128149, 29096.07, 'NOT SOLD'),
('LBALVJUGGJFFLCHRY', 2018, 'Toyota', 'Camry', 'Sedan', 'White', 139067, 43298.04, 'NOT SOLD'),
('YUNVX2W3XRAGLYJ2C', 2024, 'Ford', 'Fusion', 'Crossover', 'Gray', 128123, 25990.20, 'SOLD'),
('O0RR3TYEFA42X9JIV', 2015, 'Honda', 'Ridgeline', 'Truck', 'Gray', 139185, 14232.93, 'NOT SOLD'),
('29P48QK5YB69GZ4R3', 2021, 'Chevrolet', 'Silverado', 'Truck', 'White', 143644, 30867.13, 'NOT SOLD'),
('HL2K2OJHM6U0SHUM9', 2017, 'Toyota', 'RAV4', 'Crossover', 'White', 133009, 24066.38, 'SOLD'),
('8RGRKSHV0XTTHPZKN', 2024, 'Ford', 'Explorer', 'Crossover', 'Blue', 125410, 27047.28, 'SOLD'),
('B8GXQ5OM1CH96RMVI', 2023, 'Toyota', 'RAV4', 'Crossover', 'Red', 64648, 42283.79, 'NOT SOLD'),
('NLMQDKVL2H3WGLKQT', 2023, 'Toyota', 'Tacoma', 'Truck', 'White', 118259, 13696.42, 'NOT SOLD'),
('QWBOY1ZNE74PGMB16', 2022, 'Chevrolet', 'Silverado', 'Truck', 'Blue', 116381, 34801.17, 'NOT SOLD'),
('C6NKTK0AXAKWBUNAY', 2019, 'Toyota', 'RAV4', 'Crossover', 'Black', 134064, 16787.66, 'NOT SOLD'),
('W9HATVK41KNWB3X0C', 2017, 'Toyota', 'Camry', 'Sedan', 'Blue', 105264, 14923.68, 'SOLD'),
('LB98SUS4KTZOH9PB4', 2017, 'Honda', 'Civic', 'Sedan', 'Blue', 112043, 34233.60, 'NOT SOLD'),
('HJAR2GCVRS2EGEHGR', 2024, 'Ford', 'Escape', 'Crossover', 'Blue', 113497, 42513.12, 'NOT SOLD'),
('T2QZ1VCIQY5LLR3LV', 2019, 'Honda', 'Ridgeline', 'Sedan', 'White', 118842, 26480.45, 'NOT SOLD'),
('ZOC2ZT2WXXVEPYOKN', 2022, 'Ford', 'Explorer', 'Crossover', 'White', 80100, 25944.31, 'SOLD'),
('3J7O8VKEF4RKHN6R4', 2016, 'Nissan', 'Sentra', 'Sedan', 'Red', 130844, 42250.01, 'SOLD'),
('PCND4CAKQJBMV5MFX', 2022, 'Toyota', 'Tacoma', 'Truck', 'White', 143441, 30152.47, 'NOT SOLD');

# ---------------------------------------------------------------------- #
# Add info into "inventory"                                              #
# ---------------------------------------------------------------------- #

-- Dealership 1: Lone Star Motors
INSERT INTO `inventory` (`Dealership_ID`, `VIN`)
VALUES
(1, 'LLAO8EZ1X4P34K856'),
(1, '4L6UIO5I8X272SPM6'),
(1, 'RXRXGL13CM6EXFSI1'),
(1, 'QHNL56D861OHUNU55'),
(1, 'HQMQY76K55C334L1Y'),
(1, 'Y8DKU77B5IQX0RHPN'),
(1, 'OUOQ4ZPPZN3LVHIO5'),
(1, 'A9WTEJAC88CUK8V0K'),
(1, 'GYW02JP3VJ85ZQ8E5'),
(1, 'Q8ZXAEX7R1EYJ9CL7'),
(1, '94XLMVX8GVTLRI9J2'),
(1, 'U65SGEGT8HZIT2LU0'),
(1, 'BOPUODK7GWH2Z72XW'),
(1, 'UQG1BRJ89CILOS5OJ'),
(1, 'KPK3TV7P7OYWX0Z9F'),
(1, '2M1HHSVA8CXXYPSFY'),
(1, 'T6BGISB44YDF04XHD'),
(1, 'KWDSR7AUQ2CZ2Z72E'),
(1, '3XJEM8L1HH2PIU7TA'),
(1, 'R09CCIZQKIDTDS0C8');

-- Dealership 2: Hill Country Auto
INSERT INTO `inventory` (`Dealership_ID`, `VIN`)
VALUES
(2, '2OWA2YZJLO5L3Q1KJ'),
(2, '03CX1G8B6XWRSR2LD'),
(2, 'Y7TKHV7Z3HRW1QMPM'),
(2, '9QYWSH9LZ37ZUTYLR'),
(2, 'INCB7B8TD5LJ6S4RS'),
(2, 'FVZXYV14RR1IULMT8'),
(2, '8WHU48AL0RE9AMMGN'),
(2, 'WX6XK72MUPF54U3V8'),
(2, 'OH6H23L08VXGNRGSZ'),
(2, '9RQ8VGEV93ABXRA49'),
(2, 'JGE75EVYUSCETOVYU'),
(2, '1K2Q2M5F8ZUR3XKZF'),
(2, 'R3JH41CXLHWISNS8E'),
(2, 'Z5Q4S8WY61QY5M7F5'),
(2, '4LP54IXTZ6S0FY1O1'),
(2, '97EDSB6ZPQ51QLD2V'),
(2, 'D4MSZFGKGSR1SY7N5'),
(2, 'A9BW1MM88PXK2ZGKM'),
(2, 'W7WZ19JKD14BAXNN6'),
(2, 'F1MNX0QJJTLECLT82');

-- Dealership 3: Gulf Coast Cars
INSERT INTO `inventory` (`Dealership_ID`, `VIN`)
VALUES
(3, 'E9U92I3V2UHFQK0XC'),
(3, 'KFWY2OGZX92I4IU3U'),
(3, 'T0ROKQKTVHQZZAWO7'),
(3, 'LBALVJUGGJFFLCHRY'),
(3, 'YUNVX2W3XRAGLYJ2C'),
(3, 'O0RR3TYEFA42X9JIV'),
(3, '29P48QK5YB69GZ4R3'),
(3, 'HL2K2OJHM6U0SHUM9'),
(3, '8RGRKSHV0XTTHPZKN'),
(3, 'B8GXQ5OM1CH96RMVI'),
(3, 'NLMQDKVL2H3WGLKQT'),
(3, 'QWBOY1ZNE74PGMB16'),
(3, 'C6NKTK0AXAKWBUNAY'),
(3, 'W9HATVK41KNWB3X0C'),
(3, 'LB98SUS4KTZOH9PB4'),
(3, 'HJAR2GCVRS2EGEHGR'),
(3, 'T2QZ1VCIQY5LLR3LV'),
(3, 'ZOC2ZT2WXXVEPYOKN'),
(3, '3J7O8VKEF4RKHN6R4'),
(3, 'PCND4CAKQJBMV5MFX');

# ---------------------------------------------------------------------- #
# Add info into "sales_contracts"                                        #
# ---------------------------------------------------------------------- #

INSERT INTO `sales_contracts` (`Date`, `Customer_Name`, `Customer_Email`, `VIN`, `Sales_Tax`, `Recording_Fee`, `Processing_Fee`, `Total_Price`, `Financed`, `Monthly_Payment`) 
VALUES
  ('2024-04-10', 'Emma Johnson', 'emma.johnson@email.com', 'LLAO8EZ1X4P34K856', 1689.82, 100, 495, 40794.88, 'YES', 849.89),
  ('2023-09-15', 'Liam Garcia', 'liam.garcia@email.com', 'RXRXGL13CM6EXFSI1', 944.60, 100, 495, 24225.08, 'NO', 0),
  ('2024-01-20', 'Sophia Lee', 'sophia.lee@email.com', 'QHNL56D861OHUNU55', 502.11, 100, 495, 11881.71, 'YES', 514.41),
  ('2024-02-28', 'Mason Davis', 'mason.davis@email.com', 'A9WTEJAC88CUK8V0K', 1537.14, 100, 495, 37194.50, 'NO', 0),
  ('2024-05-03', 'Olivia Wilson', 'olivia.wilson@email.com', 'Q8ZXAEX7R1EYJ9CL7', 1283.59, 100, 495, 27650.44, 'YES', 576.05),
  ('2023-11-22', 'Noah Martinez', 'noah.martinez@email.com', 'BOPUODK7GWH2Z72XW', 2176.05, 100, 495, 50920.27, 'YES', 1060.42),
  ('2023-07-18', 'Ava Brown', 'ava.brown@email.com', 'T6BGISB44YDF04XHD', 2071.68, 100, 495, 48400.30, 'NO', 0),
  ('2024-03-11', 'James Miller', 'james.miller@email.com', 'LLAO8EZ1X4P34K856', 1689.82, 100, 495, 33796.43 + 1689.82 + 100 + 495, 'NO', 0),
  ('2023-10-05', 'Isabella Anderson', 'isabella.anderson@email.com', 'RXRXGL13CM6EXFSI1', 944.60, 100, 495, 18892.08 + 944.60 + 100 + 495, 'NO', 0),
  ('2024-06-21', 'William Thomas', 'william.thomas@email.com', 'QHNL56D861OHUNU55', 502.11, 100, 495, 10042.20 + 502.11 + 100 + 495, 'NO', 0),
  ('2024-05-05', 'Ethan Parker', 'ethan.parker@email.com', '03CX1G8B6XWRSR2LD', 2097.60, 100, 495, 45510.68, 'YES', 948.14),
  ('2023-12-12', 'Mia Roberts', 'mia.roberts@email.com', 'Y7TKHV7Z3HRW1QMPM', 823.05, 100, 495, 17778.99, 'NO', 0),
  ('2024-02-20', 'Jacob Scott', 'jacob.scott@email.com', '9QYWSH9LZ37ZUTYLR', 1737.53, 100, 495, 37783.58, 'YES', 787.16),
  ('2024-03-22', 'Emily Turner', 'emily.turner@email.com', 'INCB7B8TD5LJ6S4RS', 760.48, 100, 495, 16565.15, 'NO', 0),
  ('2023-10-18', 'Alexander Young', 'alex.young@email.com', 'FVZXYV14RR1IULMT8', 1758.24, 100, 495, 37918.07, 'YES', 789.96),
  ('2023-11-01', 'Charlotte Green', 'charlotte.green@email.com', 'WX6XK72MUPF54U3V8', 509.80, 100, 495, 11100.75, 'NO', 0),
  ('2024-01-15', 'Benjamin Hill', 'benjamin.hill@email.com', '9RQ8VGEV93ABXRA49', 2127.80, 100, 495, 47029.88, 'YES', 979.79);

# ---------------------------------------------------------------------- #
# Add info into "lease_contracts"                                        #
# ---------------------------------------------------------------------- #

INSERT INTO `lease_contracts` (`Date`, `Customer_Name`, `Customer_Email`, `VIN`, `Ending_Value`, `Lease_Fee`, `Total_Price`, `Financed`, `Monthly_Payment`)
VALUES
  ('2023-07-12', 'Alice Johnson', 'alice.johnson@email.com', 'E9U92I3V2UHFQK0XC', 15298.45, 2140.78, 19000.25, 'NO', 527.79),
  ('2024-01-05', 'Michael Smith', 'm.smith@email.com', 'KFWY2OGZX92I4IU3U', 7346.25, 1028.47, 9122.85, 'YES', 253.41),
  ('2025-03-20', 'Samantha Green', 'samantha.green@email.com', 'YUNVX2W3XRAGLYJ2C', 12995.10, 1819.31, 16110.62, 'NO', 447.52),
  ('2023-11-15', 'Daniel Lee', 'd.lee@email.com', 'HL2K2OJHM6U0SHUM9', 12033.19, 1684.65, 14922.72, 'NO', 414.52),
  ('2024-06-30', 'Emily Carter', 'emily.carter@email.com', '8RGRKSHV0XTTHPZKN', 13523.64, 1893.31, 16755.52, 'YES', 465.43),
  ('2023-02-27', 'Robert Brown', 'robert.brown@email.com', 'W9HATVK41KNWB3X0C', 7461.84, 1044.66, 9259.36, 'NO', 257.20),
  ('2025-05-10', 'Jessica Turner', 'jess.turner@email.com', 'ZOC2ZT2WXXVEPYOKN', 12972.16, 1816.10, 16008.88, 'NO', 444.69),
  ('2024-09-14', 'Brian Wilson', 'brian.wilson@email.com', '3J7O8VKEF4RKHN6R4', 21125.01, 2957.50, 26044.51, 'YES', 723.46),
  ('2023-08-09', 'Laura Martinez', 'laura.martinez@email.com', '8RGRKSHV0XTTHPZKN', 13523.64, 1893.31, 16755.52, 'NO', 465.43),
  ('2024-12-22', 'Kevin White', 'kevin.white@email.com', 'HL2K2OJHM6U0SHUM9', 12033.19, 1684.65, 14922.72, 'YES', 414.52),
  ('2024-01-20', 'Logan Brooks', 'logan.brooks@email.com', '1K2Q2M5F8ZUR3XKZF', 9173.25, 1284.25, 11558.84, 'NO', 321.08),
  ('2024-03-02', 'Ava Powell', 'ava.powell@email.com', '4LP54IXTZ6S0FY1O1', 22094.95, 3093.29, 30664.84, 'NO', 851.80),
  ('2023-12-11', 'Mason Hayes', 'mason.hayes@email.com', '97EDSB6ZPQ51QLD2V', 7292.84, 1021.00, 9430.98, 'NO', 261.97),
  ('2024-04-15', 'Lily Simmons', 'lily.simmons@email.com', 'D4MSZFGKGSR1SY7N5', 19465.13, 2725.12, 23919.88, 'NO', 664.44),
  ('2023-10-27', 'Noah James', 'noah.james@email.com', 'A9BW1MM88PXK2ZGKM', 15333.75, 2146.72, 18760.90, 'NO', 521.14);