package Scripts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;
import model.ContactGroupDetails;

import static UIWrappers.UIObjects.*;
import static ObjectRepository.ContactGroups.*;

import static ObjectRepository.Locations.*;
import static ObjectRepository.Tasks.choosefiles;
import static ObjectRepository.Tasks.tskClearBtn;
import static ObjectRepository.Threats.*;
import static Config.TakScreenshot.*;

/***********************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Verify Add Contact Group page
 * Test 2: Add New Contact Group details
 * Test 3: Verify successfully added message
 * Test 4: Verify added contact group details in list view
 * Test 5: Map contact groups to Contacts & Tasks
 * Test 6: Verify mapped successfully message for Contacts & Tasks
 * Test 7: Search mapped Contacts & Tasks(Valid/Invalid)
 * Test 8: Edit contact group name 
 * Test 9: Verify edited details in list view
 * Test 10: Check contact group name in Relationship title(List View & Edit page)
 * Test 11: Check List view pagination
 * Test 12: Show Entries drop down
 * Test 13: List view Select all check box
 * Test 14: Search with Valid/Invalid Contact Group Name
 * 
************************************************************************************************************/

public class CntGrp_AddEdit extends Page{
	
	LoginLogout ll = new LoginLogout();
	
	String contactGroupName, groupPurpose, comments;
	String modContactGroupName, modGroupPurpose, modComments; 
	
	String phContactGroupName = "Contact Group Name";
	String phGroupPurpose = "Contact Group Purpose";
	
