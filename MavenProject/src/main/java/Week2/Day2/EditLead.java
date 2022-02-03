package Week2.Day2;

import java.sql.Driver;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditLead {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		WebElement eleusername = driver.findElement(By.id("username"));
		eleusername.sendKeys("demosalesmanager");
		WebElement elepassword = driver.findElement(By.id("password"));
		elepassword.sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.linkText("Find Leads")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//label[text()='First name:'])[3]/following-sibling::div/input"))
				.sendKeys("Veeravel");
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		Thread.sleep(4000);

	 WebElement table = driver.findElement(By.xpath("(//table[@class='x-grid3-row-table'])[1]/tbody"));
	 Thread.sleep(3000);
	 List<WebElement> rows = table.findElements(By.tagName("tr"));
	 Thread.sleep(3000);
	 System.out.println(rows.size());
		                  List<WebElement> columns = rows.get(0).findElements(By.tagName("td"));
		                  Thread.sleep(3000);
		                  System.out.println(columns.size());		                  
		                  columns.get(2).click();
	
		                       
		   
		
		String title = driver.getTitle();
		System.out.println("Title is" + title);

		driver.findElement(By.linkText("Edit")).click();
		driver.findElement(By.id("updateLeadForm_companyName")).sendKeys("Testleaf123");
		driver.findElement(By.xpath("//input[@value='Update']")).click();
		String text = driver.findElement(By.xpath("//span[contains(text(),'Testleaf')]")).getText();
		if (text.contains("TestLeaf")) {
			System.out.println("The updated Text is :" + text);
		} else {
			System.out.println("Text is not Present");
		}

	}

}
