package Config;

import java.io.File;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserSetup extends Base{
	

	public BrowserSetup()
	{
		
		/*try{			
			if(browser.equals("FF"))
			{
				driver = new FirefoxDriver();
			}
			else if(browser.equals("IE"))
			{
				System.setProperty("webdriver.ie.driver", driverlocation+"IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			else if(browser.equals("CH"))
			{
				System.setProperty("webdriver.chrome.driver", driverlocation);
				driver = new ChromeDriver();
			}
			else{
				System.out.println("Invalid browser selection");
			}
			
		}catch(WebDriverException e){
			e.printStackTrace();
		}*/
		
		try{			
			
			if(browser.equals("FF"))
			{
				 Proxy proxy = new Proxy();
		        proxy.setHttpProxy("localhost:8090");
		        proxy.setFtpProxy("localhost:8090");
		        proxy.setSslProxy("localhost:8090");
		         DesiredCapabilities capabilities = new DesiredCapabilities();
		         capabilities.setCapability(CapabilityType.PROXY, proxy);
				 driver = new FirefoxDriver();
				//String Xport = System.getProperty("display.props", ":99");
		       // System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox");
		       // FirefoxBinary firefoxBinary = new FirefoxBinary();
		       // firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);
		        //firefoxBinary.setTimeout(1200000); 
				//String xvfbPropsFile = System.getProperty("display.props",":99");
				//File firefoxPath = new File(System.getProperty("display.props", "/usr/bin/firefox"));
                //FirefoxBinary ffox = new FirefoxBinary(firefoxPath);
				//ffox.setEnvironmentProperty("DISPLAY" , xvfbPropsFile);
				//ffox.setTimeout(1200000); 
				
			
				
				//System.setProperty("webdriver.gecko.driver", "/usr/bin/firefox");
				
			}
			else if(browser.equals("IE"))
			{
				System.setProperty("webdriver.ie.driver", driverlocation+"IEDriverServer");
				driver = new InternetExplorerDriver(); 
				
			}
			else if(browser.equals("CH"))
			{
				ChromeOptions options = new ChromeOptions();
			    options.setBinary("C:\\Selinium driver\\Chrome\\");
				System.setProperty("webdriver.chrome.driver", driverlocation);
				driver = new ChromeDriver();
			}
			else{
				System.out.println("Invalid browser selection");
			}
			
		}catch(WebDriverException e){
			e.printStackTrace();
		}
	}

	

}