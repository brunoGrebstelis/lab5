package lab5;


import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


/**
 * This class operates with variables form xml file
 * 
 * @author bruno
 *
 */
public class DataOperations {

	private String envvariable;
	private File xmlFile;
	private LinkedHashMap<Integer, Person> showPersons2 = new LinkedHashMap<Integer, Person>();


	/**
	 * Check if entered file is valid
	 * 
	 * @param envvariable environmental variable
	 */
	DataOperations(String envvariable) {

		if (envvariable == null) { 
			System.out.println("Enter valid enviroment variable  - 'GoToLab5XmlFile'");
			System.exit(1);
		}
		
		this.xmlFile = new File(envvariable);
		this.envvariable = envvariable; 
		
		if(!xmlFile.exists()) {
			System.out.println("The file you entered doesnt exist!");
			System.exit(1);
		}
		
		this.fill();

	}

	
	
	/**
	 * This method reads xml file and fills out variables from Person class. Also
	 * this method put all information to a linked hash map variable "showPersons"
	 */
	public void fill() {
	try {
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();
	Document doc = builder.parse(xmlFile);
	doc.getDocumentElement().normalize();
	
	NodeList nodeList = doc.getElementsByTagName("Person");
	for (int itr = 0; itr < nodeList.getLength(); itr++) {
		Element node = (Element) nodeList.item(itr);
		Element coords = (Element) node.getElementsByTagName("coordinates").item(0);
		Element loc = (Element) node.getElementsByTagName("location").item(0);
		
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Person pers = new Person(
				Long.parseLong(node.getElementsByTagName("id").item(0).getTextContent()),
				node.getElementsByTagName("name").item(0).getTextContent(),
				new Coordinates(
					Double.parseDouble(coords.getElementsByTagName("x").item(0).getTextContent()),
					Float.parseFloat(coords.getElementsByTagName("y").item(0).getTextContent())),
				Double.parseDouble(node.getElementsByTagName("height").item(0).getTextContent()),
				node.getElementsByTagName("passportID").item(0).getTextContent(),
				Color.valueOf(node.getElementsByTagName("eaycolor").item(0).getTextContent()),
				new Location(
						Integer.parseInt(loc.getElementsByTagName("x").item(0).getTextContent()),
						Long.parseLong(loc.getElementsByTagName("y").item(0).getTextContent()),
						Long.parseLong(loc.getElementsByTagName("z").item(0).getTextContent())));
				
			pers.setCreationDate(ZonedDateTime.parse(node.getElementsByTagName("creationDate").item(0).getTextContent()));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			pers.FsetDateTimeBirthString(LocalDate.parse(node.getElementsByTagName("birthsday").item(0).getTextContent(), formatter));

			showPersons2.put(itr, pers);
			}
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
}

	
	
	/**
	 * This method is used to insert new element to "showPersons2"
	 * 
	 * @param name
	 * @param coordinates
	 * @param height
	 * @param id
	 * @param passportID
	 * @param eyeColor
	 * @param location
	 * @param birthsday
	 */
	public void insert(String name, Coordinates coordinates, Double height, long id, String passportID, Color eyeColor,
	Location location, String birthsday) {

	Person newPers = new Person(id, name, coordinates, height, passportID, eyeColor, location);

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	newPers.FsetDateTimeBirthString(LocalDate.parse(birthsday, formatter));

	if (showPersons2.size() > 0) {
		int res = 0;
		int lastKey = -1;
		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			Person a = m.getValue();
			if (res < m.getKey()) {
				res = m.getKey();
			}
		}
		res = res + 2;
		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			Person a = m.getValue();
			if (lastKey + 1 != m.getKey()) {
				res = m.getKey();
			}
			lastKey = m.getKey();
		}

		System.out.println(newPers.getName() + " was added to the list");
		showPersons2.put(res - 1, newPers);
		Sort srt = new Sort(showPersons2);
		srt.sort();
	} else {
		showPersons2.put(0, newPers);
	}
}

	
	
	/**
    * This method updates information which is on entered id
	 * 
	 * @param id id
	 */
	public void update(long id) {
		long id2 = id;
		int rem = 0;
		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			Person a = m.getValue();
			if (a.getID() == id) {
				rem = m.getKey();
				System.out.println(a.getName() + " was removed");
			}
		}
		Object ob2 = showPersons2.remove(rem);
		EnterNewPerson up = new EnterNewPerson(id2); //up - updated person
		up.getPersonInfo(); 
		insert(up.getName(), up.getCord(), up.getHeight(), up.getID(), up.getPassportID(), up.getEyeColor(), up.getLocation(), up.getBirthsday());
	}

	
	
	public LinkedHashMap<Integer, Person> getShowPersons2(){
		return showPersons2;
	}
	
	public String getEnvvariable() {
		return envvariable;
	}
}