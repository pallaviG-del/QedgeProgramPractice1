import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AdvancedReports {

	
		WebDriver driver;
		ExtentReports report = new ExtentReports("./target/ExtentReportResult/extentReport.html");
		ExtentTest logger;
		
	@BeforeMethod
	public void setUp() {
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.google.com/");
				
		}
		
	@Test
	public void pageTitleValidation() {
		
		logger = report.startTest("page title test");
		logger.assignAuthor("Pallavi");
		String actual = driver.getTitle();
		String expected = "google";
		logger.log(LogStatus.INFO, "actual value----"+actual);
		logger.log(LogStatus.INFO, "expected value ----" + expected);
		if(actual.equalsIgnoreCase(expected)) {
			
			logger.log(LogStatus.PASS, "Title is matching");
			
		}else {
			
			logger.log(LogStatus.FAIL, "title is not matching");
		}
		
		}
	
	@Test
	public void pageUrlValidation() {
		logger = report.startTest("current url validation");
		logger.assignAuthor("pallavi");	
		
		driver.findElement(By.xpath("//a[@class='MV3Tnb']")).click();
		String actualcurrenturl = driver.getCurrentUrl();
		String expectedcurrenturl = "/?fg=1&utm_source=google-IN&utm_medium=referral&utm_campaign=hp-header";
		logger.log(LogStatus.INFO, "expecte url--"+ expectedcurrenturl);
		logger.log(LogStatus.INFO, "Actual Url--"+actualcurrenturl);
		
		
		if(actualcurrenturl.equals(expectedcurrenturl)) {
			
			logger.log(LogStatus.PASS, "currenturl is matchin");
			
		}
		else {
			
			logger.log(LogStatus.FAIL, "Url not matching");
		}
		
		Assert.assertEquals(actualcurrenturl, expectedcurrenturl);
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		report.endTest(logger);		
		report.flush();
		driver.close();
		
	}
	
	
	


	
	

}
