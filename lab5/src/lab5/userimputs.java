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

public class userimputs {
	

	String[] arrKey= null;
    List<String> keyElements = new ArrayList<String>();
	dataOperations data;
	InputStream imput;
	String keyWord;
	
	private int execute;
	private int scriptlen=-1;
	
	userimputs(dataOperations data, InputStream imput){
		this.data=data;
		this.imput=imput;
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
	
	/**
	 * This method is used to determine keyword
	 * If function execute_script has been lounged then keyword is command from script file
	 * If function execute_script has not been lounged then keyword is determined by input command 
	 */
	
	public void entering() {
		InputStreamReader r=new InputStreamReader(imput);    
		BufferedReader br=new BufferedReader(r); 
		System.out.println("________________________________________________________________");
		System.out.println("Enter your key word");    

		if(getexecute()>0) {
			try {
				setscriptlen();
				keyWord=data.execute_script(getscriptlen());
				if(getscriptlen()+1>=data.execute_script_lenght()) {
					execute=0;
					scriptlen=0;
				}								
				this.entering2();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			keyWord = br.readLine();
			this.entering2();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		//System.out.println("Welcome "+keyWord);
		
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
		    data.show();
		    this.entering();
		    break;
		    
		  case "help":
			  data.help();
			  this.entering();
		    break;
		    
		  case "info":
			  data.info();
			  this.entering();
		    break;
		    
		  case "insert":
			  data.getPersonInfo(data.setID());
			  this.entering();
		    break;
		    
		 case "update":
			  System.out.println("Enter ID");
			  
			  try {
				keyWord = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
			  data.update(Long.parseLong(keyWord));
			  this.entering();
		 break;
		 
		 case "replace_if_greater":
			  System.out.println("Enter ID");
			  
			  try {
				keyWord = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  long last = Long.parseLong(keyWord);
			  System.out.println("Enter NEW ID");
			  
			  
			  do {
				  try {
						keyWord = br.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  
				  if(data.test_if_ID_exist(Long.parseLong(keyWord))==1) {
					  break;
				  }else {
					  System.out.println("ID you entered already exist! Enter NEW!");
				  }
			  }while(true);
			  
			  if(Long.parseLong(keyWord)>last) {
				  //System.out.println("keyW: "+keyWord);
				  //System.out.println("last: "+last);
				  //data.update(Long.parseLong(keyWord));
				  data.removeFun(last);
				  data.getPersonInfo(Long.parseLong(keyWord));
				  this.entering();
			  }else {
				System.out.println("New ID is not greater then last ID!");
			    this.entering();
			  }
		 break;
			 
		  case "remove":
			  System.out.println("Enter ID");
			  try {
					keyWord = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			  data.removeFun(Long.parseLong(keyWord));
			  this.entering();
		    break;
		    
		  case "remove_key":
			  System.out.println("Enter key");
			  try {
					keyWord = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			  data.remove_key(Integer.parseInt(keyWord));
			  this.entering();
		    break;
		    
		  case "clear":
			  data.clear();
			  this.entering();
		    break;
		     
		  case "execute_script": 
			  setexecute();
			  this.entering();
		    break;
		    
		  case "print_ascending": 
			  data.print_ascending();
			  this.entering();
		    break;
		    
		  case "print_field_descending_birthday": 
			  data.print_field_descending_birthday();
			  this.entering();
		    break;
		    
		  case "printb": 
			  data.print_field_descending_birthday();
			  this.entering();
		    break;
		    
		  case "print": 
			  data.print_ascending();
			  this.entering();
		    break;
		    
		  case "exit": 
			  System.out.println("You have exited the programm! Pleas, restart to make more changes!");
			  System.exit(0);
		    break;
		    
		  case "remove_birthsday": 
			  data.remove_birthsday();
			  this.entering();
		    break;
		    
		  case "remove_greater": 
			  System.out.println("Enter greates key from witch you want to remove!");
			  try {
					keyWord = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			  data.remove_greater(Long.parseLong(keyWord));
			  this.entering();
		    break;
		    
		  case "save":
			  try {
				data.save();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
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
			  	//System.out.println(var);
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
	

	
	
	
}
