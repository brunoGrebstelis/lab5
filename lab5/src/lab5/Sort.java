package lab5;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * This class sorts data by its key
 * @author bruno
 *
 */

public class Sort {
	private LinkedHashMap<Integer,Person> showPersons2;
	private LinkedHashMap<Integer,Person> showPersons3=new LinkedHashMap<Integer,Person>();
	Sort(LinkedHashMap<Integer,Person> showPersons2){
		this.showPersons2=showPersons2;
	}
	
	public void sort() {
		
		int lastKey =0;
		
		do {
		for(Entry<Integer, Person> m:showPersons2.entrySet()){
			Person a = m.getValue();
			if(m.getKey()==lastKey) {
				showPersons3.put(lastKey, m.getValue());
				lastKey++;
			}
		}
		
		//if(showPersons3.size()==showPersons2.size()) {
		//	break;
		//}
		}while(showPersons2.size()>showPersons3.size());

		showPersons2.clear();
		int x = 0;
		for (Entry<Integer, Person> m : showPersons3.entrySet()) {
			Person a = m.getValue();
			showPersons2.put(x, m.getValue());
			x++;
		}
	}
}
