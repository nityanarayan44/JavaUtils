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

	// Close the file.
	public void closeFile() throws Exception { if(!pw.equals(null)) pw.close(); }
}
