package Scripts;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.BusinessFunctions.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;
import static UIWrappers.UIObjects.*;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class BFunc_DeleteMassEdit extends Page {
	
	LoginLogout ll = new LoginLogout();
	Loc_AddnDeleteMultiple loc_AddnDeleteMultiple = new Loc_AddnDeleteMultiple();
	
	String mEdtpriority ,mEdtemployeeAssigned, mEdtDepartment;
	
	BFunc_AddEdit bFunc_AddEdit = new BFunc_AddEdit();
  /* @Test(priority=0)

	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	} */
	
	@Test(priority=2)
	void checkSelectRecordsToPerformEditMsg() throws IOException, InterruptedException
	{
		Thread.sleep(1000);
		click(businessFunctionsInMainMenu);
		click(bsfOvrMassEditBtn);
		Thread.sleep(1000);
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordstoPerformEditOperation, getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=3)
	void massEditBusinessFunctions() throws InterruptedException, IOException, BiffException
	{
		Thread.sleep(1000);
		click(businessFunctionsInMainMenu);
		waitForPageLoad();
		getBusinessFunctionsSheetFromExcel();
		
		click(bsfListViewFstName);
		thread();
		click(bsfListViewSecName);
		thread();
		click(bsfOvrMassEditBtn);
		thread();
		
		
		//	Verify Please Fill at least anyone field to update message
		click(bsfmEdtSubmitBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.fillAtleastOneFieldToUpdate, getActualObjectTxt);
		takeScreenshot();
		thread();
		
		//	Priority
		click(mEdtbsfPriorityArrow);
		getTotalValuesIndd(mEdtbsfPriorityCnt);
		Random ranPriority = new Random();
		int rEPriority = ranPriority.nextInt(totalDDValCount-1)+1;
		mEdtpriority = driver.findElement(By.id("prs_priority_chzn_o_"+rEPriority)).getText();
		enterText(mEdtbsfPrioritySearchBox, mEdtpriority);
		enterKeyInKyBord(mEdtbsfPrioritySearchBox);
		thread();
		
		//	Department
		Random dept = new Random();
		int rDept = dept.nextInt(bsFunc.getRows()-1)+1;
		mEdtDepartment = bsFunc.getCell(2, rDept).getContents();
		enterText(bsfmEdtDepartment, mEdtDepartment);
		thread();
		
		//	Employee Assigned
		click(mEdtbsfEmployeeAssignedArrow);
		getTotalValuesIndd(mEdtbsfEmployeeAssigneedCnt);
		Random empAssigned = new Random();
		int rEempAssigned = empAssigned.nextInt(totalDDValCount-1)+1;
		mEdtemployeeAssigned = driver.findElement(By.id("prs_owner_chzn_o_"+rEempAssigned)).getText().split(",")[0];
		enterText(mEdtbsfEmployeeAssignedSearchBox,mEdtemployeeAssigned);
		enterKeyInKyBord(mEdtbsfEmployeeAssignedSearchBox);
		
		click(bsfmEdtSubmitBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Successfully Updated 2 Record(s)", getActualObjectTxt);		
		thread();
	}
	
	@Test(priority=4)
	void verfiyMassEditDtlsInListView() throws InterruptedException
	{
		for(int i=1; i<3; i++)
		{
			String priorityInLstView = driver.findElement(By.xpath("//table[@id='process_table']/tbody/tr["+i+"]/td[3]")).getText();
			
			if(priorityInLstView.contains(mEdtpriority))
			{
				for(int j=3; j<8; j++)
				{
					String mEdtdDtlsInLst = driver.findElement(By.xpath("//table[@id='process_table']/tbody/tr["+i+"]/td["+j+"]")).getText().trim();
					
					if(j == 3)
					{
						//	Check Priority
						verifyAssertEquals(mEdtpriority, mEdtdDtlsInLst);
					}
					else if(j ==4)
					{
						//	Check Employee Assigned
						verifyAssertEquals(mEdtemployeeAssigned.trim(), mEdtdDtlsInLst);
					}
			
			}
	    
		}
		      thread();
	 }	
		
 }
	
	@Test(priority=5)
	void deletebFunc() throws InterruptedException, IOException, BiffException
	{

		click(businessFunctionsInMainMenu);
		waitForPageLoad();
		
		bFunc_AddEdit.verifyAddNewBusiFuncPage();
		bFunc_AddEdit.addNewBusinessFunctionName();
		Thread.sleep(100);
	
		getTotalValuesIndd(bsfBusinesFunctionsLstViewTtl);
		
		Random random = new Random();
		int del = random.nextInt(totalDDValCount-1)+1;
		WebElement delBtn = driver.findElement(By.xpath("//table[@id='process_table']/tbody/tr["+del+"]/td[9]/a[2]"));
		delBtn.click();
		verifyAssert(deleteConfPopup);
		Thread.sleep(1000);
		getObjectText(deleteConfPpMessage);
		bFunc_AddEdit.functionName= driver.findElement(By.xpath("//table[@id='process_table']//tr["+del+"]/td[2]")).getText();
       //Check Delete Confirmation pop up message
		getObjectText(deleteConfPpMessage);
		Thread.sleep(1000);
		verifyAssertEquals("Confirm to remove Business Function   "+bFunc_AddEdit.functionName+" ?", getActualObjectTxt);
		click(delConfOkBtn);
		Thread.sleep(1000);
		getObjectText(msgNotificationBar);
		verifyAssertEquals("  "+bFunc_AddEdit.functionName+" Is Deleted", getActualObjectTxt);
		takeScreenshot();
	}
		
	
	@Test(priority=6, invocationCount=2)
	void addNewBusinessFunctionName() throws BiffException, IOException, InterruptedException
	{
		
		Thread.sleep(100);
		bFunc_AddEdit.verifyAddNewBusiFuncPage();
		bFunc_AddEdit.addNewBusinessFunctionName();
		thread();
	}
	
	@Test(priority=7)
	void deleteMultipleBusinessFunction() throws InterruptedException, IOException, BiffException
	{
		
		
		for(int i=1; i<3; i++)
			
		{
		/*	Random random = new Random();
			getTotalValuesIndd(bsfBusinesFunctionsLstViewTtl);
			int rfunctionName = random.nextInt(totalDDValCount-1)+1;*/
			
			driver.findElement(By.xpath("//table[@id='process_table']//tr["+i+"]/td/div/span/input")).click();
			thread();
		}
			
		click(bsfOvrDeleteBtn);
		takeScreenshot();
		verifyAssert(deleteConfPopup);
		Thread.sleep(1000);
		getObjectText(deleteConfPpMessage);
		verifyAssertEquals("Confirm to remove 2 record(s) from Business Functions?", getActualObjectTxt);
		click(delConfOkBtn);
		Thread.sleep(1000);
		getObjectText(msgNotificationBar);
		verifyAssertEquals("2 records deleted successfully", getActualObjectTxt);		
		takeScreenshot();
		thread();
	}
	
	@Test(priority=8)
	void verifySelectRecordsToPerformDeleteMsg() throws InterruptedException, IOException
	{
		click(bsfOvrDeleteBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordsToPerformDelete, getActualObjectTxt);
		thread();
		takeScreenshot();
	}
		
	@Test(priority=9)
	void verifyBusinessFunctionsHelpDocument() throws InterruptedException, IOException
	{
		click(bsfOvrInfoBtn);
		verifyAssert(bsfHelpPopup);
		thread();
		takeScreenshot();
		
		click(bsfHelpPopupCloseBtn);
		thread();
	}
	
	/*@Test(priority=10)
	void logout() throws InterruptedException 
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
}
