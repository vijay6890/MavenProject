package Scripts;

import java.io.IOException;

import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.Assets.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.AssetGroups.*;
import static Config.TakScreenshot.*;

/*********************************************************************************************************************
 * 
 * Test 1: Check whether 'Assets' highlighted or not in Main Menu 
 * 
**********************************************************************************************************************/

public class Ast_Validation extends Page{
	
	LoginLogout ll = new LoginLogout();
	
	/*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}*/
	
	@Test(priority=1)
	void chkAssetsTxtHighlightInMainMenu()
	{
		click(assetsInMainMenu);
		waitForPageLoad();
		
		getHighlightOptn(mainAssetsOptn);
		verifyAssertEquals("active", getHighlightdTxt);		
	}
	
	@Test(priority=2)
	void checkMandatoryFieldValidationInAddAssetScrn() throws InterruptedException, IOException
	{
		click(astOvrAddBtn);
		thread();
		
		click(astSubmitBtn);
		//	Asset Name
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Asset Name is required.", getActualObjectTxt);
		takeScreenshot();
	}
	
	@Test(priority=3)
	void verifyAstGrphighligtedInMainMenu() throws InterruptedException
	{
		click(assetGroupsInMainMenu);
		thread();
		
		getHighlightOptn(mainAssetGroupsOptn);
		verifyAssertEquals("active", getHighlightdTxt);	
	}
	
	@Test(priority=4)
	void validateAssetGroupsMandatoryFields() throws InterruptedException, IOException
	{
		click(astGrpOvrAddBtn);
		thread();
		
		webElement(astGrpSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		thread();
		click(astGrpSubmitBtn);
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Group Name is required.", getActualObjectTxt);
		takeScreenshot();
	}
	
	/*@Test(priority=5)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/

	
}
