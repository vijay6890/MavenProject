package tc_parametrization;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Week5.Day2.ReadExcel;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLead extends BaseClass {

	@Test(dataProvider="sendData")
	public void runCreateLead(String comp,String fname,String lname,String ph) {
		
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(comp);
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(fname);
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lname);
		driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(ph);
		driver.findElement(By.name("submitButton")).click();
	
  }
	@DataProvider
	public String[][] sendData() {
	String[][] data=new String[2][4];	
	
	data[0][0]="RR";
	data[0][1]="Marudhu";
	data[0][2]="R";
	data[0][3]="98";
	
	data[1][0]="Atos";
	data[1][1]="Ramya";
	data[1][2]="R";
	data[1][3]="981";
	
	return data;
	
	

	}
		
}






