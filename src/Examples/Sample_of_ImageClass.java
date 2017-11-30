package Examples;
import org.nng.utils.Image;

public class Sample_of_ImageClass {
	
	// Method
	public static void main(String[] args) throws Exception {
	
		//String key = "url";
		//String value= "https://www.google.co.in";
		//String imageFilePath = "E:\\dump\\a.png";
		// BUG:
		// If You Get the Exception :
		// Exception in thread "main" java.lang.IllegalArgumentException: input == null!
		// Then
		// It means, the file @param that you are passing is either not accessible or does not exist.
		
		//= Set META Data
			//Image.setMetaData("nng", "Nitya_Narayan_Gautam", "E:\\dump\\d.jpg");
			
		//= Get META Data
			String result = Image.getMetaData("nng", "E:\\dump\\d.png");
		
		//= Logout the result
			System.out.println("Image MetaData Key = 'nng' and Value = " + result);
	}
}
