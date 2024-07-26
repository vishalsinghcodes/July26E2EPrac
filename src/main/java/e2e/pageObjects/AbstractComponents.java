package e2e.pageObjects;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	
	WebDriver driver;
	
	protected AbstractComponents(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean compareText(WebElement ele, String expectedText) {
		if(ele.getText().equalsIgnoreCase(expectedText))
			return true;
		return false;
		
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartButtonEle;
	
	@FindBy(xpath="//button[text()=' Sign Out ']")
	WebElement signOutEle;
	
	
	public CartPage clickCartButton() {
		scrollToElement(cartButtonEle);
		cartButtonEle.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public LandingPageObject signOut() {
		scrollToElement(signOutEle);
		signOutEle.click();
		LandingPageObject page = new LandingPageObject(driver);
		return page;
	}
	
	public void scrollToElement(WebElement ele) {
		int x = ele.getRect().getX();
		int y = ele.getRect().getY();
		((JavascriptExecutor)driver).executeScript("window.scrollTo("+x+","+y+")");	
	}
	
	
	public void waitForElementToappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementTodisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitForElementstoappear(List<WebElement> eles) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(eles));
	}
	
	public void waitForElementToBeClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void deleteFile(String pathToFile) {
		File file = new File(pathToFile);
		if(file.exists()) {
			System.out.println("file existed");
			file.delete();
			System.out.println("file deleted");
		}else {
			System.out.println("file does not exist");
		}
		
	}
	
	public static String returnCurrentDateTime() {
		return new SimpleDateFormat("DD_MM_YYYY_HH_mm_ss").format(Calendar.getInstance().getTime());
	}
	

}
