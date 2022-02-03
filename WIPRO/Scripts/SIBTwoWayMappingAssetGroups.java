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

public class SIBTwoWayMappingAssetGroups extends Page{
	String flag;
	String assetGroupName,assetGroupID,assetGroupComments;
	String assetName,taskName;
	
    LoginLogout ll = new LoginLogout();
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		waitForPageLoad();
		driver.findElement(By.id("cometchat_hide")).click();
	}
	
	@Test(priority=1)
	void assetGroupsDataMappingValidations() throws InterruptedException, IOException{
		click(assetGroupsInMainMenu);
		thread();
		List<WebElement> assetGroupsList = driver.findElements(By.xpath("//table[@id='asset_group_table']//tbody[@role='alert']//tr"));
		int assetGroupsListSize = assetGroupsList.size();
		Random random = new Random();
		int assetGroupValue = random.nextInt(assetGroupsListSize);
		if(assetGroupValue==0){
			assetGroupValue = assetGroupValue+1;
		}
		System.out.println("Random value in asset groups++++"+assetGroupValue);
		driver.findElement(By.xpath("//table[@id='asset_group_table']//tbody[@role='alert']//tr"+"["+assetGroupValue+"]")).click();
		assetGroupName = driver.findElement(By.xpath("//table[@id='asset_group_table']//tbody[@role='alert']//tr["+assetGroupValue+"]")).getAttribute("data-for");
		System.out.println("Asset group name inside list view+++"+assetGroupName);
		assetGroupID = driver.findElement(By.xpath("//table[@id='asset_group_table']//tbody[@role='alert']//tr["+assetGroupValue+"]//td[3]")).getText();
		System.out.println("Asset group ID  inside list view+++"+assetGroupID);
		assetGroupComments = driver.findElement(By.xpath("//table[@id='asset_group_table']//tbody[@role='alert']//tr["+assetGroupValue+"]//td[4]")).getText();
		System.out.println("Asset group comments  inside list view+++"+assetGroupComments);
		thread();
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
		System.out.println("Random value in assets relationship section ++++"+assetValue);
		String assetNameInsideRelationshipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option"+"["+assetValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Assets')]/..//button[@class='btn moveall']/../../select//option"+"["+assetValue+"]")).click();
		enterText(assetLeftSearchTxtBoxRltWindow,assetNameInsideRelationshipWindow);
		thread();
		click(leftMoveBtnAssetRltWindow);
		enterText(assetRightSearchTxtBoxRltWindow,assetNameInsideRelationshipWindow);
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(assetGroupName+" "+"successfully mapped to Assets", getActualObjectTxt);
		thread();
		scrollToBottom();
		assetName = driver.findElement(By.xpath("//table[@id='rel_assets']//tbody[@role='alert']//span")).getText();
		
		
		click(tasksTabRltSection);
		click(addRemoveRelationsBtn);
		thread();
		click(taskRightMoveAllArrowRltWindow);
		List<WebElement> tasksLeftRltWindowValuesList = driver.findElements(tasksLeftRltWindowValues);
		int tasksLeftRltWindowValuesListSize = tasksLeftRltWindowValuesList.size();
		Random random2 = new Random();
		int randomTaskValue = random2.nextInt(tasksLeftRltWindowValuesListSize);
		if(randomTaskValue==0){
			randomTaskValue = randomTaskValue+1;
		}
		System.out.println("Random task value+++++"+randomTaskValue);
		String taskNameInsideRelationShipSection = driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).click();
		enterText(tasksLeftSearchTxtBoxRltWindow,taskNameInsideRelationShipSection);
		thread();
		click(tasksLeftMoveBtnRltWindow);
		enterText(tasksRightSearchTxtBoxRltWindow,taskNameInsideRelationShipSection);
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(assetGroupName+ " "+"successfully mapped to Tasks", getActualObjectTxt);
		thread();
		scrollToBottom();
		taskName = driver.findElement(By.xpath("//table[@id='rel_task']//tbody[@role='alert']//span")).getText();
		
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
		click(assetGroupsTabRltSection);
		thread();
		Select assetsInnerCountDD1 = new Select(driver.findElement(By.name("rel_asset_grp_length")));
		assetsInnerCountDD1.selectByVisibleText("100");
		thread();
		validateAssetGroupValues();
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
		click(assetGroupsTabRltSection);
		thread();
		Select assetsInnerCountDD2 = new Select(driver.findElement(By.name("rel_asset_grp_length")));
		assetsInnerCountDD2.selectByVisibleText("100");
		thread();
		validateAssetGroupValues();
		verifyAssertEquals(flag,"true");
	}
	
	public String validateAssetGroupValues(){
		List<WebElement> assetGroupsList = driver.findElements(By.xpath("//table[@id='rel_asset_grp']//tbody//tr"));
		int assetGroupsListSize = assetGroupsList.size();
		for(int i=1;i<=assetGroupsListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_asset_grp']//tbody//tr["+i+"]//td[1]")).getText().equals(assetGroupName)){
				System.out.println("Asset group name+++"+driver.findElement(By.xpath("//table[@id='rel_asset_grp']//tbody//tr["+i+"]//td[1]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_asset_grp']//tbody//tr["+i+"]//td[2]")).getText(),assetGroupID);
				System.out.println("Asset group ID+++"+driver.findElement(By.xpath("//table[@id='rel_asset_grp']//tbody//tr["+i+"]//td[2]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_asset_grp']//tbody//tr["+i+"]//td[3]")).getText(),assetGroupComments);
			    System.out.println("Asset group comments+++"+driver.findElement(By.xpath("//table[@id='rel_asset_grp']//tbody//tr["+i+"]//td[3]")).getText());
			    flag="true";
			}else
			{
				flag="false";
			}
		}
		return flag;
	}

}
