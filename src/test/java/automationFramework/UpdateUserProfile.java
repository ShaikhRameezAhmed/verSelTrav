package automationFramework;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
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
import objectRepository.POM;
import objectRepository.Profile;


public class UpdateUserProfile {
	Configuration Config = new Configuration();
	public static WebDriver driver;
	Logger logger = Logger.getLogger("Create_Profile");
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	static Takescreenshot obj = new Takescreenshot();
	
	@Test
	  public void Test() throws IOException, InterruptedException {
		  PropertyConfigurator.configure("Log4j.properties");
			driver.get(Config.getApplicationUrl());
			driver.manage().window().maximize();
			//try {
			logger.info("Browser Opened");
			logger.info("Test Case Started");
			test = rep.startTest("CreateProfileTest");
			ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
			POM.emailtextbox(driver).sendKeys(excel.getData(0, 1, 0));
			POM.passwordbox(driver).sendKeys(excel.getData(0, 1, 1));
			POM.signin(driver);
			logger.info("Sign in Completed");
			PageSynchronize.loadPageBeforeNaviating(driver);
			logger.info("Home Page");
			Profile.Settings(driver);
			PageSynchronize.loadPageBeforeNaviating(driver);
			logger.info("Settings Page");
			Profile.FirstName(driver).sendKeys(excel.getData(4, 1, 0));
			Profile.LastName(driver).sendKeys(excel.getData(4, 4, 0));
			Profile.SAVEBtn(driver);
			PageSynchronize.loadPageBeforeNaviating(driver);
			Profile.ProfileName(driver);
			obj.Takesnap(driver, Config.SnapShotPath() + "/Profile Created/Profile information saved successfully.jpg");
			Profile.Confirm(driver);
			Thread.sleep(10000);
			test.log(LogStatus.INFO, "Ending CreateProfileTest");
			test.log(LogStatus.PASS, "PASSED CreateProfileTest");
			obj.Takesnap(driver, Config.SnapShotPath() + "/Profile Created/Profile_Dispalyed.jpg");
			POM.logOut(driver);
			test.log(LogStatus.INFO, "Ending CreateProfileTest");
			test.log(LogStatus.PASS, "PASSED CreateProfileTest");
			logger.info("CreateProfileTest Successful");
			rep.endTest(test);
			rep.flush();
			/*}catch (Exception e) {
				logger.info(e.getMessage());
				test.log(LogStatus.FAIL, e.getMessage());
				rep.endTest(test);
				rep.flush();
				Assert.assertTrue(false, e.getMessage());
				test.log(LogStatus.FAIL, "Updating User Profile");
				System.out.println(e.getMessage());
			}*/
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
}
