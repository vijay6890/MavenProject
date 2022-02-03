package Scripts;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static UIWrappers.UIObjects.*;
import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Employees.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;

/***************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Add New Employee Name and Delete it
 * Test 2: Verify Deleted Successfully message
 * Test 3: Add Multiple Employee Name and Delete it
 * Test 4: Verify 'Select Records to perform edit' message
 * Test 5: Do the Mass Edit
 * Test 6: Verify the Mass Edit Details in List View
 * Test 7: Check Mass Edit message
 * Test 8: Verify 'Select records to perform delete' message
 * Test 9: Verify 'Fill atleast Anyone field to Update' message
 * Test 10: verify Employee Help Pop up
 * 
****************************************************************************************************/

public class Emp_DeleteMassEdit extends Page {
	
	LoginLogout ll = new LoginLogout();
	Emp_AddEdit emp_AddEdit = new Emp_AddEdit();
	Loc_AddnDeleteMultiple loc_AddnDeleteMultiple = new Loc_AddnDeleteMultiple();
	
	String mEdtEmployeeStatus, mEdtEmployeeType, mEdtLocation, mEdtCountry, mEdtState, mEdtAlternatEmployee;
	
   /* @Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	} */
	
	@Test(priority=1)
	void addEmployeeDetails() throws BiffException, IOException, InterruptedException
	{
		
		emp_AddEdit.verifyAddNewEmployeePage();
		emp_AddEdit.addEmployeeDetails();
		thread();
	}
	
	@Test(priority=2)
	void deleteEmployeeName() throws InterruptedException
	{
	/*	getTotalValuesIndd(empEmployeesListViewTtl);
				
		for(int i=1; i<totalDDValCount; i++)
		{
			String chkAddedTxt = driver.findElement(By.xpath("//table[@id='resource_table']//tr["+i+"]/td[11]/a[2]")).getText(); //	Check Added Name in List View
			
			if(chkAddedTxt.contains(emp_AddEdit.firstName+" "+emp_AddEdit.lastName))
			{*/
		thread();
				driver.findElement(By.xpath("//table[@id='resource_table']/tbody/tr[1]/td[11]/a[2]")).click();	// Delete button
				
				verifyAssert(deleteConfPopup);
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals("Confirm to remove Employee "+emp_AddEdit.firstName+" "+emp_AddEdit.lastName+"?", getActualObjectTxt);
				
				click(delConfOkBtn);
				thread();
				getObjectText(msgNotificationBar);
				verifyAssertEquals(emp_AddEdit.firstName+" "+emp_AddEdit.lastName+" Is Deleted", getActualObjectTxt);
	//}
	//	}		
	}
	
	@Test(priority=3, invocationCount=1)
	void addMultipleEmployeeName() throws BiffException, IOException, InterruptedException
	{
		emp_AddEdit.verifyAddNewEmployeePage();
		emp_AddEdit.addEmployeeDetails();
		thread();
	}

