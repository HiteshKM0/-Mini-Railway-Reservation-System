package Passenger;
public class Passenger {
	static int id=1;
	public String name;
	public int age;
	public String berthPref;
	public int passId;
	public String alloted;
	public int seatNum;
	public Passenger(String name, int age, String berthPref){
		this.name=name;
		this.age=age;
		this.berthPref=berthPref;
		this.passId=id++;
		alloted="";
		seatNum=-1;
	}
}
