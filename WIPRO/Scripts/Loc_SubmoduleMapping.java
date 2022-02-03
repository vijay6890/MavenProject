package Scripts;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;
import model.TaskDetails;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import UIWrappers.Page;
import static UIWrappers.UIObjects.*;
import static Config.TakScreenshot.*;
import static ObjectRepository.Contacts.*;
import static ObjectRepository.Locations.*;

import static ObjectRepository.Loc_SubModule_Mapping.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Threats.*;
public class Loc_SubmoduleMapping extends Page {
	
	LoginLogout ll = new LoginLogout();
	Loc_AddDeleteLocation lem=new Loc_AddDeleteLocation();
	
/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}*/
	
	@Test(priority=1)
	void mapTeamsToEmployee() throws InterruptedException, IOException
	{
		click(locationsInMainMenu);
		thread();
		waitForPageLoad();
		click(employeesTb);
		
		
		
		getTotalValuesIndd(locEmpTotalCount);
		if(totalDDValCount==1)
		{
			System.out.println("if");
			//try
		//	{
		//	verifyAssert(locEmpExist);
		//	}
		//	catch(Exception e)
		//	{
				
				click(rltnAddRemoveRltnsBtn);
				lem.mapEmployeesToLocation();
		//	}
			
		}
		
		webElement(emp_View);
		scrollInnerScrollBar(webelementFrame);
		
		click(emp_View);
		thread();
		click(subTeams);
		thread();
		webElement(subAddRlnRemoveBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(subAddRlnRemoveBtn);
		for(int i=1;i<=2;i++)
		{
	   
		//getTotalValuesIndd(subMapLftTtlVal);
	    getTotalValuesIndd(locEmpTeamsTtlCnt);
		 Random random=new Random();
		 int ran = random.nextInt(totalDDValCount-1)+1;
		 String NameForSearch=driver.findElement(By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/div[1]/select/option["+ran+"]")).getText();
        System.out.println("team Name "+NameForSearch);
		 
		 // click(subMapRemoveAllArrow);
		//	Enter Name for Search
			enterText(subLftSearchBox, NameForSearch);
			getObjectText(subMapPopupFirstNameInLftBox);
			verifyAssertEquals(NameForSearch, getActualObjectTxt);
          click(subMapMoveAllArrow);
			
           clear(subLftSearchBox);
			thread();
		//	Search Mapped Name
			enterText(subRgtSearchBox, NameForSearch);
			getObjectText(submapPopupFirstNameInRgtBox);
			//	Verify Mapped Name
			verifyAssertEquals(NameForSearch, getActualObjectTxt);
			clear(subRgtSearchBox);
	    
		takeScreenshot();
		thread();
		}	
		webElement(locEmpTmsSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		click(locEmpTmsSubmitBtn);
		thread();
	//	}
			
	}
	
/*	@Test(priority=2)
	void searchTeams() throws IOException, InterruptedException
	{
		 getTotalValuesIndd(subTeamsTblTtl);
		if(totalDDValCount>0)
		 {
		 Random random = new Random();
		 int ranNm = random.nextInt(totalDDValCount-1)+1;
		 String rsubteamForSearch = driver.findElement(By.xpath("//table[@id='resourcegroup_nestable_33b176b1-4213-1bb5-b927-57ebb00d6fcc']/tbody/tr["+ranNm+"]/td")).getText();
		//	Search Valid Task Group Name
		enterText(subTeamSearchBox, rsubteamForSearch);
		getObjectText(subTeamSearchRslt);
		verifyAssertEquals(rsubteamForSearch, getActualObjectTxt);
		takeScreenshot();
		clear(subTeamSearchBox);
		enterKeyInKyBord(subTeamSearchBox);
		thread();
		
		//	Search Invalid Task Group Names
		enterText(subTeamSearchBox, "Inv sub Team Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(subTeamSearchBox);
		enterKeyInKyBord(subTeamSearchBox);
		thread();
	   }
		 
			   
    }*/
	
	 @Test(priority=3)
		void mapInsuranceToEmployee() throws InterruptedException, IOException
		{
			click(subInsurance);
			for(int i=0; i<2; i++)
			{
			driver.findElement(By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[1]/div[2]/a")).click();
			
			// getTotalValuesIndd(subMapInsuLftTtlVal);
				getTotalValuesIndd(locEmpInsTtlCnt);
			 Random random=new Random();
			 int ran = random.nextInt(totalDDValCount-1)+1;
			 String NameForSearch=driver.findElement(By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/select/option["+ran+"]")).getText();
             System.out.println("Insurance name "+NameForSearch); 
			 
			//	Enter Name for Search
				enterText(subLftInsuSearchBox, NameForSearch);
				getObjectText(locEmpInsLftFstName);
				verifyAssertEquals(NameForSearch, getActualObjectTxt);
	           // click(subMapMoveAllArrow);
				click(locEmpInsLftMoveAll);
				
	            clear(subLftInsuSearchBox);
				thread();
			//	Search Mapped Name
				enterText(subRgtInsuSearchBox, NameForSearch);
				getObjectText(locEmpInsRgtFstName);
				//	Verify Mapped Name
				verifyAssertEquals(NameForSearch, getActualObjectTxt);
				clear(subRgtInsuSearchBox);
				
				
				webElement(locEmpInsSubmitBtn);
				scrollInnerScrollBar(webelementFrame);
				click(locEmpInsSubmitBtn);
				thread();

			}
		}
			
		
	 
	 
	 
/*	 @Test(priority=4)
     void mapTasksToEmployee() throws InterruptedException, IOException
     {
    	 
    	click(subTasks);
   		click(subAddRlnRemoveBtn);
   		thread();
   		
   	
   		takeScreenshot();
   		click(subSubmit);
   		thread();
    	 
    	 
     }
	
	///     pagination    ///
	
	  /*	@Test(priority=2)
   	void verifyShowEntriesDropDown() throws InterruptedException, IOException
   	{
   		try
   		{
   			getObjectText(teamsRecInfo);
   			
   			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)

   			{
   				selectTextFromDropdown(teamsShowEntLength , "25");
   				thread();
   				takeScreenshot();
   				
   				selectTextFromDropdown(teamsShowEntLength , "10");
   				thread();
   			}
   		}
   		catch(WebDriverException e)
   		{
   			System.out.println("Pagination is not available in Incident List View. It contains 10 or less than 10 records");
   		}
   	}
	
	//To view the pagination 
  @Test(priority=3)
  	void subTeamNameListViewPagination() throws IOException, InterruptedException
	     {
		   getTotalValuesIndd(sub_TeamsListViewPagination);
		
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
			int randomPge = randomPagi.nextInt((totalDDValCount-2)-4)+4;
			driver.findElement(By.xpath("//div[@id='incident_table_wrapper']/div[2]/div[2]/div/ul/li["+randomPge+"]/a")).click();
			takeScreenshot();
			click(pagiStartArw);
			thread();
		}		
	     }*/
	
/*	@Test(priority=2)
	void locationNavigation() throws InterruptedException
	{
		click(locationsInMainMenu);
		thread();
		getTotalValuesIndd(locLocationsLstViewTtl);
		Random rand=new Random();
		int rl=rand.nextInt(totalDDValCount);
		if(rl==0)
		{
			rl=rl+1;
		}
		
		driver.findElement(By.xpath("//table[@id='facilities_table']//tbody/tr["+rl+"]")).click();
		thread();
		
		webElement(rltnAddRemoveRltnsBtn);
		scrollInnerScrollBar(webelementFrame);
		click(rltnAddRemoveRltnsBtn);
		thread();
		click(mapRemoveAllArrow);
		thread();
		
		getTotalValuesIndd(mapPopupLftTtlVal);
		Random random = new Random();
		int ranNm = random.nextInt(totalDDValCount-1)+1;
		String NameForSearch = driver.findElement(By.xpath("//select[@id='bootstrap-duallistbox-nonselected-list_map_list[]']/option["+ranNm+"]")).getText();
					
		//	Enter Name for Search
		enterText(mapPopupLftSearchBox, NameForSearch);
		getObjectText(mapPopupFirstNameInLftBox);
		//	Verify Search Result In List
		verifyAssertEquals(NameForSearch, getActualObjectTxt);
		click(mapMoveAllArrow);
		
		clear(mapPopupLftSearchBox);
		thread();
		
		//	Search Mapped Name
		enterText(mapPopupRgtSearchBox, NameForSearch);
		getObjectText(mapPopupFirstNameInRgtBox);
		//	Verify Mapped Name
		verifyAssertEquals(NameForSearch, getActualObjectTxt);
		clear(mapPopupRgtSearchBox);
		thread();
		
		click(mapSubmitBtn);
		thread();
		
		try
		{
			click(locEmpDiffLocatnPpOkBtn);
			thread();
		}
		catch(NoSuchElementException nos)
		{
			nos.printStackTrace();
			System.out.println("All Employees From Same Location"); 
		}
		thread();
		
		webElement(emp_View);
		scrollInnerScrollBar(webelementFrame);
		
		click(emp_View);
		thread();
		
		
	}*/
	 
/*	 @Test(priority=3)
	 void locEmployeeInternalMapping() throws InterruptedException
	 {
		 List<WebElement> sTabLst=driver.findElements(By.xpath("//table[@id='rel_resources']/tbody/tr[2]/td/div/div/div[1]/div"));
		 List<WebElement> fstValList=driver.findElements(empsubFstVal);
		 List<WebElement> submitBtnList=driver.findElements(submitButton);
		 List<WebElement> moveAllBtn = driver.findElements(moveAll);
		 List<WebElement> removeAllBtn = driver.findElements(removeAll);
		 
		 int ad=0,k=0,j=0;
		 
		 for(int i=0;i<sTabLst.size();i++)
		 {
			 sTabLst.get(i).click();
			 List<WebElement> adRmvBtn = driver.findElements(addRemoveRltnBtn);
			 adRmvBtn.get(ad).click();
			 ad++;
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

			 By[] c = { teamListValues,tasksListValues, businessFunctionListValues };
		 }*/
		 
		 
	 
 

}
