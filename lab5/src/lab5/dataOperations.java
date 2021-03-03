package lab5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.parsers.DocumentBuilder;  
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import org.w3c.dom.Node;  
import org.w3c.dom.Element;  
import java.io.File;  

/**
 * This class operates with variables form xml file
 * @author bruno
 *
 */
public class dataOperations {
	
	String envvariable;
	private File xmlFile;
	int scriptLenhgt;
	
	LinkedHashMap<Integer,String> showPersons=new LinkedHashMap<Integer,String>();
	LinkedHashMap<Integer,Person> showPersons2=new LinkedHashMap<Integer,Person>();
	LinkedHashMap<Integer,String> helpElements=new LinkedHashMap<Integer,String>();
	
	/**
	 * Check if entered file is valid
	 * @param envvariable environmental variable
	 */
	
	dataOperations(String envvariable) {
		
		 try {
	            if (envvariable == null) throw new FileNotFoundException();
	        } catch (FileNotFoundException ex) {
	            System.out.println("Enter valid enviroment variable  - 'go_to_lab5_xml_file'");
	            System.exit(1);
	        }

	        try {
	            this.xmlFile = new File(envvariable);
	            if (!xmlFile.exists()) throw new FileNotFoundException();
	        } catch (FileNotFoundException ex) {
	            System.out.println("The file you entered doesnt exist!");
	            System.exit(1);
	        }

	
	        this.envvariable = envvariable;
	        this.fill();
	        
	}
	
	/**
	 * This method just prints out xml file
	 */
	
	public void printXMLvar() {
		
		try {   			 	
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		DocumentBuilder db = dbf.newDocumentBuilder();  
		Document doc = db.parse(xmlFile);  
		doc.getDocumentElement().normalize();
			
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  
		NodeList nodeList = doc.getElementsByTagName("Person");  
			 
		for (int itr = 0; itr < nodeList.getLength(); itr++){  
			Node node = nodeList.item(itr);  
			System.out.println("\nNode Name :" + node.getNodeName());  
			if (node.getNodeType() == Node.ELEMENT_NODE){  
				Element eElement = (Element) node;  
				System.out.println("Name: "+ eElement.getElementsByTagName("name").item(0).getTextContent());
				System.out.println("id: "+ eElement.getElementsByTagName("id").item(0).getTextContent());  
				System.out.println("eay color: "+ eElement.getElementsByTagName("eaycolor").item(0).getTextContent());
			}  
		}  
		
		}   catch (Exception e) {
			   
			e.printStackTrace();  
		}  
			 	 
	}
	
	/**
	 * This method reads xml file and fills out variables from Person class. Also this method put all information to a linked hash map variable "showPersons"
	 */
		
	public void fill() {
		try {   			 	
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
				
			  
			NodeList nodeList = doc.getElementsByTagName("Person");  

			for (int itr = 0; itr < nodeList.getLength(); itr++){  
				 Element node = (Element) nodeList.item(itr);
	             Element coords = (Element) node.getElementsByTagName("coordinates").item(0);
	             Element loc = (Element) node.getElementsByTagName("location").item(0); 
				if (node.getNodeType() == Node.ELEMENT_NODE){  
					
					Person pers = new Person(
							Long.parseLong(node.getElementsByTagName("id").item(0).getTextContent()),
							node.getElementsByTagName("name").item(0).getTextContent(),
							 new Coordinates(
								Double.parseDouble(coords.getElementsByTagName("x").item(0).getTextContent()),
								Float.parseFloat(coords.getElementsByTagName("y").item(0).getTextContent())	 
							 ),
							Double.parseDouble(node.getElementsByTagName("height").item(0).getTextContent()),
							node.getElementsByTagName("passportID").item(0).getTextContent(),
							Color.valueOf(node.getElementsByTagName("eaycolor").item(0).getTextContent()),
							 new Location(
								Integer.parseInt(loc.getElementsByTagName("x").item(0).getTextContent()),
								Long.parseLong(loc.getElementsByTagName("y").item(0).getTextContent()),
								Long.parseLong(loc.getElementsByTagName("z").item(0).getTextContent())
							 )
					);
					pers.setCreationDate(ZonedDateTime.parse(node.getElementsByTagName("creationDate").item(0).getTextContent()));
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					pers.FsetDateTimeBirthString(LocalDate.parse(node.getElementsByTagName("birthsday").item(0).getTextContent(),formatter));
					
					showPersons.put(itr, pers.getShow());
					showPersons2.put(itr,pers);
					
					//System.out.println(pers.getName());
					//System.out.println(pers.getCreationDate());
					//System.out.println(pers.FgetBirthday());
					//System.out.println(pers.getShow());
							 
				}  
			}  
			
			}   catch (Exception e) {
				   
				e.printStackTrace();  
			}
		
		
		
		
	}
	
