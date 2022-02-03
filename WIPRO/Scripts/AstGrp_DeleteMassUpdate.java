package Scripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static UIWrappers.UIObjects.*;
import static ObjectRepository.AssetGroups.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;
import static Config.TakScreenshot.*;

/**********************************************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Delete Single Asset Group Name
 * Test 2: Verify Asset Group Deleted Successfully message
 * Test 3: Delete Multiple Asset Group Name and verify the message
 * Test 4: Verify Help Pop up
 *
***********************************************************************************************************************************/

public class AstGrp_DeleteMassUpdate extends Page{
	
	LoginLogout ll = new LoginLogout();
	Loc_AddnDeleteMultiple loc_AddnDeleteMultiple = new Loc_AddnDeleteMultiple();
	AstGrp_AddEdit astGrp_AddEdit = new AstGrp_AddEdit();
	
	String assetGrpName;
	

  /* @Test(priority=0)

	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();

	}*/
	
	@Test(priority=1)
	void deleteSingleAssetGrp() throws InterruptedException, IOException, BiffException
	{
		astGrp_AddEdit.verifyAddAssetGroupPage();
		astGrp_AddEdit.addAssetGroup();
		thread();
		
		click(assetGroupsInMainMenu);
		waitForPageLoad();
		
		getTotalValuesIndd(astGrpAssetGrpListViewTtl);
		for(int i=1; i<totalDDValCount; i++)
		//for(int i=1; i<2; i++)
		{
			String getAdedAstGrpNm = driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr["+i+"]/td[2]")).getText();
			
			if(getAdedAstGrpNm.equals(astGrp_AddEdit.assetGroupName.trim()))
			{
				driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr["+i+"]/td[5]/a[2]")).click();   //	Delete button
				thread();
				takeScreenshot();
				thread();
				verifyAssert(deleteConfPopup);
				
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals("Confirm to remove Asset Group   "+astGrp_AddEdit.assetGroupName.trim()+" ?", getActualObjectTxt);
				//verifyAssertEquals("Confirm to remove Asset Group   "+getAdedAstGrpNm+" ?", getActualObjectTxt);
				
				click(delConfOkBtn);
				thread();
			}
		}	
		
		   getObjectText(msgNotificationBar);
		   takeScreenshot();
		   verifyAssertEquals("  "+astGrp_AddEdit.assetGroupName.trim()+" Is Deleted", getActualObjectTxt);
		   thread();
		   thread();
	}
	
	@Test(priority=2)
	void verifySelectRecordsToPerformDeleteMsg() throws InterruptedException, IOException
	{
		click(astGrpOvrDeleteBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordsToPerformDelete, getActualObjectTxt);
		thread();
	}
	
	@Test(priority=3, invocationCount=3)
	void addMultipleAssetGroupName() throws BiffException, IOException, InterruptedException
	{
		astGrp_AddEdit.verifyAddAssetGroupPage();
		astGrp_AddEdit.addAssetGroup();
		thread();
	}
	
	@Test(priority=4)
	void deleteMultipleAssetGrpName() throws IOException, InterruptedException
	{
		getTotalValuesIndd(astGrpAssetGrpListViewTtl);		
		
		for(int j=1; j<4; j++)
		{
			driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr["+j+"]//input")).click();
			thread();
		}
		
		click(astGrpOvrDeleteBtn);
		
		verifyAssert(deleteConfPopup);
		getObjectText(deleteConfPpMessage);
		takeScreenshot();
		
		verifyAssertEquals("Confirm to remove 3 record(s) from Asset Groups?", getActualObjectTxt);
		click(delConfOkBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("3 records deleted Successfully", getActualObjectTxt);
		thread();
	}
	
	@Test(priority=5)
	void verifyAssetGrpHelpPopup() throws InterruptedException, IOException
	{
		click(astGrpOvrInfoBtn);
		thread();
		verifyAssert(deleteConfPopup);
		takeScreenshot();
		thread();
		
		click(astGrpHelpPopupCloseBtn);
		thread();
	}
	
	/*@Test(priority=6)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
}

