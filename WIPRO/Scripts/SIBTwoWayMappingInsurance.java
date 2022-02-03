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

public class SIBTwoWayMappingInsurance extends Page{
	String flag;
	String insurancePolicyName,insurancePolicyNumber,insurancePremium,insuranceExpiryDate,insuranceAdmin,insuranceAdminNumber,insuranceContact,insuranceContactNumber,insuranceAdminAfterSplit;
	String locationName,employeeFirstName,employeeSecondName,employeeName,assetName,businessFunctionName;
	
LoginLogout ll = new LoginLogout();
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		waitForPageLoad();
		driver.findElement(By.id("cometchat_hide")).click();
	}
	
	@Test(priority=1)
	void insuranceDataMappingValidations() throws InterruptedException, IOException{
		click(insuranceInMainMenu);
		thread();
		List<WebElement> insuranceList = driver
				.findElements(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr"));
		int insuranceListSize = insuranceList.size();
		Random insurance = new Random();
		int insuranceValue = insurance.nextInt(insuranceListSize);
		if (insuranceValue == 0) {
			insuranceValue = insuranceValue + 1;
		}
		System.out.println("Insurance value inside list view+++"+insuranceValue);
		driver.findElement(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr" + "[" + insuranceValue + "]")).click();
		insurancePolicyName = driver.findElement(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr[" + insuranceValue + "]//td[2]")).getText();
		System.out.println("Insurance policy name in list view+++"+insurancePolicyName);
		insurancePolicyNumber = driver.findElement(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr[" + insuranceValue + "]//td[3]")).getText();
		System.out.println("Insurance policy number in list view+++"+insurancePolicyNumber);
		insurancePremium = driver.findElement(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr[" + insuranceValue + "]//td[5]")).getText();
		System.out.println("Insurance premium in list view+++"+insurancePremium);
		insuranceExpiryDate = driver.findElement(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr[" + insuranceValue + "]//td[6]")).getText();
		System.out.println("Insurance expiry in list view+++"+insuranceExpiryDate);
		insuranceContact = driver.findElement(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr[" + insuranceValue + "]//td[7]")).getText();
		System.out.println("Insurance contact in list view+++"+insuranceContact);
		insuranceContactNumber = driver.findElement(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr[" + insuranceValue + "]//td[8]")).getText();
		System.out.println("Insurance contact number in list view+++"+insuranceContactNumber);
		driver.findElement(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr["+insuranceValue+"]//td[10]//a[@title='Edit']")).click();
		thread();
		insuranceAdmin = driver.findElement(By.xpath("//div[@id='insur_admin_chzn']//span")).getText();
		System.out.println("Insurance admin list view+++"+insuranceAdmin);
		if(insuranceAdmin.equals("Select Insurer Admin")){
			driver.findElement(By.xpath("//input[@class='btn validate-cancel {insurance_form}' and @value='Cancel']")).click();
			thread();
			scrollToBottom();
		}
		if(insuranceAdmin.equals("Select Insurer Admin")==false){
			click(employeesInMainMenu);
			thread();
			insuranceAdminAfterSplit = insuranceAdmin.split(",")[0];
			Select employeesCountDD = new Select(driver.findElement(By.name("resource_table_length")));
			employeesCountDD.selectByVisibleText("100");
			insuranceAdminNumber = driver.findElement(By.xpath("//table[@id='resource_table']//tbody[@role='alert']//tr[@data-for='"+insuranceAdminAfterSplit+"']//td[7]")).getText();
			click(insuranceInMainMenu);
			thread();
			driver.findElement(By.xpath("//table[@id='insurance_table']//tbody[@role='alert']//tr" + "[" + insuranceValue + "]")).click();
			scrollToBottom();
			thread();
		}
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnRelationWindow);
		List<WebElement> leftValueListInsideRelationShipWindow = driver.findElements(By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']/../../select//option"));
		int leftValueListInsideRelationShipWindowSize = leftValueListInsideRelationShipWindow.size();
		Random randomLocationName = new Random();
		int locationNameValue = randomLocationName.nextInt(leftValueListInsideRelationShipWindowSize);
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
		verifyAssertEquals(insurancePolicyName + " " + "successfully mapped to Locations", getActualObjectTxt);
		thread();
		scrollToBottom();
		locationName = driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody[@role='alert']//span")).getText();
		
		click(employeesTabInsuranceRelationSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnEmpRltWindow);
		List<WebElement> leftEmployeeRelationWindowValueList = driver.findElements(leftEmployeeRelationWindowValues);
		int leftEmployeeRelationWindowValueListSize = leftEmployeeRelationWindowValueList.size();
		Random random = new Random();
		int employeeValue = random.nextInt(leftEmployeeRelationWindowValueListSize);
		if(employeeValue==0){
			employeeValue = employeeValue+ 1;
		}
		System.out.println("Employee value inside relationship section+++"+employeeValue);
		String employeeNameInsideRelationShipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option"+"["+employeeValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option"+"["+employeeValue+"]")).click();
	    enterText(empLeftSearchTxtBoxRltWindow,employeeNameInsideRelationShipWindow);
	    thread();
	    click(leftMoveBtnEmpRltWindow);
	    enterText(empRightSearchTxtBoxRltWindow,employeeNameInsideRelationShipWindow);
	    thread();
	    click(submitButtonRelationShipWindow);
	    waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(insurancePolicyName+" "+"successfully mapped to Employees", getActualObjectTxt);
		thread();
		scrollToBottom();
		employeeFirstName = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//span")).getText();
		employeeSecondName = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//td[2]")).getText();
		employeeName = employeeFirstName+" "+employeeSecondName;
		
		click(assetsTabInsuranceRelationSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnAssetRltWindow);
		List<WebElement> leftAssetRelationWindowValueList = driver.findElements(leftAssetRelationWindowValues);
		int leftAssetRelationWindowValueListSize = leftAssetRelationWindowValueList.size();
		Random random1 = new Random();
		int assetValue = random1.nextInt(leftAssetRelationWindowValueListSize);
		if(assetValue == 0){
			assetValue = assetValue+1;
		}
		System.out.println("Asset value inside relationship section+++"+assetValue);
		String assetNameInsideRelationShipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option"+"["+assetValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option"+"["+assetValue+"]")).click();
		enterText(assetLeftSearchTxtBoxRltWindow,assetNameInsideRelationShipWindow);
		thread();
		click(leftMoveBtnAssetRltWindow);
		enterText(assetRightSearchTxtBoxRltWindow,assetNameInsideRelationShipWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(insurancePolicyName+" "+"successfully mapped to Assets", getActualObjectTxt);
		thread();
		scrollToBottom();
		assetName = driver.findElement(By.xpath("//table[@id='rel_assets']//tbody[@role='alert']//span")).getText();
		
		click(businessFntTabInsuranceRltSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnBusinessFtnRltWindow);
		List <WebElement> leftBusinessFntRltWindowValuesList = driver.findElements(leftBusinessFntRltWindowValues);
		int leftBusinessFntRltWindowValuesListSize = leftBusinessFntRltWindowValuesList.size();
		Random random2 = new Random();
		int leftBusinessFntRltWindowValue = random2.nextInt(leftBusinessFntRltWindowValuesListSize);
		if(leftBusinessFntRltWindowValue==0){
			leftBusinessFntRltWindowValue = leftBusinessFntRltWindowValue + 1;
		}
		System.out.println("Business function value inside relationship section+++"+leftBusinessFntRltWindowValue);
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
		verifyAssertEquals(insurancePolicyName+" "+"successfully mapped to Business Functions", getActualObjectTxt);
		thread();
		scrollToBottom();
		businessFunctionName = driver.findElement(By.xpath("//table[@id='rel_processes']//tbody[@role='alert']//span")).getText();
		
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
		click(insuranceTabRltSection);
		thread();
		Select insuranceInnerCountDD = new Select(driver.findElement(By.name("rel_insurances_length")));
		insuranceInnerCountDD.selectByVisibleText("100");
		thread();
		validateInsuranceValues();
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
		click(insuranceTabRltSection);
		thread();
		Select insuranceInnerCountDD1 = new Select(driver.findElement(By.name("rel_insurances_length")));
		insuranceInnerCountDD1.selectByVisibleText("100");
		thread();
		validateInsuranceValues();
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
		click(insuranceTabRltSection);
		thread();
		Select insuranceInnerCountDD2 = new Select(driver.findElement(By.name("rel_insurances_length")));
		insuranceInnerCountDD2.selectByVisibleText("100");
		thread();
		validateInsuranceValues();
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
		click(insuranceTabRltSection);
		thread();
		Select insuranceInnerCountDD3 = new Select(driver.findElement(By.name("rel_insurances_length")));
		insuranceInnerCountDD3.selectByVisibleText("100");
		thread();
		validateInsuranceValues();
		verifyAssertEquals(flag,"true");
		
	}
	
	public String validateInsuranceValues(){
		List<WebElement> insuranceList = driver.findElements(By.xpath("//table[@id='rel_insurances']//tbody//tr"));
		int insuranceListSize = insuranceList.size();
		for(int i=1;i<=insuranceListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[1]")).getText().equals(insurancePolicyName)){
				System.out.println("Insurance policy name+++"+driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[1]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[2]")).getText(),insurancePolicyNumber);
				System.out.println("Insurance policy number+++"+driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[2]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[3]")).getText(),insurancePremium);
				System.out.println("Insurance premium+++"+driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[3]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[4]")).getText(),insuranceExpiryDate);
				System.out.println("Insurance expiry date+++"+driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[4]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[7]")).getText(),insuranceContact);
				System.out.println("Insurance contact+++"+driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[7]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[8]")).getText(),insuranceContactNumber);
				System.out.println("Insurance contact number+++"+driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[8]")).getText());
				if(insuranceAdmin.equals("Select Insurer Admin")){
					flag="true";
					break;
				}
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[5]")).getText(),insuranceAdminAfterSplit);
				System.out.println("Insurance admin+++"+driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[5]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[6]")).getText(),insuranceAdminNumber);
				System.out.println("Insurance admin number+++"+driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody//tr["+i+"]//td[6]")).getText());
				
				flag="true";
			}else
			{
				flag="false";
			}
		}
		return flag;
	}
	

}
