package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber/",glue = "stepDefinition.stepDefinition",monochrome=true, tags = "@Regression",
plugin= {"html:target/cucumber.html"}) //Using tags we can run a specific Test case/Feature - TAGS is just like GROUPS of TESTNG

//Inbuilt Cucumber cant scan TestNG code so we use "AbstractTestNGCucumberTests"
public class TestNGRunner extends AbstractTestNGCucumberTests 

{ 
	
	
}
