package importAndExport;

import static ObjectRepository.Locations.*;
import static UIWrappers.UIObjects.click;
import static UIWrappers.UIObjects.waitForPageLoad;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import ObjectRepository.Locations.*;
import Scripts.Loc_ImportData;
import static UIWrappers.UIObjects.*;
import Scripts.LoginLogout;
import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class CSVWrite extends Page {
	String num = "";
	int numSize, i, j;
	String csv = "src/Files/LocationsWrite.csv";
	String desktop = System.getProperty("user.home") + "\\Desktop";
	String fileDest = desktop;
	List<WebElement> viewScreenValues;
	ArrayList<String> viewScreenValuesStorage = new ArrayList<String>();
	// String[] viewScreenValuesStorage =new String[viewScreenValues.size()];

	List<String[]> data = new ArrayList<String[]>();
	static LoginLogout login = new LoginLogout();
	static Loc_ImportData loc = new Loc_ImportData();

	@Test(priority = 0)
	void csvWriteandCopy() throws IOException {
		System.out.println(fileDest);
		CSVWriter writer = new CSVWriter(new FileWriter(csv));

		// Create record
		data.add(new String[] { "Location Name", "Location Status", "Location Type", "Manager Email ID",
				"Alternate Manager Email ID", "Built on Year", "Address", "Country", "State", "City", "Phone", "Fax",
				"Zip Code", "Reference Id" });

		data.add(new String[] { "Blogtags", "Fax", "Floor", "Barris Hunnisett", "demouser2@stayinbusiness.net", "2011",
				"Colorado", "United States of America", "Colorado", "Colorado", "8776092233", "801-281-0318",
				"UT-84107", "LOC001" });
		data.add(new String[] { "CaliforniaOffice", "Damaged", "Building", "demouser3@stayinbusiness.net",
				"demouser4@stayinbusiness.net", "2010", "Colorado", "United States of America", "California",
				"California", "8776092234", "801-281-0319", "UT-84105", "LOC002" });
		data.add(new String[] { "Los Angeles Office", "Renovation", "Floor", "demouser5@stayinbusiness.net",
				"demouser6@stayinbusiness.net", "2009", "Colorado", "United States of America", "Colorado", "Colorado",
				"8776092235", "801-281-0320", "UT-84100", "LOC003" });
		writer.writeAll(data);
		System.out.println("Data written into CSV file successfully");
		writer.close();
		File destDir = new File(fileDest);
		File srcFile = new File(csv);
		FileUtils.copyFileToDirectory(srcFile, destDir);
		System.out.println("The file has been copied in the destination");
		data.remove(0);
	}

	@Test(priority = 1)
	void locImport() throws BiffException, IOException, InterruptedException {

		login.loginToSIB();
		click(locationsInMainMenu);
		waitForPageLoad();
		/*
		 * click(locOvrImportCSVBtn); click(locSelectCSVFileFld); thread();
		 * uploadFile(fileDest + "\\LocationsWrite.csv"); thread();
		 * takeScreenshot(); Thread.sleep(3000); click(locImportSubmitBtn);
		 * thread(); takeScreenshot(); waitForElement(msgNotificationBar); num =
		 * driver.findElement(By.cssSelector("div.notify-message-bar")).getText(
		 * ).replaceAll("\\D+", ""); numSize = Integer.valueOf(num);
		 * System.out.println(numSize); if
		 * (driver.findElement(By.cssSelector("div.notify-message-bar")).getText
		 * ().startsWith("Failed")) { getObjectText(msgNotificationBar);
		 * verifyAssertEquals("Failed to import " + numSize + " " + "locations,"
		 * + " " + "these locations are already present in the grid",
		 * getActualObjectTxt); System.out.println(
		 * "The uploaded CSV file contains some exitsing locations"); } else {
		 * getObjectText(msgNotificationBar); verifyAssertEquals(numSize + " " +
		 * "Location(s) imported successfully", getActualObjectTxt);
		 * System.out.println("Locations were imported successfully");
		 * //System.out.println(driver.findElement(By.xpath(
		 * "//table[@id='facilities_table']//tbody//tr[1]")).getText());
		 */

	}

	/*
	 * @Test(priority = 2) void gridValuesvalidation() throws IOException {
	 * String gridValArray[] = new String[100]; int k = 0; String afterSplit[] ;
	 * 
	 * List<WebElement> gridVal = driver .findElements(By.xpath(
	 * "//table[@id='facilities_table']//tbody[@role='alert']//tr//td")); for
	 * (int i = 1; i < data.size(); i++) { String dummyarray[] = data.get(i);
	 * System.out.println(i+" "+ "Index value begins"); System.out.println(
	 * "Size of data variable" + data.size()); for (int j = 3; j <= 11; j++) {
	 * if (j == 3){ System.out.println("Enters into j2 loop");
	 * System.out.println(" The value of an k" + k); gridValArray[k] = driver
	 * .findElement(
	 * By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr[" + i
	 * + "]")) .getAttribute("data-for"); }
	 * 
	 * System.out.println("Value of i++++" + i); System.out.println(
	 * "Grid values++" + gridValArray[k]); k++; if(j==5) { String split= driver
	 * .findElement(By.xpath(
	 * "//table[@id='facilities_table']//tbody[@role='alert']//tr[" + i +
	 * "]//td[" + j + "]")) .getText(); // System.out.println(
	 * "Splitting is required for this value---->"+split);
	 * afterSplit=split.split(","); System.out.println("------------>");
	 * System.out.println("***********Split[0]*********");
	 * System.out.println(afterSplit[0]);
	 * System.out.println("***********Split[0]*********");
	 * 
	 * gridValArray[k]=afterSplit[0];
	 * 
	 * 
	 * } else { gridValArray[k] = driver .findElement(By.xpath(
	 * "//table[@id='facilities_table']//tbody[@role='alert']//tr[" + i +
	 * "]//td[" + j + "]")) .getText(); }
	 * 
	 * 
	 * }} }
	 * 
	 * CSVReader reader = new CSVReader(new FileReader(csv), ',' , '"' , 1);
	 * 
	 * String[] nextLine; while ((nextLine = reader.readNext()) != null) { if
	 * (nextLine != null) { System.out.println((nextLine[0] + nextLine[1]); }
	 * for (int j = 0; j < dummyarray.length; j++) { Sys
	 * tem.out.println("dummyarray-----------"+dummyarray[j]);
	 * if(dummyarray[j].contains(gridValue)) { System.out.println(
	 * "Grid contains all values that were imported through CSV"); } else {
	 * System.out.println("This field value is not present in the grid"+" "
	 * +dummyarray[j]); }
	 * 
	 */
	@SuppressWarnings("resource")
	@Test(priority = 2)
	void LocImportViewValidation() throws InterruptedException, IOException {
		CSVReader reader = new CSVReader(new FileReader(csv), ',', '"', 1);
		viewScreenValues = driver.findElements(By.xpath(
				"//div[@id='showdisplay_view']//div[@class='control-group']//div[@class='controls view_content']"));
		String[] nextLine = reader.readNext();
		String[] e = nextLine;
		int k = 0;
		List<WebElement> LocationviewButton = driver.findElements(By.xpath("//a[@title='View']"));

		for (i = 0; i < data.size(); i++) {
			System.out.println("j value is here" + j);
			System.out.println("Entered into view button loop");
			LocationviewButton.get(i).click();
			thread();
			for (j = 0; j < nextLine.length; j++)

			{
				if (j != 3) {

					viewScreenValuesStorage.add(viewScreenValues.get(j).getText());

				} else {

					String split = driver.findElement(By.id("v_fac_manager")).getText();
					String[] afterSplit = split.split(",");

					viewScreenValuesStorage.add(afterSplit[0]);
				}

				continue;
			}

			Thread.sleep(4000);
			System.out.println("------------------->" + (viewScreenValuesStorage));
			System.out.println("*******" + i + "" + "st iteration ends here");
			click(closeButton);
			System.out.println("The value of an i is here----------->" + i);
			for (int value = 0; value <nextLine.length; value++) {
				if ((value >= 14) || (value <= 19)) {
					String userName = driver.findElement(loggedInUserName).getText();
					if (viewScreenValuesStorage.contains(e[value]))
						System.out.println("This value matches with view screeen^^^^^" + " " + e[value]);
				} else {
					System.out.println("This value doesn't exist in the view screen$$$$" + " " + e[value]);
				}
				System.out.println("#####" + viewScreenValuesStorage);

				if (viewScreenValuesStorage.contains(e[value])) {

					System.out.println("This value is retained & visible in view screen" + e[value]);
				} else {
					System.out.println("This value is conflicting with CSV file" + " " + e[value]);
				}
				continue;

			}

			viewScreenValuesStorage.clear();

		}

		// System.out.println("Index is" + viewScreenValuesStorage.indexOf("El
		// Paso"));
		// System.out.println("------------------->" +
		// (viewScreenValuesStorage));

	}

}
