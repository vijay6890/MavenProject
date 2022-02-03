package Scripts;

/******************************************************************************************************************************
 * 
 * @author Rahul Nair
 * 
 
*******************************************************************************************************************************/

import static Config.TakScreenshot.takeScreenshot;


import static ObjectRepository.Locations.*;
import static ObjectRepository.TaskGroups.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Employees.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Assets.*;
import static ObjectRepository.Insurance.*;
import static ObjectRepository.BusinessFunctions.*;

import static ObjectRepository.Teams.*;
import static UIWrappers.UIObjects.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class SIBTwoWayMappingEmployees extends Page{
	String flag;
	String employeeName,employeeFirstName,employeeLastName,employeeTitle,employeeType,employeeBusinessPhone,employeeMobileNumber,employeeEmail,employeeStatus,employeeAlternateEmployee,employeeLocation;
	String taskName;
	String businessFunctionName;
	String locationName;
	String insuranceName;
	String teamName;
	
	
	
    LoginLogout ll = new LoginLogout();
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		waitForPageLoad();
		driver.findElement(By.id("cometchat_hide")).click();
	}
	
	@Test(priority=1)
	void employeesDataMappingValidations() throws InterruptedException, IOException{
		click(employeesInMainMenu);
		thread();
		Select employeesCountDD = new Select(driver.findElement(By.name("resource_table_length")));
		employeesCountDD.selectByVisibleText("100");
		List<WebElement> employeeList = driver.findElements(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr"));
		int employeeListSize = employeeList.size();
		Random random = new Random();
		int randomEmployeeValue = random.nextInt(employeeListSize);
		if(randomEmployeeValue==0){
			randomEmployeeValue = randomEmployeeValue + 1;
		}
		driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr"+"["+randomEmployeeValue+"]")).click();
		employeeFirstName = driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+randomEmployeeValue+"]//td[2]")).getText();
		System.out.println("Employee first name +++"+employeeFirstName);
		employeeLastName = driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+randomEmployeeValue+"]//td[3]")).getText();
		System.out.println("Employee last name +++"+employeeLastName);
		employeeName = employeeFirstName + " "+ employeeLastName;
		employeeTitle = driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+randomEmployeeValue+"]//td[4]")).getText();
		System.out.println("Employee Title +++"+employeeTitle);
		employeeType = driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+randomEmployeeValue+"]//td[5]")).getText();
		System.out.println("Employee Type +++"+employeeType);
		employeeBusinessPhone = driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+randomEmployeeValue+"]//td[6]")).getText();
		System.out.println("Employee Business phone +++"+employeeBusinessPhone);
		employeeMobileNumber = driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+randomEmployeeValue+"]//td[7]")).getText();
		System.out.println("Employee Mobile Number +++"+employeeMobileNumber);
		employeeEmail = driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+randomEmployeeValue+"]//td[8]")).getText();
		System.out.println("Employee Email +++"+employeeEmail);
		employeeLocation = driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+randomEmployeeValue+"]//td[9]")).getText();
		System.out.println("Employee Location +++"+employeeLocation);
		employeeStatus = driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+randomEmployeeValue+"]//td[10]")).getText();
		System.out.println("Employee Status +++"+employeeStatus);
		driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr["+randomEmployeeValue+"]//td[11]//a[@title='Edit']")).click();
		thread();
		scrollToBottom();
		employeeAlternateEmployee = driver.findElement(alternateEmployeeValue).getText();
		System.out.println("Employee alternate employee +++"+employeeAlternateEmployee);
		driver.findElement(By.xpath("//button[@class='btn validate-cancel {res_form}' and contains(text(),'Cancel')]")).click();
		thread();
		
		scrollToBottom();
		click(addRemoveRelationsBtn);
		thread();
		click(taskRightMoveAllArrowRltWindow);
		List<WebElement> tasksLeftRltWindowValuesList = driver.findElements(tasksLeftRltWindowValues);
		int tasksLeftRltWindowValuesListSize = tasksLeftRltWindowValuesList.size();
		Random random1 = new Random();
		int randomTaskValue = random1.nextInt(tasksLeftRltWindowValuesListSize);
		if(randomTaskValue==0){
			randomTaskValue = randomTaskValue+1;
		}
		String taskNameInsideRelationshipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).click();
		enterText(tasksLeftSearchTxtBoxRltWindow,taskNameInsideRelationshipWindow);
		thread();
		click(tasksLeftMoveBtnRltWindow);
		enterText(tasksRightSearchTxtBoxRltWindow,taskNameInsideRelationshipWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(employeeName+ " "+"successfully mapped to Tasks", getActualObjectTxt);
		thread();
		scrollToBottom();
		thread();
		taskName = driver.findElement(By.xpath("//table[@id='rel_task']//tbody[@role='alert']//span")).getText();
		
		click(businessFntTabInsuranceRltSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnBusinessFtnRltWindow);
		List <WebElement> leftBusinessFntRltWindowValuesList = driver.findElements(leftBusinessFntRltWindowValues);
		int leftBusinessFntRltWindowValuesListSize = leftBusinessFntRltWindowValuesList.size();
		Random random3 = new Random();
		int leftBusinessFntRltWindowValue = random3.nextInt(leftBusinessFntRltWindowValuesListSize);
		if(leftBusinessFntRltWindowValue==0){
			leftBusinessFntRltWindowValue = leftBusinessFntRltWindowValue + 1;
		}
		String businessFunctionNameInsideRelationShipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Business Functions')]/..//button[@class='btn moveall']/../../select//option"+"["+leftBusinessFntRltWindowValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Business Functions')]/..//button[@class='btn moveall']/../../select//option"+"["+leftBusinessFntRltWindowValue+"]")).click();
		enterText(businessFntLeftSearchTextBoxRltWindow,businessFunctionNameInsideRelationShipWindow);
		thread();
		click(leftMoveBtnBusinessFntRltWindow);
		enterText(businessFntRightSearchTextBoxRltWindow,businessFunctionNameInsideRelationShipWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(employeeName+" "+"successfully mapped to Business Functions", getActualObjectTxt);
		thread();
		scrollToBottom();
		thread();
		businessFunctionName = driver.findElement(By.xpath("//table[@id='rel_process']//tbody[@role='alert']//span")).getText();
		
		click(locationsTabEmpRelationshipSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnRelationWindow);
		List<WebElement> leftValueListInsideRelationShipWindow = driver.findElements(By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']/../../select//option"));
		int leftValueListInsideRelationShipWindowSize = leftValueListInsideRelationShipWindow.size();
		Random random2 = new Random();
		int locationNameValue = random2.nextInt(leftValueListInsideRelationShipWindowSize);
		if (locationNameValue == 0) {
			locationNameValue = locationNameValue + 1;
		}
		String LocationValueInsieRelationShipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']/../../select//option"+"["+locationNameValue+"]")).getText();	
		driver.findElement(By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']/../../select//option"+"["+locationNameValue+"]")).click();
		enterText(leftSearchTextBoxLocationsRelationWindow, LocationValueInsieRelationShipWindow);
		thread();
		click(locLeftMoveBtnRltWindow);
		enterText(rightSearchTextBoxLocationsRelationWindow, LocationValueInsieRelationShipWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(employeeName+ " " + "successfully mapped to Locations", getActualObjectTxt);
		thread();
		scrollToBottom();
		thread();
		locationName = driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody[@role='alert']//tr//td[1]")).getText();	
		
		click(insuranceTabRltSection);
		click(addRemoveRelationsBtn);
		thread();
		click(insuranceRightMoveAllBtnRltWindow);
		List<WebElement> insuranceLeftRltWindowValueList = driver.findElements(insuranceLeftRltValues);
		int insuranceLeftRltWindowValueListSize = insuranceLeftRltWindowValueList.size();
		Random random4 = new Random();
		int randomInsuranceValue = random4.nextInt(insuranceLeftRltWindowValueListSize);
		if(randomInsuranceValue==0){
			randomInsuranceValue=randomInsuranceValue+1;
		}
		String insuranceNameInsideRelationShipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Insurance')]/..//button[@class='btn moveall']/../../select//option"+"["+randomInsuranceValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Insurance')]/..//button[@class='btn moveall']/../../select//option"+"["+randomInsuranceValue+"]")).click();
		enterText(insuranceLeftSearchTxtBoxRltWindow,insuranceNameInsideRelationShipWindow);
		thread();
		click(insuranceLeftMoveArrowRltWindow);
		enterText(insuranceRightSearchTxtBoxRltWindow,insuranceNameInsideRelationShipWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(employeeName+" "+"successfully mapped to Insurance", getActualObjectTxt);
		thread();
		scrollToBottom();
		thread();
		insuranceName = driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody[@role='alert']//span")).getText();
		
		click(teamsTabTasksRltSection);
    	click(addRemoveRelationsBtn);
    	thread();
		click(rightMoveAllArrowTeamsRltWindow);
		List<WebElement> teamsLeftRltWindowValuesList = driver.findElements(teamsLeftRltWindowValues);
		int teamsLeftRltWindowValuesListSize = teamsLeftRltWindowValuesList.size();
		Random random5 = new Random();
		int randomTeamValue = random5.nextInt(teamsLeftRltWindowValuesListSize);
		if(randomTeamValue==0){
			randomTeamValue = randomTeamValue+1;
		}
		String teamNameInsideRelationShipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Teams')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTeamValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Teams')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTeamValue+"]")).click();
		enterText(teamsLeftSearchTxtBoxRltWindow,teamNameInsideRelationShipWindow);
		thread();
		click(teamsLeftMoveBtnRltWindow);
		enterText(teamsRightSearchTxtBoxRltWindow,teamNameInsideRelationShipWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(employeeName+" "+"successfully mapped to Teams", getActualObjectTxt);
		thread();
		scrollToBottom();
		thread();
		teamName = driver.findElement(By.xpath("//table[@id='rel_resourcegroup']//tbody[@role='alert']//span")).getText();
		
		scrollToTop();
		click(tasksInMainMenu);
		thread();
		Select tasksCountDD = new Select(driver.findElement(By.name("tasks_table_length")));
		tasksCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedTask = driver.findElement(By.xpath("//table[@id='tasks_table']//tbody//tr[@data-for='"+taskName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedTask);
		mappedTask.click();
		scrollToBottom();
		thread();
		Select employeesInnerCountDD = new Select(driver.findElement(By.name("rel_resources_length")));
		employeesInnerCountDD.selectByVisibleText("100");
		thread();
		validateEmployeeValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(businessFunctionsInMainMenu);
		thread();
		Select businessFunctionsCountDD = new Select(driver.findElement(By.name("process_table_length")));
		businessFunctionsCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedBusinessFunction = driver.findElement(By.xpath("//table[@id='process_table']//tbody//tr[@data-for='"+businessFunctionName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedBusinessFunction);
		mappedBusinessFunction.click();
		scrollToBottom();
		click(employeesTabInsuranceRelationSection);
		thread();
		scrollToBottom();
		Select employeesInnerCountDD1 = new Select(driver.findElement(By.name("rel_resources_length")));
		employeesInnerCountDD1.selectByVisibleText("100");
		thread();
		validateEmployeeValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(locationsInMainMenu);
		thread();
		Select locationsCountDD = new Select(driver.findElement(By.name("facilities_table_length")));
		locationsCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedLocation = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody//tr[@data-for='"+locationName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedLocation);
		mappedLocation.click();
		scrollToBottom();
		click(employeesTabInsuranceRelationSection);
		thread();
		scrollToBottom();
		Select employeesInnerCountDD2 = new Select(driver.findElement(By.name("rel_resources_length")));
		employeesInnerCountDD2.selectByVisibleText("100");
		thread();
		validateEmployeeValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(insuranceInMainMenu);
		thread();
		Select insuranceCountDD = new Select(driver.findElement(By.name("insurance_table_length")));
		insuranceCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedInsurance = driver.findElement(By.xpath("//table[@id='insurance_table']//tbody//tr[@data-for='"+insuranceName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedInsurance);
		mappedInsurance.click();
		scrollToBottom();
		click(employeesTabInsuranceRelationSection);
		thread();
		scrollToBottom();
		Select employeesInnerCountDD3 = new Select(driver.findElement(By.name("rel_resources_length")));
		employeesInnerCountDD3.selectByVisibleText("100");
		thread();
		validateEmployeeValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(teamsInMainMenu);
		thread();
		Select teamsCountDD = new Select(driver.findElement(By.name("resourcegroup_table_length")));
		teamsCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedTeam = driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tbody//tr[@data-for='"+teamName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedTeam);
		mappedTeam.click();
		scrollToBottom();
		thread();
		Select employeesInnerCountDD4 = new Select(driver.findElement(By.name("rel_resources_length")));
		employeesInnerCountDD4.selectByVisibleText("100");
		thread();
		validateEmployeeValues();
		verifyAssertEquals(flag,"true");
	}
	
	public String validateEmployeeValues(){
		String alternateEmployeeValueAfterSplit;
		List<WebElement> employeeList = driver.findElements(By.xpath("//table[@id='rel_resources']//tbody//tr"));
		int employeeListSize = employeeList.size();
		for(int i=1;i<=employeeListSize;i++){
			try{
				if(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td//a/../div//span")).getText().equals(employeeFirstName)&&driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(employeeLastName)){
					WebElement mappedEmployee = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td//a/../div//span"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedEmployee);
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText(),employeeTitle);
					System.out.println("Employee title+++"+driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText());
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[4]")).getText(),employeeType);
					System.out.println("Employee Type+++"+driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[4]")).getText());
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[5]")).getText(),employeeBusinessPhone);
					System.out.println("Employee business phone+++"+driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[5]")).getText());
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[6]")).getText(),employeeMobileNumber);
					System.out.println("Employee mobile number+++"+driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[6]")).getText());
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[7]")).getText(),employeeEmail);
					System.out.println("Employee email+++"+driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[7]")).getText());
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[8]")).getText(),employeeStatus);
					System.out.println("Employee status+++"+driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[8]")).getText());
					if(employeeAlternateEmployee.equals("Select Alternate Employee")){
						flag="true";
						break;
					}
					alternateEmployeeValueAfterSplit=employeeAlternateEmployee.split(",")[0];
					verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[9]")).getText(),alternateEmployeeValueAfterSplit);
					System.out.println("Employee alternate employee+++"+driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[9]")).getText());
				    flag="true";
				}else
					
				{
					flag="false";
				}
				
			}catch(Exception E){
			if(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td//a/../span")).getText().equals(employeeFirstName)&&driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText().equals(employeeLastName)){
				WebElement mappedEmployee = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td//a/../span"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedEmployee);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText(),employeeTitle);
				System.out.println("Employee title+++"+driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[3]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[4]")).getText(),employeeType);
				System.out.println("Employee Type+++"+driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[4]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[5]")).getText(),employeeBusinessPhone);
				System.out.println("Employee business phone+++"+driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[5]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[6]")).getText(),employeeMobileNumber);
				System.out.println("Employee mobile number+++"+driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[6]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[7]")).getText(),employeeEmail);
				System.out.println("Employee email+++"+driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[7]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[8]")).getText(),employeeStatus);
				System.out.println("Employee status+++"+driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[8]")).getText());
				if(employeeAlternateEmployee.equals("Select Alternate Employee")){
					flag="true";
					break;
				}
				alternateEmployeeValueAfterSplit=employeeAlternateEmployee.split(",")[0];
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[9]")).getText(),alternateEmployeeValueAfterSplit);
				System.out.println("Employee alternate employee+++"+driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//tr["+i+"]//td[9]")).getText());
			    flag="true";
			}else
				
			{
				flag="false";
			}
		}
		
	}
		return flag;
	}
	
	
	

}
