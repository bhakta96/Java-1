package weatherpkg;

public class WindData extends Time{

	private int speed;
	private int direction;
	
	public WindData (int time, int s, int d) 
	{
		super(time);
		speed = s;
		direction = d;
	}

	public int getSpeed() 
	{
		return speed;
	}

	public void setSpeed(int speed) 
	{
		this.speed = speed;
	}

	public int getDirection() 
	{
		return direction;
	}

	public void setDirection(int direction) 
	{
		this.direction = direction;
	}

	public String toString () 
	{
		String stringData = "Time: " + getTime() + ", WndSpd: " + speed + ", WndDir: " + direction;
		return stringData;
	}
}
