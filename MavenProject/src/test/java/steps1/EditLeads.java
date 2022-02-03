package steps1;

import org.openqa.selenium.By;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EditLeads extends BaseClass1 {
	
	
	
	@Then("Click on Phone tab")
	public void clickPhoneTab() {
		driver.findElement(By.xpath("//span[text()='Phone']")).click();

	}
	@Given("Enter the Phone number as {string}")
	public void enterPhone(String phoneno) {
		driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(phoneno);
	}
	@When("Click on {string} Button")	
	public void findLeadsButton(String Find)
		{
			driver.findElement(By.xpath("//button[text()='"+Find+"']")).click();
		}
	@Then("Click on First row")
	public void firstRow() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
	}
	
	@Then("Click on Edit Button")
	public void edit()
	{
		driver.findElement(By.linkText("Edit")).click();
	}
	
	@Then("Modify the CompanyName as {string}")
	public void companyName(String Company)
	{
		driver.findElement(By.id("updateLeadForm_companyName")).sendKeys(Company);
	}
	@Then("Click on Submit Button")
	public void submit()
	{
		driver.findElement(By.name("submitButton")).click();
	}
	

	

}
