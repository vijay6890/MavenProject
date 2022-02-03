package Week2.Day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropdownHandling {
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
		driver.findElement(By.linkText("Create Lead")).click();
		 WebElement elesource=  driver.findElement(By.id("createLeadForm_dataSourceId"));
		 Select dropdown=new Select(elesource);
		 dropdown.selectByVisibleText("Direct Mail");
		 WebElement eleindustry=  driver.findElement(By.id("createLeadForm_industryEnumId"));
		 Select dropdown1=new Select(eleindustry);
		 dropdown1.selectByIndex(7);
		 WebElement eleowner=  driver.findElement(By.id("createLeadForm_ownershipEnumId"));
		 Select dropdown2=new Select(eleowner);
		 dropdown2.selectByValue("OWN_PARTNERSHIP");	
		   driver.findElement(By.id("createLeadForm_marketingCampaignId")).sendKeys("Affiliated Sites");
		 
		 
		 }
}
