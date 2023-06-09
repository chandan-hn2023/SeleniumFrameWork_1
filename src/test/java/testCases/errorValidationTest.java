package testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import PageObjects.CartPage;
import PageObjects.LandingPage;
import PageObjects.ProductPage;
import PageObjects.checkoutPage;
import PageObjects.confirmationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import testComponents.baseTest;

public class errorValidationTest extends baseTest {

	@Test(groups = {"ErrorHandling"})
	//@Test(groups = {"ErrorHandling"} , retryAnalyzer=Retry.class) //retryAnalyzer=Retry.class)- Mandatory if u want to run any Test
	
	public void loginErrorValidation() throws IOException, InterruptedException
	{
		//Giving invalid Email and Password
		landingpage.loginApplication("chandanhngowda@gmail.com", "Password@2022"); //Logging into the application
		//Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		
	}
	
@Test
	
	public void productErrorValidation() throws IOException, InterruptedException
	{
		
		String productname = "ZARA COAT 3";
		
		//PRODUCT PAGE
		ProductPage ProductPage = landingpage.loginApplication("chandanhn174@gmail.com", "Password@1996"); //Logging into the application
		List<WebElement> products = ProductPage.getProductList();
		ProductPage.addProductToCart(productname);
		
		//CART PAGE
		CartPage cartPage = ProductPage.goToCartPage();
		Boolean match = cartPage.verifySelectedProduct(productname);
		Assert.assertTrue(match);
		
	
	}

}

