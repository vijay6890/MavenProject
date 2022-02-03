package FeaturesStep;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;

public class AssignIncident extends BaseClassService {
	String text2=null;
	@Given("Click on Assignment Group Lookup")
	public void assignIncident()
	{
		driver.findElement(By.name("lookup.incident.assignment_group")).click();
		driver.switchTo().defaultContent();
	}
	
	@Given("Assign the incident to  Software group and verify it with created incident")
	public void assignSoftware() throws InterruptedException
	{
		String acttext="Software";
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lst = new ArrayList<String>(windowHandles);
		driver.switchTo().window(lst.get(1));
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//span[@class='icon-vcr-right'])[4]")).click();
		Thread.sleep(1000);
		WebElement table = driver.findElement(By.xpath("//tbody[@class='list2_body']"));
		Thread.sleep(3000);
		List<WebElement> rows2 = table.findElements(By.tagName("tr"));
		Thread.sleep(2000);
		//System.out.println(rows2.size());
		outerloop: for (int i = 0; i < rows2.size(); i++) {
			List<WebElement> columns2 = rows2.get(i).findElements(By.tagName("td"));
			// Thread.sleep(3000);
			int size = columns2.size();
			// System.out.println("Number of Cells in Row"+i+"are"+size);
			for (int j = 0; j < size; j++) {
			  text2 = columns2.get(j).getText();
				System.out.println(text2);
				if (text2.equalsIgnoreCase("Software")) {
					int currentrow = i + 1;
					driver.findElement(
							By.xpath("//tbody[@class='list2_body']/tr" + "[" + currentrow + "]" + "/td[3]/a")).click();
					break outerloop;
				}

				
			}
			
	}
		Assert.assertEquals(acttext, text2);
		Thread.sleep(3000);
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> lst1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(lst1.get(0));
		
		driver.switchTo().frame("gsft_main");

		String attribute1 = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
	
		Assert.assertEquals(attribute, attribute1);
	}
	
	
	@Given("Update the incident with Work Notes")
	public void assignNotes() throws InterruptedException
	{
		
		driver.findElement(By.xpath("//*[@id='activity-stream-textarea']")).sendKeys("Veeravel");
	}
	
	

}
