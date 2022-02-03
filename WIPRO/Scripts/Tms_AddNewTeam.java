package Scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

import java.util.Random;


import jxl.read.biff.BiffException;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import UIWrappers.Page;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.Teams.*;
import static ObjectRepository.Threats.*;

import static ObjectRepository.Locations.*;
import static ObjectRepository.Tasks.*;
import static Config.TakScreenshot.*;

/********************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Add New Team Details
 * Test 2: Verify Team Added Successfully message
 * Test 3: Verify Added Team Name in List View
 * Test 4: Verify Team Name in Relationship title
 * Test 5: Verify Team Name in Mapping pop up
 * Test 6: Do the Mapping
 * Test 7: Verify the mapped successfully message
 * Test 8: Search with Valid/Invalid Team Name
 * Test 9: Verify 'Clear' button functionality
 * Test 10: Edit the Team Name 
 * Test 11: Verify the Edited Details in List View
 * Test 12: Verify Team Successfully Updated message
 * Test 13: Verify 'Cancel' button redirection from Edit screen
 * Test 14: Verify Select All check box
 * Test 15: Verify Team List view pagination
 * Test 16: Check Show Entries drop down
 * 
*********************************************************************************************************/

public class Tms_AddNewTeam extends Page{
	
	LoginLogout ll = new LoginLogout();
	
	
	int ranTeamName;
	String teamName, modteamLeaderName, modifiedTeamType, modifiedLocation,teamType,teamLeader,location,Comments,modifiedTeamName;
	String modifiedTeamLeader;
	WebElement getAddedtmNm;
	String phTeamName = "Team Name";
	String phResponsibility = "Responsibility";
	String selectTeamLeader = "Select Team Leader";
	String selectTeamType ="Select/Add New Team Type";
			//"Select Team Type";
	String selectLocation = "Select Location";
	
   /* @Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	} */
	
	@Test(priority=1)
	void addNewTeamDtls() throws BiffException, IOException, InterruptedException
	{
		
		click(teamsInMainMenu);
		Thread.sleep(1000);
		click(tmsOvrAddBtn);
		Thread.sleep(1000);

		getTeamsSheetFromExcel();
		
		Random random = new Random();
		ranTeamName = random.nextInt(tms.getRows()-1)+1;
		
		//	Team Name
		teamName = tms.getCell(0, ranTeamName).getContents();
		enterText(tmsTeamName, teamName);
		
		//	Choose Team Leader
		click(tmsTeamLeaderArrow);
		getTotalValuesIndd(tmsTeamLeaderCnt);
		if(totalDDValCount > 1)
		{
			Random rTmLdrrandom = new Random();
			int rteamLdrName = rTmLdrrandom.nextInt(totalDDValCount-1)+1;			
			String teamLeaderName = driver.findElement(By.id("group_leader_chzn_o_"+rteamLdrName)).getText();
			enterText(tmsTeamLeaderSearchBox, teamLeaderName.substring(0, 10));
			enterKeyInKyBord(tmsTeamLeaderSearchBox);
		}
		
		
		//	Responsibility
		enterText(tmsResponsibility, tms.getCell(1, ranTeamName).getContents());
		
		//	Choose Team Type
		getTotalValuesIndd(tmsTeamTypeCnt);
		click(tmsTeamTypeArrow);
		Random team = new Random();
		int rteamType = team.nextInt(totalDDValCount-1)+1;
		String teamType = driver.findElement(By.id("team_type_chzn_o_"+rteamType)).getText();
		enterText(tmsTeamTypeSearchBox, teamType);
		enterKeyInKyBord(tmsTeamTypeSearchBox);
		
		
		//	Choose Location
		click(tmsLocationArrow);
		getTotalValuesIndd(tmsLocationCnt);
		if(totalDDValCount > 1)
		{
			Random rlocatn = new Random();
			int rlocName = rlocatn.nextInt(totalDDValCount-1)+1;			
			String location = driver.findElement(By.id("location_chzn_o_"+rlocName)).getText();
			enterText(tmsLocationSearchBox, location);
			enterKeyInKyBord(tmsLocationSearchBox);
		}	
		
		//Alternate Team Leader
		click(tmsAltrEmpArrow);
		getTotalValuesIndd(tmsAltrEmpCnt);
		if(totalDDValCount>1)
		{
			Random rAltrEmp=new Random();
			int rAlt=rAltrEmp.nextInt(totalDDValCount-1)+1;
			String altrEmpName=driver.findElement(By.xpath("//li[@id='alternate_group_leader_chzn_o_"+rAlt+"']")).getText();
			//String altrEmpName=driver.findElement(By.id("alternate_group_leader_chzn_o_"+rAlt)).getText();
			enterText(tmsAltrEmpSearchBox,altrEmpName.substring(0, altrEmpName.indexOf(",")));
			enterKeyInKyBord(tmsAltrEmpSearchBox);			
		}
		
		//	Comments
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);
		
