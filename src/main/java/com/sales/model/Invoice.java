/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.model;
import java.util.ArrayList;
/**
 *
 * @author CompuFast
 */
public class Invoice {
    private int index ;
    private String customer;
    private String date ;
   
    private ArrayList<Line> lines;
    public Invoice() {
        
        
        
        
        
    }

    public Invoice(int index, String customer, String date) {
        this.index = index;
        this.customer = customer;
        this.date = date;
    }
    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Invoice{" + "index=" + index + ", customer=" + customer + ", date=" + date + '}';
    }

    public ArrayList<Line> getLines() {
        if(lines == null)
        {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<Line> line) {
        this.lines = lines;
    }
    
     public double gettotal(){
    
   double total = 0.0; 
   for(Line line : getLines()){
       
       total+= line.getlinetotal();
   }
    return total; 
}
     
     public String getAsCSV(){
         
         return index +","+date+","+customer;
     }
     
}
