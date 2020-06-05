/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motCLD.Classes;

/**
 *
 * @author jouse
 */
public class Parts {
    public int  Part_ID;
    public String Type; 
    public String Part_Name;
    public String Part_Type;
    public String Avaliable_Quantity;
    public double Price;
    public String Lastest_Modified_Date;

    public Parts() {
    }

    public Parts(int Part_ID, String Part_Name, String Part_Type) {
        this.Part_ID = Part_ID;
        this.Part_Name = Part_Name;
        this.Part_Type = Part_Type;
    }

    

    
    
    public Parts(int Part_ID, String Type, String Part_Name, String Part_Type, String Avaliable_Quantity, double Price, String Lastest_Modified_Date) {
        this.Part_ID = Part_ID;
        this.Type = Type;
        this.Part_Name = Part_Name;
        this.Part_Type = Part_Type;
        this.Avaliable_Quantity = Avaliable_Quantity;
        this.Price = Price;
        this.Lastest_Modified_Date = Lastest_Modified_Date;
    }

    
    public Parts(int Part_ID, double Price, String Type, String Part_Name, String Part_Type, String Avaliable_Quantity, String Lastest_Modified_Date) {
        this.Part_ID = Part_ID;
        this.Price = Price;
        this.Type = Type;
        this.Part_Name = Part_Name;
        this.Part_Type = Part_Type;
        this.Avaliable_Quantity = Avaliable_Quantity;
        this.Lastest_Modified_Date = Lastest_Modified_Date;
    }

    
    public int getPart_ID() {
        return Part_ID;
    }

    public void setPart_ID(int Part_ID) {
        this.Part_ID = Part_ID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getPart_Name() {
        return Part_Name;
    }

    public void setPart_Name(String Part_Name) {
        this.Part_Name = Part_Name;
    }

    public String getPart_Type() {
        return Part_Type;
    }

    public void setPart_Type(String Part_Type) {
        this.Part_Type = Part_Type;
    }

    public String getAvaliable_Quantity() {
        return Avaliable_Quantity;
    }

    public void setAvaliable_Quantity(String Avaliable_Quantity) {
        this.Avaliable_Quantity = Avaliable_Quantity;
    }

    public String getLastest_Modified_Date() {
        return Lastest_Modified_Date;
    }

    public void setLastest_Modified_Date(String Lastest_Modified_Date) {
        this.Lastest_Modified_Date = Lastest_Modified_Date;
    }
    
    

}
