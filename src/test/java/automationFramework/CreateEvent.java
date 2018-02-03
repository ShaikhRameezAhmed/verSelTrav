package automationFramework;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import objectRepository.CreateEventRepository;
import objectRepository.BootstrapAlerts;
import objectRepository.POM;

public class CreateEvent {
	Configuration Config = new Configuration();
	public static WebDriver driver;
	Logger logger = Logger.getLogger("Create_Event");
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	static Takescreenshot obj = new Takescreenshot();
  
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
		}

  @Test
  public void f() throws IOException, InterruptedException {
	  //driver.manage().timeouts().implicitlyWait(40, TimeUnit.MILLISECONDS);
	  PropertyConfigurator.configure("Log4j.properties");
		driver.get(Config.getApplicationUrl());
		driver.manage().window().maximize();
		try{
		logger.info("Browser Opened");
		logger.info("Test Case Started");
		test = rep.startTest("CreateEvent");
		ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
		POM.emailtextbox(driver).sendKeys(excel.getData(0, 1, 0));
		POM.passwordbox(driver).sendKeys(excel.getData(0, 1, 1));
		POM.signin(driver);
		test.log(LogStatus.INFO, "Ending Sign in");
		test.log(LogStatus.PASS, "PASSED Sign in");
		PageSynchronize.loadPageBeforeNaviating(driver);
		WebElement modalDisplay = driver.findElement(By.xpath("//div[@class='modal-content yui3-widget-stdmod']"));
		//WebDriverWait block = new WebDriverWait(driver, 10);
		WebElement Cross = driver.findElement(By
				.xpath("//*[@class='yui3-widget modal yui3-widget-positioned yui3-widget-stacked yui3-widget-modal modal-focused']"
						+ "/descendant::button[@type='button'][text()='×']"));

		Thread.sleep(4000);
		if(modalDisplay.isDisplayed()||Cross.isDisplayed()){
		BootstrapAlerts.AlertsDismiss(driver);
		}

		
		
		CreateEventRepository.newsTab(driver);
		PageSynchronize.loadPageBeforeNaviating(driver);
		CreateEventRepository.EventLink(driver);
		test.log(LogStatus.INFO, "Clicked on Event Link");
		test.log(LogStatus.PASS, "Clicked on Event Link");
		PageSynchronize.loadPageBeforeNaviating(driver);
		CreateEventRepository.createEvent(driver);
		PageSynchronize.loadPageBeforeNaviating(driver);
		CreateEventRepository.scrolldown(driver, 1,0);
		CreateEventRepository.title(driver).sendKeys(excel.getData(5, 4, 0));
		CreateEventRepository.summary(driver).sendKeys(excel.getData(5, 7, 0));
		CreateEventRepository.summary(driver).click();
		//putting data  on content iframe
		CreateEventRepository.content(driver).sendKeys("Selenium content test" + Keys.TAB);
		driver.switchTo().window(driver.getWindowHandle());
		CreateEventRepository.StartDate(driver).sendKeys("2/11/2018");
		CreateEventRepository.StartTime(driver).sendKeys("7:30:00 AM");
		CreateEventRepository.EndDate(driver).sendKeys("2/14/2018");
		CreateEventRepository.EndTime(driver).sendKeys("7:30:00 AM");
		test.log(LogStatus.INFO, "Create Event data (Summary/ start time, end time content) entered successfully");
		test.log(LogStatus.PASS, "Create Event data (Summary/ start time, end time content) entered successfully");
		//CreateEventRepository.summary(driver).sendKeys(Keys.TAB + "2/11/2018" +Keys.TAB + "7:30:00 AM" +Keys.TAB + "2/14/2018" + Keys.TAB + "7:30:00 AM" +Keys.TAB);
		CreateEventRepository.publish(driver);
		PageSynchronize.loadPageBeforeNaviating(driver);
		obj.Takesnap(driver, Config.SnapShotPath() + "/CreateEvent/Event_Created.jpg");
		POM.logOut(driver);
		test.log(LogStatus.INFO, "Create Event Completed");
		test.log(LogStatus.PASS, "Create Event Successfull");
		logger.info("CreateEventTest Successful");
		rep.endTest(test);
		rep.flush();
	} catch (Exception e) {
		logger.info(e.getMessage());
		test.log(LogStatus.FAIL, e.getMessage());
		rep.endTest(test);
		rep.flush();
		//Assert.assertTrue(false, e.getMessage());
		test.log(LogStatus.FAIL, "Creating event");
		System.out.println(e.getMessage());
	}
  }
  
  
  
  @AfterTest
  public void tearDown(){
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  rep.endTest(test);
	  rep.flush();
	  driver.quit();
  }
}
