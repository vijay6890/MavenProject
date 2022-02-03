package Week5.Day2;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class CreateLead extends BaseClass1{
	
	@BeforeTest
	public void setvalue() {
	fileName="CreateLead";

	}
	@Test(dataProvider = "sendData")
	public void runCreateLead(String company,String Fname,String Lname,String phone ) {
		
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(company);
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(Fname);
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(Lname);
		driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(phone);
		driver.findElement(By.name("submitButton")).click();

}
 
}






