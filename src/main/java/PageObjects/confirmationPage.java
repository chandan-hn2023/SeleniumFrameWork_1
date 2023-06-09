package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.ReusableComponents;

public class confirmationPage extends ReusableComponents {

	WebDriver driver;
	public confirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver; //Assigning StandAloneTest class's driver into this Local driver || this .driver points to the local driver	
		PageFactory.initElements(driver, this);
	}

	//CODE: String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage()
	{
		return confirmationMessage.getText();
	}
	
	
	

}
