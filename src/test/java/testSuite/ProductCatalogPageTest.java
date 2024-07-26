package testSuite;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import e2e.TestComponents.BaseTest;
import e2e.TestComponents.RetryListener;
import e2e.pageObjects.ProductCatalogPage;

public class ProductCatalogPageTest extends BaseTest{
	
	
	@Test
	public void errorMessageonLoginTest() throws IOException{
		lp.loginapplication("username@gmail.com", "pass123");
		Assert.assertTrue(lp.checkinctoastText("Incorrect email or password."));	
	}
	
	
	@Test(dataProvider = "CorrectdataPro", groups = {"Regression"},retryAnalyzer = RetryListener.class)
	public void checkBlinkText(HashMap<String,String> data) throws IOException, InterruptedException{
		ProductCatalogPage productcatalogpage = lp.loginapplication(data.get("username"), data.get("password"));
		productcatalogpage.waitForElementstoappear(productcatalogpage.productdetailscardEle);
		Assert.assertEquals(productcatalogpage.blinkTextele.getText(), "User can only see maximum 9 products on a page");
		
	}
	
	
	
	@DataProvider
	public Object[] CorrectdataPro() throws IOException {
		String pathToCorrectData = System.getProperty("user.dir") +"\\src\\test\\java\\testData\\CorrectCredentials.json";
		List<HashMap<String,String>> listdata = dataFromJson(pathToCorrectData);
		int noOfrecords = listdata.size();
		Object[] dataToTest = new Object[noOfrecords];
		for(int i =0;i<noOfrecords;i++) {
			dataToTest[i]= listdata.get(i);
		}
		return dataToTest;
	}

}
