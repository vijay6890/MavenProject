package Week4.Day2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTables {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/table.html");
		driver.manage().window().maximize();
		List<WebElement> tablerows = driver.findElements(By.tagName("tr"));
		System.out.println("No of Rows" + tablerows.size());
		WebElement firstrow = tablerows.get(1);
		List<WebElement> Tablecolumn = firstrow.findElements(By.tagName("td"));
		System.out.println("No of Columns" + Tablecolumn.size());

		for (WebElement eachrow : tablerows) {
			System.out.println(eachrow.getText());
		}
		for (int i = 1; i < tablerows.size(); i++) {
			WebElement row = tablerows.get(i);
			List<WebElement> col = row.findElements(By.tagName("td"));
			String coltext = col.get(1).getText();
			System.out.println(coltext);

		}

	}

}
