package motCLD.Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ucsy
 */
public class SQLConnection {
    Connection con =null;
    public Connection getSQLConnection() throws Exception{
        String url = "jdbc:mysql://localhost:3306/santhitsa_db";
        String login = "admeKhant";
        String pass = "memeKhant";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,login,pass);
            if(con !=null)
                System.out.println("Connection OK");
            else
                System.out.println("Connection not OK");
        }catch (Exception e) 
        {
             System.out.println(e.getMessage());
        }
        return con;
    }
    public static void main(String args[]) throws Exception{
     SQLConnection obj=new SQLConnection();
     obj.getSQLConnection();
    }
}
