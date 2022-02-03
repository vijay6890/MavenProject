package Scripts;

/******************************************************************************************************************************
 * 
 * @author Rahul Nair
 * 
 
*******************************************************************************************************************************/

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
import static ObjectRepository.Threats.*;
import static ObjectRepository.Assets.*;
import static ObjectRepository.Insurance.*;
import static ObjectRepository.BusinessFunctions.*;
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

public class SIBTwoWayMappingAssets extends Page{
	String flag;
	String assetName,assetStatus,assetType,assetID,assetManufacturerName,assetQuantity,assetComments;
	String locationName,assetGroupName,contactName,insuranceName,businessFunctionName,taskName;
	
    LoginLogout ll = new LoginLogout();
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		waitForPageLoad();
		driver.findElement(By.id("cometchat_hide")).click();
	}
	
	@Test(priority=1)
	void assetsDataMappingValidations() throws InterruptedException, IOException{
		click(assetsInMainMenu);
		thread();	
		List<WebElement> assetsList = driver
				.findElements(By.xpath("//table[@id='assets_table']//tbody[@role='alert']//tr"));
		int assetsListSize = assetsList.size();
		Random random = new Random();
		int assetValue = random.nextInt(assetsListSize);
		if (assetValue == 0) {
			assetValue = assetValue + 1;
		}
		System.out.println("Asset list view random value+++"+assetValue);
		driver.findElement(By.xpath("//table[@id='assets_table']//tbody[@role='alert']//tr" + "[" + assetValue + "]")).click();
		assetName = driver.findElement(By.xpath("//table[@id='assets_table']//tbody[@role='alert']//tr[" + assetValue + "]//td[2]")).getText();
		System.out.println("Asset value inside list+++"+assetName);
		assetStatus= driver.findElement(By.xpath("//table[@id='assets_table']//tbody[@role='alert']//tr[" + assetValue + "]//td[3]")).getText();
		System.out.println("Asset status inside list+++"+assetStatus);
		assetType = driver.findElement(By.xpath("//table[@id='assets_table']//tbody[@role='alert']//tr[" + assetValue + "]//td[4]")).getText();
		System.out.println("Asset Type inside list+++"+assetStatus);
		assetID= driver.findElement(By.xpath("//table[@id='assets_table']//tbody[@role='alert']//tr[" + assetValue + "]//td[5]")).getText();
		System.out.println("Asset ID inside list+++"+assetID);
		assetManufacturerName = driver.findElement(By.xpath("//table[@id='assets_table']//tbody[@role='alert']//tr[" + assetValue + "]//td[6]")).getText();
		System.out.println("Asset manufacturer inside list+++"+assetManufacturerName);
		assetQuantity = driver.findElement(By.xpath("//table[@id='assets_table']//tbody[@role='alert']//tr[" + assetValue + "]//td[8]")).getText();
		System.out.println("Asset quantity inside list+++"+assetQuantity);
		assetComments = driver.findElement(By.xpath("//table[@id='assets_table']//tbody[@role='alert']//tr[" + assetValue + "]//td[10]")).getText();
		System.out.println("Asset comments inside list+++"+assetComments);
		thread();
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnRelationWindow);
		List<WebElement> leftValueListInsideRelationShipWindow = driver.findElements(By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']/../../select//option"));
		int leftValueListInsideRelationShipWindowSize = leftValueListInsideRelationShipWindow.size();
		Random randomValue = new Random();
		int locationNameValue = randomValue.nextInt(leftValueListInsideRelationShipWindowSize);
		if (locationNameValue == 0) {
			locationNameValue = locationNameValue + 1;
		}
		System.out.println("Location random value inside relationship section random value+++"+locationNameValue);
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
		verifyAssertEquals(assetName + " " + "successfully mapped to Locations", getActualObjectTxt);
		thread();
		scrollToBottom();
		locationName = driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody[@role='alert']//span")).getText();
		
		click(assetGroupsTabRltSection);
		click(addRemoveRelationsBtn);
		thread();
		click(assetGroupRightMoveAllArrowRltWindow);
		List<WebElement> assetGroupLeftRltWindowValuesList = driver.findElements(assetGroupLeftRltWindowValues);
		int assetGroupLeftRltWindowValuesSize = assetGroupLeftRltWindowValuesList.size();
	    Random random1 = new Random();
		int assetGroupValue = random1.nextInt(assetGroupLeftRltWindowValuesSize);
		if(assetGroupValue==0){
			   assetGroupValue = assetGroupValue+1;
		   }
		System.out.println("Random asset group value inside relatioship section+++"+assetGroupValue);
		String randomAssetGroupName = driver.findElement(By.xpath("//label[contains(text(),'List of Asset Groups')]/..//button[@class='btn moveall']/../../select//option"+"["+assetGroupValue+"]")).getText();
	    driver.findElement(By.xpath("//label[contains(text(),'List of Asset Groups')]/..//button[@class='btn moveall']/../../select//option"+"["+assetGroupValue+"]")).click();
		enterText(assetGroupLeftSearchTxtBoxRltWindow,randomAssetGroupName);
		thread();
		click(assetGroupLeftMoveBtnRltWindow);
		enterText(assetGroupRightSearchTxtBoxRltWindow,randomAssetGroupName);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(assetName+" "+"successfully mapped to Asset Groups", getActualObjectTxt);
		thread();
		scrollToBottom();
		assetGroupName = driver.findElement(By.xpath("//table[@id='rel_asset_grp']//tbody[@role='alert']//tr//td[1]")).getText();
		
		click(contactsTabTasksRltSection);
    	click(addRemoveRelationsBtn);
    	thread();
    	click(rightMoveAllArrowContactsRltWindow);
    	List<WebElement> contactsLeftRltWindowValuesList = driver.findElements(contactsLeftRltWindowValues);
    	int contactsLeftRltWindowValuesSize = contactsLeftRltWindowValuesList.size();
    	Random random2 = new Random();
    	int randomContactValue = random2.nextInt(contactsLeftRltWindowValuesSize);
    	if(randomContactValue==0){
    		randomContactValue = randomContactValue+1;
    	}
    	System.out.println("Random value inside contacts relationship section+++"+randomContactValue);
    	String contactNameInsideRelationShipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Contacts')]/..//button[@class='btn moveall']/../../select//option"+"["+randomContactValue+"]")).getText();
    	driver.findElement(By.xpath("//label[contains(text(),'List of Contacts')]/..//button[@class='btn moveall']/../../select//option"+"["+randomContactValue+"]")).click();
    	enterText(contactsLeftSearchTxtBoxRltWindow,contactNameInsideRelationShipWindow);
    	thread();
    	click(contactsLeftMoveBtnRltWindow);
    	enterText(contactsRightSearchTxtBoxRltWindow,contactNameInsideRelationShipWindow);
    	thread();
    	click(submitButtonRelationShipWindow);
    	waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(assetName+" "+"successfully mapped to Contacts", getActualObjectTxt);
		thread();
		scrollToBottom();
		contactName = driver.findElement(By.xpath("//table[@id='rel_contacts']//tbody[@role='alert']//span")).getText();
		
		click(insuranceTabRltSection);
		click(addRemoveRelationsBtn);
		thread();
		click(insuranceRightMoveAllBtnRltWindow);
		List<WebElement> insuranceLeftRltWindowValueList = driver.findElements(insuranceLeftRltValues);
		int insuranceLeftRltWindowValueListSize = insuranceLeftRltWindowValueList.size();
		Random random3 = new Random();
		int randomInsuranceValue = random3.nextInt(insuranceLeftRltWindowValueListSize);
		if(randomInsuranceValue==0){
			randomInsuranceValue=randomInsuranceValue+1;
		}
		System.out.println("Random value inside insurance relationship section+++"+randomInsuranceValue);
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
		verifyAssertEquals(assetName+" "+"successfully mapped to Insurance", getActualObjectTxt);
		thread();
		scrollToBottom();
		insuranceName = driver.findElement(By.xpath("//table[@id='rel_insurances']//tbody[@role='alert']//span")).getText();
		
		click(businessFntTabInsuranceRltSection);
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnBusinessFtnRltWindow);
		List <WebElement> leftBusinessFntRltWindowValuesList = driver.findElements(leftBusinessFntRltWindowValues);
		int leftBusinessFntRltWindowValuesListSize = leftBusinessFntRltWindowValuesList.size();
		Random random4 = new Random();
		int leftBusinessFntRltWindowValue = random4.nextInt(leftBusinessFntRltWindowValuesListSize);
		if(leftBusinessFntRltWindowValue==0){
			leftBusinessFntRltWindowValue = leftBusinessFntRltWindowValue + 1;
		}
		String businessFunctionNameInsideRelationshipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Business Functions')]/..//button[@class='btn moveall']/../../select//option"+"["+leftBusinessFntRltWindowValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Business Functions')]/..//button[@class='btn moveall']/../../select//option"+"["+leftBusinessFntRltWindowValue+"]")).click();
		enterText(businessFntLeftSearchTextBoxRltWindow,businessFunctionNameInsideRelationshipWindow);
		thread();
		click(leftMoveBtnBusinessFntRltWindow);
		enterText(businessFntRightSearchTextBoxRltWindow,businessFunctionNameInsideRelationshipWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(assetName+" "+"successfully mapped to Business Functions", getActualObjectTxt);
		thread();
		scrollToBottom();
		businessFunctionName = driver.findElement(By.xpath("//table[@id='rel_processes']//tbody[@role='alert']//span")).getText();
		
		click(tasksTabRltSection);
		click(addRemoveRelationsBtn);
		thread();
		click(taskRightMoveAllArrowRltWindow);
		List<WebElement> tasksLeftRltWindowValuesList = driver.findElements(tasksLeftRltWindowValues);
		int tasksLeftRltWindowValuesListSize = tasksLeftRltWindowValuesList.size();
		Random random5 = new Random();
		int randomTaskValue = random5.nextInt(tasksLeftRltWindowValuesListSize);
		if(randomTaskValue==0){
			randomTaskValue = randomTaskValue+1;
		}
		String taskNameInsideRelationShipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).click();
		enterText(tasksLeftSearchTxtBoxRltWindow,taskNameInsideRelationShipWindow);
		thread();
		click(tasksLeftMoveBtnRltWindow);
		enterText(tasksRightSearchTxtBoxRltWindow,taskNameInsideRelationShipWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(assetName+ " "+"successfully mapped to Tasks", getActualObjectTxt);
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
		click(employeesTabInsuranceRelationSection);
		thread();
		scrollToBottom();
		click(assetsTabInsuranceRelationSection);
		thread();
		Select assetsInnerCountDD1 = new Select(driver.findElement(By.name("rel_assets_length")));
		assetsInnerCountDD1.selectByVisibleText("100");
		thread();
		validateAssetValues();
		verifyAssertEquals(flag,"true");
		
		scrollToTop();
		click(assetGroupsInMainMenu);
		thread();
		Select assetGroupsCountDD = new Select(driver.findElement(By.name("asset_group_table_length")));
		assetGroupsCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedAsset = driver.findElement(By.xpath("//table[@id='asset_group_table']//tbody//tr[@data-for='"+assetGroupName+"']//td[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedAsset);
		mappedAsset.click();
		scrollToBottom();
		thread();
		Select assetsInnerCountDD2 = new Select(driver.findElement(By.name("rel_assets_length")));
		assetsInnerCountDD2.selectByVisibleText("100");
		thread();
		validateAssetValues();
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
		click(assetsTabInsuranceRelationSection);
		thread();
		Select assetsInnerCountDD4 = new Select(driver.findElement(By.name("rel_assets_length")));
		assetsInnerCountDD4.selectByVisibleText("100");
		thread();
		validateAssetValues();
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
		click(assetsTabInsuranceRelationSection);
		thread();
		Select assetsInnerCountDD5 = new Select(driver.findElement(By.name("rel_assets_length")));
		assetsInnerCountDD5.selectByVisibleText("100");
		thread();
		validateAssetValues();
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
		click(assetsTabInsuranceRelationSection);
		thread();
		Select assetsInnerCountDD6 = new Select(driver.findElement(By.name("rel_assets_length")));
		assetsInnerCountDD6.selectByVisibleText("100");
		thread();
		validateAssetValues();
		verifyAssertEquals(flag,"true");
	}
	
	
	public String validateAssetValues(){
		List<WebElement> assetList = driver.findElements(By.xpath("//table[@id='rel_assets']//tbody//tr"));
		int assetListSize = assetList.size();
		for(int i=1;i<=assetListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_assets']//tbody//tr["+i+"]//td[1]")).getText().equals(assetName)){
				System.out.println("Asset Name++"+driver.findElement(By.xpath("//table[@id='rel_assets']//tbody//tr["+i+"]//td[1]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_assets']//tbody//tr["+i+"]//td[2]")).getText(),assetStatus);
				System.out.println("Asset status++"+driver.findElement(By.xpath("//table[@id='rel_assets']//tbody//tr["+i+"]//td[2]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_assets']//tbody//tr["+i+"]//td[3]")).getText(),assetType);
				System.out.println("Asset Type++"+driver.findElement(By.xpath("//table[@id='rel_assets']//tbody//tr["+i+"]//td[3]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_assets']//tbody//tr["+i+"]//td[4]")).getText(),assetID);
				System.out.println("Asset ID++"+driver.findElement(By.xpath("//table[@id='rel_assets']//tbody//tr["+i+"]//td[4]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_assets']//tbody//tr["+i+"]//td[5]")).getText(),assetManufacturerName);
				System.out.println("Asset manufacturername++"+driver.findElement(By.xpath("//table[@id='rel_assets']//tbody//tr["+i+"]//td[5]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_assets']//tbody//tr["+i+"]//td[6]")).getText(),assetQuantity);
				System.out.println("Asset quantity++"+driver.findElement(By.xpath("//table[@id='rel_assets']//tbody//tr["+i+"]//td[6]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_assets']//tbody//tr["+i+"]//td[7]")).getText(),assetComments);
				System.out.println("Asset comments++"+driver.findElement(By.xpath("//table[@id='rel_assets']//tbody//tr["+i+"]//td[7]")).getText());
				flag="true";
			}
			else
			{
				flag="false";
			}
		}
		return flag;
	}

}
