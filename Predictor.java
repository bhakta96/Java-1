package weatherpkg;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Predictor implements PredictorADT 
{
	private TreeSet<DayStats> allDays;

	public Predictor () 
	{
		allDays = new TreeSet<DayStats>();
		ProcessFile();
	}
	public void addDay (DayStats newDay) 
	{
		allDays.add(newDay);
	}
	public DayStats getDay (DayStats tester) 
	{ 
		DayStats toReturn = allDays.floor(tester);
		if (toReturn == null)
			return null;
		if (toReturn.equals(tester))
			return toReturn;		
		return null;
	}
	@Override
	public DayStats predictDay(int m, int d, int y) 
	{ 
		DayStats day = new DayStats(m, d, 2016);
		DayStats toReturn = getDay(day);
		if (toReturn == null)
			toReturn = allDays.ceiling(day);
		if (toReturn == null)
			toReturn = allDays.floor(day);		
		return toReturn;
	}
	@Override
	public TempData predictAverageDailyTemp(int m, int d, int y) 
	{
		DayStats day = predictDay(m, d, y);
		TempData avgTemp = new TempData(1200, day.getAverageTemp());
		return avgTemp;
	}
	@Override
	public ArrayList<TempData> predictAverageDailyTemps(int m, int d, int y, int numDays) 
	{
		ArrayList<TempData> toReturn = new ArrayList<TempData>();
		for (int i = 0; i < numDays; i++ )
			{
				toReturn.add(predictAverageDailyTemp(m, d+i, y));
			}
		return toReturn;
	}
	@Override
	public WindData predictAverageDailyWind(int m, int d, int y) 
	{
		DayStats day = predictDay(m, d, y);
		WindData avgWind = new WindData(1200, day.getAverageWindSpeed(), day.getAverageWindDirection());
		return avgWind;
	}
	@Override
	public ArrayList<WindData> predictAverageDailyWinds(int m, int d, int y, int numDays) 
	{
		ArrayList<WindData> toReturn = new ArrayList<WindData>();
		for (int i = 0; i < numDays; i++ )
			{
				toReturn.add(predictAverageDailyWind(m, d+i, y));
			}
		return toReturn;
	}
	
	public void ProcessFile () {
		InputStreamReader myInfile = null;
		try
		{
			myInfile = new InputStreamReader(getClass().getResourceAsStream("/weatherpkg/weatherDataAll.txt"));
		}
		catch (Exception e) {System.err.println("Didn't open file");}	
		
		Scanner scan = new Scanner(myInfile);
		try
		{
			String aLine = null;
			for (int i=0; i < 6; i++)
				aLine = scan.nextLine();  // consume headings
			// sample line 13963,20170101,53,12,OVC006, ,5, ,BR, ,46, ,7.8, ,46, ,7.7, ,46, ,7.8, ,100, ,6, ,20, , , ,29.66, , , , , ,29.95, ,AA, ,  T, ,29.94, 
			while ((aLine = scan.nextLine())!=null) {
				String[] tokens = aLine.split(",");
				String dateToken = tokens[1];
				try {
					int m = Integer.parseInt(dateToken.substring(4, 6));
					int d = Integer.parseInt(dateToken.substring(6, 8));
					int y = Integer.parseInt(dateToken.substring(0, 4));
					DayStats testAdd = new DayStats(m,d,y);
					DayStats toAdd = this.getDay(testAdd);
					if (toAdd == null) {
						// this day has never been added so add newly create day
						toAdd = testAdd;
						this.addDay(toAdd);
					}
					
					int timeInt = Integer.parseInt(tokens[2]);
					int temp1 = -999;
					try {
						temp1 = Integer.parseInt(tokens[10].trim()); // trim removes extra white space
					} catch (Exception e) {
						// ignore this temp entry it must not be a valid integer
					}
					int temp2 = -999;
					try {
						temp2 = Integer.parseInt(tokens[14].trim()); // trim removes extra white space
					} catch (Exception e) {
						// ignore this temp entry it must not be a valid integer
					}
					if (temp1 != -999 && temp2 != -999){
						toAdd.addTemp(new TempData(timeInt,temp1,temp2));
					}
					else if (temp1 == -999 && temp2 != -999){
						toAdd.addTemp(new TempData(timeInt,temp2));
					}
					else if (temp1 != -999 && temp2 == -999){
						toAdd.addTemp(new TempData(timeInt,temp1));
					}	
					// else just ignore this temp entry because neither was a valid temp
					

					try {
						int windspeed = Integer.parseInt(tokens[24].trim()); 
						int winddirection = Integer.parseInt(tokens[26].trim()); 
						toAdd.addWind(new WindData(timeInt,windspeed,winddirection));
					} catch (Exception e) {
						// ignore this wind entry if anything could not be parsed
					}				
					//System.out.println(toAdd);
				} catch (Exception e) {
					// Ignore this entire line because there was a problem with either the date
					// or the time 
				}

			}

		}
		catch (Exception e){      
			if (myInfile == null)
				return;
			try
			{
				myInfile.close( );
				scan.close();
			}
			catch (IOException e2) { }

		}		
	}		
}
