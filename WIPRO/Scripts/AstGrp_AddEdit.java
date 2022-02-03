package Scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static UIWrappers.UIObjects.*;
import static ObjectRepository.AssetGroups.*;

import static ObjectRepository.Locations.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Threats.*;
import static Config.TakScreenshot.*;


/****************************************************************************************************************************
 *
 *@author Sellamuthu
 *
 * Test 1: Add Asset Groups
 * Test 2: Verify Asset Group Added Successfully message
 * Test 3: Verify the added Asset Group name in List View
 * Test 4: Verify the Mouse Hover Comments of Asset Group
 * Test 5: Check the Asset Group Name in Mapping pop up
 * Test 6: Do the Assets & Tasks mapping to Asset Groups
 * Test 7: Verify Asset & Task successfully mapped message
 * Test 8: Verify the Asset Group Name in Relationship title
 * Test 9: Search with Valid/Invalid Asset Group Name
 * Test 10: Check 'Select All' check box functionality
 * Test 11: Verify 'Clear' button
 * Test 12: Edit Asset Group Name
 * Test 13: Verify Asset Group Name updated successfully message
 * Test 14: Verify Updated details in List View
 * Test 15: Check the Pagination
 * Test 16: Show Entries drop down
 * 
*****************************************************************************************************************************/

public class AstGrp_AddEdit extends Page{
	
	LoginLogout ll =new LoginLogout();
	
	String assetGroupName, assetGroupId, assetGroupComments;
	String modifiedAssetGroupName, modifiedAssetGroupId, modifiedAssetGroupComments;
	String phAssetGroupName = "Group Name";
	String phAssetGroupId = "Asset Group ID";
	
	/*@Test(priority=0)

	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();

	}*/

	@Test(priority=1)
	void verifyAddAssetGroupPage() throws InterruptedException
	{
		Thread.sleep(1000);
		click(assetGroupsInMainMenu);
		waitForPageLoad();
		Thread.sleep(1000);
		click(astGrpOvrAddBtn);
		Thread.sleep(1000);
	}
	
	
	