	/**
	 * This method prints out all information which is on "showPersons2"
	 */
	
	public void show() {
		for(Entry<Integer, Person> m:showPersons2.entrySet()){
			Person a = m.getValue();
			System.out.println(m.getKey()+a.getShow());	
			
		}
	}
	
	/**
	 * This method prints out all available commands
	 */
	
	public void help() {
	helpElements.put(1,"help : display help for available commands");
	helpElements.put(2,"info : print information about the collection (type, date of initialization, number of elements, etc.) to standard output");
	helpElements.put(3,"show : print to standard output all elements of the collection in string representation");
	helpElements.put(4,"insert_null {element} : add a new item with the given key");
	helpElements.put(5,"update_id {element} : update the value of the collection item whose id is equal to the given");
	helpElements.put(6,"remove_key null : remove an item from the collection by its key");
	helpElements.put(7,"clear : clear collection");
	helpElements.put(8,"save : save collection to file");
	helpElements.put(9,"execute_script file_name : read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively.");
	helpElements.put(10,"exit : end the program (without saving to file)");
	helpElements.put(11,"remove_greater {element} : remove all items from the collection that are greater than the specified one");
	helpElements.put(12,"history : print the last 6 commands (without their arguments)");
	helpElements.put(13,"replace_if_greater null {element} : replace value by key if new value is greater than old");
	helpElements.put(14,"remove_all_by_birthday birthday : remove from the collection all elements whose birthday field value is equivalent to the given one");
	helpElements.put(15,"print_ascending : display the elements of the collection in ascending order");
	helpElements.put(16,"print_field_descending_birthday : print the birthday field values ​​of all elements in descending order");
	
	for(Entry<Integer, String> m:helpElements.entrySet()){  
		System.out.println(m.getValue());	
	}
	
	}
	
	/**
	 * This method prints out information about "showPersons2"
	 */
	