		enterText(scEdtrCommentsField, tms.getCell(2, ranTeamName).getContents().trim());
		thread();		
		switchBackFromFrame();
		takeScreenshot();
		
		click(tmsSubmitBtn);
		thread();
		
		try
		{
		
		waitForElement(msgNotificationBar); 
		getObjectText(msgNotificationBar);
		verifyAssertEquals(teamName+" Successfully Added", getActualObjectTxt);
		takeScreenshot();
		thread();
		}
		catch(Exception e)
		{
			addNewTeamDtls();
			thread();
		}
	}		
	
	@Test(priority=2)
	void verifyAddedTeamNameInListView()
	{
		getTotalValuesIndd(tmsTeamsLstViewTtl);
		for(int tms=1; tms<2;tms++)
		{
			String getAddedtmNm = driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tr["+tms+"]/td[2]")).getText();
			if(getAddedtmNm.contains(teamName))
			{
			  for(int j=2; j<9; j++)
			 {
										
			  String ChkAddedDtls = driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tr["+tms+"]/td["+j+"]")).getText();
					 
			  //driver.manage().timeouts().implicitlyWait(78 ,TimeUnit.SECONDS);
										
			  if(j ==2)
			  verifyAssertEquals(teamName , ChkAddedDtls);
			  if(j ==3)
			  verifyAssertEquals(teamLeader, ChkAddedDtls);
			  if(j==4)
			  verifyAssertEquals(teamType,ChkAddedDtls);
			  if(j==5)
			  verifyAssertEquals(location, ChkAddedDtls)  ;
			  if(j==8)
					verifyAssertEquals(Comments, ChkAddedDtls);							
				break;
										
			}			
		   }
		}
	}
	
	 @Test(priority=3)
     void verifyTeamNameInRelationshipTitleBr() throws InterruptedException
     {
        getObjectText(selectdNmeInRltnBar);
        verifyAssertEquals(teamName, getActualObjectTxt.substring(4).trim());
        thread();

      }
	
	@Test(priority=4)
	void mapTeamToEmployees() throws InterruptedException, IOException
	{
		click(employeesTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		thread();
		
		//click to mapping the employees Names in Relationship task 
		
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
	
		//Verify task name successfully added message 
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(teamName+" successfully mapped to Employees", getActualObjectTxt);
		takeScreenshot();
		thread();	
	}
	
	@Test(priority=5)
	void searchMappedEmployees() throws IOException, InterruptedException
	{
		getTotalValuesIndd(TmsRltnEmployeesCnt);
		Random empName = new Random();
		int rempName = empName.nextInt(totalDDValCount-1)+1;
		
		//	Search Valid Employee Name
		String employeName = driver.findElement(By.xpath("//table[@id='rel_resources']//tbody/tr["+rempName+"]/td")).getText();
	    enterText(TmsRltnEmployeesSearchBox, employeName);
	    thread();
		getObjectText(TmsRltnEmployeesSrchRslt);
		verifyAssertEquals( employeName, getActualObjectTxt);
		takeScreenshot();
		clear(TmsRltnEmployeesSearchBox);
		enterKeyInKyBord(TmsRltnEmployeesSearchBox);
		
		
		//	Search Invalid Employee Name
		enterText(TmsRltnEmployeesSearchBox, "Inv Emp Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(TmsRltnEmployeesSearchBox);
		enterKeyInKyBord(TmsRltnEmployeesSearchBox);
		thread();
	}

	@Test(priority=6)
	void verifyTeamNmInTaskMappingPp() throws InterruptedException, IOException
	{
		click(tasksTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Tasks To "+teamName, getActualObjectTxt);
		
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(teamName+" successfully mapped to Tasks", getActualObjectTxt);
		takeScreenshot();
		thread();	
	}
	

	@Test(priority=7)
	void searchMappedTaskNames() throws IOException, InterruptedException
	{
		//	Search with Valid Details
		getTotalValuesIndd(TmsRltnTasksCnt);
		Random random = new Random();
		int rtskNm = random.nextInt(totalDDValCount-1)+1;
		String getTaskName = driver.findElement(By.xpath("//table[@id='rel_task']//tr["+rtskNm+"]/td")).getText();
		enterText(TmsRltnTaskSearchbox, getTaskName);
		enterKeyInKyBord(TmsRltnTaskSearchbox);
		thread();
		getObjectText(TmsRltnTaskSearchRslt);
		verifyAssertEquals(getTaskName, getActualObjectTxt);
		takeScreenshot();
		clear(TmsRltnTaskSearchbox);
		enterKeyInKyBord(TmsRltnTaskSearchbox);
		thread();
		
		//	Search with Invalid Details
		enterText(TmsRltnTaskSearchbox, "Inv Task Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(TmsRltnTaskSearchbox);
		enterKeyInKyBord(TmsRltnTaskSearchbox);
		thread();
	}
	
	@Test(priority=8)
	void verifyTeamNmInLocationsMappingPp()
	{
		click(locationsTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Locations To "+teamName, getActualObjectTxt);
	}
	
	@Test(priority=9)
	void mapTeamNameToLocations() throws InterruptedException, IOException
	{
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
	}
	
	@Test(priority=10)
	void verifyTeamSuccMappedToLocationsMsg() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(teamName+" successfully mapped to Locations", getActualObjectTxt);
		takeScreenshot();
		thread();	
	}
	
	@Test(priority=11)
	void searchMappedLocations() throws IOException, InterruptedException
	{
		getTotalValuesIndd(TmsRltnLocationsCnt);
		
		Random locName = new Random();
		int rlocName = locName.nextInt(totalDDValCount-1)+1;
		
		//	Search Valid Location Name
		String locatnName = driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody/tr["+rlocName+"]/td")).getText();
		enterText(TmsRltnLocationsSearchBox, locatnName);
		getObjectText(TmsRltnLocationsSrchRslt);
		verifyAssertEquals(locatnName, getActualObjectTxt);
		takeScreenshot();
		clear(TmsRltnLocationsSearchBox);
		enterKeyInKyBord(TmsRltnLocationsSearchBox);
		
		//	Search Invalid Location Name
		enterText(TmsRltnLocationsSearchBox, "Inv Locatn Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(TmsRltnLocationsSearchBox);
		enterKeyInKyBord(TmsRltnLocationsSearchBox);
		thread();
		thread();
	}
	
	/*@Test(priority=12)
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
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

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
        Thread.sleep(200);
        }*/
	
	
	@Test(priority=13)
	void searchWithValidInvalidTeamName() throws IOException, InterruptedException
	{
		getTotalValuesIndd(tmsTeamsLstViewTtl);
		Random random = new Random();
		int rteam = random.nextInt(totalDDValCount-1)+1;
		String teamNamesearch = driver.findElement(By.xpath("//table[@id='resourcegroup_table']//tr["+rteam+"]/td")).getText(); 
		
		//	Search Valid  Team Name
		enterText(tmsTeamsSearchBox, teamNamesearch);
		getObjectText(tmsLstViewSrchRslt);
		verifyAssertEquals(teamNamesearch, getActualObjectTxt);
		takeScreenshot();
		clear(tmsTeamsSearchBox);
		enterKeyInKyBord(tmsTeamsSearchBox);
		thread();
		//	Search Invalid Team Name
		enterText(tmsTeamsSearchBox, "Inv Team Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(tmsTeamsSearchBox);
		enterKeyInKyBord(tmsTeamsSearchBox);
		thread();	
		
	}
	
	@Test(priority=14)
	void SelectAllChkBox() throws IOException, InterruptedException
	{
		click(tmsSelectAllChkBox);
		takeScreenshot();
		
		getTotalValuesIndd(tmsTeamsLstViewTtl);
		for(int i=1; i<totalDDValCount-1; i++)
		{
			boolean chkd = driver.findElement(By.xpath("//table[@id='resourcegroup_table']/tbody/tr["+i+"]/td/div/span")).isEnabled();
			Assert.assertEquals(true, chkd);
			thread();			
		}		
		click(tmsSelectAllChkBox);
	}
	
	@Test(priority=15)
	void verifyClearButtonFunc() throws InterruptedException, IOException
	{
		getTotalValuesIndd(tmsTeamsLstViewTtl);
		Random random = new Random();
		int rrow = random.nextInt(totalDDValCount-1)+1;
		WebElement teamName = driver.findElement(By.xpath("//table[@id='resourcegroup_table']/tbody/tr["+rrow+"]/td[2]"));
		teamName.click();
		//Edit Button 
		
		driver.findElement(By.xpath("//table[@id='resourcegroup_table']/tbody/tr/td[9]/a[1]")).click();
		thread();
		
		//	Scroll down the page
		webElement(tmsClearBtn);
		scrollInnerScrollBar(webelementFrame);
		thread();
		
		// click clear button 
		click(tmsClearBtn);
		thread();
		takeScreenshot();
		//	Team Name
		getAttributePh(tmsTeamName);
		verifyAssertEquals(phTeamName, getAttribtePh);
		
		//	Team Leader
		 getObjectText(tmsTeamLeaderDefTxt);
		 verifyAssertEquals(selectTeamLeader, getActualObjectTxt);
		
		//	Responsibility
		getAttributePh(tmsResponsibility);
		verifyAssertEquals(phResponsibility, getAttribtePh);
		
		//	Team Type
		getObjectText(tmsTeamTypeDefTxt);
		verifyAssertEquals(selectTeamType, getActualObjectTxt);
		
		//	Location
		getObjectText(tmsLocationDefTxt);
		verifyAssertEquals(selectLocation, getActualObjectTxt);
		
		//	Comments
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);
		
		getObjectText(scEdtrCommentsField);
		verifyAssertEquals("", getActualObjectTxt);
		thread();		
		switchBackFromFrame();
		
		click(tmsCancelBtn);
		thread();
		verifyAssert(tmsTeamsSearchBox);
		thread();
	}
	
	@Test(priority=16)
	void teamsListViewPagination() throws IOException, InterruptedException
	{
		getTotalValuesIndd(tmsListViewPagination);
		
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
			driver.findElement(By.xpath("//div[@id='resourcegroup_table_wrapper']/div[2]/div[2]/div/ul/li["+randomPge+"]/a")).click();
			thread();
			takeScreenshot();
			click(pagiStartArw);
			thread();
		}
	}
	
	@Test(priority=17)
	void teamsShowEntries() throws IOException, InterruptedException
	{
		try
		{
			getObjectText(tmsListViewRecsInfo);
			
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
			{
				selectTextFromDropdown(tmsListViewLength, "25");
				thread();
				takeScreenshot();
				
				selectTextFromDropdown(tmsListViewLength, "10");
				thread();
			}
		}
		catch(WebDriverException e)
		{
			System.out.println("Pagination is not available in Teams List View. It contains 10 or less than 10 records");
		}
	}
	
	@Test(priority=18)
	void editTeamName() throws InterruptedException, IOException, BiffException
	{
		
		click(teamsInMainMenu);
		thread();
		//Edit button		
		click(tmsEditBtn);
		thread();
		
		//	Scroll down the page
		webElement(tmsClearBtn);
		scrollInnerScrollBar(webelementFrame);
		thread();
		
		// click clear button 
		click(tmsClearBtn);
		thread();
		
		Random random = new Random();
		ranTeamName = random.nextInt(tms.getRows()-1)+1;
		thread();		
		
		//	Team Name
		modifiedTeamName = tms.getCell(0, ranTeamName).getContents();
		enterText(tmsTeamName, modifiedTeamName);
		
		//	Choose Team Leader		
		click(tmsTeamLeaderArrow);
		getTotalValuesIndd(tmsTeamLeaderCnt);
		Random teamld = new Random();
		int rtmsln = teamld.nextInt(totalDDValCount-1)+1;
		modteamLeaderName = driver.findElement(By.id("group_leader_chzn_o_"+ rtmsln)).getText().split(",")[0];
		enterText(tmsTeamLeaderSearchBox, modteamLeaderName);
		enterKeyInKyBord(tmsTeamLeaderSearchBox);
		
		//	Responsibility
		enterText(tmsResponsibility, tms.getCell(1, ranTeamName).getContents());
		
		//	Choose Team Type
		getTotalValuesIndd(tmsTeamTypeCnt);
		click(tmsTeamTypeArrow);
		Random teamtype = new Random();
		int rtmstype = teamtype .nextInt(totalDDValCount-1)+1;
		modifiedTeamType = driver.findElement(By.id("team_type_chzn_o_"+ rtmstype)).getText().split(",")[0];
		enterText(tmsTeamTypeSearchBox, modifiedTeamType);
		enterKeyInKyBord(tmsTeamTypeSearchBox);
		
		//	Choose Location
		getTotalValuesIndd(tmsLocationCnt);
		click(tmsLocationArrow);
		modifiedLocation = driver.findElement(By.id("location_chzn_o_"+(random.nextInt(totalDDValCount-1)+1))).getText();
		enterText(tmsLocationSearchBox, modifiedLocation);
		enterKeyInKyBord(tmsLocationSearchBox);
		
		//Alternate Team Leader
		click(tmsAltrEmpArrow);
		getTotalValuesIndd(tmsAltrEmpCnt);
		if(totalDDValCount>1)
		{
			Random rAltrEmp=new Random();
			int rAlt=rAltrEmp.nextInt(totalDDValCount-1)+1;
			String altrEmpName=driver.findElement(By.xpath("//li[@id='alternate_group_leader_chzn_o_"+rAlt+"']")).getText();
			//String altrEmpName=driver.findElement(By.id("alternate_group_leader_chzn_o_"+rAlt)).getText();
			enterText(tmsAltrEmpSearchBox,altrEmpName.substring(0, altrEmpName.indexOf(",")));
			enterKeyInKyBord(tmsAltrEmpSearchBox);			
		}
		
		//	Comments
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);
		
		enterText(scEdtrCommentsField, tms.getCell(2, ranTeamName).getContents().trim());
		thread();		
		switchBackFromFrame();
		
		takeScreenshot();
		click(tmsSubmitBtn);
		thread();
		
		try
		{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(modifiedTeamName+" Successfully Updated", getActualObjectTxt);
		takeScreenshot();
		thread();	
		}
		catch(Exception e)
		{
			editTeamName();
			thread();
		}
			  
	}
	
	@Test(priority=19)
	void verifyEditedDetailsInLstView() throws InterruptedException
	{
		for(int i=1; i<2; i++)
		{
			String getModifidDtls = driver.findElement(By.xpath("//table[@id='resourcegroup_table']/tbody/tr["+i+"]/td[2]")).getText();
			
			if(getModifidDtls.contains(modifiedTeamName))
				//if(getModifidDtls.contains(teamName))
			{
				for(int j=2; j<9; j++)
				{
					String modifidDtls = driver.findElement(By.xpath("//table[@id='resourcegroup_table']/tbody/tr["+i+"]/td["+j+"]")).getText();
			   if(j == 2)
			    {
				  verifyAssertEquals(modifiedTeamName, modifidDtls);
			    }
			   else if(j == 3)
			    {
				//verifyAssertEquals(modifiedTeamLeader, modifidDtls);
				   verifyAssertEquals(modteamLeaderName,modifidDtls);
			    }
			    else if(j == 4)
			    {
				verifyAssertEquals(modifiedTeamType, modifidDtls);
			    }
			   else if(j == 5)
			   {
				verifyAssertEquals(modifiedLocation, modifidDtls);
			   }
			   
			}
		  }
	   }
	    
	}
	
	 
	 @Test(priority=20)
		void verifyViewPage()throws IOException, InterruptedException
		{
			click(tmsViewBtn);
			thread();
			takeScreenshot();
	     	//getObjectText(tmsViewPopup);
	     	thread();
	     	click(tmsViewCloseBtn);
	     	Thread.sleep(1000);
		}
	
	/*@Test(priority=21)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
}
