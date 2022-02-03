package Week2.Day2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnListboxes {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/Dropdown.html");
		driver.manage().window().maximize();
		Select Training = new Select(driver.findElement(By.id("dropdown1")));
		Training.selectByIndex(2);
		Select Program = new Select(driver.findElement(By.name("dropdown2")));
		Program.selectByVisibleText("UFT/QTP");
		Select Program1 = new Select(driver.findElement(By.id("dropdown3")));
		Program1.selectByValue("3");
		driver.findElement(By.xpath("//option[contains(text(),'sendKeys')]/..")).sendKeys("Loadrunner");
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//option[text()='Selenium'])[6]")).click();
		driver.findElement(By.xpath("(//option[text()='UFT/QTP'])[6]")).click();
		Select Sel = new Select(driver.findElement(By.xpath("//select[@class='dropdown']")));
		List<WebElement> count = Sel.getOptions();
		int size = count.size();
		System.out.println("Total No of Values in the Dropdown " + size);
		System.out.println("List of Values are :");

		for (int i = 0; i < size; i++) {
			System.out.println(count.get(i).getText());

		}

		// driver.findElement(By.xpath("//option[text()='Select your
		// programs']/following-sibling::option[2]")).sendKeys("Appium");

	}

}
