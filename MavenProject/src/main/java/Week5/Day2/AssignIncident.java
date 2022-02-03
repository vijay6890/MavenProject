package Week5.Day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignIncident extends BaseClassServiceNow {
	@Test
	public void assignIncident() throws InterruptedException {
String 	text2=null;
	//	driver.findElement(By.xpath("(//div[text()='Incidents'])[1]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//div[text()='Open'])[1]")).click();
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//span[contains(text(),'Press')]/following-sibling::input"))
		.sendKeys(attribute + Keys.ENTER);
		
		WebElement webtable1 = driver.findElement(By.xpath("//table[@id='incident_table']//tbody"));
		List<WebElement> rows1 = webtable1.findElements(By.tagName("tr"));
		//System.out.println(rows1.size());
		List<WebElement> columns1 = rows1.get(0).findElements(By.tagName("td"));
		//System.out.println(columns1.size());
		columns1.get(2).findElement(By.tagName("a")).click();
		driver.findElement(By.name("lookup.incident.assignment_group")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lst = new ArrayList<String>(windowHandles);
		driver.switchTo().window(lst.get(1));
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//span[@class='icon-vcr-right'])[4]")).click();
		Thread.sleep(1000);
		WebElement table = driver.findElement(By.xpath("//tbody[@class='list2_body']"));
		Thread.sleep(3000);
		List<WebElement> rows2 = table.findElements(By.tagName("tr"));
		Thread.sleep(2000);
		//System.out.println(rows2.size());
		outerloop: for (int i = 0; i < rows2.size(); i++) {
			List<WebElement> columns2 = rows2.get(i).findElements(By.tagName("td"));
			// Thread.sleep(3000);
			int size = columns2.size();
			// System.out.println("Number of Cells in Row"+i+"are"+size);
			for (int j = 0; j < size; j++) {
			 text2 = columns2.get(j).getText();
				// System.out.println(text2);
				if (text2.equalsIgnoreCase("Software")) {
					int currentrow = i + 1;
					driver.findElement(
							By.xpath("//tbody[@class='list2_body']/tr" + "[" + currentrow + "]" + "/td[3]/a")).click();
					break outerloop;
				}

			}

		}
		Thread.sleep(3000);
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> lst1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(lst1.get(0));
		driver.switchTo().defaultContent();
		driver.switchTo().frame("gsft_main");

		driver.findElement(By.xpath("//*[@id='activity-stream-textarea']")).sendKeys("Veeravel");
		String attribute1 = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		if (attribute.equals(attribute1)) {
			System.out.println("Incident has been updated correct");
		} else {
			System.out.println("Incident captured as wrong");
		}
		if (text2.equalsIgnoreCase("Software")) {
			System.out.println("Assignment Group is updated as Software");
		} else {
			System.out.println("Assignment group is not updated as Software");
		}
	}

}
