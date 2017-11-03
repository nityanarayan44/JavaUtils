/**
 * @author Ashutosh Mishra
 */

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.File;

import org.nng.utils.Files;

public class WriteABrowser {
	@Test() 
	@Parameters("browserName=chrome")
	public void writeFile(String browserName) throws Exception{
	  // Write file
		(new Files("browserFile")).writeFile(browserName);
	}
	
	// At your constant file
	@Test()
	public void setBrowser() throws Exception {
		File file = new File("browserFile");
		String browser = (new Files()).readFile(file).get(0);
		System.out.println(" >>>>>> " + browser );
		return;
	}
}
