/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motCLD.Classes;

/**
 *
 * @author ucsy
 */
public class Customers {
    
    public String Cust_Name;
    public String Phone_No;
    public String Address;
    public double Debt;
    public double Payment;
    public double Total_Charged;
    public String Registered_Date;

    public Customers(String Cust_Name, String Phone_No, String Address, double Debt, double Payment, double Total_Charged, String Registered_Date) {
        this.Cust_Name = Cust_Name;
        this.Phone_No = Phone_No;
        this.Address = Address;
        this.Debt = Debt;
        this.Payment = Payment;
        this.Total_Charged = Total_Charged;
        this.Registered_Date = Registered_Date;
    }

    public Customers(String Cust_Name, String Phone_No, String Address, double Debt, double Payment, double Total_Charged) {
        this.Cust_Name = Cust_Name;
        this.Phone_No = Phone_No;
        this.Address = Address;
        this.Debt = Debt;
        this.Payment = Payment;
        this.Total_Charged = Total_Charged;
    }

    public Customers(String Cust_Name, String Phone_No, String Address, double Total_Charged, String Registered_Date) {
        this.Cust_Name = Cust_Name;
        this.Phone_No = Phone_No;
        this.Address = Address;
        this.Total_Charged = Total_Charged;
        this.Registered_Date = Registered_Date;
    }

    public Customers(String Cust_Name) {
        this.Cust_Name = Cust_Name;
    }

    
    
    
   

    public Customers() {
    }

    public String getCust_Name() {
        return Cust_Name;
    }

    public void setCust_Name(String Cust_Name) {
        this.Cust_Name = Cust_Name;
    }

    public String getPhone_No() {
        return Phone_No;
    }

    public void setPhone_No(String Phone_No) {
        this.Phone_No = Phone_No;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public double getDebt() {
        return Debt;
    }

    public void setDebt(double Debt) {
        this.Debt = Debt;
    }

    public double getPayment() {
        return Payment;
    }

    public void setPayment(double Payment) {
        this.Payment = Payment;
    }

    public double getTotal_Charged() {
        return Total_Charged;
    }

    public void setTotal_Charged(double Total_Charged) {
        this.Total_Charged = Total_Charged;
    }

    public String getRegistered_Date() {
        return Registered_Date;
    }

    public void setRegistered_Date(String Registered_Date) {
        this.Registered_Date = Registered_Date;
    }

    
    
    
    
    
}
