import org.nng.utils.Image;
import org.nng.utils.Image;

public class Image_MetaData {
	
	// Method
	public static void main(String[] args) throws Exception {
	
		//= [Create the Class Object]
			//TODO: Make this Image Class to Static
			//Images img = new Images();
			//String key = "url";
			//String value= "https://www.google.co.in";
		
		//= Set META Data
			Image.setMetaData("url", "https://www.google.co.in", "E:\\dump\\a.png");
			
		//= Get META Data
			String result = Image.getMetaData("url", "E:\\dump\\a.png");
		
		//= Logout the result
			System.out.println("Value = " + result);
	}
}
