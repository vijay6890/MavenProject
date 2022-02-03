package Week2.Day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Facebook {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://en-gb.facebook.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(By.linkText("Create New Account")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("firstname")).sendKeys("Veeravel");
		driver.findElement(By.name("lastname")).sendKeys("vel");
		driver.findElement(By.xpath("//div[contains(text(),'Mobile number')]/following-sibling::input"))
				.sendKeys("9789725676");
		driver.findElement(By.id("password_step_input")).sendKeys("Tharu@123");
		Select day = new Select(driver.findElement(By.id("day")));
		day.selectByValue("23");
		Select month = new Select(driver.findElement(By.id("month")));
		month.selectByValue("1");
		Select year = new Select(driver.findElement(By.id("year")));
		year.selectByValue("1987");
		driver.findElement(By.xpath("//label[text()='Female']")).click();

	}

}
