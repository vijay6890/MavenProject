package Week4.Day1;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnFrames {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
WebDriverManager.chromedriver().setup();
ChromeDriver driver=new  ChromeDriver();
driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm");
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
driver.switchTo().frame("iframeResult");
Thread.sleep(3000);
driver.findElement(By.xpath("//button[text()='Try it']")).click();
Alert alert = driver.switchTo().alert();
alert.accept();
      String text = driver.findElement(By.xpath("//p[contains(text(),'OK')]")).getText();
      if(text.contains("OK"))
      {
    	  System.out.println("You clicked on ok");
      }
      else
      {
    	  System.out.println("You clicked on Cancel");
      }
}

}
