package Scripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static Config.TakScreenshot.*;
import static ObjectRepository.Threats.*;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.TaskGroups.*;
import static ObjectRepository.Locations.*;

/*******************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Add and Delete Single Task Group Name
 * Test 2: Add and Delete multiple task groups
 * Test 3: Verify deleted successfully message
 * Test 4: Verify 'Select records to perform delete' message
 * Test 6: Check Task Group Help pop up window
 * 
********************************************************************************************************/

public class TskGrp_Delete extends Page {	
	
	
	LoginLogout ll = new LoginLogout();
	TskGrp_AddEdit tskGrp_AddEdit = new TskGrp_AddEdit(); 
	Loc_AddnDeleteMultiple loc_AddnDeleteMultiple = new Loc_AddnDeleteMultiple();
		
/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	} */
	
	@Test(priority=1)
	void deleteTaskGroupName() throws InterruptedException, IOException, BiffException
	{
		thread();
		tskGrp_AddEdit.verifyAddTaskGroupPage();
		tskGrp_AddEdit.addNewTaskGroup();
		thread();
		
		click(taskGroupsInMainMenu);
		waitForPageLoad();
		
		getTotalValuesIndd(tskGrpTaskGroupNameTtl);
		
	/*	for(int i=1; i<totalDDValCount; i++)
		{
			System.out.println(driver.findElement(By.xpath("//table[@id='task_group_table']/tbody/tr["+i+"]/td[2]")).getText()+"   "+tskGrp_AddEdit.taskGroupName);
			if(driver.findElement(By.xpath("//table[@id='task_group_table']/tbody/tr["+i+"]/td[2]")).getText().contains(tskGrp_AddEdit.taskGroupName))
			{*/
				thread();
				int i=1;
				driver.findElement(By.xpath("//table[@id='task_group_table']/tbody/tr["+i+"]/td[4]/a[2]")).click();
				verifyAssert(deleteConfPopup);
				
				//	Check Delete Confirmation pop up message
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals("Confirm to remove Task Group   "+tskGrp_AddEdit.taskGroupName+" ?", getActualObjectTxt);
				thread();
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals("  "+tskGrp_AddEdit.taskGroupName+" Is Deleted", getActualObjectTxt);
				takeScreenshot();
		//	}
	//	}
	}
		
	@Test(priority=2, invocationCount=3)
	void addTaskGroupName() throws BiffException, IOException, InterruptedException
	{
		tskGrp_AddEdit.verifyAddTaskGroupPage();
		tskGrp_AddEdit.addNewTaskGroup();
		thread();
	}
		
	@Test(priority=3)
	void deleteMultipleTaskGroupName() throws InterruptedException, IOException
	{
		for(int i=1; i<3; i++)
		{
			driver.findElement(By.xpath("//table[@id='task_group_table']/tbody/tr["+i+"]/td/div/span/input")).click();			
		}
			
		click(tskGrpOvrDeleteBtn);
		verifyAssert(deleteConfPopup);
		
		//	Check Delete Confirmation pop up message
		getObjectText(deleteConfPpMessage);
		verifyAssertEquals("Confirm to remove 2 record(s) from Task Groups?", getActualObjectTxt);
		
		click(delConfOkBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("2 records deleted successfully", getActualObjectTxt);		
		takeScreenshot();
		thread();
	}
		
	@Test(priority=4)
	void verifySelectRecordsToPerformDeleteMsg() throws InterruptedException, IOException
	{
		click(tskGrpOvrDeleteBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordsToPerformDelete, getActualObjectTxt);
		thread();
		takeScreenshot();
	}
		
	@Test(priority=5)
	void checkTaskGroupHelpDocuments() throws InterruptedException, IOException
	{
		click(tskGrpOvrInfoBtn);
		verifyAssert(tskGrpHelpPopup);
		thread();
		takeScreenshot();
		
		click(tskGrpHelpPopupCloseBtn);
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
