update Parts set Avaliable_Quantity=9000,Price=40000000 where Part_ID=3;

update Parts set Part_Name='jj' where Part_ID=3;


--to change flage for Parts_Update_Log table when Parts table occur update trancation
update Parts set Avaliable_Quantity=100 where Part_ID=3;
select Update_Date from Parts_Update_Log where Part_ID=3 and New_Quantity=1000;

--select data to store in list for Update_Date from lastest index of list--
update Parts_Update_Log set Flag=0 where Part_ID=3 and New_Quantity=100 and Update_Date=?;

--To View Updated Parts from Parts--
select pts.* 
from Parts AS pts
INNER JOIN Parts_Update_Log AS ptsULog
ON pts.Part_ID=ptsULog.Part_ID 
AND  ptsULog.Update_Date between '2019-06-28' AND '2019-06-29'
group by Part_Name and Part_Type;

SELECT *
FROM dbo.Customers AS CUS
INNER JOIN dbo.Orders AS ORD 
ON CUS.CustomerID = ORD.CustomerID
AND CUS.FirstName = 'John'

select pts.Part_ID,pts.Part_Name,pts.Part_Type,pts.Avaliable_Quantity,pts.Part_Unit,pts.Price from Parts AS pts INNER JOIN Parts_Update_Log AS ptsULog ON pts.Part_ID=ptsULog.Part_ID AND  ptsULog.Update_Date between '2019-06-16' AND '2019-06-18' group by ptsULog.Part_Type

select * from Parts_Update_Log where Part_ID=74 and Part_Name='2 * 1 GI HOLLOW PIPE' and Part_Type='1.2 mm' and Flag=1;