	@Test(priority=2)
	void addAssetGroup() throws BiffException, IOException, InterruptedException
	{
		getAssetGroupsSheetFromExcel();	
		
		ArrayList<Integer> agrplist = new ArrayList<Integer>();
		
		for(int i=1; i<astGrp.getRows(); i++)
		{
			agrplist.add(i);
		}
		
		Collections.shuffle(agrplist);
		
		for(int j=0; j<1; j++)
		{
			//	Group Name
			assetGroupName = astGrp.getCell(0, agrplist.get(j)).getContents();
			enterText(astGrpGroupName, assetGroupName.trim());
			
			//	Asset Group ID
			assetGroupId = astGrp.getCell(1, agrplist.get(j)).getContents();
			enterText(astGrpAssetGroupId, assetGroupId.trim());
			
			//	Comments
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);
			
			assetGroupComments = astGrp.getCell(2, agrplist.get(j)).getContents().trim();
			enterText(scEdtrCommentsField, assetGroupComments.trim());
			thread();		
			switchBackFromFrame();
			
			takeScreenshot();
			webElement(astGrpSubmitBtn);
			scrollInnerScrollBar(webelementFrame);
			click(astGrpSubmitBtn);
			thread();
		}		
	}
	
	
	@Test(priority=3)
	void verifyAssetGrpSuccAddedMsg() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(assetGroupName.trim()+" Successfully Added", getActualObjectTxt);
		thread();
	}
	
	@Test(priority=4)
	void verifyAddedAssetGrpNameInListView()
	{
		getTotalValuesIndd(astGrpAssetGrpListViewTtl);
		for(int i=1; i<totalDDValCount; i++)
		{
			String vfyAddedAstGrpNm = driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr["+i+"]/td[2]")).getText();
			
			if(vfyAddedAstGrpNm.contains(assetGroupName))
			{
				for(int j=2; j<5; j++)
				{
					String addedAssetGrpDtls = driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr["+i+"]/td["+j+"]")).getText();;
					
					if(j == 2)
						verifyAssertEquals(assetGroupName, addedAssetGrpDtls);	//	Group Name
					else if(j == 3)
						verifyAssertEquals(assetGroupId, addedAssetGrpDtls);	//	Asset Group Id
					else if(j == 4)
						verifyAssertEquals(assetGroupComments, addedAssetGrpDtls);		//	Comments										
				}
			}			
		}
	}
	
	@Test(priority=5)
	void CheckAssetGrpCommentsMouseHover() throws IOException, InterruptedException
	{
		getTotalValuesIndd(astGrpAssetGrpListViewTtl);
		
		for(int i=1; i<totalDDValCount; i++)
		{
			if(driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr["+i+"]/td[2]")).getText().contains(assetGroupName))
			{
				WebElement coments = driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr["+i+"]/td[4]"));
				mouseHoverOn(coments);
				thread();
				takeScreenshot();
			}
		}
	}
	
	@Test(priority=6)
	void verifyAssetGrpNameInMapAssetsPp() throws InterruptedException
	{
		click(rltnAddRemoveRltnsBtn);
		thread();
		try
		{
		verifyAssert(mapMappingPopup);
		thread();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Assets To "+assetGroupName.trim(), getActualObjectTxt);		
	}
	
	@Test(priority=7)
	void mapAssetsToAssetGroups() throws InterruptedException, IOException
	{
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
	}
	
	@Test(priority=8)
	void verifyAssetSuccMapedToAsetGrpMsg() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(assetGroupName.trim()+" successfully mapped to Assets", getActualObjectTxt);
		thread();
	}
	
	@Test(priority=9)
    void searchMappedAssets() throws IOException, InterruptedException
	{
		getTotalValuesIndd(AstGrpRltnAssetsCnt);
		
		Random astName = new Random();
		int rastName = astName.nextInt(totalDDValCount-1)+1;
		thread();
		
		//	Search Valid Asset Name
		String assetName = driver.findElement(By.xpath("//table[@id='rel_assets']/tbody/tr["+rastName+"]/td")).getText();
		enterText(AstGrpRltnAssetsSearchBox, assetName);
		getObjectText(AstGrpRltnAssetsSrchRslt);
		verifyAssertEquals(assetName, getActualObjectTxt);
		takeScreenshot();
		clear(AstGrpRltnAssetsSearchBox);
		enterKeyInKyBord(AstGrpRltnAssetsSearchBox);
		
		//	Search Invalid Asset Name
		enterText(AstGrpRltnAssetsSearchBox, "Inv Asset Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(AstGrpRltnAssetsSearchBox);
		enterKeyInKyBord(AstGrpRltnAssetsSearchBox);
		thread();
	}
	
	@Test(priority=10)
	void verifyAssetGrpNameInMapTaskPp()
	{
		click(tasksTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Tasks To "+assetGroupName.trim(), getActualObjectTxt);
	}
	
	@Test(priority=11)
	void mapTaskToAssetGroups() throws InterruptedException, IOException
	{
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
	}
	
	@Test(priority=12)
	void verifyTaskMapedToAsetGrpSuccMsg() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(assetGroupName.trim()+" successfully mapped to Tasks", getActualObjectTxt);
		thread();
	}
	
	@Test(priority=13)
	void searchMappedTasks() throws IOException, InterruptedException
	{
		getTotalValuesIndd(AstGrpRltnTasksCnt);
		
		//	Search Valid Task Group Name
		Random tskGrpNm = new Random();
		int rtskGrpNm = tskGrpNm.nextInt(totalDDValCount-1)+1;		
		
		String taskGroupNm = driver.findElement(By.xpath("//table[@id='rel_task']//tr["+rtskGrpNm+"]/td")).getText().substring(4);
		enterText(AstGrpRltnTasksSearchBox, taskGroupNm);
		getObjectText(AstGrpRltnTasksSrchRslt);
		verifyAssertEquals(taskGroupNm, getActualObjectTxt.substring(4));
		takeScreenshot();
		clear(AstGrpRltnTasksSearchBox);
		enterKeyInKyBord(AstGrpRltnTasksSearchBox);
		thread();
		
		//	Search Invalid Task Group Name
		enterText(AstGrpRltnTasksSearchBox, "Inv Tsk Grp Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(AstGrpRltnTasksSearchBox);
		enterKeyInKyBord(AstGrpRltnTasksSearchBox);
		thread();
	}	
	
	@Test(priority=14)
	void verifyAssetGrpNameInRelationshipTitle()
	{
		getTotalValuesIndd(astGrpAssetGrpListViewTtl);
		
		Random random = new Random();
		int rastgrp = random.nextInt(totalDDValCount-1)+1;
		
		WebElement asetName = driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr["+rastgrp+"]/td[2]"));
		String getAsetNmForRltnTitle = asetName.getText();
		asetName.click();
		
		getObjectText(astGrpRelationshipBarTitle);
		verifyAssertEquals(getAsetNmForRltnTitle, getActualObjectTxt.substring(4).trim());
	}
	
	@Test(priority=15)
	void searchValidAssetGrpName() throws IOException, InterruptedException
	{
		getTotalValuesIndd(astGrpAssetGrpListViewTtl);
		Random search = new Random();
		int rsr = search.nextInt(totalDDValCount-1)+1;
		
		String astGrpNameFrSearch = driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr["+rsr+"]/td[2]")).getText();
		enterText(astGrpAssetGroupsSearchBox, astGrpNameFrSearch);
		takeScreenshot();
		
		getObjectText(astGrpLstViewFstName);
		verifyAssertEquals(astGrpNameFrSearch, getActualObjectTxt);
		clear(astGrpAssetGroupsSearchBox);
		enterKeyInKyBord(astGrpAssetGroupsSearchBox);
		thread();
	}
	
	@Test(priority=16)
	void searchInvalidAssetGrpName() throws IOException, InterruptedException
	{
		enterText(astGrpAssetGroupsSearchBox, "Invalid Asset Grp");
		takeScreenshot();
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		clear(astGrpAssetGroupsSearchBox);
		enterKeyInKyBord(astGrpAssetGroupsSearchBox);
		thread();		
	}
	
	@Test(priority=17)
	void verifySelectAllCheckBox() throws IOException, InterruptedException
	{
		click(astGrpSelectAllChkBox);
		takeScreenshot();
		
		getTotalValuesIndd(astGrpAssetGrpListViewTtl);
		for(int i=1; i<=totalDDValCount; i++)
		{
			boolean chkd = driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr["+i+"]/td/div/span/input")).isEnabled();
			Assert.assertEquals(true, chkd);
		}		
		click(astGrpSelectAllChkBox);
	}
	
	@Test(priority=18)
	void verifyClearBtn() throws InterruptedException, IOException
	{
		getTotalValuesIndd(astGrpAssetGrpListViewTtl);
		
	//	Random agrpFrEdit = new Random();
	//	int redt = agrpFrEdit.nextInt(totalDDValCount-1)+1;
		
	//	WebElement nameForEdit = driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr["+redt+"]/td[2]"));
		WebElement nameForEdit = driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr[1]/td[2]"));
		nameForEdit.click();
		
		//driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr["+redt+"]/td[5]/a")).click();
		driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr[1]/td[5]/a")).click();
		thread();
		
		webElement(astGrpClearBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(astGrpClearBtn);
		thread();
		takeScreenshot();
		thread();
		
		//	Group Name
		getAttributePh(astGrpGroupName);
		verifyAssertEquals(phAssetGroupName, getAttribtePh);
		
		//	Asset Group Id
		getAttributePh(astGrpAssetGroupId);
		verifyAssertEquals(phAssetGroupId, getAttribtePh);	
		
		//	Comments
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);		
		getObjectText(scEdtrCommentsField);
		verifyAssertEquals("", getActualObjectTxt);
		switchBackFromFrame();
		thread();		
	}
	
	@Test(priority=19)
	void editAssetGroupName() throws BiffException, IOException, InterruptedException
	{
		getAssetGroupsSheetFromExcel();
		
		ArrayList<Integer> agrplist = new ArrayList<Integer>();
		
		for(int i=1; i<astGrp.getRows(); i++)
		{
			agrplist.add(i);
		}
		
		Collections.shuffle(agrplist);
		
		for(int j=0; j<1; j++)
		{
			//	Group Name
			modifiedAssetGroupName = astGrp.getCell(0, agrplist.get(j)).getContents();
			enterText(astGrpGroupName, modifiedAssetGroupName);
			
			//	Asset Group ID
			modifiedAssetGroupId = astGrp.getCell(1, agrplist.get(j)).getContents();
			enterText(astGrpAssetGroupId, modifiedAssetGroupId);
			
			//	Comments
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);
			
			modifiedAssetGroupComments = astGrp.getCell(2, agrplist.get(j)).getContents().trim();
			enterText(scEdtrCommentsField, modifiedAssetGroupComments);
			thread();		
			switchBackFromFrame();
			
			takeScreenshot();
			click(astGrpSubmitBtn);
			thread();
		}				
	}
	
	@Test(priority=20)
	void verifyAssetGrpNameUpdatedSuccMsg() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(modifiedAssetGroupName.trim()+" Successfully Updated", getActualObjectTxt);
		thread();
	}
	
	@Test(priority=21)
	void verifyUpdatedDetailsInListView()
	{
		getTotalValuesIndd(astGrpAssetGrpListViewTtl);
		for(int i=1; i<totalDDValCount; i++)
		{
			String vfyModifiedAstGrpNm = driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr["+i+"]/td[2]")).getText();
			
			if(vfyModifiedAstGrpNm.contains(modifiedAssetGroupName))
			{
				for(int j=2; j<5; j++)
				{
					String modiAssetGrpDtls = driver.findElement(By.xpath("//table[@id='asset_group_table']/tbody/tr["+i+"]/td["+j+"]")).getText();;
					
					if(j == 2)
						verifyAssertEquals(modifiedAssetGroupName, modiAssetGrpDtls);	//	Group Name
					else if(j == 3)
						verifyAssertEquals(modifiedAssetGroupId, modiAssetGrpDtls);	//	Asset Group Id
					else if(j == 4)
						verifyAssertEquals(modifiedAssetGroupComments, modiAssetGrpDtls);	//	Comments					
				}
			}			
		}		
	}
	
	@Test(priority=22)
	void verifyAssetGrpPagination()
	{
		getTotalValuesIndd(astGrpListViewPagination);
		
		try
		{
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
				int randomPge = randomPagi.nextInt((totalDDValCount)-4)+4;
				driver.findElement(By.xpath("//div[@id='asset_group_table_wrapper']//div[@class='dataTables_paginate paging_bootstrap pagination pagination-right']/ul/li["+randomPge+"]/a")).click();
				thread();
				takeScreenshot();
				click(pagiStartArw);
				thread();
			}	
			else
			{
				System.out.println("***** Pagination is not available in Asset Groups List View. Table has 10 or less than 10 records *****");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	@Test(priority=23)
	void assetGrpShowEntriesDrpDown() throws IOException, InterruptedException
	{
		try
		{
			getObjectText(astGrpListViewRecsInfo);
			
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
			{
				selectTextFromDropdown(astGrpListViewLength , "25");
				thread();
				takeScreenshot();
				
				selectTextFromDropdown(astGrpListViewLength , "10");
				thread();
			}
		}
		catch(WebDriverException e)
		{
			System.out.println("Asset Groups list view has 10 or less than 10 records. So values are not selected from show entries drop down");
		}thread();
	}
	
	@Test(priority=24)
	void verifyViewpage() throws IOException, InterruptedException
	{
		webElement(astGrpViewBtn);
		scrollInnerScrollBar(webelementFrame);
		click(astGrpViewBtn);
		thread();
		takeScreenshot();
		thread();
     	//getObjectText(astGrpViewPopup);
     	//thread();
		webElement(astGrpViewCloseBtn);
		scrollInnerScrollBar(webelementFrame);
     	click(astGrpViewCloseBtn);
     	thread();
	}
	
	/*@Test(priority=25)
    void Documentupload() throws IOException, InterruptedException, AWTException
    {
		webElement(docuemntTb);
		scrollInnerScrollBar(webelementFrame);
    	
       click(docuemntTb);
		//click(By.xpath("//li[@id='documents']"));
    	thread();
    	//Scroll down the page
		webElement(choosefiles);
		scrollInnerScrollBar(webelementFrame);
		
	    //click choosefiles button 
		click(choosefiles);
		//click(By.xpath("//input[@id='doc_files']"));
		thread();
		takeScreenshot();
    	
       //put path to your image in a clipboard
        StringSelection ss = new StringSelection("D:\\SIB\\Logo\\FatPipe_logo.jpg");
	        thread();
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        thread();
        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        // click to upload the files 
         click(docUpload);
         thread();
         thread();
        
      }*/
        
	
	/*@Test(priority=26)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/

}
