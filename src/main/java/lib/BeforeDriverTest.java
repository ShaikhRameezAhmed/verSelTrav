package lib;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lib.DriverAndObjectDetails.DriverName;

public class BeforeDriverTest {
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	public static WebDriver driver;
	
	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		rep.endTest(test);
	}
	
	@BeforeTest
	  @Parameters("browsers")
		public void beforeTest(String browser) {
		System.setProperty("https.proxyHost", " 172.31.17.167");
      System.setProperty("https.proxyPort", "8080");
			if (browser.equalsIgnoreCase("Chrome")) {
				new DriverAndObjectDetails(DriverName.CH);
				driver = DriverAndObjectDetails.CreateDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				new DriverAndObjectDetails(DriverName.IE);
				driver = DriverAndObjectDetails.CreateDriver();
			} else {
				new DriverAndObjectDetails(DriverName.FF);
				driver = DriverAndObjectDetails.CreateDriver();
			}
		}

	@AfterTest
	public void afterTest() {
		rep.endTest(test);
		rep.flush();
		driver.quit();
	}
}
