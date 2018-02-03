package automationFramework;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
import lib.DriverAndObjectDetails;
import lib.DriverAndObjectDetails.DriverName;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.PageSynchronize;
import lib.Takescreenshot;
import objectRepository.BootstrapAlerts;
import objectRepository.POM;
import objectRepository.ServicealertRepository;

public class CreateServiceAlert {
	Configuration Config = new Configuration();
	public static WebDriver driver;
	Logger logger = Logger.getLogger("Create_Service_Alert");
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	static Takescreenshot obj = new Takescreenshot();

	@Test
	public void f() throws IOException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.MILLISECONDS);
		PropertyConfigurator.configure("Log4j.properties");
		driver.get(Config.getApplicationUrl());
		driver.manage().window().maximize();
		try {
			logger.info("Browser Opened");
			logger.info("Test Case Started");
			test = rep.startTest("createAlertTest");
			ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
			POM.emailtextbox(driver).sendKeys(excel.getData(0, 1, 0));
			POM.passwordbox(driver).sendKeys(excel.getData(0, 1, 1));
			POM.signin(driver);
			test.log(LogStatus.INFO, "Ending Sign in");
			test.log(LogStatus.PASS, "PASSED Sign in");
			PageSynchronize.loadPageBeforeNaviating(driver);
			BootstrapAlerts.AlertsDismiss(driver);
			ServicealertRepository.servicealerts(driver);
			PageSynchronize.loadPageBeforeNaviating(driver);
			ServicealertRepository.createAlert(driver);
			PageSynchronize.loadPageBeforeNaviating(driver);
			ServicealertRepository.title(driver).sendKeys(excel.getData(1, 1, 0));
			ServicealertRepository.reference(driver).sendKeys(excel.getData(1, 4, 0));
			ServicealertRepository.description(driver).sendKeys(excel.getData(1, 10, 0));
			ServicealertRepository.submitAlert(driver);
			ServicealertRepository.messageDisplayed(driver);
			test.log(LogStatus.INFO, "Ending Creating Service Alert");
			test.log(LogStatus.PASS, "PASSED Creating Service Alert");
			PageSynchronize.loadPageBeforeNaviating(driver);
			ServicealertRepository.notification(driver);
			PageSynchronize.loadPageBeforeNaviating(driver);
			ServicealertRepository.notificationlink(driver);
			Thread.sleep(10000);
			test.log(LogStatus.INFO, "Ending Newly Created Service Alert");
			test.log(LogStatus.PASS, "PASSED Newly Created Service Alert");
			obj.Takesnap(driver,
					Config.SnapShotPath() + "/Notification for newly Created Service Alert/Alert_Dispalyed.jpg");
			POM.logOut(driver);
			test.log(LogStatus.INFO, "Ending createAlertTest");
			test.log(LogStatus.PASS, "PASSED createAlertTest");
			logger.info("createAlertTest Successful");
			rep.endTest(test);
			rep.flush();
		} catch (Exception e) {
			logger.info(e.getMessage());
			
			
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			//Assert.assertTrue(false, e.getMessage());
			test.log(LogStatus.FAIL, "Creating Alert");
			System.out.println(e.getMessage());
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
	}

	@AfterTest
	public void tearDown() {
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
