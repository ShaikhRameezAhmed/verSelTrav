package automationFramework;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utility.Configuration;
import lib.DriverAndObjectDetails;
import lib.DriverAndObjectDetails.DriverName;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.PageSynchronize;
import lib.Takescreenshot;
import objectRepository.IncidentCreate;
import objectRepository.POM;

public class CreateIncident {
	Configuration Config = new Configuration();
	public static WebDriver driver;
	Logger logger = Logger.getLogger("Create_Incident");
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	static Takescreenshot obj = new Takescreenshot();
	
	 @Test
	public void Test() throws IOException, InterruptedException {
		PropertyConfigurator.configure("Log4j.properties");
		driver.get(Config.getApplicationUrl());
		driver.manage().window().maximize();
		try {
			logger.info("Browser Opened");
			logger.info("Test Case Started");
			test = rep.startTest("CreateIncidentTest");
			ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
			POM.emailtextbox(driver).sendKeys("sally@Travelport.com");
			POM.passwordbox(driver).sendKeys("Abc1234!");
			POM.signin(driver);
			logger.info("Sign in Completed");
			PageSynchronize.loadPageBeforeNaviating(driver);
			logger.info("Home Page");
			/*IncidentCreate.ButtonCreate(driver);
			logger.info("Create Button Clicked");*/
			//PageSynchronize.loadPageBeforeNaviating(driver);
			
			WebElement modalDisplay = driver.findElement(By.xpath("//div[@class='modal-content yui3-widget-stdmod']"));		
			WebDriverWait block = new WebDriverWait(driver, 30);	
			closePopUpDisplayWithFocussed(block);		
			block.until(ExpectedConditions.visibilityOf(modalDisplay));
			while((modalDisplay.isDisplayed())) {
				modalDisplay.click();
				closePopUpDisplayWithFocussed(block);
			}
			
			
			
			IncidentCreate.ButtonQATEST(driver);
			PageSynchronize.loadPageBeforeNaviating(driver);
			IncidentCreate.Subject(driver).sendKeys(excel.getData(3, 1, 0));
			IncidentCreate.Question(driver).sendKeys(excel.getData(3, 1, 1));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			// Scroll vertically downward by 250 pixels
			jse.executeScript("window.scrollBy(0,250)", "");
			IncidentCreate.SubmitBTN(driver);
			PageSynchronize.loadPageBeforeNaviating(driver);
			//IncidentCreate.Validate(driver);
			IncidentCreate.notification(driver);
			PageSynchronize.loadPageBeforeNaviating(driver);
			IncidentCreate.notificationlink(driver);
			Thread.sleep(10000);
			test.log(LogStatus.INFO, "Ending Newly Created Incident Alert");
			test.log(LogStatus.PASS, "PASSED Displayed Newly Created Incident Alert");
			obj.Takesnap(driver,
					Config.SnapShotPath() + "/Notification for newly Created Incident Alert/Alert_Dispalyed.jpg");
			POM.logOut(driver);
			test.log(LogStatus.INFO, "Ending IncidentAlertTest");
			test.log(LogStatus.PASS, "PASSED IncidentAlertTest");
			logger.info("IncidentAlertTest Successful");
			rep.endTest(test);
			rep.flush();
		} catch (Exception e) {
			logger.info(e.getMessage());
		/*	test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
			test.log(LogStatus.FAIL, "Creating Incident");
			System.out.println(e.getMessage());*/
		}

	}

	private void closePopUpDisplayWithFocussed(WebDriverWait block) {
		WebElement Cross = driver.findElement(By
				.xpath("//*[@class='yui3-widget modal yui3-widget-positioned yui3-widget-stacked yui3-widget-modal modal-focused']"
						+ "/descendant::button[@type='button'][text()='×']"));
		
		block.until(ExpectedConditions.visibilityOf(Cross));
		if(Cross.isDisplayed()) {
			Cross.click();
		}
	}
	 
	 @BeforeTest
	  @Parameters("browsers")
		public void beforeTest(String browser) {
			if (browser.equalsIgnoreCase("Chrome")) {
				driver = new DriverAndObjectDetails(DriverName.CH).CreateDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				driver = new DriverAndObjectDetails(DriverName.IE).CreateDriver();
			} else {
				driver = new DriverAndObjectDetails(DriverName.FF).CreateDriver();
			}
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
		}
	 
	 /*@AfterTest
		public void afterTest() {
			rep.endTest(test);
			rep.flush();
			driver.quit();
		}*/
	
}
