package lab5;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains 2 methods which help Userimputs class execute script file
 * @author bruno
 *
 */

public class ExecuteScript {
	
	private int scriptLenhgt;
	
	public int execute_script_lenght() {
		return scriptLenhgt;
	}

	public String execute_script(int i) throws IOException {

		List<String> scriptElements = new ArrayList<String>();

		//FileInputStream fstream_school = new FileInputStream("script.txt");
		FileInputStream fstream_school = new FileInputStream(System.getenv("GoToLab5ScriptFile"));
		DataInputStream data_input = new DataInputStream(fstream_school);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input));
		String str_line;

		while ((str_line = buffer.readLine()) != null) {
			str_line = str_line.trim();
			if ((str_line.length() != 0)) {
				scriptElements.add(str_line);
			}
		}

		scriptLenhgt = scriptElements.size();

		return (scriptElements.get(i));

	}

}
