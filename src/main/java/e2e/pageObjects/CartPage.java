package e2e.pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstractComponents{

	WebDriver driver;
	CartPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(xpath="//button[text()='Continue Shopping']")
	WebElement continueShoppingEle;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> proinCartList;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutButton;
	
	
	public ProductCatalogPage clickContinueShoppinButton() {
		continueShoppingEle.click();
		ProductCatalogPage page = new ProductCatalogPage(driver);
		return page;		
	}
	
	public boolean checkProductinCart(String proname) {	
		return proinCartList.stream().anyMatch(s->s.getText().equalsIgnoreCase(proname));	
	}
	
	public CheckoutPage clickCheckOutButton() {
		checkoutButton.click();
		CheckoutPage checkouPage = new CheckoutPage(driver);
		return checkouPage;
	}
	
	
	
	
	
	
}
