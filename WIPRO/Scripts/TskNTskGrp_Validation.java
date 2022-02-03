package Scripts;

import java.io.IOException;
import java.util.Random;

import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static ObjectRepository.Locations.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.TaskGroups.*;
import static UIWrappers.UIObjects.*;
import static Config.TakScreenshot.*;

public class TskNTskGrp_Validation extends Page
{
	LoginLogout ll = new LoginLogout();
	Tsk_AddEdit ta=new Tsk_AddEdit();
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}
	
	@Test(priority=1)
	void checkTaskHighlightedInMainMenu() throws InterruptedException
	{
		click(tasksInMainMenu);
		thread();
		
		getHighlightOptn(mainTasksOptn);
		verifyAssertEquals("active", getHighlightdTxt);	
		
	}
	
	@Test(priority=2)
	void checkTasksMandatoryFieldValidation() throws InterruptedException, IOException, BiffException
	{
		getTasksSheetFromExcel();
		
		click(tskOvrAddBtn);
		thread();
		
		webElement(tskSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		click(tskSubmitBtn);
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Task Name is required.",getActualObjectTxt);
		takeScreenshot();
		
		Random rrow = new Random();
		int rexRow = rrow.nextInt(tsk.getRows()-1)+1;

		ta.taskName = tsk.getCell(0, rexRow).getContents();
		enterText(tskTaskName, ta.taskName);
		thread();		
		click(tskSubmitBtn);
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Task ID is required.",getActualObjectTxt);
		takeScreenshot();
		
		int n=100;
		Random rnd=new Random();
		int rInt=rnd.nextInt(n-1)+1;
		ta.taskId=tsk.getCell(2, rexRow).getContents()+rInt;
		enterText(tskTaskId, ta.taskId);
		thread();			
		click(tskSubmitBtn);
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Duration should be DD:HH:MM",getActualObjectTxt);
		takeScreenshot();
		
	}
	
	@Test(priority=3)
	void checkTaskGroupsHighlightedInMainMenu() throws InterruptedException
	{
		click(taskGroupsInMainMenu);
		thread();
		
		getHighlightOptn(mainTaskGroupsOptn);
		verifyAssertEquals("active", getHighlightdTxt);	
	}
	
	@Test(priority=4)
	void checkTaskGroupsMandatoryFieldValidation() throws InterruptedException, IOException
	{
		click(tskGrpOvrAddBtn);
		thread();
		
		click(tskGrpSubmitBtn);
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Task Group Name is required.",getActualObjectTxt);
		takeScreenshot();
	}

}
