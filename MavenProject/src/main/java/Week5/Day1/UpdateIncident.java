package Week5.Day1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateIncident extends BaseClassServiceNow {
	@Test
	public void updateInstance() throws InterruptedException {

		driver.findElement(By.xpath("(//div[text()='Open'])[1]")).click();
		Thread.sleep(5000);
		driver.switchTo().frame("gsft_main");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Press')]/following-sibling::input"))
				.sendKeys(attribute + Keys.ENTER);
		System.out.println("Updated instance is :" + attribute);
		Thread.sleep(3000);
		WebElement webtable1 = driver.findElement(By.xpath("//tbody[@class='list2_body']"));
		List<WebElement> rows1 = webtable1.findElements(By.tagName("tr"));
		// System.out.println(rows1.size());
		List<WebElement> columns1 = rows1.get(0).findElements(By.tagName("td"));
		// System.out.println(columns1.size());
		columns1.get(2).findElement(By.tagName("a")).click();
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='incident.urgency']")));
		sel.selectByIndex(0);
		String text2 = sel.getOptions().get(0).getText();
		System.out.println(text2);
		Select sel1 = new Select(driver.findElement(By.xpath("//select[@name='incident.state']")));
		sel1.selectByIndex(1);
		String text3 = sel1.getOptions().get(1).getText();
		System.out.println(text3);
		if (text2.contains("High") && text3.contains("Progress")) {
			System.out.println("Urgency and State are updated correctly");
		} else {
			System.out.println("Urgency and State are not updated");
		}

	}

}
