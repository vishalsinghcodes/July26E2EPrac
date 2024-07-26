package testSuite;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import e2e.TestComponents.BaseTest;
import e2e.pageObjects.CartPage;
import e2e.pageObjects.CheckoutPage;
import e2e.pageObjects.OrderDetailsPage;
import e2e.pageObjects.ProductCatalogPage;

public class E2ETests extends BaseTest{
	
	@Test(dataProvider = "CorrectdataPro")
	public void PurchaseProduct(HashMap<String,String> data) throws IOException, InterruptedException{
		ProductCatalogPage productcatalogpage = lp.loginapplication(data.get("username"), data.get("password"));
		productcatalogpage.addToCart(data.get("product"));
		CartPage cartPage =  productcatalogpage.clickCartButton();
		cartPage.checkProductinCart(data.get("product"));
		CheckoutPage checkOutPage  = cartPage.clickCheckOutButton();
		checkOutPage.selectCountry("ind", "India");
		OrderDetailsPage orderDetailsPage =   checkOutPage.clickPlaceOrderButton();
		orderDetailsPage.downloadExcel();
		Thread.sleep(3000);
		Assert.assertTrue(orderDetailsPage.checkOrdersinExcelandWebPage(data.get("username")));
		Thread.sleep(3000);
		
	}
	
	@Test(dataProvider = "CorrectdataPro", groups = {"Regression"})
	public void PurchasemultipleProduct(HashMap<String,String> data) throws IOException, InterruptedException{
		List<String> products = Arrays.asList("ZARA COAT 3");
		ProductCatalogPage productcatalogpage = lp.loginapplication(data.get("username"), data.get("password"));
		productcatalogpage.addItemsToCart(products);
		CartPage cartPage =  productcatalogpage.clickCartButton();
//		cartPage.checkProductinCart(data.get("product"));
		CheckoutPage checkOutPage  = cartPage.clickCheckOutButton();
		checkOutPage.selectCountry("ind", "India");
		OrderDetailsPage orderDetailsPage = checkOutPage.clickPlaceOrderButton();
		orderDetailsPage.downloadExcel();
		Thread.sleep(3000);
		Assert.assertTrue(orderDetailsPage.checkOrdersinExcelandWebPage(data.get("username")));
		Thread.sleep(3000);
		
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
