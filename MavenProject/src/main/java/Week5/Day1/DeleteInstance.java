package Week5.Day1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteInstance extends BaseClassServiceNow {

	@Test
	public void runDelete() throws InterruptedException {

		driver.findElement(By.xpath("(//div[text()='Open'])[1]")).click();
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//span[contains(text(),'Press')]/following-sibling::input"))
				.sendKeys(attribute + Keys.ENTER);
		System.out.println("instance to be Delete :" + attribute);
		WebElement webtable1 = driver.findElement(By.xpath("//table[@id='incident_table']//tbody"));
		List<WebElement> rows1 = webtable1.findElements(By.tagName("tr"));
		// System.out.println(rows1.size());
		List<WebElement> columns1 = rows1.get(0).findElements(By.tagName("td"));
		// System.out.println(columns1.size());
		columns1.get(2).findElement(By.tagName("a")).click();
		String attribute1 = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println(attribute1);
		driver.findElement(By.xpath("(//button[text()='Delete'])[1]")).click();
		driver.findElement(By.xpath("(//button[text()='Delete'])[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Press')]/following-sibling::input"))
				.sendKeys(attribute + Keys.ENTER);
		String records = driver.findElement(By.xpath("//tbody[@class='list2_body']//td")).getText();
		// System.out.println(records);
		if (records.contains("No records")) {
			System.out.println("Record has been Deleted Successfully");
		} else {
			System.out.println("Record has not deleted");
		}
	}

}
