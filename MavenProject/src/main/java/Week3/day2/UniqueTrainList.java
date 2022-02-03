package Week3.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UniqueTrainList {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://erail.in/");
		driver.findElement(By.id("chkSelectDateOnly")).click();
		driver.findElement(By.id("txtStationFrom")).clear();
		Thread.sleep(3000);
		driver.findElement(By.id("txtStationFrom")).sendKeys("MAS", Keys.ENTER);
		driver.findElement(By.id("txtStationTo")).clear();
		Thread.sleep(3000);
		driver.findElement(By.id("txtStationTo")).sendKeys("MDU", Keys.ENTER);
		driver.findElement(By.id("buttonFromTo")).click();
		List<WebElement> findElements = driver
				.findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//td[2]"));
		List<String> trainnames = new ArrayList<String>();
		int size2 = findElements.size();
		System.out.println(size2);

		for (int i = 0; i < findElements.size(); i++) {
			WebElement train = findElements.get(i);
			trainnames.add(train.getText());
			// unique.add(train.getText());

		}

		Set<String> unique = new TreeSet<String>();
		System.out.println("Unique Train List");
		for (String eachtrain : trainnames) {

			unique.add(eachtrain);
			System.out.println(eachtrain);

		}

	}

}
