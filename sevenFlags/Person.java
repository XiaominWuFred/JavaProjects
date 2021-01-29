import java.util.ArrayList;

/**
 * person Object
 * @author Xiaomin Wu
 *
 */
public class Person {
	private int number;
	private int maxLines;
	private ArrayList<Ride> lines = new ArrayList<Ride>();
	private Status status;
	private int tooked;
	private String level;
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * default constructor
	 * precondition:
	 * number is not negative
	 * @param number
	 * @throws
	 * IllegalArgumentException
	 * indicates that the input number is negative
	 */
	public Person(int number) throws IllegalArgumentException{
		if(number < 0){
			throw new IllegalArgumentException("Person number wrong");
		}else{
			this.number = number;
			this.tooked = 0;
		}
	}

	public int getTooked() {
		return tooked;
	}

	public void setTooked(int tooked) {
		this.tooked = tooked;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getMaxLines() {
		return maxLines;
	}

	public void setMaxLines(int maxLines) {
		this.maxLines = maxLines;
	}



	public ArrayList<Ride> getLines() {
		return lines;
	}

	public void setLines(ArrayList<Ride> lines) {
		this.lines = lines;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * compare object o with this Person object
	 * if number and maxLines equal, return true
	 * else return false
	 * @param o
	 * input object
	 */
	public boolean equals(Object o){
		if(o instanceof Person){
			if(((Person)o).getMaxLines() == this.getMaxLines() && ((Person)o).getNumber() == this.getNumber()){
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
		if(this.getMaxLines() == 1){
			return this.number + ".\t\t"+this.getLines().get(0).getName()+"\t\t"+this.getStatus()+"\n";
		}else if(this.getMaxLines() == 2){
			return this.number + ".\t\t"+this.getLines().get(0).getName()+"\t\t"+this.getLines().get(1).getName()+"\t\t"+this.getStatus()+"\n";
		}else if(this.getMaxLines() == 3){
			return this.number + ".\t\t"+this.getLines().get(0).getName()+"\t\t"+this.getLines().get(1).getName()+"\t\t"+this.getLines().get(2).getName()+"\t\t"+this.getStatus()+"\n";
		}else{
			return "";
		}
	}
}
