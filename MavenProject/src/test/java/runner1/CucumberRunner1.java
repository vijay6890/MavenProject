package runner1;

import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(features="src/test/java/features1",glue="steps1",monochrome=true,publish=true,tags="@Functional or @Smoke")
public class CucumberRunner1 extends AbstractTestNGCucumberTests {	
	
}
