package lab5;

/**
 * Main class
 * @author bruno
 *
 */
public class MainLab5 {
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args)  {
				
		//dataOperations doc = new dataOperations("person_data.xml");
		DataOperations doc = new DataOperations(System.getenv("GoToLab5XmlFile"));
		Userimputs user = new Userimputs(doc, System.in);
		user.entering(); 
		
	}

}
