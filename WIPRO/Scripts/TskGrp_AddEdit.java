package Scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;
import model.TaskGroupDetails;

import static ObjectRepository.TaskGroups.*;
import static ObjectRepository.Tasks.*;
import static Config.TakScreenshot.*;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Locations.*;

/***************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Verify Add Task Group page
 * Test 2: Add New Task Group details
 * Test 3: Verify successfully added message
 * Test 4: Verify added task group details in list view
 * Test 5: Map task groups to Tasks
 * Test 6: Verify mapped successfully message for Tasks
 * Test 7: Search mapped Tasks(Valid/Invalid)
 * Test 8: Edit task group name 
 * Test 9: Verify edited details in list view
 * Test 10: Check task group name in Relationship title(List View & Edit page)
 * Test 11: Check List view pagination
 * Test 12: Show Entries drop down
 * Test 13: List view Select all check box
 * Test 14: Search with Valid/Invalid Task Group Name
 * 
****************************************************************************************************/

public class TskGrp_AddEdit extends Page {
	
	LoginLogout ll = new LoginLogout();
	
	String taskGroupName, summary;
	String modTaskGroupName, modSummary;
	String phTaskGroupName = "Task Group Name";
	
	ArrayList<TaskGroupDetails> tskGrpList = new ArrayList<TaskGroupDetails>();
	
/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		thread();
	}    */

	@Test(priority=1)
	void verifyAddTaskGroupPage() throws BiffException, IOException, InterruptedException
	{
		click(taskGroupsInMainMenu);
		waitForPageLoad();
		
		click(tskGrpOvrAddBtn);
		thread();
	}
	
	@Test(priority=2)
	void addNewTaskGroup() throws BiffException, IOException, InterruptedException
	{
		if(tskGrpList.size() == 0)
		{
			getTaskGroupsSheetFromExcel();
			
			for(int row=1; row<tskGrp.getRows(); row++)
			{
				TaskGroupDetails taskGroupDetails = new TaskGroupDetails();
				
				for(int col=0; col<tskGrp.getColumns(); col++)
				{
					if(col == 0)
						taskGroupDetails.setTaskGroupName(tskGrp.getCell(col, row).getContents().trim());
					if(col == 1)
						taskGroupDetails.setSummary(tskGrp.getCell(col, row).getContents().trim());
				}
				
				tskGrpList.add(taskGroupDetails);
			}
		}
		
		Random random = new Random();
		int rtskGrp= random.nextInt(tskGrp.getRows()-1)+1;
		
		if(tskGrpList.size() > 0)
		{
			TaskGroupDetails taskGroupDetails = tskGrpList.get(rtskGrp);
			
			//	Task Group Name
			taskGroupName = taskGroupDetails.getTaskGroupName().trim();
			enterText(tskGrpTaskGroupName, taskGroupName);
			
			//	Summary
			summary = taskGroupDetails.getSummary().trim();
			
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);
			
			enterText(scEdtrCommentsField, summary);
			thread();		
			switchBackFromFrame();
			takeScreenshot();
			
			click(tskGrpSubmitBtn);
			thread();
		}	
		
		//	Verify Successfully Added Message
		getObjectText(msgNotificationBar);
		verifyAssertEquals(taskGroupName+" Successfully Added", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=3)
	void verifyAddedTaskGrpDetailsInListView() throws InterruptedException
	{
		for(int i=1; i<3; i++)
		{
			String getAddedTskGrpName = driver.findElement(By.xpath("//table[@id='task_group_table']//tr["+i+"]/td[2]")).getText();
			
			if(getAddedTskGrpName.contains(taskGroupName))
			{
				for(int j=2; j<10; j++)
				{
					String chkAddedDtls = driver.findElement(By.xpath("//table[@id='task_group_table']//tr["+i+"]/td["+j+"]")).getText();
					
					if(j == 2)
						verifyAssertEquals(taskGroupName, chkAddedDtls);
					if(j == 3)
						verifyAssertEquals(summary, chkAddedDtls);
					
					break;
				}
			}			
		}
		
		thread();
	}
	
	@Test(priority=4)
	void mapTasksToTaskGroup() throws InterruptedException, IOException
	{
		click(rltnAddRemoveRltnsBtn);
		thread();
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Tasks To "+taskGroupName, getActualObjectTxt);
		
		doMapping();
		click(mapSubmitBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(taskGroupName+" successfully mapped to Tasks", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=5)
	void searchMappedTasks() throws IOException, InterruptedException
	{
		getTotalValuesIndd(tskGrpRltnTasksCnt);
		
		//	Search Valid Task Group Name 
		Random tskGrpNm = new Random();
		int rtskGrpNm = tskGrpNm.nextInt(totalDDValCount-1)+1;		
		String taskGroupNm = driver.findElement(By.xpath("//table[@id='rel_task']//tr["+rtskGrpNm+"]/td")).getText();
		enterText(tskGrpRltnTasksSearchBox, taskGroupNm);
		getObjectText(tskGrpRltnTasksSrchRslt);
		verifyAssertEquals(taskGroupNm, getActualObjectTxt);
		takeScreenshot();
		clear(tskGrpRltnTasksSearchBox);
		enterKeyInKyBord(tskGrpRltnTasksSearchBox);
		thread();
		
		//	Search Invalid Contact Group Name
		enterText(tskGrpRltnTasksSearchBox, "Inv Tsk Grp Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(tskGrpRltnTasksSearchBox);
		enterKeyInKyBord(tskGrpRltnTasksSearchBox);
		thread();
	}
	
	@Test(priority=6)
	void verifyClearndCancelBtnFunc() throws InterruptedException, IOException
	{
		getTotalValuesIndd(tskGrpTaskGroupNameTtl);
		Random random = new Random();
		int rrow = random.nextInt(totalDDValCount-1)+1;
		
		driver.findElement(By.xpath("//table[@id='task_group_table']//tr["+rrow+"]/td[4]/a")).click();	//	Edit Button
		thread();
		
		click(tskGrpClearBtn);
		thread();
		takeScreenshot();
		
		//	Task Group Name
		getAttributePh(tskGrpTaskGroupName);
		verifyAssertEquals(phTaskGroupName, getAttribtePh);		
		
		//	Summary
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);		
		getObjectText(scEdtrCommentsField);
		verifyAssertEquals("", getActualObjectTxt);
		switchBackFromFrame();
		thread();
		
		//	Verify Cancel button
		click(tskGrpCancelBtn);
		thread();
		verifyAssert(tskGrpTaskGroupsSearchBox);		
	}
	
	@Test(priority=7)
	void editTaskGroupName() throws InterruptedException, BiffException, IOException
	{
		getTaskGroupsSheetFromExcel();
		
		getTotalValuesIndd(tskGrpTaskGroupNameTtl);
		Random random = new Random();
		int rrow = random.nextInt(totalDDValCount-1)+1;
		
		//driver.findElement(By.xpath("//table[@id='task_group_table']//tr["+rrow+"]/td[4]/a")).click();	//	Edit Button
		driver.findElement(By.xpath("//table[@id='task_group_table']//tr[1]/td[4]/a")).click();
		thread();
		
		click(tskGrpClearBtn);
		thread();
		
		//	Task Group Name
		Random random1 = new Random();
		int rrowex = random1.nextInt(tskGrp.getRows()-1)+1;
		modTaskGroupName = tskGrp.getCell(0, rrowex).getContents().trim();
		enterText(tskGrpTaskGroupName, modTaskGroupName);
		
		//	Summary
		modSummary = tskGrp.getCell(1, rrowex).getContents().trim();
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);
		
		enterText(scEdtrCommentsField, modSummary);
		thread();
		switchBackFromFrame();
		takeScreenshot();
		
		click(tskGrpSubmitBtn);
		thread();
		
		//	Verify Successfully Updated message
		getObjectText(msgNotificationBar);
		verifyAssertEquals(modTaskGroupName+" Successfully Updated", getActualObjectTxt);
		thread();
	}
	
	@Test(priority=8)
	void verifyModifiedDetailsInListView() throws InterruptedException
	{
		for(int i=1; i<3; i++)
		{
			String getModTskGrpName = driver.findElement(By.xpath("//table[@id='task_group_table']//tr["+i+"]/td[2]")).getText();
			
			if(getModTskGrpName.contains(modTaskGroupName))
			{
				for(int j=2; j<4; j++)
				{
					String chkModDtls = driver.findElement(By.xpath("//table[@id='task_group_table']//tr["+i+"]/td["+j+"]")).getText();
					
					if(j == 2)
						verifyAssertEquals(modTaskGroupName, chkModDtls);
					if(j == 3)
						verifyAssertEquals(modSummary, chkModDtls);
										
					break;
				}
			}			
		}
		
		thread();
	}
	
	@Test(priority=9)
	void verifyTskGrpNameInRelationshipTitle() throws InterruptedException
	{
		getTotalValuesIndd(tskGrpTaskGroupNameTtl);
		
		Random random = new Random();
		int rr = random.nextInt(totalDDValCount-1)+1;
		WebElement rTskGrpName = driver.findElement(By.xpath("//table[@id='task_group_table']//tr["+rr+"]/td[2]"));
		String taskGrpName = rTskGrpName.getText();
		rTskGrpName.click();
		thread();
		//	Check Name in List View Page
		getObjectText(tskGrpRelationshipTitleNm);
		verifyAssertEquals(taskGrpName, getActualObjectTxt.substring(4));
		
		driver.findElement(By.xpath("//table[@id='task_group_table']//tr["+rr+"]/td[4]/a")).click();
		thread();
		
		getObjectText(tskGrpRelationshipTitleNm);
		verifyAssertEquals(taskGrpName, getActualObjectTxt.substring(4));
		
		click(tskGrpSubmitBtn);
		thread();
	}
	
	@Test(priority=10)
	void verifyTaskGrpListViewPagination() throws IOException, InterruptedException
	{
		getTotalValuesIndd(tskGrpListViewPagination);
				
		if(totalDDValCount > 5)
		{
			click(pagiEndArw);
			takeScreenshot();
			thread();
			
			click(pagiStartArw);
			takeScreenshot();
			thread();
			
			if(totalDDValCount == 6)
			{
				click(pagiNextArw);
				takeScreenshot();
				thread();
				
				click(pagiPreviousArw);
				takeScreenshot();
				thread();
			}
			
			//	Click Pagination Number
			Random randomPagi = new Random();
			int randomPge = randomPagi.nextInt((totalDDValCount)-4)+4;
			driver.findElement(By.xpath("//div[@class='dataTables_paginate paging_bootstrap pagination pagination-right']/ul/li["+randomPge+"]/a")).click();
			thread();
			takeScreenshot();
			click(pagiStartArw);
			thread();
		}
	}
	
	@Test(priority=11)
	void verifySelectAllChkBox() throws IOException, InterruptedException
	{
		click(tskGrpSelectAllChkBox);
		takeScreenshot();
		
		getTotalValuesIndd(tskGrpTaskGroupNameTtl);
		for(int i=1; i<=totalDDValCount-1; i++)
		{
			boolean chkd = driver.findElement(By.xpath("//table[@id='task_group_table']/tbody/tr["+i+"]/td/div/span/input")).isEnabled();
			Assert.assertEquals(true, chkd);					
		}		
		click(tskGrpSelectAllChkBox);
		thread();
	}

	@Test(priority=12)
	void verifyShowEntriesDropdown() throws InterruptedException, IOException
	{
		try
		{
			getObjectText(tskGrpListViewRecsInfo);
			
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
			{
				selectTextFromDropdown(tskGrpListViewLength, "25");
				thread();
				takeScreenshot();
				
				selectTextFromDropdown(tskGrpListViewLength, "10");
				thread();
			}
		}
		catch(WebDriverException e)
		{
			System.out.println("Pagination is not available in Task Group List View. It contains 10 or less than 10 records");
		}
	}
	
	@Test(priority=13)
	void searchTaskGroupName() throws IOException, InterruptedException
	{
		getTotalValuesIndd(tskGrpTaskGroupNameTtl);
		
		Random random = new Random();
		int rr = random.nextInt(totalDDValCount-1)+1;		
		String rTskGrpNmForSearch = driver.findElement(By.xpath("//table[@id='task_group_table']//tr["+rr+"]/td[2]")).getText();
		
		//	Search Valid Task Group Name
		enterText(tskGrpTaskGroupsSearchBox, rTskGrpNmForSearch);
		getObjectText(tskGrpLstViewSearchRslt);
		verifyAssertEquals(rTskGrpNmForSearch, getActualObjectTxt);
		takeScreenshot();
		clear(tskGrpTaskGroupsSearchBox);
		enterKeyInKyBord(tskGrpTaskGroupsSearchBox);
		
		//	Search Invalid Task Group Name
		enterText(tskGrpTaskGroupsSearchBox, "Inv Task Grp Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(tskGrpTaskGroupsSearchBox);
		enterKeyInKyBord(tskGrpTaskGroupsSearchBox);
		thread();
	}
	
	 /*@Test(priority=14)
     void Documentupload() throws IOException, InterruptedException, AWTException
     {
     	
     	click(docuemntTb);
    	thread();
     	click(choosefiles);
     	thread();
         //put path to your image in a clipboard
         StringSelection ss = new StringSelection("D:\\SIB\\Logo\\FatPipe_logo.jpg");
     	 thread();
         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
      
          thread();
         //imitate mouse events like ENTER, CTRL+C, CTRL+V
         Robot robot = new Robot();
         robot.keyPress(KeyEvent.VK_CONTROL);
         robot.keyPress(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_CONTROL);
         robot.keyPress(KeyEvent.VK_ENTER);
         robot.keyRelease(KeyEvent.VK_ENTER);
        // click to upload the files 
         click(docUpload);
         thread();
         thread();
         }*/
	 
	 @Test(priority=15)
		void verifyViewPage()throws IOException, InterruptedException
		{
			click(tskGrpViewBtn);
			takeScreenshot();
	     	getObjectText(tskGrpViewPopup);
	     	thread();
	     	click(tskGrpViewCloseBtn);
	     	thread();
		}
	    
	
	/*@Test(priority=16)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/

}

