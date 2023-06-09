package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.ReusableComponents;

public class checkoutPage extends ReusableComponents {

	WebDriver driver;
	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver; //Assigning StandAloneTest class's driver into this Local driver || this .driver points to the local driver	
		PageFactory.initElements(driver, this);
	}
	//Clicking on Select country
	//CODE: a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"India").build().perform();
	
	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;
	
	//Selecting the country
	//CODE: driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	
	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement myCountry;
	
	//Clicking on Place order button
	//CODE: driver.findElement(By.cssSelector(".action__submit")).click();
	
	@FindBy(css = ".action__submit")
	WebElement placeOrder;
	
	By waitForCountries = (By.cssSelector(".ta-results"));
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
        a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(waitForCountries);
		
		//Selecting the country
        myCountry.click();
	}
	
	//Calling confirmationPage
	public confirmationPage placeOrder()
	{
		placeOrder.click();
		
		//Creating object and returning ConfirmationPage
		return new confirmationPage(driver);
	}
	
	}
	
	


