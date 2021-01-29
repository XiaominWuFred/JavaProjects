import java.util.ArrayList;
/**
 * The Ride Object
 * @author Xiaomin Wu
 *
 */
public class Ride {
	private int duration;
	private int timeLeft;
	private String Name;
	private VirtualLine virtualLine;
	private HoldingQueue holdingQueue;
	private ArrayList<Person> peopleOnRide = new ArrayList<Person>();
	private int capacity;
	private int PersonPlayed;

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	

	public int getPersonPlayed() {
		return PersonPlayed;
	}

	public void setPersonPlayed(int personPlayed) {
		PersonPlayed = personPlayed;
	}

	public Ride(String name){
		this.Name = name;
		this.PersonPlayed = 0;
		this.virtualLine = new VirtualLine();
		this.holdingQueue = new HoldingQueue();
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public VirtualLine getVirtualLine() {
		return virtualLine;
	}
	public void setVirtualLine(VirtualLine virtualLine) {
		this.virtualLine = virtualLine;
	}
	public HoldingQueue getHoldingQueue() {
		return holdingQueue;
	}
	public void setHoldingQueue(HoldingQueue holdingQueue) {
		this.holdingQueue = holdingQueue;
	}
	public ArrayList<Person> getPeopleOnRide() {
		return peopleOnRide;
	}
	public void setPeopleOnRide(ArrayList<Person> peopleOnRide) {
		this.peopleOnRide = peopleOnRide;
	}
	/**
	 * compare object o with this Ride object
	 * if name equal, return true
	 * else return false
	 * @param o
	 * input object
	 */
	public boolean equals(Object o){
		if(o instanceof Ride){
			if(((Ride)o).getName().equals(this.getName())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	/**
	 * print infomation of the object
	 * return string
	 * string contain infomation
	 */
	public String toString(){
		String onRide = "";
		for(Person a: this.getPeopleOnRide()){
			onRide = onRide + a.getLevel()+" "+a.getNumber() + ", ";
		}
		
		String hold = "";
		for(Object a: this.getHoldingQueue()){
			hold = hold + ((Person)a).getLevel()+" "+((Person)a).getNumber() + ", "; 
		}
		
		String VL = "";
		for(Object a: this.getVirtualLine()){
			VL = VL + ((Person)a).getLevel()+" "+((Person)a).getNumber() + ", "; 
		}
		
		return this.getName() + "- Time remaining: "+this.getTimeLeft()+" min\n"
					+"On Ride: " + onRide + "\n"
					+"Holding Queue: " + hold+"\n"
							+ "Virtual Queue: " + VL+"\n"
							+"----------------------------------------\n";
	}
	
}
