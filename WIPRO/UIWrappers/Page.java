package UIWrappers;

import org.testng.annotations.*;

import Config.Base;
import Config.BrowserSetup;
import static UIWrappers.UIObjects.*;

public class Page extends Base{

	public static void startTestWithSIB() throws InterruptedException
	{
		new BrowserSetup();
		driver.manage().window().maximize();
		openUrl(url);
		waitForPageLoad();
		thread();
	}
	
	@BeforeSuite
	public static void openBrowser() throws Exception{
		startTestWithSIB();
	}
}




