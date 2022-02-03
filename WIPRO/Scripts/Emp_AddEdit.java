package Scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;
import model.EmployeeDetails;

import static ObjectRepository.Admin.repSmsListViewRecsInfo;
import static ObjectRepository.BusinessFunctions.*;
import static ObjectRepository.Employees.*;
import static ObjectRepository.Locations.*;

import static ObjectRepository.Tasks.*;
import static ObjectRepository.Threats.*;
import static UIWrappers.UIObjects.*;
import static Config.TakScreenshot.*;

/*********************************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Verify display if Add New Employee page
 * Test 2: Add New Employee details
 * Test 3: Check the added Employee details in list view
 * Test 4: Map Employees to Tasks, Business Functions, Insurance and Teams
 * Test 5: Verify mapped successfully message for Tasks, Business Functions, Insurance and Teams
 * Test 6: Search mapped details(Valid/Invalid)
 * Test 7: Verify the Employee Name in List View & Edit screen Relationship title text
 * Test 8: Check Clear & Cancel button functionality
 * Test 9: Edit Employee Details
 * Test 10: Check the modified details in list view
 * Test 11: Verify the pagination
 * Test 12: Show Entries drop down
 * Test 13: Check Select All check box
 * Test 14: Verify Updated Successfully message when editing the details
 * 
**********************************************************************************************************************/

public class Emp_AddEdit<addEmployeeDetails> extends Page {

	
	LoginLogout ll = new LoginLogout();
	
	String employeeStatus, employeeType, location, alternateEmployee, firstName, lastName, title, emailId, businessPhone, mobileN0;
	String modFirstName, modLastName, modTitle, modEmployeeStatus, modEmployeeType, modBusinessPhone, modEmailId, modMobileN0, modLocation, modAlternateEmployee;
	
	String phFirstName = "First Name";
	String phLastName = "Last Name";
	String phTitle = "Title";
	String phEmployeeId = "Employee ID";
	String defEmployeeStatusTxt = "Select/Add New Employee Status";
	String defEmployeeTypeTxt = "Select/Add New Employee Type";
	String phDepartment = "Department";
	String phEmail = "E-Mail";
	String phBusinessPhone = "Business Phone";
	String phMobile = "Mobile Number";
	String phResidenceNumber = "Residence Number";
	String phOfficeNumber = "Office Number";
	String phOfficeExtn = "Extn";
	String phAddress = "Address";
	String phCountry = "Select Country";
	String phState = "Select State";
	String phCity = "City";
	String phZipcode = "Zip Code";
	String phSecondaryEmail = "Secondary Email";
	String phSecondaryMobile = "Secondary Mobile Number";
	String defLocationTxt = "Select Location";
	String defAlternateEmployeeTxt = "Select Alternate Employee";
	
	ArrayList<EmployeeDetails> empList = new ArrayList<EmployeeDetails>();

	String verifyObjDisplay;
	
	

/*  @Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}   */

	@Test(priority=1)
	void verifyAddNewEmployeePage() throws InterruptedException, IOException
	{
		 Thread.sleep(1000);
		click(employeesInMainMenu);
		waitForPageLoad();
		//driver.findElement(By.id("cometchat_hide")).click();
		Thread.sleep(1000);
		//otherRealtionshipFunctionality();
		click(empOvrAddBtn);
		thread();
		verifyAssert(empFirstName);
		takeScreenshot();
		Thread.sleep(1000);
	}
	
