package Week2.Day2;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PlayWithRadioButtons {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/radio.html");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='yes']")).click();
		String text = driver.findElement(By.xpath("//label[@for='Checked']")).getText();
		System.out.println("Default Selected radio button is " + text);
		boolean selected = driver.findElement(By.xpath("(//input[@value='1'])[3]")).isSelected();
		if (selected == false) {
			driver.findElement(By.xpath("(//input[@value='1'])[3]")).click();
		}
	}

}
