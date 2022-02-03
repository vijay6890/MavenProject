package Scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import jxl.read.biff.BiffException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import UIWrappers.Page;
import static UIWrappers.UIObjects.*;
import static Config.TakScreenshot.*;
import static ObjectRepository.Threats.*;

import static ObjectRepository.Locations.*;
import static ObjectRepository.Tasks.*;


/***************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Add New Threat Name
 * Test 2: Verify Threat Successfully Added Message
 * Test 3: Verify Added Threat Name in Relationship title
 * Test 4: Check the Mouse Hover text for added Threat Name Comments * 
 * Test 5: Verify the added Threat Name in List View  
 * Test 6: Verify the selected Threat Name, Type in Edit -> Threat Name, Type drop down
 * Test 7: Check the Threat Name in Edit screen - Relationship bar
 * Test 8: Verify 'Clear'button functionality in Edit page
 * Test 9: Verify 'Cancel' button redirection from 'Edit' screen
 * Test 10: Search with Valid & Invalid Threat Name in List View
 * Test 11: Verify 'Select All' check box
 * Test 12: Verify Pagination links
 * Test 13: Show Entries drop down for list view
 * Test 14: Map Locations to Threat Name 
 * Test 15: Verify Location mapped to threat name successfully message
 * Test 16: Search the mapped locations in Relationship table(Valid & Invalid)
 * Test 17: Map the Task Group to Threat Name
 * Test 18: Verify Task Group mapped to Threat Name successfully message
 * Test 19: Search the mapped Task Groups in Relationship table(Valid & Invalid) 
 * Test 20: Logout the session & close the window
 * 
****************************************************************************************************/

public class Thr_AddndDelete extends Page{
	
	LoginLogout ll = new LoginLogout();
				
	public String threatNme, ranThreatType;
	public String noMatchingRecsFound = "No matching records found";
	public WebElement getAddedThretNm;
	String srchTskGrp;
	public int t;
	
	
    /*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}*/
	
	@Test(priority=1)
	void addNewThreatName() throws InterruptedException, BiffException, IOException
	{
		getThreatsSheetFromExcel();
		thread();
				
		click(threatsInMainMenu);
		thread();
		
		click(thrOvrAddBtn);
		thread();
		//		Threat Name
		Random random = new Random();
		int randomThrNm = random.nextInt(thr.getRows());
		threatNme = thr.getCell(0, randomThrNm).getContents();
		enterText(thrThreatName, threatNme);
		thread();
		
		//		Threat Type
		click(thrThreatTypeDDArw);
		getTotalValuesIndd(thrThreatTypeDDTtlVal);
		if(totalDDValCount > 1)
		{
			Random ttype = new Random();
			int rthrType = ttype.nextInt(totalDDValCount-1)+1;
			ranThreatType = driver.findElement(By.id("threat_type_chzn_o_"+rthrType)).getText();
			enterText(thrThreatTypeSearchBox, ranThreatType); 
			enterKeyInKyBord(thrThreatTypeSearchBox);
			thread();
		}			
		
		/*try
		{
			getObjectText(thrClickToAddTxtInDD);
			if(getActualObjectTxt.contains("Click to add"))
			{
				click(thrClickToAddTxtInDD);
				waitForElement(thrAddNewThreatConfPp);
				
				//	Verify the Text of Threat Add Confirmation Pop Up
				getObjectText(thrThreatAddConfPpMsg);
				verifyAssertEquals("Do you want to add "+thr.getCell(0, randomThrNm).getContents().trim()+" into Threat Name ?", getActualObjectTxt);
				click(thrThreatAddConfOkBtn);
				
				thread();
				takeScreenshot();
				
				//	Verify Threat Name Added Successfully Message
				getObjectText(thrmsgNotificationBar);
				verifyAssertEquals(thr.getCell(0, randomThrNm).getContents().trim()+" added into Threat Name successfully", getActualObjectTxt);				
			}
		}
		catch(WebDriverException e)
		{
			//enterKeyInKyBord(thrThreatTypeSearchBox);
			takeScreenshot();
		}*/
				
		//	Enter Comments
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);
		enterText(scEdtrCommentsField, thr.getCell(1, 1).getContents().trim());
		thread();		
		switchBackFromFrame();
		
