/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.model;

/**
 *
 * @author CompuFast
 */
public class Line {
    
    
    private String itemname;
    private double price;
    private int ammount;
    private Invoice invoice ;

    public Line() {
    }
    public Line ( String itemname, double price, int ammount, Invoice invoice) {
        
        this.itemname = itemname;
        this.price = price;
        this.ammount = ammount;
        this.invoice = invoice;
    }
     public double getlinetotal(){
    
    return price * ammount;
    
}
    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
            return "Line{" + "index=" + invoice.getIndex() + ", itemname=" + itemname + ", price=" + price + ", ammount=" + ammount + '}';
    }
    
    public String getAsCSV(){
         
         return invoice.getIndex() +","+itemname+","+price+","+ammount ;
     }
    
}
