package lab5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class works with user inputs
 * @author bruno
 *
 */
public class Userimputs {

	private String[] arrKey;
    private List<String> keyElements = new ArrayList<String>();
	private DataOperations data;
	private InputStream imput;
	private String keyWord;
	private int execute;
	private int scriptlen=-1;
	
	Userimputs(DataOperations data, InputStream imput){
		this.data=data;
		this.imput=imput;
	}
	
	
	/**
	 * This method is used to determine keyword
	 * If function execute_script has been lounged then keyword is command from script file
	 * If function execute_script has not been lounged then keyword is determined by input command 
	 */
	public void entering() {
		InputStreamReader r=new InputStreamReader(imput);    
		BufferedReader br=new BufferedReader(r);
		ExecuteScript exsc = new ExecuteScript();
		System.out.println("________________________________________________________________");
		System.out.println("Enter your key word");    

		if(getexecute()>0) {
			try {
				setscriptlen();
				keyWord=exsc.execute_script(getscriptlen());
				if(getscriptlen()+1>=exsc.execute_script_lenght()) {
					execute=0;
					scriptlen=0;
				}
				System.out.println(keyWord);
				this.entering2();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			keyWord = br.readLine();
			this.entering2();
		} catch (IOException e) {
			e.printStackTrace();
		}    
		
	}
	
	/**
	 * This method is used to operate with user inputs and to run method from dataOperation class
	 */	
	public void entering2() {	
		InputStreamReader r=new InputStreamReader(imput);    
		BufferedReader br=new BufferedReader(r); 
		
		keyElements.add(keyWord);
		arrKey = (String[])keyElements.toArray(new String[keyElements.size()]);
		
		switch(keyWord) {
		
		  case "show": 
			PrintOut prSh = new PrintOut(data.getShowPersons2());
			prSh.show();
		    this.entering();
		    break;
		    
		  case "help":
			  PrintOut prHe = new PrintOut(data.getShowPersons2());
			  prHe.help();
			  this.entering();
		    break;
		    
		  case "info":
			  PrintOut prIn = new PrintOut(data.getShowPersons2());
			  prIn.info();
			  this.entering();
		    break;
		    
		  case "insert":
			  IDoperations newinsert = new IDoperations(data.getShowPersons2());
			  EnterNewPerson np = new EnterNewPerson(newinsert.setID());
			  np.getPersonInfo();
			  data.insert(np.getName(), np.getCord(), np.getHeight(), np.getID(), np.getPassportID(), np.getEyeColor(), np.getLocation(), np.getBirthsday());
			  this.entering();
		    break;
		    
		 case "update":
			 long updateId=0;
			  do {
			  System.out.println("Enter ID");
			  try {
					keyWord = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			  if (keyWord != null && !keyWord.isEmpty()) {
				  try {
					  updateId =Long.parseLong(keyWord);
					} catch (NumberFormatException nfe) {
						System.out.print("Value is NOT VALID - ");
					}
			  }
			  }while(updateId == 0);
			   
			  data.update(updateId);
			  this.entering();
		 break;
		 
		 case "replace_if_greater":
			long last=0;
			long newIDelement = 0;
			do {
				System.out.println("Enter ID");
				try {
					keyWord = br.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (keyWord != null && !keyWord.isEmpty()) {
					try {
					  	last =Long.parseLong(keyWord);
					} catch (NumberFormatException nfe) {
						System.out.print("Value is NOT VALID - ");
					}
					}
			}while(last == 0);
			
			do {
				System.out.println("Enter NEW ID");
				try {
					keyWord = br.readLine();
				} catch (IOException e) {
						e.printStackTrace();
				}
				if (keyWord != null && !keyWord.isEmpty()) {
				try {
					newIDelement =Long.parseLong(keyWord);
				} catch (NumberFormatException nfe) {
						System.out.print("Value is NOT VALID - ");
				}
				}
				if(newIDelement>last) {
					IDoperations newtest = new IDoperations(data.getShowPersons2());
					if(newtest.testIfIDExist(Long.parseLong(keyWord))==1) {
						Remove remRIG = new Remove(data.getShowPersons2());
						remRIG.removeFun(last);
						EnterNewPerson rigp = new EnterNewPerson(Long.parseLong(keyWord));
						rigp.getPersonInfo();
						data.insert(rigp.getName(), rigp.getCord(), rigp.getHeight(), rigp.getID(), rigp.getPassportID(), rigp.getEyeColor(), rigp.getLocation(), rigp.getBirthsday());
					}else {
						newIDelement = 0;
						System.out.print("ID you entered already exist! - ");
					}
				}else {
					newIDelement = 0;
					System.out.print("New ID is not greater then last ID! - ");
				}
			}while(newIDelement == 0);
			this.entering();
		 break;
			 
		  case "remove":
			  long remElem=0;
			  do {
			  System.out.println("Enter ID");
			  try {
					keyWord = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			  if (keyWord != null && !keyWord.isEmpty()) {
				  try {
					  remElem =Long.parseLong(keyWord);
					} catch (NumberFormatException nfe) {
						System.out.print("Value is NOT VALID - ");
					}
			  }
			  }while(remElem == 0);
			  Remove remRem = new Remove(data.getShowPersons2());
			  remRem.removeFun(remElem);
			  this.entering();
		    break;
		    
		  case "remove_key":
			  int remKey=-1;
			  do {
			  System.out.println("Enter Key");
			  try {
					keyWord = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			  if (keyWord != null && !keyWord.isEmpty()) {
				  try {
					  remKey =Integer.parseInt(keyWord);
					} catch (NumberFormatException nfe) {
						System.out.print("Value is NOT VALID - ");
					}
			  }
			  }while(remKey == -1);
			  
			  Remove remRemK = new Remove(data.getShowPersons2());
			  remRemK.remove_key(remKey);
			  this.entering();
		    break;
		    
		  case "clear":
			  Remove remCl = new Remove(data.getShowPersons2());
			  remCl.clear();
			  this.entering();
		    break;
		     
		  case "execute_script": 
			  setexecute();
			  this.entering();
		    break;
		    
		  case "print_ascending":
			  PrintOut prAs = new PrintOut(data.getShowPersons2());
			  prAs.print_ascending();
			  this.entering();
		    break;
		    
		  case "print_field_descending_birthday": 
			  PrintOut prDe = new PrintOut(data.getShowPersons2());
			  prDe.print_field_descending_birthday();
			  this.entering();
		    break;
		    
		  case "printb": 
			  PrintOut prDe2 = new PrintOut(data.getShowPersons2());
			  prDe2.print_field_descending_birthday();
			  this.entering();
		    break;
		    
		  case "print": 
			  PrintOut prAs2 = new PrintOut(data.getShowPersons2());
			  prAs2.print_ascending();
			  this.entering();
		    break;
		    
		  case "exit": 
			  System.out.println("You have exited the programm! Pleas, restart to make more changes!");
			  System.exit(0);
		    break;
		    
		  case "remove_birthsday": 
			  Remove remBi = new Remove(data.getShowPersons2());
			  remBi.remove_birthsday();
			  this.entering();
		    break;
		    
		  case "remove_greater":
			  long keyVal=0;
			  do {
			  System.out.println("Enter greates key from witch you want to remove!");
			  try {
					keyWord = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			  if (keyWord != null && !keyWord.isEmpty()) {
				  try {
						keyVal =Long.parseLong(keyWord);
					} catch (NumberFormatException nfe) {
						System.out.print("Value is NOT VALID - ");
					}
			  }
			  }while(keyVal == 0);
			  Remove remGr = new Remove(data.getShowPersons2());
			  remGr.remove_greater(Long.parseLong(keyWord));
			  this.entering();
		    break;
		    
		  case "save":
			  try {
				Save newSave = new Save(data.getShowPersons2(),data.getEnvvariable());
				newSave.save();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			  System.out.println("Your data has been successfully SAVED");
			  this.entering();
		    break;
		    
		  case "history":
			  	int var=0;
			  	if(arrKey.length>6) {
			  		var=(arrKey.length-6);
			  	}
			  	int len;
			  	if(arrKey.length>6) {
			  		len=0;
			  	}
			  	System.out.println("|");
			    for(int i =var; i<(arrKey.length); i++) {
			    	System.out.println(arrKey[i]);
			    }
			    this.entering();
			    break;
		   
		    
		  default:
			  System.out.println("You have entered unexisting key word. To see all accessable "
			  		+ "key words pleas type 'help'");
			  this.entering();
		}
	}
	
	public int getexecute() {
		return execute;
	}
	
	public void setexecute() {
		this.execute=execute+1;
	}
	
	public int getscriptlen() {
		return scriptlen;
	}
	
	public void setscriptlen() {
		this.scriptlen=scriptlen+1;
	}	
}
