package testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class reRunFailedTestCases implements IRetryAnalyzer { //IRetryAnalyzer - Is an interface provided TestNG, to re-run failed test cases

	int count = 0;
	int maxTry = 2;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxTry)
		{
			count++;
			return true;
		}
		return false;
	} 
	
	
	
}
