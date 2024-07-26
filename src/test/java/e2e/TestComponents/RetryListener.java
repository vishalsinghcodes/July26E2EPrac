package e2e.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {

	int count = 0;
	int maxCount = 2;

	@Override
	public boolean retry(ITestResult result) {
		if (count < maxCount) {
			count++;
			return true;
		}

		return false;
	}

}
