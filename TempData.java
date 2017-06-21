package weatherpkg;

public class TempData extends Time{

	private double averageFarenheit; 
	
	public TempData (int time, double temp) 
	{
		super(time);
		averageFarenheit = temp;
	}
	public TempData (int time, int temp1, int temp2) 
	{
		super(time);
		averageFarenheit =  ( (double) temp1 + temp2 ) / 2 ;
	}	
	public double getAverageFarenheit() 
	{
		return averageFarenheit;
	}
	public void setAverageFarenheit(double averageFarenheit) 
	{
		this.averageFarenheit = averageFarenheit;
	}
	public String toString () 
	{
		String stringData = "Time: " + getTime() + ", AvgTemp: " + averageFarenheit;
		return stringData;
	}
}
