package com.templatecreator.templatecreator;

import com.templatecreator.objects.Company;
import com.templatecreator.objects.Guest;

/*Class used to format output string and replace variables from the outputString to include Guest and Company information.*/
public class TemplateCreator {

	public String createOutputTemplate(Guest guest, Company company, String outputString) {
		String templateString = outputString.replace("{FIRSTNAME}", guest.getFirstName());
		templateString = templateString.replace("{LASTNAME}", guest.getLastName());
		templateString = templateString.replace("{ROOM}", guest.getReservation().getRoomNumber());
		templateString = templateString.replace("{COMPANY}", company.getCompany());
		templateString = templateString.replace("{CITY}", company.getCompany());
		templateString = templateString.replace("{TIMEZONE}", company.getTimezone());
		
		return templateString;
	}
}
