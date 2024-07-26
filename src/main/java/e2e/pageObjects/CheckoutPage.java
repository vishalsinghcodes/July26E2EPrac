package e2e.pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponents{

	WebDriver driver;
	CheckoutPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(xpath="//*[@placeholder='Select Country']")
	WebElement countryTextBoxEle;
	
	@FindBy(xpath="//span[@class='ng-star-inserted']")
	List<WebElement> countryList;
	
	@FindBy(xpath="//a[text()='Place Order ']")
	WebElement placeOrderButton;

	
	public void selectCountry(String partialText, String countryName) {
		countryTextBoxEle.sendKeys(partialText);
		waitForElementstoappear(countryList);
		countryList.stream().filter(s->s.getText().equalsIgnoreCase(countryName)).limit(1).forEach(s->s.click());
	}
	
	
	public OrderDetailsPage clickPlaceOrderButton() {
		placeOrderButton.click();
		OrderDetailsPage page = new OrderDetailsPage(driver);
		return page;
	}
	
	
	
	
	
}
