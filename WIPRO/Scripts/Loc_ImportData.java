package Scripts;

import java.awt.AWTException;
import java.io.IOException;

import jxl.read.biff.BiffException;

import org.testng.annotations.Test;

import UIWrappers.Page;
import static UIWrappers.UIObjects.*;
import static Config.TakScreenshot.*;
import static ObjectRepository.Locations.*;

/*******************************************************************************************************
 * 
 *
 * 
********************************************************************************************************/

public class Loc_ImportData extends Page{
	
	LoginLogout ll = new LoginLogout();
	
	/*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}*/
	
	@Test(priority=1)
	void verifyCancelBtnRedirectnInImprtCSV() throws BiffException, IOException, InterruptedException
	{
		click(locationsInMainMenu);
		waitForPageLoad();
		click(locOvrImportCSVBtn);
		click(locSelectCSVFileFld);
		thread();
		uploadFile("D:\\SIB\\ImportFiles\\Locations.csv");
		thread();
		takeScreenshot();
		thread();
		click(locImportSubmitBtn);
		thread();
		takeScreenshot();
		/*click(locOvrImportDataBtn);
		click(locImptCancelBtn);
		vfyLocationsSearchBox();*/
	}
	
	@Test(priority=2)
	void verifyHelpCSVFilePopup() throws IOException, InterruptedException, AWTException
	{
		//click(locationsInMainMenu);
		click(locOvrImportCSVBtn);
		click(locImptHelpCSVBtn);
		
		thread();
		download();
		thread();
		
		//Runtime.getRuntime().exec("D:\\TMedicine_WrkSpace\\SIB\\SIB_Auto_Scripts\\helpcsv.exe");
		
	}

}
