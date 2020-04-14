package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.Testbase;
import com.crm.qa.pages.Homepage;
import com.crm.qa.pages.Loginpage;



public class loginPageTest extends Testbase {
	Loginpage loginpage; // login page obj
	Homepage homepage;
	public loginPageTest() {  // child class constructor 
	  super();  // keyword to call super constructor (parent) properties from properties file	
	}
@BeforeMethod
public void SetUp() {
	initialization();
	
	//Loginpage loginpage = new Loginpage(); // creating obj for login page
	//Or
	loginpage = new Loginpage();
}
@Test(priority=1)
public void loginPageTitleTest() {
	String title =loginpage.ValidateLoginPageTitle();
//right click on any page u will get page source option to see title
	Assert.assertEquals(title,"CRMPRO - CRM software for customer relationship management, sales, and support."); //should import Assert from TestNG	
}
@Test(priority=2)
public void crmlogoPageTest() {       
	boolean flag =loginpage.ValidateCRMImage();
	Assert.assertTrue(flag);
}

@Test (priority=3) //passing parameters getting from properties file and it is inheriting from Testbase class
public void loginTest() {
	
	homepage =loginpage.login(prop.getProperty("username"), prop.getProperty("password")); // we created obj for Homepage class obj
   	
}



}

