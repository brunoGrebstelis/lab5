package lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * This class is responsible for all kinds of removal methods
 * @author bruno
 *
 */

public class Remove {
	
	private LinkedHashMap<Integer, Person> showPersons2;
	Remove(LinkedHashMap<Integer, Person> showPersons2){
		this.showPersons2=showPersons2;
	}
	
	public void removeFun(long id) {

		int rem = 0;
		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			Person a = m.getValue();
			if (a.getID() == id) {
				rem = m.getKey();
				System.out.println(a.getName() + " was removed");
			}
		}
		Object ob2 = showPersons2.remove(rem);
	}
	
	public void remove_key(int key) {
		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			Person a = m.getValue();
			if (m.getKey() == key) {
				System.out.println(a.getName() + " was removed");
			}
		}
		Object ob2 = showPersons2.remove(key);
	}
	
	public void clear() {

		showPersons2.clear();
		System.out.println("You have deleted all elements!");

	}
	
	public void remove_greater(long key) {
		int count = 0;
		int count2 = 0;
		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			if (key <= m.getKey()) {
				count2++;
			}
		}
		int arr[] = new int[count2];

		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			Person a = m.getValue();
			if (key <= m.getKey()) {
				System.out.println(a.getName() + " was removed");
				arr[count] = m.getKey();
				count++;
			}
		}
		// Object ob2 = showPersons2.remove(rem);
		for (int i = 0; i < arr.length; i++) {
			Object ob3 = showPersons2.remove(arr[i]);
		}
		count = 0;
	}
	
	
	public void remove_birthsday() {
		int year=0;
		int day=0;
		int month=0;
		String birthsday;
		InputStreamReader r2 = new InputStreamReader(System.in);
		BufferedReader br2 = new BufferedReader(r2);
		String keyWord2 = null;

		do {
			System.out.println("Enter birth year!");
			try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (keyWord2 != null && !keyWord2.isEmpty()) {
				try {
					year = Integer.parseInt(keyWord2);
				} catch (NumberFormatException nfe) {
					System.out.print("Value is NOT VALID - ");
				}
			}
		} while (year==0||year<1000||year>2022);

		do {
			System.out.println("Enter birth month!");
			try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (keyWord2 != null && !keyWord2.isEmpty()) {
				try {
					month = Integer.parseInt(keyWord2);
				} catch (NumberFormatException nfe) {
					System.out.print("Value is NOT VALID - ");
				}
			}
		} while (month==0||month<1||month>12);

		do {
			System.out.println("Enter birth date!");
			try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (keyWord2 != null && !keyWord2.isEmpty()) {
				try {
					day = Integer.parseInt(keyWord2);
				} catch (NumberFormatException nfe) {
					System.out.print("Value is NOT VALID - ");
				}
			}
		} while (day==0||day<1||day>31);

		String month2 = Integer.toString(month);
		String year2 = Integer.toString(year);
		String day2 = Integer.toString(day);

		if (month < 10) {
			month2 = "0" + month2;
		}
		if (day < 10) {
			day2 = "0" + day2;
		}

		birthsday = year2 + "-" + month2 + "-" + day2;

		int res = -1;

		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			Person a = m.getValue();
			if (a.FgetBirthday().toString().equals(birthsday)) {
				// System.out.println(a.FgetBirthday());
				res = m.getKey();
				System.out.println(a.getName() + " was removed");
			}

		}
		Object ob2 = showPersons2.remove(res);
	}

	
}
