package FeaturesStep;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import io.cucumber.java.en.Given;

public class DeleteIncident extends BaseClassService{
	
	@Given("Click on {string} button")
	public void clickDelete(String Delete)
	{
		driver.findElement(By.xpath("(//button[text()='"+Delete+"'])[1]")).click();
	}
	
	@Given("Click on {string} button in the popup")
	public void clickDeleteOnPopup(String Deletepopup)
	{
		driver.findElement(By.xpath("(//button[text()='"+Deletepopup+"'])[3]")).click();
		
	}

	@Given("Verify the Deleted Incident")
public void verifyIncident() throws InterruptedException {
	Thread.sleep(3000);
	String exprecords="No records to display";
	driver.findElement(By.xpath("//span[contains(text(),'Press')]/following-sibling::input"))
	.sendKeys(attribute + Keys.ENTER);
String actrecords = driver.findElement(By.xpath("//tbody[@class='list2_body']//td")).getText();


Assert.assertEquals(exprecords, actrecords);
}
}
