package lib;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import Utility.Configuration;

public class DriverAndObjectDetails {
	public static DesiredCapabilities dc;
	public static WebDriver wd;
	static DriverName driver;
	static Configuration Config = new Configuration();

	public enum DriverName {
		IE, CH, FF, SF
	}

	public DriverAndObjectDetails(DriverName driver) {
		DriverAndObjectDetails.driver = driver;
		System.out.println(driver);
	}

	public static WebDriver CreateDriver() {
		try {
			switch (driver) {
			case IE:
				dc = new DesiredCapabilities();
				System.setProperty("webdriver.ie.driver", Config.getIEPath());
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				dc.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				wd = new InternetExplorerDriver(dc);
				wd.manage().window().maximize();
				return wd;
			case CH:
				ChromeOptions chromeOptions = new ChromeOptions();
				// dc = new DesiredCapabilities();
				System.setProperty("webdriver.chrome.driver", Config.getChromePath());
				chromeOptions.addArguments("--start-maximized");
				chromeOptions.addArguments("--disable-web-security");
				//chromeOptions.addArguments("--no-proxy-server");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				chromeOptions.setExperimentalOption("prefs", prefs);
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				setUniversalProxy(capabilities);
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				wd = new ChromeDriver(capabilities);
				return wd;
			case FF:
				ProfilesIni prof = new ProfilesIni();			
				FirefoxProfile ffProfile= prof.getProfile("default");
				ffProfile.setAcceptUntrustedCertificates(true);
				ffProfile.setAssumeUntrustedCertificateIssuer(false);
				System.setProperty("webdriver.gecko.driver", "D:\\gecko driver\\geckodriver-v0.19.1\\geckodriver.exe");
				DesiredCapabilities capability = DesiredCapabilities.firefox();
				setUniversalProxyFF(capability, ffProfile);
				wd = new FirefoxDriver(capability);
				wd.manage().window().maximize();
				return wd;
			default:
				break;
			}
			return null;
		} catch (Exception e) {
			return null;

		}
	}
	
	
	public static DesiredCapabilities setUniversalProxy(DesiredCapabilities capa){
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("172.31.17.167:8080");
		proxy.setSslProxy("172.31.17.167:8080");
		capa.setCapability("proxy", proxy);
		return capa;
	}
	public static DesiredCapabilities setUniversalProxyFF(DesiredCapabilities capa,FirefoxProfile ffProfile){
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("172.31.17.167:8080");
		proxy.setSslProxy("172.31.17.167:8080");
		capa.setCapability("proxy", proxy);
		return capa;
	}

}
