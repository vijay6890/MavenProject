package Scripts;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Locations.locationsInMainMenu;
import static ObjectRepository.Locations.msgNotificationBar;
import static ObjectRepository.Locations.rightMoveAllBtnThreatRltWindow;
import static ObjectRepository.Locations.threatsLeftMoveBtnRltWindow;
import static ObjectRepository.Locations.threatsLeftRltWindowValues;
import static ObjectRepository.Locations.threatsLeftSearchTxtBoxRltWindow;
import static ObjectRepository.Locations.threatsRightSearchTxtBoxRltWindow;
import static ObjectRepository.Locations.threatsTabRelationSection;
import static ObjectRepository.TaskGroups.taskRightMoveAllArrowRltWindow;
import static ObjectRepository.TaskGroups.tasksLeftMoveBtnRltWindow;
import static ObjectRepository.TaskGroups.tasksLeftRltWindowValues;
import static ObjectRepository.TaskGroups.tasksLeftSearchTxtBoxRltWindow;
import static ObjectRepository.TaskGroups.tasksRightSearchTxtBoxRltWindow;
import static ObjectRepository.TaskGroups.tasksTabRltSection;
import static ObjectRepository.TaskGroups.*;
import static ObjectRepository.Employees.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Assets.*;
import static ObjectRepository.Insurance.*;
import static ObjectRepository.BusinessFunctions.*;
import static ObjectRepository.ContactGroups.contactGroupsInMainMenu;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Teams.*;
import static ObjectRepository.AssetGroups.*;
import static ObjectRepository.Contacts.*;
import static UIWrappers.UIObjects.click;
import static UIWrappers.UIObjects.enterText;
import static UIWrappers.UIObjects.getActualObjectTxt;
import static UIWrappers.UIObjects.getObjectText;
import static UIWrappers.UIObjects.scrollToBottom;
import static UIWrappers.UIObjects.thread;
import static UIWrappers.UIObjects.verifyAssertEquals;
import static UIWrappers.UIObjects.waitForElement;
import static UIWrappers.UIObjects.waitForPageLoad;
import static UIWrappers.UIObjects.verifyAssertEquals;
import static UIWrappers.UIObjects.scrollToTop;

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

