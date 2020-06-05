
create database Bestie_Shopkeeper_DB;

use Bestie_Shopkeeper_DB;


--For Windows
--Parts Table--
    create table Parts
        (Part_ID int not null auto_increment,
         Type char(30),
         Part_Name char(50),
         Part_Type char(30),
         Avaliable_Quantity int,
         Part_Unit char(25),
         Price int,
         Inserted_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
         Primary key (Part_ID));

--Customers Table--
    create table Customers 
        (Cust_Name char(50) not null,
         Phone_No char(20),
         Address char(65),
         Debt int,
         Payment int,
         Total_Purchased_Charged int,
         Registered_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
         primary key (Cust_Name));

--Customers_Purchased_Log--
create table Customers_Purchased_Log 
        (Purchased_ID int not null auto_increment,
         Cust_Name char(50) not null,
         Part_ID int,
         Type char(30),
         Part_Name char(50),
         Part_Type char(30),
         Current_Total_Paid int,
         Current_Debt int,
         Current_Total_Bill int,
         Existing_Debt int,
         Existing_Payment int,
         Existing_Total_Charged int,
         Each_Purchased_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
         foreign key (Cust_Name) references Customers(Cust_Name),
         Primary key (Purchased_ID));

--Parts_Update_Log--
create table Parts_Update_Log
        (Part_ID int,
         Type char(30),
         Part_Name char(50),
         Part_Type char(30),
         Flag int,
         New_Quantity int,
         Existing_Quantity int,
         Part_Unit char(25),
         New_Price int,
         Existing_Price int,
         Update_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);

--Customers_Repay_Debt_Log--
create table Customers_Repay_Debt_Log
        (Repay_ID int not null,
         Cust_Name char(50),
         Phone_No char(20),
         Address char(65),
         Flag int,
         Existing_Debt int,
         Repay_Debt int,
         Current_Debt int,
         Current_Payment int,
         Current_Total_Purchased_Charged int,
         Repaid_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
         primary key (Repay_ID));