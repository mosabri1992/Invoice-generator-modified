/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author CompuFast
 */
public class TableModel extends AbstractTableModel {
    private ArrayList <Invoice> invoice;
    private String[] columns = {"Number","Date","customer","total"};
    

    public TableModel(ArrayList<Invoice> invoice) {
        this.invoice = invoice;
    }

    
    @Override
    public int getRowCount() {
      
        return invoice.size();
    
    }

    @Override
    public int getColumnCount() {
     
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
      
      return columns[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

     Invoice invoice1 = invoice.get(rowIndex); 
     switch(columnIndex){
         
         case 0 : return invoice1.getIndex();
         case 1 : return invoice1.getDate();
         case 2 : return invoice1.getCustomer();
         case 3 : return invoice1.gettotal();
         default:return "";
     }
    }
}
