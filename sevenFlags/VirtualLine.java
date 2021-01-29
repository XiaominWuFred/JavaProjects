import java.util.LinkedList;
/**
 * the VirtualLine
 * @author Xiaomin Wu
 *
 */
@SuppressWarnings({ "rawtypes", "serial" })
public class VirtualLine extends LinkedList {

	/**
	 * enqueue to the last of the Linkedlist 
	 * @param p
	 * person to add
	 * @throws FullLineException 
	 * used for holding line only
	 */
	@SuppressWarnings("unchecked")
	public void enqueue(Person p) throws FullLineException{
		super.addLast(p);
	}
	/**
	 * dequeue a person; a person get served
	 * precondition:
	 * the line is not empty
	 * @return 
	 * Person, the person dequeued 
	 * @throws EmptyLineException
	 * indicates the line is empty
	 */
	public Person dequeue() throws EmptyLineException{
		if(super.isEmpty()){
			throw new EmptyLineException("Empty VirtualLine");
		}else{
			return (Person) super.removeFirst();
		}
		
	}
	/**
	 * peek the line
	 * @return Person
	 * the Person at the first position of the line 
	 */
	public Person peek(){
		return (Person)super.peek();
	}
	
}