	ArrayList<ContactGroupDetails> cntGrpList = new ArrayList<ContactGroupDetails>();
		
/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}     */
	
	@Test(priority=1)
	void verifyAddContactGroupPage() throws BiffException, IOException, InterruptedException
	{
		click(contactGroupsInMainMenu);
		waitForPageLoad();
		
		click(cntGrpOvrAddBtn);
		thread();
	}
	
	@Test(priority=2)
	void addNewContactGroup() throws BiffException, IOException, InterruptedException
	{
		
		getContactGroupsSheetFromExcel();
		Thread.sleep(1000);
		/*if(cntGrpList.size() == 0)
		{
			getContactGroupsSheetFromExcel();
			System.out.println("Row Count "+cntGrp.getRows());
			for(int row=1; row<cntGrp.getRows(); row++)
			{
				ContactGroupDetails contactGroupDetails = new ContactGroupDetails();
				
				for(int col=0; col<cntGrp.getColumns(); col++)
				{
					if(col == 0)
						contactGroupDetails.setContactGroupName(cntGrp.getCell(col, row).getContents());
					if(col == 1)
						contactGroupDetails.setGroupPurpose(cntGrp.getCell(col, row).getContents());
					if(col == 2)
						contactGroupDetails.setComments(cntGrp.getCell(col, row).getContents());				
				}				
				cntGrpList.add(contactGroupDetails);
			}
		}*/
		
		Random random = new Random();
		int rcntGrp= random.nextInt(cntGrp.getRows()-1)+1;
		System.out.println("random num "+rcntGrp);
			
			
			//	Contact Group Name
			contactGroupName = cntGrp.getCell(0, rcntGrp).getContents().trim();
            enterText(cntGrpContactGroupName, contactGroupName);
            thread();
			
			//	Contact Group Purpose
			groupPurpose = cntGrp.getCell(1,rcntGrp).getContents().trim();
			enterText(cntGrpGroupPurpose, groupPurpose);
			
			//	Comments
			comments = cntGrp.getCell(2, rcntGrp).getContents().trim();
			
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);
			
			enterText(scEdtrCommentsField, comments);
			thread();		
			switchBackFromFrame();
			takeScreenshot();
			
			webElement(cntGrpSubmitBtn);
			scrollInnerScrollBar(webelementFrame);
			
			click(cntGrpSubmitBtn);
			Thread.sleep(1000);
			takeScreenshot();  
			  getObjectText(msgNotificationBar);
				if(getActualObjectTxt.equals(contactGroupName+" Successfully Added"))
				{
					System.out.println("perform contact group add operation");
				}
				else
				{
					verifyAddContactGroupPage();
					thread();
					addNewContactGroup();
					Thread.sleep(1000);
				}
				}
	
	@Test(priority=3)
	void verifyAddedContactGrpDetailsInListView() throws InterruptedException
	{
		getTotalValuesIndd(cntGrpContactGrpNameTtl);
		for(int i=1; i<totalDDValCount; i++)
		{
			String getAddedCntGrpName = driver.findElement(By.xpath("//table[@id='contactgroup_table']//tr["+i+"]/td[2]")).getText();			
			if(getAddedCntGrpName.equals(contactGroupName))
			{
				for(int j=2; j<5; j++)
				{
					String chkAddedDtls = driver.findElement(By.xpath("//table[@id='contactgroup_table']//tr["+i+"]/td["+j+"]")).getText();
					if(j == 2)
						verifyAssertEquals(contactGroupName, chkAddedDtls);	//	Group Name
					if(j == 3)
						verifyAssertEquals(groupPurpose, chkAddedDtls);	//	Group Purpose
					if(j == 4)
						verifyAssertEquals(comments, chkAddedDtls);	//	Comments					
				}
				break;
			}		
		}		
		thread();
	}
	
	@Test(priority=4)
	void mapContactsToContactGroup() throws InterruptedException, IOException
	{
		click(rltnAddRemoveRltnsBtn);
		thread();
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Contacts To "+contactGroupName.trim(), getActualObjectTxt);
		
		doMappingt();
		click(mapSubmitBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(contactGroupName.trim()+" successfully mapped to Contacts", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=5)
	void searchMappedContacts() throws IOException
	{
		getTotalValuesIndd(cntGrpRltnContactsTtl);
		
		//	Search Valid Contact Group Name
		Random cntGrpNm = new Random();
		int rcntGrpNm = cntGrpNm.nextInt(totalDDValCount-1)+1;	
		
		String contactGroupNm = driver.findElement(By.xpath("//table[@id='rel_contacts']//tr["+rcntGrpNm+"]/td[2]")).getText();
		enterText(cntGrpRltnContactsSearchBox, contactGroupNm);
		getObjectText(cntGrpRltnContactsSrchRslt);
		verifyAssertEquals(contactGroupNm, getActualObjectTxt);
		takeScreenshot();
		clear(cntGrpRltnContactsSearchBox);
		enterKeyInKyBord(cntGrpRltnContactsSearchBox);
		
		//	Search Invalid Contact Group Name
		enterText(cntGrpRltnContactsSearchBox, "Inv Cnt Grp Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(cntGrpRltnContactsSearchBox);
		enterKeyInKyBord(cntGrpRltnContactsSearchBox);
	}
	
	@Test(priority=6)
	void mapTasksToContactGroup() throws InterruptedException, IOException
	{
		click(tasksTb);
		thread();
		click(rltnAddRemoveRltnsBtn);
		thread();
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Tasks To "+contactGroupName.trim(), getActualObjectTxt);
		
		doMappingt();
		click(mapSubmitBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(contactGroupName.trim()+" successfully mapped to Tasks", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=7)
	void searchMappedTasks() throws IOException, InterruptedException
	{
		getTotalValuesIndd(cntGrpRltnTasksTtl);
		
		//	Search Valid Tasks Name
		Random tskNm = new Random();
		int rtskNm = tskNm.nextInt(totalDDValCount-1)+1;		
		
		String taskNm = driver.findElement(By.xpath("//table[@id='rel_task']//tr["+rtskNm+"]/td[2]")).getText();
		enterText(cntGrpRltnTasksSearchBox, taskNm);
		getObjectText(cntGrpRltnTasksSrchRslt);
		verifyAssertEquals(taskNm.trim(), getActualObjectTxt);
		takeScreenshot();
		clear(cntGrpRltnTasksSearchBox);
		enterKeyInKyBord(cntGrpRltnTasksSearchBox);
		
		//	Search Invalid Tasks Name
		enterText(cntGrpRltnTasksSearchBox, "Inv Task Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(cntGrpRltnTasksSearchBox);
		enterKeyInKyBord(cntGrpRltnTasksSearchBox);
		thread();
	}
	
	@Test(priority=8)
	void verifyClearndCancelBtnFunc() throws InterruptedException, IOException
	{
		getTotalValuesIndd(cntGrpContactGrpNameTtl);
		Random random = new Random();
		int rrow = random.nextInt(totalDDValCount-1)+1;
		
		driver.findElement(By.xpath("//table[@id='contactgroup_table']//tr["+rrow+"]/td[5]/a")).click();		//	Edit Button
		thread();
		
		click(cntGrpClearBtn);
		thread();
		takeScreenshot();
		
		//	Contact Group Name
		getAttributePh(cntGrpContactGroupName);
		verifyAssertEquals(phContactGroupName, getAttribtePh);		
		
		//	Group Purpose
		getAttributePh(cntGrpGroupPurpose);
		verifyAssertEquals(phGroupPurpose, getAttribtePh);		
		
		//	Comments
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);		
		getObjectText(scEdtrCommentsField);
		verifyAssertEquals("", getActualObjectTxt);
		switchBackFromFrame();
		thread();
		
		//	Verify Cancel button
		click(cntGrpCancelBtn);
		thread();
		verifyAssert(cntGrpContactGroupsSearchBox);		
	}
	
	@Test(priority=9)
	void editContactGroupName() throws InterruptedException, BiffException, IOException
	{
		getContactGroupsSheetFromExcel();
		
		getTotalValuesIndd(cntGrpContactGrpNameTtl);
		//Random random = new Random();
	//	int rrow = random.nextInt(totalDDValCount-1)+1;
		
	//	driver.findElement(By.xpath("//table[@id='contactgroup_table']//tr["+rrow+"]/td[5]/a")).click();	//	Edit Button
		driver.findElement(By.xpath("//table[@id='contactgroup_table']//tr[1]/td[5]/a")).click();	//	Edit Button
		thread();
		
		click(cntGrpClearBtn);
		thread();
		
		//	Contact Group Name
		Random random1 = new Random();
		int rrowex = random1.nextInt(cntGrp.getRows()-1)+1;
		modContactGroupName = cntGrp.getCell(0, rrowex).getContents().trim();
		enterText(cntGrpContactGroupName, modContactGroupName);
		
		//	Group Purpose
		modGroupPurpose = cntGrp.getCell(1, rrowex).getContents().trim();
		enterText(cntGrpGroupPurpose, modGroupPurpose);
		
		//	Comments
		modComments = cntGrp.getCell(2, rrowex).getContents().trim();
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);
		
		enterText(scEdtrCommentsField, modComments);
		thread();
		switchBackFromFrame();
		takeScreenshot();
		thread();
		
		click(cntGrpSubmitBtn);
		Thread.sleep(1000);
		try
	    {
			
			
			   File newFile = new File(xlFilesLocation);
				if (newFile.exists())
				            {
					            getObjectText(msgNotificationBar);
					            click(cntGrpClearBtn);
					            thread();
					            addNewContactGroup();
				            }
			 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Thread.sleep(1000);
		//	Verify Successfully Updated message
		getObjectText(msgNotificationBar);
		verifyAssertEquals(modContactGroupName+" Successfully Updated", getActualObjectTxt);
		thread();
	}
	
	@Test(priority=10)
	void verifyModifiedDetailsInListView()
	{
		getTotalValuesIndd(cntGrpContactGrpNameTtl);
		for(int i=1; i<totalDDValCount; i++)
		{
			String getModCntGrpName = driver.findElement(By.xpath("//table[@id='contactgroup_table']//tr["+i+"]/td[2]")).getText();
			
			if(getModCntGrpName.contains(modContactGroupName))
			{
				for(int j=2; j<4; j++)
				{
					String chkModDtls = driver.findElement(By.xpath("//table[@id='contactgroup_table']//tr["+i+"]/td["+j+"]")).getText();
					
					if(j == 2)
						verifyAssertEquals(modContactGroupName, chkModDtls);
					if(j == 3)
						verifyAssertEquals(modGroupPurpose, chkModDtls);
					if(j == 4)
						verifyAssertEquals(modComments, chkModDtls);				
				}
				break;
			}			
		}
	}
	
	@Test(priority=11)
	void verifyCntGrpNameInRelationshipTitle() throws InterruptedException
	{
		getTotalValuesIndd(cntGrpContactGrpNameTtl);
		
		Random random = new Random();
		int rr = random.nextInt(totalDDValCount-1)+1;
		WebElement rCntGrpName = driver.findElement(By.xpath("//table[@id='contactgroup_table']//tr["+rr+"]/td[2]"));
		String contGrpName = rCntGrpName.getText();
		rCntGrpName.click();
		
		//	Check Name in List View Page
		getObjectText(cntGrpRltnTitleName);
		verifyAssertEquals(contGrpName, getActualObjectTxt.substring(4));
		
		driver.findElement(By.xpath("//table[@id='contactgroup_table']//tr["+rr+"]/td[5]/a")).click();
		thread();
		
		getObjectText(cntGrpRltnTitleName);
		thread();
		verifyAssertEquals(contGrpName, getActualObjectTxt.substring(4));
		
		click(cntGrpSubmitBtn);
		thread();
	}
	
	@Test(priority=12)
	void verifyContactGrpListViewPagination() throws IOException, InterruptedException
	{
		getTotalValuesIndd(cntGrpListViewPagination);
				
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
			int randomPge = randomPagi.nextInt(totalDDValCount-1)+1;
			driver.findElement(By.xpath("//div[@class='dataTables_paginate paging_bootstrap pagination pagination-right']/ul/li["+randomPge+"]/a")).click();
			thread();
			takeScreenshot();
			click(pagiStartArw);
			thread();
		}
	}
	
	@Test(priority=13)
	void verifySelectAllChkBox() throws IOException, InterruptedException
	{
		click(cntGrpSelectAllChkBox);
		takeScreenshot();
		
		getTotalValuesIndd(cntGrpContactGrpNameTtl);
		for(int i=1; i<=totalDDValCount-1; i++)
		{
			boolean chkd = driver.findElement(By.xpath("//table[@id='contactgroup_table']/tbody/tr["+i+"]/td/div/span/input")).isEnabled();
			Assert.assertEquals(true, chkd);					
		}		
		click(cntGrpSelectAllChkBox);
		thread();
	}

	@Test(priority=14)
	void verifyShowEntriesDropdown() throws InterruptedException, IOException
	{
		try
		{
			getObjectText(cntGrpListViewRecsInfo);
			
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
			{
				selectTextFromDropdown(cntGrpListViewLength , "25");
				thread();
				takeScreenshot();
				
				selectTextFromDropdown(cntGrpListViewLength , "10");
				thread();
			}
		}
		catch(WebDriverException e)
		{
			System.out.println("Contact Groups list view has 10 or less than 10 records. So values are not selected from show entries drop down");
		}
	}
	
	@Test(priority=15)
	void verifyViewPage()throws IOException, InterruptedException
	{
		webElement(cntGrpViewBtn);
		scrollInnerScrollBar(webelementFrame);
		click(cntGrpViewBtn);
		thread();
		takeScreenshot();
     	getObjectText(cntGrpViewPopup);
     	thread();
     	webElement(cntGrpViewCloseBtn);
		scrollInnerScrollBar(webelementFrame);
     	click(cntGrpViewCloseBtn);
     	Thread.sleep(100);
	}
	
	
	/*@Test(priority=16)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
}
