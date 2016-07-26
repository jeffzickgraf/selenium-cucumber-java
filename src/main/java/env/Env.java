package env;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionNotFoundException;


public class Env 
{
	public static RemoteWebDriver driver = null;
	static String browserName = null;
	static Boolean isMobile;
	
	public static String getBrowserName()
	{
		browserName = System.getProperty("browser"); 
		
		if(browserName == null)
			browserName = "ff";
		return browserName;
	}
	
	public static RemoteWebDriver CreateWebDriver(String browser)
	{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			System.out.println("Browser: " + browser);
			
			switch (browser.toLowerCase()) {
			case "mobileandroid":
				capabilities.setCapability("platformName","Android");
				capabilities.setCapability("deviceName", "C538899C");
				isMobile = true;
				break;
			case "mobileios":
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("deviceName", "AA7EEEAADD92242C665D2807B538BDACFAA5A0DB"); //Jeff iPhone
				isMobile = true;
				break;
			case "ff":
			case "firefox":
				capabilities.setCapability("platformName", "Windows");
				capabilities.setCapability("platformVersion", "8.1");
				capabilities.setCapability("browserName", "Firefox");
				capabilities.setCapability("browserVersion", "45");
				capabilities.setCapability("resolution", "1440x900");
				capabilities.setCapability("location", "US East");				
				isMobile = false;
				break;

			case "ch":
			case "chrome":
				capabilities.setCapability("platformName", "Windows");
				capabilities.setCapability("platformVersion", "8.1");
				capabilities.setCapability("browserName", "Chrome");
				capabilities.setCapability("browserVersion", "51");
				capabilities.setCapability("resolution", "1440x900");
				capabilities.setCapability("location", "US East");
				isMobile = false;
				break;

			case "ie" :
			case "internetexplorer":
				capabilities.setCapability("platformName", "Windows");
				capabilities.setCapability("platformVersion", "7");
				capabilities.setCapability("browserName", "Internet Explorer");
				capabilities.setCapability("browserVersion", "11");
				capabilities.setCapability("resolution", "1440x900");
				capabilities.setCapability("location", "US East");
				isMobile = false;
				break;

			case "safari":
				System.out.println("Safari not supported yet");				
				break;
				
			 default:
				 System.out.println("Invalid browser name "+browser);
				 System.exit(0);
					break;	
			}//switch
			
			
			if(isMobile)
			{
				capabilities.setCapability("browserName", "mobileOS");
				capabilities.setCapability("automationName", "PerfectoMobile");
			}
			
			capabilities.setCapability("user", System.getProperty("PerfectoUser"));
			capabilities.setCapability("password", System.getProperty("PerfectoPassword"));			
			capabilities.setCapability("newCommandTimeout", "120");			
			capabilities.setCapability("scriptName", "Selenium Responsive");
			
			try {
				driver = new RemoteWebDriver (new URL(System.getProperty("PerfectoCloud") + "/nexperience/perfectomobile/wd/hub"), capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(isMobile)
			{
				RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
				Map<String,String> params = new HashMap<String,String>();
				params.put("name", "WEBVIEW");
				executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
			}
			
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
			
			return driver;
        }
	}
