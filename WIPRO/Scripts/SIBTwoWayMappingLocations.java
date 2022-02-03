package Scripts;

/******************************************************************************************************************************
 * 
 * @author Rahul Nair
 * 
 
*******************************************************************************************************************************/

import static Config.TakScreenshot.takeScreenshot;


import static ObjectRepository.Insurance.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Employees.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Assets.*;
import static ObjectRepository.Insurance.*;
import static ObjectRepository.BusinessFunctions.*;
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

public class SIBTwoWayMappingLocations extends Page{
	String flag;
	String locationName,locationStatus,locationType,locationManager,locationManagerContact,locationManagerEmail,locationCity,locationState,locationCountry;
	String employeeFirstName,employeeSecondName;
	String threatName;
	String assetName;
	String insuranceName;
	String businessFunctionName;
	LoginLogout ll = new LoginLogout();
	
	/*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		waitForPageLoad();
		driver.findElement(By.id("cometchat_hide")).click();
	}*/
	
	@Test(priority=1)
	void locationsDataMappingValidations() throws InterruptedException, IOException{
		String employeeNameAfterSplit;
		click(locationsInMainMenu);
		thread();	
		Select locationsCountDD = new Select(driver.findElement(By.name("facilities_table_length")));
		locationsCountDD.selectByVisibleText("100");
		List<WebElement> locationsList = driver.findElements(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr"));
		int locationsListSize = locationsList.size();
		Random random = new Random();
		int locationValue = random.nextInt(locationsListSize);
		if(locationValue==0){
			locationValue = locationValue+1;
		}
		driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr"+"["+locationValue+"]")).click();
		locationName = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr["+locationValue+"]"+"//td[2]")).getAttribute("title");
		System.out.println("++++++++"+locationName);
		locationStatus = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr["+locationValue+"]//td[3]")).getText();
		System.out.println("++++++++"+locationStatus);
		locationType = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr["+locationValue+"]//td[4]")).getText();
		System.out.println("++++++++"+locationType);
		locationManager= driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr["+locationValue+"]//td[5]")).getText();
		System.out.println("++++++++"+locationManager);
		locationManagerContact = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr["+locationValue+"]//td[6]")).getText();
		System.out.println("++++++++"+locationManagerContact);
		locationCity = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr["+locationValue+"]//td[8]")).getText();
		System.out.println("++++++++"+locationCity);
		locationState = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr["+locationValue+"]//td[9]")).getText();
		System.out.println("++++++++"+locationState);
		locationCountry = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody[@role='alert']//tr["+locationValue+"]//td[10]")).getText();
		System.out.println("++++++++"+locationCountry);
		thread();
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
		String employeeNameInsideRelationShipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option"+"["+employeeValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Employees')]/..//button[@class='btn moveall']/../../select//option"+"["+employeeValue+"]")).click();
	    enterText(empLeftSearchTxtBoxRltWindow,employeeNameInsideRelationShipWindow);
	    thread();
	    click(leftMoveBtnEmpRltWindow);
	    //click(leftMoveAllBtnEmpRltWindow);
	    enterText(empRightSearchTxtBoxRltWindow,employeeNameInsideRelationShipWindow);
	    thread();
	    click(submitButtonRelationShipWindow);
	    try{
	    	thread();
	    	if(driver.findElement(By.xpath("//div[@class='modal-content']//div//h4[contains(text(),'Employees')]")).isDisplayed()){
				driver.findElement(otherLocationsEmployeesOkButton).click();
				waitForElement(msgNotificationBar);
				getObjectText(msgNotificationBar);
				takeScreenshot();
				verifyAssertEquals(locationName+" "+"successfully mapped to Employees", getActualObjectTxt);
				thread();
				scrollToBottom();
	    	}}catch(Exception E){
	    		scrollToBottom();
	    	}
	    thread();
	    employeeFirstName = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//span[1]")).getText();
	    employeeSecondName = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody[@role='alert']//td[2]")).getText();
	    
	    click(threatsTabRelationSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnThreatRltWindow);
		List<WebElement> leftThreatRelationWindowValueList = driver.findElements(threatsLeftRltWindowValues);
		int leftThreatRelationWindowValueListSize = leftThreatRelationWindowValueList.size();
		Random random2 = new Random();
		int threatValue = random2.nextInt(leftThreatRelationWindowValueListSize);
		if(threatValue==0){
			threatValue = threatValue+ 1;
		}
		String threatNameInsideRelationshipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Threats')]/..//button[@class='btn moveall']/../../select//option"+"["+threatValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Threats')]/..//button[@class='btn moveall']/../../select//option"+"["+threatValue+"]")).click();
	    enterText(threatsLeftSearchTxtBoxRltWindow,threatNameInsideRelationshipWindow);
	    thread();
	    click(threatsLeftMoveBtnRltWindow);
	    enterText(threatsRightSearchTxtBoxRltWindow,threatNameInsideRelationshipWindow);
	    thread();
	    click(submitButtonRelationShipWindow);
	    waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(locationName+" "+"successfully mapped to Threats", getActualObjectTxt);
		thread();
		scrollToBottom();
		
		thread();
		threatName = driver.findElement(By.xpath("//table[@id='rel_threats']//tbody[@role='alert']//td[1]")).getText();
		
		
		click(assetsTabInsuranceRelationSection);
		click(addRemoveRelationsBtn);
		click(rightMoveAllBtnAssetRltWindow);
		List<WebElement> leftAssetRelationWindowValueList = driver.findElements(leftAssetRelationWindowValues);
		int leftAssetRelationWindowValueListSize = leftAssetRelationWindowValueList.size();
		Random random3 = new Random();
		int assetValue = random3.nextInt(leftAssetRelationWindowValueListSize);
		if(assetValue == 0){
			assetValue = assetValue+1;
		}
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
		verifyAssertEquals(locationName+" "+"successfully mapped to Assets", getActualObjectTxt);
		thread();
		scrollToBottom();
		thread();
		assetName = driver.findElement(By.xpath("//table[@id='rel_assets']//tbody[@role='alert']//span")).getText();
		
		thread();
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
		verifyAssertEquals(locationName+" "+"successfully mapped to Insurance", getActualObjectTxt);
		thread();
		scrollToBottom();
		insuranceName = driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody[@role='alert']//span")).getText();
		
		thread();
		click(businessFntTabInsuranceRltSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnBusinessFtnRltWindow);
		List <WebElement> leftBusinessFntRltWindowValuesList = driver.findElements(leftBusinessFntRltWindowValues);
		int leftBusinessFntRltWindowValuesListSize = leftBusinessFntRltWindowValuesList.size();
		Random random5 = new Random();
		int leftBusinessFntRltWindowValue = random5.nextInt(leftBusinessFntRltWindowValuesListSize);
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
		verifyAssertEquals(locationName+" "+"successfully mapped to Business Functions", getActualObjectTxt);
		thread();
		scrollToBottom();
		businessFunctionName = driver.findElement(By.xpath("//table[@id='rel_processes']//tbody[@role='alert']//span")).getText();
		UIWrappers.UIObjects.scrollToTop();
		
		click(employeesInMainMenu);
		thread();
		Select employeesCountDD = new Select(driver.findElement(By.name("resource_table_length")));
		employeesCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedEmployee = driver.findElement(By.xpath("//table[@id='resource_table']//tbody//tr//td[contains(text(),'"+employeeFirstName+"')]/../td//div[contains(text(),'"+employeeSecondName+"')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedEmployee);
		mappedEmployee.click();
		
		click(locationsTabEmpRelationshipSection);
		thread();
		scrollToBottom();
		thread();
		Select locationsInnerCountDD = new Select(driver.findElement(By.name("rel_facilities_length")));
		locationsInnerCountDD.selectByVisibleText("100");
		thread();
		validateLocationValues();
		verifyAssertEquals(flag,"true");
		scrollToTop();
		
		click(threatsInMainMenu);
		thread();
		Select threatsCountDD = new Select(driver.findElement(By.name("threat_table_length")));
		threatsCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedThreat = driver.findElement(By.xpath("//table[@id='threat_table']//tbody//tr[@data-for='"+threatName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedThreat);
		mappedThreat.click();
		thread();
		scrollToBottom();
		Select locationsInnerCountDD1 = new Select(driver.findElement(By.name("rel_facilities_length")));
		locationsInnerCountDD1.selectByVisibleText("100");
		thread();
		validateLocationValues();
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
		thread();
		scrollToBottom();
		Select locationsInnerCountDD2 = new Select(driver.findElement(By.name("rel_facilities_length")));
		locationsInnerCountDD2.selectByVisibleText("100");
		thread();
		validateLocationValues();
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
		thread();
		scrollToBottom();
		Select locationsInnerCountDD3 = new Select(driver.findElement(By.name("rel_facilities_length")));
		locationsInnerCountDD3.selectByVisibleText("100");
		thread();
		validateLocationValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(businessFunctionsInMainMenu);
		thread();
		Select businessFunctionCountDD = new Select(driver.findElement(By.name("process_table_length")));
		businessFunctionCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedBusinessFunction = driver.findElement(By.xpath("//table[@id='process_table']//tbody//tr[@data-for='"+businessFunctionName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedBusinessFunction);
		mappedBusinessFunction.click();
		thread();
		scrollToBottom();
		Select locationsInnerCountDD4 = new Select(driver.findElement(By.name("rel_facilities_length")));
		locationsInnerCountDD4.selectByVisibleText("100");
		thread();
		validateLocationValues();
		verifyAssertEquals(flag,"true");
	}
	
	public String validateLocationValues(){
		String employeeNameAfterSplit;
		List<WebElement> locationsListEmpRltSection = driver.findElements(By.xpath("//table[@id='rel_facilities']//tbody//tr"));
		int locationsListEmpRltSectionSize = locationsListEmpRltSection.size();
		for(int i=1;i<=locationsListEmpRltSectionSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody//tr["+i+"]//td[1]")).getText().equals(locationName)){
				WebElement mappedLocation = driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody//tr["+i+"]//td[1]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedLocation);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody//tr["+i+"]//td[2]")).getText(),locationStatus);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody//tr["+i+"]//td[3]")).getText(),locationType);
				employeeNameAfterSplit = locationManager.split(",")[0];
				System.out.println("Employee name after split++++"+employeeNameAfterSplit);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody//tr["+i+"]//td[4]")).getText(),employeeNameAfterSplit);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody//tr["+i+"]//td[5]")).getText(),locationManagerContact);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody//tr["+i+"]//td[7]")).getText(),locationCity);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody//tr["+i+"]//td[8]")).getText(),locationState);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody//tr["+i+"]//td[9]")).getText(),locationCountry);
				flag="true";
			}else
			{
				flag="false";
			}
		}
		return flag;
	}
	
	
	

	
	
	
	

}
