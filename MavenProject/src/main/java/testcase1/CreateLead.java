package testcase1;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Week5.Day2.ReadExcel;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLead extends BaseClass {

	@Test
	public void runCreateLead() {
		
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys("FIS");
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Raja");
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys("R");
		driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys("98");
		driver.findElement(By.name("submitButton")).click();
	
  }
		
}






