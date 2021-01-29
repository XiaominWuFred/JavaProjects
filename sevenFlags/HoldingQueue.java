/**
 * HoldingQueue extending VirtualLine
 * a limited line for Person who are up next for a ride
 * @author Xiaomin Wu
 *
 */
@SuppressWarnings("serial")
public class HoldingQueue extends VirtualLine{
	
	private int maxSize;
	
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
	/**
	 * enqueue to the last of the Linkedlist 
	 * @param p
	 * person to add
	 * precondition:
	 * the holding line is not full
	 * @throws FullLineException 
	 * indicate the line is full
	 */
	public void enqueue(Person p) throws FullLineException{
		if(super.size() == this.maxSize){
			throw new FullLineException("FullLine enqueue");
		}else{
			super.enqueue(p);
		}
	}
	
	
	
}
