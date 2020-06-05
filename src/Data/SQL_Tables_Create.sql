mysql> create database SanThitSa_DB;
Query OK, 1 row affected (0.00 sec)

mysql> use SanThitSa_DB;
Database changed
mysql> create table Parts(Part_ID int not null,Part_Name char(50),Type char(30),Quantity_Rate int,Quantity_Unit char(25),Price int,Lastest_Modified_Date timestamp,Primary key (Part_ID));
Query OK, 0 rows affected (0.26 sec)

mysql> desc Parts;
+-----------------------+-----------+------+-----+-------------------+-----------------------------+
| Field                 | Type      | Null | Key | Default           | Extra                       |
+-----------------------+-----------+------+-----+-------------------+-----------------------------+
| Part_ID               | int(11)   | NO   | PRI | NULL              |                             |
| Part_Name             | char(50)  | YES  |     | NULL              |                             |
| Type                  | char(30)  | YES  |     | NULL              |                             |
| Quantity_Rate         | int(11)   | YES  |     | NULL              |                             |
| Quantity_Unit         | char(25)  | YES  |     | NULL              |                             |
| Price                 | int(11)   | YES  |     | NULL              |                             |
| Lastest_Modified_Date | timestamp | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
+-----------------------+-----------+------+-----+-------------------+-----------------------------+
7 rows in set (0.01 sec)

mysql> create table Parts(Part_ID int not null auto_increment,Type char(30),Part_Name char(50),Part_Type char(30),Quantity_Rate int,Quantity_Unit char(25),Price int,Lastest_Modified_Date timestamp,Primary key (Part_ID));
Query OK, 0 rows affected (0.41 sec)

mysql> desc Parts;
+-----------------------+-----------+------+-----+-------------------+-----------------------------+
| Field                 | Type      | Null | Key | Default           | Extra                       |
+-----------------------+-----------+------+-----+-------------------+-----------------------------+
| Part_ID               | int(11)   | NO   | PRI | NULL              | auto_increment              |
| Type                  | char(30)  | YES  |     | NULL              |                             |
| Part_Name             | char(50)  | YES  |     | NULL              |                             |
| Part_Type             | char(30)  | YES  |     | NULL              |                             |
| Quantity_Rate         | int(11)   | YES  |     | NULL              |                             |
| Quantity_Unit         | char(25)  | YES  |     | NULL              |                             |
| Price                 | int(11)   | YES  |     | NULL              |                             |
| Lastest_Modified_Date | timestamp | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
+-----------------------+-----------+------+-----+-------------------+-----------------------------+
8 rows in set (0.00 sec)

mysql> create table Customers (Cust_Name char(50) not null,Phone_No char(20),Address char(65),Debt int,Payment int,Total_Charged int,Registered_Date timestamp,primary key (Cust_Name));
Query OK, 0 rows affected (0.25 sec)

mysql> desc Customers;
+-----------------+-----------+------+-----+-------------------+-----------------------------+
| Field           | Type      | Null | Key | Default           | Extra                       |
+-----------------+-----------+------+-----+-------------------+-----------------------------+
| Cust_Name       | char(50)  | NO   | PRI | NULL              |                             |
| Phone_No        | char(20)  | YES  |     | NULL              |                             |
| Address         | char(65)  | YES  |     | NULL              |                             |
| Debt            | int(11)   | YES  |     | NULL              |                             |
| Payment         | int(11)   | YES  |     | NULL              |                             |
| Total_Charged   | int(11)   | YES  |     | NULL              |                             |
| Registered_Date | timestamp | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
+-----------------+-----------+------+-----+-------------------+-----------------------------+
7 rows in set (0.00 sec)

