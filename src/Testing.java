import java.util.HashMap;

import org.nng.utils.Reports;

public class Testing {
	
	public static void main(String[] args) throws Exception {
		
		// Setting data
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("projectID", "pId-100003");
		data.put("projectName", "prj-1");
		data.put("testCaseName", "GoogleSearch");
		data.put("teststepName", "pEntering the ");
		data.put("teststepStatus", "pId-100003");
		data.put("teststepTime", "pId-100003");
		
		Reports report = new Reports("http://127.0.0.1:5000/nng/core/api/v1/sendTestData", "POST");
		report.sendData( data );
		
	}
}
