package Scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;
import model.BusinessFunctionDetails;

import static UIWrappers.UIObjects.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Threats.*;

import static Config.TakScreenshot.*;
import static ObjectRepository.BusinessFunctions.*;

/*************************************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Verify Add New Business Functions page
 * Test 2: Add New Business Function name and verify the message
 * Test 3: Verify Added Business Function name in list view
 * Test 4: Map Locations, Employees, Assets, Insurance and Tasks to Business Functions
 * Test 5: Verify the mapped successfully message for Locations, Employees, Assets, Insurance and Tasks
 * Test 6: Check the Business Functions name in Locations, Employees, Assets, Insurance and Tasks pop up
 * Test 7: Verify Business Functions name in Relationship title name in List View & Edit page
 * Test 8: Search Business Function Name(Valid/Invalid) in list view
 * Test 9: Verify Clear & Cancel button functionality
 * Test 10: Verify Select All check box
 * Test 11: Edit Business Function name and verify the edited details in list view
 * Test 12: Check the Pagination link in list view
 * Test 13: Verify Show Entries drop down  
 * 
**************************************************************************************************************************/

public class BFunc_AddEdit extends Page {
	
	String phFunctionName = "Function Name";
	String phFunctionId = "Business Function ID";
	String phDepartment = "Department";
	String defTxtPriority = "Select Process Priority";
	String defTxtEmployeeAssigned = "Select Employee Assigned";
	String phLossPerDay = "Loss Per Day";
	String phMinEmployeesReq = "Minimum Employees Required";
	//String phRecoveryTime = "Recovery Time (Days:Hours:Minutes)";
	String phRecoveryTime ="Recovery Time";
	String phObjective = "Objective";
	String phConsequences = "Consequences";
	
	String functionName, priority, employeeAssigned, lossPerDay, minimumEmployeesRequired, recoveryTime;
	
	LoginLogout ll = new LoginLogout();
	
