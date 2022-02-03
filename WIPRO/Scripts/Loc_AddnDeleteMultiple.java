package Scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;
import static Config.TakScreenshot.*;


/******************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Add New Location Name and Delete it
 * Test 2: Verify Location Deleted Successfully message
 * Test 3: Verify Select Records to Perform Delete message
 * Test 4: Add Multiple Locations and delete it
 * Test 5: Verify Select Records to Perform Edit message
 * Test 6: Do the Mass Edit
 * Test 7: Verify Mass Edit Updated Successfully message
 * Test 8: Verify the Mass Edit Details in List View
 * Test 9: Verify Mass Edit zip code in edit screen
 * Test 10: Check Location Help pop up
 *  
*******************************************************************************************************/

public class Loc_AddnDeleteMultiple extends Page{
	
	LoginLogout ll = new LoginLogout();
	Loc_AddDeleteLocation loc_AddDeleteLocation = new Loc_AddDeleteLocation();
	
	public String selectRecordsToPerformDelete = "Select records to perform delete!";
	public String selectRecordstoPerformEditOperation = "Select records to perform Edit Operation...";
	public String fillAtleastOneFieldToUpdate = "Fill atleast any one field to update!";
	public String noRecordsFound = "No Records Found";
	
	private int country;
	private String statusInmEdtScr, typeInmEdtScr, managerInmEdtScr, stateInmEdtScr, cityInmEdtScr, countryInmEdtScr, zipcodeInmEdtScr;
		
   /*Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}  */
	
	@Test(priority=1)
	void deleteLocationName() throws InterruptedException, IOException, BiffException
	{
		loc_AddDeleteLocation.verifyAddNewLocationNamePge();
		loc_AddDeleteLocation.addNewLocation();
		getTotalValuesIndd(locLocationsLstViewTtl);	
		for(int i=1; i<totalDDValCount; i++)
		{
			String getAdedLocNm = driver.findElement(By.xpath("//table[@id='facilities_table']/tbody/tr["+i+"]/td[2]")).getText().substring(3);
			if(getAdedLocNm.contains(loc_AddDeleteLocation.adedLocationName))
			{
				thread();
				driver.findElement(By.xpath("//table[@id='facilities_table']/tbody/tr["+i+"]/td[12]/a[2]")).click();
				Thread.sleep(1000);
				verifyAssert(deleteConfPopup);
				Thread.sleep(1000);
				click(delConfOkBtn);
				Thread.sleep(1000);
			}			
		}
		Thread.sleep(1000);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(loc_AddDeleteLocation.adedLocationName+" Is Deleted", getActualObjectTxt);
		thread();
	}
	
	
 /* @Test(priority=2)
	void verifySelectRecordsToPerformDeleteMsg() throws IOException, InterruptedException
	{
		click(locOvrDeleteBtn);
		thread();
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(selectRecordsToPerformDelete, getActualObjectTxt);
		thread();
	}
	
	@Test(priority=3, invocationCount =3)
	void addMultipleLocationName() throws BiffException, IOException, InterruptedException
	{
		loc_AddDeleteLocation.verifyAddNewLocationNamePge();
		loc_AddDeleteLocation.addNewLocation();
		thread();
	}
	
  @Test(priority=4)
	void deleteMultipleLocation() throws IOException, InterruptedException
	{
		for(int i=1; i<4; i++)
		{
			driver.findElement(By.xpath("//table[@id='facilities_table']/tbody/tr["+i+"]/td/div/span/input")).click();
			takeScreenshot();
		}
		
		click(locOvrDeleteBtn);
		verifyAssert(deleteConfPopup);
		
		getObjectText(deleteConfPpMessage);
		takeScreenshot();
		verifyAssertEquals("Confirm to remove 3 records from Locations?", getActualObjectTxt);
		click(delConfOkBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Successfully deleted 3 records", getActualObjectTxt);
		takeScreenshot();
		thread();
	}*/
	
