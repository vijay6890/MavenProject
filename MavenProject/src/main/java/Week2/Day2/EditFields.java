package Week2.Day2;

import java.awt.RenderingHints.Key;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditFields {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/Edit.html");
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys("veeravelvl@gmail.com");
		String text = driver.findElement(By.xpath(
				"//label[text()='Append a text and press keyboard tab']/following-sibling::br/following-sibling::input"))
				.getAttribute("value");
		driver.findElement(By.xpath(
				"//label[text()='Append a text and press keyboard tab']/following-sibling::br/following-sibling::input"))
				.sendKeys("text" + Keys.TAB);
		String concat = text.concat("Text");
		System.out.println("Default Text is :" + concat);
		Thread.sleep(2000);
		String text2 = driver.findElement(By.xpath("//input[@value='TestLeaf']")).getText();
		System.out.println(text2);
		driver.findElement(By.xpath("//input[@value='Clear me!!']")).clear();
		boolean Enabled = driver.findElement(By.xpath("//input[@disabled='true']")).isEnabled();

		if (Enabled == false) {
			System.out.println("The Field is Disabled State");
		} else {
			System.out.println("The Field is in Enabled State");
		}

	}

}
