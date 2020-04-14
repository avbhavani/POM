package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestutillForwaits;
import com.crm.qa.util.WebEventListener;

public class Testbase {                //parent class

	public static WebDriver driver; // global variable
	public static Properties prop; // global variable
	//public  static EventFiringWebDriver e_driver; //global variable for EventFiringWebDriver
	//public static WebEventListener eventListener; // global variable for WebEventListener
public Testbase()  // constructor name 
	{
	
	prop = new Properties();
	
	try {
		FileInputStream ip = new FileInputStream("C:\\Users\\avsub\\eclipse-workspace\\FreeCRMtest\\src"
				+ "\\main\\java\\com\\crm\\qa\\config\\crmconfig.properties");
	 
		prop.load(ip);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
		
	catch (IOException e) {
		
		e.printStackTrace();
	}
}

public void initialization()
{
	String browsername = prop.getProperty("browser");
	System.out.println("initialize browser");
	
	if (browsername.equals("chrome"))
	{
	System.setProperty("webdriver.chrome.driver","C:\\Users\\avsub\\Downloads\\chromedriver_win32\\chromedriver.exe");
	driver = new ChromeDriver();
}
	else if(browsername.equals("FF"))
	{
		System.setProperty("webdriver.gecko.driver","C:\\Users\\avsub\\Downloads\\geckodriver-v0.26.0-win32\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	//
	/*e_driver = new EventFiringWebDriver(driver);
	// Now create object of EventListerHandler to register it with EventFiringWebDriver
	eventListener = new WebEventListener();
	e_driver.register(eventListener); //registering e_driver with eventListener
	driver = e_driver; //assining e_driver to driver
*/	
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); // hardcoding value here 
	// instead we can call it from another class of global varible in class TestutillForwaits.java 
	//OR
	driver.manage().timeouts().pageLoadTimeout(TestutillForwaits.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS); //without hardcoding
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//OR
	driver.manage().timeouts().pageLoadTimeout(TestutillForwaits.IMPLICIT_WAIT, TimeUnit.SECONDS); //if we want to change waittime u 
	//can go to utill class and change
	driver.get(prop.getProperty("url"));
}
}









