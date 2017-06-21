package weatherpkg;

public class Time implements Comparable<Time> {
	private int time;
	
	public Time (int t){
		time = t;
	}

	public int compareTo(Time rhs) {

		if (time < rhs.getTime())
			return -1;
		else if (time > rhs.getTime())
			return 1;
		return 0;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
