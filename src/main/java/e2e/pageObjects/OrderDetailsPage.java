package e2e.pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderDetailsPage extends AbstractComponents {

	WebDriver driver;

	OrderDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".btn.btn-primary.mt-3.mb-3:last-of-type")
	List<WebElement> downloadExcel;

	@FindBy(css=".em-spacer-1 .ng-star-inserted")
	List<WebElement> orderListele;
	
	public void downloadExcel() {
		downloadExcel.get(1).click();
	}

	public boolean checkOrdersinExcelandWebPage(String username) throws IOException {
		List<String> orderIdsFromPage = orderListele.stream().map(s->s.getText().split(" ")[1]).collect(Collectors.toList());
		String nameOfExcel = "\\order-invoice_" + username.split("@")[0] + ".xlsx";
		String downloadPath = System.getProperty("user.dir") + "\\Downloads" + nameOfExcel;
		FileInputStream fis = new FileInputStream(downloadPath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		int noOfSheets = wb.getNumberOfSheets();
		XSSFSheet reqSheet = null;
		for (int i = 0; i < noOfSheets; i++) {
			if(wb.getSheetAt(i).getSheetName().equalsIgnoreCase("data")) {
				reqSheet= wb.getSheetAt(i);
			}
		
		}
		List<String> orderIdsinExcel = new ArrayList<String>();
		int noOfRows = reqSheet.getPhysicalNumberOfRows();
		for(int i=1;i<noOfRows;i++) {
			orderIdsinExcel.add(reqSheet.getRow(i).getCell(0).getStringCellValue());
		}
		orderIdsFromPage.stream().forEach(s->System.out.println(s));
		orderIdsinExcel.stream().forEach(s->System.out.println(s));
		deleteFile(downloadPath);
		return orderIdsFromPage.stream().collect(Collectors.toSet()).equals(orderIdsinExcel.stream().collect(Collectors.toSet()));
		

	}

}
