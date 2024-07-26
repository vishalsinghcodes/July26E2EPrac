package testSuite;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import e2e.TestComponents.BaseTest;
import e2e.pageObjects.LandingPageObject;

public class LandingPageTests extends BaseTest{
	
	
	@Test(groups = {"Regression"})
	public void errorMessageonLoginTest() throws IOException{
		lp.loginapplication("username@gmail.com", "pass123");
		Assert.assertTrue(lp.checkinctoastText("Incorrect email or password."));	
	}
	
	
	

}
