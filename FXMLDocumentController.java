/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



/**
 *
 * @author pujit
 */
public class FXMLDocumentController  implements Initializable 
{
    private String currentOperation;
    
    private double currentnum=0;
    
    List<Double> listOperand = new ArrayList<>();
    List<String> listOperator = new ArrayList<>();
    String str = "";
    
    //private static final String FILENAME="C:\\Users\\pujit\\Desktop\\Output.txt";
    
    @FXML
    private TextField Display; 
    
    @FXML 
    private Button Clear;
    
    @FXML 
    private Button Toggle;
    
    @FXML 
    private Button EqualTo;
    
    @FXML
    private void handleButtonClearAction(ActionEvent event) 
    {
        Display.setText("");
        listOperand.clear();
        listOperator.clear();
        currentnum = 0;
    }
    public static void fileAppender(String str)
    {
        try
        {
            //String savestr = "C:\\Users\\pujit\\Desktop\\mysave.txt"; 
       	    File f = new File("C:\\Users\\pujit\\OneDrive\\Documents\\NetBeansProjects\\Calculator\\src\\calculator\\Calculator.txt");

            PrintWriter out = null;
	    if ( f.exists() && !f.isDirectory() ) 
            {
		out = new PrintWriter(new FileOutputStream(new File("C:\\Users\\pujit\\OneDrive\\Documents\\NetBeansProjects\\Calculator\\src\\calculator\\Calculator.txt"), true));
                out.println();
                out.append("\n"+str);
		out.close();
	    }
            else 
            {
		out = new PrintWriter("C:\\Users\\pujit\\OneDrive\\Documents\\NetBeansProjects\\Calculator\\src\\calculator\\Calculator.txt");
                
	        out.println(str+"\n");
		out.close();
	    }
        }
	catch(Exception e)
	{
            e.printStackTrace();
	}
    }
    
    @FXML
    private void handleButtonToggleAction(ActionEvent event) 
    {
        try
        {
            String value=Display.getText();
        
            Double num1=Double.parseDouble(value);
        
            num1=num1*(-1);
        
            Display.setText(String.valueOf(num1));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    @FXML
    private void handleButtonNumberAction(ActionEvent event) 
    {
        
        String Number=((Button)event.getSource()).getText();
        //System.out.println("Number is"+Number);
        
        String oldtext=Display.getText();
       //System.out.println("OldText"+oldtext);
        
        
        
        String newText=oldtext+Number;
        //System.out.println("NewText"+ newText+"");
        Display.setText(newText);
    }
    
    @FXML
    private void handleButtonEqualToAction(ActionEvent event) throws IOException
    {
        str = "";
        String newText="";
        newText = Display.getText();
        
        //String newText=Display.getText();
        //System.out.println("newtext"+newText+"yippie");
        //int i = 0;
        if(!newText.equals("") && newText != null && newText != " ")
        {
        Double newNum= Double.parseDouble(newText);
        //System.out.println(newNum);
        listOperand.add(newNum);
        }
        
        if(listOperator.contains("%"))
        {
            for(int i=0;i<listOperator.size();i++)
            {
                str += listOperand.get(i);
                str += listOperator.get(i);
            }
        }
        else
        {
        for(int i=0;i<listOperator.size();i++)
        {
            str += listOperand.get(i);
            //System.out.println("total"+listOperand.get(i));
            str += listOperator.get(i);
            //System.out.println(str);
        }
        str += listOperand.get(listOperand.size()-1);
        }
        //System.out.println(str);
        
        while(!listOperator.isEmpty())
        //pw.write("ho");
        {
        
        if(listOperator.contains("/"))
        {
            int index = listOperator.indexOf("/");
            listOperator.remove(index);
            if(listOperand.get(index+1) == 0)
            {
                Display.setText("Cannot Compute");
                return;
            }
            currentnum= listOperand.get(index)/listOperand.get(index+1);
            
            listOperand.set(index+1, currentnum);
            listOperand.remove(index);
        }
        else if(listOperator.contains("X"))
        {
            int index = listOperator.indexOf("X");
            listOperator.remove(index);
            currentnum= listOperand.get(index)*listOperand.get(index+1);
            listOperand.set(index+1, currentnum);
            listOperand.remove(index);
        }
        else if(listOperator.contains("-"))
        {
            int index = listOperator.indexOf("-");
            listOperator.remove(index);
            currentnum= listOperand.get(index)-listOperand.get(index+1);
            listOperand.set(index+1, currentnum);
            listOperand.remove(index);
        }
        else if(listOperator.contains("+"))
        {
            int index = listOperator.indexOf("+");
            listOperator.remove(index);
            currentnum= listOperand.get(index)+listOperand.get(index+1);
            listOperand.set(index+1, currentnum);
            listOperand.remove(index);
        }
        else if(listOperator.contains("%"))
        {
            int index = listOperator.indexOf("%");
            listOperator.remove(index);
            currentnum= currentnum/100;
            listOperand.set(index, currentnum);
            //listOperand.remove(index);
        }
        
        
        Display.setText(""+listOperand.get(0));
        
        }
        str = str+"="+listOperand.get(0);
        fileAppender(str);
        
    }
    
    @FXML
    private void handleButtonOperationAction(ActionEvent event) 
    {
        String currentText=Display.getText();
        //System.out.println(currentText);
        currentnum=Double.parseDouble(currentText);
        
        listOperand.add(currentnum);
        
        
        Display.setText("");
        currentOperation=((Button)event.getSource()).getText();
        //System.out.println(currentOperation);
        listOperator.add(currentOperation);
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
}

