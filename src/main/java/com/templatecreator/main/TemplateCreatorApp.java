package com.templatecreator.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.templatecreator.io.JsonReader;
import com.templatecreator.objects.Company;
import com.templatecreator.objects.Guest;
import com.templatecreator.objects.Template;
import com.templatecreator.templatecreator.TemplateCreator;
import com.templatecreator.util.TemplateUtils;

public class TemplateCreatorApp {

	private static TemplateCreator creator = new TemplateCreator();
	private static boolean createOutput = true;
	private static final String SEPARATOR_STRING = "\n===========================================================================================================\n";

	/*
	 * Main method which calls other private methods to get Guest, Company, and
	 * Template String to use in creating final output message
	 */
	public static void main(String[] args) {
		JsonReader jsonReader = new JsonReader();
		Scanner scanner = new Scanner(System.in);

		while (createOutput) {
			System.out.println("Welcome to the TemplateCreator App!" + SEPARATOR_STRING);
			/*Step One - Get Guest Information*/
			Guest chosenGuest = getGuestInformation(scanner, jsonReader.getGuestsFromFile());
			/*Step Two - Get Company Information*/
			Company chosenCompany = getCompanyInformation(scanner, jsonReader.getCompaniesFromFile());
			/*Step Three - Get Template / Create New Template*/
			String chosenTemplateString = getTemplateInformation(scanner, jsonReader.getTemplatesFromFile());
			/*Step Four - Combine Guest, Company, and chosenTemplateString to create final message.*/
			System.out.println("\nOutput message: ");
			System.out.println(creator.createOutputTemplate(chosenGuest, chosenCompany, chosenTemplateString));
			/*Step Five - Create another message? If yes, continue. If no, exit program.*/			
			while(createOutput) {
				System.out.println("\nCreate another message? Write 'Yes' to continue or 'No' to exit." + SEPARATOR_STRING);
				String userInput = scanner.next().toUpperCase().trim();
				scanner.nextLine();
				
				if(userInput.equals("YES")) {
					break;
				} else if(userInput.equals("NO")) {
					createOutput = false;
				} else {
					System.out.println("Please enter 'Yes' to continue or type anything else to exit.");
				}
			}
		}
	}

	/*Method used to get chosen guest using guestList from JSON file*/
	private static Guest getGuestInformation(Scanner scanner, List<Guest> guestList) {
		boolean validChoice = false;
		Map<String, Guest> guestMap = new HashMap<>();

		for (Guest guest : guestList) {
			guestMap.put(guest.getId(), guest);
		}

		Guest selectedGuest = new Guest();

		while (!validChoice) {
			System.out.println("Choose a guest from the guest list by id: ");
			System.out.println(guestMap + SEPARATOR_STRING);

			String userInput = String.valueOf(scanner.nextLine());

			selectedGuest = guestMap.get(userInput);

			if (selectedGuest != null) {
				validChoice = true;
			} else {
				System.out.println(userInput + " is am invalid guest id, please try again by chosing an id from the guest list.\n");
			}
		}

		System.out.println("Chosen guest: " + selectedGuest.getFirstName() + " " + selectedGuest.getLastName());
		return selectedGuest;
	}

	/*Method used to get chosen company using companyList from JSON file*/
	private static Company getCompanyInformation(Scanner scanner, List<Company> companyList) {
		boolean validChoice = false;
		Map<String, Company> companyMap = new HashMap<>();

		for (Company company : companyList) {
			companyMap.put(company.getId(), company);
		}

		Company selectedCompany = new Company();

		while (!validChoice) {
			System.out.println("\nChoose a company from the guest list by id: ");
			System.out.println(companyMap + SEPARATOR_STRING);

			String userInput = String.valueOf(scanner.nextLine());

			selectedCompany = companyMap.get(userInput);

			if (selectedCompany != null) {
				validChoice = true;
			} else {
				System.out.println(userInput + "is an invalid company id, please try again by chosing an id from the company list.\n");
			}
		}

		System.out.println("Chosen company: " + selectedCompany.getCompany());
		return selectedCompany;
	}

	/*
	 * Method takes in Scanner and templateList to return a string that contains
	 * a greeting and either a premade template or custom template created from
	 * the user.
	 */
	private static String getTemplateInformation(Scanner scanner, List<Template> templateList) {
		boolean validChoice = false;
		String greeting = TemplateUtils.getGreeting();
		String outputString = "";

		while (!validChoice) {
			System.out.println("\nChoose an option:");
			System.out.println("1: Premade template");
			System.out.println("2: New template" + SEPARATOR_STRING);
			
			String userInput = String.valueOf(scanner.nextLine());
			switch (userInput.toUpperCase().trim()) {
				case "1":
				case "1:":
				case "PREMADE":
				case "PREMADE TEMPLATE":
				case "1: PREMADE TEMPLATE":
					outputString = getPremadeTemplateString(scanner, templateList);
					validChoice = true;
					break;
				case "2":
				case "2:":
				case "NEW":
				case "NEW TEMPLATE":
				case "2: NEW TEMPLATE":
					outputString = createNewTemplate(scanner);
					validChoice = true;
					break;
				default:
					System.out.println(userInput + " is an invalid option. Please try again.");
			}
		}
		
		return greeting + outputString;
	}

	/*
	 * Method called from getTemplateInformation method to get a premade
	 * template string from the templateList.
	 */
	private static String getPremadeTemplateString(Scanner scanner, List<Template> templateList) {
		Map<String, Template> templateMap = new HashMap<>();
		Template selectedTemplate = new Template();

		for (Template template : templateList) {
			templateMap.put(template.getId(), template);
		}

		System.out.println("\nChoose a premade template by Template ID:");
		System.out.println(templateMap + SEPARATOR_STRING);

		String userInput = String.valueOf(scanner.nextLine());
		selectedTemplate = templateMap.get(userInput);

		if (selectedTemplate != null) {
			System.out.println("\nTemplate title: " + selectedTemplate.getTitle() + " chosen.");
			System.out.println("Message: " + selectedTemplate.getMessage());
			System.out.println("If this template is okay write 'Yes', otherwise type anything else to try again.");

			userInput = String.valueOf(scanner.nextLine());
			if (userInput.toUpperCase().trim().equals("YES")) {
				return selectedTemplate.getMessage();
			}
		} else {
			System.out.println(userInput + " is an invalid option. Please try again.");
		}
		return getPremadeTemplateString(scanner, templateList);
	}
	
	/*
	 * Method called from getTemplateInformation method to get a customer
	 * template string created by user.
	 */
	private static String createNewTemplate(Scanner scanner) {
		System.out.println("\nType out a new template. Hit enter when you are done.");
		System.out.println(SEPARATOR_STRING);
		System.out.println("To use Guest information write {FIRSTNAME}, {LASTNAME}, or {ROOMNUMBER}");
		System.out.println("To use Company information write {COMPANY}, {CITY}, or {TIMEZONE}");
		
		String template = String.valueOf(scanner.nextLine());
		
		System.out.println("\nYour template will be: ");
		System.out.println(template);
		System.out.println("If this template is okay write 'Yes', otherwise type anything to try again.");
		
		String userInput = String.valueOf(scanner.nextLine());
		
		if (userInput.toUpperCase().trim().equals("YES")) {
			return template;
		} else {
			return createNewTemplate(scanner);
		}
	}
}