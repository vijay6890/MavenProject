package steps1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLead extends BaseClass1 {
	
	 @When("Click on {string} link") public void click_on_CRMSFAlink(String link)
	 { driver.findElement(By.linkText(link)).click(); }

	@Then("{string} Page should be displayed")
	public void verifyhomePage(String actpage) {
	  String exppage = driver.findElement(By.xpath("//div[contains(text(),'"+actpage+"')]")).getText();
	  if(actpage.equals(exppage))
	  {
		  System.out.println(actpage+"is displayed");
	  }
	  else
	  {
		  System.out.println(actpage+"is not displayed");
	  }
	}
	
	public void verifycreateleadpage(String expcreateLead) {
		String actcreatelead = driver.findElement(By.xpath("//a[text()='"+expcreateLead+"'")).getText();
		if(actcreatelead.equals(expcreateLead))
		{
			System.out.println("Create Lead Page is displayed");
		}
		else
		{
			System.out.println("Create Lead page is not displayed");
		}

	}
	
	@Given("Enter the Company name as {string}")
	public void enter_the_company_name_as(String company) {
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(company);
	}
	@Given("Enter the FirstName as {string}")
	public void enter_the_first_name_as(String Fname) {
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(Fname);
	}
	@Given("Enter the LastName as {string}")
	public void enter_the_last_name_as(String Lname) {
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(Lname);
	}
	
	@When("Click on Create Lead button")
	public void submit() {
		driver.findElement(By.name("submitButton")).click();
	}
	
	/*
	 * @Then("New Lead should be created") public void new_lead_should_be_created()
	 * {
	 * 
	 * }
	 */

}
