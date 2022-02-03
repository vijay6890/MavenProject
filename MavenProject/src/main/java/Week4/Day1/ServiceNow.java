package Week4.Day1;

import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
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
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("Incident");
		Thread.sleep(3000);
		
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//button[text()='New']")).click();
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
		String attribute = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
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
			System.out.println("incident is created Successfully");
		} else {
			System.out.println("Incident is not created Successfully");
		}

		WebElement findElement = driver.findElement(By.xpath("//a[@class='linked formlink']"));
		File src = findElement.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/pic2.png");
		FileUtils.copyFile(src, dest);

	}

}
