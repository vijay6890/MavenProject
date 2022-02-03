package FeaturesStep;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.cucumber.java.en.Given;

public class UpdateIncident extends BaseClassService {
	
	//public boolean text2;
	//public boolean text3;
	@Given("Search for Existing incident")
	public void searchExistingIncident() throws InterruptedException
	{
		driver.switchTo().frame("gsft_main");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Press')]/following-sibling::input"))
		.sendKeys(attribute + Keys.ENTER);
   System.out.println("Updated instance is :" + attribute);
     Thread.sleep(3000);
	}
	
	@Given("Click on the Searched Incident")
	public void clickIncident()
	{
		WebElement webtable1 = driver.findElement(By.xpath("//tbody[@class='list2_body']"));
		List<WebElement> rows1 = webtable1.findElements(By.tagName("tr"));
		List<WebElement> columns1 = rows1.get(0).findElements(By.tagName("td"));
		columns1.get(2).findElement(By.tagName("a")).click();
	}
	@Given("Select the Urgency as High and Verify it")
	public void selectUrgency()
	{
		Select sel = new Select(driver.findElement(By.xpath("//select[@name='incident.urgency']")));
		sel.selectByIndex(0);
		boolean text2 = sel.getOptions().get(0).isDisplayed();
		System.out.println(text2);
      Assert.assertTrue(text2);
	}
	
	@Given("Select the State  as InProgress and Verify it")
	public void selectState()
	{
		Select sel1 = new Select(driver.findElement(By.xpath("//select[@name='incident.state']")));
		sel1.selectByIndex(1);
		 boolean text3 = sel1.getOptions().get(1).isDisplayed();
		System.out.println(text3);
		Assert.assertTrue(text3);
	}
	
	

}
