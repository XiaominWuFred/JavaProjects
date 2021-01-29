/**
 *  status of a person
 *  ¡°Available¡±, ¡°Holding¡± and ¡°OnRide¡±
 * @author Xiaomin Wu
 *
 */
public enum Status {
	Available(0),
	Holding(1),
	OnRide(2);
	
	private final int value;
	
	private Status(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}

}
