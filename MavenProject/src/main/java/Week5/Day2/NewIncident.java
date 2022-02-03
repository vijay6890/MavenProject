package Week5.Day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewIncident extends BaseClassServiceNow {

	@Test
	public void newIncidet() throws InterruptedException {

		driver.findElement(By.xpath("(//div[text()='Create New'])[1]")).click();
		Thread.sleep(5000);
		driver.switchTo().frame("gsft_main");

		driver.findElement(By.id("lookup.incident.caller_id")).click();
		driver.switchTo().defaultContent();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> caller = new ArrayList<String>(windowHandles);
		driver.switchTo().window(caller.get(1));
		driver.findElement(By.linkText("Abel Tuter")).click();
		driver.switchTo().window(caller.get(0));
		Thread.sleep(3000);
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//span[@class='icon icon-lightbulb']")).click();
		driver.switchTo().defaultContent();
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> caller1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(caller1.get(1));
		driver.findElement(By.linkText("Issue with a web page")).click();
		driver.switchTo().window(caller1.get(0));
		driver.switchTo().frame("gsft_main");
		 attribute = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println(attribute);
		driver.findElement(By.xpath("(//button[text()='Submit'])[2]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Press')]/following-sibling::input"))
				.sendKeys(attribute + Keys.ENTER);
		WebElement webtable = driver.findElement(By.xpath("//table[@id='incident_table']//tbody"));
		List<WebElement> rows = webtable.findElements(By.tagName("tr"));
		System.out.println(rows.size());
		List<WebElement> columns = rows.get(0).findElements(By.tagName("td"));
		String text = columns.get(2).getText();
		if (text.equals(attribute)) {
			System.out.println(text+"incident is created Successfully");
		} else {
			System.out.println("Incident is not created Successfully");
		}

	}
}
