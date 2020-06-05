
create database SanThitSa_DB;

use SanThitSa_DB;

--For Linux
--Parts Table--
    create table Parts
        (Part_ID int not null auto_increment,
         Type char(30),Part_Name char(50),
         Part_Type char(30),Quantity_Rate int,
         Quantity_Unit char(25),
         Price int,
         Lastest_Modified_Date timestamp,
         Primary key (Part_ID));
--Customers Table--
    create table Customers 
        (Cust_Name char(50) not null,
         Phone_No char(20),
         Address char(65),
         Debt int,
         Payment int,
         Total_Charged int,
         Registered_Date timestamp,
         primary key (Cust_Name));

--For Windows
--Parts Table--
    create table Parts
        (Part_ID int not null auto_increment,
         Type char(30),
         Part_Name char(50),
         Part_Type char(30),
         Quantity_Rate int,
         Quantity_Unit char(25),
         Price int,
         Lastest_Modified_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
         Primary key (Part_ID));
--Customers Table--
    create table Customers 
        (Cust_Name char(50) not null,
         Phone_No char(20),
         Address char(65),
         Debt int,
         Payment int,
         Total_Charged int,
         Registered_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
         primary key (Cust_Name));
--Customers_Purchased_Parts--
create table Customers_Purchased_Parts 
        (Purchased_ID int not null auto_increment,
         Cust_Name char(50) not null,
         Part_ID int not null,
         Type char(30),
         Part_Name char(50),
         Part_Type char(30),
         Current_Total_Paid int,
         Current_Debt int,
         Current_Total_Bill int,
         Existing_Debt int,
         Existing_Total_Charged int,
         Each_Purchased_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
         foreign key (Cust_Name) references Customers(Cust_Name),
         foreign key (Part_ID) references Parts(Part_ID),
         Primary key (Purchased_ID));


create table Customers_Purchased_Parts 
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
         Existing_Total_Charged int,
         Each_Purchased_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
         foreign key (Cust_Name) references Customers(Cust_Name),
         Primary key (Purchased_ID));



