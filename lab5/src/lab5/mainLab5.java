package lab5;



/**
 * Main class
 * @author bruno
 *
 */

public class mainLab5 {
	
	/**
	 * Main method
	 * @param args
	 */

	public static void main(String[] args)  {
				
		//dataOperations doc = new dataOperations("person_data.xml");
		dataOperations doc = new dataOperations(System.getenv("go_to_lab5_xml_file"));
		userimputs user = new userimputs(doc, System.in);
		user.entering(); 
		
	}

}
