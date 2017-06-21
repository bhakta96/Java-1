package weatherpkg;

import java.util.ArrayList;

public interface PredictorADT {
	public void addDay (DayStats newDay);
	public DayStats getDay (DayStats tester);
	public DayStats predictDay (int m, int d, int y);
	
	public TempData predictAverageDailyTemp (int m, int d, int y);
	public ArrayList<TempData> predictAverageDailyTemps (int m, int d, int y, int numDays);
	
	public WindData predictAverageDailyWind (int m, int d, int y);
	public ArrayList<WindData> predictAverageDailyWinds (int m, int d, int y, int numDays);	
}
