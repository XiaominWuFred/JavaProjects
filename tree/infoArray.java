/**
 * a self designed unlimited String array structure
 * @author Xiaomin Wu
 *
 */
public class infoArray {
	private String[] info;
	private String[] infoCpy;
	private int max;
	private int amount;
	public infoArray(){
		this.info = new String[100];
		amount = 0;
		max = 100;
	}
	/**
	 * unlimited add element
	 * @param s
	 * input s
	 */
	public void addElement(String s){
		
		if(amount == max){
			infoCpy = new String[max];
			for(int i = 0; i < max; i++){
				infoCpy[i] = info[i];
			}
			
			info = new String[2*max];
			max = max*2;
			
			for(int i = 0; i < infoCpy.length;i++){
				info[i] = infoCpy[i];
			}
			
		}
		
		info[amount] = s;
		amount++;
	}
	public String[] getInfo() {
		return info;
	}
	public void setInfo(String[] info) {
		this.info = info;
	}
	public String[] getInfoCpy() {
		return infoCpy;
	}
	public void setInfoCpy(String[] infoCpy) {
		this.infoCpy = infoCpy;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * return the last element String
	 * @return
	 * @throws emptyListException
	 */
	public String getElement() throws emptyListException{
		
		if(amount == 0){
			throw new emptyListException("info list empty");
		}
		
		String re = info[amount - 1];
		info[amount - 1] = null;
		amount--;
		
		return re;
	}
	/**
	 * get index position element
	 * move all other element forward
	 * @param index
	 * @return
	 * @throws NoSuchIndexException
	 */
	public String getElement(int index) throws NoSuchIndexException{
		String re = "";
		if(index < 1 || index >amount){
			throw new NoSuchIndexException("index out of range");
		}
		
		re = info[index -1];
		for(int i = index - 1; i < amount-1 ; i++){
			info[i] = info[i+1];
		}
		info[amount - 1] = null;
		amount--;
		
		return re;
	}
	
	
}
