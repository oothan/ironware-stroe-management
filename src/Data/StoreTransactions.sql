--create user for SanThitSa_DB

--For Linux
create user 'admeSkpper'@'localhost' identified by 'memeSkpper';

grant all privileges on *.* to 'admeSkpper'@'localhost';

grant all privileges on Bestie_Shopkeeper_DB.* to 'admeSkpper'@'localhost';


--For Windows
create user 'admeSkpper' identified by 'memeSkpper';

grant all privileges on *.* to 'admeSkpper';

grant all privileges on Bestie_Shopkeeper_DB.* to 'admeSkpper';




--Trigger Transcation--
delimiter //


create trigger Parts_Update_Trigger
    after update on Parts
    for each row begin insert into Parts_Update_Log
                                    (Part_ID,
                                     Type,
                                     Part_Name,
                                     Part_Type,
                                     Flag,
                                     New_Quantity,
                                     Existing_Quantity,
                                     Part_Unit,
                                     New_Price,
                                     Existing_Price)
                                values(old.Part_ID,
                                        new.Type,
                                        new.Part_Name,
                                        new.Part_Type,
                                        1,
                                        new.Avaliable_Quantity,
                                        old.Avaliable_Quantity,
                                        old.Part_Unit,
                                        new.Price,
                                        old.Price);end//

create trigger Purchased_Parts 
    after update on Customers 
    for each row begin insert into Customers_Purchased_Parts
                                        (Cust_Name,Existing_Debt,Existing_Total_Charged)
                                    values(new.Cust_Name,new.Debt,new.Total_Charged);end//




