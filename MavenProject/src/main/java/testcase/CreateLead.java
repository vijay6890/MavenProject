package testcase;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Week5.Day2.ReadExcel;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLead {
  @Test(dataProvider = "senddata")
	public void runCreateLead(String company,String Fname,String Lname,String no) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(company);
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(Fname);
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(Lname);
		driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(no);
		driver.findElement(By.name("submitButton")).click();
		driver.close();
  }
		@DataProvider
		public  String[][] senddata() throws IOException
		{
			ReadExcel re=new ReadExcel();
	String[][] data = re.ReadData();
	return data;
		}

}






