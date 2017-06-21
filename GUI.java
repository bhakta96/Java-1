// Asav Bhakta
// Project 2 GUI weather prediction 
package weatherpkg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Point;
import javax.swing.JSlider;


import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import javax.swing.JScrollBar;
import org.eclipse.ui.internal.MessageLine;
import java.awt.Frame;
import org.eclipse.swt.awt.SWT_AWT;
import java.awt.BorderLayout;
import javax.swing.JRootPane;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.jface.viewers.TableViewer;


public class GUI {
	

	protected Shell shlWeatherPrediction;

	/**
	 * Launch the application.
	 * @param args
	 */
	
	
	private int Month = 1,Day = 1,Year= 2016;
	int MONTH;
	private int i = 0;
	Label label_1;
	Predictor predict = new Predictor();
	boolean avgtemp;
	boolean getDay;

	
	
	public static void main(String[] args) {
		
		
		try {
			GUI window = new GUI();
			window.open();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlWeatherPrediction.open();
		shlWeatherPrediction.layout();
		while (!shlWeatherPrediction.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	
	protected void createContents() {
		shlWeatherPrediction = new Shell();
		shlWeatherPrediction.setImage(SWTResourceManager.getImage(GUI.class, "/weatherpkg/sky.jpg"));
		shlWeatherPrediction.setSize(818, 595);
		shlWeatherPrediction.setText("Weather Prediction");
		
		Button PredictTemp = new Button(shlWeatherPrediction, SWT.NONE);
		PredictTemp.setGrayed(true);
		PredictTemp.setBounds(174, 196, 117, 35);
		PredictTemp.setText("Predict Temp");
		
		
		Label TextBox = new Label(shlWeatherPrediction, SWT.WRAP | SWT.CENTER);
		TextBox.setImage(SWTResourceManager.getImage(GUI.class, "/weatherpkg/sky.jpg"));
		TextBox.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		TextBox.setBounds(174, 136, 436, 35);
		
		
		Scale Mslider = new Scale(shlWeatherPrediction, SWT.NONE);
		Mslider.setBackground(SWTResourceManager.getColor(135, 206, 250));
		Mslider.setForeground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		Mslider.setMaximum(12);
		Mslider.setMinimum(1);
		Mslider.setBounds(10, 432, 160, 54);
		
		Scale Dslider = new Scale(shlWeatherPrediction, SWT.NONE);
		Dslider.setBackground(SWTResourceManager.getColor(135, 206, 250));
		Dslider.setMinimum(1);	
		Dslider.setBounds(294, 432, 190, 54);
		
		
		Scale Yslider = new Scale(shlWeatherPrediction, SWT.NONE);
		Yslider.setBackground(SWTResourceManager.getColor(135, 206, 250));
		Yslider.setMaximum(2017);
		Yslider.setMinimum(2016);
		Yslider.setBounds(627, 432, 160, 54);
		
		Label month = new Label(shlWeatherPrediction, SWT.WRAP | SWT.CENTER);
		month.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		month.setBounds(46, 492, 93, 25);
		month.setText("Month");
		
		Label day = new Label(shlWeatherPrediction, SWT.WRAP | SWT.CENTER);
		day.setBounds(351, 492, 81, 25);
		day.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		day.setText("Day");
		
		Label year = new Label(shlWeatherPrediction, SWT.WRAP | SWT.CENTER);
		year.setBounds(670, 492, 81, 25);
		year.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		year.setText("Year");
		
		Button AvgWinds = new Button(shlWeatherPrediction, SWT.RADIO);
		AvgWinds.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		AvgWinds.setImage(null);
		AvgWinds.setBounds(591, 329, 160, 25);
		AvgWinds.setText("Avg Daily Winds");
		
		Button AvgTemps = new Button(shlWeatherPrediction, SWT.RADIO);
		AvgTemps.setBounds(10, 329, 160, 25);
		AvgTemps.setText("Avg Daily Temps");
		
		
		test h = new test();
		Spinner numDays = new Spinner(shlWeatherPrediction, SWT.BORDER);
		numDays.setMaximum(30);
		numDays.setMinimum(1);
		numDays.setBounds(340, 302, 72, 31);
		
		// animation window 
		Composite composite = new Composite(shlWeatherPrediction, SWT.EMBEDDED);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		Composite composite1 = new Composite(shlWeatherPrediction, SWT.EMBEDDED);
		composite1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		
		Label lblNumDays = new Label(shlWeatherPrediction, SWT.WRAP | SWT.CENTER);
		lblNumDays.setBounds(329, 339, 93, 25);
		lblNumDays.setText("Num Days");
		
		Button GetDay = new Button(shlWeatherPrediction, SWT.RADIO);
		GetDay.setBounds(10, 269, 95, 25);
		GetDay.setText("Get Day");
		
		Button PredictDay = new Button(shlWeatherPrediction, SWT.RADIO);
		PredictDay.setBounds(591, 269, 119, 25);
		PredictDay.setText("Predict Day");
		
		Button PredictWind = new Button(shlWeatherPrediction, SWT.NONE);
		PredictWind.setBounds(493, 196, 117, 35);
		PredictWind.setText("Predict Wind");
		
		label_1 = new Label(shlWeatherPrediction, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(GUI.class, "/weatherpkg/sky.jpg"));
		label_1.setBounds(0, 0, 844, 539);
		
		 
		//set number of days
		h.setnumDays(numDays.getText());
		
		numDays.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				h.setnumDays(numDays.getText());
				
			}
		});
		
		
		
		
		// select month by using slider
		Mslider.addDragDetectListener(new DragDetectListener() {
			public void dragDetected(DragDetectEvent arg0) {
				Mslider.addMouseMoveListener(new MouseMoveListener() {
					public void mouseMove(MouseEvent arg0) {
						Month = Mslider.getSelection();
						
						test h = new test();
						h.setMonth(Month);
						
						String s = null;
						if(Month == 1)
						{
							s = "January";
						}
						else if(Month == 2 )
						{						
							s= "Feburary";
						}
					
						else if(Month == 3)
						{
							s= "March";
						}
						else if(Month == 4)
						{
							s= "April";
						}
						else if(Month == 5)
						{
							s= "May";
						}
						else if(Month == 6)
						{
							s= "June";
						}
						else if(Month == 7)
						{
							s= "July";
						}
						else if(Month == 8)
						{
							s= "August";
						}
						else if(Month == 9)
						{
							s= "September";
						}
						else if(Month == 10)
						{
							s= "October";
						}
						else if(Month == 11)
						{
							s= "November";
						}
						else if(Month == 12)
						{
							s= "December";
						}
						
						month.setText(s);
					
					}
				});
			}
		});
		
		// select year by using slider
		Yslider.addDragDetectListener(new DragDetectListener() {
			public void dragDetected(DragDetectEvent arg0) {
				Yslider.addMouseMoveListener(new MouseMoveListener() {
					public void mouseMove(MouseEvent arg0) {
						Year = Yslider.getSelection();
						test h = new test();
						h.setYear(Year);
						year.setText(String.valueOf(Year));
					}
				});
			}
		});
		
		//select day by using slider
		Dslider.addDragDetectListener(new DragDetectListener() {
			public void dragDetected(DragDetectEvent arg0) {
				Dslider.addMouseMoveListener(new MouseMoveListener() {
					public void mouseMove(MouseEvent arg0) {
					
						if(Month == 1)
						{
							Dslider.setMaximum(31);
						}
						else if(Month == 2 && Year == 2016)
						{
							Dslider.setMaximum(29);
						}
						else if(Month == 2)
						{
							Dslider.setMaximum(28);
						}
						else if(Month == 3)
						{
							Dslider.setMaximum(31);
						}
						else if(Month == 4)
						{
							Dslider.setMaximum(30);
						}
						else if(Month == 5)
						{
							Dslider.setMaximum(31);
						}
						else if(Month == 6)
						{
							Dslider.setMaximum(30);
						}
						else if(Month == 7)
						{
							Dslider.setMaximum(31);
						}
						else if(Month == 8)
						{
							Dslider.setMaximum(31);
						}
						else if(Month == 9)
						{
							Dslider.setMaximum(30);
						}
						else if(Month == 10)
						{
							Dslider.setMaximum(31);
						}
						else if(Month == 11)
						{
							Dslider.setMaximum(30);
						}
						else if(Month == 12)
						{
							Dslider.setMaximum(31);
						}
						Day = Dslider.getSelection();
						test h = new test();
						h.setDay(Day);
						day.setText(String.valueOf(Day));
					}
				});
			}
		});
		// predict temperature if button is pressed
		PredictTemp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				TempData s = predict.predictAverageDailyTemp(Month, Day, Year);
				TextBox.setText(s.toString());
				
			}
		});
		// predict wind if button is pressed
	PredictWind.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				WindData s = predict.predictAverageDailyWind(Month, Day, Year);
				TextBox.setText(s.toString());
			}
		});
	// will call Animation class if radio button AvgTemp
		AvgTemps.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				test h = new test();
				h.setbool(true);
				composite1.setVisible(false);
				composite.setBounds(0, 0, 850, 100);
				composite.setVisible(true);
				Frame frame_1 = SWT_AWT.new_Frame(composite);
				
				Panel panel = new Panel();
				frame_1.add(panel);
				panel.setLayout(new BorderLayout(0, 0));
				
				JRootPane rootPane = new JRootPane();
				panel.add(rootPane);
				rootPane.getContentPane().add(new Animation());
			}
		});
		// will call Animation class if radio button AvgWind
		AvgWinds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				test h = new test();
				h.setbool(false);
				
				Point i = shlWeatherPrediction.getSize();
				
				composite.setVisible(false);
				composite1.setVisible(true);
				composite1.setBounds(0, 0, 850, 100);
				
				Frame frame_2 = SWT_AWT.new_Frame(composite1);
				
				Panel panel1 = new Panel();
				frame_2.add(panel1);
				panel1.setLayout(new BorderLayout(0, 0));
				
				JRootPane rootPane = new JRootPane();
				panel1.add(rootPane);
				rootPane.getContentPane().add(new Animation());
			}
		});
		//  will call Table class if radio button GetDay is pressed
		GetDay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				test h = new test();
				h.setboolDay(true);
				JRootPane rootPane = new JRootPane();
				rootPane.getContentPane().add(new Table());
			}
		});
		
	//  will call Table class if radio button PredictDay is pressed
		PredictDay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				test h = new test();
				h.setboolDay(false);
				JRootPane rootPane = new JRootPane();
				rootPane.getContentPane().add(new Table());
			}
		});
	
	}
	
	
	
}
class test
{
	private static int Month=1,Day=1,Year=2016;
	private static boolean avgTemp,gDay;
	private static String numDays;
	public void c()
	{}
	
	public static void setMonth(Integer month) {
		
		 Integer i =  new Integer(month);
		 Month= i.intValue();
		
	}
	public static void setDay(Integer day) {
		
		 Integer i =  new Integer(day);
		 Day= i.intValue();
		
	}
	public static void setYear(Integer year) {
		
		 Integer i =  new Integer(year);
		 Year= i.intValue();
		
	}
	public static void setnumDays(String numdays) {
		
		 numDays= numdays;
		
	}
	public static void setbool(boolean avgtemp)
	{
		avgTemp = avgtemp;
	}
	public static void setboolDay(boolean gday)
	{
		gDay = gday;
	}
	public static int getMonth()
	{
		return Month;
	}
	public static int getDay()
	{
		return Day;
	}
	public static int getYear()
	{
		return Year;
	}
	public static String getnumDays()
	{
		return numDays;
	}
	public static boolean getbool()
	{
		return avgTemp;
	}
	public static boolean getboolDay()
	{
		return gDay;
	}
}