	@Test(priority=2)
	void addEmployeeDetails() throws BiffException, IOException, InterruptedException
	{
		if(empList.size() == 0)
		{
			getEmpSheetNameFromExcel();
			thread();
			
			for(int row=1; row<emp.getRows(); row++)
			{
				EmployeeDetails employeeDetails = new EmployeeDetails();
				
				for(int col=0; col<emp.getColumns(); col++)
				{
					if(col == 0)
						employeeDetails.setFirstName(emp.getCell(col, row).getContents());
					if(col == 1)
						employeeDetails.setLastName(emp.getCell(col, row).getContents());
					if(col == 2)
						employeeDetails.setTitle(emp.getCell(col, row).getContents());
					if(col == 3)
						employeeDetails.setEmployeeId(emp.getCell(col, row).getContents());
					if(col == 6)
						employeeDetails.setDepartment(emp.getCell(col, row).getContents());
					if(col == 7)
						employeeDetails.setEmail(emp.getCell(col, row).getContents());
					if(col == 8)
						employeeDetails.setBusinessPhone(emp.getCell(col, row).getContents());
					if(col == 9)
						employeeDetails.setMobile(emp.getCell(col, row).getContents());
					if(col == 10)
						employeeDetails.setResidenceNumber(emp.getCell(col, row).getContents());					
					if(col == 11)
						employeeDetails.setOfficeNumber(emp.getCell(col, row).getContents());
					if(col == 12)
						employeeDetails.setExtn(emp.getCell(col, row).getContents());
					if(col == 13)
						employeeDetails.setAddress(emp.getCell(col, row).getContents());
					if(col == 14)
						employeeDetails.setCountry(emp.getCell(col, row).getContents());
					if(col == 15)
						employeeDetails.setState(emp.getCell(col, row).getContents());
					if(col == 16)
						employeeDetails.setCity(emp.getCell(col, row).getContents());
					if(col == 17)
						employeeDetails.setZipcode(emp.getCell(col, row).getContents());
					if(col == 18)
						employeeDetails.setSecondaryEmail(emp.getCell(col, row).getContents());
					if(col == 19)
						employeeDetails.setSecondaryMobile(emp.getCell(col, row).getContents());					
				}
				
				empList.add(employeeDetails);
			}
		}
		
		Random random = new Random();
		int rempNm = random.nextInt(emp.getRows()-1)+1;
		
		if(empList.size() > 0)
		{
			EmployeeDetails employeeDetails = empList.get(rempNm);
			
			//	First Name
			firstName = employeeDetails.getFirstName().trim();
			enterText(empFirstName, firstName);			
			
			//	Last Name
			lastName = employeeDetails.getLastName().trim();
			enterText(empLastname, lastName);			
			
			//	Title
			title = employeeDetails.getTitle().trim();
			enterText(empTitle, title);			
			
			//	Employee Id
			enterText(empEmployeeId, employeeDetails.getEmployeeId());			
			
			//	Employee Status
			click(empEmployeeStatusArrow);
			getTotalValuesIndd(empEmployeeStatusCnt);
			if(totalDDValCount > 1)
			{
				Random selectEmpStatus = new Random();
				int empStatus = selectEmpStatus.nextInt(totalDDValCount-1)+1;
				employeeStatus = driver.findElement(By.id("rsc_status_chzn_o_"+empStatus)).getText();
				enterText(empEmployeeStatusSearchBox, employeeStatus);
				enterKeyInKyBord(empEmployeeStatusSearchBox);
			}						
			
			//	Employee Type
			click(empEmployeeTypeArrow);
			getTotalValuesIndd(empEmployeeTypeCnt);
			if(totalDDValCount > 1)
			{
				Random selectEmpType = new Random();
				int empType = selectEmpType.nextInt(totalDDValCount-1)+1;
				employeeType = driver.findElement(By.id("resourcetype_chzn_o_"+empType)).getText();
				enterText(empEmployeeTypeSearchBox, employeeType);
				thread();
				enterKeyInKyBord(empEmployeeTypeSearchBox);
			}						
			
			//	Department
			enterText(empDepartment, employeeDetails.getDepartment());			
			
			//	Email
			emailId = employeeDetails.getEmail();
			enterText(empEmail, emailId);			
			
			//	Business Phone
			businessPhone = employeeDetails.getBusinessPhone();
			enterText(empBusinessPhone, businessPhone);			
			
			//	Mobile
			mobileN0 = employeeDetails.getMobile();
			enterText(empMobile, mobileN0);			
			
			//	Residence Number
			enterText(empResidenceNumber, employeeDetails.getResidenceNumber());			
			
			//	Office Number
			enterText(empOfficeNumber, employeeDetails.getOfficeNumber());			
			
			//	Office Extn
			enterText(empOfficeExtnNo, employeeDetails.getExtn());			
			
			//	Address
			enterText(empAddress, employeeDetails.getAddress());			
			
			//	Country
			click(empCountryDDArrow);
			enterText(empCountryDDSrchBox, employeeDetails.getCountry());
			enterKeyInKyBord(empCountryDDSrchBox);
			thread();
			
			//	State
			click(empStateDDArrow);
			enterText(empStateDDSrchBox, employeeDetails.getState());			
			enterKeyInKyBord(empStateDDSrchBox);
			thread();
			
			//	City
			enterText(empCity, employeeDetails.getCity());			
			
			//	Zip Code
			enterText(empZipcode, employeeDetails.getZipcode());			
			
			//	Secondary Email
			enterText(empSecEmail, employeeDetails.getSecondaryEmail());			
			
			//	Secondary Mobile No
			enterText(empSecMobileNo, employeeDetails.getSecondaryMobile());			
			
			//	Location
			click(empLocationArrow);
			getTotalValuesIndd(empLocationCnt);
			if(totalDDValCount > 1)
			{
				Random selectLocation = new Random();
				int empLoc = selectLocation.nextInt(totalDDValCount-1)+1;
				location = driver.findElement(By.id("location_chzn_o_"+empLoc)).getText();
				enterText(empLocationSearchBox, location);
				enterKeyInKyBord(empLocationSearchBox);
				thread();
			}						
			
			//	Alternate Employee
			webElement(empAlternateEmployeeArrow);
			scrollInnerScrollBar(webelementFrame);
			click(empAlternateEmployeeArrow);
			getTotalValuesIndd(empAlternateEmployeeCnt);
			if(totalDDValCount > 1)
			{
				Random selectAlterEmp = new Random();
				int empAlterEmp = selectAlterEmp.nextInt(totalDDValCount-1)+1;
				alternateEmployee = driver.findElement(By.id("alternate_resource_chzn_o_"+empAlterEmp)).getText().split(",")[0];
				enterText(empAlternateEmployeeSearchBox, alternateEmployee);
				enterKeyInKyBord(empAlternateEmployeeSearchBox);
				thread();
			}
			webElement(empSubmitBtn);
			scrollInnerScrollBar(webelementFrame);
			
			click(empSubmitBtn);
			takeScreenshot();  
			thread();
			//		Check Terminated Pop Up
			try
			{
				verifyObjDisplay(empTerminatedPopup);
				if(chkObjDisplay == true)
				{
					click(empTerminatedPpOkBtn);
					thread();
				}				
			}
			catch(NoSuchElementException no)
			{
				no.printStackTrace();
			}
		} thread();
		
		getObjectText(msgNotificationBar);
		if(getActualObjectTxt.equals(firstName+" "+lastName+" Successfully Added"))
		{
			System.out.println("perform add operation");
		}
		else
		{
			verifyAddNewEmployeePage();
			thread();
			addEmployeeDetails();
		}
		
		 }
	
