package FeaturesStep;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClassService extends AbstractTestNGCucumberTests {
public static ChromeDriver driver;
public static String attribute;
	@BeforeMethod
	public void preCondition() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://dev87778.service-now.com/navpage.do");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("Sra@1234");
		driver.findElement(By.id("sysverb_login")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("Incident",Keys.ENTER);
	
	}
	
	@AfterMethod
	public void postCondition() {
		driver.close();

	}
}
