import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * the operation class
 * @author Xiaomin Wu
 *
 */
public class FXGuiMaker {
	static Scanner input = new Scanner(System.in);
	static FXComponentTree tree;
	private static boolean Quit = false;
	
	/**
	 * find correct componentType
	 * @param s
	 * @return
	 * type
	 */
	public static ComponentType type(String s){
		s = s.toLowerCase();
		ComponentType re = null;
		switch(s){
			case"h":
				re =  ComponentType.findType("hbox");
				break;
			case"v":
				re =  ComponentType.findType("vbox");
				break;
			case"t":
				re =  ComponentType.findType("textarea");
				break;
			case"b":
				re =  ComponentType.findType("button");
				break;
			case"L":
				re =  ComponentType.findType("label");
				break;
		}
		return re;
	}
	/**
	 * The main method runs a menu driven application 
	 * which first creates an FXComponentTree based on the 
	 * passed in file and then prompts the 
	 * user for a menu command selecting the operation. 
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println("L- Load from file\n"
				+ "P- Print\n"
				+ "C- Cursor to child (index number)\n"
				+ "A- Add child (index, type, prompt for text)\n"
				+ "U- Cursor up (to parent)\n"
				+ "E- Edit Text\n"
				+ "D- Delete child (index number)\n"
				+ "R- Cursor to root\n"
				+ "S- Save to Text File\n"
				+ "X- Export to FXML file (Extra Credit)\n"
				+ "Q - Quit");
		
		while(!Quit){
			System.out.println("Please select an option:");
			String token = input.nextLine();
			token = token.toLowerCase();
			switch(token){
			
			
			case "l": 
				while(true){
				System.out.println("Please enter filename:");
				try {
					try {
						tree = FXComponentTree.readFromFile(input.nextLine());
						break;
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (leaveGapException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ChildrenFullException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
				break;
				
			case "p": 
				if(tree == null){
					System.out.println("Please load a tree first");
				}else{
					System.out.println(FXComponentTree.printElement(tree));
				}
				break;
				
			case "c":
				if(tree == null){
					System.out.println("Please load a tree first");
				}else{
					System.out.println("Please enter number of child (starting with 1): ");
					int index = input.nextInt();
					try {
						tree.setCursor(tree.getCursor().accessChild(index));
					} catch (EmptyChildException e) {
						// TODO Auto-generated catch block
						System.out.println("The Node has no such child");
					} catch (WrongIndexException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Cursor Moved to " + tree.getCursor().getType());
				}
				
				break;
			case "a":
				
				if(tree == null){
					System.out.println("Please load a tree first");
				}else{
					System.out.println("Select component type (H - HBox, V - VBox, T - TextArea, B - Button, L - Label):");
					ComponentType t = type(input.nextLine());
					System.out.println("Please enter text:");
					String text = input.nextLine();
					System.out.println("Please enter an index:");
					int i = input.nextInt();
					
					tree.addChild(i-1, new FXTreeNode(t,text));
				}
				
				
				break;
			case"u": 
				
				if(tree == null){
					System.out.println("Please load a tree first");
				}else{
					if(!tree.getCursor().equals(tree.getRoot())){
						tree.setCursor(tree.getCursor().getParent());
						}else{
							System.out.println("cursor at root");
						}
				}
				
				
				break;
			case"e":
				
				if(tree == null){
					System.out.println("Please load a tree first");
				}else{
					System.out.println("Please enter new text:");
					String text = input.nextLine();
					tree.getCursor().setText(text);
					System.out.println("Text Edited.");
				}
				
				
				break;
			case"d":
				
				if(tree == null){
					System.out.println("Please load a tree first");
				}else{
					System.out.println("Please enter number of child (starting with 1):");
					int i = input.nextInt();
					
					try {
						System.out.println(tree.accessChild(i).getType()+"removed");
					} catch (EmptyChildException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WrongIndexException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tree.deleteChild(i);
				}
				
				
				break;
			case"r":
				
				if(tree == null){
					System.out.println("Please load a tree first");
				}else{
					tree.setCursor(tree.getRoot());
				}
				
				
				break;
			case"s":
				
				if(tree == null){
					System.out.println("Please load a tree first");
				}else{
					System.out.println("Please enter a filename:");
					String text = input.nextLine();
					try {
						FXComponentTree.writeToFile(tree, text);
						System.out.println("File saved");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				
				break;
			case"x":
				
				if(tree == null){
					System.out.println("Please load a tree first");
				}else{
					System.out.println("Please enter a filename:");
					String text = input.nextLine();
					try {
						FXComponentTree.writeToFxmlFile(tree, text);
						System.out.println("File saved");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				
				break;
			case"q":
				Quit = true;
				break;
			}
		}
	}
}
