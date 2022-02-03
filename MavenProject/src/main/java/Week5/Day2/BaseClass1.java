package Week5.Day2;

import java.io.IOException;
import java.time.Duration;

import org.checkerframework.framework.qual.PreconditionAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass1 {

	public ChromeDriver driver;
	public String fileName;
	
	 @DataProvider
	  public String[][] sendData() throws IOException {
		// TODO Auto-generated method stub
		   return ReadExcel1.captureData(fileName);
	}


	@BeforeMethod
	@Parameters({"url","username","password"})
	public void precondition(String url, String uname, String pwd) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();

	}

	@AfterMethod
	public void postcondition() {
		driver.close();
	}

}
