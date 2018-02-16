/**
 * @author Ashutosh Mishra
 * @desc Extract and build the date and time accordingly.
 */
package org.nng.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAndTime {

	/*
	 * --------------------------------
	 *  Get the date and time
	 * --------------------------------
	 */
		//Date pattern
		public static String defaultPattern = "dd_MM_yyyy";
	
		//Get Current data
		public static String getCurrentDateAsD_M_Y() {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(defaultPattern);
			LocalDate localDate = LocalDate.now();
			String current_Date = dtf.format(localDate);
			return current_Date;
		}
		/**
		 * @desc User defined pattern Like: [dd-MM-yyyy, dd/MM/yyyy, dd MM yyyy]
		 * @param pattern
		 * @return current date with the specified date pattern
		 */
		public static String getCurrentDateAsD_M_Y(String pattern) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
			LocalDate localDate = LocalDate.now();
			String current_Date = dtf.format(localDate);
			return current_Date;
		}
		
		// Get Past date.
		public static String getPreviousDateByDays(long forDays) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(this.defaultPattern);
			LocalDate localDate = LocalDate.now();
			String previous_Date = dtf.format(localDate.minusDays(forDays));
			return previous_Date;
		}
		
		public static String getPreviousDateByMonths(long forMonths) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(this.defaultPattern);
			LocalDate localDate = LocalDate.now();
			String previous_Date = dtf.format(localDate.minusMonths(forMonths));
			return previous_Date;
		}
		
		public static String getPreviousDateByWeeks(long forWeeks) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(this.defaultPattern);
			LocalDate localDate = LocalDate.now();
			String previous_Date = dtf.format(localDate.minusWeeks(forWeeks));
			return previous_Date;
		}
		
		public static String getPreviousDateByYears(long forYears) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(this.defaultPattern);
			LocalDate localDate = LocalDate.now();
			String previous_Date = dtf.format(localDate.minusYears(forYears));
			return previous_Date;
		}
		
		// Get future date.
		public static String getFutureDateByDays(long forDays) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(this.defaultPattern);
			LocalDate localDate = LocalDate.now();
			String future_Date = dtf.format(localDate.plusDays(forDays));
			return future_Date;
		}
		
		public static String getFutureDateByMonths(long forMonths) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(this.defaultPattern);
			LocalDate localDate = LocalDate.now();
			String future_Date = dtf.format(localDate.plusMonths(forMonths));
			return future_Date;
		}
		
		public static String getFutureDateByWeeks(long forWeeks) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(this.defaultPattern);
			LocalDate localDate = LocalDate.now();
			String future_Date = dtf.format(localDate.plusWeeks(forWeeks));
			return future_Date;
		}
		
		public static String getFutureDateByYears(long forYears) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(this.defaultPattern);
			LocalDate localDate = LocalDate.now();
			String future_Date = dtf.format(localDate.plusYears(forYears));
			return future_Date;
		}
	/**
	  * Months and Days
	  * Functions to get the result in number format
	  */
		public static int getMonthNumber(String monthName) throws Exception {
			String months = "JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DEC";
			int monthNum = 0;
			// Calculate
			if(monthName == null || monthName.equals("") || monthName.length() < 3) { monthNum = -1;}
			else if ( (months.contains( (monthName.toUpperCase()).substring(0, 3) )) ) { monthNum = ((months.indexOf((monthName.toUpperCase()).substring(0, 3)))/4) + 1; }
			else { monthNum = -1; }
			// Return the value
			return monthNum;
		}

		public static int getDayNumber(String dayName) throws Exception {
			String days = "SUN MON TUE WED THU FRI SAT";
			int dayNum = 0;
			// Calculate
			if(dayName == null || dayName.equals("") || dayName.length() < 3) { dayNum = -1;}
			else if ( (days.contains( (dayName.toUpperCase()).substring(0, 3) )) ) { dayNum = ((days.indexOf((dayName.toUpperCase()).substring(0, 3)))/4) + 1; }
			else { dayNum = -1; }
			// return
			return dayNum;
		}
		
}
