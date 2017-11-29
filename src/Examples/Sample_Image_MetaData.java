package Examples;
import org.nng.utils.Image;

public class Sample_Image_MetaData {
	
	// Method
	public static void main(String[] args) throws Exception {
	
		//String key = "url";
		//String value= "https://www.google.co.in";
		//String imageFilePath = "E:\\dump\\a.png";
		
		//= Set META Data
			Image.setMetaData("url", "https://www.google.co.in", "E:\\dump\\a.png");
			
		//= Get META Data
			String result = Image.getMetaData("url", "E:\\dump\\a.png");
		
		//= Logout the result
			System.out.println("Image MetaData Key = 'url' and Value = " + result);
	}
}
