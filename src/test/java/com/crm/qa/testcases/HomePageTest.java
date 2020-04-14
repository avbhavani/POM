package com.crm.qa.testcases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.Testbase;
import com.crm.qa.pages.Contactspage;
import com.crm.qa.pages.Dealspage;
import com.crm.qa.pages.Homepage;
import com.crm.qa.pages.Loginpage;
import com.crm.qa.util.TestutillForwaits;
//ctrl.shift.o
public class HomePageTest extends Testbase {
	Loginpage loginpage; // object of login page like for all pages below
	Homepage homepage;
	 Contactspage contactpage;
	 Dealspage dealspage;
	TestutillForwaits testutill;
	// home page object
	public HomePageTest () {
		super();
		
	}
	@BeforeMethod
	public void SetUp() {
		initialization();
		
		//Loginpage loginpage = new Loginpage(); // creating obj for login page
		//Or
		loginpage = new Loginpage();
		homepage=loginpage.login(prop.getProperty("username"),prop.getProperty("password")); //using prop properties from testbase class
		//using super key and inherited
		testutill = new TestutillForwaits();
		contactpage = new Contactspage();
		dealspage = new  Dealspage();
		
		
	}	
////test cases should be separated -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser
////Test cases should be seperated and independent that is why we were launching browser
	//each time else cache will stacked and slowdown the browser and more load on browser.	
@Test(priority =1)
public void VerifyHomePageTitle() {
	String HomepageTitle =homepage.ValidateHomePageTitle();
	Assert.assertEquals(HomepageTitle, "CRMPRO","homepage title not matching");//here we can insert message in assertion
	//to show this message when testcase is failed saying "home page title failed"
}
@Test(priority =2)
public void verifyUserNameTest() {
	testutill.SwitchToFrame();
	Assert.assertTrue(homepage.veriFyCorrectUserName());
}

@Test(priority =3)
public void contactsPageTest() {
	testutill.SwitchToFrame(); // hence it is on diff frame created method in properties file
 contactpage =	homepage.clickOnContactsLink();
}
@Test(priority =4)
public void clickOnDealsLink() {
	testutill.SwitchToFrame();
	dealspage= homepage.clickOnDealsLink();
}

@AfterMethod  // to close driver
	public void tearDown() {
		driver.quit();
	}
	

}
