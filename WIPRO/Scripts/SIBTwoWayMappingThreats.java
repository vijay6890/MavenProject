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
import static ObjectRepository.Employees.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Assets.*;
import static ObjectRepository.Insurance.*;
import static ObjectRepository.BusinessFunctions.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Teams.*;
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

public class SIBTwoWayMappingThreats extends Page{
	String flag;
	String threatName,threatComments;
	String locationName;
	String taskGroupName;
	
LoginLogout ll = new LoginLogout();
	
	/*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		waitForPageLoad();
		driver.findElement(By.id("cometchat_hide")).click();
	}*/
	
	@Test(priority=1)
	void threatsDataMappingValidations() throws InterruptedException, IOException{
		click(threatsInMainMenu);
		thread();
		Select threatsCountDD = new Select(driver.findElement(By.name("threat_table_length")));
		threatsCountDD.selectByVisibleText("100");
		List<WebElement> threatList = driver
				.findElements(By.xpath("//table[@id='threat_table']//tbody[@role='alert']//tr"));
		int insuranceListSize = threatList.size();
		Random threat = new Random();
		int threatValue = threat.nextInt(insuranceListSize);
		if (threatValue == 0) {
			threatValue = threatValue + 1;
		}
		System.out.println("Threat value inside list view++++"+threatValue);
		driver.findElement(By.xpath("//table[@id='threat_table']//tbody[@role='alert']//tr" + "[" + threatValue + "]")).click();
		threatName = driver.findElement(By.xpath("//table[@id='threat_table']//tbody[@role='alert']//tr[" + threatValue + "]//td[2]")).getText();
		System.out.println("Threat name+++"+threatName);
		threatComments=driver.findElement(By.xpath("//table[@id='threat_table']//tbody[@role='alert']//tr["+threatValue+"]//td[4]")).getText();
		System.out.println("Threat comments+++"+threatComments);
		thread();
		click(addRemoveRelationsBtn);
		thread();
		click(rightMoveAllBtnRelationWindow);
		List<WebElement> leftValueListInsideRelationShipWindow = driver
				.findElements(By.xpath("//label[contains(text(),'List of Locations')]/..//button[@class='btn moveall']/../../select//option"));
		int leftValueListInsideRelationShipWindowSize = leftValueListInsideRelationShipWindow.size();
		Random random= new Random();
		int locationNameValue = random.nextInt(leftValueListInsideRelationShipWindowSize);
		if (locationNameValue == 0) {
			locationNameValue = locationNameValue + 1;
		}
		System.out.println("Location value inside relationship section++++"+locationNameValue);
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
		verifyAssertEquals(threatName + " " + "successfully mapped to Locations", getActualObjectTxt);
		thread();
		scrollToBottom();
		thread();
		locationName = driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody//td//div//span")).getText();
		
		
		click(taskGroupsTabRltSection);
		click(addRemoveRelationsBtn);
		thread();
		click(taskGroupsRightMoveAllArrowRltWindow);
		List <WebElement> leftTaskGroupsRltWindowValuesList = driver.findElements(taskGroupLeftRltWindowValues);
		int leftTaskGroupsRltWindowValuesListSize = leftTaskGroupsRltWindowValuesList.size();
	    Random random1 = new Random();
		int leftTaskGroupsRltWindowValue = random1.nextInt(leftTaskGroupsRltWindowValuesListSize);
		if(leftTaskGroupsRltWindowValue==0){
			   leftTaskGroupsRltWindowValue = leftTaskGroupsRltWindowValue + 1;
			}
		System.out.println("Task group value inside relationship section++++"+leftTaskGroupsRltWindowValue);
		String taskGroupNameInsideRelationshipWindow = driver.findElement(By.xpath("//label[contains(text(),'List of Task Groups')]/..//button[@class='btn moveall']/../../select//option"+"["+leftTaskGroupsRltWindowValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Task Groups')]/..//button[@class='btn moveall']/../../select//option"+"["+leftTaskGroupsRltWindowValue+"]")).click();
		enterText(taskGroupLeftSearchTxtBoxRltWindow,taskGroupNameInsideRelationshipWindow);
		thread();
		click(taskGroupsLeftMoveBtnRltWindow);
		enterText(taskGroupsRightSearchTxtBoxRltWindow,taskGroupNameInsideRelationshipWindow);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(threatName+" "+"successfully mapped to Task Groups", getActualObjectTxt);
		thread();
		scrollToBottom();
		thread();
		taskGroupName = driver.findElement(By.xpath("//table[@id='rel_task_grp']//tbody//td[1]")).getText();
		
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
		thread();
		click(threatsTabRelationSection);
		scrollToBottom();
		Select threatsInnerCountDD = new Select(driver.findElement(By.name("rel_threats_length")));
		threatsInnerCountDD.selectByVisibleText("100");
		thread();	
		validateThreatValues();
		verifyAssertEquals(flag,"true");
		
		
	}
	
	
	public String validateThreatValues(){
		
		List<WebElement> threatList = driver.findElements(By.xpath("//table[@id='rel_threats']//tbody//tr"));
		int threatListSize = threatList.size();
		for(int i=1;i<=threatListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_threats']//tbody[@role='alert']//tr["+i+"]//td[1]")).getText().equals(threatName)){
				System.out.println("Threat name++++"+driver.findElement(By.xpath("//table[@id='rel_threats']//tbody[@role='alert']//tr["+i+"]//td[1]")).getText());
				WebElement mappedThreat = driver.findElement(By.xpath("//table[@id='rel_threats']//tbody[@role='alert']//tr["+i+"]//td[1]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedThreat);
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_threats']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText(),threatComments);
				System.out.println("Threat comments++++"+driver.findElement(By.xpath("//table[@id='rel_threats']//tbody[@role='alert']//tr["+i+"]//td[2]")).getText());
				flag="true";
			}else
			{
				flag="false";
			}
		}
		return flag;
	}
	


}
