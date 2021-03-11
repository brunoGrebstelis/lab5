package lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * This class is responsible for new person addition 
 * @author bruno
 *
 */

public class EnterNewPerson {
	
	private String name;
	private Coordinates coordinates;
	private Double height;
	private String passportID;
	private Color eyeColor;
	private Location location;
	private String birthsday;
	private long id;

	private Double xcor;
	private float ycor;
	private Integer color;
	private int xloc;
	private long yloc;
	private long zloc;
	private int year;
	private int day;
	private int month;
	
	EnterNewPerson(long id){
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public Coordinates getCord() {
		return coordinates;
		
	}
	
	public Double getHeight() {
		return height;
	}
	
	public long getID() {
		return id;
	}
	
	public String getPassportID() {
		return passportID;
	}
	
	public Color getEyeColor() {
		return eyeColor;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public String getBirthsday() {
		return birthsday;
	}
	
	public void getPersonInfo() {

		System.out.println("");
		System.out.println("Do you realy want to make changes here? [yes/no]");
		InputStreamReader r2 = new InputStreamReader(System.in);
		BufferedReader br2 = new BufferedReader(r2);
		String keyWord2 = null;
		try {
			keyWord2 = br2.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (keyWord2.equals("yes")) {
			do {
				System.out.println("Enter name!");
				try {
					keyWord2 = br2.readLine();
					name = keyWord2;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			} while (keyWord2==null);
		} else {
			System.out.println("restart to start again");
			System.exit(0);

		}
		System.out.println("Enter x coordinate!");
		do {
			// System.out.println("Enter x coordinate!");
			try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (keyWord2 != null && !keyWord2.isEmpty()) {
				try {
					xcor = Double.parseDouble(keyWord2);
					break;
				} catch (NumberFormatException nfe) {
					System.out.println("Enter VALID x coordinate!");
				}
			}
		} while (true);

		System.out.println("Enter y coordinate!");
		do {
			try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (keyWord2 != null && !keyWord2.isEmpty()) {
				try {
					ycor = Float.parseFloat(keyWord2);
					break;
				} catch (NumberFormatException nfe) {
					System.out.println("Enter VALID y coordinate!");
				}
			}
		} while (true);

		Coordinates newCoordinates = new Coordinates((xcor), (ycor));
		coordinates = newCoordinates;
		// System.out.println("x: "+coordinates.getX()+"y: "+coordinates.getY());

		System.out.println("Enter height!");
		do {
			try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (keyWord2 != null && !keyWord2.isEmpty()) {

				try {
					if (Double.parseDouble(keyWord2) > 0) {
						height = Double.parseDouble(keyWord2);
						break;
					} else {
						System.out.println("Hight must be greater then 0! Enter again!");
					}
				} catch (NumberFormatException nfe) {
					System.out.println("Enter VALID height!");
				}

			}
		} while (true);

		do {
			System.out.println("Enter passportID!");
			try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (keyWord2 != null && !keyWord2.isEmpty()) {
				passportID = keyWord2;
				// System.out.println(name);
				break;
			}
		} while (true);

		do {
			System.out.println("Choose eay color 1-5 (" + Arrays.toString(Color.values()) + "): ");

			try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (keyWord2 != null && !keyWord2.isEmpty()) {
				try {
					color = Integer.parseInt(keyWord2);
					if (color >= 1 && color <= 5 && color != null) {
						eyeColor = Color.values()[color - 1];
						break;
					}
				} catch (NumberFormatException nfe) {
					System.out.println("Enter VALID symbol!");
				}
			}
		} while (true);

		System.out.println("Enter x location!");
		do {
			try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (keyWord2 != null && !keyWord2.isEmpty()) {
				try {
					xloc = Integer.parseInt(keyWord2);
					break;
				} catch (NumberFormatException nfe) {
					System.out.println("Enter VALID x location!");
				}
			}
		} while (true);

		System.out.println("Enter y location!");
		do {
			try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (keyWord2 != null && !keyWord2.isEmpty()) {
				try {
					yloc = Long.parseLong(keyWord2);
					break;
				} catch (NumberFormatException nfe) {
					System.out.println("Enter VALID y location!");
				}
			}
		} while (true);

		System.out.println("Enter z location!");
		do {
			try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (keyWord2 != null && !keyWord2.isEmpty()) {
				try {
					zloc = Long.parseLong(keyWord2);
					break;
				} catch (NumberFormatException nfe) {
					System.out.println("Enter VALID z location!");
				}
			}
		} while (true);

		Location newLocation = new Location((xloc), (yloc), (zloc));
		location = newLocation;
		// System.out.println(location.getShow());

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
					if (year > 1000 && year < 2022) {
						break;
					}
				} catch (NumberFormatException nfe) {
					System.out.println("Enter VALID year!");
				}
			}
		} while (true);

		do {
			System.out.println("Enter birth month!");
			try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (keyWord2 != null && !keyWord2.isEmpty()) {
				try {
					month = Integer.parseInt(keyWord2);
					if (month > 0 && month <= 12) {
						break;
					}
				} catch (NumberFormatException nfe) {
					System.out.println("Enter VALID month!");
				}
			}
		} while (true);

		do {
			System.out.println("Enter birth date!");
			try {
				keyWord2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (keyWord2 != null && !keyWord2.isEmpty()) {
				try {
					day = Integer.parseInt(keyWord2);
					if (day > 0 && day <= 31) {
						break;
					}
				} catch (NumberFormatException nfe) {
					System.out.println("Enter VALID date!");
				}
			}
		} while (true);

		String month2 = Integer.toString(month);
		String year2 = Integer.toString(year);
		String day2 = Integer.toString(day);

		if (month < 10) {
			month2 = "0" + month2;
		}
		if (day < 10) {
			day2 = "0" + day2;
		}

		birthsday = month2 + "/" + day2 + "/" + year2;

	}

}
