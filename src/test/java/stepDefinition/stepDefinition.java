package stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import PageObjects.CartPage;
import PageObjects.LandingPage;
import PageObjects.ProductPage;
import PageObjects.checkoutPage;
import PageObjects.confirmationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testComponents.baseTest;

public class stepDefinition extends baseTest {

	public LandingPage landingPage; //Global level inistialisation
	public ProductPage productPage; //Global inistialisation
	confirmationPage ConfirmationPage;//Global level inistialisation
	
	@Given("Logging into the application")
	
	public void Logging_into_the_application() throws IOException
	{
		landingPage=launchApplication();		
	}
	
	//@Given("Logged in with Username <Username> and <Password>")
	//Cant leave parameters in the code <Username> and <Password> instead we have use Regular expression (.*) - It represents a value
	//To fetch the data from feture file EXAMPLE table we use regular expression | Can't do the same for the step which have string in feature file
	
	@Given("^Logged in with Username (.*) and (.*)$") //This step should be genric
	//REMEMBER: Tell the string that it is a regular expression using ^ at the begining and $ at the end
	public void loginCredentials(String Username, String Password)
	{
		productPage = landingpage.loginApplication(Username,Password);
	}
	
	@When("^Adding the product (.*) to the cart$")
	
	public void addProduct(String productName) throws InterruptedException
	{
		List<WebElement> products = productPage.getProductList();
		productPage.addProductToCart(productName);
	}
	
	@When("^Checkout (.*) and Submit the order$")
	
	public void checkOut(String productName) throws InterruptedException
	{
		CartPage cartPage = productPage.goToCartPage();
		Boolean match = cartPage.verifySelectedProduct(productName);
		Assert.assertTrue(match);
		
		//CHECKOUT PAGE
		checkoutPage CheckOutPage = cartPage.clickCheckout();
		CheckOutPage.selectCountry("India");
		
		//CONFIRMATION PAGE
		ConfirmationPage =  CheckOutPage.placeOrder();
	}
	
	//I verify "THANKYOU FOR THE ORDER." message is displayed on confirmation page
	//Regular expression won't work as we have the String in feature file step - "THANKYOU FOR THE ORDER." Instead we use {string}
	@Then("I verify message {string} is displayed on confirmation page") 
	
	public void messageVerification(String string)
	{
		String confirmMessage = ConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
}
	
	//Short cut for above steps - TidyGerkin extension
	//There is an extension called TidyGerkin we can get readymade code for feature file implementation
	//We just have to paste the feature file data into extension, we will get the below code
	
/* package my.package.name
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class MyStepDefinitions {

    @Given("^Logging into the application$")
    public void logging_into_the_application() throws Throwable {
        throw new PendingException();
    }

    @Given("^Logged in with Username (.+) and (.+)$")
    public void logged_in_with_username_and(String username, String password) throws Throwable {
        throw new PendingException();
    }

    @When("^Adding the product (.+) to the cart$")
    public void adding_the_product_to_the_cart(String productname) throws Throwable {
        throw new PendingException();
    }

    @Then("^I verify \"([^\"]*)\" message is displayed on confirmation page$")
    public void i_verify_something_message_is_displayed_on_confirmation_page(String strArg1) throws Throwable {
        throw new PendingException();
    }

    @And("^Checkout (.+) and Submit the order$")
    public void checkout_and_submit_the_order(String productname) throws Throwable {
        throw new PendingException();
    } */


