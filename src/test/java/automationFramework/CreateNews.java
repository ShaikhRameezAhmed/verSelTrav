package automationFramework;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utility.Configuration;
import lib.BeforeDriverTest;
import lib.DriverAndObjectDetails;
import lib.DriverAndObjectDetails.DriverName;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.PageSynchronize;
import lib.Takescreenshot;
import objectRepository.BootstrapAlerts;
import objectRepository.CreateEventRepository;
import objectRepository.CreateNewsRepository;
import objectRepository.POM;

public class CreateNews extends BeforeDriverTest {
	Configuration Config = new Configuration();
	public static WebDriver driver;
	Logger logger = Logger.getLogger("Create_Service_Alert");
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	static Takescreenshot obj = new Takescreenshot();
  
	@BeforeTest
	  @Parameters("browsers")
		public void beforeTest(String browser) {
		System.setProperty("https.proxyHost", " 172.31.17.167");
        System.setProperty("https.proxyPort", "8080");
			if (browser.equalsIgnoreCase("Chrome")) {
				new DriverAndObjectDetails(DriverName.CH);
				driver = DriverAndObjectDetails.CreateDriver();
				//driver= new DriverAndObjectDetails
			} else if (browser.equalsIgnoreCase("IE")) {
				new DriverAndObjectDetails(DriverName.IE);
				driver = DriverAndObjectDetails.CreateDriver();
			} else {
				new DriverAndObjectDetails(DriverName.FF);
				driver = DriverAndObjectDetails.CreateDriver();
			}
		}

  @Test
	public void f() throws IOException, InterruptedException {
		//driver.manage().timeouts().implicitlyWait(40, TimeUnit.MILLISECONDS);
		PropertyConfigurator.configure("Log4j.properties");
		driver.get(Config.getApplicationUrl());
		driver.manage().window().maximize();
		try {
			logger.info("Browser Opened");
			logger.info("Test Case Started");
			test = rep.startTest("CreateNews");
			ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
			POM.emailtextbox(driver).sendKeys(excel.getData(0, 1, 0));
			POM.passwordbox(driver).sendKeys(excel.getData(0, 1, 1));
			POM.signin(driver);
			test.log(LogStatus.INFO, "Ending Sign in");
			test.log(LogStatus.PASS, "PASSED Sign in");
			PageSynchronize.loadPageBeforeNaviating(driver);
			BootstrapAlerts.AlertsDismiss(driver);
			
			CreateNewsRepository.newsTab(driver);
			PageSynchronize.loadPageBeforeNaviating(driver);
			CreateNewsRepository.newsLink(driver);
			test.log(LogStatus.INFO, "Clicked on News Link");
			test.log(LogStatus.PASS, "Clicked on News Link");
			PageSynchronize.loadPageBeforeNaviating(driver);
			CreateNewsRepository.createArticle(driver);
			PageSynchronize.loadPageBeforeNaviating(driver);
			//data to be put into feilds
			int titlenumber = generateNumber();
			String title = excel.getData(2, 1, 0) + titlenumber;
			int summaryNumber = generateNumber();
			String summary = excel.getData(2, 4, 0)+summaryNumber;
			int contentNumber = generateNumber();
			String finalcontent ="Creating News for#"+ contentNumber;
			CreateNewsRepository.scrolldown(driver, 1, 0);
			
			CreateNewsRepository.title(driver).sendKeys(title);
			CreateNewsRepository.title(driver).sendKeys(Keys.TAB);
			
			CreateNewsRepository.summary(driver).sendKeys(summary);
			CreateNewsRepository.scrolldown(driver, 1, 0);
			CreateNewsRepository.summary(driver).click();
			
			CreateEventRepository.content(driver).sendKeys(finalcontent);
			driver.switchTo().window(driver.getWindowHandle());
			
			
			CreateNewsRepository.scrolldown(driver, 1, 0);
			Thread.sleep(1000);
			CreateNewsRepository.publish(driver);
			PageSynchronize.loadPageBeforeNaviating(driver);
			CreateNewsRepository.home(driver);
			CreateNewsRepository.validate(driver,titlenumber);
			Thread.sleep(4000);
			obj.Takesnap(driver, Config.SnapShotPath() + "/CreateNews/News_Created.jpg");
			POM.logOut(driver);
			test.log(LogStatus.INFO, "Create News Completed");
			test.log(LogStatus.PASS, "Create News Successfull");
			logger.info("CreateNewsTest Successful");
			rep.endTest(test);
			rep.flush();
		} catch (Exception e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
			test.log(LogStatus.FAIL, "Creating News");
			System.out.println(e.getMessage());
		}

	}
  
//Generate math random
	public static int generateNumber() {
		int aNumber = 0;
		aNumber = (int) ((Math.random() * 90000) + 10000);
		return((aNumber));
	}
	
  @AfterTest
  public void tearDown(){
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  rep.endTest(test);
	  rep.flush();
	  driver.quit();
  }
  
  @AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		rep.endTest(test);
	}
}
