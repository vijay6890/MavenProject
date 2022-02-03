package FeaturesStep;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.cucumber.java.en.Given;

@Test
public class NewIncident extends BaseClassService {
	
	
	@Given("Click on {string} Link")
	public void clickOnNewIncident(String link) throws InterruptedException
	{

		driver.findElement(By.xpath("(//div[text()='"+link+"'])[1]")).click();
		Thread.sleep(5000);
	}
	
	@Given("Click on Caller id Lookup")
	public void click_on_caller_id_lookup() {
		driver.switchTo().frame("gsft_main");

		driver.findElement(By.id("lookup.incident.caller_id")).click();
		driver.switchTo().defaultContent();
	}
	@Given("Select the value as {string} in the New Window")
	public void select_the_value_as_in_the_new_window(String abellink) throws InterruptedException {
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> caller = new ArrayList<String>(windowHandles);
		driver.switchTo().window(caller.get(1));
		driver.findElement(By.linkText(abellink)).click();
		driver.switchTo().window(caller.get(0));
		Thread.sleep(3000);
	}
	@Given("Click on Short Description lookup")
	public void click_on_short_description_lookup() {
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//span[@class='icon icon-lightbulb']")).click();
		driver.switchTo().defaultContent();
	}
	@Given("Get the Incident number value")
	public void get_the_incident_number_value() {
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> caller1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(caller1.get(0));
		driver.switchTo().frame("gsft_main");
		 attribute = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println(attribute);  
	}
	@Given("Click on Submit Button")
	public void click_on_submit_button() {
		driver.findElement(By.xpath("(//button[text()='Submit'])[2]")).click();
	}
	@Given("Verify the incident is successfully created.")
	public void verify_the_incident_is_successfully_created() {
		driver.findElement(By.xpath("//span[contains(text(),'Press')]/following-sibling::input"))
		.sendKeys(attribute + Keys.ENTER);
WebElement webtable = driver.findElement(By.xpath("//table[@id='incident_table']//tbody"));
List<WebElement> rows = webtable.findElements(By.tagName("tr"));
System.out.println(rows.size());
List<WebElement> columns = rows.get(0).findElements(By.tagName("td"));
String text = columns.get(2).getText();
Assert.assertEquals(text, attribute);

}
}
