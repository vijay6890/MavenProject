package Week4.Day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String text4 = "";
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement ele = driver.findElement(By.xpath("(//a[text()='Men'])[1]"));
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
		driver.findElement(By.xpath("(//a[text()='Jackets'])[1]")).click();
		String text = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		System.out.println("Count of the Items are:" + text);
		String text2 = driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText();
		String text3 = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		String str1 = text2.substring(1, 5);
		System.out.println(str1);
		String str2 = text3.substring(1, 3);
		System.out.println(str2);
		int n1 = Integer.parseInt(str1);
		int n2 = Integer.parseInt(str2);
		int sum = n1 + n2;
		System.out.println(sum);
		String string = Integer.toString(sum);
		System.out.println(string);
		if (string.contains(text)) {
			System.out.println("Categories sum matches and items count are matching");
		} else {
			System.out.println("Counts are mismatching");
		}

		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[1]")).click();
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		driver.findElement(By.xpath("//input[@class='FilterDirectory-searchInput']")).sendKeys("Duke");
		driver.findElement(By.xpath("//label[text()='Duke']/div[1]")).click();
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		List<WebElement> list = driver.findElements(By.xpath("//h3[text()='Duke']"));
		for (int i = 0; i < list.size(); i++) {

			text4 = list.get(i).getText();

		}
		if (text4.equals("Duke")) {
			System.out.println("All the brands are Duke ");
		} else {
			System.out.println("All the brands are not Duke");
		}

		driver.findElement(By.xpath("//span[@class='myntraweb-sprite sort-downArrow sprites-downArrow']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[text()='Better Discount']")).click();
		Thread.sleep(3000);
		List<WebElement> list1 = driver.findElements(By.xpath("//span[@class='product-discountedPrice']"));
		Thread.sleep(3000);
		String text5 = list1.get(0).getText();
		System.out.println("Price of the first item" + text5);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//img[@class='img-responsive'])[1]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> handle = new ArrayList<String>(windowHandles);
		driver.switchTo().window(handle.get(1));
		WebElement src = driver.findElement(By.xpath("//div[@class='image-grid-image']"));
		File screenshotAs = src.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/pic5.png");
		FileUtils.copyFile(screenshotAs, dest);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Wishlist']")).click();
		driver.close();

	}

}
