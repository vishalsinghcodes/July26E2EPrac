package e2e.pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogPage extends AbstractComponents {

	WebDriver driver;

	ProductCatalogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='card-body']")
	public
	List<WebElement> productdetailscardEle;

	@FindBy(xpath = "//div[@aria-label='Product Added To Cart']")
	WebElement productCatalogtoastele;

	@FindBy(css = ".ng-animating")
	WebElement spinnerEle;
	
	@FindBy(css="[class*='blink']")
	public
	WebElement blinkTextele;

	public List<String> listOfAllElements() {
		waitForElementstoappear(productdetailscardEle);
		List<String> prolist = new ArrayList<String>();
		prolist = productdetailscardEle.stream().map(s -> s.findElement(By.xpath("h5/b")).getText())
				.collect(Collectors.toList());
		return prolist;
	}

	public void addToCart(String proname) {
		waitForElementstoappear(productdetailscardEle);
		productdetailscardEle.stream().filter(s->s.findElement(By.xpath("h5/b")).getText()
				.equalsIgnoreCase(proname))
		.map(s->s.findElement(By.xpath("button[text()=' Add To Cart']"))).limit(1).forEach(s->{
			scrollToElement(s);
			s.click();	
		});
		waitForElementToappear(productCatalogtoastele);
		waitForElementTodisappear(spinnerEle);
	}
	
	public void addItemsToCart(List<String> items) throws InterruptedException {
		waitForElementstoappear(productdetailscardEle);
		Thread.sleep(2000);
		productdetailscardEle.stream().filter(s->items.contains(s.findElement(By.xpath("h5/b")).getText()))
		.map(s->s.findElement(By.xpath("button[text()=' Add To Cart']"))).forEach(s->{			
			scrollToElement(s);
			waitForElementToBeClickable(s);
			s.click();	
			waitForElementToappear(productCatalogtoastele);
			waitForElementTodisappear(spinnerEle);
		});
		
	}

}
