/**
 * @author Ashutosh Mishra
 * @desc Set a main class for org.nng-*.jar
 * edited
 */

package Examples;
import javax.swing.JOptionPane;
import org.nng.utils.DateAndTime;

public class Help {

	/*
	 * ===================
	 * Main
	 * ===================
	 * 
	 */
	public static void main(String[] args) throws Exception {
		// Class Instance
		Help helpObj = new Help();
		
		// Execute when org.nng-*.jar directly executed by double click
		if(args.length == 0) {
			System.out.println( "Current Date : " + (new DateAndTime()).getCurrentDateAsD_M_Y());
			helpObj.help();
		}
	}

	/*
	 * ===================
	 * HELP function
	 * ===================
	 * 
	 */
	public void help() throws Exception {
		//
			String msg = "######## 	\n" +
						 "#  Help  #    \n" +
						 "##############################################\n" +
						 "# This JAR (Java Archive) is not meant to be  \n" +
						 "# an executable program. To use its API, you  \n" +
						 "# need to import this to your java program    \n" +
						 "##############################################\n" +
						 "# Available Packages are as follows           \n" +
						 "# 1- org.nng.utils.*                          \n" +
						 "# 2- org.nng.automation.utils.*               \n" +
						 "##############################################\n" +
						 "# @Author: Ashutosh Mishra [@github: nityanarayan44] \n" +
						 "# @License: MIT License 2017 \n" +
						 "# @Written: 10 Aug 2017     \n" +
						 "# @Modified: 23 Nov 2017   \n" +
						 "##############################################\n";
							
			javax.swing.JOptionPane.showMessageDialog(null, msg, "HELP : Jar Usage", JOptionPane.INFORMATION_MESSAGE);
		// Last
			return;
	}
}/*EOClass*/
