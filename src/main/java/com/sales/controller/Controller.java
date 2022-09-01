/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.controller;



import com.sales.model.Invoice;
import com.sales.model.Line;
import com.sales.model.LinesTableModel;
import com.sales.model.TableModel;
import com.sales.view.Interface;
import com.sales.view.InvoiceDialog;
import com.sales.view.LineDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CompuFast
 */
public class Controller implements ActionListener , ListSelectionListener{
    
    
    
    private Interface frame;
    private InvoiceDialog invoiceDialog;
    
    private LineDialog lineDialog;
 
    public Controller(Interface frame)
    {
     this.frame = frame;   
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedindex = frame.getInvoicetable().getSelectedRow();
        if(selectedindex>-1)
        {
            
        
        System.out.println("Selected"+selectedindex);
        Invoice currentinvoice = frame.getInvoice().get(selectedindex);
        frame.getInvoicenumber().setText(""+currentinvoice.getIndex());
        frame.getInvoicedate().setText(currentinvoice.getDate());
        frame.getCstname().setText(currentinvoice.getCustomer());
        frame.getInvoicetotal().setText(""+currentinvoice.gettotal());
        LinesTableModel linestablemodel = new LinesTableModel(currentinvoice.getLines());
        frame.getLinetable().setModel(linestablemodel);
        linestablemodel.fireTableDataChanged();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String actioncommand = e.getActionCommand();
     System.out.println("Action "+ actioncommand);
        switch (actioncommand) {
            case "Load File":
                loadfile();
                break;
                   case "Save File":
                savefile();
                break;
                   case "Create New Invoice":
                CreateNewInvoice();
                break;
                   case "Delete Invoice":
                DeleteInvoice();
                break;
                   case "Save":
                save();
                break;
                   case "Cancel":
                cancel();
                break;
                 case "createInvoiceCancel":
                Createinvoicecancel();
                break;
                 case "createInvoiceOK":
                createInvoiceOK();
                break;
                 case "createLineCancel":
                CreatecreateLineCancel();
                break;
                 case "createLineOK":
                createcreateLineOK();
                break;
           
        }
 
    }

    private void loadfile() {
        
        JFileChooser fc= new JFileChooser();
        try  {
      int result =  fc.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION)
        {
        File header_file = fc.getSelectedFile();
         Path headerpath  = Paths.get(header_file.getAbsolutePath());
         List<String> headerlines = Files.readAllLines(headerpath);
            System.out.println("Invoices have been read");
            ArrayList<Invoice> invoicearray = new ArrayList<>();
            for (String headerline : headerlines){
             String[] headerparts =  headerline.split(",");
             int invoiceindex = Integer.parseInt(headerparts[0]);
              String invoicedate = headerparts[2];
              String invoicename = headerparts[1];
                       Invoice invoice = new Invoice(invoiceindex,invoicedate,invoicename);
                       invoicearray.add(invoice);
            }
            System.out.println("Checkpoint");
            result = fc.showOpenDialog(frame);
            if(result == JFileChooser.APPROVE_OPTION)
            {
                File linefile = fc.getSelectedFile();
                Path linepath = Paths.get(linefile.getAbsolutePath());
                List<String> linelines = Files.readAllLines(linepath);
                    System.out.println("lines have been read");
                 for (String lineline : linelines) 
                 {
                     String lineparts[] = lineline.split(",");
                   int invoiceindex = Integer.parseInt(lineparts[0]) ;
                    String itemname = lineparts[1];
                    double itemprice = Double.parseDouble(lineparts[2]);
                    int count = Integer.parseInt(lineparts[3]);
                    Invoice inv = null ;
                    for (Invoice invoice :invoicearray)
                    {
                        if(invoice.getIndex() == invoiceindex){
                            inv = invoice; 
                            break;
                        }
                    }
                    Line line = new Line(itemname,itemprice,count,inv);
                    inv.getLines().add(line);
                 }
                 System.out.println("Checkpoint");
            }
            
             frame.setInvoice(invoicearray);
             TableModel tablemodel = new TableModel(invoicearray);
             frame.setTablemodel(tablemodel);
             frame.getInvoicetable().setModel(tablemodel);
             frame.getTablemodel().fireTableDataChanged();
        }
        
       
        } 
        catch(IOException ex){ 
        ex.printStackTrace();
        
        }
    }