	@Test(priority=5)
	void verifySelectARecordToPerformEditMsg() throws InterruptedException, IOException
	{
		click(locOvrMassEditBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(selectRecordstoPerformEditOperation, getActualObjectTxt);
		thread();
	}
	
	@Test(priority=6)
	void massEdit() throws BiffException, IOException, InterruptedException
	{
		getLocSheetNameFromExcel();
		
		for(int i=1; i<3; i++)
		{
			driver.findElement(By.xpath("//table[@id='facilities_table']/tbody/tr["+i+"]/td/div/span/input")).click();	
			thread();
		}
				
		click(locOvrMassEditBtn);
		
		//	Status
		Random meStatus = new Random();
		click(locmEdtStatusDDArw);
		getTotalValuesIndd(locmEdtStatusCnt);
		int rst = meStatus.nextInt(totalDDValCount-1)+1;
		statusInmEdtScr = driver.findElement(By.id("facility_status_chzn_o_"+rst)).getText();
		enterText(locmEdtStatusSearchBox, statusInmEdtScr);
		enterKeyInKyBord(locmEdtStatusSearchBox);		
		
		//	Type
		Random meType = new Random();
		click(locmEdtTypeDDArw);
		getTotalValuesIndd(locmEdtTypeCnt);
		int rty = meType.nextInt(totalDDValCount-1)+1;
		typeInmEdtScr = driver.findElement(By.id("facility_type_chzn_o_"+rty)).getText();
		enterText(locmEdtTypeSearchBox, typeInmEdtScr);
		enterKeyInKyBord(locmEdtTypeSearchBox);
		
		//	Manager
		Random meManager = new Random();
		click(locmEdtManagerDDArw);
		getTotalValuesIndd(locmEdtManagerCnt);
		int rmgr = meManager.nextInt(totalDDValCount-1)+1;
		managerInmEdtScr = driver.findElement(By.id("facility_manager_chzn_o_"+rmgr)).getText();
		enterText(locmEdtManagerSearchBox, managerInmEdtScr.split(",")[0]);
		enterKeyInKyBord(locmEdtManagerSearchBox);
		
		//	Country
		ArrayList<Integer> list = new ArrayList<Integer>();
	    for (int i=1; i<loc.getRows(); i++) 
	    {
	    	list.add(new Integer(i));
	    }
	    
	    Collections.shuffle(list);
	    
	    for (int i=0; i<1; i++) 
	    {
	    	country = list.get(i);
	    	click(locmEdtCountryDDArw);
	    	countryInmEdtScr = loc.getCell(6, country).getContents();
	    	enterText(locmEdtCountrySearchBox, countryInmEdtScr);
	    	enterKeyInKyBord(locmEdtCountrySearchBox);
	    }
		
		//	State
	    click(locmEdtStateDDArw);
	    stateInmEdtScr = loc.getCell(7, country).getContents();
	    enterText(locmEdtStateSearchBox, stateInmEdtScr);
	    enterKeyInKyBord(locmEdtStateSearchBox);
	    
	    //	Zip Code
	    zipcodeInmEdtScr = loc.getCell(9, country).getContents();
	    enterText(locmEdtZipCode, zipcodeInmEdtScr);
	    
	    //	City
	    cityInmEdtScr = loc.getCell(8, country).getContents();
	    enterText(locmEdtCity, cityInmEdtScr);
	    
	    takeScreenshot();
	    click(locmEdtSubmitBtn);
	    Thread.sleep(1000);
	    getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("2 locations have been updated with "+managerInmEdtScr.split(",")[0],getActualObjectTxt);
		thread();
	}
	
	@Test(priority=7)
	void verifyMassUpdateTextInListView() throws InterruptedException
	{
		for(int i=1; i<3; i++)
		{
			for(int j=3; j<9; j++)
			{
				String getupdatdTxt = driver.findElement(By.xpath("//table[@id='facilities_table']/tbody/tr["+i+"]/td["+j+"]")).getText();
				if(i == 1 && j == 3 && statusInmEdtScr.equals("None"))
				{
					verifyAssertEquals("", getupdatdTxt);	//	Status
				}					
				else if(i == 1 && j == 3 && !statusInmEdtScr.equals("None"))
				{
					verifyAssertEquals(statusInmEdtScr, getupdatdTxt);	//	Status
				}				
				else if(i == 1 && j == 4 && typeInmEdtScr.equals("None"))
				{
					verifyAssertEquals("", getupdatdTxt);		//	Type
				}	
				else if(i == 1 && j == 4 && !typeInmEdtScr.equals("None"))
				{
					verifyAssertEquals(typeInmEdtScr, getupdatdTxt);		//	Type
				}
				else if(i == 1 && j == 5 && managerInmEdtScr.equals("None"))
				{
					verifyAssertEquals("", getupdatdTxt);	//	Manager
				}
				else if(i == 1 && j == 5 && !managerInmEdtScr.equals("None"))
				{
					verifyAssertEquals(managerInmEdtScr, getupdatdTxt);	//	Manager
				}
				else if(i == 1 && j == 8)
				{
					verifyAssertEquals(cityInmEdtScr, getupdatdTxt);	//	City
				}
				else if(i == 1 && j == 9)
				{
					verifyAssertEquals(stateInmEdtScr, getupdatdTxt);	//	State
				}
				else if(i == 1 && j ==10)
				{
					verifyAssertEquals(countryInmEdtScr, getupdatdTxt);		//	Country
				}
			}
		}
		thread();
		
		for(int z=1; z<3; z++)
		{
			driver.findElement(By.xpath("//table[@id='facilities_table']//tbody/tr["+z+"]/td[12]/a")).click();
			thread();
			
			//		Verify Zip code
			getAttributeVal(locZipCode);
			verifyAssertEquals(zipcodeInmEdtScr, getAttribteVal);
			
			click(locCancelBtn);
			thread();
		}
	}	
	
	@Test(priority=8)
	void locationHelpDocument() throws InterruptedException, IOException
	{
		click(locOvrInfoBtn);
		verifyAssert(locLocationHelpPp);
		takeScreenshot();
		thread();
		
		click(locLocationHelpPpCloseBtn);
	}
	
	/*@Test(priority=9)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
}