	@Test(priority=3)
	void verifyAddedEmpNameInListView()
	{
		for(int i=1; i<11; i++)
		{
			String getAddedEmpName = driver.findElement(By.xpath("//table[@id='resource_table']//tr["+i+"]/td[2]")).getText();
			
			if(getAddedEmpName.contains(firstName+" "+lastName))
			{
				for(int j=2; j<10; j++)
				{
					String chkAddedDtls = driver.findElement(By.xpath("//table[@id='resource_table']//tr["+i+"]/td["+j+"]")).getText();
					
					if(j == 2)
						verifyAssertEquals(firstName+" "+lastName, chkAddedDtls);
					if(j == 3)
						verifyAssertEquals(title, chkAddedDtls);
					if(j == 4)
						verifyAssertEquals(employeeType, chkAddedDtls);
					if(j == 5)
						verifyAssertEquals(businessPhone, chkAddedDtls);
					if(j == 6)
						verifyAssertEquals(mobileN0, chkAddedDtls);
					if(j == 7)
						verifyAssertEquals(emailId, chkAddedDtls);
					if(j == 8)
						verifyAssertEquals(location, chkAddedDtls);
					if(j == 9)
						verifyAssertEquals(employeeStatus, chkAddedDtls);
					
					break;
				}
			}			
		}
	}
	