		webElement(thrSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		click(thrSubmitBtn);
		Thread.sleep(1000);
		
		//		Verify Threat Name Already Exists Message
	    getObjectText(msgNotificationBar);
	    if(getActualObjectTxt.equals(threatNme+" Successfully Added"))
	    		{
	    	
	    	      System.out.println("Perform to add the threat Name");
	    		}
	    
	    else
	    {
	    	addNewThreatName();
	    	thread();
	    	
	    }
	    
	}
	

	@Test(priority=2)
	void verifyThreatNmSuccAddedMessage() throws BiffException, IOException, InterruptedException
	{
		getObjectText(thrmsgNotificationBar);
		thread();
		verifyAssertEquals(threatNme.trim()+" Successfully Added", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=3)
	void verifyAddedThreatNmInRelatnshipTitle() throws InterruptedException
	{
		getTotalValuesIndd(thrThreatsListViewCnt);		
		for(t=1; t<=totalDDValCount; t++)
		{
			getAddedThretNm = driver.findElement(By.xpath("//table[@id='threat_table']/tbody/tr["+t+"]/td[2]"));
			if(getAddedThretNm.getText().contains(threatNme))
			{
				getAddedThretNm.click();
				
				//	Verify Selected Name In Relationship Bar
				getObjectText(selectdNmeInRltnBar);
				verifyAssertEquals(threatNme, getActualObjectTxt.substring(4).trim());
				break;
			}			
		}
		thread();
	}
	
	@Test(priority=4)
	void verifyAddedThreatNamendMouseHovrInListView() throws BiffException, IOException, InterruptedException
	{
		getTotalValuesIndd(thrThreatsListViewCnt);
		for(t=1; t<=totalDDValCount; t++)
		{
			getAddedThretNm = driver.findElement(By.xpath("//table[@id='threat_table']/tbody/tr["+t+"]/td[2]"));
			if(getAddedThretNm.getText().contains(threatNme))
			{
				//	Mouse Hover to Comments
				Actions action = new Actions(driver);
				action.moveToElement(driver.findElement(By.xpath("//table[@id='threat_table']/tbody/tr["+t+"]/td[4]"))).build().perform();
				thread();
						
				takeScreenshot();
				thread();
				break;
			}			
		}
		thread();
	}			
		
	
	@Test(priority=5)
	void vfySelectedThreatNmDtlsInEditScreen() throws BiffException, IOException, InterruptedException
	{
		getTotalValuesIndd(thrThreatsListViewCnt);
		for(t=1; t<=totalDDValCount; t++)
		{
			getAddedThretNm = driver.findElement(By.xpath("//table[@id='threat_table']/tbody/tr["+t+"]/td[2]"));
			if(getAddedThretNm.getText().contains(threatNme))
			{
				//	Click 'Edit' button
				driver.findElement(By.xpath("//table[@id='threat_table']/tbody/tr["+t+"]/td[5]/a")).click();
				thread();
					
				//	Verify Selected Threat Name
				getAttributeVal(thrThreatName);
				verifyAssertEquals(threatNme, getAttribteVal);
					
				//	Verify Selected Threat Type
				getObjectText(thrSelectdThrTypeInEdt);
				verifyAssertEquals(ranThreatType, getActualObjectTxt);
				takeScreenshot();
				
				webElement(thrCancelBtn);
				scrollInnerScrollBar(webelementFrame);
				click(thrCancelBtn);
				thread();		
				verifyAssert(thrThreatsSearchBox);	
				thread();
			}
		}				
	}
	
	@Test(priority=6)
	void verifySelectdThrNmInEdtPgeRltnTitle() throws InterruptedException
	{
		getObjectText(selectdNmeInRltnBar);
		verifyAssertEquals(threatNme.trim(), getActualObjectTxt.substring(4).trim());
		thread();
	}
	
	
	@Test(priority=7)
	void mapThreatToLocations() throws InterruptedException, IOException
	{
      verifyAddedThreatNmInRelatnshipTitle();
      thread();
		
		//	Verify No Records Found Message in List View Before Mapping
		getObjectText(rltnLocatnTbEmpty);
		verifyAssertEquals("No Records Found", getActualObjectTxt);
		
		
		click(rltnAddRemoveRltnsBtn);
		thread();
		verifyAssert(rltnMapLocationsPp);
		thread();
		
		getObjectText(rltnThreatNmInMapPp);
		verifyAssertEquals("Map Locations To "+threatNme.trim(), getActualObjectTxt);
		
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();		
		
		//	Verify Mapped successfully message
		getObjectText(msgNotificationBar);
		verifyAssertEquals(threatNme.trim()+" successfully mapped to Locations", getActualObjectTxt);
		takeScreenshot();
		thread();
		
	}
	
	@Test(priority=8)
	void searchMappedLocations() throws IOException, InterruptedException
	{
		getTotalValuesIndd(thrTtlLocatnsInRltnTbl);
		Random random = new Random();
		int loc = random.nextInt(totalDDValCount-1)+1;
		String searchdLoc = driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody/tr["+loc+"]/td")).getText();
		enterText(thrRltnLocatnSearchBox, searchdLoc.substring(4));
		
		//	Verify Search Result
		getObjectText(thrLocatnRltnSrchRslt);
		verifyAssertEquals(searchdLoc, getActualObjectTxt);
		takeScreenshot();
		clear(thrRltnLocatnSearchBox);
		takeScreenshot();
		enterKeyInKyBord(thrRltnLocatnSearchBox);
		thread();		
		
		//	Search Invalid Location Name
		enterText(thrRltnLocatnSearchBox, "Invalid Location Name");
		getObjectText(thrLocatnRltnSrchRslt);
		verifyAssertEquals(noMatchingRecsFound, getActualObjectTxt);
		takeScreenshot();
		clear(thrRltnLocatnSearchBox);
		enterKeyInKyBord(thrRltnLocatnSearchBox);
		takeScreenshot();
		thread();
	}
	
	
	@Test(priority=9)
	void mapTasktoTaskGroups() throws InterruptedException, IOException
	{
		click(taskGroupsTb);
		getObjectText(rltnTskGrpTbEmpty);
		verifyAssertEquals("No Records Found", getActualObjectTxt);
		thread();
		try
		{
		
		click(rltnAddRemoveRltnsBtn);
		
		waitForElement(rltnMapLocationsPp);
		verifyAssert(rltnMapLocationsPp);
		thread();
		
		getObjectText(rltnThreatNmInMapPp);
		verifyAssertEquals("Map Task Groups To "+threatNme.trim(), getActualObjectTxt);
		
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(threatNme.trim()+" successfully mapped to Task Groups", getActualObjectTxt);
		takeScreenshot();
		thread();
		
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	@Test(priority=10)
	void searchMappedTaskGroups()throws InterruptedException, IOException
	{
		//		Search with Valid Details
		
		try
		{
		getTotalValuesIndd(thrTtlTskGrpsInRltnTbl);
		if(totalDDValCount==0)
		{
			System.out.println("No records to map");
		}
		else if (totalDDValCount==1)
		{
			 srchTskGrp = driver.findElement(By.xpath("//table[@id='rel_task_grp']//tbody/tr[1]/td")).getText();
		}
		else
		{
		Random random = new Random();
		int tskgrp = random.nextInt(totalDDValCount-1)+1;
		 srchTskGrp = driver.findElement(By.xpath("//table[@id='rel_task_grp']//tbody/tr["+tskgrp+"]/td")).getText();
		}
		enterText(thrRltnTskGrpsSearchBox, srchTskGrp.substring(4));
		
		getObjectText(thrTskGrpsRltnSrchRslt);
		verifyAssertEquals(srchTskGrp, getActualObjectTxt);
		takeScreenshot();
		clear(thrRltnTskGrpsSearchBox);
		takeScreenshot();
		enterKeyInKyBord(thrRltnTskGrpsSearchBox);
		thread();
		
		//	Search with Invalid Details
		enterText(thrRltnTskGrpsSearchBox, "Invalid Task Group");
		getObjectText(thrTskGrpsRltnSrchRslt);
		verifyAssertEquals(noMatchingRecsFound, getActualObjectTxt);
		takeScreenshot();
		clear(thrRltnTskGrpsSearchBox);
		takeScreenshot();
		enterKeyInKyBord(thrRltnTskGrpsSearchBox);
		thread();
		}
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	/* @Test(priority=11)
	    void Documentupload() throws IOException, InterruptedException, AWTException
	    {
	    	
	        click(docuemntTb);
	    	thread();
	    	//Scroll down the page
			webElement(choosefiles);
			scrollInnerScrollBar(webelementFrame);
			
		    //click choosefiles button 
			click(choosefiles);
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
	    
	@Test(priority=12)
	void verifyClearCancelBtnFuncInEdtScreen() throws IOException, InterruptedException
	{ 
		
		click(thrEditBtn);
		thread();
		webElement(thrClearBtn);
		scrollInnerScrollBar(webelementFrame);
		thread();
		
		click(thrClearBtn);
		takeScreenshot();
				
		//	Verify Threat Name
		getAttributePh(thrThreatName); 
		verifyAssertEquals("Threat Name", getAttribtePh);
		
		//	Verify Threat Type
		getObjectText(thrSelectdThrTypeInEdt);
		verifyAssertEquals("Select/Add New Threat Type", getActualObjectTxt);
		
		//	Comments
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);		
		getObjectText(scEdtrCommentsField);
		verifyAssertEquals("", getActualObjectTxt);
		switchBackFromFrame();
		thread();
		
		click(thrCancelBtn);
		thread();		
		verifyAssert(thrThreatsSearchBox);	
	}
	
	@Test(priority=13)
	void searchWithValidThreatName() throws BiffException, IOException, InterruptedException
	{
		enterText(thrThreatsSearchBox, threatNme);
		takeScreenshot();
		
		getObjectText(thrSrchRslt);
		verifyAssertEquals(threatNme.trim(), getActualObjectTxt);
	
		clear(threatsSearchBox);
		enterKeyInKyBord(threatsSearchBox);
		thread();
	}
	
	
	@Test(priority=14)
	void searchWithInvalidThreatName() throws BiffException, IOException, InterruptedException
	{
		enterText(thrThreatsSearchBox, "Invalid Threat Name");
		takeScreenshot();
		
		getObjectText(thrNoMatchngRecsFoundMsg);
		verifyAssertEquals(noMatchingRecsFound, getActualObjectTxt);
		clear(threatsSearchBox);
		enterKeyInKyBord(threatsSearchBox);
		thread();
	}
	
	@Test(priority=15)
	void verifySelectAllCheckBoxFunc() throws InterruptedException, IOException
	{
		click(thrSelectAllChkBox);
		thread();
		takeScreenshot();
		
		getTotalValuesIndd(thrThreatsListViewCnt);
		for(int i=1; i<=totalDDValCount; i++)
		{
			boolean chkd = driver.findElement(By.xpath("//table[@id='threat_table']/tbody/tr["+i+"]/td/div/span")).isEnabled();
			Assert.assertEquals(true, chkd);			
		}		
		click(thrSelectAllChkBox);
	}
	
    @Test(priority=16)
	void verifyViewPage()throws IOException, InterruptedException
	{
		click(thrViewBtn);
		takeScreenshot();
     	getObjectText(thrViewPopup);
     	thread();
     	click(thrViewCloseBtn);
     	thread();
	}
    
    @Test(priority=17)
	void pagination() throws IOException, InterruptedException
	{
		getTotalValuesIndd(listViewPagination);
				
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
			
			Random randomPagi = new Random();
			int randomPge = randomPagi.nextInt((totalDDValCount-2)-4)+4;
			driver.findElement(By.xpath("//div[3]/div[1]/div[2]/div/div/div[1]/div[2]/div[1]/div/div/div/div[2]/div[2]/div/ul/li["+randomPge+"]/a")).click();
			thread();
			takeScreenshot();
			click(pagiStartArw);
			thread();
		}		
	}
	
	@Test(priority=18)
	void verifyShowEntriesDropdown() throws IOException, InterruptedException
	{
		try
		{
			getObjectText(thrListViewRecsInfo);
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
			{
				selectTextFromDropdown(thrListViwLength , "25");
				thread();
				takeScreenshot();
				
				selectTextFromDropdown(thrListViwLength , "10");
				thread();
			}
		}
		catch(WebDriverException e)
		{
			System.out.println("Threats list view has 10 or less than 10 records. So values are not selected from show entries drop down");
		}thread();
	}
	
	
	/*@Test(priority=19)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
}


