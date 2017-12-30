package automationFramework;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utility.Configuration;
import lib.BeforeDriverTest;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.PageSynchronize;
import lib.Takescreenshot;
import objectRepository.CreateNewsRepository;
import objectRepository.POM;

public class CreateNews extends BeforeDriverTest {
	Configuration Config = new Configuration();
	public static WebDriver driver;
	Logger logger = Logger.getLogger("Create_Service_Alert");
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	static Takescreenshot obj = new Takescreenshot();
  
	/*@BeforeTest
	  @Parameters("browsers")
		public void beforeTest(String browser) {
		System.setProperty("https.proxyHost", " 172.31.17.167");
        System.setProperty("https.proxyPort", "8080");
			if (browser.equalsIgnoreCase("Chrome")) {
				driver = new DriverAndObjectDetails(DriverName.CH).CreateDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				driver = new DriverAndObjectDetails(DriverName.IE).CreateDriver();
			} else {
				driver = new DriverAndObjectDetails(DriverName.FF).CreateDriver();
			}
		}*/

  @Test
	public void f() throws IOException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.MILLISECONDS);
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
			CreateNewsRepository.newsTab(driver);
			PageSynchronize.loadPageBeforeNaviating(driver);
			CreateNewsRepository.newsLink(driver);
			test.log(LogStatus.INFO, "Clicked on News Link");
			test.log(LogStatus.PASS, "Clicked on News Link");
			PageSynchronize.loadPageBeforeNaviating(driver);
			CreateNewsRepository.createArticle(driver);
			PageSynchronize.loadPageBeforeNaviating(driver);
			CreateNewsRepository.scrolldown(driver, 1, 0);
			// Thread.sleep(1000);
			CreateNewsRepository.title(driver).sendKeys(excel.getData(2, 1, 0));
			CreateNewsRepository.scrolldown(driver, 1, 0);
			CreateNewsRepository.summary(driver).sendKeys(excel.getData(2, 4, 0));
			CreateNewsRepository.summary(driver).click();
			// Clicking oustide the page
			// driver.findElement(By.xpath("//html")).click();
			// CreateNewsRepository.scrolldown(driver,1,0);
			CreateNewsRepository.summary(driver).sendKeys(Keys.TAB + "Creating News for #12346");
			CreateNewsRepository.scrolldown(driver, 1, 0);
			Thread.sleep(1000);
			CreateNewsRepository.publish(driver);
			PageSynchronize.loadPageBeforeNaviating(driver);
			CreateNewsRepository.home(driver);
			CreateNewsRepository.scrolldown(driver, 1, 0);
			CreateNewsRepository.validate(driver);
			Thread.sleep(2000);
			obj.Takesnap(driver, Config.SnapShotPath() + "/CreateNews/News_Created.jpg");
			//POM.logOut(driver);
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
  
 /* @AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		rep.endTest(test);
	}*/

	/*@AfterTest
	public void afterTest() {
		rep.endTest(test);
		rep.flush();
		driver.quit();
	}*/
}
