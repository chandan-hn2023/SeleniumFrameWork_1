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

public class ProductPage extends ReusableComponents {

	WebDriver driver; //Local driver
	
	//Constructor
	public ProductPage(WebDriver driver) //This driver is from StandAloneTest class
	{
		//Below step is mandatory when u r sending variable to Parent class
		super(driver); //Sending the variable to Parent class(ReusableComponents) using a keyword "super"
		this.driver=driver; //Assigning StandAloneTest class's driver into this Local driver || this .driver points to the local driver	
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory - driver.findelement - U can use Page factory, By. - We can't use
	//CODE: List<WebElement> products= driver.findElements(By.cssSelector("div.mb-3"));
	
	//Finding and grabbing Zara coat3 item
	//Grab all the items in the page into a LIST by finding genric web element(All the items should be selected in the page)
	
	@FindBy(css = "div.mb-3") 
	List<WebElement> products;
	
	//For By. 
	By productsList = By.cssSelector("div.mb-3");
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	By loadingIcon = By.cssSelector("ng-animating");
		
	    public List<WebElement> getProductList()
	    {
	    	waitForElementToAppear(productsList); //Waits till the products gets loded - This method is from ReusableComponents class
	    	return products; //Loads all the products
	    }
	    
	    //CODE: WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
	    public WebElement getProductByName(String productname)
	    {
	    	//Iterate through all items in list and find Zara coat3
			WebElement prod = getProductList().stream().filter(product->
			product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null); //product name = ZARA COAT 3
			return prod;
	    }
		
	    //CODE: prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		public void addProductToCart(String productname) throws InterruptedException
		{
			//Clicking Add to the cart button of the particular item
			WebElement addProd = getProductByName(productname);
			waitForElementToAppear(toastMessage);
			waitForElementToDisAppear(loadingIcon);
			addProd.findElement(addTocart).click(); 
			
		}
		
		
	
		

	}


