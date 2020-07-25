package com.qa.hs.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hs.keyword.base.Base;

public class KeyWordEngine {
	public WebDriver driver;
	public Properties prop;
	
	public static Workbook book;
	public static Sheet sheet;
	
	public Base base;
	
	
	public final String SCENARIO_SHEET_PATH = "C:\\Users\\pratik.gupta1\\Desktop\\Project Work\\IDS\\IDSAutomationTesting\\KeywordDrivenHubSpot\\src\\main\\java\\com\\qa\\hs\\keyword\\scenarios\\hubspot_scenario.xlsx";
	
	public void startExecution(String sheetName) throws FileNotFoundException {
		
//		String locatorName = null;
//		String locatorValue = null;
		FileInputStream file = null;
		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		
		int k = 0;
		
		for (int i = 0; i<sheet.getLastRowNum(); i++) {
			try {
				
			
				String locatorType = sheet.getRow(i+1).getCell(k+1).toString().trim();
				String locatorValue = sheet.getRow(i+1).getCell(k+2).toString().trim();			
				String action = sheet.getRow(i+1).getCell(k+3).toString().trim();
				String value = sheet.getRow(i+1).getCell(k+4).toString().trim();
				
				switch (action) {
				case "open browser":
					base = new Base();
					prop = base.init_properties();
					
					if (value.isEmpty() || value.equals("NA")) {
						driver = base.init_driver(prop.getProperty("browser"));
					} else {
						driver = base.init_driver(value);
					}
					break;

				case "enter url":
					if (value.isEmpty() || value.equals("NA")) {
						driver.get(prop.getProperty("url"));
					} else {
						driver.get(value);
					}
					break;
					
				case "quit":
					driver.quit();
					break;
					
				default:
					break;
				}
				
				switch (locatorType) {
				case "id":
					WebElement element = driver.findElement(By.id(locatorValue));
						if (action.equalsIgnoreCase("sendkeys")) {
							element.sendKeys(value);							
						} else if (action.equalsIgnoreCase("click")) {
							element.click();
						}
						locatorType = null;
					break;

				
				default:
					break;
				}
				
				
		} catch(Exception e) {
			
		}
		}
	}
}