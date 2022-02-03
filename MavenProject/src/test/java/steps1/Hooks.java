package steps1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks extends BaseClass1 {
	@Before
	public void preCondition() {
		
		WebDriverManager.chromedriver().setup(); 
		driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("http://leaftaps.com/opentaps/");
	WebElement eleusername = driver.findElement(By.id("username"));
	eleusername.sendKeys("DemoSalesManager");
	WebElement elepassword = driver.findElement(By.id("password"));
	elepassword.sendKeys("crmsfa");
	driver.findElement(By.className("decorativeSubmit")).click();
	
	   String actTitle = driver.getTitle();
	if(actTitle.contains("Leaftaps"))
	{
		System.out.println("Title is matching");
	}
	else
	{
		System.out.println("Title is not matching");
	}
	driver.findElement(By.linkText("CRM/SFA")).click();
	driver.findElement(By.linkText("Leads")).click();
}
	
	@After
	public void postCondition()
	{
		driver.close();
	}

}
