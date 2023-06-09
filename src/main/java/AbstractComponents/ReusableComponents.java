package AbstractComponents;           //THIS CLASS WILL BE THE PARENT CLASS OF ALL PAGE OBJECT CLASSESS

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CartPage;
import PageObjects.orderPage;

public class ReusableComponents {
	
	WebDriver driver; //Local driver
	
	
	
	public ReusableComponents(WebDriver driver)  //We have to create a consructor to catch the variable(driver) from Landing page class
	{
		this.driver=driver; //Assigning StandAloneTest class's driver into this Local driver || this .driver points to the local driver
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement clickCart;
	
	By loadingIcon = By.cssSelector("ng-animating");
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orders;

	//For "By Locators"
	public void waitForElementToAppear(By findBy)
	{
	    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy)); //(By.cssSelector("div.mb-3")) - Is a ByLocator
	    
	}
	
	//For "Web Elements"
	
	public void waitForWebElementToAppear(WebElement findBy) throws InterruptedException
	{
	    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.visibilityOf(findBy)); 
	    
	}
	
	public void waitForElementToDisAppear(By findBy) throws InterruptedException
	{
		Thread.sleep(2000);
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	//CODE: driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
	        //Calling CartPage
			public CartPage goToCartPage() throws InterruptedException
			{
				waitForElementToDisAppear(loadingIcon);
				clickCart.click();
				
				//Creating object for CartPage
				CartPage cartPage = new CartPage(driver);
				return cartPage;
			}
			
			public orderPage goToOrdersPage()
			{
				orders.click();
				orderPage orderPage = new orderPage(driver);
				return orderPage;
			}
}
