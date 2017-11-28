/**
 * @package org.nng.utils
 * @author Ashutosh Mishra [@github: nityanarayan44]
 * @desc To send data to Reporting Server under "org.nng.utils" package
 * 
 */
//Import Section
package org.nng.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class Reports {


	/* 
	 * +====================================+
	 * | Global Instance					|
	 * +====================================+
	 */
		// Connection META Properties
		private String REQ_SERVER_URL 	= "http://localhost:8080"; //nng/v1/core/sendTestData/
		private String REQ_METHOD		= "POST";
		private String REQ_USER_AGENT	= "Mozilla/5.0";
		private String REQ_LANGUAGE		= "en-US,en;q=0.5";
		private String REQ_CONTENT_TYPE	= "application/json";
		
		private String RES_STATUS_CODE	= "";
		private String RES_MESSAGE		= "";
		private String RES_HEADERS		= "";
		private String RES_DATA			= "";
		
		// Connection Instance
		HttpsURLConnection httpsConnection	= null;
		HttpURLConnection httpConnection	= null;
		
		// URL Instance
		URL urlObject	= null;
		
		// Params
		private String projectId, projectName;
		private String testCaseName, testStepName, testStepStatus, testStepTime;
		private JSONObject jsonObject;
		private JSONArray jsonArray;
		private JSONObject testStepData;
		
		// Logger
		private Logger logger = Logger.getAnonymousLogger();
		
		// Extra
		private long TimeStamp_t1 = 0;
		private long TimeStamp_t2 = 0;
		
	/* 
	 * +====================================+
	 * | Constructors						|
	 * +====================================+
	 */
		public Reports() throws Exception {
			//Initializing
			jsonObject 	= new JSONObject();
			jsonArray 	= new JSONArray();

			// Start the connection
			this.urlObject 	= new URL(this.REQ_SERVER_URL);
			this.httpConnection 	= (HttpURLConnection)this.urlObject.openConnection();
			
			// Setting Connection META Property
			this.httpConnection.setRequestMethod(this.REQ_METHOD);
			this.httpConnection.setRequestProperty("Accept-Language",this.REQ_LANGUAGE);
			this.httpConnection.setRequestProperty("Content-Type", 	this.REQ_CONTENT_TYPE);
			this.httpConnection.setRequestProperty("User-Agent", 	this.REQ_USER_AGENT);
			
			// Last
		}
		
		public Reports(String serverUrl) throws Exception{
			//Initializing instances
				jsonObject 	= new JSONObject();
				jsonArray 	= new JSONArray();
				
			// set the target server params
				this.REQ_SERVER_URL = serverUrl;
			
			// Start the connection
				this.urlObject 	= new URL(this.REQ_SERVER_URL);
				httpConnection 	= (HttpURLConnection)this.urlObject.openConnection();
				
			// Setting Connection META Property
				httpConnection.setRequestMethod(this.REQ_METHOD);
				httpConnection.setRequestProperty("Accept-Language",this.REQ_LANGUAGE);
				httpConnection.setRequestProperty("Content-Type", 	this.REQ_CONTENT_TYPE);
				httpConnection.setRequestProperty("User-Agent", 	this.REQ_USER_AGENT);
			// Last
		}
		
		public Reports(String serverUrl, String serverMethod) throws Exception{
			//Initializing instances
				jsonObject 	= new JSONObject();
				jsonArray 	= new JSONArray();
				
			// set the target server params
				this.REQ_SERVER_URL = serverUrl;
				this.REQ_METHOD		= serverMethod;
			
			// Start the connection
				this.urlObject 	= new URL(this.REQ_SERVER_URL);
				httpConnection 	= (HttpURLConnection)this.urlObject.openConnection();
				
			// Setting Connection META Property
				httpConnection.setRequestMethod(this.REQ_METHOD);
				httpConnection.setRequestProperty("Accept-Language",this.REQ_LANGUAGE);
				httpConnection.setRequestProperty("Content-Type", 	this.REQ_CONTENT_TYPE);
				httpConnection.setRequestProperty("User-Agent", 	this.REQ_USER_AGENT);
			// Last
		}
		
		// If user wants to set its own meta property of server then
		public Reports(String serverUrl, String serverMethod, HashMap<String, String> serverMeta) throws Exception{
			//Initializing instances
				jsonObject 	= new JSONObject();
				jsonArray 	= new JSONArray();
				
			// set the target server params
				this.REQ_SERVER_URL = serverUrl;
				this.REQ_METHOD		= serverMethod;
			
			// Start the connection
				this.urlObject 	= new URL(this.REQ_SERVER_URL);
				httpConnection 	= (HttpURLConnection)this.urlObject.openConnection();
				
			// Setting Connection META Property
				httpConnection.setRequestMethod(this.REQ_METHOD);
			
			// Setting passed server request META
				for ( String key : serverMeta.keySet()) {
					this.httpConnection.setRequestProperty(key, serverMeta.get(key));
				}
				
			// Last
		}
		
	/* 
	 * +====================================+
	 * | Private Methods					|
	 * +====================================+
	 */
		
		//
		// Putting server params into JSON Object
		//
		private int setJSONObjectData(HashMap<String, String> data) throws Exception {
			// Status variable
			int status = -1;
			// Putting into a JSON Object
			for ( String key : data.keySet()) {
				this.jsonObject.put(key, data.get(key));
			} status = 0;
			//Return status
			return status;
		}
		
		//
		// Fixed Params to be send out.
		//
		private String setData(String projectId, String projectName, String testCaseName, String testCaseStepName, String testCaseStepStatus, String testCaseStepTime) throws Exception {
			// Organizing data
			jsonObject.put("timeStamp", System.currentTimeMillis());
			jsonObject.put("projectId", projectId);
			jsonObject.put("projectName", projectName);
			jsonObject.put("testCaseName", testCaseName);
			// Grouping TestCase data in to a different JSON object.
			testStepData.put("StepName", testCaseStepName);
			testStepData.put("StepStatus", testCaseStepStatus);
			testStepData.put("StepTime", testCaseStepTime);
			
			jsonObject.put("testCaseData", testStepData);
			// Returning data
			return jsonObject.toString();
		}
		
		private String setData(String testCaseName, String testCaseStepName, String testCaseStepStatus, String testCaseStepTime) throws Exception {
			// Organizing data
			jsonObject.put("timeStamp", System.currentTimeMillis());
			jsonObject.put("projectId", this.projectId);
			jsonObject.put("projectName", this.projectName);
			jsonObject.put("testCaseName", testCaseName);
			// Grouping TestCase data in to a different JSON object.
			testStepData.put("StepName", testCaseStepName);
			testStepData.put("StepStatus", testCaseStepStatus);
			testStepData.put("StepTime", testCaseStepTime);
			
			jsonObject.put("testCaseData", testStepData);
			// Returning data
			return jsonObject.toString();
		}
		
		private void log(String message, int status) throws Exception {
			switch(status) {
				case 200: logger.log(Level.INFO, message+"\n"+"Report Server: Okay"); break;
				default: logger.log(Level.SEVERE, message+"\n"+"Report Server: Not Okay"); break;
			}
			return;
		}
	
	/* 
	 * +====================================+
	 * | Public Methods						|
	 * +====================================+
	 * = Online Reporting Server
	 * - Reports.sendData
	 * 
	 * TODO:
	 * = For Offline Reporting
	 * - Reports.putData 			[To Store the data locally as buffered data]
	 * - Reports.generateJSONFile	[Generate the JSON file from buffered data]
	 * - Reports.generateXMLFile	[Generate the XML file from buffered data]
	 * - Reports.generateHTMLFile	[Generate the HTML file from buffered data]
	 */
		// Capture current time-stamp
		public void startTimeCapture() throws Exception {
			this.TimeStamp_t1 = System.currentTimeMillis();
			this.TimeStamp_t2 = 0;
			return;
		}
		
		// Returns the long time in mili seconds
		public long stopTimeCapture() throws Exception {
			if(this.TimeStamp_t1 != 0)
				this.TimeStamp_t2 = System.currentTimeMillis() - this.TimeStamp_t1;
			else
				this.TimeStamp_t2 = -1;
			
			return this.TimeStamp_t2;
		}
		
		public String sendData( HashMap<String, String> dataToSend) throws Exception {
			
			// Pack the Data
				this.setJSONObjectData( dataToSend );
				String data = this.jsonObject.toString();
				
			// Send the Data
				this.httpConnection.setDoOutput(true);	
				DataOutputStream request_dataOutputStream = new DataOutputStream(this.httpConnection.getOutputStream());
				request_dataOutputStream.writeBytes(data);
				request_dataOutputStream.flush();
				request_dataOutputStream.close();
				
			// Receiving the server response.
				// Getting Response Status Code
				this.RES_STATUS_CODE= this.httpConnection.getResponseCode() + " ";
				this.RES_MESSAGE 	= this.httpConnection.getResponseMessage();
				
				// Getting Response input Stream
				BufferedReader response_incommingStream = new BufferedReader(new InputStreamReader(this.httpConnection.getInputStream()));
				
				// Reading the Response input stream
				StringBuffer response = new StringBuffer(); String incommingLine;
				while ((incommingLine = response_incommingStream.readLine()) != null) { 
					response.append(incommingLine); 
				}
				response_incommingStream.close();
			
			// Set this incomming stream data to Global vars.
				this.RES_DATA = response.toString();
				
			// Log the sent request
				String stdout = "+--------------------\n"+
								"| Request Sent:	  \n" + 
								"| URL		: %-30s    \n" +
								"| METHOD	: %-10s     \n" +
								"| DATA		: %s      \n" +
								"+--------------------\n"+
								"| RESPONSE RECEIVED: \n"+
								"| STATUS CODE : %-10s\n"+
								"| MESSAGE     : %-50s  \n"+
								"+--------------------\n\n";
				System.out.printf(stdout, this.REQ_SERVER_URL, this.REQ_METHOD, data, this.RES_STATUS_CODE, this.RES_MESSAGE);
			
			// Last
				return this.RES_DATA;
		}
		
		public void postTestData(File file, String testCaseName, String stepName, String stepStatus, String stepTime) throws Exception {
			String data = this.setData(testCaseName, stepName, stepStatus, stepTime);
			// Connections
				String url = this.REQ_SERVER_URL;
				URL obj = new URL(url);
				HttpURLConnection con = (HttpURLConnection)obj.openConnection();
	
			//add reuqest header
				con.setRequestMethod(this.REQ_METHOD);
				con.setRequestProperty("User-Agent", this.REQ_USER_AGENT);
				con.setRequestProperty("Accept-Language", this.REQ_LANGUAGE);
				con.setRequestProperty("Content-Type", this.REQ_CONTENT_TYPE);
			
			//send data to server now.
				con.setDoOutput(true);	
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				// Experiments
				// byte[][] b = new byte[3][2];
				// Base64.encode(FileUtils.readFileToByteArray(file));
				// wr.write(b, 10, 1893423);
				wr.writeBytes(data);
				wr.flush();
				wr.close();
				
			//take server response.
				int responseCode = con.getResponseCode();
				
				//get the server response.
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) { response.append(inputLine); }
				in.close();
	
			//Log response result
				this.log("Sending "+this.REQ_METHOD+" request to URL : " + this.REQ_SERVER_URL+ "\nServer Response: "+response.toString(), responseCode);
			
			return;
		}
/*LLOC*/
}/*EOClass*/

