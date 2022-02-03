package Week2.Day1;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLeadTest {
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
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys("TestLeaf");
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys("veeravel");
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys("Vel");
		driver.findElement(By.id("createLeadForm_birthDate")).sendKeys("01/01/1987");
		driver.findElement(By.id("createLeadForm_primaryPhoneCountryCode")).clear();
		driver.findElement(By.id("createLeadForm_primaryPhoneCountryCode")).sendKeys("97");
		driver.findElement(By.id("createLeadForm_primaryPhoneAreaCode")).sendKeys("044");
		driver.findElement(By.id("createLeadForm_primaryPhoneExtension")).sendKeys("452");
		driver.findElement(By.id("createLeadForm_primaryEmail")).sendKeys("veeravelvl@gmail.com");
		driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys("9789725676");
		driver.findElement(By.id("createLeadForm_primaryPhoneAskForName")).sendKeys("9789725676");
		driver.findElement(By.id("createLeadForm_primaryWebUrl")).sendKeys("www.google.com");
		driver.findElement(By.id("createLeadForm_generalToName")).sendKeys("Veeravel");
		driver.findElement(By.id("createLeadForm_generalAddress1")).sendKeys("Anuppanadi");
		driver.findElement(By.id("createLeadForm_generalCity")).sendKeys("Madurai");
		driver.findElement(By.id("createLeadForm_generalPostalCode")).sendKeys("625009");
		driver.findElement(By.id("createLeadForm_generalPostalCodeExt")).sendKeys("45");
		driver.findElement(By.id("createLeadForm_generalAttnName")).sendKeys("Rajee");
		Select State = new Select(driver.findElement(By.id("createLeadForm_generalStateProvinceGeoId")));
		State.selectByVisibleText("Alabama");
		Select Country = new Select(driver.findElement(By.id("createLeadForm_generalCountryGeoId")));
		Country.selectByVisibleText("Afghanistan");
		String text = driver.findElement(By.id("createLeadForm_firstName")).getText();
		System.out.println(text);
		driver.findElement(By.name("submitButton")).click();
		String text2 = driver.findElement(By.id("sectionHeaderTitle_leads")).getText();
		if (text2.equalsIgnoreCase("View Lead")) {
			System.out.println("View Lead is Present");
		} else {
			System.out.println("View Lead is not Present");
		}

	}
}
