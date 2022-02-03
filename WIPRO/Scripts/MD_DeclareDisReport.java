package Scripts;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Locations.*;
import static ObjectRepository.ManageDisaster.*;
import static ObjectRepository.TaskGroups.tskGrpExportBtn;
import static ObjectRepository.TaskGroups.tskGrpHelpPopup;
import static ObjectRepository.TaskGroups.tskGrpHelpPopupCloseBtn;
import static ObjectRepository.TaskGroups.tskGrpOvrInfoBtn;
import static ObjectRepository.Tasks.tskSubmitBtn;
import static ObjectRepository.Threats.*;
import static UIWrappers.UIObjects.*;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriverException;

import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;
import UIWrappers.Page;
import jxl.read.biff.BiffException;

/***************************************************************************************************
 * 
 * @author Tamizhselvi
 * 
 * 
 * 
****************************************************************************************************/

public class MD_DeclareDisReport extends Page {
	

	LoginLogout ll = new LoginLogout();

	
	public String declareddisaster;
	/*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}*/
	
	@Test(priority=1)
	void addManageDisaster() throws InterruptedException
	{
		webElement(manageDisasterInMainMenu);
	    scrollInnerScrollBar(webelementFrame);
		click(manageDisasterInMainMenu);
		Thread.sleep(1000);
	
	}
	
	
	@Test(priority=2)
	void verifyInfoBtn() throws InterruptedException, IOException
	{
		click(mdOvrInfoBtn);
		verifyAssert(mdHelpPopup);
		thread();
		takeScreenshot();
		webElement(mdHelpPopupCloseBtn);
		scrollInnerScrollBar(webelementFrame);
		click(mdHelpPopupCloseBtn);
		thread();		
	}
	
	@Test(priority=3)
	void verifyExportBtn() throws InterruptedException, AWTException
	{
		click(mdExportBtn);
		thread();
		download();
		thread();
	}

	@Test(priority=4)
	void verifyListofDeclaredReport() throws InterruptedException, IOException
	{
		click(mdDeclareRestoredReport);
		thread();
		takeScreenshot();
		
        String parentwindow = driver.getWindowHandle();
		
		for (String ttlWindowsCnt : driver.getWindowHandles())  
	     {  
			switchToWindow(ttlWindowsCnt);
			waitForPageLoad();
			takeScreenshot();
	     }
		/*click(mdReportSaveAsPdf);
		FirefoxProfile fprofile = new FirefoxProfile();
		fprofile.setPreference("browser.download.folderList", 2);
		fprofile.setPreference( "browser.download.manager.showWhenStarting", false );
		fprofile.setPreference("browser.download.dir", "C:\\Users\\Tamizhselvi\\Desktop\\SIB download Doc");
		fprofile.setPreference("browser.helperApps.alwaysAsk.force", false);
		fprofile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf");*/
		
		driver.close();
		driver.switchTo().window(parentwindow);
		thread();
	    
	}
	
	@Test(priority=5)
	void verifySearchwithValidInvalidData() throws IOException, InterruptedException
	{
		getTotalValuesIndd(mdLsViewCnt);
		Random random = new Random();
		int rrow = random.nextInt(totalDDValCount-1)+1;
		String declareddisaster = driver.findElement(By.xpath("//table[@id='disaster_table']/tbody/tr["+rrow+"]/td[1]")).getText();

		//Search Valid Declare Disaster 
		enterText(mdListSearchBox,declareddisaster.substring(4));
		getObjectText(mdListDeclareSrchRslt);
		verifyAssertEquals(declareddisaster,getActualObjectTxt);
		takeScreenshot();
		clear(mdListSearchBox);
		enterKeyInKyBord(mdListSearchBox);
		thread();
		
       //Search Invalid Declare Disaster 
   		enterText(mdListSearchBox, "Inv DeclareDisaster");
   		getObjectText(noRecordsFoundMsg);
   		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
   		takeScreenshot();
   		clear(mdListSearchBox);
   		enterKeyInKyBord(mdListSearchBox);
   		thread();
    }

	@Test(priority=6)
	void verifyShowEntriesDropDown() throws InterruptedException, IOException
	{
		try
		{
			getObjectText(mdListViewRecsInfo);
			
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
            {
                 selectTextFromDropdown(mdListViewLength , "25");
                 thread();
                 takeScreenshot();

                 selectTextFromDropdown(mdListViewLength , "10");
                 thread();
            }
          }
               catch(WebDriverException e)
           {
               System.out.println("Pagination is not available in Declare Disaster List View. It contains 10 or less than 10 records");
           }
        }
	
	@Test(priority=7)
	void verifymdListViewPagination() throws IOException, InterruptedException
	{
		getTotalValuesIndd(mdListViewPagination);
		
		if(totalDDValCount>5)
		{
			click(pagiEndArw);
			takeScreenshot();
			thread();
			
			click(pagiStartArw);
			takeScreenshot();
			thread();
			
			if(totalDDValCount==6)
			{
				click(pagiNextArw);
				takeScreenshot();
				thread();
				
				click(pagiPreviousArw);
				takeScreenshot();
				thread();
			}
			//Click Pagination Number
			Random randomPagi = new Random();
			int randomPge = randomPagi.nextInt((totalDDValCount)-4)+4;
			driver.findElement(By.xpath("//div[@id='disaster_table_wrapper']/div[2]/div[2]/div/ul/li["+randomPge+"]/a")).click();
			thread();
			takeScreenshot();
			click(pagiStartArw);
			thread();
		}
	}

}
