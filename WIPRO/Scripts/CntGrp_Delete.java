package Scripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.ContactGroups.*;
import static ObjectRepository.Locations.*;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.Threats.*;

/*******************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Add and Delete Single Contact Group Name
 * Test 2: Add and Delete multiple contact groups
 * Test 3: Verify deleted successfully message
 * Test 4: Verify 'Select records to perform delete' message
 * Test 6: Check Contact Group Help pop up window
 * 
********************************************************************************************************/

public class CntGrp_Delete extends Page {
	
	LoginLogout ll = new LoginLogout();
	CntGrp_AddEdit cntGrp_AddEdit = new CntGrp_AddEdit(); 
	Loc_AddnDeleteMultiple loc_AddnDeleteMultiple = new Loc_AddnDeleteMultiple();
	
/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}  */
	
	
	@Test(priority=1)
	void deleteContactGroupName() throws InterruptedException, IOException, BiffException
	{
		cntGrp_AddEdit.verifyAddContactGroupPage();
		cntGrp_AddEdit.addNewContactGroup();
		Thread.sleep(1000);
		getTotalValuesIndd(cntGrpContactGrpNameTtl);
		
		for(int i=1; i<2; i++)
		{
			if(driver.findElement(By.xpath("//table[@id='contactgroup_table']/tbody/tr["+i+"]/td[2]")).getText().contains(cntGrp_AddEdit.contactGroupName))
			{
				thread();
				driver.findElement(By.xpath("//table[@id='contactgroup_table']/tbody/tr["+i+"]/td[5]/a[2]")).click();
				Thread.sleep(1000);
				verifyAssert(deleteConfPopup);
				Thread.sleep(1000);
				//	Check Delete Confirmation pop up message
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals("Confirm to remove Contact Group "+cntGrp_AddEdit.contactGroupName+"?", getActualObjectTxt);
				thread();
				
				click(delConfOkBtn);
				Thread.sleep(1000);
				getObjectText(msgNotificationBar);
				thread();
				verifyAssertEquals(cntGrp_AddEdit.contactGroupName+" Is Deleted", getActualObjectTxt);
				takeScreenshot();
			}
		}
	}
	
	@Test(priority=2, invocationCount=3)
	void addContactGroupName() throws BiffException, IOException, InterruptedException
	{
		cntGrp_AddEdit.verifyAddContactGroupPage();
		cntGrp_AddEdit.addNewContactGroup();
		thread();
	}
	
	@Test(priority=3)
	void deleteMultipleContactGroupName() throws InterruptedException, IOException
	{
		getTotalValuesIndd(cntGrpContactGrpNameTtl);
		
		for(int i=1; i<4; i++)
		{
			driver.findElement(By.xpath("//table[@id='contactgroup_table']/tbody/tr["+i+"]/td/div/span/input")).click();
			Thread.sleep(1000);
		}
		
		click(cntGrpOvrDeleteBtn);
		verifyAssert(deleteConfPopup);
		
		//	Check Delete Confirmation pop up message
		getObjectText(deleteConfPpMessage);
		verifyAssertEquals("Confirm to remove 3 record(s) from Contact Groups?", getActualObjectTxt);
		Thread.sleep(1000);
		click(delConfOkBtn);
		thread();
		Thread.sleep(1000);
		getObjectText(msgNotificationBar);
		verifyAssertEquals("3 records deleted successfully", getActualObjectTxt);		
		takeScreenshot();
		thread();
	}
	
	@Test(priority=4)
	void verifySelectRecordsToPerformDeleteMsg() throws InterruptedException, IOException
	{
		click(cntGrpOvrDeleteBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordsToPerformDelete, getActualObjectTxt);
		thread();
		takeScreenshot();
	}
	
	@Test(priority=5)
	void checkContactGroupHelpDocuments() throws InterruptedException, IOException
	{
		click(cntGrpOvrInfoBtn);
		thread();
		verifyAssert(cntGrpHelpPopup);
		thread();
		takeScreenshot();
		
		click(cntGrpHelpPpCloseBtn);
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
