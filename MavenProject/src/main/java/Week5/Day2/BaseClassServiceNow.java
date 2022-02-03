package Week5.Day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClassServiceNow {
	  public ChromeDriver driver;
	   static String attribute;
	 
	  @BeforeMethod
	  @Parameters (
			  {"url","username","password"})
	public void Precondition(String url,String username,String password) throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys(password);
		driver.findElement(By.id("sysverb_login")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("Incident",Keys.ENTER);
	
	}
	  @AfterMethod
	public void postCondition() 
	{
		driver.close();
	}
	

}
