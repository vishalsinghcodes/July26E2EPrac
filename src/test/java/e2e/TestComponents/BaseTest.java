package e2e.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import e2e.pageObjects.LandingPageObject;

public class BaseTest {
	public WebDriver driver;
	public LandingPageObject lp;
	
	public WebDriver initializeDriver() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\e2e\\resources\\GlobalProperty.properties";
		FileInputStream fis = new FileInputStream(filePath);
		Properties prop = new Properties();
		prop.load(fis);
		String browsername = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		String headOption = System.getProperty("headOption")!=null?System.getProperty("headOption"):prop.getProperty("headOption");
		String downloadPath = System.getProperty("user.dir") + "\\Downloads";
		//String downloadPath = "C:\\Users\\Vishal Singh\\eclipse-workspace\\PracticeE2EECommerce2024\\Downloads";
		switch(browsername) {
		
		case "chrome":
			ChromeOptions op = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", downloadPath);
			op.setExperimentalOption("prefs", prefs);
			op.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			op.addArguments(headOption);
			driver = new ChromeDriver(op);
			break;
		case "firefox": 
			FirefoxProfile profile = new FirefoxProfile();
             profile.setPreference("browser.download.dir", downloadPath);
             profile.setPreference("browser.download.folderList", 2);
             profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf"); // Set MIME type as needed
             profile.setPreference("pdfjs.disabled", true); // Disable PDF viewer plugin
             profile.setPreference("browser.download.useDownloadDir", true);
             FirefoxOptions firefoxOptions = new FirefoxOptions();
             firefoxOptions.setProfile(profile);
             firefoxOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
             firefoxOptions.addArguments(headOption);
             driver = new FirefoxDriver(firefoxOptions);	
             break;
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
            Map<String, Object> edgePrefs = new HashMap<>();
            edgePrefs.put("download.default_directory", downloadPath);
            edgeOptions.setExperimentalOption("prefs", edgePrefs);
            edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            edgeOptions.addArguments(headOption);
            driver = new EdgeDriver(edgeOptions);
            break;
		default:
			ChromeOptions cop = new ChromeOptions();
			Map<String, Object> prefss = new HashMap<String, Object>();
			prefss.put("download.default_directory", downloadPath);
			cop.setExperimentalOption("prefs", prefss);
			cop.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			cop.addArguments(headOption);
			driver = new ChromeDriver();	
			break;
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		if(headOption.equalsIgnoreCase("head")) {
			driver.manage().window().maximize();
		}else {
			driver.manage().window().setSize(new Dimension(1440, 900));
		}
		
		return driver;
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPageObject launchApplication() throws IOException {
		driver = initializeDriver();
		lp = new LandingPageObject(driver);
		lp.goToPageLandingPage();	
		return lp;
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	
	public String getscreenShotPath(WebDriver driver, String testName) throws IOException {
		String path = System.getProperty("user.dir") +"\\ScreenShots" +"\\"+testName+".png";
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(path));
		return path;
		
	}
	
	
	public List<HashMap<String,String>> dataFromJson(String pathToJson) throws IOException {
		String stringOfJson = FileUtils.readFileToString(new File(pathToJson),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(stringOfJson, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
	}
	
	
	

}
