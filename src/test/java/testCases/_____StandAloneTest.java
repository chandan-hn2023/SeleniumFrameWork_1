package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class _____StandAloneTest {

	public static void main(String[] args) {
		
		String productname = "ZARA COAT 3";
		//Copy paste Webdrivermanager Maven dependency into POM file
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("chandanhngowda@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@2022");
		driver.findElement(By.id("login")).click();
		
		//Waits till the products gets loded
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
		
		//Finding and grabbing Zara coat3 item
		//Grab all the items in the page into a LIST by finding genric web element(All the items should be selected in the page)
		
		List<WebElement> products= driver.findElements(By.cssSelector("div.mb-3"));
		
		//Iterate through all items in list and find Zara coat3
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null); //product name = ZARA COAT 3
		
		//Clicking Add to the cart button of the particular item
		//NEW CONCEPT: LAST-OF-TYPE highlights the last button|FIRST-OF-TYPE viceversa
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); 
		
		//Using explicit wait for validation of TOSTER MESSAGE(which is "Product added succesfully")
		
		//Globalised this code - WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//Explicit wait for loading animation
		
		//Below step is taking too much of time to execute - BOTH STEPS ARE VALID
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-animating"))); //"ng-animating" - Locator of loading animation
		
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); //Instead of above step - BOTH STEPS ARE VALID
		
		//------------------------------------------------------------------------------------------------------------------------
		//Clicking on Cart button in the header
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//Grabbing the elements(items) into a list in Cart page
		
		List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
		
		//Iterate through all items in list and find Zara coat3
		//FILTER - It will scan all the webelements and provide you the desired Web element
		//anyMatch - It will scan all the items and check whether the we have the desired text/output 
		Boolean match = cartProducts.stream().anyMatch(cartProduct->
		cartProduct.getText().equalsIgnoreCase(productname)); //product name = ZARA COAT 3
		Assert.assertTrue(match);
		
		//TO click on Checkout button
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//Clicking on Select country dropdown --- Sending the input ---- Selecting that input (USING ACTION CLASS)
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		//Selecting the country
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		
		//Clicking on Place order button
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
	
	}

}






























