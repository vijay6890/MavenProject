package steps1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import steps.BaseClass;

public class MergeLead extends BaseClass1 {
	
	
	public List<String> allhandles;
	
	@When("Click on From Contact Lookup")
	public void clickContactLookup()
	{
		driver.findElement(By.xpath("//img[@alt='Lookup']")).click();
	
	}
	
	
	@Then ("New Window should be displayed")
	public void newWindow() throws InterruptedException
	{
		Set<String> allWindows = driver.getWindowHandles();
		 allhandles = new ArrayList<String>(allWindows);
		driver.switchTo().window(allhandles.get(1));
		}
	/*
	 * @Then ("New Window1 should be displayed") public void newWindow1() throws
	 * InterruptedException { Set<String> allWindows2 = driver.getWindowHandles();
	 * List<String> allhandles2 = new ArrayList<String>(allWindows2);
	 * driver.switchTo().window(allhandles2.get(1));
	 * 
	 * }
	 */
	
	@And("Enter the First name as {string}")
	public void firstName(String Firstname)
	{
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(Firstname);
	}
	
	
	
	@Then("{string} Grid should be displayed")
	public void listGrid(String leadlist)
	{
		driver.findElement(By.xpath("//span[text()='"+leadlist+"']")).click();
	}
	
	@Then("Get the text of First row link id data")
	public void getText() throws InterruptedException
	{
		Thread.sleep(2000);
		leadID = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).getText();
		System.out.println("Gettext"+leadID);
	}
	@And("Click on First row data")
	public void firstRowData() throws InterruptedException
	{
		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.switchTo().window(allhandles.get(0));
	}
	
	@And("Click on To Contact Lookup")
	public void toContactLookup()
	{
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
	}
	
	@And("Click on the {string} Button")
	public void mergeButton(String merge)
	{
		driver.findElement(By.xpath("//a[text()='"+merge+"']")).click();
	}
	@And("Accept the Popup Alert")
	public void popupAlert() {
		driver.switchTo().alert().accept();

	}
	
	@When("Enter the Lead ID in the Lead ID field")
	public void passLeadId()
	{
		System.out.println("Entered Lead ID is "+ leadID);
		driver.findElement(By.xpath("//input[@name='id']")).sendKeys(leadID);
	}
	
	@Then("No Records should be available")
	public void leadListGrid()
	{
		String text = driver.findElement(By.className("x-paging-info")).getText();
		if (text.equals("No records to display")) {
			System.out.println("Text matched");
		} else {
			System.out.println("Text not matched");
		}
		
	}
	
}
