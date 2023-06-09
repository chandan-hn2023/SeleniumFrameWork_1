package testCases;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.LandingPage;
import PageObjects.ProductPage;
import PageObjects.checkoutPage;
import PageObjects.confirmationPage;
import PageObjects.orderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import testComponents.baseTest;

public class SubmitOrderTest extends baseTest {

	//String productname = "ZARA COAT 3";
	
	@Test(dataProvider = "getData",groups= {"Purchase"}) //Giving the adddress of Data provider
	
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{	
		//PRODUCT PAGE
		ProductPage ProductPage = landingpage.loginApplication(input.get("email"), input.get("password")); //Logging into the application
		List<WebElement> products = ProductPage.getProductList();
		ProductPage.addProductToCart(input.get("products"));
		
		//CART PAGE
		CartPage cartPage = ProductPage.goToCartPage();
		Boolean match = cartPage.verifySelectedProduct(input.get("products"));
		Assert.assertTrue(match);
		
		//CHECKOUT PAGE
		checkoutPage CheckOutPage = cartPage.clickCheckout();
		CheckOutPage.selectCountry("India");
		
		//CONFIRMATION PAGE
		confirmationPage ConfirmationPage =  CheckOutPage.placeOrder();
		String confirmMessage = ConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}
	
	//@Test(dependsOnMethods = {"submitOrder"})
	//To verify whether Zara coat 3 is displaying in Orders page or not
	//This scenrio is dependent on the above @Test so we should use "   (dependsOnMethods = {"submitOrder"})    "
	
/*	public void orderHistoryTest(String productName)
	{
		ProductPage ProductPage = landingpage.loginApplication("chandanhngowda@gmail.com", "Password@2022");
		orderPage orderPage = ProductPage.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	} */
	
	@DataProvider //Give the address of Data provider in @Test
	
	public Object[] [] getData() throws IOException
	{
		//We can use HASH MAP concept when we have multiple parameters
		
		/*HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "chandanhngowda@gmail.com");
		map.put("password", "Password@2022");
		map.put("products", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "chandanhn174@gmail.com");
		map1.put("password", "Password@1996");
		map1.put("products", "ADIDAS ORIGINAL"); */
		
		//Instead of Hashmap we use Json to push the data into the test
		//1. Create a json file - Right click on test/java -> File -> Name the file(.json extension) -> Put the above data into the file
		//2. Create a new class -> Inside it create a method which scans the json file and create HashMaps out of it 
		
		List<HashMap<String,String>> data = getJasonDataToMap(System.getProperty("user.dir")+"//src//test//java//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
