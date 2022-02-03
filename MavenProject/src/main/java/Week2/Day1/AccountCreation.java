package Week2.Day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AccountCreation {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		 WebElement eleusername = driver.findElement(By.xpath("//input[@id='username']"));
		eleusername.sendKeys("demosalesmanager");
		WebElement elepassword = driver.findElement(By.xpath("//input[@id='password']"));
		elepassword.sendKeys("crmsfa");
		driver.findElement(By.xpath("//input[@class='decorativeSubmit']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'CRM')]")).click();
		driver.findElement(By.xpath("//a[text()='Leads']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Create')]")).click();
		driver.findElement(By.xpath("//input[@id='createLeadForm_companyName']")).sendKeys("TestLeaf");
	    driver.findElement(By.xpath("//input[@id='createLeadForm_firstName']")).sendKeys("veeravel");
	    driver.findElement(By.xpath("//input[@id='createLeadForm_lastName']")).sendKeys("Vel");
	    driver.findElement(By.xpath("//input[@name='submitButton']")).click();

	}
}
