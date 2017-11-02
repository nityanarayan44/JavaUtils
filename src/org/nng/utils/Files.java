// @Author: AShutosh Mishra

import java.io.*;
import java.util.*;

public class Files {
	PrintWriter pw = null;
	String file = null;
	// Constructor
	public Files(String file) throws Exception {
		pw = new PrintWriter(new File(file));
	}

	//Write content inside a file.
	public void writeFile(String text) throws Exception {
		// If you want this file in a specific location then give absolute path along with the filename.
		String file = "yourFileName.ext";
		pw.write(text);
	}
	
	//Read the files
	public String readFile(File fileObj) throws Exception {
		System.out.println("Reading ....");
		// Vars
			String text = "";
			BufferedReader br = null;
			FileReader fr = null;
			String sCurrentLine = "";
			
		//Initiating file reader and buffer reader.
			fr = new FileReader(fileObj);
			br = new BufferedReader(fr);
			
		//Reading file now, line by line.
			while ((sCurrentLine = br.readLine()) != null) {
				//trim all the gaps and tabs
					String line = sCurrentLine.replaceAll("[\t]+", "");
				//split the line, if it contains '='
					text += line;
			}
		
		//Close the stream now.
			if (br != null) br.close();
			if (fr != null) fr.close();
			
		// return the data
			return text;
	}
	// Close the file.
	public void closeFile() throws Exception { if(!pw.equals(null)) pw.close(); }
}
