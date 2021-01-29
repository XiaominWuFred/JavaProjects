/**
 * enum of Component type
 * @author Xiaomin Wu
 *
 */
public enum ComponentType {
	Button(0),
	Label(1),
	TextArea(2),
	HBox(3),
	VBox(4),
	AnchorPane(5);
	
	
	private final int value;
	/**
	 * constructor
	 * @param value
	 */
	private ComponentType(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	/**
	 *find out a type based on a string 
	 * @param a
	 * @return
	 */
	public static ComponentType findType(String a){
		a = a.toLowerCase();
		ComponentType re = null;
		switch(a){
		case "button": re =  Button;
			break;
		case "label": re = Label;
		break;
		case "textarea": re = TextArea;
		break;
		case "hbox": re = HBox;
		break;
		case "vbox": re = VBox;
		break;
		case "anchorpane": re = AnchorPane;
		}
		return re;
	}

}