public class SIBTwoWayMappingBusinessFunctions extends Page{
	String flag;
	String businessFunctionName,businessFunctionID,priority,department,employeeAssigned,mobileNumber,recoveryTime,lossPerDay,minimumEmployees,businessFunctionComments;
	String locationName,employeeFirstName,employeeLastName,employeeName,assetName,insuranceName,taskName;
	
LoginLogout ll = new LoginLogout();
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		waitForPageLoad();
		driver.findElement(By.id("cometchat_hide")).click();
	}
	
	@Test(priority=1)
	void businessFunctionsDataMappingValidations() throws InterruptedException, IOException{
		click(businessFunctionsInMainMenu);
		thread();
		List<WebElement> businessFunctionsList = driver
				.findElements(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr"));
		int businessFunctionsListSize = businessFunctionsList.size();
		Random businessFunction = new Random();
		int businessFunctionValue = businessFunction.nextInt(businessFunctionsListSize);
		if (businessFunctionValue == 0) {
			businessFunctionValue = businessFunctionValue + 1;
		}
		System.out.println("Business function random value from list view++"+businessFunctionValue);
		driver.findElement(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr" + "[" + businessFunctionValue + "]")).click();
		businessFunctionName = driver.findElement(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr[" + businessFunctionValue + "]//td[2]")).getText();
		System.out.println("Business function name in list view+++"+businessFunctionName);
		priority = driver.findElement(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr[" + businessFunctionValue + "]//td[3]")).getText();
		System.out.println("Business function priority in list view+++"+priority);
		employeeAssigned = driver.findElement(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr[" + businessFunctionValue + "]//td[4]")).getText();
		System.out.println("Business function employee assigned in list view+++"+employeeAssigned);
		mobileNumber = driver.findElement(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr[" + businessFunctionValue + "]//td[5]")).getText();
		System.out.println("Business function mobile number in list view+++"+mobileNumber);
		recoveryTime = driver.findElement(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr[" + businessFunctionValue + "]//td[6]")).getText();
		System.out.println("Business function recovery time in list view+++"+recoveryTime);
		lossPerDay = driver.findElement(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr[" + businessFunctionValue + "]//td[7]")).getText();
		System.out.println("Business function loss per day in list view+++"+lossPerDay);
		minimumEmployees = driver.findElement(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr[" + businessFunctionValue + "]//td[8]")).getText();
		System.out.println("Business function minimum employees in list view+++"+minimumEmployees);
		driver.findElement(By.xpath("//table[@id='process_table']//tbody[@role='alert']//tr["+businessFunctionValue+"]//td[9]//a[@title='Edit']")).click();
		thread();
		businessFunctionID=driver.findElement(By.xpath("//input[@id='process_ref_id']")).getAttribute("value");
		System.out.println("Business function ID inside list view+++"+businessFunctionID);
		department = driver.findElement(By.id("process_department")).getAttribute("value");
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='sceditor-container ltr']//iframe")));
		businessFunctionComments = driver.findElement(By.xpath("//body[@dir='ltr']//p")).getText();
		driver.switchTo().defaultContent();
		scrollToBottom();
		driver.findElement(By.xpath("//input[@class='btn validate-cancel {process_form}' and @value='Cancel']")).click();
		thread();
		scrollToBottom();
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnRelationWindow);
		List<WebElement> leftValueListInsideRelationShipWindow = driver
				.findElements(By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']/../../select//option"));
		int leftValueListInsideRelationShipWindowSize = leftValueListInsideRelationShipWindow.size();
		Random random = new Random();
		int locationNameValue = random.nextInt(leftValueListInsideRelationShipWindowSize);
		if (locationNameValue == 0) {
			locationNameValue = locationNameValue + 1;
		}
		System.out.println("Location value inside relationship section+++"+locationNameValue);
		String leftLocationValue = driver.findElement(By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']/../../select//option"+"["+locationNameValue+"]")).getText();	
		driver.findElement(By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']/../../select//option"+"["+locationNameValue+"]")).click();
		enterText(leftSearchTextBoxLocationsRelationWindow, leftLocationValue);
		thread();
		click(locLeftMoveBtnRltWindow);
		enterText(rightSearchTextBoxLocationsRelationWindow, leftLocationValue);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(businessFunctionName + " " + "successfully mapped to Locations", getActualObjectTxt);
		thread();
		scrollToBottom();
		locationName = driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody[@role='alert']//span")).getText();
		
		click(employeesTabInsuranceRelationSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnEmpRltWindow);
		List<WebElement> leftEmployeeRelationWindowValueList = driver.findElements(leftEmployeeRelationWindowValues);
		int leftEmployeeRelationWindowValueListSize = leftEmployeeRelationWindowValueList.size();
		Random random1 = new Random();
		int employeeValue = random1.nextInt(leftEmployeeRelationWindowValueListSize);
		if(employeeValue==0){
			employeeValue = employeeValue+ 1;
		}
		System.out.println("Employee value inside relationship section+++"+employeeValue);
		String employeeNameInRltWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option"+"["+employeeValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option"+"["+employeeValue+"]")).click();
	    enterText(empLeftSearchTxtBoxRltWindow,employeeNameInRltWindow);
	    thread();
	    click(leftMoveBtnEmpRltWindow);
	    enterText(empRightSearchTxtBoxRltWindow,employeeNameInRltWindow);
	    thread();
	    click(submitButtonRelationShipWindow);
	    waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(businessFunctionName+" "+"successfully mapped to Employees", getActualObjectTxt);
		thread();
		scrollToBottom();
		employeeFirstName = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//span")).getText();
		employeeLastName = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//td[2]")).getText();
		employeeName = employeeFirstName+" "+employeeLastName;
		
		click(assetsTabInsuranceRelationSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnAssetRltWindow);
		List<WebElement> leftAssetRelationWindowValueList = driver.findElements(leftAssetRelationWindowValues);
		int leftAssetRelationWindowValueListSize = leftAssetRelationWindowValueList.size();
		Random random3 = new Random();
		int assetValue = random3.nextInt(leftAssetRelationWindowValueListSize);
		if(assetValue == 0){
			assetValue = assetValue+1;
		}
		String assetNameInRltWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option"+"["+assetValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option"+"["+assetValue+"]")).click();
		enterText(assetLeftSearchTxtBoxRltWindow,assetNameInRltWindow);
		thread();
		click(leftMoveBtnAssetRltWindow);
		enterText(assetRightSearchTxtBoxRltWindow,assetNameInRltWindow);
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(businessFunctionName+" "+"successfully mapped to Assets", getActualObjectTxt);
		thread();
		scrollToBottom();
		assetName = driver.findElement(By.xpath("//table[@id='rel_assets']//tbody[@role='alert']//span")).getText();
		
		
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
		System.out.println("Insurance value inside relationship section+++"+randomInsuranceValue);
		String insuranceNameInRltWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Insurance')]/..//button[@class='btn moveall']/../../select//option"+"["+randomInsuranceValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Insurance')]/..//button[@class='btn moveall']/../../select//option"+"["+randomInsuranceValue+"]")).click();
		enterText(insuranceLeftSearchTxtBoxRltWindow,insuranceNameInRltWindow);
		thread();
		click(insuranceLeftMoveArrowRltWindow);
		enterText(insuranceRightSearchTxtBoxRltWindow,insuranceNameInRltWindow);
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(businessFunctionName+" "+"successfully mapped to Insurance", getActualObjectTxt);
		thread();
		scrollToBottom();
		insuranceName = driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody[@role='alert']//span")).getText();
		
		click(tasksTabRltSection);
		click(addRemoveRelationsBtn);
		thread();
		click(taskRightMoveAllArrowRltWindow);
		List<WebElement> tasksLeftRltWindowValuesList = driver.findElements(tasksLeftRltWindowValues);
		int tasksLeftRltWindowValuesListSize = tasksLeftRltWindowValuesList.size();
		Random random6 = new Random();
		int randomTaskValue = random6.nextInt(tasksLeftRltWindowValuesListSize);
		if(randomTaskValue==0){
			randomTaskValue = randomTaskValue+1;
		}
		System.out.println("Tasks value inside relationship section+++"+randomTaskValue);
		String taskNameInRltWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).click();
		enterText(tasksLeftSearchTxtBoxRltWindow,taskNameInRltWindow);
		thread();
		click(tasksLeftMoveBtnRltWindow);
		enterText(tasksRightSearchTxtBoxRltWindow,taskNameInRltWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(businessFunctionName+ " "+"successfully mapped to Tasks", getActualObjectTxt);
		thread();
		scrollToBottom();
		taskName = driver.findElement(By.xpath("//table[@id='rel_task']//tbody[@role='alert']//span")).getText();
		
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
		click(businessFntTabInsuranceRltSection);
		thread();
		scrollToBottom();
		Select businessFunctionsInnerCountDD2 = new Select(driver.findElement(By.name("rel_processes_length")));
		businessFunctionsInnerCountDD2.selectByVisibleText("100");
		thread();
		validateBusinessFunctionValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(employeesInMainMenu);
		thread();
		Select employeesCountDD = new Select(driver.findElement(By.name("resource_table_length")));
		employeesCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedEmployee = driver.findElement(By.xpath("//table[@id='resource_table']//tbody//tr[@data-for='"+employeeName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedEmployee);
		mappedEmployee.click();
		scrollToBottom();
		click(businessFntTabInsuranceRltSection);
		thread();
		Select businessFunctionsInnerCountDD1 = new Select(driver.findElement(By.name("rel_process_length")));
		businessFunctionsInnerCountDD1.selectByVisibleText("100");
		thread();
		validateBusinessFunctionValues1();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(assetsInMainMenu);
		thread();
		Select assetsCountDD = new Select(driver.findElement(By.name("assets_table_length")));
		assetsCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedAsset = driver.findElement(By.xpath("//table[@id='assets_table']//tbody//tr[@data-for='"+assetName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedAsset);
		mappedAsset.click();
		scrollToBottom();
		click(businessFntTabInsuranceRltSection);
		thread();
		Select businessFunctionsInnerCountDD3 = new Select(driver.findElement(By.name("rel_processes_length")));
		businessFunctionsInnerCountDD3.selectByVisibleText("100");
		thread();
		validateBusinessFunctionValues();
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
		click(businessFntTabInsuranceRltSection);
		thread();
		scrollToBottom();
		Select businessFunctionsInnerCountDD4 = new Select(driver.findElement(By.name("rel_processes_length")));
		businessFunctionsInnerCountDD4.selectByVisibleText("100");
		thread();
		validateBusinessFunctionValues();
		verifyAssertEquals(flag,"true");
		
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
		click(businessFntTabInsuranceRltSection);
		thread();
		Select businessFunctionsInnerCountDD5 = new Select(driver.findElement(By.name("rel_processes_length")));
		businessFunctionsInnerCountDD5.selectByVisibleText("100");
		thread();
		validateBusinessFunctionValues();
		verifyAssertEquals(flag,"true");
				
	}
	
	public String validateBusinessFunctionValues(){
		List<WebElement> businessFunctionList = driver.findElements(By.xpath("//table[@id='rel_processes']//tbody//tr"));
		int businessFunctionListSize = businessFunctionList.size();
		for(int i=1;i<=businessFunctionListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[1]")).getText().equals(businessFunctionName)){
				System.out.println("Business function Name+++"+driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[1]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[2]")).getText(),businessFunctionID);
				System.out.println("Business function ID+++"+driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[2]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[3]")).getText(),priority);
				System.out.println("Business function priority"+driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[3]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[4]")).getText(),department);
			    System.out.println("Business function department++"+driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[4]")).getText());
			    verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[5]")).getText(),employeeAssigned);
			    System.out.println("Business function employee assigned+++"+driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[5]")).getText());
			    verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[6]")).getText(),mobileNumber);
			    System.out.println("Business function employee assigned mobile number+++"+driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[6]")).getText());
			    verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[7]")).getText(),recoveryTime);
			    System.out.println("Business function recovery time+++"+driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[7]")).getText());
			    verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[8]")).getText(),lossPerDay);
			    System.out.println("Business function Minimum employees required+++"+driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[8]")).getText());
			    verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[9]")).getText(),minimumEmployees);
			    System.out.println("Business function minimum employees required+++"+driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[9]")).getText());
			    verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[10]")).getText(),businessFunctionComments);
			    System.out.println("Business function comments+++"+driver.findElement(By.xpath("//table[@id='rel_processes']//tbody//tr["+i+"]//td[10]")).getText());
			    flag="true";
			}else{
				flag="false";
			}
		}
		return flag;
	}
	
	
	public String validateBusinessFunctionValues1(){
		List<WebElement> businessFunctionList = driver.findElements(By.xpath("//table[@id='rel_process']//tbody//tr"));
		int businessFunctionListSize = businessFunctionList.size();
		for(int i=1;i<=businessFunctionListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[1]")).getText().equals(businessFunctionName)){
				System.out.println("Business function Name+++"+driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[1]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[2]")).getText(),businessFunctionID);
				System.out.println("Business function ID+++"+driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[2]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[3]")).getText(),priority);
				System.out.println("Business function priority"+driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[3]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[4]")).getText(),department);
			    System.out.println("Business function department++"+driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[4]")).getText());
			    verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[5]")).getText(),employeeAssigned);
			    System.out.println("Business function employee assigned+++"+driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[5]")).getText());
			    verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[6]")).getText(),mobileNumber);
			    System.out.println("Business function employee assigned mobile number+++"+driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[6]")).getText());
			    verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[7]")).getText(),recoveryTime);
			    System.out.println("Business function recovery time+++"+driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[7]")).getText());
			    verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[8]")).getText(),lossPerDay);
			    System.out.println("Business function Minimum employees required+++"+driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[8]")).getText());
			    verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[9]")).getText(),minimumEmployees);
			    System.out.println("Business function minimum employees required+++"+driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[9]")).getText());
			    verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[10]")).getText(),businessFunctionComments);
			    System.out.println("Business function comments+++"+driver.findElement(By.xpath("//table[@id='rel_process']//tbody//tr["+i+"]//td[10]")).getText());
			    flag="true";
			}else{
				flag="false";
			}
		}
		return flag;
	}

}
