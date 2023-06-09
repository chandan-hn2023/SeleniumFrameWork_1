package PageObjects;               //PAGE OBJECTS SHOULDN'T HOLD ANY DATA, IT SHOULD FOCUS ONLY ON ELEMENTS AND ACTIONS
                                   //driver.findelement - U can use Page factory, By. - We can't use
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.ReusableComponents;

public class orderPage extends ReusableComponents {

	WebDriver driver; //Local driver
	
	//Constructor
	public orderPage(WebDriver driver) //This driver is from StandAloneTest class
	{
		//Below step is mandatory when u r sending variable to Parent class
		super(driver); //Sending the variable to Parent class(ReusableComponents) using a keyword "super"
		this.driver=driver; //Assigning StandAloneTest class's driver into this Local driver || this .driver points to the local driver	
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory - driver.findelement - U can use Page factory, By. - We can't use
	
		//CODE: List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
	    @FindBy(css = "tr td:nth-child(3)")
		List<WebElement> oderedProducts;
	    
	    @FindBy(css = ".totalRow button")
	    WebElement checkOut;
	    
	    public Boolean verifyOrderDisplay(String productname)
	    {
	    	Boolean match = oderedProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productname)); //product name = ZARA COAT 3
	    	return match;
	    }
		
	    
	 
	    
	    
		

	}


