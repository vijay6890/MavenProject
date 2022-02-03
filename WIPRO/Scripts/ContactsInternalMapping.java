/******************************************************************************************************************************
	 * 
	 * @author Manikandan
	 * 
	 * Test 1: Login to SIB application
	 * Test 2: Navigate to Contacts module
	 * Test 3: Verify sub module mapping functionality in Contacts groups sub tab
	 * Test 4: Verify sub module mapping functionality in Locations sub tab
	 * Test 5: Verify sub module mapping functionality in Tasks sub tab
	 * Test 6: Verify label validations in Contacts screen
	*******************************************************************************************************************************/

package Scripts;
import static ObjectRepository.Contacts.*;
import static UIWrappers.UIObjects.click;
import static UIWrappers.UIObjects.thread;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import UIWrappers.Page;
import UIWrappers.UIObjects;

import jxl.read.biff.BiffException;
public class ContactsInternalMapping extends Page

{
	
	int size=0;
	String flag;
	String tasks[] = { "resource", "resourcegrp", "assets", "asset_group", "contacts", "contact_group", "task_group",
	"processes" };
	String contactGroups[]={"contacts","tasks"};
			
	
	
	
	LoginLogout ll = new LoginLogout();
	/*@Test(priority = 0)
	void login() throws BiffException, IOException, InterruptedException {
		ll.loginToSIB();
    }*/
	@Test(priority = 1)
	void contactScreenNavigation() throws InterruptedException {
		click(contactsInMainMenu);
	Thread.sleep(3000);
		int contactValuesSize=driver.findElements(contactGridValues).size();
			System.out.println(contactValuesSize);
		System.out.println("--->"+contactValuesSize);
		thread();
		int contactSelection = new Random().nextInt(contactValuesSize);
		if (contactSelection == 0) {
			 contactSelection = +1;
		 
		driver.findElement(By.xpath("//table[@id='contacts_table']//tbody//tr"+"["+contactSelection+"]")).click();
				Thread.sleep(2000);}
	
	click(addRemoveButton);
		thread();
		click(contRightBoxRemoveAll);
		thread();
		List<WebElement> contLeftList=driver.findElements(contLeftBox);
		 int ContDyn = new Random().nextInt(contLeftList.size());
		 if(ContDyn==0){
				 ContDyn=+1;
			 }
			String taskValue=driver.findElement(By.xpath("//select[@id='map_list']/..//select[contains(@name,'map_list[]_helper1')]/option"+"["+ContDyn+"]")).getText();
			driver.findElement(By.xpath("//select[@id='map_list']/..//select[contains(@name,'map_list[]_helper1')]/option"+"["+ContDyn+"]")).click();
		UIObjects.enterText(contLeftSearchBox,taskValue);
		System.out.println("The picked value from list box"+taskValue);
		thread();
		click(contleftBoxPushOne);
		thread();
		click(submitBtn);
		thread();	
		String addedTaskRelation=driver.findElement(By.xpath("//table[@id='rel_task']//tbody[@role='alert']//td[1]/div/span")).getText();
		System.out.println(addedTaskRelation);
		org.testng.Assert.assertEquals(taskValue, addedTaskRelation,"The added task relation is available in the grid"); 
		click(contactViewButton);
	   Thread.sleep(2000);
	   }
	@Test(priority = 2)
	
	       	
	void tasksInternalMapping() throws InterruptedException {
	     List<WebElement> submitButtonList = driver.findElements(submitButton);
		 List<WebElement> gridValueComp = driver.findElements(gridValue);
		 List<WebElement> s = driver.findElements(By.xpath("//div[contains(@class,'span12 module-tabitem')]"));
		 List<WebElement> moveAllBtn = driver.findElements(moveAll);
		 List<WebElement> removeAllBtn = driver.findElements(removeAll);
		 System.out.println(s.size());
		 int bt = 0,k=0,j=0;
		 String [] array =new String[100];
		 for (int i = 0; i <s.size(); i++) {
			 s.get(i).click();
			 List<WebElement> addRemoveButton = driver.findElements(addremoveRelationsButton);
			 System.out.println("The Valus of BT is ----->" + bt);
			 addRemoveButton.get(bt).click();
			 bt++;
			 JavascriptExecutor jse = (JavascriptExecutor) driver;
			 jse.executeScript("window.scrollBy(0,250)", "");
			 moveAllBtn.get(i).click();
			 WebElement inputBox = driver
					 .findElement(By.xpath("//select[@id='bootstrap-duallistbox-nonselected-list_map_list[]']"));
			 String leftBoxValues = inputBox.getAttribute("value");
			 thread();
			 if (inputBox.getAttribute("value").equals("")) {
				 System.out.println("All data were moved to Mapped Items list box");
			 }
			 removeAllBtn.get(i).click();
			 thread();
			 if (rightBox.equals(null)) {
				 System.out.println(("All data were moved to Mapped Items right side list box"));
			 }

			 By[] c = { empListValues, teamListValues, assetListValues, assetGroupListValues, contactListValues,
					 contactGroupListValues, taskGroupListValues, businessFunctionListValues };
			int newsize = driver.findElements(c[i]).size();
			 System.out.println("------>" + newsize);
			 int empDyn = new Random().nextInt(newsize);
			 if (empDyn == 0) {
				 empDyn = +1;
			 }
			 String empValue = driver.findElement(By.xpath("//select[contains(@id,'ddl_" + tasks[k]
					 + "_mdata')]/..//select[@name='map_list[]_helper1']/option"
					 + "[" + empDyn + "]")).getText();
			 driver.findElement(By.xpath("//select[contains(@id,'ddl_" + tasks[k]
					 + "_mdata')]/..//select[@name='map_list[]_helper1']/option"
					 + "[" + empDyn + "]")).click();
			 List<WebElement> leftSearchBox = driver.findElements(By.xpath("//input[@class='filter']"));
			 List<WebElement> moveAll = driver.findElements(By.xpath("//button[@class='btn moveall']"));
			 List<WebElement> pushRight = driver.findElements(By.xpath("//button[@class='btn move']"));
			 thread();
			 leftSearchBox.get(j).sendKeys(empValue);
			 //driver.findElements(By.xpath("//input[@class='filter']")).get(j).click();
			 thread();
			 pushRight.get(i).click();
			 j++;
			 leftSearchBox.get(j).sendKeys(empValue);
			 leftSearchBox.get(j).equals(empValue);
			 thread();
			 submitButtonList.get(k).click();
			 j++;
			 System.out.println("The Filtered value moved to the right box successfully-->" + empValue);
			 WebDriverWait wait = new WebDriverWait(driver, 5);
			 wait.until(ExpectedConditions.presenceOfElementLocated(gridValue));
			 thread();
			   array[i]= driver.findElements(gridValue).get(k).getText();
			 System.out.println("Array value is --->"+array[i]);
	   List<WebElement> noRecordsFound=driver.findElements(By.xpath("//table[@id='rel_task']//td[@class='dataTables_empty']"));
	    	
			 if(empValue.contains(driver.findElements(gridValue).get(k).getText()))
			 {
				 flag="true";
			 }
			 else
			 {
				 flag="false";
			 }
			 
			UIObjects.verifyAssertEquals(flag,"true"); 
			 System.out.println("The added value is listed out in the Grid");
			 thread();
			 k++;
			
	
		 }
		 click(contactViewButton);
		 System.out.println(" The internal mapping has been completed for Tasks Tab");
	}
@Test(priority=3)

void contactGroupsInternalMapping() throws InterruptedException {
	click(contactGroupsTab);
	thread();
		click(addRemoveButton);
		thread();
		click(contRightBoxRemoveAll);
		thread();
		List<WebElement> contLeftList=driver.findElements(contLeftBox);
		 int ContDyn = new Random().nextInt(contLeftList.size());
		 if(ContDyn==0){
				 ContDyn=+1;
			 }
		String contactGroupValue=driver.findElement(By.xpath("//select[@id='map_list']/..//select[contains(@name,'map_list[]_helper1')]/option"+"["+ContDyn+"]")).getText();
		
		driver.findElement(By.xpath("//select[@id='map_list']/..//select[contains(@name,'map_list[]_helper1')]/option"+"["+ContDyn+"]")).click();
		UIObjects.enterText(contLeftSearchBox,contactGroupValue);
		System.out.println("The picked value from list box"+contactGroupValue);
		thread();
		click(contleftBoxPushOne);
		thread();
		click(submitBtn);
		thread();	
		String addedContactGroupRelation=driver.findElement(By.xpath("//table[@id='rel_contact_grp']//tr[@class='odd nestable-prow']//td[1]")).getText();
		System.out.println(addedContactGroupRelation);
		org.testng.Assert.assertEquals(contactGroupValue, addedContactGroupRelation,"The added ContactGroup relation is available in the grid"); 
		click(contactGroupViewButton);
	   Thread.sleep(2000);
	   
    List<WebElement> submitButtonList = driver.findElements(submitButton);
	 List<WebElement> gridValueComp = driver.findElements(gridValue);
	 List<WebElement> contGrpSubMenus  = driver.findElements(By.xpath("//div[@class='span2 module-tab-list']/div"));
	 List<WebElement> moveAllBtn = driver.findElements(moveAll);
	 List<WebElement> removeAllBtn = driver.findElements(removeAll);
	 List<WebElement> CtGrpAddRemoveButton = driver.findElements(contactGrpAddRemoveBtns);
	 //System.out.println(contGrpSub
	 int bt = 0,k=0,j=0;
	 String [] array =new String[100];
	 for (int i = 0;i<CtGrpAddRemoveButton.size();i++) {
		 if(i==1)
		 {
		  contGrpSubMenus.get(i).click();
		 }
		 Thread.sleep(5000);
		  CtGrpAddRemoveButton.get(bt).click();
		 System.out.println(CtGrpAddRemoveButton.size());
		 System.out.println("The Valus of BT is ----->" + bt);
		  Thread.sleep(3000);
		  System.out.println(CtGrpAddRemoveButton.get(bt));
		

		 bt++;
		 JavascriptExecutor jse = (JavascriptExecutor) driver;
		 jse.executeScript("window.scrollBy(0,250)", "");
		 thread();
		 moveAllBtn.get(i).click();
		 WebElement inputBox = driver
				 .findElement(By.xpath("//select[@id='bootstrap-duallistbox-nonselected-list_map_list[]']"));
		 String leftBoxValues = inputBox.getAttribute("value");
		 thread();
		 if (inputBox.getAttribute("value").equals("")) {
			 System.out.println("All data were moved to Mapped Items list box");
		 }
		 removeAllBtn.get(i).click();
		 thread();
		 if (rightBox.equals(null)) {
			 System.out.println(("All data were moved to Mapped Items right side list box"));
		 }

		 By[] contactGroupList = {  contactListValues,tasksListValues };
			
		  int newsize = driver.findElements(contactGroupList[i]).size();
		 System.out.println("------>" + newsize);
		 thread();
		 int contDyn = new Random().nextInt(newsize);
		 if (contDyn == 0) {
			 contDyn = +1;
		 }
		 String contGrpTabListValues = driver.findElement(By.xpath("//select[contains(@id,'ddl_" + contactGroups[k]+ "_mdata')]/..//select[@name='map_list[]_helper1']/option" + "[" + contDyn + "]")).getText();
		 driver.findElement(By.xpath("//select[contains(@id,'ddl_" + contactGroups[k]+ "_mdata')]/..//select[@name='map_list[]_helper1']/option"
				 + "[" + contDyn + "]")).click();
		 List<WebElement> leftSearchBox = driver.findElements(By.xpath("//table[@id='rel_contact_grp']//input[@class='filter']"));
		 List<WebElement> moveAll = driver.findElements(By.xpath("//button[@class='btn moveall']"));
		 List<WebElement> pushRight = driver.findElements(By.xpath("//button[@class='btn move']"));
		 thread();
		 leftSearchBox.get(j).sendKeys(contGrpTabListValues);
		 //driver.findElements(By.xpath("//input[@class='filter']")).get(j).click();
		 thread();
		 pushRight.get(i).click();
		 j++;
		 leftSearchBox.get(j).sendKeys(contGrpTabListValues);
		 leftSearchBox.get(j).equals(contGrpTabListValues);
		 thread();
		 
		 submitButtonList.get(k).click();
		 j++;
		 System.out.println("The Filtered value moved to the right box successfully-->" + contGrpTabListValues);
		 WebDriverWait wait = new WebDriverWait(driver, 5);
		 wait.until(ExpectedConditions.presenceOfElementLocated(contactGroupGridValue));
		 thread();
		   array[i]= driver.findElements(contactGroupGridValue).get(k).getText();
		 System.out.println("Array value is --->"+array[i]);
  List<WebElement> noRecordsFound=driver.findElements(By.xpath("//table[@id='rel_task']//td[@class='dataTables_empty']"));
   	
		 if(contGrpTabListValues.contains(driver.findElements(contactGroupGridValue).get(k).getText()))
		 {
			 flag="true";
		 }
		 else
		 {
			 flag="false";
		 }
		 
		UIObjects.verifyAssertEquals(flag,"true"); 
		 System.out.println("The added value is listed out in the Grid");
		 thread();
		 k++;
		 }
	 click(contactGroupViewButton);
	 System.out.println(" The internal mapping has been completed for ContactGroups Tab");
}}
