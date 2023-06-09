package testComponents;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {
	
	public WebDriver driver; //Don't create driver object inside the code, define it in a global level variable 
	public Properties prop; //Global level access
	public LandingPage landingpage; //Initialising LandingPage for global level access
	
	public WebDriver intializeDriver() throws IOException
	{
		
		//Intialize the properties class
		Properties prop = new Properties();
		
		//Creating .properties file and passing the path as an argument 
		//FileInputStream class - Helps to convert "file" into "Input stream object"
		FileInputStream fis = new FileInputStream("D:\\Chandan\\Selenium\\SeleniumFrameworkDesign\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);
		
		//To run the scripts in multiple broswers
		//If System.getProperty("browser")!=null is TRUE then System.getProperty("browser")- FireFox will execute 
		//If its FALSE prop.getProperty("browser")- Chrome will execute
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		//String browserName = prop.getProperty("browser"); -FOR SINGLE BROWSER EXECUTION
		
		if(browserName.contains("chrome"))
		{
			//To run in HEADLESS MODE - Means execution happens bakendBrowser windows won't popup, faster execution
			ChromeOptions options = new ChromeOptions(); 
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
			{
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			//driver.manage().window().setSize(new Dimension(1400,900)); - Helps to run in full screeen mode
			
		}
		
		if(browserName.equalsIgnoreCase("FireFox"))
		{
			//FireFox
			System.setProperty("webdriver.gecko.driver","C:\\Gecko Driver4\\geckodriver");
			driver = new FirefoxDriver();
		}
		
		if(browserName.equalsIgnoreCase("Edge"))
		{
			//Edge
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	//Read json to string 
	
		public List<HashMap<String, String>> getJasonDataToMap(String filePath) throws IOException
		{
			
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//Convert string to Hashmap
		//Download a dependency called "Jackson DataBind" from Maven repository
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		}
		
		public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
		{
	        TakesScreenshot ts = ((TakesScreenshot)driver);
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        File file = new File((System.getProperty("user.dir")+"//reports//") + testCaseName + ".png");
	        FileUtils.copyFile(source, file);
	        return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
		}
		
	@BeforeMethod(alwaysRun=true)
		public LandingPage launchApplication() throws IOException
		{
			driver = intializeDriver();
			//LANDING PAGE
			landingpage = new LandingPage(driver); //Creating object for LandingPage class
			landingpage.goToUrl(); //Accessing URL
			return landingpage;
		}
	
	@AfterMethod(alwaysRun=true)
	
	public void closeBrowser()
	{
		driver.close();
	}
		
		
		 
	}


