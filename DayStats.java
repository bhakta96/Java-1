package weatherpkg;

import java.util.TreeSet;

public class DayStats implements Comparable<DayStats> 
{
	private int month;
	private int day;
	private int year;
	private TreeSet<TempData> dailyTemps;
	private TreeSet<WindData> dailyWinds;
	
	public DayStats()
	{
		;
	}
	public DayStats (int m, int d, int y) 
	{
		month = m;
		day = d;
		year = y;
		dailyTemps = new TreeSet<TempData>();
		dailyWinds = new TreeSet<WindData>();
	}
	public String toString () {
		String toReturn = "";
		toReturn += month +"/"+day+"/"+year+":\n";
		toReturn += dailyTemps.toString()+"\n";
		toReturn += dailyWinds.toString();
		return toReturn;
	}
	public int getMonth() 
	{
		return month;
	}
	public void setMonth(int month) 
	{
		this.month = month;
	}
	public int getDay() 
	{
		return day;
	}
	public void setDay(int day) 
	{
		this.day = day;
	}
	public int getYear() 
	{
		return year;
	}
	public void setYear(int year) 
	{
		this.year = year;
	}
	public void addTemp (TempData t) 
	{
		dailyTemps.add(t);
	}
	public void addWind (WindData w) 
	{
		dailyWinds.add(w);
	}	
	public int getAverageTemp () 
	{
		if (dailyTemps.size()<=0)
			return -999;
		int averageTemp = 0, total = 0;
		for (TempData tempData : dailyTemps)
		{
			total += tempData.getAverageFarenheit();
		}
		averageTemp = total / dailyTemps.size();
		return averageTemp;
	}
	public int getAverageWindSpeed () 
	{
		if (dailyWinds.size()<=0)
			return -999;		
		int averageWindSpeed = 0, total = 0;
		for (WindData windData : dailyWinds)
		{
			total += windData.getSpeed();
		}
		averageWindSpeed = total / dailyWinds.size();
		return averageWindSpeed;
	}	
	public int getAverageWindDirection () 
	{
		int averageWindDirection = 0, total = 0;
		for (WindData windData : dailyWinds)
		{
			total += windData.getDirection();
		}
		averageWindDirection = total / dailyWinds.size();
		return averageWindDirection;
	}	
	public int compareTo(DayStats rhs) 
	{
		if ( year < rhs.year ) return -1;
		else if ( year > rhs.year ) return +1;
		if ( month < rhs.month ) return -1;
		else if ( month > rhs.month ) return +1;
		if ( day < rhs.day ) return -1;
		else if ( day > rhs.day ) return +1;
			
		return 0;
	}
	public boolean equals (DayStats rhs)
	{
		return this.compareTo(rhs)==0;
	}
	public WindData get(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