	ArrayList<BusinessFunctionDetails> bsfList = new ArrayList<BusinessFunctionDetails>();

	
   @Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}
	
	@Test(priority=1)
	void verifyAddNewBusiFuncPage() throws InterruptedException
	{
		click(businessFunctionsInMainMenu);
		waitForPageLoad();
		Thread.sleep(1000);
		click(bsfOvrAddBtn);
		Thread.sleep(1000);
		verifyAssert(bsfFunctionName);
		Thread.sleep(1000);
		//driver.manage().timeouts().pageLoadTimeout(3,TimeUnit.SECONDS);
	}
	
	
	@Test(priority=2)
	void addNewBusinessFunctionName() throws BiffException, IOException, InterruptedException
	{
		
		getBusinessFunctionsSheetFromExcel();
		
		if(bsfList.size() == 0)
		{		
			getBusinessFunctionsSheetFromExcel();
			
			for(int row=1; row<bsFunc.getRows(); row++)
			{
				BusinessFunctionDetails businessFunctionDetails = new BusinessFunctionDetails();
				
				for(int col=0; col<bsFunc.getColumns(); col++)
				{
					if(col == 0)						
						businessFunctionDetails.setFunctionName(bsFunc.getCell(col, row).getContents());
					if(col == 1)
						businessFunctionDetails.setFunctionId(bsFunc.getCell(col, row).getContents());
					if(col == 2)
						businessFunctionDetails.setDepartment(bsFunc.getCell(col, row).getContents());
					if(col == 3)
						businessFunctionDetails.setLossPerDay(bsFunc.getCell(col, row).getContents());
					if(col == 4)
						businessFunctionDetails.setMinimumEmployeesRequired(bsFunc.getCell(col, row).getContents());
					if(col == 5)
						businessFunctionDetails.setRecoveryTime(bsFunc.getCell(col, row).getContents());
					if(col == 6)
						businessFunctionDetails.setObjective(bsFunc.getCell(col, row).getContents());
					if(col == 7)
						businessFunctionDetails.setConsequences(bsFunc.getCell(col, row).getContents());
					if(col == 8)
						businessFunctionDetails.setComments(bsFunc.getCell(col, row).getContents());
				}
				
				bsfList.add(businessFunctionDetails);
			}
			
		}
			
			Random random = new Random();
			int ranRow = random.nextInt(bsFunc.getRows()-1)+1; 
			
			System.out.println(ranRow+" random int------row count "+bsfList.size());
			
			if(bsfList.size() > 0 && bsfList.size()!=ranRow)
			{				
				BusinessFunctionDetails businessFunctionDetails  = bsfList.get(ranRow);
				
				//	Function Name
				functionName = businessFunctionDetails.getFunctionName().trim();
				enterText(bsfFunctionName, functionName);
				//	Function Id
				
				enterText(bsfFunctionId, businessFunctionDetails.getFunctionId().trim());
				//	Department
				enterText(bsfDepartment, businessFunctionDetails.getDepartment().trim());
				
				//	Priority
				click(bsfPriorityArrow);
				getTotalValuesIndd(bsfPriorityCnt);
				Random ranPriority = new Random();
				int rPriority = ranPriority.nextInt(totalDDValCount-1)+1;
				priority = driver.findElement(By.id("process_priority_chzn_o_"+rPriority)).getText();
				enterText(bsfPrioritySearchBox, priority);
				enterKeyInKyBord(bsfPrioritySearchBox);
				
				//	Employee Assigned
				click(bsfEmployeeAssignedArrow);
				getTotalValuesIndd(bsfEmployeeAssigneedCnt);
				Random empAssigned = new Random();
				int rempAssigned = empAssigned.nextInt(totalDDValCount-1)+1;
				employeeAssigned = driver.findElement(By.id("process_owner_chzn_o_"+rempAssigned)).getText().split(",")[0];
				enterText(bsfEmployeeAssignedSearchBox, employeeAssigned);
				enterKeyInKyBord(bsfEmployeeAssignedSearchBox);
				
				//	Loss Per Day
				lossPerDay = businessFunctionDetails.getLossPerDay().trim();
				enterText(bsfLossPerDay, lossPerDay);
				//	Minimum Employee Required
				minimumEmployeesRequired = businessFunctionDetails.getMinimumEmployeesRequired().trim();
				enterText(bsfMinimumEmpRequired, minimumEmployeesRequired);
				//	Recovery Time
				recoveryTime = businessFunctionDetails.getRecoveryTime().trim();
				enterText(bsfRecoveryTime, recoveryTime);
				//	Objective
				enterText(bsfObjective, businessFunctionDetails.getObjective());
				//	Consequences
				enterText(bsfConsequences, businessFunctionDetails.getComments());
				//	Comments
				WebElement ifram = driver.findElement(scEdtrFrame);
				driver.switchTo().frame(ifram);
				
				enterText(scEdtrCommentsField, businessFunctionDetails.getComments().trim());
				thread();		
				switchBackFromFrame();	
				
				webElement(bsfSubmitBtn);
				scrollInnerScrollBar(webelementFrame);
				takeScreenshot();
				click(bsfSubmitBtn);
				thread();
					
			}
			else
			{
				verifyAddNewBusiFuncPage();
				addNewBusinessFunctionName();				
			}
			
              //Verify functionName Added Successfully message
			   // Thread.sleep(1000);
			    getObjectText(msgNotificationBar);
			    verifyAssertEquals(functionName+" Successfully Added", getActualObjectTxt);	
			    thread();
		}
	     
	
	@Test(priority=3)
	void verifyAddedBusinessFuncNameInListView() throws InterruptedException
	{
		for(int row=1; row<11; row++)
		{
			WebElement getAddedBusFuncNmInListView = driver.findElement(By.xpath("//table[@id='process_table']/tbody/tr["+row+"]/td[2]"));
			
			if(getAddedBusFuncNmInListView.getText().contains(functionName))
			{
				getAddedBusFuncNmInListView.click();
				
				for(int col=2; col<9; col++)
				{
					String getRegisteredDetails = driver.findElement(By.xpath("//table[@id='process_table']/tbody/tr["+row+"]/td["+col+"]")).getText();
					
					if(col == 2)
						verifyAssertEquals(functionName, getRegisteredDetails);
					if(col == 3)						
						verifyAssertEquals(priority, getRegisteredDetails);
					if(col == 4)
						verifyAssertEquals(employeeAssigned, getRegisteredDetails);
					if(col == 6)
						verifyAssertEquals(recoveryTime, getRegisteredDetails);
					if(col == 7) 
						verifyAssertEquals(lossPerDay, getRegisteredDetails.replace(",", ""));
					if(col == 8)
						verifyAssertEquals(minimumEmployeesRequired, getRegisteredDetails);
				}
					break;
			}			
		}	
		
		thread();
	}
	
	@Test(priority=4)
	void mapBusinesFuncToLocations() throws InterruptedException, IOException
	{
		webElement(rltnAddRemoveRltnsBtn);
		scrollInnerScrollBar(webelementFrame);

		click(rltnAddRemoveRltnsBtn);
		thread();
		verifyAssert(mapMappingPopup);
		
		//	Verify Business Function Name in Locations Pop Up
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Locations To "+functionName, getActualObjectTxt);
		thread();
		
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
		
		//	Verify Mapped successfully message
		getObjectText(msgNotificationBar);
		verifyAssertEquals(functionName+" successfully mapped to Locations", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=5)
	void searchMappedLocations() throws IOException, InterruptedException
	{
		getTotalValuesIndd(bsfRltnLocationsCnt);
		
		Random locName = new Random();
		int rlocName = locName.nextInt(totalDDValCount-1)+1;
		
		//	Search Valid Location Name
		String locatnName = driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody/tr["+rlocName+"]/td")).getText();
		enterText(bsfRltnLocationsSearchBox, locatnName);
		getObjectText(bsfRltnLocationsSrchRslt);
		verifyAssertEquals(locatnName, getActualObjectTxt);
		takeScreenshot();
		clear(bsfRltnLocationsSearchBox);
		enterKeyInKyBord(bsfRltnLocationsSearchBox);
		
		//	Search Invalid Location Name
		enterText(bsfRltnLocationsSearchBox, "Inv Locatn Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(bsfRltnLocationsSearchBox);
		enterKeyInKyBord(bsfRltnLocationsSearchBox);
		thread();
	}
	
	@Test(priority=6)
	void mapBusinesFuncToEmployees() throws InterruptedException, IOException
	{
		click(employeesTb);
		click(rltnAddRemoveRltnsBtn);
		thread();
		verifyAssert(mapMappingPopup);
		
		//	Verify Business Function Name in Employees Pop Up
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Employees To "+functionName, getActualObjectTxt);
		thread();
		
		doMappingt();
		takeScreenshot();
		thread();
		click(mapSubmitBtn);
		Thread.sleep(1000);
		
		//	Verify Mapped successfully message
		getObjectText(msgNotificationBar);
		verifyAssertEquals(functionName+" successfully mapped to Employees", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=7)
	void searchMappedEmployees() throws IOException, InterruptedException
	{
		getTotalValuesIndd(bsfRltnEmployeesCnt);
		
		Random empName = new Random();
		int rempName = empName.nextInt(totalDDValCount-1)+1;
		
		//	Search Valid Employee Name
		String employeName = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody/tr["+rempName+"]/td")).getText();
		enterText(bsfRltnEmployeesSearchBox, employeName);
		enterKeyInKyBord(bsfRltnEmployeesSearchBox);
		thread();
		getObjectText(bsfRltnEmployeesSrchRslt);
		verifyAssertEquals(employeName, getActualObjectTxt);
		takeScreenshot();
		clear(bsfRltnEmployeesSearchBox);
		enterKeyInKyBord(bsfRltnEmployeesSearchBox);
		thread();
		
		//	Search Invalid Employee Name
		enterText(bsfRltnEmployeesSearchBox, "Inv Emp Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(bsfRltnEmployeesSearchBox);
		enterKeyInKyBord(bsfRltnEmployeesSearchBox);
		thread();
	}
	
	@Test(priority=8)
	void mapBusinesFuncToAssets() throws InterruptedException, IOException
	{
		click(assetsTb);
		click(rltnAddRemoveRltnsBtn);
		thread();
		verifyAssert(mapMappingPopup);
		
		//	Verify Business Function Name in Assets Pop Up
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Assets To "+functionName, getActualObjectTxt);
		thread();
		
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
		
		//	Verify Mapped successfully message
		getObjectText(msgNotificationBar);
		verifyAssertEquals(functionName+" successfully mapped to Assets", getActualObjectTxt);
		thread();
	}
	
	@Test(priority=9)
	void searchMappedAssets() throws IOException, InterruptedException
	{
		getTotalValuesIndd(bsfRltnAssetsCnt);
		
		Random astName = new Random();
		int rastName = astName.nextInt(totalDDValCount-1)+1;
		
		//	Search Valid Asset Name
		String assetName = driver.findElement(By.xpath("//table[@id='rel_assets']//tbody/tr["+rastName+"]/td")).getText();
		enterText(bsfRltnAssetsSearchBox, assetName);
		enterKeyInKyBord(bsfRltnAssetsSearchBox);
		thread();
		getObjectText(bsfRltnAssetsSrchRslt);
		verifyAssertEquals(assetName, getActualObjectTxt);
		takeScreenshot();
		clear(bsfRltnAssetsSearchBox);
		enterKeyInKyBord(bsfRltnAssetsSearchBox);
		thread();
		
		//	Search Invalid Asset Name
		enterText(bsfRltnAssetsSearchBox, "Inv Asset Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(bsfRltnAssetsSearchBox);
		enterKeyInKyBord(bsfRltnAssetsSearchBox);
		thread();
	}
	
	@Test(priority=10)
	void mapBusinesFuncToInsurance() throws InterruptedException, IOException
	{
		click(insuranceTb);
		click(rltnAddRemoveRltnsBtn);
		thread();
		verifyAssert(mapMappingPopup);
		
		//	Verify Business Function Name in Insurance Pop Up
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Insurance To "+functionName, getActualObjectTxt);
		thread();
		
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
		
		//	Verify Mapped successfully message
		getObjectText(msgNotificationBar);
		verifyAssertEquals(functionName+" successfully mapped to Insurance", getActualObjectTxt);
		thread();
	}
	
	@Test(priority=11)
	void searchMappedInsurance() throws IOException, InterruptedException
	{
		getTotalValuesIndd(bsfRltnInsuranceCnt);
		
		Random insName = new Random();
		int rinsName = insName.nextInt(totalDDValCount-1)+1;
		
		//	Search Valid Insurance Name
		String insurancName = driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody/tr["+rinsName+"]/td")).getText();
		enterText(bsfRltnInsuranceSearchBox, insurancName);
		enterKeyInKyBord(bsfRltnInsuranceSearchBox);
		thread();
		getObjectText(bsfRltnInsuranceSrchRslt);
		verifyAssertEquals(insurancName,getActualObjectTxt);
		takeScreenshot();
		clear(bsfRltnInsuranceSearchBox);
		enterKeyInKyBord(bsfRltnInsuranceSearchBox);
		
		//	Search Invalid Insurance Name
		enterText(bsfRltnInsuranceSearchBox, "Inv Insurance Name");
		enterKeyInKyBord(bsfRltnInsuranceSearchBox);
		thread();
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(bsfRltnInsuranceSearchBox);
		enterKeyInKyBord(bsfRltnInsuranceSearchBox);
		thread();
	}
	
	@Test(priority=12)
	void mapBusinesFuncToTasks() throws InterruptedException, IOException
	{
		click(tasksTb);
		click(rltnAddRemoveRltnsBtn);
		thread();
		verifyAssert(mapMappingPopup);
		
		//	Verify Business Function Name in Tasks Pop Up
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Tasks To "+functionName, getActualObjectTxt);
		thread();
		
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
		
		//	Verify Mapped successfully message
		getObjectText(msgNotificationBar);
		verifyAssertEquals(functionName+" successfully mapped to Tasks", getActualObjectTxt);
		thread();
	}
	
	@Test(priority=13)
	void searchMappedTasks() throws IOException, InterruptedException
	{
		getTotalValuesIndd(bsfRltnTasksCnt);
		
		Random tskName = new Random();
		int rtskName = tskName.nextInt(totalDDValCount-1)+1;
		
		//	Search Valid Task Name
		String taskName = driver.findElement(By.xpath("//table[@id='rel_task']//tbody/tr["+rtskName+"]/td")).getText();
		enterText(bsfRltnTasksSearchBox, taskName);
		enterKeyInKyBord(bsfRltnTasksSearchBox);
		thread();
		getObjectText(bsfRltnTasksSrchRslt);
		verifyAssertEquals(taskName,  getActualObjectTxt);
		takeScreenshot();
		clear(bsfRltnTasksSearchBox);
		enterKeyInKyBord(bsfRltnTasksSearchBox);
		thread();
		
		//	Search Invalid Task Name
		enterText(bsfRltnTasksSearchBox, "Inv Task Name");
		enterKeyInKyBord(bsfRltnTasksSearchBox);
		thread();
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(bsfRltnTasksSearchBox);
		enterKeyInKyBord(bsfRltnTasksSearchBox);
		thread();
	}
	
	
	@Test(priority=14)
	void verifyBusinessFunctionsNameInRelationshipTitle() throws InterruptedException
	{
		getTotalValuesIndd(bsfBusinesFunctionsLstViewTtl);
		Random random = new Random();
		int rrow = random.nextInt(totalDDValCount-1)+1;
		
		WebElement businesFuncNm = driver.findElement(By.xpath("//table[@id='process_table']//tr["+rrow+"]/td[2]"));
		String busFuncNmForChkTtl = businesFuncNm.getText();
		businesFuncNm.click();
		thread();
		
		//	Verify Business Functions Name In List View Relationship Title
		getObjectText(bsfRelationshipTitleNm);
		verifyAssertEquals(busFuncNmForChkTtl, getActualObjectTxt.substring(4));
		
		driver.findElement(By.xpath("//table[@id='process_table']//tr["+rrow+"]/td[9]/a[1]")).click();
		thread();
		
		//	Verify Business Functions Name In Edit Screen Relationship Title
		getObjectText(bsfRelationshipTitleNm);
		verifyAssertEquals(busFuncNmForChkTtl, getActualObjectTxt.substring(4));
		thread();
		
		webElement(bsfCancelBtn);
		scrollInnerScrollBar(webelementFrame);
		thread();
		click(bsfCancelBtn);
		thread();
	}
	
	@Test(priority=15)
	void searchBusinessFunctionsInListView() throws IOException, InterruptedException
	{
		getTotalValuesIndd(bsfBusinesFunctionsLstViewTtl);
		
		Random random = new Random();
		int rbfunc = random.nextInt(totalDDValCount-1)+1;
		
		String bfuncNmFrSrch = driver.findElement(By.xpath("//table[@id='process_table']//tr["+rbfunc+"]/td[2]")).getText();
		
		//	Search Valid Business Function Name
		enterText(bsfBusinessFunctionsSearchBox, bfuncNmFrSrch);
		getObjectText(bsfLstViewSrchRslt);
		verifyAssertEquals(bfuncNmFrSrch, getActualObjectTxt);
		takeScreenshot();
		clear(bsfBusinessFunctionsSearchBox);
		enterKeyInKyBord(bsfBusinessFunctionsSearchBox);
		
		//	Search Invalid Business Function Name
		enterText(bsfBusinessFunctionsSearchBox, "Inv Business Func Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(bsfBusinessFunctionsSearchBox);
		enterKeyInKyBord(bsfBusinessFunctionsSearchBox);
		thread();		
	}
	
    @Test(priority=16)
	void verifyClearndCancelBtnFunc() throws InterruptedException
	{
		getTotalValuesIndd(bsfBusinesFunctionsLstViewTtl);
		thread();
		Random random = new Random();
		int rrow = random.nextInt(totalDDValCount-1)+1;
		
		driver.findElement(By.xpath("//table[@id='process_table']/tbody/tr["+rrow+"]/td[9]/a[1]")).click();
		thread();
		
		webElement(bsfClearBtn);
		scrollInnerScrollBar(webelementFrame);
		thread();
		click(bsfClearBtn);
		thread();
		
		//	Function Name
		getAttributePh(bsfFunctionName);
		verifyAssertEquals(phFunctionName, getAttribtePh);
		//	Function ID
		getAttributePh(bsfFunctionId);
		verifyAssertEquals(phFunctionId, getAttribtePh);
		//	Department
		getAttributePh(bsfDepartment);
		verifyAssertEquals(phDepartment, getAttribtePh);
		//	Priority
		getObjectText(bsfPriorityDefTxt);
		verifyAssertEquals(defTxtPriority, getActualObjectTxt);
		//	Employee Assigned
		getObjectText(bsfEmployeeAssignedDefTxt);
		verifyAssertEquals(defTxtEmployeeAssigned, getActualObjectTxt);
		//	Loss Per Day
		getAttributePh(bsfLossPerDay);
		verifyAssertEquals(phLossPerDay, getAttribtePh);
		//	Minimum Employees Required
		getAttributePh(bsfMinimumEmpRequired);
		verifyAssertEquals(phMinEmployeesReq, getAttribtePh);
		//	Recovery Time
		getAttributePh(bsfRecoveryTime);
		verifyAssertEquals(phRecoveryTime, getAttribtePh.trim());
		//	Objective
		getAttributePh(bsfObjective);
		verifyAssertEquals(phObjective, getAttribtePh);
		//	Consequences
		getAttributePh(bsfConsequences);
		verifyAssertEquals(phConsequences, getAttribtePh);
		//	Comments
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);		
		getObjectText(scEdtrCommentsField); 
		verifyAssertEquals("", getActualObjectTxt);
		switchBackFromFrame();
		thread();
		
		webElement(bsfCancelBtn);
		scrollInnerScrollBar(webelementFrame);
		thread();
		click(bsfCancelBtn);
		thread();
		verifyAssert(bsfBusinessFunctionsSearchBox);
	}
	
	@Test(priority=17)
	void verifySelectAllCheckBox() throws IOException, InterruptedException
	{
		click(bsfSelectAllChkBox);
		thread();
		takeScreenshot();
		
		getTotalValuesIndd(bsfBusinesFunctionsLstViewTtl);
		for(int i=1; i<=totalDDValCount-1; i++)
		{
			boolean chkd = driver.findElement(By.xpath("//table[@id='process_table']//tr["+i+"]/td/div/span/input")).isEnabled();
			Assert.assertEquals(true, chkd);					
		}		
		click(bsfSelectAllChkBox);
		thread();
	}
	
	@Test(priority=18)
	void editBusinessFunctionName() throws InterruptedException, BiffException, IOException
	{
		getBusinessFunctionsSheetFromExcel();
		
		getTotalValuesIndd(bsfBusinesFunctionsLstViewTtl);
		Random random = new Random();
		int rrow = random.nextInt(totalDDValCount-1)+1;
		
		driver.findElement(By.xpath("//table[@id='process_table']//tr["+rrow+"]/td[9]/a[1]")).click();
		thread();
		
		webElement(bsfClearBtn);
		scrollInnerScrollBar(webelementFrame);
		thread();
		click(bsfClearBtn);
		thread();
		
		Random random1 = new Random();
		int ranRow = random1.nextInt(bsfList.size()); 
		
		if(bsfList.size() > 0)
		{
			BusinessFunctionDetails businessFunctionDetails  = bsfList.get(ranRow);
			
			//	Function Name
			functionName = businessFunctionDetails.getFunctionName().trim();
			enterText(bsfFunctionName, functionName);
			//	Function Id
			enterText(bsfFunctionId, businessFunctionDetails.getFunctionId().trim());
			//	Department
			enterText(bsfDepartment, businessFunctionDetails.getDepartment().trim());
			
			//	Priority
			click(bsfPriorityArrow);
			getTotalValuesIndd(bsfPriorityCnt);
			Random ranPriority = new Random();
			int rPriority = ranPriority.nextInt(totalDDValCount-1)+1;
			priority = driver.findElement(By.id("process_priority_chzn_o_"+rPriority)).getText();
			enterText(bsfPrioritySearchBox, priority);
			enterKeyInKyBord(bsfPrioritySearchBox);
			
			//	Employee Assigned
			click(bsfEmployeeAssignedArrow);
			getTotalValuesIndd(bsfEmployeeAssigneedCnt);
			Random empAssigned = new Random();
			int rempAssigned = empAssigned.nextInt(totalDDValCount-1)+1;
			employeeAssigned = driver.findElement(By.id("process_owner_chzn_o_"+rempAssigned)).getText().split(",")[0];
			enterText(bsfEmployeeAssignedSearchBox, employeeAssigned);
			enterKeyInKyBord(bsfEmployeeAssignedSearchBox);
			
			//	Loss Per Day
			lossPerDay = businessFunctionDetails.getLossPerDay().trim();
			enterText(bsfLossPerDay, lossPerDay);
			//	Minimum Employee Required
			minimumEmployeesRequired = businessFunctionDetails.getMinimumEmployeesRequired().trim();
			enterText(bsfMinimumEmpRequired, minimumEmployeesRequired);
			//	Recovery Time
			recoveryTime = businessFunctionDetails.getRecoveryTime().trim();
			enterText(bsfRecoveryTime, recoveryTime);
			//	Objective
			enterText(bsfObjective, businessFunctionDetails.getObjective());
			//	Consequences
			enterText(bsfConsequences, businessFunctionDetails.getComments());
			//	Comments
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);
			
			enterText(scEdtrCommentsField, businessFunctionDetails.getComments().trim());
			thread();		
			switchBackFromFrame();				
		}	
		
		takeScreenshot();
		click(bsfSubmitBtn);
		thread();
		
		//	Verify Updated Successfully message
		getObjectText(msgNotificationBar);
		verifyAssertEquals(functionName+" Successfully Updated", getActualObjectTxt);
		thread();
	}
	
	@Test(priority=19)
	void verifyEditedBusinesFuncsInListView() throws InterruptedException
	{
		getTotalValuesIndd(bsfBusinesFunctionsLstViewTtl);
		
		for(int row=1; row<=totalDDValCount; row++)
		{
			WebElement getAddedBusFuncNmInListView = driver.findElement(By.xpath("//table[@id='process_table']/tbody/tr["+row+"]/td[2]"));
			
			if(getAddedBusFuncNmInListView.getText().contains(functionName))
			{
				getAddedBusFuncNmInListView.click();
				
				for(int col=2; col<9; col++)
				{
					String getRegisteredDetails = driver.findElement(By.xpath("//table[@id='process_table']/tbody/tr["+row+"]/td["+col+"]")).getText();
					
					if(col == 2)
						verifyAssertEquals(functionName, getRegisteredDetails);
					if(col == 3)						
						verifyAssertEquals(priority, getRegisteredDetails);
					if(col == 4)
						verifyAssertEquals(employeeAssigned, getRegisteredDetails);
					if(col == 6)
						verifyAssertEquals(recoveryTime, getRegisteredDetails);
					if(col == 7) 
						verifyAssertEquals(lossPerDay, getRegisteredDetails.replace(",", ""));
					if(col == 8)
						verifyAssertEquals(minimumEmployeesRequired, getRegisteredDetails);
				}
					break;
			}			
		}	
		
		thread();
	}
	
	@Test(priority=20)
	void businessFunctionsListViewPagination() throws IOException, InterruptedException
	{
		getTotalValuesIndd(bsfListViewPagination);
		
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
			driver.findElement(By.xpath("//div[@id='process_table_wrapper']//div/div[2]/div/ul/li["+randomPge+"]/a")).click();
			thread();
			takeScreenshot();
			click(pagiStartArw);
			thread();
		}		
	}
	
	@Test(priority=21)
	void verifyShowEntriesDropDown() throws InterruptedException, IOException
	{
		try
		{
			getObjectText(bsfListViewRecsInfo); 
			
			
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
			{
				selectTextFromDropdown(bsfListViwLength, "25");
				thread();
				takeScreenshot();
			 
				selectTextFromDropdown(bsfListViwLength, "10");
				thread();
			}
		}
		catch(WebDriverException e)
		{
			System.out.println("Pagination is not available in Business Functions List View. It contains 10 or less than 10 records");
		}
		thread();
	}
	
	@Test(priority=22)
	void verifyViewPage()throws IOException, InterruptedException
	{
		thread();
		click(bsfViewBtn);
		thread();
		takeScreenshot();
     	getObjectText(bsfViewPopup);
     	thread();
     	webElement(bsfViewCloseBtn);
		scrollInnerScrollBar(webelementFrame);
		thread();
     	click(bsfViewCloseBtn);
     	Thread.sleep(1000);
	}
	
	
	/*@Test(priority=23)
    void Documentupload() throws IOException, InterruptedException, AWTException
    {
		
		webElement(docuemntTb);
		scrollInnerScrollBar(webelementFrame);
		thread();
    	
		click(docuemntTb);
      	thread();
        //Scroll down the page
		webElement(choosefiles);
		scrollInnerScrollBar(webelementFrame);
		thread();
			
		    //click choosefiles button 
			click(choosefiles);
			thread();
			takeScreenshot();
      	
       
        //put path to your image in a clipboard
        StringSelection ss = new StringSelection("D:\\SIB\\Logo\\FatPipe_logo.jpg");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

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
        Thread.sleep(200);
        }*/
        
   /*@Test(priority=24)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/

}
