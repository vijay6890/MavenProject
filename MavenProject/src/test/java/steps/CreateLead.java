package steps;

import org.openqa.selenium.By;

import io.cucumber.java.de.Wenn;
import io.cucumber.java.en.When;

public class CreateLead extends BaseClass {
	@When("click on CRMSFA link")
	public void clickcrmsfalink() {
	driver.findElement(By.linkText("CRM/SFA")).click();

	}

}
