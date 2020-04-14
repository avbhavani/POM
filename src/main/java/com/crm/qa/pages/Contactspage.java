package com.crm.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.Testbase;

public class Contactspage extends Testbase{
	
	@FindBy(xpath="/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/fieldset/legend")//for contacts
	WebElement contactsLbel;
	
	@FindBy(id="first_name")// newcontact page 
	WebElement firstname;
	
	@FindBy(id="surname") // newcontact page 
	WebElement surname;
	
	@FindBy(name="client_lookup") // newcontact page 
	WebElement Company;
	
	@FindBy(xpath="//input[@type='submit' and @value='Save']")//newcontact page for svae
	WebElement Savebtn;
	
	
	//initializing the page objects
	
	public Contactspage() {
		
		PageFactory.initElements(driver, this);
		
	}
	
public boolean VerifyContactsLabel() {
	return contactsLbel.isDisplayed();
}

public void SelectContactsByName(String name) {
//Dynamic x path should learn
	/*driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
			+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();*/ 
//this I used absolute xpath for Name 
	//driver.findElement(By.xpath("//*[@id=\"vContactsForm\"]/table/tbody/tr[3]/td[2]/strong")).click();
	

driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
	+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();;
}

//new contacts page method
public void CreateNewcontact(String title, String Fname,String Lname,String Comp) throws InterruptedException {
//implicit wait
//driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

/*WebDriverWait wait = new WebDriverWait(driver, 20); //explicit wait
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='title']")));*/

WebElement e = 	driver.findElement(By.xpath("//select[@name='title']")); //creating obje for dropdown title
Select sele = new Select(e);
//or
//Select sele = new Select(driver.findElement(By.name("title"))); //or we can use below xpath as well.
//or this way
//Select sele = new Select(driver.findElement(By.xpath("//select[@name='title']")));
	
	

sele.selectByVisibleText(title);
firstname.sendKeys(Fname);
surname.sendKeys(Lname);
Company.sendKeys(Comp);
Savebtn.click();


	
}

}
