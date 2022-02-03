package Week2.Day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForce {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("  https://www.salesforce.com/in/form/signup/freetrial-sales/?d=70130000000Enyk");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		Thread.sleep(2000);
		driver.findElement(By.name("UserFirstName")).sendKeys("Veeravel");
		driver.findElement(By.name("UserLastName")).sendKeys("V");
		driver.findElement(By.name("UserEmail")).sendKeys("veeravelvl@gmail.com");
		driver.findElement(By.name("CompanyName")).sendKeys("CTS");
		driver.findElement(By.name("UserPhone")).sendKeys("9789725676");
		Select Title = new Select(driver.findElement(By.name("UserTitle")));
		Title.selectByValue("Marketing_PR_Manager_AP");
		Select employees = new Select(driver.findElement(By.name("CompanyEmployees")));
		employees.selectByValue("75");
		Select country = new Select(driver.findElement(By.name("CompanyCountry")));
		country.selectByValue("AS");
		driver.findElement(By.name("UserFirstName")).click();
		driver.findElement(By.xpath("//input[@id='SubscriptionAgreement']/following-sibling::div")).click();
		// driver.close();

	}

}
