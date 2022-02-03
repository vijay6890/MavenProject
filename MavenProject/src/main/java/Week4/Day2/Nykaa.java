package Week4.Day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.nykaa.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions act = new Actions(driver);
		act.moveToElement(brands).perform();
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris", Keys.ENTER);
		String text = driver.findElement(By.linkText("L'Oreal Paris")).getText();

		driver.findElement(By.linkText("L'Oreal Paris")).click();
		String title = driver.getTitle();
		System.out.println(title);
		if (title.contains(text)) {
			System.out.println("Text is Present");

		} else {
			System.out.println("Text is Not Present");
		}
		driver.findElement(By.xpath("//span[contains(text(),'Sort')]")).click();
		driver.findElement(By.xpath("//span[text()='popularity']")).click();
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		String text6 = driver.findElement(By.xpath("//span[text()='Shampoo']")).getText();
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();

		String text5 = driver.findElement(By.xpath("//span[text()='Shampoo']")).getText();
		// System.out.println(attribute);
		if (text5.equals(text6)) {
			System.out.println("Shampoo got Selected");
		} else {
			System.out.println("Shampoo is not selected");
		}
		driver.findElement(By.xpath("(//div[contains(text(),'Protect Shampoo')])[1]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lst = new ArrayList<String>(windowHandles);
		driver.switchTo().window(lst.get(1));
		String text2 = driver.findElement(By.xpath("(//span[contains(text(),'150')])[1]")).getText();
		System.out.println("Price of the Product" + text2);
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		driver.findElement(By.xpath("//span[@class='cart-count']")).click();
		WebElement ele = driver.findElement(By.xpath("//iframe[@src='/mobileCartIframe?ptype=customIframeCart']"));
		driver.switchTo().frame(ele);

		String text3 = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		System.out.println("Total Amount=" + text3);
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		// driver.switchTo().window(lst.get(2));
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		// driver.switchTo().window(lst.get(3));
		String text4 = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div/span"))
				.getText();
		System.out.println("Total Price" + text4);

		if (text3.contains(text4)) {
			System.out.println("Both Prices are Equal");
		}

		else {
			System.out.println("Prices are not equal");

		}
		driver.quit();

	}

}
