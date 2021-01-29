import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * FXComponentTree Object
 * @author Xiaomin Wu
 *
 */
public class FXComponentTree {
	private FXTreeNode root;
	private FXTreeNode cursor;
	
	/**
	 * Sets the cursor to the root of the FXComponentTree
	 */
	public void cursorToRoot(){
		cursor = root;
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
	public void deleteChild(int index){
		try {
			cursor.deleteChild(index);
		} catch (WrongIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * check child
	 * @param index
	 * @throws EmptyChildException
	 * @throws WrongIndexException
	 */
	public FXTreeNode accessChild(int index) throws EmptyChildException, WrongIndexException{
		return cursor.accessChild(index);
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
	public void addChild(int index, FXTreeNode node){
		try {
			cursor.addChild(index, node);
		} catch (leaveGapException e) {
			System.out.println("leaving gap here, change an index please");
		} catch (ChildrenFullException e) {
			System.out.println("Full list, can not add in more");
		}
	}

	/**
	 * Sets the current node᯳ text to the specified text.
	 * @param text
	 */
	public void setTextAtCursor(String text){
		cursor.setText(text);
	}
	
	/**
	 * Moves the cursor to the child node of the of the 
	 * cursor corresponding to the specified index
	 * @param index
	 */
	public void cursorToChild(int index){
		
		try {
			cursor = cursor.accessChild(index);
		} catch (EmptyChildException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Moves the cursor to the parent of the current node.
	 * @throws NoParentException 
	 */
	public void cursorToParent() throws NoParentException{
		if(cursor.getParent() == null){
			throw new NoParentException("this is a root");
		}
		cursor = cursor.getParent();
	}
	
	/**
	 * Generates the FXComponentTree based on the file name that is passed in.
	 * @param filename
	 * @return
	 * @throws ChildrenFullException 
	 * @throws leaveGapException 
	 * @throws NumberFormatException 
	 * @throws FileNotFoundException 
	 */
	public static FXComponentTree readFromFile(String filename) throws NumberFormatException, leaveGapException, ChildrenFullException, FileNotFoundException{
		FXComponentTree tree = new FXComponentTree();
		//create info array
		infoArray info = new infoArray();
        //fetch infomation from file
		File f = new File(filename);
        Scanner fileIn;

			fileIn = new Scanner(f);
			 int i = 0;
	         while (fileIn.hasNextLine()) {
	             info.addElement( fileIn.nextLine() );
	             i = i+1;
	         }
	         fileIn.close();


		String[] holder = new String[3];	//the string holder of input command
		
		try {
			String line = info.getElement(1);
			int ii = line.indexOf(" ");
			holder[0] = line.substring(0, ii);
			line = line.substring(ii+1, line.length());
			ii = line.indexOf(" ");
			if(ii == -1){
				holder[1] = line;
				holder[2] = " ";					//container
			}else{
				holder[1] = line.substring(0, ii);
				line = line.substring(ii+1);
				holder[2] = line;					//control
			}
			
		} catch (NoSuchIndexException e) {
			// TODO Auto-generated catch block
			System.out.println("Index wrong");
		}
		
		enrollStart(holder[0], ComponentType.findType(holder[1]), holder[2], tree);//put root
		
		tree.setCursor(tree.getRoot());
		holder[0] = "";
		// finish all line in tree
		while(info.getAmount() != 0){
			try {
				String line = info.getElement(1);
				if(!line.equals("")){
				int ii = line.indexOf(" ");
				holder[0] = line.substring(0, ii);
				line = line.substring(ii+1, line.length());
				ii = line.indexOf(" ");
				if(ii == -1){
					holder[1] = line;
					holder[2] = " ";					//container
				}else{
					holder[1] = line.substring(0, ii);
					line = line.substring(ii+1);
					holder[2] = line;					//control
				}
				}
			} catch (NoSuchIndexException e) {
				// TODO Auto-generated catch block
				System.out.println("Index wrong");
			}
			
			if(!holder[0].equals("")){
			String newCommand = holder[0].substring(2);
			enrollElement(newCommand, ComponentType.findType(holder[1]), holder[2], tree);	//recursion put others
			tree.setCursor(tree.getRoot());
			}
		}
		
		return tree;   
	}
	
	/**
	 * set the root
	 * @param command
	 * @param type
	 * @param text
	 * @param tree
	 */
	public static void enrollStart(String command, ComponentType type, String text, FXComponentTree tree){
		if(command.length() == 1){
			if(text.equals(" ")){
				tree.setRoot(new FXTreeNode(type));	
			}
		}
	}
	/**
	 * recursion method to fill in one line of element
	 * precondition: cursor != null
	 * @param command
	 * @param type
	 * @param text
	 * @param cursor
	 * @throws NumberFormatException
	 * @throws leaveGapException
	 * @throws ChildrenFullException
	 */
	public static void enrollElement(String command, ComponentType type, String text, FXComponentTree tree) throws NumberFormatException, leaveGapException, ChildrenFullException{
		if(command.length() == 1){
			if(text.equals(" ")){
				FXTreeNode N = new FXTreeNode(type);
				N.setParent(tree.getCursor());
				tree.addChild(Integer.parseInt(command), N);
			}else{
				FXTreeNode N = new FXTreeNode(type, text);
				N.setParent(tree.getCursor());
				tree.addChild(Integer.parseInt(command), N);
			}
			
		}else{
			
			tree.setCursor(tree.getCursor().getChildren()[Integer.parseInt(command.substring(0, 1))]);
			
			String newCommand = command.substring(2);
			enrollElement(newCommand, type, text, tree);
		}
	}
	
	
	
	public FXTreeNode getRoot() {
		return root;
	}

	public void setRoot(FXTreeNode root) {
		this.root = root;
	}

	public FXTreeNode getCursor() {
		return cursor;
	} 

	public void setCursor(FXTreeNode cursor) {
		this.cursor = cursor;
	}

	/**
	 * Generates a text file that reflects the structure of the FXComponentTree.
	 *  format of the tree of the file should match the format of the input file
	 * @param tree
	 * @throws FileNotFoundException 
	 */
	public static void writeToFile(FXComponentTree tree, String fileName) throws FileNotFoundException{
		 File F = new File(fileName);
         PrintWriter pw = new PrintWriter(F);
         pw.println(outputElement(tree));
         pw.close();
	}
	
	
	/**
	 * Generates a fxml file that reflects the structure of the FXComponentTree.
	 *  format of the tree of the file should match the format of the input file
	 * @param tree
	 * @throws FileNotFoundException 
	 */
	public static void writeToFxmlFile(FXComponentTree tree, String fileName) throws FileNotFoundException{
		 File F = new File(fileName);
         PrintWriter pw = new PrintWriter(F);
         pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <?import javafx.scene.control.*?> <?import java.lang.*?> <?import javafx.scene.layout.*?>");
         pw.println(outputElementFXML(tree));
         pw.close();
	}
	
	/**
	 * out all elements with format
	 * @param cursor
	 * @return
	 */
	public static String outputElement(FXComponentTree tree){
		String prefix = "0";
		FXTreeNode cursor = tree.getRoot();
		return outputElement( prefix, cursor);
	}
	/**
	 * helper function 
	 * @param prefix
	 * @param cursor
	 * @return
	 */
	public static String outputElement(String prefix,FXTreeNode cursor){
		String re = "";
		
		if(cursor.hasChild(0)){
			re = re + prefix +" " + cursor.toString(); //print itself
			for(int i = 0; i < cursor.getChildrenAmount(); i ++){	//then print its children
				re =  re + outputElement(prefix+"-"+String.valueOf(i), cursor.getChildren()[i]);
			}
			
			return re;
		}else{
			return re + prefix +" " + cursor.toString(); //print itself;
		}
	}
	
	
	
	/**
	 * out all elements with format
	 * @param cursor
	 * @return
	 */
	public static String outputElementFXML(FXComponentTree tree){

		FXTreeNode cursor = tree.getRoot();
		return outputElementFXML(cursor);
	}
	/**
	 * helper function 
	 * @param prefix
	 * @param cursor
	 * @return
	 */
	public static String outputElementFXML(FXTreeNode cursor){
		String re = "";
		
		if(cursor.hasChild(0)){
			re = re  +" " + cursor.toStringFXML(); //print itself
			for(int i = 0; i < cursor.getChildrenAmount(); i ++){	//then print its children
				re =  re + outputElementFXML( cursor.getChildren()[i]);
			}
			re = re + cursor.toStringFXMLend();
			
			return re;
		}else{
			return re  +" " + cursor.toStringFXML(); //print itself;
		}
	}
	
	
	
	
	/**
	 * out all elements with format
	 * @param cursor
	 * @return
	 */
	public static String printElement(FXComponentTree tree){
	
		FXTreeNode cursor = tree.getRoot();
		
		return printElement(printPerfix(0), cursor, 0, tree.getCursor());
	}
	/**
	 * helper function 
	 * @param prefix 
	 * @param cursor
	 * @return
	 */
	public static String printElement(String prefix,FXTreeNode cursor, int iteration, FXTreeNode position){
		String re = "";
		
		if(cursor.hasChild(0)){
			if(position.equals(cursor)){
				if(prefix.equals("")){
					prefix = "==>";
				}else if(prefix.equals("--+")){
					prefix = "==>";
				}else{
					int tag = prefix.lastIndexOf(" ");
					prefix = prefix.subSequence(0, tag+1) + "==>";
				}	
			}
			
			re = re + prefix  + cursor.toString(); //print itself
			
			for(int i = 0; i < cursor.getChildrenAmount(); i ++){	//then print its children
				re =  re + printElement(printPerfix(iteration+1), cursor.getChildren()[i], iteration + 1, position);
			}
			
			return re;
		}else{
			if(position.equals(cursor)){
				if(prefix.equals("")){
					prefix = "==>";
				}else if(prefix.equals("--+")){
					prefix = "==>";
				}else{
					int tag = prefix.lastIndexOf(" ");
					prefix = prefix.subSequence(0, tag+1) + "==>";
				}	
			}
			return re + prefix +" " + cursor.toString(); //print itself;
		}
	}
	/**
	 * generate perfix for recursive function
	 * @param iteration
	 * time iteration
	 * @return
	 * string
	 */
	public static String printPerfix(int iteration){
		if(iteration == 0){
			return "";
		}else if(iteration == 1){
			return "--+";
		}else{
			String re = "+--";
			for(int i = 0; i < (iteration-1)*3; i++){
				re = " "+re;
			}
			return re;
		}
		
	}
	
	/** 
	 * print a cursor's all child
	 * precondition:
	 * consAmount = Amount
	 * @param re
	 * @param children
	 * @param amount
	 * @param consAmount
	 * @return
	 */
	public static String outputChild(String re, FXTreeNode[] children, int amount, int consAmount){
		
		if(amount != 0){
			int sub = consAmount - amount;
			return re + "-" + sub +" " + children[sub].toString() + "\n" + 
					outputChild(re, children, amount-1, consAmount);
		}else{
			return "\n";
		}
		
	}


}
