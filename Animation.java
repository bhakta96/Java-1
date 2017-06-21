package weatherpkg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextPane;
import javax.swing.JPopupMenu;
import java.awt.Canvas;
import java.awt.ScrollPane;
import java.awt.Toolkit;


public class Animation extends JPanel{
	
	public Animation() {
	}
	
	int x = 0, y = 90, x_1 = 0; 
	Predictor predict = new Predictor();
	GUI call = new GUI();
	int i = 0;
	int j = 0;
	
	public void paint(Graphics g )
	{
		super.paint(g);
		
		test x1 = new test(); // will call test class to get information 
		int month = x1.getMonth();
		int day = x1.getDay();
		int year = x1.getYear();
		
		String numDays = x1.getnumDays();
		String strngValue;
		setBackground(new Color(30, 140, 255));
		
		g.create();
		int index = 0;
		
		if (x1.getbool()) // if radio button AvgTemp is pressed then will execute following code 
		{
			ArrayList<TempData> s = predict.predictAverageDailyTemps(month,day, year, Integer.parseInt(numDays));
			TempData check = s.get(i);
			strngValue = String.valueOf(check);
			index = strngValue.length();
		}
		else //if radio button AvgWind is pressed then will execute following code 
		{
			ArrayList<WindData> s = predict.predictAverageDailyWinds(month, day, year, Integer.parseInt(numDays));
			WindData check = s.get(i);
			strngValue = String.valueOf(check);
			
			index = strngValue.length();
			
		}
		
		
		int check = Integer.parseInt(numDays);
		Graphics2D g1 = (Graphics2D)g;
		Font font = new Font ("TimesNewRoman", Font.BOLD+Font.PLAIN,40);
		g1.setFont(font);
		g1.setColor(Color.black);
		g1.drawString(strngValue, x, y); // will display the string 
		

		try {Thread.sleep(100);}catch(Exception ex){}
		
		x+=10;
	
		if(x>this.getWidth()) // will move the text
		{
			x=0;
			if(i == check-1)
			{
				return;
				
			}
			else 
				i = i + 1;
			
		}
		repaint();
	}
	
}