	@Test(priority=4)
	void mapEmployeesToTask() throws InterruptedException, IOException
	{
		
		//	Check Employee Name in Mapping pop up
		webElement(rltnAddRemoveRltnsBtn);
		scrollInnerScrollBar(webelementFrame);
        Thread.sleep(1000);
		click(tasksTb);
		thread();
		click(rltnAddRemoveRltnsBtn);
		thread();
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Tasks To "+firstName+" "+lastName, getActualObjectTxt);
		
		//	Mapping
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
		//	Verify Employee Name mapped successfully message
		getObjectText(msgNotificationBar);
		verifyAssertEquals(firstName+" "+lastName+" successfully mapped to Tasks", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=5)
	void searchMappedTaskNames() throws IOException, InterruptedException
	{
		//	Search with Valid Details
		getTotalValuesIndd(empRltnTasksTblTtl);
		Random random = new Random();
		int rtskNm = random.nextInt(totalDDValCount-1)+1;
		String getTaskName = driver.findElement(By.xpath("//table[@id='rel_task']//tr["+rtskNm+"]/td")).getText();
		enterText(empRltnTaskSearchbox, getTaskName);
		
		getObjectText(empRltnTaskSearchRslt);
		verifyAssertEquals(getTaskName, getActualObjectTxt);
		takeScreenshot();
		clear(empRltnTaskSearchbox);
		enterKeyInKyBord(empRltnTaskSearchbox);
		thread();
		
		//	Search with Invalid Details
		enterText(empRltnTaskSearchbox, "Inv Task Name");
		
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(empRltnTaskSearchbox);
		enterKeyInKyBord(empRltnTaskSearchbox);
		thread();
	}
	
	@Test(priority=6)
	void mapEmployeesToBusinessFunctions() throws InterruptedException, IOException
	{
		click(businessFunctionsTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Business Functions To "+firstName+" "+lastName, getActualObjectTxt);
		
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(firstName+" "+lastName+" successfully mapped to Business Functions", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=7)
	void searchMappedBusinessFunctions() throws IOException, InterruptedException
	{
		//		Search with Valid Details
		getTotalValuesIndd(empRltnBusinesFuncTblTtl);
		Random random = new Random();
		int rBusiFunc = random.nextInt(totalDDValCount-1)+1;
		String getBusinessFunctionName = driver.findElement(By.xpath("//table[@id='rel_process']//tr["+rBusiFunc+"]/td")).getText();
		enterText(empRltnBusinesFuncSearchBox, getBusinessFunctionName);
		try
		{
		getObjectText(empRltnBusinessFuncSearchRslt);
		verifyAssertEquals( getBusinessFunctionName, getActualObjectTxt);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		takeScreenshot();
		clear(empRltnBusinesFuncSearchBox);
		enterKeyInKyBord(empRltnBusinesFuncSearchBox);
		thread();
		
		//	Search with Invalid Details
		enterText(empRltnBusinesFuncSearchBox, "Inv Bus Func Name");
		enterKeyInKyBord(empRltnBusinesFuncSearchBox);
		thread();
		
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound,getActualObjectTxt);
		takeScreenshot();
		clear(empRltnBusinesFuncSearchBox);
		enterKeyInKyBord(empRltnBusinesFuncSearchBox);
		thread();
	}
	
	@Test(priority=8)
	void mapEmployeesToLocations() throws IOException, InterruptedException
	{
		click(locationsTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Locations To "+firstName+" "+lastName, getActualObjectTxt);
		
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(firstName+" "+lastName+" successfully mapped to Locations", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=9)
	void searchMappedLocations() throws IOException, InterruptedException
	{
//		Search with Valid Details
		getTotalValuesIndd(empRltnLocationTblTtl);
		Random random = new Random();
		int rLocNm = random.nextInt(totalDDValCount-1)+1;
		String getLocationName = driver.findElement(By.xpath("//table[@id='rel_facilities']/tbody/tr["+rLocNm+"]/td")).getText();
		enterText(empRltnLocationSearchBox, getLocationName);
		
		
		getObjectText(empRltnLocationSearchRslt);
		verifyAssertEquals(getLocationName, getActualObjectTxt);
		takeScreenshot();
		clear(empRltnLocationSearchBox);
		enterKeyInKyBord(empRltnLocationSearchBox);
		thread();
		
		//	Search with Invalid Details
		enterText(empRltnLocationSearchBox, "Inv Location Name");
		enterKeyInKyBord(empRltnLocationSearchBox);
		thread();
		getObjectText(noRecordsFoundMsg);
		thread();
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		thread();
		clear(empRltnLocationSearchBox);
		enterKeyInKyBord(empRltnLocationSearchBox);
		thread();
	}
	
	
	@Test(priority=10)
	void mapEmployeesToInsurance() throws InterruptedException, IOException
	{
		click(empInsuranceTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Insurance To "+firstName+" "+lastName, getActualObjectTxt);
		
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(firstName+" "+lastName+" successfully mapped to Insurance", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=11)
	void searchMappedInsuranceName() throws IOException, InterruptedException
	{
		//		Search with Valid Details
		getTotalValuesIndd(empRltnInsuranceTblTtl);
		Random random = new Random();
		int rInsNm = random.nextInt(totalDDValCount-1)+1;
		String getInsuranceName = driver.findElement(By.xpath("//table[@id='rel_insurances']/tbody/tr["+rInsNm+"]/td")).getText();
		enterText(empRltnInsuranceSearchBox, getInsuranceName);
		
		
		getObjectText(empRltnInsuranceSearchRslt);
		verifyAssertEquals(getInsuranceName, getActualObjectTxt);
		takeScreenshot();
		clear(empRltnInsuranceSearchBox);
		enterKeyInKyBord(empRltnInsuranceSearchBox);
		thread();
		
		//	Search with Invalid Details
		enterText(empRltnInsuranceSearchBox, "Inv Insurance Name");
		enterKeyInKyBord(empRltnInsuranceSearchBox);
		thread();
		getObjectText(noRecordsFoundMsg);
		thread();
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		thread();
		clear(empRltnInsuranceSearchBox);
		enterKeyInKyBord(empRltnInsuranceSearchBox);
		thread();
	}
	
	@Test(priority=12)
	void mapEmployeesToTeams() throws InterruptedException, IOException
	{
		click(teamsTb);
		thread();
		click(rltnAddRemoveRltnsBtn);
		thread();
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Teams To "+firstName+" "+lastName, getActualObjectTxt);
		
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(firstName+" "+lastName+" successfully mapped to Teams", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=13)
	void searchMappedTeams() throws IOException, InterruptedException
	{
		//		Search with Valid Details
		getTotalValuesIndd(empRltnTeamsTblTtl);
		Random random = new Random();
		int rTms = random.nextInt(totalDDValCount-1)+1;
		String getTeamsName = driver.findElement(By.xpath("//table[@id='rel_resourcegroup']/tbody/tr["+rTms+"]/td")).getText();
		enterText(empRltnTeamsSearchBox, getTeamsName);
		
		
		getObjectText(empRltnTeamsSearchRslt);
		verifyAssertEquals( getTeamsName, getActualObjectTxt);
		takeScreenshot();
		clear(empRltnTeamsSearchBox);
		enterKeyInKyBord(empRltnTeamsSearchBox);
		thread();
		
		
		//	Search with Invalid Details
		enterText(empRltnTeamsSearchBox, "Inv TeamName");
		getObjectText(noRecordsFoundMsg);
		thread();
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
	    takeScreenshot();
	    thread();
		clear(empRltnTeamsSearchBox);
		enterKeyInKyBord(empRltnTeamsSearchBox);
		Thread.sleep(20);
	}
	
	
	
	@Test(priority=14)
	void verifyEmployeeNameInRelationshipTitleEditDtls() throws InterruptedException, BiffException, IOException
	{
		
		getTotalValuesIndd(empEmployeesListViewTtl);
		Random random = new Random();
		int rrow = random.nextInt(totalDDValCount-1)+1;
		
		
		WebElement rEmpName = driver.findElement(By.xpath("//table[@id='resource_table']//tr["+rrow+"]/td[2]"));
		String EmpName = rEmpName.getText();
		rEmpName.click();
		
		//	Verify Employee Name in Relationship Title  
		getObjectText(empRelationshipTitleName);
		thread();
		//System.out.println("Full Name "+getActualObjectTxt.split(" for ")[0]);
		verifyAssertEquals(EmpName, getActualObjectTxt.substring(4).substring(0, getActualObjectTxt.substring(4).indexOf(" ")));
		driver.findElement(By.xpath("//table[@id='resource_table']/tbody/tr["+rrow+"]/td[11]/a[1]")).click();
		thread();
		
		//	Verify Employee Name in Edit Screen Relationship Title
		getObjectText(empRelationshipTitleName);
		verifyAssertEquals(EmpName, getActualObjectTxt.substring(4).substring(0, getActualObjectTxt.substring(4).indexOf(" ")));
		
		webElement(empCancelBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(empCancelBtn);
		thread();
		
	}
	
		@Test(priority=15)
		void editEmployeeDetails() throws InterruptedException, BiffException, IOException
		{
		    click( empEditBtn);
		    thread();
			
			webElement(empClearBtn);
			scrollInnerScrollBar(webelementFrame);
			click(empClearBtn);
			thread();
		    Random random1 = new Random();
		    int rempNm1 = random1.nextInt(emp.getRows()-1)+1;
		if(empList.size() > 0)
		{
			EmployeeDetails employeeDetails = empList.get(rempNm1);
			getEmpSheetNameFromExcel();
			
			//	First Name
			modFirstName = employeeDetails.getFirstName().trim();
			enterText(empFirstName, modFirstName);			
			
			//	Last Name
			modLastName = employeeDetails.getLastName().trim();
			enterText(empLastname, modLastName);	
			
			//	Title
			modTitle = employeeDetails.getTitle().trim();
			enterText(empTitle, modTitle);	
			
			//	Employee Id
			enterText(empEmployeeId, employeeDetails.getEmployeeId());	
			
	    	//	Employee Status
			click(empEmployeeStatusArrow);
			getTotalValuesIndd(empEmployeeStatusCnt);
			Random selectEmpStatus = new Random();
			int empStatus = selectEmpStatus.nextInt(totalDDValCount-1)+1;
			modEmployeeStatus = driver.findElement(By.id("rsc_status_chzn_o_"+empStatus)).getText();
			enterText(empEmployeeStatusSearchBox, modEmployeeStatus);
			enterKeyInKyBord(empEmployeeStatusSearchBox);	
			
			//	Employee Type
			click(empEmployeeTypeArrow);
			getTotalValuesIndd(empEmployeeTypeCnt);
			Random selectEmpType = new Random();
			int empType = selectEmpType.nextInt(totalDDValCount-1)+1;
			modEmployeeType = driver.findElement(By.id("resourcetype_chzn_o_"+empType)).getText();
			enterText(empEmployeeTypeSearchBox, modEmployeeType);
			enterKeyInKyBord(empEmployeeTypeSearchBox);	
			
			//	Department
			enterText(empDepartment, employeeDetails.getDepartment());		
			
			//	Email
			modEmailId = employeeDetails.getEmail();
			enterText(empEmail,modEmailId);		
			
			//	Business Phone
			modBusinessPhone = employeeDetails.getBusinessPhone();
			enterText(empBusinessPhone, modBusinessPhone);		
			
			//	Mobile
			modMobileN0 = employeeDetails.getMobile();
			enterText(empMobile, modMobileN0);		
			
			//	Residence Number
			enterText(empResidenceNumber, employeeDetails.getResidenceNumber());	
			
			//	Office Number
			enterText(empOfficeNumber, employeeDetails.getOfficeNumber());			
			
			//	Office Extn
			enterText(empOfficeExtnNo, employeeDetails.getExtn());			
			
			//	Address
			enterText(empAddress, employeeDetails.getAddress());
			
            //	Country
					click(empCountryDDArrow);
					enterText(empCountryDDSrchBox, employeeDetails.getCountry());
					enterKeyInKyBord(empCountryDDSrchBox);
					
			//	State
					click(empStateDDArrow);
					enterText(empStateDDSrchBox, employeeDetails.getState());			
					enterKeyInKyBord(empStateDDSrchBox);
					
		/*	//	Country
			enterText(empCountry, employeeDetails.getCountry());			
			//	State
			enterText(empState, employeeDetails.getState());	*/	
					
			//	City
    	    enterText(empCity, employeeDetails.getCity());		
		    
			//	Zip Code
			enterText(empZipcode, employeeDetails.getZipcode());
			
			//	Secondary Email
			enterText(empSecEmail, employeeDetails.getSecondaryEmail());
			
			//	Secondary Mobile No
			enterText(empSecMobileNo, employeeDetails.getSecondaryMobile());
			
			//	Location
			click(empLocationArrow);
			getTotalValuesIndd(empLocationCnt);
			Random selectLocation = new Random();
			int empLoc = selectLocation.nextInt(totalDDValCount-1)+1;
			modLocation = driver.findElement(By.id("location_chzn_o_"+empLoc)).getText();
			enterText(empLocationSearchBox, modLocation);
			enterKeyInKyBord(empLocationSearchBox);
			thread();
			
			//	Alternate Employee
			click(empAlternateEmployeeArrow);
			getTotalValuesIndd(empAlternateEmployeeCnt);
			Random selectAlterEmp = new Random();
			int empAlterEmp = selectAlterEmp.nextInt(totalDDValCount-1)+1;
			modAlternateEmployee = driver.findElement(By.id("alternate_resource_chzn_o_"+empAlterEmp)).getText().split(",")[0];
			enterText(empAlternateEmployeeSearchBox, modAlternateEmployee);
			enterKeyInKyBord(empAlternateEmployeeSearchBox);
			thread();
			webElement(empSubmitBtn);
			scrollInnerScrollBar(webelementFrame);
			thread();
			click(empSubmitBtn);
			thread();
			
		}
		thread();
		//	Verify Employee Name Successfully Updated message
		getObjectText(msgNotificationBar);
		if(getActualObjectTxt.equals(modFirstName+" "+modLastName+" Successfully Updated"))
		{
			System.out.println("perform add operation");
		}
		else
		{
			click(employeesInMainMenu);
			thread();
			editEmployeeDetails();
			//click(empClearBtn);
			//thread();
			//addEmployeeDetails();
		}
		
   /*try
		{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(modFirstName+" "+modLastName+" Successfully Updated", getActualObjectTxt);
		}
		catch(Exception e)
		{
			click(empClearBtn);
			thread();
			addEmployeeDetails();
		}*/
	}
		
	
	@Test(priority=16)
	void verifyModifiedDetailsInEmpListView()
	{
		for(int i=1; i<10; i++)
		{
			String getModEmpName = driver.findElement(By.xpath("//table[@id='resource_table']//tr["+i+"]/td[2]")).getText();
			
			if(getModEmpName.contains(modFirstName+" "+modLastName))
			{
				for(int j=2; j<10; j++)
				{
					String chkModDtls = driver.findElement(By.xpath("//table[@id='resource_table']//tr["+i+"]/td["+j+"]")).getText();
					
					if(j == 2)
						verifyAssertEquals(modFirstName+" "+modLastName, chkModDtls);
					if(j == 3)
						verifyAssertEquals(modTitle, chkModDtls);
					if(j == 4)
						verifyAssertEquals(modEmployeeType, chkModDtls);
					if(j == 5)
						verifyAssertEquals(modBusinessPhone, chkModDtls);
					if(j == 6)
						verifyAssertEquals(modMobileN0, chkModDtls);
					if(j == 7)
						verifyAssertEquals(modEmailId, chkModDtls);
					if(j == 8)
						verifyAssertEquals(modLocation, chkModDtls);
					if(j == 9)
						verifyAssertEquals(modEmployeeStatus, chkModDtls);
					
					break;
				}
			}			
		}		
	}
	
	@Test(priority=17)
	void employeesListViewPagination() throws IOException, InterruptedException
	{
		getTotalValuesIndd(empEmployeesListViewPagination);
		
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
			driver.findElement(By.xpath("//div[@id='resource_table_wrapper']/div[2]/div[2]/div/ul/li["+randomPge+"]/a")).click();
			thread();
			takeScreenshot();
			click(pagiStartArw);
			thread();
		}
	}
	
	@Test(priority=18)
	void verifySelectAllChkBox() throws IOException, InterruptedException
	{
		click(empSelectAllChkBox);
		takeScreenshot();
		
		getTotalValuesIndd(empEmployeesListViewTtl);
		for(int i=1; i<=totalDDValCount; i++)
		{
			boolean chkd = driver.findElement(By.xpath("//table[@id='resource_table']/tbody/tr["+i+"]/td/div/span/input")).isEnabled();
			Assert.assertEquals(true, chkd);					
		}		
		click(empSelectAllChkBox);
		thread();
	}
	
	@Test(priority=19)
	void verifyClearndCancelBtnFunc() throws InterruptedException
	{
		getTotalValuesIndd(empEmployeesListViewTtl);
		Random random = new Random();
		int rrow = random.nextInt(totalDDValCount-1)+1;
		WebElement rEmpName = driver.findElement(By.xpath("//table[@id='resource_table']//tr["+rrow+"]/td[2]"));
		rEmpName.click();
		
		driver.findElement(By.xpath("//table[@id='resource_table']//tr["+rrow+"]/td[11]/a[1]")).click();
		thread();
		
		webElement(empClearBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(empClearBtn);
		thread();
		
		//	First Name
		getAttributePh(empFirstName);
		verifyAssertEquals(phFirstName, getAttribtePh);		
		//	Last Name
		getAttributePh(empLastname);
		verifyAssertEquals(phLastName, getAttribtePh);		
		//	Title
		getAttributePh(empTitle);
		verifyAssertEquals(phTitle, getAttribtePh);		
		//	Employee Id
		getAttributePh(empEmployeeId);
		verifyAssertEquals(phEmployeeId, getAttribtePh);		
		//	Employee Status
		getObjectText(empEmployeeStatusDefTxt);
		verifyAssertEquals(defEmployeeStatusTxt, getActualObjectTxt);		
		//	Employee Type
		getObjectText(empEmployeeTypeDefTxt);
		verifyAssertEquals(defEmployeeTypeTxt, getActualObjectTxt);
		//	Department
		getAttributePh(empDepartment);
		verifyAssertEquals(phDepartment, getAttribtePh);		
		//	E-mail
		getAttributePh(empEmail);
		verifyAssertEquals(phEmail, getAttribtePh);		
		//	Business Phone
		getAttributePh(empBusinessPhone);
		verifyAssertEquals(phBusinessPhone, getAttribtePh);		
		//	Mobile
		getAttributePh(empMobile);
		verifyAssertEquals(phMobile, getAttribtePh);		
		//	Residence Number
		getAttributePh(empResidenceNumber);
		verifyAssertEquals(phResidenceNumber, getAttribtePh);
		//	Office Number
		getAttributePh(empOfficeNumber);
		verifyAssertEquals(phOfficeNumber, getAttribtePh);
		//	Office Extn
		getAttributePh(empOfficeExtnNo);
		verifyAssertEquals(phOfficeExtn, getAttribtePh);
		//	Address
		getAttributePh(empAddress);
		verifyAssertEquals(phAddress, getAttribtePh);
		
		//Country
		getObjectText(empCountry);
		verifyAssertEquals(phCountry, getActualObjectTxt);
		//	State
		getObjectText(empState);
		verifyAssertEquals(phState, getActualObjectTxt);   
		
		/*//	Country
		getAttributePh(empCountry);
		verifyAssertEquals(phCountry, getAttribtePh);
		//	State
		getAttributePh(empState);
		verifyAssertEquals(phState, getAttribtePh);
		*/
		
		//	City
		getAttributePh(empCity);
		verifyAssertEquals(phCity, getAttribtePh);
		//	Zip Code
		getAttributePh(empZipcode);
		verifyAssertEquals(phZipcode, getAttribtePh);
		//	Secondary Email
		getAttributePh(empSecEmail);
		verifyAssertEquals(phSecondaryEmail, getAttribtePh);
		//	Secondary Mobile
		getAttributePh(empSecMobileNo);
		verifyAssertEquals(phSecondaryMobile, getAttribtePh);
		//	Location
		getObjectText(empLocationDefTxt);
		verifyAssertEquals(defLocationTxt, getActualObjectTxt);
		//	Alternate Employee
		getObjectText(empAlternateEmployeeDefTxt);
		verifyAssertEquals(defAlternateEmployeeTxt, getActualObjectTxt);
		
		click(empCancelBtn);
		thread();
		verifyAssert(empEmployeesSearchBox);
		thread();
	}
	
	@Test(priority=20)
	void verifyShowEntriesDropdown() throws InterruptedException, IOException
	{
		try
		{
			getObjectText(empListViewRecsInfo);
			thread();
			
				String actVal;
				if(getActualObjectTxt.contains(","))
				{
					//System.out.println("inside if");
					String entries[]=getActualObjectTxt.split("of ");
					String val[]=entries[1].split(",");
					String val1=val[1].split(" ")[0];
					actVal=val[0].concat(val1);
		        }
				else
				{
					//System.out.println("inside else");
					actVal=getActualObjectTxt.split("of ")[1].split(" ")[0].trim();
				}
			
			if(Integer.parseInt(actVal)>10)
			{
				selectTextFromDropdown(empListViewLength, "25");
				thread();
				takeScreenshot();
				
				selectTextFromDropdown(empListViewLength, "10");
				thread();
			}
		}
		catch(WebDriverException e)
		{
			System.out.println("Pagination is not available in Employees List View. It contains 10 or less than 10 records");
		}
	}
	
	
	/* @Test(priority=21)
     void Documentupload() throws IOException, InterruptedException, AWTException
     {
     	
		    click(docuemntTb);
     	    thread();
     	    
     	    //Scroll down the page
			webElement(choosefiles);
			scrollInnerScrollBar(webelementFrame);
			
		    //click choosefiles button 
			click(choosefiles);
			thread();
			takeScreenshot();
     
         //put filepath
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
         thread();
         thread();
        }*/
     
/*	 @Test(priority=22)
	 void verifyViewpage() throws IOException, InterruptedException
	 {
		    thread();
		    click(empFstViewBtn);
			takeScreenshot();
	     	getObjectText(empViewPopup);
	     	thread();
	     	click(empViewCloseBtn);
	     	thread();
	 }*/
	 
	 
	 @Test(priority=23)
		void otherRealtionshipFunctionality() throws InterruptedException, IOException
		{
			click(otherRelationTb);
			thread();
			
			getTotalValuesIndd(otherRltnTblTtl);
			//System.out.println("Class name "+driver.findElement(By.xpath("//table[@id='rel_other']/tbody/tr/td")).getAttribute("class"));
			
			if(totalDDValCount==1 && driver.findElement(By.xpath("//table[@id='rel_other']/tbody/tr/td")).getAttribute("class").equals("dataTables_empty"))
			{
				System.out.println("No other relationships for this Employee");
			}
			if(totalDDValCount>=1 && !driver.findElement(By.xpath("//table[@id='rel_other']/tbody/tr/td")).getAttribute("class").equals("dataTables_empty"))
			{
			Random random = new Random();
			int rOtrRln = random.nextInt(totalDDValCount);
			
			if(rOtrRln==0)
			{
				rOtrRln=+1;
			}
			
			String othrRln=driver.findElement(By.xpath("//table[@id='rel_other']/tbody/tr["+rOtrRln+"]/td[1]")).getText();
			thread();
			
			enterText(otherRltnSearchBox,othrRln);
			enterKeyInKyBord(otherRltnSearchBox);
			thread();
			
			getObjectText(otherRltnSearchRslt);
			verifyAssertEquals(othrRln, getActualObjectTxt);
			takeScreenshot();
			clear(otherRltnSearchBox);
			enterKeyInKyBord(otherRltnSearchBox);
			thread();
			
			//invalid search
			enterText(otherRltnSearchBox,"invalid relation");
			enterKeyInKyBord(otherRltnSearchBox);
			thread();
			
			getObjectText(otherRltnSearchRslt);
			verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
			takeScreenshot();
			clear(otherRltnSearchBox);
			enterKeyInKyBord(otherRltnSearchBox);
			thread();
			
			//getObjectText(empRelationshipTitleName);
			getObjectText(By.xpath(".//*[@id='content']/div[2]/div[2]/div[1]/h2"));
			thread();
			String name[]=getActualObjectTxt.split(" ");
			String fullName=name[2].concat(" ").concat(name[3]);
			
			
			//Individual Unrelate 
			
			String recordName=driver.findElement(By.xpath("//table[@id='rel_other']/tbody/tr["+rOtrRln+"]/td[2]")).getText();
			thread();
			//WebElement unRelateBtn=driver.findElement(By.xpath("//table[@id='rel_other']/tbody/tr["+rOtrRln+"]/td[5]"));
			//unRelateBtn.click();
			click(By.xpath("//table[@id='rel_other']/tbody/tr["+rOtrRln+"]/td[5]"));
			thread();
			
			verifyAssert(deleteConfPopup);
			getObjectText(otherRltnPopUpText);
			//verifyAssertEquals("Are you sure to unrelate "+firstName+" "+lastName+" from "+recordName+"?",getActualObjectTxt);
			verifyAssertEquals("Confirm to unrelate "+fullName+" from "+recordName+"?",getActualObjectTxt);
	//		click(otherCancelBtn);
	//		thread();
		
			click(delConfOkBtn);
		    thread();
		    click(empOtrRltnAssignArrow);
		    thread();
		    getTotalValuesIndd(empOtrTotalCnt);
		    Random rand=new Random();
		    int rval=rand.nextInt(totalDDValCount-1)+1;
		    String empName=driver.findElement(By.id("new_employee_list_chzn_o_"+rval)).getText();
		    enterText(empOtrSearchBox,empName);
		    enterKeyInKyBord(empOtrSearchBox);
		    click(skipnContBtn);
		    thread();
		    
			getObjectText(msgNotificationBar);
			verifyAssertEquals(fullName+" successfully unrelated from "+recordName, getActualObjectTxt);
			
					
			//Unrelate Functionality
			
			click(unRelateAllBtn);
			thread();					
			getObjectText(otherRltnPopUpText);
			verifyAssertEquals("Confirm to unrelate "+fullName+" from all modules?",getActualObjectTxt);
			//verifyAssertEquals("Confirm to unrelate "+firstName+" "+lastName+" from all modules?",getActualObjectTxt);
			
		//	click(otherCancelBtn);
		//	thread();
			
			click(delConfOkBtn);
		    thread();
		    click(empOtrRltnAssignArrow);
		    thread();
		    getTotalValuesIndd(empOtrTotalCnt);
		    Random randv=new Random();
		    int rvalue=randv.nextInt(totalDDValCount-1)+1;
		    String empName1=driver.findElement(By.id("new_employee_list_chzn_o_"+rvalue)).getText();
		    enterText(empOtrSearchBox,empName1);
		    enterKeyInKyBord(empOtrSearchBox);
		    click(skipnContBtn);
		    thread();
		    
			getObjectText(msgNotificationBar);
			verifyAssertEquals(fullName+" successfully unrelated from all modules", getActualObjectTxt);
			
			
		
			}
					
			
		}
	
	/*@Test(priority=24)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
}