	public void info() {
		
		ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("UTC"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss");
		String formattedString = zonedDateTimeNow.format(formatter);
		
		System.out.println("Type: "+showPersons.getClass());
		System.out.println("Data: "+formattedString);
		System.out.println("Number of elements: "+showPersons2.size());
		
	}
	
	/**
	 * This method is used to generate unique id
	 * @return id
	 */

	public long setID() {
		long maxID = 0;
		if(showPersons2.size()==0) {
			maxID = 0;
		}else {
			for(Entry<Integer, Person> m:showPersons2.entrySet()){
				Person a = m.getValue();
				if(a.getID()>maxID) {
					maxID=(a.getID());
				}	
			}
		}
		return maxID+1;
	}
	
	/**
	 * This method is used to insert new element to "showPersons2"
	 * @param name
	 * @param coordinates
	 * @param height
	 * @param id
	 * @param passportID
	 * @param eyeColor
	 * @param location
	 * @param birthsday
	 */
	
	public void insert( String name, Coordinates coordinates, 
			 Double height, long id,
			 String passportID, Color eyeColor, Location location, String birthsday) {
		
		
		
		
		Person newPers = new Person(id,name,coordinates,height,passportID,
				eyeColor, location);
		
		
			

		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		newPers.FsetDateTimeBirthString(LocalDate.parse(birthsday,formatter));
		
		
		
		//System.out.println(newPers.getShow());
		//showPersons.put(showPersons.size(), newPers.getShow());
		//showPersons2.put(showPersons.size(), newPers);
	if(showPersons2.size()>0) {
		int res=0;
		int lastKey = -1;
		for(Entry<Integer, Person> m:showPersons2.entrySet()){
			Person a = m.getValue();
			if(res<m.getKey()) {
				res=m.getKey();
			}
		}
		res=res+2;
		for(Entry<Integer, Person> m:showPersons2.entrySet()){
			Person a = m.getValue();
			//System.out.println("key: "+m.getKey());
			//System.out.println("lastkey: "+lastKey);
			if(lastKey+1!=m.getKey()) {
				res=m.getKey();
			}
			lastKey=m.getKey();
		}
		
		
		System.out.println(newPers.getName()+" was added to the list");
		showPersons2.put(res-1, newPers);
		sort();
	}else {
		showPersons2.put(0, newPers);
	}
		
		
	}
	
	/**
	 * This method sorts "showPersons2" variables in ascending order
	 */
	
	public void sort() {
	LinkedHashMap<Integer,Person> showPersons3=new LinkedHashMap<Integer,Person>();
	
	int lastKey =0;
	
	do {
	for(Entry<Integer, Person> m:showPersons2.entrySet()){
		Person a = m.getValue();
		if(m.getKey()==lastKey) {
			showPersons3.put(lastKey, m.getValue());
			lastKey++;
		}
	}
	
	if(showPersons3.size()==showPersons2.size()) {
		break;
	}
	}while(true);	
	
	showPersons2.clear();
	int x = 0;
	for(Entry<Integer, Person> m:showPersons3.entrySet()){
		Person a = m.getValue();
		showPersons2.put(x, m.getValue());
		//System.out.println(a.getName());
		x++;
	}
	
	}
	
	/**
	 * This method updates information which is on entered id
	 * @param id id
	 */
	
	public void update(long id) {
		long id2 = id;
		int rem = 0;
		for(Entry<Integer, Person> m:showPersons2.entrySet()){
			Person a = m.getValue();
			if(a.getID()==id) {
				rem=m.getKey();
				System.out.println(a.getName()+" was removed");
			}
		}
		Object ob2 = showPersons2.remove(rem);
		getPersonInfo(id2);
	}  
	
	/**
	 * This method delete element by it's id
	 * @param id 
	 */
	
	public void removeFun(long id) {
		
		int rem = 0;
		for(Entry<Integer, Person> m:showPersons2.entrySet()){
			Person a = m.getValue();
			if(a.getID()==id) {
				rem=m.getKey();
				System.out.println(a.getName()+" was removed");
			}
		}
		Object ob2 = showPersons2.remove(rem);
	}
	
	/**
	 * This method delete element by it's key
	 * @param key
	 */
	
	public void remove_key(int key) {
		for(Entry<Integer, Person> m:showPersons2.entrySet()){
			Person a = m.getValue();
			if(m.getKey()==key) {
				System.out.println(a.getName()+" was removed");
			}
		}
		Object ob2 = showPersons2.remove(key);
	}
	
	/**
	 * This method delete all elements on "showPersons2"
	 */
	
	public void clear() {
		
			showPersons2.clear();
			System.out.println("You have deleted all elements!");
		
	}
	
	/**
	 * this method saves current "showPersons2" infotrmation to an xml file
	 * @throws FileNotFoundException
	 */
	
	public void save() throws FileNotFoundException {
		
		PrintWriter writer = new PrintWriter(envvariable);
		writer.print("");
		// other operations
		writer.close();
		
		PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(envvariable, true)));
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            out.println("<information>");
            for(Entry<Integer, Person> m:showPersons2.entrySet()){
    			Person a = m.getValue();

            out.println("	<Person>");
            out.println("		<id>"+a.getID()+"</id>");
            out.println("		<name>"+a.getName()+"</name>");
            out.println("		<coordinates>");
            out.println("			<x>"+a.getXcoordinate()+"</x>");
            out.println("			<y>"+a.getYcoordinate()+"</y>");
            out.println("		</coordinates>");
            out.println("		<creationDate>"+a.getCreationDate()+"</creationDate>");
            out.println("		<height>"+a.getHeight()+"</height>");
            out.println("		<birthsday>"+a.FgetBirthday()+"</birthsday>");
            out.println("		<passportID>"+a.getPassportID()+"</passportID>");
            out.println("		<eaycolor>"+a.getEyeColor()+"</eaycolor>");
            out.println("		<location>");
            out.println("			<x>"+a.getXlocation()+"</x>");
            out.println("			<y>"+a.getYlocation()+"</y>");
            out.println("			<z>"+a.getZlocation()+"</z>");
            out.println("		</location>");
            out.println("	</Person>");
            }
            out.println("</information>");
            
        }catch (IOException e) {
            System.err.println(e);
        }finally{
            if(out != null){
                out.close();
            }
        } 
	}
	
	/**
	 * This method is used as a method which helps to use "execute_script" function
	 * @return amount of commands on script file
	 */
	
	public int execute_script_lenght() {
		return scriptLenhgt;
	}
	
	/**
	 * This method is used to "execute_script"
	 * @param i command number
	 * @return String of specific command
	 * @throws IOException
	 */
	
	public String execute_script(int i) throws IOException{
		
		    
		 	String[] arr= null;
		    List<String> scriptElements = new ArrayList<String>();

		    	//FileInputStream fstream_school = new FileInputStream("script.txt"); 
		        FileInputStream fstream_school = new FileInputStream(System.getenv("go_to_lab5_script_file")); 
		        DataInputStream data_input = new DataInputStream(fstream_school); 
		        BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input)); 
		        String str_line; 

		        while ((str_line = buffer.readLine()) != null) 
		        { 
		            str_line = str_line.trim(); 
		            if ((str_line.length()!=0))  
		            { 
		            	scriptElements.add(str_line);
		            } 
		        }

		        arr = (String[])scriptElements.toArray(new String[scriptElements.size()]);
		        
		        scriptLenhgt=arr.length;
		        
		        return (arr[i]);
		    
            
  } 
	
	/**
	 * This method removes all elements whose key is greater or equal to entered key
	 * @param key entered key
	 */
	
	public void remove_greater(long key) {
		int rem = 0;
		
		int count = 0;
		int count2 = 0;
		for(Entry<Integer, Person> m:showPersons2.entrySet()){
			Person a = m.getValue();
			if(key<=m.getKey()) {
				count2++;
			}
		}
		int arr[] = new int[count2];
		
		for(Entry<Integer, Person> m:showPersons2.entrySet()){
			Person a = m.getValue();
			if(key<=m.getKey()) {
				System.out.println(a.getName()+" was removed");
				arr[count]=m.getKey();
				count++;
			}
		}
		//Object ob2 = showPersons2.remove(rem);
		for(int i=0; i<arr.length; i++) {
			Object ob3 = showPersons2.remove(arr[i]);
		}
		count=0; 
	}
	
	/**
	 * This method tests if entered id exist
	 * @param id
	 * @return if id exists method returns 0, if does not then 1
	 */
	 
	public int test_if_ID_exist(long id) {
		int beenThere=1;
		for(Entry<Integer, Person> m:showPersons2.entrySet()){
			Person a = m.getValue();
			if(id==a.getID()) {
				beenThere=0;
			}	
		}
	    return beenThere;
	}
	
	/**
	 * This method removes element form "showPersons2" by it's birthday
	 */
	
	public void remove_birthsday() {
		int year;
		int day;
		int month;
		String birthsday;
		InputStreamReader r2=new InputStreamReader(System.in);    
		BufferedReader br2=new BufferedReader(r2); 
		String keyWord2 = null;
  
		  
		 do {
			  System.out.println("Enter birth year!");
			  try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(keyWord2!=null&&!keyWord2.isEmpty()) {
				  try {
					  year=Integer.parseInt(keyWord2);
					  if(year>1000&&year<2022) {
						 break; 
					  }
				  } catch (NumberFormatException nfe) {
					  System.out.println("Enter VALID year!");
				  }	 
			  }
		  }while(true);
		  
		 
		  do {
			  System.out.println("Enter birth month!");
			  try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(keyWord2!=null&&!keyWord2.isEmpty()) {
				  try {
					  month=Integer.parseInt(keyWord2);
					  if(month>0&&month<=12) {
						 break; 
					  }
				  } catch (NumberFormatException nfe) {
					  System.out.println("Enter VALID month!");
				  }	 
			  }
		  }while(true);
		  
		  
		  do {
			  System.out.println("Enter birth date!");
			  try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(keyWord2!=null&&!keyWord2.isEmpty()) {
				  try {
					  day=Integer.parseInt(keyWord2);
					  if(day>0&&day<=31) {
						 break; 
					  }
				  } catch (NumberFormatException nfe) {
					  System.out.println("Enter VALID date!");
				  }	 
			  }
		  }while(true);
		  
		  String month2 = Integer.toString(month);
		  String year2 = Integer.toString(year);
		  String day2 = Integer.toString(day);
		  
		  if(month<10) {
			  month2="0"+month2;
		  }
		  if(day<10) {
			  day2="0"+day2;
		  }
		  
		  birthsday= year2+"-"+month2+"-"+day2;
		  
		  int res =-1;
		  
		  for(Entry<Integer, Person> m:showPersons2.entrySet()){
				Person a = m.getValue();
				if(a.FgetBirthday().toString().equals(birthsday)) {
					//System.out.println(a.FgetBirthday());
					res=m.getKey();
					System.out.println(a.getName()+" was removed");
				}
					
			}
		  Object ob2 = showPersons2.remove(res);
	}
	
	/**
	 * This method prints out all elements on "showPersons2" in ascending order by their keys
	 */
	
	public void print_ascending() {
		for(Entry<Integer, Person> m:showPersons2.entrySet()){
			Person a = m.getValue();
			System.out.println("Element nr."+m.getKey()+" "+a.getName()+" "+a.FgetBirthday()+" "+a.getEyeColor());	
			
		}
	}
	
	/**
	 * This method prints out all elements on "showPersons2" in descending order by their birthday date
	 */

	public void print_field_descending_birthday() {
		int arr[] = new int[showPersons2.size()];
		LocalDate lastB = LocalDate.now();
		LocalDate lastB2 = LocalDate.now();
		//System.out.println(lastB);
		for(Entry<Integer, Person> m:showPersons2.entrySet()){
			Person a = m.getValue();
			if(lastB.isAfter(a.FgetBirthday())) {
				lastB=a.FgetBirthday();
				arr[0]=m.getKey();	
			}		
		}
		int cancel =0;
		for(int i = 0; i<(showPersons2.size()-1);i++) {
			for(Entry<Integer, Person> m:showPersons2.entrySet()){
				Person a = m.getValue();
				if(lastB.equals(a.FgetBirthday())&&arr[i]!=m.getKey()&&arr[i]<m.getKey()&&cancel==0) {
					lastB2=a.FgetBirthday();
					arr[i+1]=m.getKey();
					cancel=1;
					
				}
				if(lastB.isBefore(a.FgetBirthday())&&lastB2.isAfter(a.FgetBirthday())&&cancel==0) {
					lastB2=a.FgetBirthday();
					arr[i+1]=m.getKey();
					//cancel=1;
					
				}
			}
			cancel=0;
			lastB=lastB2;
			lastB2=LocalDate.now();
		}
		for(int i=arr.length;i>0;i--) {
			for(Entry<Integer, Person> m:showPersons2.entrySet()){
				Person a = m.getValue();
				if(arr[i-1]==m.getKey()) {
				System.out.println(a.getName()+" - "+a.FgetBirthday());
				}
			}
		}
	}
	
	/**
	 * This method collects information from from user to create new "showPersons2" element 
	 * @param id
	 */
	
	public void getPersonInfo(long id) {
		   
		  String name = null;
		  Coordinates coordinates; 
		  Double height;
		  String passportID;
		  Color eyeColor = null;
		  Location location;
		  String birthsday;
		  
		  Double xcor;
		  float ycor;
		  Integer color = null;
		  int xloc;
		  long yloc;
		  long zloc;
		  int year;
		  int day;
		  int month;
		  
		  
		  
		  System.out.println("");
		  System.out.println("Do you realy want to make changes here? [yes/no]");
		  InputStreamReader r2=new InputStreamReader(System.in);    
		  BufferedReader br2=new BufferedReader(r2); 
		  String keyWord2 = null;
		try {
			keyWord2 = br2.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  if(keyWord2.equals("yes")) {
			do {
				System.out.println("Enter name!");
			  try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(keyWord2!=null&&!keyWord2.isEmpty()) {
				  name=keyWord2;
				  //System.out.println(name);
				  break;
			  }
			}while(true);
		  }else {
			 System.out.println("restart to start again");
			 System.exit(0);
			  
		  }
		  System.out.println("Enter x coordinate!");
		  do {
		  //System.out.println("Enter x coordinate!");  
			  try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(keyWord2!=null&&!keyWord2.isEmpty()) {
				  try {
					  xcor=Double.parseDouble(keyWord2);
					  break;
				  } catch (NumberFormatException nfe) {
					  System.out.println("Enter VALID x coordinate!");
				  }	 
			  }
		  }while(true);
		  
		  System.out.println("Enter y coordinate!");
		  do {
			  try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(keyWord2!=null&&!keyWord2.isEmpty()) {
				  try {
					  ycor=Float.parseFloat(keyWord2);
					  break;
				  } catch (NumberFormatException nfe) {
					  System.out.println("Enter VALID y coordinate!");
				  }	 
			  }
		  }while(true);
		  
		  Coordinates newCoordinates = new Coordinates((xcor),(ycor));
		  coordinates = newCoordinates;
		  //System.out.println("x: "+coordinates.getX()+"y: "+coordinates.getY());
		  
		  System.out.println("Enter height!");
		  do {
			  try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(keyWord2!=null&&!keyWord2.isEmpty()) {
				  
				  try {
					  if(Double.parseDouble(keyWord2)>0) {
					  height=Double.parseDouble(keyWord2);
					  break;
					  }else {
						  System.out.println("Hight must be greater then 0! Enter again!");
					  }
				  } catch (NumberFormatException nfe) {
					  System.out.println("Enter VALID height!");
				  }
				  
			  }
		  }while(true);
		  
		  do {
				System.out.println("Enter passportID!");
			  try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(keyWord2!=null&&!keyWord2.isEmpty()) {
				  passportID=keyWord2;
				  //System.out.println(name);
				  break;
			  }
			}while(true);
		  
		  do {
			  System.out.println("Choose eay color 1-5 (" + Arrays.toString(Color.values()) + "): ");
			  
				  try {
					keyWord2 = br2.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
				  if(keyWord2!=null&&!keyWord2.isEmpty()) {
					  try {
						  color=Integer.parseInt(keyWord2);
						  if(color>=1&&color<=5&&color!=null) {
							  eyeColor = Color.values()[color - 1];
							  break;
						  }
					  } catch (NumberFormatException nfe) {
						  System.out.println("Enter VALID symbol!");
					  }
				  }
		  }while(true);
		  
		  System.out.println("Enter x location!");
		  do {
			  try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(keyWord2!=null&&!keyWord2.isEmpty()) {
				  try {
					  xloc=Integer.parseInt(keyWord2);
					  break;
				  } catch (NumberFormatException nfe) {
					  System.out.println("Enter VALID x location!");
				  }	 
			  }
		  }while(true);
		  
		  System.out.println("Enter y location!");
		  do {
			  try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(keyWord2!=null&&!keyWord2.isEmpty()) {
				  try {
					  yloc=Long.parseLong(keyWord2);
					  break;
				  } catch (NumberFormatException nfe) {
					  System.out.println("Enter VALID y location!");
				  }	 
			  }
		  }while(true);
		  
		  System.out.println("Enter z location!");
		  do {
			  try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(keyWord2!=null&&!keyWord2.isEmpty()) {
				  try {
					  zloc=Long.parseLong(keyWord2);
					  break;
				  } catch (NumberFormatException nfe) {
					  System.out.println("Enter VALID z location!");
				  }	 
			  }
		  }while(true);
		  
		  Location newLocation = new Location((xloc),(yloc),(zloc));
		  location = newLocation;
		  //System.out.println(location.getShow());
		  
		  
		 
		  do {
			  System.out.println("Enter birth year!");
			  try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(keyWord2!=null&&!keyWord2.isEmpty()) {
				  try {
					  year=Integer.parseInt(keyWord2);
					  if(year>1000&&year<2022) {
						 break; 
					  }
				  } catch (NumberFormatException nfe) {
					  System.out.println("Enter VALID year!");
				  }	 
			  }
		  }while(true);
		  
		 
		  do {
			  System.out.println("Enter birth month!");
			  try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(keyWord2!=null&&!keyWord2.isEmpty()) {
				  try {
					  month=Integer.parseInt(keyWord2);
					  if(month>0&&month<=12) {
						 break; 
					  }
				  } catch (NumberFormatException nfe) {
					  System.out.println("Enter VALID month!");
				  }	 
			  }
		  }while(true);
		  
		  
		  do {
			  System.out.println("Enter birth date!");
			  try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(keyWord2!=null&&!keyWord2.isEmpty()) {
				  try {
					  day=Integer.parseInt(keyWord2);
					  if(day>0&&day<=31) {
						 break; 
					  }
				  } catch (NumberFormatException nfe) {
					  System.out.println("Enter VALID date!");
				  }	 
			  }
		  }while(true);
		  
		  String month2 = Integer.toString(month);
		  String year2 = Integer.toString(year);
		  String day2 = Integer.toString(day);
		  
		  if(month<10) {
			  month2="0"+month2;
		  }
		  if(day<10) {
			  day2="0"+day2;
		  }
		  
		  birthsday= month2+"/"+day2+"/"+year2;
		  
		  //System.out.println(birthsday);
		 
		  
		  insert(name, coordinates, height, id, passportID, eyeColor, location, birthsday);
		  
	}
}




