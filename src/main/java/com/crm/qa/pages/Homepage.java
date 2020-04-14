package com.crm.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.Testbase;

public class Homepage extends Testbase{
	
	
	@FindBy(xpath="//td[contains(text( ),'User: Demo User')]") //xpath for username on top
	WebElement userNameLabel;
	///html/body/table[1]/tbody/tr[1]/td/table/tbody/tr/td[1] //absolute x-path
	////td[contains(text( ),'User: Demo User')] //realtive x-path
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")//for contacts
	WebElement contactsLink;
	
	@CacheLookup
	@FindBy(xpath="//a[contains(text(),'New Contact')]")//for new contacts
	WebElement NewcontactLink;
	
	//@FindBy(xpath="//*[@id=\"navmenu\"]/ul/li[4]/ul/li[1]/a")//for new contacts
	//WebElement NewcontactLink;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	
	WebElement DealsLink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement TasksLink;
	
	@FindBy(xpath="//a[contains(text(),'Cases')]")
	WebElement CasesLink;
	
//initializing homepage objects

public Homepage(){    // //Homepage page constructor

		PageFactory.initElements(driver,this); //use this also PageFactory.initElements(driver,Homenpage.class)		
}
public String ValidateHomePageTitle() {
	
		return driver.getTitle();
	}

public boolean veriFyCorrectUserName() {
	return userNameLabel.isDisplayed();
}
public Contactspage clickOnContactsLink() {
contactsLink.click();
return new Contactspage(); // landing page object after homepage 
//contactsLink.click();
//return new Contactspage(); // landing page object after homepage 
}
public void  clickOnNewContactLink() throws InterruptedException {
	driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	//here for datadriven testing  iam creating mouse hover using actions to pass data to contacts page
	Thread.sleep(3000);
	Actions act =new Actions(driver);
	act.moveToElement(contactsLink).build().perform();
	Thread.sleep(3000);
	NewcontactLink.click();

}
	

public Dealspage clickOnDealsLink() {
	DealsLink.click();
	return new Dealspage();
}
public Taskspage clickOnTaskspageLink() {
	TasksLink.click();
	return new Taskspage();
}
public Casespage clickOnCasespageLink() {
	CasesLink.click();
		return new Casespage();	
}
}