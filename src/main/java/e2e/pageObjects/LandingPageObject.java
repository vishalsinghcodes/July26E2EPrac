package e2e.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPageObject extends AbstractComponents{
	
	
	WebDriver driver;
	public LandingPageObject(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(id="userEmail")
	WebElement usernameTextele;
	
	@FindBy(id="userPassword")
	WebElement passwordTextele;
	
	@FindBy(id="login")
	WebElement loginButtonEle;
	
	@FindBy(xpath="//*[@aria-label='Incorrect email or password.']")
	WebElement incorrectcredstoastTextEle;
	
	public void goToPageLandingPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	public ProductCatalogPage loginapplication(String username, String password) {
		usernameTextele.sendKeys(username);
		passwordTextele.sendKeys(password);
		loginButtonEle.click();
		ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
		return productCatalogPage;
	}
	
	public boolean checkinctoastText(String expectedText) {
		System.out.println("Incorrect credentials toastmessage is : " +incorrectcredstoastTextEle.getText());
		return compareText(incorrectcredstoastTextEle,expectedText );
	}
	
	
	
	
	
	

}
