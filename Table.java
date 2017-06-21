package weatherpkg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

public class Table extends JPanel {
	
	
	public Table() {
	
	
	Predictor predict = new Predictor();
	GUI call = new GUI();
	        
	test x1 = new test();
	int month = x1.getMonth();
	int day = x1.getDay();
	int year = x1.getYear();
	
	        // create JFrame and JTable
	        JFrame frame = new JFrame();
	        JTable table = new JTable(); 
	        String[] v;
	        
			if (x1.getboolDay()) // if getDay is pressed
			{
				
				if( month > 2 && year == 2017)
				{
					System.out.println("You cannot get Information from future, so I will give information from past");
					year = 2016;
				}
				
				
					DayStats x = predict.getDay(new DayStats(month,day,year));
					String y = x.toString();
					v= y.split(",");
				
				
			}
			else //if PredictDay is pressed
			{
				DayStats x = predict.predictDay(month, day, year);
				String y = x.toString();
		    	v= y.split(",");
			}
	        
			// created vector to store value since i dont know the size of data
	    	Vector Time = new Vector(1, 1);
	    	Vector AvgTemp = new Vector(1, 1);
	    	Vector WindS = new Vector(1, 1);
	    	Vector WindD = new Vector(1, 1);
	    	
	    	for(int i = 0; i < v.length; i++) // will check if it is time, wind and etc and store it in respectable 
	    	{                                 // vector 
	    		if(i == 0)
	    		{
	    			
	    			Time.addElement(v[i].substring(16));
	    			
	    		}	
	    		if(v[i].substring(1, 2).contentEquals("T"))
	    		{
	    			String s = v[i].substring(6);
	    			boolean check = true;
	    			
	    			for(int j = 0; j < Time.size(); j++)
	    			{
	    				
	    				if(Time.elementAt(j) .equals( v[i].substring(6)))
	    				{
	    					check = false;
	    				}
	    			}
	    			if(check)
	    			{
	    				Time.addElement(v[i].substring(6));
	    			}
	    		}
	    		if(v[i].substring(1, 4).contentEquals("Avg"))
	    		{
	    			String s = v[i].substring(10);
	    			boolean check = true;
	    			for (int j = 0; j < s.length(); j++)
	    			{
	    				if(s.charAt(j) == ']')
	    				{
	    					check = false;
	    					AvgTemp.addElement(v[i].substring(10, 10 + j));
	    				}
	    			}
	    			if(check)
	    				AvgTemp.addElement(v[i].substring(10));
	    		}
	    		if(v[i].substring(1, 5).contentEquals("WndS"))
	    		{
	    			WindS.addElement(v[i].substring(8));
	    		}
	    		if(v[i].substring(1, 5).contentEquals("WndD"))
	    		{
	    			String s = v[i].substring(8);
	    			boolean check = true;
	    			for (int j = 0; j < s.length(); j++)
	    			{
	    			
	    				if(s.charAt(j) == ']')
	    				{
	    					check = false;
	    					WindD.addElement(v[i].substring(8, 8 + j));
	    				}
	    			}
	    			if(check)
	    				WindD.addElement(v[i].substring(8));
	    		}
	    		
	    			
	    	}
	        // create a table model and set a Column Identifiers to this model 
	        Object[] columns = {"Time","AvgTemp","WindSpeed","WindDirection"}; // column name
	        DefaultTableModel model = new DefaultTableModel();
	        model.setColumnIdentifiers(columns);
	       
	        // set the model to the table
	        table.setModel(model);
	        
	        // Change A JTable Background Color, Font Size, Font Color, Row Height
	        table.setBackground(Color.LIGHT_GRAY);
	        table.setForeground(Color.black);
	        Font font = new Font("",1,22);
	        table.setFont(font);
	        table.setRowHeight(30);
	        
	        // create JTextFields
	        JTextField textTime = new JTextField();
	        JTextField textAvgTemp = new JTextField();
	        JTextField textWndSpd = new JTextField();
	        JTextField textWndDir = new JTextField();
	      
	        // create JScrollPane
	        JScrollPane pane = new JScrollPane(table);
	        pane.setBounds(0, 0, 880, 400);
	        
	        frame.getContentPane().setLayout(null);
	        
	        frame.getContentPane().add(pane);
	        
	        // add JTextFields to the jframe
	        frame.getContentPane().add(textTime);
	        frame.getContentPane().add(textAvgTemp);
	        frame.getContentPane().add(textWndSpd);
	        frame.getContentPane().add(textWndDir);
	    

	        
	        // create an array of objects to set the row data
	        Object[] row = new Object[4];
	        for (int i = 0; i < Time.size();i++)
	        {
	        	 model.addRow(row);
	             model.setValueAt(Time.elementAt(i), i, 0);
	        }
	        for(int i = 0; i<AvgTemp.size();i++)
	        {
	            model.setValueAt(AvgTemp.elementAt(i), i, 1);
	        }
	        for (int i = 0; i < WindS.size(); i++)
	        {
	        	model.setValueAt(WindS.elementAt(i), i, 2);
	        }
	 
	        for (int i = 0; i<WindD.size();i++)
	        {
	        	 model.setValueAt(WindD.elementAt(i), i, 3);
	        }
	                
	
	       frame.setSize(900,450);
	       frame.setLocationRelativeTo(null);
	       frame.setVisible(true);
	}
	    
	}