    private void savefile() {
        
        ArrayList<Invoice> invoices = frame.getInvoice();
        String header = "";
        String line = "";
        for(Invoice invoice : invoices)
        {
            String invoicecsv = invoice.getAsCSV();
            header+=invoicecsv;
            header+="/n";
            for (Line line1 : invoice.getLines()) {
                
                String lcsv = line1.getAsCSV();
                line+=lcsv;
                line+="/n";
                
            }
            try {
            JFileChooser fc = new JFileChooser();
          int result = fc.showSaveDialog(frame);
                    if(result == JFileChooser.APPROVE_OPTION)
                    {
                        File headerfile = fc.getSelectedFile();
                        FileWriter headerwriter = new FileWriter(headerfile);
                        headerwriter.write(header);
                        headerwriter.flush();
                        headerwriter.close();
                        result = fc.showSaveDialog(frame);
                        if (result == JFileChooser.APPROVE_OPTION )
                        {
                            File linefile = fc.getSelectedFile();
                            FileWriter linewriter = new FileWriter(headerfile);
                            linewriter.write(header);
                            linewriter.flush();
                            linewriter.close();
                        }
                        
                    }
            
        } catch(IOException ex){
            
        }
        
        
        } 
                
        
    }

    private void CreateNewInvoice() {
        
        
          invoiceDialog = new InvoiceDialog(frame);
           invoiceDialog.setVisible(true);
        
    }

    private void DeleteInvoice() {
        int selectedrow = frame.getInvoicetable().getSelectedRow();
      
      if(selectedrow >-1)
      {
          frame.getInvoice().remove(selectedrow);
          frame.getTablemodel().fireTableDataChanged();
          
      }
    }

    private void save() {
          String date = frame.getInvoicedate().getText();
      String customer = frame.getCstname().getText();
       int selectedrow = frame.getInvoicetable().getSelectedRow();
        int index1 = Integer.parseInt(frame.getInvoicenumber().getText()) ;
      if(index1 >-1){
      Invoice invoices = new Invoice (index1,date,customer);
      frame.getInvoice().remove(selectedrow);
      frame.getInvoice().add(invoices);
      frame.getTablemodel().fireTableDataChanged();
      }
    }

    private void cancel() {
        
        
        System.exit(0);
        
    }

    private void Createinvoicecancel() {
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog = null;
          
    }

    private void createInvoiceOK() {
      String date = invoiceDialog.getInvDateField().getText();
      String customer = invoiceDialog.getCustNameField().getText();
      int index1 = frame.getindex();
      
      Invoice invoices = new Invoice (index1,date,customer);
      
      frame.getInvoice().add(invoices);
      frame.getTablemodel().fireTableDataChanged();
      frame.getInvoicetable().setRowSelectionInterval(index1-1, index1-1);
      invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog = null;
        
        
        // creates lines dialoge that comes out after the invoice dialoge
        
        lineDialog = new LineDialog(frame);
        lineDialog.setVisible(true);
               
      
      
    }

    private void CreatecreateLineCancel() {

      lineDialog.setVisible(false);
      lineDialog.dispose();
      lineDialog = null;

    }

    private void createcreateLineOK() {
      String item = lineDialog.getItemNameField().getText();
      int count = Integer.parseInt(lineDialog.getItemCountField().getText());
      int price = Integer.parseInt(lineDialog.getItemPriceField().getText());
      
      int selectedinvoice = frame.getInvoicetable().getSelectedRow();
       if(selectedinvoice >-1){
      Invoice invoices = frame.getInvoice().get(selectedinvoice);
      
      Line line = new Line(item, price, count, invoices);
      invoices.getLines().add(line);
           LinesTableModel linesTableModel = (LinesTableModel) frame.getLinetable().getModel();
           linesTableModel.fireTableDataChanged();
      lineDialog.getItemNameField().setText("");
      lineDialog.getItemCountField().setText("");
      lineDialog.getItemPriceField().setText("");;
      }
      
        
        
        }

    
    
}
