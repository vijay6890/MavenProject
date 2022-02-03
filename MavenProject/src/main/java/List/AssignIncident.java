package List;

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

public class AssignIncident {
	
public static void main(String[] args) throws InterruptedException {
	
WebDriverManager.chromedriver().setup();
ChromeDriver driver = new ChromeDriver();
driver.get("https://dev87778.service-now.com/navpage.do");
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.switchTo().frame("gsft_main");
driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("Sra@1234");
driver.findElement(By.id("sysverb_login")).click();
driver.switchTo().defaultContent();
Thread.sleep(4000);
driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("Incident",Keys.ENTER);
String 	text2=null;
	//	driver.findElement(By.xpath("(//div[text()='Incidents'])[1]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//div[text()='Open'])[1]")).click();
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//span[contains(text(),'Press')]/following-sibling::input"))
		.sendKeys("INC0000027"+ Keys.ENTER);
		
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
		Thread.sleep(2000);
driver.findElement(By.xpath("(//input[@placeholder='Search'])[1]")).sendKeys("Software",Keys.ENTER);
Thread.sleep(3000);
driver.findElement(By.linkText("Software")).click();
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> lst1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(lst1.get(0));
		driver.switchTo().defaultContent();
		driver.switchTo().frame("gsft_main");

		driver.findElement(By.xpath("//*[@id='activity-stream-textarea']")).sendKeys("Veeravel");
		String attribute1 = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		/*if (attribute.equals(attribute1)) {
			System.out.println("Incident has been updated correct");
		} else {
			*/System.out.println("Incident captured as wrong");
	/*	if (text2.equalsIgnoreCase("Software")) {
			System.out.println("Assignment Group isupdated as Software");
		} else {
			System.out.println("Assignment group is not updated as Software");
		}
	}

}*/
}
}

