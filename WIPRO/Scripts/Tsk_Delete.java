package Scripts;

import java.io.IOException;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import org.testng.annotations.Test;


import static Config.TakScreenshot.*;
import static ObjectRepository.Threats.*;
import static UIWrappers.UIObjects.*;

import static ObjectRepository.Locations.*;

import static ObjectRepository.Tasks.*;

/*******************************************************************************************************
 * 
 * @author Tamizhselvi
 * 
 * Test 1: Add and Delete Single Task Name
 * Test 2: Add and Delete multiple task 
 * Test 3: Verify deleted successfully message
 * Test 4: Verify 'Select records to perform delete' message
 * @param <tsk_AddnDeleteMultiple>
 * @param <Tsk_DeleteMultiple>
 *
********************************************************************************************************/

public class Tsk_Delete extends Page {	
	
	
	
	LoginLogout ll = new LoginLogout();
	Tsk_AddEdit tsk_AddEdit = new Tsk_AddEdit();

	Loc_AddnDeleteMultiple loc_AddnDeleteMultiple = new Loc_AddnDeleteMultiple();
	String Selectrecordstoperformdelete,taskName;
	

/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}*/
	
	@Test(priority=1)
	void deleteName() throws InterruptedException, IOException, BiffException
	{
		Thread.sleep(100);
		tsk_AddEdit.verifyAddNewTaskPge();
		tsk_AddEdit.addNewTaskName();
		
		
		click(tasksInMainMenu);
		thread();
		
		getTotalValuesIndd(tsktaskNameLstViewTtl);
		
		Random random = new Random();
		int del = random.nextInt(totalDDValCount-1)+1;
		WebElement delBtn = driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+del+"]/td[9]/a[2]"));
		
		delBtn.click();
		verifyAssert(deleteConfPopup);
		
		getObjectText(deleteConfPpMessage);
		taskName = driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+del+"]/td[2]")).getText();
		verifyAssertEquals("Confirm to remove Task   "+taskName+" ?", getActualObjectTxt);
		takeScreenshot();
		Thread.sleep(1000);
		click(delConfOkBtn);
		
	}
	

	@Test(priority=2)
	void verifyTaskNameDeletedSuccMessage() throws IOException, InterruptedException
	{
		Thread.sleep(1000);
		getObjectText(msgNotificationBar);
		verifyAssertEquals("  "+taskName+" Is Deleted", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
		
	@Test(priority=3, invocationCount=2)
	void addTaskName() throws BiffException, IOException, InterruptedException
	{
		tsk_AddEdit.verifyAddNewTaskPge();
		tsk_AddEdit.addNewTaskName();
		Thread.sleep(100);
	}
		
	@Test(priority=4)
	void deleteMultipleTaskName() throws InterruptedException, IOException
	{
		for(int i=1; i<3; i++)
		{
			driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+i+"]/td/div/span/input")).click();	
			
		}
			
		click(tskOvrDeleteBtn);
		verifyAssert(deleteConfPopup);
		thread();
		
		
		//	Check Delete Confirmation pop up message
		getObjectText(deleteConfPpMessage);
		verifyAssertEquals("Confirm to remove 2 record(s) from Tasks?", getActualObjectTxt);
		Thread.sleep(1000);
		click(delConfOkBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("2 records deleted successfully", getActualObjectTxt);		

		takeScreenshot();
		thread();
	}
		
	@Test(priority=5)
	void verifySelectRecordsToPerformDeleteMsg() throws InterruptedException, IOException
	{
		click(tskOvrDeleteBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordsToPerformDelete, getActualObjectTxt);
		thread();
		takeScreenshot();
	}
	
	 // To view the Info 
    @Test(priority=6)
     void TaskInfo() throws IOException, InterruptedException
     {
     	click(tskInfo);
     	takeScreenshot();
     	getObjectText(tskInfopopup);
     	thread();
     	click(tskInfoclosebtn);
     	
     }
     
		
	/*@Test(priority=7)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
}
	

	
	/*@Test(priority=1)
	void deleteSingleTaskName() throws IOException, InterruptedException
	{
		click(tasksInMainMenu);
		waitForPageLoad();
		
		getTotalValuesIndd(tsktaskNameLstViewTtl);
		
		Random random = new Random();
		int del = random.nextInt(totalDDValCount-1)+1;
		WebElement delBtn = driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+del+"]/td[9]/a[2]"));
		
		delBtn.click();
		verifyAssert(deleteConfPopup);
		
		getObjectText(deleteConfPpMessage);
		taskName = driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+del+"]/td[2]")).getText();
		verifyAssertEquals("Confirm to remove Task "+taskName+"?", getActualObjectTxt);
		takeScreenshot();
		click(delConfOkBtn);
		thread();	
	}

	@Test(priority=2)
	void verifyTaskNameDeletedSuccMessage() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(taskName+" Is Deleted", getActualObjectTxt);
		takeScreenshot();
		thread();
	}

	@Test(priority=3)
	void verifySelectRecordsToDeleteMsg() throws InterruptedException, IOException
	{
		click(tskOvrDeleteBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordsToPerformDelete, getActualObjectTxt);
		takeScreenshot();
		thread();
	}

	@Test(priority=4)
	void deleteMultipleTaskName() throws IOException, InterruptedException
	{
		for(int i=0; i<2; i++)
		{
			Random random = new Random();
			getTotalValuesIndd(tsktaskNameLstViewTtl);
			int ranmtskName = random.nextInt(totalDDValCount-1)+1;
			
			driver.findElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+ranmtskName +"]/td/div/span/input")).click();			
		}
		
		takeScreenshot();
		click(tskOvrDeleteBtn);
		
		waitForElement(deleteConfPopup);
		getObjectText(deleteConfPpMessage);
		verifyAssertEquals("Confirm to remove 2 records from Tasks?", getActualObjectTxt);
		takeScreenshot();
		
		click(delConfOkBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Successfully deleted 2 records", getActualObjectTxt);
		takeScreenshot();
		thread();
	  }
	  @Test(priority=5)
	  void logout() throws InterruptedException
	  {
		ll.logoutSession();
		thread();
		closeWindow();
	  }

	}*/


	
	