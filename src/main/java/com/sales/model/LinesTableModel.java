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
public class LinesTableModel extends AbstractTableModel{
    
    
    private ArrayList<Line> lines ;
    private String[] columns = {"Number","itemname","itemprice","itemcount","itemtotal"};

    public LinesTableModel(ArrayList<Line> lines) {
        this.lines = lines;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }
    

    @Override
    public int getRowCount() {
       
        return lines.size();
       
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
        
        Line line = lines.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return line.getInvoice().getIndex();
               case 1: return line.getItemname(); 
               case 2: return line.getPrice();
               case 3: return line.getAmmount();
               case 4: return line.getlinetotal();
            default:
                return "";
        }
    }
    
}
