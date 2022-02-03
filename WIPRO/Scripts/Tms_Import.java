package Scripts;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Teams.*;
import static ObjectRepository.Locations.*;
import static UIWrappers.UIObjects.*;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class Tms_Import extends Page
{

   WebElement impBtn;
   LoginLogout ll = new LoginLogout();	
	
	@Test(priority=0)
   void login() throws BiffException, IOException, InterruptedException
    {
	ll.loginToSIB();
    }
	
	@Test(priority=1)
	void importTeamsCSVFile() throws InterruptedException, IOException
	{
		click(teamsInMainMenu);
		waitForPageLoad();
		click(tmsOvrImportBtn);
		thread();
		click(tmsUploadFile);
		thread();
		uploadFile("D:\\SIB\\ImportFiles\\Teams.csv");
	    thread();
		click(tmsImpSubmitBtn);
		
        verifyAssert(tmsTeamsSearchBox);
		
		getObjectText(msgNotificationBar);
		takeScreenshot();		
		verifyAssertEquals("41 Team(s) updated.",getActualObjectTxt);
		thread();
	       
		}
	
	@Test(priority=2)
	void verifyTeamsImportCancelBtn() throws InterruptedException
	{
		thread();
		WebDriverWait wait=new WebDriverWait(driver,10);
		impBtn=wait.until(ExpectedConditions.visibilityOfElementLocated(tmsOvrImportBtn));
		
		webElement(tmsOvrImportBtn);
		scrollInnerScrollBar(webelementFrame);
		
		//click(tmsOvrImportBtn);
		impBtn.click();
		thread();
		click(tmsImpCancelBtn);
		thread();
		verifyAssert(tmsTeamsSearchBox);
		thread();
	}
	
	@Test(priority=3)
	void teamImportHelpDoc() throws InterruptedException, AWTException
	{
		click(tmsOvrImportBtn);
		thread();
		click(tmsHelpDocBtn);
		thread();
		download();
		thread();	
	}
	

}
