package Week2.Day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAccount {
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
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.linkText("Create Contact")).click();
		driver.findElement(By.id("firstNameField")).sendKeys("Veeravel");
		driver.findElement(By.id("lastNameField")).sendKeys("v");
		driver.findElement(By.id("createContactForm_firstNameLocal")).sendKeys("Raja");
		driver.findElement(By.id("createContactForm_lastNameLocal")).sendKeys("kumar");
		driver.findElement(By.id("createContactForm_departmentName")).sendKeys("Sales Tax");
		driver.findElement(By.id("createContactForm_description")).sendKeys("Sales Tax offer");
		driver.findElement(By.id("createContactForm_primaryEmail")).sendKeys("veeravelvl@gmail.com");
		Select state = new Select(driver.findElement(By.id("createContactForm_generalStateProvinceGeoId")));
		state.selectByVisibleText("New York");
		driver.findElement(By.name("submitButton")).click();
		driver.findElement(By.linkText("Edit")).click();
		driver.findElement(By.id("updateContactForm_description")).clear();
		driver.findElement(By.name("importantNote")).sendKeys("Verification are Completed");
		driver.findElement(By.xpath("//input[@value='Update']")).click();
		String title = driver.getTitle();
		System.out.println("Title of the Page is :" + title);

	}
}