	@Test(priority=4)
	void deleteMultipleEmployeeName() throws InterruptedException, IOException
	{
		getTotalValuesIndd(empEmployeesListViewTtl);
		
		for(int i=1; i<2; i++)
		{
			driver.findElement(By.xpath("//table[@id='resource_table']/tbody/tr["+i+"]/td/div/span/input")).click();
			thread();
		}
		
		click(empOvrDeleteBtn);
		verifyAssert(deleteConfPopup);
		
		//	Check Delete Confirmation pop up message
		getObjectText(deleteConfPpMessage);
		verifyAssertEquals("Confirm to remove 1 record(s) from Employees?", getActualObjectTxt);
		
		click(delConfOkBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("1 records deleted successfully ", getActualObjectTxt);		
		takeScreenshot();
		thread();
	}
	
	@Test(priority=5)
	void checkSelectRecordsToPerformEditMsg() throws InterruptedException, IOException
	{
		click(empOvrMassUpdateBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordstoPerformEditOperation, getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=6)
	void massEdit() throws BiffException, IOException, InterruptedException
	{
		getEmpSheetNameFromExcel();
		
		click(empListViewFstName);
		click(empListViewSecName);
		
		click(empOvrMassUpdateBtn);
		thread();
		
		//	Check Fill At least Anyone field to Update message
		click(empmEdtSubmitBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.fillAtleastOneFieldToUpdate, getActualObjectTxt);
		thread();
		
		//	Select Employee Status
		click(empmEdtEmployeeStatusArrow);
		getTotalValuesIndd(empmEdtEmployeeStatusCnt);
		Random empStatus = new Random();
		int rempStatus = empStatus.nextInt(totalDDValCount-1)+1;
		mEdtEmployeeStatus = driver.findElement(By.id("resource_status_chzn_o_"+rempStatus)).getText();
		enterText(empmEdtEmployeeStatusSearchBox, mEdtEmployeeStatus);
		enterKeyInKyBord(empmEdtEmployeeStatusSearchBox);
		
		//		Select Employee Status
		click(empmEdtEmployeeTypeArrow);
		getTotalValuesIndd(empmEdtEmployeeTypeCnt);		
		Random empType = new Random();
		int rempType = empType.nextInt(totalDDValCount-1)+1;
		mEdtEmployeeType = driver.findElement(By.id("resource_type_chzn_o_"+rempType)).getText();
		enterText(empmEdtEmployeeTypeSearchBox, mEdtEmployeeType);
		enterKeyInKyBord(empmEdtEmployeeTypeSearchBox);
		
		//	Location
		click(empmEdtLocationArrow);
		getTotalValuesIndd(empmEdtLocationCnt);		
		Random empLocatn = new Random();
		int rempLoc = empLocatn.nextInt(totalDDValCount-1)+1;
		mEdtLocation = driver.findElement(By.id("resource_location_chzn_o_"+rempLoc)).getText();
		enterText(empmEdtLocationSearchBox, mEdtLocation);
		enterKeyInKyBord(empmEdtLocationSearchBox);
		
		//	Address
		Random random = new Random();
		int rrow = random.nextInt(emp.getRows()-1)+1;
		enterText(empmEdtAddress, emp.getCell(13, rrow).getContents());
		thread();
		
		//	Country
		click(empmEdtCountryArrow);
		getTotalValuesIndd(empmEdtCountryCnt);		
		Random empCountry = new Random();
		int rempCountry = empCountry.nextInt(totalDDValCount-1)+1;
		mEdtCountry = driver.findElement(By.id("resource_country_chzn_o_"+rempCountry)).getText();
		enterText(empmEdtCountrySearchBox, mEdtCountry);
		enterKeyInKyBord(empmEdtCountrySearchBox);
		thread();
		
		//	State
		click(empmEdtStateArrow);
		getTotalValuesIndd(empmEdtStateCnt);		
		Random empState = new Random();
		int rempState = empState.nextInt(totalDDValCount-1)+1;
		thread();
		mEdtState = driver.findElement(By.id("resource_state_chzn_o_"+rempState)).getText();
		enterText(empmEdtStateSearchBox, mEdtState);
		enterKeyInKyBord(empmEdtStateSearchBox);
				
		//	City
		enterText(empmEdtCity, emp.getCell(16, rrow).getContents());
		//	Zip Code
		enterText(empmEdtZipcode, emp.getCell(17, rrow).getContents());
		
		//	Alternate Employee
		click(empmEdtAlternateEmployeeArrow);
		getTotalValuesIndd(empmEdtAlternateEmployeeCnt);		
		Random empAlterEmp = new Random();
		int rempAlterEmp = empAlterEmp.nextInt(totalDDValCount-1)+1;
		mEdtAlternatEmployee = driver.findElement(By.id("resource_alternate_resource_chzn_o_"+rempAlterEmp)).getText();
		enterText(empmEdtAlternateEmployeeSearchBox, mEdtAlternatEmployee.substring(0, mEdtAlternatEmployee.indexOf(",")));
		enterKeyInKyBord(empmEdtAlternateEmployeeSearchBox);
		takeScreenshot();
		
		click(empmEdtSubmitBtn);
		thread();
		takeScreenshot();
		
		//	Verify Employees Updated Successfully message
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Successfully Updated 2 Record(s)", getActualObjectTxt);
	}
	
	@Test(priority=7)
	void verifyMassEditDetailsInListView()
	{
		for(int i=1; i<3; i++)
		{
			String empTypeInLstView = driver.findElement(By.xpath("//table[@id='resource_table']//tr["+i+"]/td[4]")).getText();
			
			if(empTypeInLstView.contains(mEdtEmployeeType))
			{
				for(int j=4; j<8; j++)
				{
					String mEdtdDtlsInLstView = driver.findElement(By.xpath("//table[@id='resource_table']//tr["+i+"]/td["+j+"]")).getText();
					
					if(j == 4)
						//	Check Employee Type
				
						verifyAssertEquals(mEdtEmployeeType, mEdtdDtlsInLstView);
					else if(j == 8)
						//	Check Location
						verifyAssertEquals(mEdtLocation, mEdtdDtlsInLstView);
					else if(j == 9)
						//	Check Employee Status
						verifyAssertEquals(mEdtEmployeeStatus, mEdtdDtlsInLstView);;
				}
			}			
		}
	}
	
	@Test(priority=8)
	void verifySelectRecordsToPerformDeleteMsg() throws IOException, InterruptedException
	{
		click(empOvrDeleteBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordsToPerformDelete, getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=9)
	void verifyEmployeeHelpDocument() throws InterruptedException, IOException
	{
		click(empOvrInfoBtn);
		verifyAssert(empHelpPopup);
		thread();
		takeScreenshot();
		
		click(empHelpPopupCloseBtn);
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
