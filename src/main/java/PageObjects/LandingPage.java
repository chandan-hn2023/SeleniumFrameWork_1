package PageObjects;               //PAGE OBJECTS SHOULDN'T HOLD ANY DATA, IT SHOULD FOCUS ONLY ON ELEMENTS AND ACTIONS

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.ReusableComponents;

public class LandingPage extends ReusableComponents {

	WebDriver driver; //Local driver
	
	//Constructor
	public LandingPage(WebDriver driver) //This driver is from SubmitOrderTest class
	{
		//Below step is mandatory when u r sending variable to Parent class
		super(driver); //Sending the variable to Parent class(ReusableComponents) using a keyword "super"
		this.driver=driver; //Assigning StandAloneTest class's driver into this Local driver || this .driver points to the local driver	
		PageFactory.initElements(driver, this);
	}
	
	public void goToUrl()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	    //CODE: WebElement loginEmail = driver.findElement(By.id("userEmail"));
		//Use Page factory concept to reduce code complexity(For above line)
		
	    @FindBy(id = "userEmail") //INTERVIEW QUESTION - This step don't know who the DRIVER is, so we use PageFactory.initelements method inside the constructor
		WebElement loginEmail;
		
	    //CODE: WebElement loginPassword = driver.findElement(By.id("userPassword"))
	    @FindBy(id = "userPassword")
	    WebElement loginPassword;
	    
	    //CODE: WebElement login = driver.findElement(By.id("login")
	    @FindBy(id = "login")
	    WebElement login;
	    
	  //CODE: .ng-tns-c4-4.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	    @FindBy(css = "[class*='flyInOut']")
	    WebElement errorMessage;
	    
	    public ProductPage loginApplication(String email,String password)
	    {
	    	loginEmail.sendKeys(email);
	    	loginPassword.sendKeys(password);
	    	login.click();
	    	
	    	//Creating object for ProductPage
	    	ProductPage ProductPage = new ProductPage(driver); //Calling ProductPage class into LandingPage class
	    	return ProductPage;
	    }
	    
	    public String getErrorMessage() throws InterruptedException
	    {
	    	waitForWebElementToAppear(errorMessage);
	    	return errorMessage.getText();
	    	
	    }
		
		
	
		

	}


