package com.qa.hs.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Base {

	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(String browsername) {
		if(browsername.equals("chrome")) {
			//Set Chrome Driver path and set capabilities
				DesiredCapabilities cap = DesiredCapabilities.chrome();	
			
			//Set ACCEPT_SSL_CERTS variable to true
			    cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			    ChromeOptions o = new ChromeOptions();
			    System.setProperty("webdriver.chrome.driver","./WebDriver/chromedriver.exe");
			    o.setExperimentalOption("useAutomationExtension", false);
						    
			    if(prop.getProperty("headless").equals("yes")) {
			    	ChromeOptions options = new ChromeOptions();
			    	options.addArguments("--headless");
			    	driver = new ChromeDriver(options);
			    } else {
			    	driver = new ChromeDriver(o); 
				    
			    }
			    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		return driver;
	}
	
	public Properties init_properties() throws FileNotFoundException {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:\\Users\\pratik.gupta1\\Desktop\\Project Work\\IDS\\IDSAutomationTesting\\KeywordDrivenHubSpot\\src\\main\\java\\com\\qa\\hs\\keyword\\config\\config.properties");			
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}
