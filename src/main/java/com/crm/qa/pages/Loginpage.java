package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;

import com.crm.qa.base.Testbase;

public class Loginpage extends Testbase {
	
	//page factory -OR(object repository):(below ones are page objects)
	@FindBy(name="username") // annotation of pagefactory
	WebElement username;
	
	@FindBy (name="password")
	WebElement password;
	
	@FindBy (xpath ="//input[@type='submit']")
	WebElement loginbtn;
	////*[@id="loginForm"]/div/div/input
	
	@FindBy (xpath ="//*[@id=\"navbar-collapse\"]/ul/li[2]/a")
	WebElement signUpbtn;
	
	@FindBy(xpath = "//img[contains(@class,'img-responsive')]")
	WebElement crmlogo;


//Q: how will you initialize your page factory methods ?? interview
// A: using pagefactory.initElements method in pagefactory
	
//initializing Loginpage objects
public Loginpage() //login page constructor
{
	PageFactory.initElements(driver,this);// or use this also PageFactory.initElements(driver,Loginpage.class);
}

//Actions:diffrent features on login page

public String ValidateLoginPageTitle()
{
	return driver.getTitle();
}

public boolean ValidateCRMImage() {
	return crmlogo.isDisplayed() ;
	
}

public Homepage login(String un,String pwd) {
	username.sendKeys(un);
	password.sendKeys(pwd);
	loginbtn.click();
	
	/*//JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", loginbtn);*/
	
	return new  Homepage(); //  it will return homepage obj bcz after login landing page is home page
}

@AfterMethod  // to close driver
public void tearDown() {
	driver.quit();
}





}