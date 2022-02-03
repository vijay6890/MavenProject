package Config;

import org.openqa.selenium.WebDriver;

public class Base {
	
	public static WebDriver driver =null;
	public static String browser = "CH";
	public static String url = "http://testing.mybusinesscontinuity.org/";
	//public static String url = "http://10.2.3.37:8006/";
	public static String driverlocation = "C:\\Selinium driver\\Chrome\\chromedriver.exe";

	public static String xlFilesLocation = "E:\\SIB\\Testing\\Automation\\Test_Data\\TestData_Tamizh.xls";
	public static String screenShotLocation = "E:\\SIB\\Testing\\Automation\\Screenshots";

		
	//	AUTOIT
	public static String thrImage = "D:\\TMedicine_WrkSpace\\SIB\\AutoIt_Scripts\\th.exe";
}

