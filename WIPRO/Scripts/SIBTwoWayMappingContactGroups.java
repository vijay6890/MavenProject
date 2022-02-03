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

public class SIBTwoWayMappingContactGroups extends Page{
	String flag;
	String contactGroupName,contactGroupPurpose,contactGroupComments;
	String contactFirstName,contactLastName,contactName,taskName;
	
    LoginLogout ll = new LoginLogout();
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
		waitForPageLoad();
		driver.findElement(By.id("cometchat_hide")).click();
	}
	
	@Test(priority=1)
	void contactGroupsDataMappingValidations() throws InterruptedException, IOException{
		click(contactGroupsInMainMenu);
		thread();
		List<WebElement> contactGroupsList = driver.findElements(By.xpath("//table[@id='contactgroup_table']//tbody[@role='alert']//tr"));
		int contactGroupsListSize = contactGroupsList.size();
		Random random = new Random();
		int contactGroupValue = random.nextInt(contactGroupsListSize);
		if(contactGroupValue==0){
			contactGroupValue = contactGroupValue+1;
		}
		System.out.println("Random value in contact groups list view+++"+contactGroupValue);
		driver.findElement(By.xpath("//table[@id='contactgroup_table']//tbody[@role='alert']//tr"+"["+contactGroupValue+"]")).click();
		contactGroupName = driver.findElement(By.xpath("//table[@id='contactgroup_table']//tbody[@role='alert']//tr["+contactGroupValue+"]")).getAttribute("data-for");
		System.out.println("Contact group name inside list+++"+contactGroupName);
		contactGroupPurpose = driver.findElement(By.xpath("//table[@id='contactgroup_table']//tbody[@role='alert']//tr["+contactGroupValue+"]//td[3]")).getText();
		System.out.println("Conatct group purpose inside list+++"+contactGroupPurpose);
		contactGroupComments = driver.findElement(By.xpath("//table[@id='contactgroup_table']//tbody[@role='alert']//tr["+contactGroupValue+"]//td[4]")).getText();
		System.out.println("Contact group comments++"+contactGroupComments);
		thread();
    	click(addRemoveRelationsBtn);
    	thread();
    	click(rightMoveAllArrowContactsRltWindow);
    	List<WebElement> contactsLeftRltWindowValuesList = driver.findElements(contactsLeftRltWindowValues);
    	int contactsLeftRltWindowValuesSize = contactsLeftRltWindowValuesList.size();
    	Random random1 = new Random();
    	int randomContactValue = random1.nextInt(contactsLeftRltWindowValuesSize);
    	if(randomContactValue==0){
    		randomContactValue = randomContactValue+1;
    	}
    	System.out.println("Contact value inside relationship section+++"+randomContactValue);
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
		verifyAssertEquals(contactGroupName+" "+"successfully mapped to Contacts", getActualObjectTxt);
		thread();
		scrollToBottom();
		contactFirstName = driver.findElement(By.xpath("//table[@id='rel_contacts']//tbody[@role='alert']//span")).getText();
		contactLastName = driver.findElement(By.xpath("//table[@id='rel_contacts']//tbody[@role='alert']//td[3]")).getText();
		contactName = contactFirstName+" "+contactLastName;
		
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
		System.out.println("Task value inside relationship section+++"+randomTaskValue);
		String taskNameInsideRelationShipSection = driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).getText();
		driver.findElement(By.xpath("//label[contains(text(),'List of Tasks')]/..//button[@class='btn moveall']/../../select//option"+"["+randomTaskValue+"]")).click();
		enterText(tasksLeftSearchTxtBoxRltWindow,taskNameInsideRelationShipSection);
		thread();
		click(tasksLeftMoveBtnRltWindow);
		enterText(tasksRightSearchTxtBoxRltWindow,taskNameInsideRelationShipSection);
		thread();
		click(submitButtonRelationShipWindow);
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(contactGroupName+" "+"successfully mapped to Tasks", getActualObjectTxt);
		thread();
		scrollToBottom();
		taskName = driver.findElement(By.xpath("//table[@id='rel_task']//tbody[@role='alert']//span")).getText();
		
		scrollToTop();
		click(contactsInMainMenu);
		thread();
		Select contactsCountDD = new Select(driver.findElement(By.name("contacts_table_length")));
		contactsCountDD.selectByVisibleText("100");
		thread();
		WebElement mappedContact = driver.findElement(By.xpath("//table[@id='contacts_table']//tbody//td[contains(text(),'"+contactFirstName+"')]/../td//div[contains(text(),'"+contactLastName+"')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", mappedContact);
		mappedContact.click();
		scrollToBottom();
		click(contactsGroupTabRltSection);
		thread();
		Select contactGroupsInnerCountDD1 = new Select(driver.findElement(By.name("rel_contact_grp_length")));
		contactGroupsInnerCountDD1.selectByVisibleText("100");
		thread();
		validateContactGroupValues();
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
		click(contactsGroupTabRltSection);
		thread();
		Select contactGroupsInnerCountDD2 = new Select(driver.findElement(By.name("rel_contact_grp_length")));
		contactGroupsInnerCountDD2.selectByVisibleText("100");
		thread();
		validateContactGroupValues();
		verifyAssertEquals(flag,"true");
	}

	
	public String validateContactGroupValues(){
		List<WebElement> contactGroupsList = driver.findElements(By.xpath("//table[@id='rel_contact_grp']//tbody//tr"));
		int contactGroupsListSize = contactGroupsList.size();
		for(int i=1;i<=contactGroupsListSize;i++){
			if(driver.findElement(By.xpath("//table[@id='rel_contact_grp']//tbody//tr["+i+"]//td[1]")).getText().equals(contactGroupName.trim())){
				System.out.println("Contact group name+++"+driver.findElement(By.xpath("//table[@id='rel_contact_grp']//tbody//tr["+i+"]//td[1]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_contact_grp']//tbody//tr["+i+"]//td[2]")).getText(),contactGroupPurpose);
				System.out.println("Contact group purpose+++"+driver.findElement(By.xpath("//table[@id='rel_contact_grp']//tbody//tr["+i+"]//td[2]")).getText());
				verifyAssertEquals(driver.findElement(By.xpath("//table[@id='rel_contact_grp']//tbody//tr["+i+"]//td[3]")).getText(),contactGroupComments);
				System.out.println("Contact group comments+++"+driver.findElement(By.xpath("//table[@id='rel_contact_grp']//tbody//tr["+i+"]//td[3]")).getText());
				flag="true";
			}else
			{
				flag="false";
			}
		}
		return flag;
	}
}
