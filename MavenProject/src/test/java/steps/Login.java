package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login extends BaseClass {
	

	/*
	 * @Given("Open the Chrome Browser") public void open_the_chrome_browser() {
	 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
	 * 
	 * }
	 * 
	 * @Given("Load the Application Url {string}") public void
	 * load_the_application_url(String url) { driver.get(url);
	 * driver.manage().window().maximize();
	 */
	
	@Given("Enter {string} as Username")
	public void enter_demo_salaes_manager_as_username(String username) {
		WebElement eleusername = driver.findElement(By.id("username"));
		eleusername.sendKeys(username);
	}
	@Given("Enter {string} as Password")
	public void enter_crmsfa_as_password(String password) {
		WebElement elepassword = driver.findElement(By.id("password"));
		elepassword.sendKeys(password);
	}
	@When("Click on Login Button")
	public void click_on_login_button() {
		driver.findElement(By.className("decorativeSubmit")).click();
	}
	@Then("Title should be matching with {string}")
	public void home_page_should_be_displayed(String title) {
	   String title1= driver.getTitle();
	   if(title1.equals(title))
	   {
		   System.out.println("Home page found");
	   }
		   else
		   {
			   System.out.println("Home page is not found");
		   }
	}
	   
	   @But("Error page should be displayed")
	   public void errorpage()
	   {
		   System.out.println("error message");
	   }
	   
	

	

}
