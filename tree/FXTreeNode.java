/**
 * FXTreeNode object
 * @author Xiaomin Wu
 *
 */
public class FXTreeNode {
	private String text;
	private ComponentType type;
	private FXTreeNode parent;
	private FXTreeNode[] children;
	final int maxChildren = 10;
	
	/**
	 * constructor with only type
	 * @param type
	 */
	public FXTreeNode(ComponentType type){
		this.type = type;
		children = new FXTreeNode[maxChildren];
	}
	/**
	 * constructor with text and type
	 * @param type
	 * @param text
	 */
	public FXTreeNode(ComponentType type, String text){
		this.text = text;
		this.type = type;
		children = new FXTreeNode[maxChildren];
	}
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ComponentType getType() {
		return type;
	}
	public void setType(ComponentType type) {
		this.type = type;
	}
	public FXTreeNode getParent() {
		return parent;
	}
	public void setParent(FXTreeNode parent) {
		this.parent = parent;
	}
	public FXTreeNode[] getChildren() {
		return children;
	}
	public void setChildren(FXTreeNode[] children) {
		this.children = children;
	}
	public int getMaxChildren() {
		return maxChildren;
	}
	
	/**
	 * delete child 
	 * procondition:
	 * empty child is null
	 * inddex is bigger than 0 smaller than or equal to 10
	 * @param index
	 * the index of position to add
	 * @throws WrongIndexException
	 * indicates the index is wrong
	 */
	public void deleteChild(int index) throws WrongIndexException{
		
		if(index - 1 < 0 || index > 10 ){
			throw new WrongIndexException("wrong Index");
		}
		
		if(index == 10){
			this.children[9] = null;
		}
		
		for(int i = index - 1; i <= 8;i++){
			this.children[i] = this.children[i+1];
		}
	}
	/**
	 * add a children 
	 * precondition:
	 * not full, no leaving gap here
	 * 
	 * @param index
	 * the index
	 * @param node
	 * the node to be added
	 * @throws leaveGapException
	 * indicates the add leave gap
	 * @throws ChildrenFullException
	 * indicates the children list is full
	 */
	public void addChild(int index, FXTreeNode node) throws leaveGapException, ChildrenFullException{
		
		
		
		if(index != 0){
		if(children[index-1] == null ){
			throw new leaveGapException("the adding will leave gap");
		}else if(children[9] != null){
			throw new ChildrenFullException("the children list of"+this.text+" is full");
		}else{
			for(int i = 9; i > index; i--){
				children[i] = children[i - 1];
			}
			
			children[index] = node;
		}
		}else{
			if(children[9] != null){
				throw new ChildrenFullException("the children list of"+this.text+" is full");
			}else{
				for(int i = 9; i > index; i--){
					children[i] = children[i - 1];
				}
				
				children[index] = node;
			}
		}
	}
	/**
	 * check if two nodes have same children
	 * @param c
	 * @return
	 */
	public boolean equalChild(FXTreeNode c){
		boolean re = true;
		for(int i = 0 ; i < c.getChildrenAmount(); i ++){
			 if(this.children[i] == null && c.getChildren()[i] != null){
				re = false;
			 }else if(this.children[i] != null && c.getChildren()[i] == null){
				 re = false;
			 }else if(!c.getChildren()[i].equals(this.children[i])){
				 re = false;
			 }
		}
		return re;
	}
	/**
	 * access a child and return it
	 * @param index
	 * the child index
	 * @throws EmptyChildException
	 * indicate there is no child at this index
	 * @throws WrongIndexException
	 * indicate the index is wrong
	 */
	public FXTreeNode accessChild(int index) throws EmptyChildException, WrongIndexException{
		if(index - 1 < 0 || index > 10 ){
			throw new WrongIndexException("wrong Index");
		}else if(this.children[index-1] == null){
			throw new EmptyChildException("no child in this index");
		}else{
			return children[index-1];
		}
		
	}
	/**
	 * get children amount
	 * @return
	 */
	public int getChildrenAmount(){
		int count = 0;
		for(FXTreeNode a : children){
			if(a != null){
				count++;
			}
		}
		return count;
	}
	
	/**
	 * check if any child here
	 * @param index
	 * @return
	 */
	public boolean hasChild(int index){
		return this.children[index] != null;
	}
	/**
	 * put info of this node to string
	 */
	public String toString(){
		String re = "";
		if(this.text != null){
		re = this.type +" "+ this.text + "\n";
		}else{
			re = this.type +"\n";
		}
		return re;
	}
	
	
	/**
	 * put info of this node to string
	 */
	public String toStringFXML(){
		String re = "";
		
		if(this.type == ComponentType.AnchorPane){
			re = "<" + this.type + " maxHeight=\"-Infinity\" maxWidth=\"-Infinity\""
					+ " minHeight=\"-Infinity\" minWidth=\"-Infinity\""
					+ " prefHeight=\"415.0\" prefWidth=\"770.0\"> <children>";
		}else if(this.type == ComponentType.Button){
			re = "<" +  this.type + " mnemonicParsing=\"false\" text=\""+this.text+"\" />";
		}else if(this.type == ComponentType.HBox){
			re = "<" + this.type + "> <children>";
		}else if(this.type == ComponentType.Label){
			re = "<"+this.type+" text=\"" + this.text+"\" />";
		}else if(this.type == ComponentType.TextArea){
			re = "<"+this.type+" promptText=\""+this.text+"\" />";
		}else if(this.type==ComponentType.VBox){
			re = "<"+this.type+"> <children>";
		}
		
		
		
		return re;
	}
	
	
	/**
	 * put info of this node to string
	 */
	public String toStringFXMLend(){
		String re = "";
		
		if(this.type == ComponentType.AnchorPane){
			re = "</children> </AnchorPane>";
		}else if(this.type == ComponentType.Button){
			re = "";
		}else if(this.type == ComponentType.HBox){
			re = "</children> </HBox>";
		}else if(this.type == ComponentType.Label){
			re = " ";
		}else if(this.type == ComponentType.TextArea){
			re = " ";
		}else if(this.type==ComponentType.VBox){
			re = "</children> </VBox>";
		}
		
		
		
		return re;
	}
	
	
	/**
	 * check if an input object o equals this object
	 * @param o
	 * the input object
	 */
	public boolean equals(Object o){
		if(o instanceof FXTreeNode){
			if(this.text == null){
				if(((FXTreeNode)o).getText() != null){
					return false;
				}else if(this.type != ((FXTreeNode)o).getType()){
					return false;
				}else if(!this.equalChild((FXTreeNode)o)){
					return false;
				}else{
					return true;
				}
			}else if(this.getText().equals(((FXTreeNode)o).getText()) && this.getType() == ((FXTreeNode)o).getType() && this.equalChild((FXTreeNode)o)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
}
