package lab5;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * This class tests if id exists and sets new id
 * @author bruno
 *
 */

public class IDoperations {
	
	private LinkedHashMap<Integer, Person> showPersons2;
	IDoperations(LinkedHashMap<Integer, Person> showPersons2){
		this.showPersons2=showPersons2;
	}
	
	public int testIfIDExist(long id) {
		int beenThere = 1;
		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			Person a = m.getValue();
			if (id == a.getID()) {
				beenThere = 0;
			}
		}
		return beenThere;
	}
	
	public long setID() {
		long maxID = 0;
		if (showPersons2.size() == 0) {
			maxID = 0;
		} else {
			for (Entry<Integer, Person> m : showPersons2.entrySet()) {
				Person a = m.getValue();
				if (a.getID() > maxID) {
					maxID = (a.getID());
				}
			}
		}
		return maxID + 1;
	}	
}
