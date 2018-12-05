package com.templatecreator.util;

import java.util.Calendar;

//Class to hold public static utility methods used in TemplateCreatorApp
public class TemplateUtils {
	
	public static String getGreeting() {
		Calendar c = Calendar.getInstance();
		int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
		String greeting = "";

		if (timeOfDay >= 0 && timeOfDay < 12) {
			greeting = "Good morning, ";
		} else if (timeOfDay >= 12 && timeOfDay < 16) {
			greeting = "Good afternoon, ";
		} else if (timeOfDay >= 16 && timeOfDay < 21) {
			greeting = "Good evening, ";
		} else if (timeOfDay >= 21 && timeOfDay < 24) {
			greeting = "Good night,";
		}

		return greeting;
	}
}
