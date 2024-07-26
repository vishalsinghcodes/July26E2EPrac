package e2e.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer{

	int count=0;
	int maxCount =2;
	
	@Override
	public boolean retry(ITestResult result) {
		count++;
		if(count<maxCount)
			return true;

		return false;
	}
	
	
	
	

}
