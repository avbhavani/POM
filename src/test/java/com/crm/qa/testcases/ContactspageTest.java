package com.crm.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.Testbase;
import com.crm.qa.pages.Contactspage;
import com.crm.qa.pages.Dealspage;
import com.crm.qa.pages.Homepage;
import com.crm.qa.pages.Loginpage;
import com.crm.qa.util.TestutillForwaits;

public class ContactspageTest extends Testbase {
	Loginpage loginpage;
	Homepage homepage;
	 Contactspage contactpage;
	 Dealspage dealspage;
	 TestutillForwaits testutill;
	 
	 String sheetName = "Contactas page"; // creating String for Contacts sheet of xls in utilclass
	 
public ContactspageTest() {
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
	testutill.SwitchToFrame();// bcz it is on different frame
	contactpage= homepage.clickOnContactsLink();// referencing contacts page bcz we were clicking in home page they are interlinked
	
	
}

@Test(priority=1)
public void VerifyContactsLabelPage() {
	Assert.assertTrue(contactpage.VerifyContactsLabel(),"contacts label is missing on the page");
}

@Test(priority=2)
public void SelectContactspage() {
	contactpage.SelectContactsByName("Aakash Shah");
}

@Test(priority=2)
public void selectMultipleContactsTest(){
	contactpage.SelectContactsByName("Aakash Shah");
	contactpage.SelectContactsByName("akash reddy");
	
}
@DataProvider
public Object [][] getCRMtestDta() throws InvalidFormatException{
	Object input[][]= TestutillForwaits.getTestData(sheetName);
	
	return input;
	
}

//Data driven approach 
@Test(priority=4,dataProvider="getCRMtestDta") 
//if we want to run from method select the method right click run insted running all the tests in class
public void ValidateCreateNewContact(String title ,String Fname  ,String Lname  ,String Comp) throws InterruptedException {
	Thread.sleep(3000);
	homepage.clickOnNewContactLink();// homepage obj calling newcontact method
//contactpage.CreateNewcontact("Mr.","Tom", "Ake","google"); //it is hard coded
	//driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	contactpage.CreateNewcontact(title, Fname, Lname, Comp);
}



@AfterMethod  // to close driver
public void tearDown() {
	driver.quit();
	}

}


