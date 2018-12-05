package com.templatecreator.io;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.templatecreator.objects.Company;
import com.templatecreator.objects.Guest;
import com.templatecreator.objects.Template;

/*Class utilizing GSON dependency - used to read from JSON files and convert JSON to POJOs for Guest, Company, and Template objects.*/
public class JsonReader {

	private static final String GUEST_FILE_PATH = "src/main/resources/com/templatecreator/input/Guests.json";
	private static final String COMPANY_FILE_PATH = "src/main/resources/com/templatecreator/input/Companies.json";
	private static final String TEMPLATE_FILE_PATH = "src/main/resources/com/templatecreator/input/Templates.json";
	

	private Gson gson = new Gson();

	public List<Guest> getGuestsFromFile() {
		List<Guest> guestList = new ArrayList<>();
		
		try (Reader reader = new FileReader(GUEST_FILE_PATH)) {
			guestList = gson.fromJson(reader, new TypeToken<List<Guest>>(){}.getType());			
		} catch (IOException e) {
			System.out.println("Exception occurred reading from Guest.json file: " + e);
		}

		return guestList;
	}

	public List<Company> getCompaniesFromFile() {
		List<Company> companyList = new ArrayList<>();
		
		try (Reader reader = new FileReader(COMPANY_FILE_PATH)) {
			companyList = gson.fromJson(reader, new TypeToken<List<Company>>(){}.getType());			
		} catch (IOException e) {
			System.out.println("Exception occurred reading from Company.json file: " + e);
		}
		return companyList;
	}

	public List<Template> getTemplatesFromFile() {
		List<Template> templateList = new ArrayList<>();
		
		try (Reader reader = new FileReader(TEMPLATE_FILE_PATH)) {
			templateList = gson.fromJson(reader, new TypeToken<List<Template>>(){}.getType());			
		} catch (IOException e) {
			System.out.println("Exception occurred reading from Template.json file: " + e);
		}
		
		return templateList;
	}

}
