--create user for SanThitSa_DB

--For Linux
create user 'admekhant'@'localhost' identified by 'memeKhant';

grant all privileges on SanThitSa_DB.* to 'admekhant'@'localhost';


--For Windows
create user 'admeKhant' identified by 'memeKhant';

grant all privileges on SanThitSa_DB.* to 'admeKhant';

grant all privileges on *.* to 'admeKhant';


--Trigger Transcation--
delimiter //

create trigger Purchased_Parts 
    after update on Customers 
    for each row begin insert into Customers_Purchased_Parts
                                        (Cust_Name,Existing_Debt,Existing_Total_Charged)
                                    values(new.Cust_Name,new.Debt,new.Total_Charged);end//




