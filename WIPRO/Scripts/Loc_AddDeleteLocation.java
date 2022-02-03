package Scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import jxl.read.biff.BiffException;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;


import UIWrappers.Page;



import static UIWrappers.UIObjects.*;
import static ObjectRepository.Employees.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Tasks.*;
import static ObjectRepository.Threats.*;
import static Config.TakScreenshot.*;

import java.net.URL; 

/*****************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Add New Location
 * Test 2: Verify Location Successfully Added message
 * Test 3: Verify Added Location Name details in List View
 * Test 4: Do the Employees, Threats, Assets, Insurance & Business Functions mapping to Locations
 * Test 5: Verify the Location Name in Mapping pop up
 * Test 6: Verify mapped successfully message for Employees, Threats, Assets, Insurance & Business Functions
 * Test 7: Search mapped Employees, Threats, Assets, Insurance & Business Functions in relationship list view
 * Test 8: Search Valid/Invalid Location Name in list view
 * Test 9: Verify Select All Check Box
 * Test 10: Verify Pagination
 * Test 11: Check Show Entries
 * Test 12: Check 'Clear' button functionality in edit screen
 * Test 13: Edit location details
 * Test 14: Verify edited details in list view
 *  
******************************************************************************************************/

public class Loc_AddDeleteLocation extends Page{
	

	LoginLogout ll = new LoginLogout();
	
	private int locatn;
	public String adedLocationName, adedStatus, adedType, adedManager, adedAddress, adedCountry, adedState, adedCity, adedZipcode;
	public String adedPhone,adedFax,adedBuiltOnYr, adedLocationId;
	private String phLocationName = "Location Name";
	private String locationStatusDefTxt = "Select Location Status";
	private String locationTypeDefTxt = "Select Location Type";
	private String selectManagerDefTxt = "Select Manager";
	private String phBuilOnYear = "yyyy";
	private String phLocationId = "Location ID";
	private String phAddress = "Address";
	private String phPhone="Phone";
	private String phFax="Fax";
	private String selectCountryDefTxt = "Select Country";
	private String selectStateDefTxt = "Select State";
	private String phCity = "City";
	private String phZipcode = "Zip Code";
	private String locationDetailsTxt = "Location Details";
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}
	
	@Test(priority=1)
	void verifyAddNewLocationNamePge() throws InterruptedException
	{
		click(locationsInMainMenu);
		 thread();
		//waitForPageLoad();
		click(locOvrAddBtn);
		verifyAssert(locLocationName);
		thread();
	}
	
	@Test(priority=2)
	void addNewLocation() throws BiffException, IOException, InterruptedException
	{
		getLocSheetNameFromExcel();
		ArrayList<Integer> list = new ArrayList<Integer>();
	    for (int i=1; i<loc.getRows(); i++) 
	    {
	    	list.add(new Integer(i));
	    }
	    
	    Collections.shuffle(list);
	    
	    for (int i=0; i<1; i++) 
	    {
	    	locatn = list.get(i);
	    	//	Location Name
	    	adedLocationName = loc.getCell(0, locatn).getContents();
	    	Thread.sleep(1000);
	    	enterText(locLocationName, adedLocationName);
	    	//	Status
	    	click(locStatusDDArw);
	    	getTotalValuesIndd(locStatusDDCnt);
	    	Random rstatus = new Random();
	    	int rst = rstatus.nextInt(totalDDValCount-1)+1;
	    	adedStatus = driver.findElement(By.id("fac_status_chzn_o_"+rst)).getText();
	    	enterText(locStatusSearchBox, adedStatus);
	    	enterKeyInKyBord(locStatusSearchBox);
	    	thread();    	
	    	//	Type
	    	click(locTypeDDArw);
	    	getTotalValuesIndd(locTypeDDCnt);
	    	Random rtype = new Random();
	    	int rtyp = rtype.nextInt(totalDDValCount-1)+1;
	    	adedType = driver.findElement(By.id("fac_type_chzn_o_"+rtyp)).getText();
	    	System.out.println("Added Type: "+adedType); 
	    	enterText(locTypeSearchBox, adedType);
	    	enterKeyInKyBord(locTypeSearchBox);
	    	thread();
	    	
	    	//	Manager
	    	click(locManagerDDArw);
	    	getTotalValuesIndd(locManagerDDCnt);
	    	if(totalDDValCount > 1)
	    	{
	    		Random rmanager = new Random();
		    	int rmgr = rmanager.nextInt(totalDDValCount-1)+1;
		    	adedManager = driver.findElement(By.id("fac_manager_chzn_o_"+rmgr)).getText();
		    	String mngr=driver.findElement(By.id("fac_manager_chzn_o_"+rmgr)).getText();
		    	//enterText(locManagerSearchBox, driver.findElement(By.id("fac_manager_chzn_o_"+rmgr)).getText().split(",")[0]);
		    	
		    	enterText(locManagerSearchBox, mngr.substring(0, mngr.indexOf(",")));
		    	thread();
		    	enterKeyInKyBord(locManagerSearchBox);
		    	thread();
	    	}    	
	    	
	    	//	Built On Year
	    	adedBuiltOnYr = loc.getCell(1, locatn).getContents();
	    	enterText(locBuiltOnYear, adedBuiltOnYr);
	    	
	    	//	Location Id
	    	adedLocationId = loc.getCell(2, locatn).getContents();
	    	enterText(locLocationId, adedLocationId);
	    	
	    	//	Address
	    	adedAddress = loc.getCell(3, locatn).getContents();
	    	enterText(locAddress, adedAddress);
	    	
	    	//Phone
	    	adedPhone=loc.getCell(4, locatn).getContents();
	    	enterText(locPhone,adedPhone);
	    	thread();
	    	
	    	//Fax
	    	adedFax=loc.getCell(5, locatn).getContents();
	    	enterText(locFax,adedFax);
	    	thread();
	    	//	Country
	    	click(locCountryDDArw);
	    	adedCountry = loc.getCell(6, locatn).getContents();
	    	System.out.println("Country: "+adedCountry);
	    	enterText(locCountrySearchBox, adedCountry);
	    	enterKeyInKyBord(locCountrySearchBox);
	    	thread();
	    	//	State
	    	click(locStateDDArw);
	    	adedState = loc.getCell(7, locatn).getContents();
	    	System.out.println("Aded State: "+adedState);
	    	enterText(locStateSearchBox, adedState);
	    	enterKeyInKyBord(locStateSearchBox);
	    	thread();
	    	
	    	//	City
	    	adedCity = loc.getCell(8, locatn).getContents();
	    	enterText(locCity, adedCity);
	    	
	    	//	Zip Code
	    	adedZipcode = loc.getCell(9, locatn).getContents();
	    	enterText(locZipCode, adedZipcode);
	    	thread();
	    	
	    	//Alternate Manager
	    	click(locAltrManagerDD);
	    	getTotalValuesIndd(locAltrManagerDDCnt);
	    	
	    	if(totalDDValCount>1)
	    	{
	    	Random randAltrMng=new Random();
	    	int rAltMngr=randAltrMng.nextInt(totalDDValCount-1)+1;
	    	String altrManager=driver.findElement(By.xpath("//li[@id='fac_alt_manager_chzn_o_"+rAltMngr+"']")).getText();
	    	
	    	enterText(locAltrManagerSearchBox,altrManager.substring(0, altrManager.indexOf(",")));
	    	thread();
	    	enterKeyInKyBord(locAltrManagerSearchBox);
	    	thread();
	    	
	    	}
	    }
	    webElement(locSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		
	    click(locSubmitBtn);
	    thread(); 
	    getObjectText(msgNotificationBar);
		if(getActualObjectTxt.equals(adedLocationName+" Successfully Added"))
		{
			System.out.println("perform Location add operation");
		}
		else
		{
			verifyAddNewLocationNamePge();
			thread();
			addNewLocation();
			thread();
		}
		}
	

	@Test(priority=3)

	void verifyAddedLocationNameInListView() throws InterruptedException
	{
		getTotalValuesIndd(locLocationsLstViewTtl);				
		for(int i=1; i<totalDDValCount; i++)
		{
			WebElement getAddedLocNm = driver.findElement(By.xpath("//table[@id='facilities_table']/tbody/tr["+i+"]/td[2]"));
			if(getAddedLocNm.getText().contains(adedLocationName))
			{
				getAddedLocNm.click();		//	Click On Added Location Name
				for(int j=2; j<11; j++)
				{
					WebElement getAddedLocNameDtls = driver.findElement(By.xpath("//table[@id='facilities_table']/tbody/tr["+i+"]/td["+j+"]"));
					try
					{
						if(j == 2)
							verifyAssertEquals("Map "+adedLocationName, getAddedLocNameDtls.getText());	//	Location Name						
						else if(j == 3)
							verifyAssertEquals(adedStatus, getAddedLocNameDtls.getText()); 	//	Status
						else if(j == 4)
							verifyAssertEquals(adedType, getAddedLocNameDtls.getText());		//	Type
						else if(j == 5)
							verifyAssertEquals(adedManager, getAddedLocNameDtls.getText());	//	Manager
						else if(j == 7)
							verifyAssertEquals(adedAddress, getAddedLocNameDtls.getText());		//	Address
						else if(j == 8)
							verifyAssertEquals(adedCity, getAddedLocNameDtls.getText());		//	City
						else if (j == 9)
							verifyAssertEquals(adedState, getAddedLocNameDtls.getText());		//	State
						else if(j == 10)
						{
							/*WebElement getAddedLocCountry= driver.findElement(By.xpath("//table[@id='facilities_table']/tbody/tr["+i+"]/td["+j+"]/div[1]"));	
							verifyAssertEquals(adedCountry, getAddedLocCountry.getText());      //	Country*/
							
							verifyAssertEquals(adedCountry, getAddedLocNameDtls.getText());
						}
						
					}catch(Exception e) {
						e.printStackTrace();
					}					
				}
			}			
		}
		thread();
	}
	

	
	@Test(priority=4)

	void viewLocationDetails() throws InterruptedException
	{
		getTotalValuesIndd(locLocationsLstViewTtl);				
		for(int i=1; i<2; i++)
		{
			WebElement getAddedLocNm = driver.findElement(By.xpath("//table[@id='facilities_table']/tbody/tr["+i+"]/td[2]"));
			if(getAddedLocNm.getText().contains(adedLocationName))
			{
				click(locViewBtn);
				thread();
				
				getObjectText(locLocationsDetailsTxtInViewPge);
				verifyAssertEquals(locationDetailsTxt, getActualObjectTxt);
				thread();
				
				//click(locationsInMainMenu);
				click(By.xpath("//div[@id='showdisplay_view']/fieldset/div[6]/button"));        //close button
				thread();
			}
		}
	}
	
	@Test(priority=5)
	void verifyLocationNameInEmpMapingPp() throws InterruptedException, BiffException, IOException
	{
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Employees To "+adedLocationName, getActualObjectTxt);
		thread(); 
		
	}
	
	

	@Test(priority=6)
	void mapEmployeesToLocation() throws InterruptedException, IOException
	{

	/*	driver.findElement(By.xpath("html/body/div[4]/div[1]/div[1]/div[1]/div/ul/li[3]/a")).click();
		thread();
		scrollInnerScrollBar(driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]/div[1]/p/a")));
		thread();
		click(By.xpath(".//*[@id='content']/div[2]/div[2]/div[1]/p/a"));
		thread();  */
		
		doMappingt();
		click(mapSubmitBtn);
		//click(By.xpath(".//*[@id='post_relationship']"));      //submit
		thread();
		//click(By.xpath("html/body/div[9]/div/div/div[3]/button"));
		//driver.findElement(By.xpath("html/body/div[9]/div/div/div[3]/button[2]")).click();
		driver.findElement(By.cssSelector("button[class='btn btn btn-primary']")).click();
		thread();
		
		//waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(adedLocationName+" successfully mapped to Employees", getActualObjectTxt);
		thread();
		
		/*doMappingt();
		takeScreenshot();
		
		click(mapSubmitBtn);
		thread();
		
		try
		{			
			verifyAssert(locEmpDiffLocatnPp);
			getObjectText(locEmpDiffLocatnMsg);
			String[] diffLocatnPpMsg = getActualObjectTxt.split("-");
			verifyAssertEquals("Employees are different from "+adedLocationName+" location", diffLocatnPpMsg[1].trim());
			
			//		Check Count
			getTotalValuesIndd(locEmpDiffLocatnPpCnt);
			verifyAssertEquals(diffLocatnPpMsg[0].trim(), String.valueOf(totalDDValCount));  
		
			click(locEmpDiffLocatnPpOkBtn);
			
		}
		catch(NoSuchElementException nos)
		{
			nos.printStackTrace();
			System.out.println("All Employees From Same Location"); 
		}*/
	}
	
	

	@Test(priority=7)
	void searchMappedEmployees() throws IOException, InterruptedException
	{
		//		Search Valid Employee Name
		getTotalValuesIndd(locRltnEmployeesCnt);
		Random random = new Random();
		int rEmp = random.nextInt(totalDDValCount-1)+1;
		if(rEmp==0)
		{
			rEmp=rEmp+1;
		}
		String getEmpName = driver.findElement(By.xpath("//table[@id='rel_resources']/tbody/tr["+rEmp+"]/td")).getText();
		enterText(locRltnEmployeesSearchBox , getEmpName);
	
		getObjectText(locRltnEmployeesSrchRslt);
		verifyAssertEquals(getEmpName, getActualObjectTxt);
		takeScreenshot();
		clear(locRltnEmployeesSearchBox );
		enterKeyInKyBord(locRltnEmployeesSearchBox );
		thread();
		
		//		Search Invalid Employee Name 
		enterText(locRltnEmployeesSearchBox, "Inv Emp Name");
		//getObjectText(noRecordsFoundMsg);
		getObjectText(locRltnEmployeesSrchRslt);
		
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(locRltnEmployeesSearchBox);
		
		enterKeyInKyBord(locRltnEmployeesSearchBox);
		thread();
	}	
	

	@Test(priority=8)
	void verifyLocationNameInThreatsMapingPp() throws InterruptedException
	{
		click(threatsTb);
		thread();
		
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Threats To "+adedLocationName, getActualObjectTxt);		
	}
	

	@Test(priority=9)
	void mapThreatsToLocation() throws InterruptedException, IOException
	{
		doMappingt();
		takeScreenshot();
		
		click(mapSubmitBtn);
		thread();
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(adedLocationName+" successfully mapped to Threats", getActualObjectTxt);	
	}
	
	

	@Test(priority=10)
	void searchMappedThreats() throws IOException, InterruptedException
	{
		scrollInnerScrollBar(driver.findElement(rltnHeader));
		thread();
		//		Search Valid Threat Name
		getTotalValuesIndd(locRltnThrNmCnt);
		Random random = new Random();
		int rThrNm = random.nextInt(totalDDValCount-1)+1;
		String getthrName = driver.findElement(By.xpath("//table[@id='rel_threats']/tbody/tr["+rThrNm+"]/td")).getText();
		//enterText( locRltnThrNmSearchBox , getthrName.substring(5));
		enterText( locRltnThrNmSearchBox , getthrName);
		
		getObjectText(locRltnThrNmSrchRslt);
		verifyAssertEquals(getthrName, getActualObjectTxt);
		takeScreenshot();
		clear(locRltnThrNmSearchBox );
		enterKeyInKyBord(locRltnThrNmSearchBox);
		thread();
		
		//		Search Invalid Threats Name 
		scrollInnerScrollBar(driver.findElement(rltnHeader));
		thread();
		enterText(locRltnThrNmSearchBox , "Inv Threat Name");
		enterKeyInKyBord(locRltnThrNmSearchBox);
		thread();
		//getObjectText(noRecordsFoundMsg);
		getObjectText(locRltnThrNmSrchRslt);
		thread();
		System.out.println("Threats search "+getActualObjectTxt);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(locRltnThrNmSearchBox );
		enterKeyInKyBord(locRltnThrNmSearchBox );
		thread();
	}	
	

	@Test(priority=11)
	void verifyLocationNameInAssetsMapingPp()
	{
		click(assetsTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Assets To "+adedLocationName, getActualObjectTxt);		
	}
	

	@Test(priority=12)
	void mapAssetsToLocation() throws InterruptedException, IOException
	{
		doMappingt();
		takeScreenshot();
		
		click(mapSubmitBtn);
		thread();
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(adedLocationName+" successfully mapped to Assets", getActualObjectTxt);
	}
	

	@Test(priority=13)
	void searchMappedAssets() throws IOException, InterruptedException
	{
		//		Search Valid Asset Name
		getTotalValuesIndd(locRltnAssetsCnt);
		Random random = new Random();
		int rass = random.nextInt(totalDDValCount-1)+1;
		String getAssets = driver.findElement(By.xpath("//table[@id='rel_assets']/tbody/tr["+ rass+"]/td")).getText();
		enterText( locRltnAssetsSearchBox , getAssets.substring(5));
	
		getObjectText(locRltnAssetsSrchRslt);
		verifyAssertEquals(getAssets, getActualObjectTxt);
		takeScreenshot();
		clear(locRltnAssetsSearchBox );
		enterKeyInKyBord(locRltnAssetsSearchBox);
		Thread.sleep(1000);
		
		//		Search Invalid Assets Name 
		enterText(locRltnAssetsSearchBox , "Inv Assests Name");
		//getObjectText(noRecordsFoundMsg);
		getObjectText(locRltnAssetsSrchRslt);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(locRltnAssetsSearchBox);
		enterKeyInKyBord(locRltnAssetsSearchBox);
		thread();
	}	
	

	@Test(priority=14)
	void verifyLocationNameInInsuranceMapingPp()
	{
		click(insuranceTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Insurance To "+adedLocationName, getActualObjectTxt);		
	}
	

	@Test(priority=15)
	void mapInsuranceToLocations() throws InterruptedException, IOException
	{
		doMappingt();
		takeScreenshot();
		
		click(mapSubmitBtn);
		thread();
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(adedLocationName+" successfully mapped to Insurance", getActualObjectTxt);	
	}
	

	@Test(priority=16)
	void searchMappedInsuranceNm() throws IOException, InterruptedException
	{
		//		Search Valid Insurance Name
		getTotalValuesIndd(locRltnInsCnt);
		Random random = new Random();
		int rIns = random.nextInt(totalDDValCount-1)+1;
		String getIns = driver.findElement(By.xpath("//table[@id='rel_insurances']/tbody/tr["+ rIns+"]/td")).getText();
		enterText(locRltnInsSearchBox , getIns);
	
		getObjectText(locRltnInsSrchRslt);
		verifyAssertEquals(getIns, getActualObjectTxt);
		takeScreenshot();
		clear(locRltnInsSearchBox );
		enterKeyInKyBord(locRltnInsSearchBox);
		thread();
		
		//		Search Invalid Insurance Name 
		enterText(locRltnInsSearchBox , "Inv Ins Name");
		//getObjectText(noRecordsFoundMsg);
		getObjectText(locRltnInsSrchRslt);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(locRltnInsSearchBox );
		enterKeyInKyBord(locRltnInsSearchBox );
		thread();
	}	
	

	@Test(priority=17)
	void verifyLocationNameInBusinessFunctionsMapingPp()
	{
		click(businessFunctionsTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Business Functions To "+adedLocationName, getActualObjectTxt);		
	}
	
	

	@Test(priority=18)
	void mapBusinessFunctionsToLocations() throws InterruptedException, IOException
	{
		doMappingt();
		takeScreenshot();
		
		click(mapSubmitBtn);
		thread();
		
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(adedLocationName+" successfully mapped to Business Functions", getActualObjectTxt);
	}

	@Test(priority=19)
	void searchMappedBusinessFunctions() throws IOException, InterruptedException
	{
		//		Search Valid Business Functions
		getTotalValuesIndd(locRltnBusinesFuncCnt);
		Random random = new Random();
		int rBusiFunc = random.nextInt(totalDDValCount-1)+1;
		String getBusinessFunctionName = driver.findElement(By.xpath("//table[@id='rel_processes']/tbody/tr["+rBusiFunc+"]/td")).getText();
		enterText(locRltnBusinesFuncSearchBox, getBusinessFunctionName.substring(5));
		
		getObjectText(locRltnBusinessFuncSearchRslt);
		verifyAssertEquals(getBusinessFunctionName, getActualObjectTxt);
		takeScreenshot();
		clear(locRltnBusinesFuncSearchBox);
		enterKeyInKyBord(locRltnBusinesFuncSearchBox);
		thread();
		
		//		Search Invalid Business Functions
		enterText(locRltnBusinesFuncSearchBox, "Inv Bus Func Name");
		getObjectText(locRltnBusinessFuncSearchRslt);
		//getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(locRltnBusinesFuncSearchBox);
		enterKeyInKyBord(locRltnBusinesFuncSearchBox);
		thread();
	}
	

	@Test(priority=20)
	void verfiyAndMapContacts() throws IOException, InterruptedException
	{
		click(emergencycontactsTb);
		click(rltnAddRemoveRltnsBtn);
        verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Contacts To "+adedLocationName, getActualObjectTxt);
		
		//Mapping contacts
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
		waitForElement(msgNotificationBar);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals(adedLocationName+" successfully mapped to Contacts", getActualObjectTxt);
		thread();
		
	}

	@Test(priority=21)
	void searchContact() throws IOException, InterruptedException
	{
		getTotalValuesIndd(locRltnEmergCont);
		Random random = new Random();
		int rEmgCont = random.nextInt(totalDDValCount-1)+1;
		String getEmergencyContact = driver.findElement(By.xpath("//table[@id='rel_contacts']/tbody/tr["+rEmgCont+"]/td")).getText();
		enterText(locRltnEmergContSearchBox , getEmergencyContact);
		getObjectText(locRltnEmergContSrchRslt);
		verifyAssertEquals(getEmergencyContact, getActualObjectTxt);
		takeScreenshot();
		clear(locRltnEmergContSearchBox);
		enterKeyInKyBord(locRltnEmergContSearchBox);
		thread();
		
		// Search Invalid Emergency Contact
		enterText(locRltnEmergContSearchBox, "Inv Emergency Cont");
		getObjectText(locRltnEmergContSrchRslt);
		//getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(locRltnEmergContSearchBox );
		enterKeyInKyBord(locRltnEmergContSearchBox);
		thread();
		
		}
	
	// Mapping teams to Locations
	
		@Test(priority=22)
		void verifyLocNameInTeamsMappingPp() throws InterruptedException
		{
			click(teamsTb);
			click(rltnAddRemoveRltnsBtn);
			verifyAssert(mapMappingPopup);
			
			getObjectText(mapMappingPpTitleNm);
			verifyAssertEquals("Map Teams To "+adedLocationName, getActualObjectTxt);
			thread();
		}
		
		@Test(priority=23)
		void mapTeamsToLocations() throws InterruptedException, IOException
		{
			doMappingt();
			takeScreenshot();
			
			click(mapSubmitBtn);
			thread();
			
			waitForElement(msgNotificationBar);
			getObjectText(msgNotificationBar);
			takeScreenshot();
			verifyAssertEquals(adedLocationName+" successfully mapped to Teams", getActualObjectTxt);
		}
		
		@Test(priority=24)
		void searchMappedTeams() throws IOException, InterruptedException
		{
			getTotalValuesIndd(locRltnTeamsCnt);
			Random rand=new Random();
			int ranTeam=rand.nextInt(totalDDValCount-1)+1;
			String teamName=driver.findElement(By.xpath("//table[@id='rel_resourcegroup']/tbody/tr["+ranTeam+"]/td[1]")).getText();
			enterText(locRltnTeamsSearchBox,teamName);
			getObjectText(locRltnTeamsSearchRslt);
			verifyAssertEquals(teamName,getActualObjectTxt);
			takeScreenshot();
			clear(locRltnTeamsSearchBox);
			enterKeyInKyBord(locRltnTeamsSearchBox);
			thread();
			
			
		}	
	
		
	/* @Test(priority=25)
     void Documentupload() throws IOException, InterruptedException, AWTException
     {
     	
     	click(docuemntTb);
    	thread();
     	click(choosefiles);
     	//thread();
		//uploadFile("D:\\SIB\\Logo\\FatPipe_logo.jpg");
		//thread();
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
	
	@Test(priority=26)
	void searchLocationsNameInListView() throws IOException, InterruptedException
	{
		getTotalValuesIndd(locLocationsLstViewTtl);
		Random srch = new Random();
		int lnm = srch.nextInt(totalDDValCount-1)+1;
		String searchedLocName = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody/tr["+lnm+"]/td[2]")).getText().substring(4);
		thread();
		//		Search Valid Location Name
		enterText(locLocationsSearchBox, searchedLocName);
		takeScreenshot();
		getObjectText(locLocationLstViewFstName);
		try
		{
		verifyAssertEquals(searchedLocName, getActualObjectTxt.substring(4));
		thread();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		clear(locLocationsSearchBox);
		enterKeyInKyBord(locLocationsSearchBox);
		thread();
		
		//		Invalid Location Name
		enterText(locLocationsSearchBox, "Invalid Location Name");
		takeScreenshot();
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		clear(locLocationsSearchBox);
		enterKeyInKyBord(locLocationsSearchBox);
		thread();
	}
	
	@Test(priority=27)
	void selectAllChkBox() throws IOException, InterruptedException
	{		
		click(locSelectAllChkBox);
		takeScreenshot();
		
		getTotalValuesIndd(locLocationsLstViewTtl);
		for(int i=1; i<=totalDDValCount; i++)
		{
			boolean chkd = driver.findElement(By.xpath("//table[@id='facilities_table']/tbody/tr["+i+"]/td/div/span")).isEnabled();	//	Check Check box is enabled
			Assert.assertEquals(true, chkd);
		}		
		click(locSelectAllChkBox);		
	}
	
	@Test(priority=28)
	void locationListViewPagination() throws IOException, InterruptedException
	{
		getTotalValuesIndd(locListViewPagination);		
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
				int randomPge = randomPagi.nextInt((totalDDValCount-2)-4)+4;
				driver.findElement(By.xpath("//div[@id='facilities_table_wrapper']//div[@class='dataTables_paginate paging_bootstrap pagination pagination-right']/ul/li["+randomPge+"]/a")).click();
				thread();
				takeScreenshot();
				click(pagiStartArw);
				thread();
			}	
			else
			{
				System.out.println("***** Pagination is not available. Locations list view has 10 or less than 10 records *****");
			}
		} 
		catch(Exception e) 
		{
			//e.printStackTrace();
		}		
	}
	
	@Test(priority=29)
	void locationLstViewShowEntries() throws InterruptedException, IOException
	{
		try
		{
			getObjectText(locListViewRecsInfo);
			
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].substring(0, 2).trim()) >=10)
			{
				selectTextFromDropdown(locListViewLength, "25");
				thread();
				takeScreenshot();
				
				selectTextFromDropdown(locListViewLength, "10");
				thread();
			}
			else
			{
				System.out.println("***** Pagination is not available in Locations List View. List View has 10 or less than 10 records. So we can't choose the list view length *****");
			}
		}
		catch(WebDriverException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=30)
	void editLocationName() throws InterruptedException, BiffException, IOException
	{
		getTotalValuesIndd(locLocationsLstViewTtl);
		for(int i=1; i<totalDDValCount; i++)
		{
			String AdedLocNm = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody/tr["+i+"]/td[2]")).getText();
			if(AdedLocNm.contains(adedLocationName))
			{
				driver.findElement(By.xpath("//table[@id='facilities_table']//tbody/tr["+i+"]/td[12]/a[1]")).click();
				thread();
				
				//		Verify entered values in edit screen
				
				//		Location Name
				getAttributeVal(locLocationName);
				System.out.println(adedLocationName+"expected----Actual"+getAttribteVal);
				verifyAssertEquals(adedLocationName, getAttribteVal);
				
				/*getObjectText(locLocationName);
				System.out.println(adedLocationName+"expected----Actual"+getActualObjectTxt);
				verifyAssertEquals(adedLocationName,getActualObjectTxt);*/
				
				
				//		Location Status
				getObjectText(locStatusDDDefTxt);
				verifyAssertEquals(adedStatus, getActualObjectTxt);
				
				//		Location Type
				getObjectText(locTypeDDDefTxt);
				verifyAssertEquals(adedType, getActualObjectTxt);
				
				//		Manager				
				try
				{
				getObjectText(locManagerDDDefTxt);
				verifyAssertEquals(adedManager, getActualObjectTxt);
				}
				catch(Exception e)
				{
					
				}
				
				//		Built On Year
				getAttributeVal(locBuiltOnYear);
				verifyAssertEquals(adedBuiltOnYr, getAttribteVal);
				
				//		Location Id
				getAttributeVal(locLocationId);
				verifyAssertEquals(adedLocationId, getAttribteVal);
				
				//		Address
				getAttributeVal(locAddress);
				verifyAssertEquals(adedAddress, getAttribteVal);
				
				
				//      Phone
				getAttributeVal(locPhone);
				verifyAssertEquals(adedPhone.trim(),getAttribteVal.trim());
				
				//      Fax
				getAttributeVal(locFax);
				verifyAssertEquals(adedFax.trim(),getAttribteVal.trim());
				
				//		Country
				getObjectText(locCountryDDDefTxt);
				verifyAssertEquals(adedCountry.trim(), getActualObjectTxt.trim());
				
				//		State
				getObjectText(locStateDDDefTxt);
				verifyAssertEquals(adedState, getActualObjectTxt);
				
				//		City
				getAttributeVal(locCity);
				verifyAssertEquals(adedCity, getAttribteVal);
				
				//		Zip code
				getAttributeVal(locZipCode);
				verifyAssertEquals(adedZipcode, getAttribteVal);
				
				click(locClearBtn);
				thread();
				
				
				
				//		Location Name
				getAttributePh(locLocationName);
				verifyAssertEquals(phLocationName, getAttribtePh);
				
				//		Location Status
				getObjectText(locStatusDDDefTxt);
				verifyAssertEquals(locationStatusDefTxt, getActualObjectTxt);
				
				//		Location Type
				getObjectText(locTypeDDDefTxt);
				verifyAssertEquals(locationTypeDefTxt, getActualObjectTxt);
				
				//		Manager
				getObjectText(locManagerDDDefTxt);
				verifyAssertEquals(selectManagerDefTxt, getActualObjectTxt);
				
				//		Built on year
				getAttributePh(locBuiltOnYear);
				verifyAssertEquals(phBuilOnYear, getAttribtePh);
				
				//		Location Id
				getAttributePh(locLocationId);
				verifyAssertEquals(phLocationId, getAttribtePh);
				
				//		Address
				getAttributePh(locAddress);
				verifyAssertEquals(phAddress, getAttribtePh);
				
				//Phone
				getAttributePh(locPhone);
				verifyAssertEquals(phPhone,getAttribtePh);
				
				//Fax
				getAttributePh(locFax);
				verifyAssertEquals(phFax,getAttribtePh);
				
				//		Country
				getObjectText(locCountryDDDefTxt);
				verifyAssertEquals(selectCountryDefTxt, getActualObjectTxt.trim());
				
				//		State
				getObjectText(locStateDDDefTxt);
				verifyAssertEquals(selectStateDefTxt, getActualObjectTxt);
				
				//		City
				getAttributePh(locCity);
				verifyAssertEquals(phCity, getAttribtePh);
				
				//		Zip code				
				getAttributePh(locZipCode);
				verifyAssertEquals(phZipcode, getAttribtePh);
				
				
				
				//		Edit location name
				getLocSheetNameFromExcel();	
				thread();
				
				ArrayList<Integer> list = new ArrayList<Integer>();
			    for (int n=1; n<loc.getRows(); n++) 
			    {
			    	list.add(new Integer(n));
			    }
			    
			    Collections.shuffle(list);
			    
			    for (int n=0; n<1; n++) 
			    {
			    	locatn = list.get(n);
			    	//	Location Name
			    	adedLocationName = loc.getCell(0, locatn).getContents();
			    	enterText(locLocationName, adedLocationName);
			    	thread();
			    	
			    	//	Status
			    	click(locStatusDDArw);
			    	getTotalValuesIndd(locStatusDDCnt);
			    	Random rstatus = new Random();
			    	int rst = rstatus.nextInt(totalDDValCount-1)+1;
			    	adedStatus = driver.findElement(By.id("fac_status_chzn_o_"+rst)).getText();
			    	enterText(locStatusSearchBox, adedStatus);
			    	enterKeyInKyBord(locStatusSearchBox);
			    	thread();
			    	
			    	//	Type
			    	click(locTypeDDArw);
			    	getTotalValuesIndd(locTypeDDCnt);
			    	Random rtype = new Random();
			    	int rtyp = rtype.nextInt(totalDDValCount-1)+1;
			    	adedType = driver.findElement(By.id("fac_type_chzn_o_"+rtyp)).getText();
			    	System.out.println("Added Type: "+adedType); 
			    	enterText(locTypeSearchBox, adedType);
			    	enterKeyInKyBord(locTypeSearchBox);
			    	thread();
			    	
			    	//	Manager
			    	click(locManagerDDArw);
			    	getTotalValuesIndd(locManagerDDCnt);
			    	if(totalDDValCount > 1)
			    	{
			    		Random rmanager = new Random();
				    	int rmgr = rmanager.nextInt(totalDDValCount-1)+1;
				    	adedManager = driver.findElement(By.id("fac_manager_chzn_o_"+rmgr)).getText();
				    	String mngr=driver.findElement(By.id("fac_manager_chzn_o_"+rmgr)).getText();
				    	//enterText(locManagerSearchBox, driver.findElement(By.id("fac_manager_chzn_o_"+rmgr)).getText().split(",")[0]);
				    	
				    	enterText(locManagerSearchBox, mngr.substring(0, mngr.indexOf(",")));
				    	thread();
				    	enterKeyInKyBord(locManagerSearchBox);
				    	thread();
			    	}    	
			    	
			    	//	Built On Year
			    	adedBuiltOnYr = loc.getCell(1, locatn).getContents();
			    	enterText(locBuiltOnYear, adedBuiltOnYr);
			    	
			    	//	Location Id
			    	adedLocationId = loc.getCell(2, locatn).getContents();
			    	enterText(locLocationId, adedLocationId);
			    	
			    	//	Address
			    	adedAddress = loc.getCell(3, locatn).getContents();
			    	enterText(locAddress, adedAddress);
			    	
			    	//Phone
			    	adedPhone=loc.getCell(4, locatn).getContents();
			    	enterText(locPhone,adedPhone);
			    	thread();
			    	
			    	//Fax
			    	adedFax=loc.getCell(5, locatn).getContents();
			    	enterText(locFax,adedFax);
			    	thread();
			    	
			    	//	Country
			    	click(locCountryDDArw);
			    	adedCountry = loc.getCell(6, locatn).getContents();
			    	System.out.println("Country: "+adedCountry);
			    	enterText(locCountrySearchBox, adedCountry);
			    	enterKeyInKyBord(locCountrySearchBox);
			    	thread();
			    	
			    	
			    	//	State
			    	click(locStateDDArw);
			    	adedState = loc.getCell(7, locatn).getContents();
			    	System.out.println("Aded State: "+adedState);
			    	enterText(locStateSearchBox, adedState);
			    	enterKeyInKyBord(locStateSearchBox);
			    	thread();
			    	
			    	//	City
			    	adedCity = loc.getCell(8, locatn).getContents();
			    	enterText(locCity, adedCity);
			    	
			    	//	Zip Code
			    	adedZipcode = loc.getCell(9, locatn).getContents();
			    	enterText(locZipCode, adedZipcode);
			    	thread();
			    }
			    webElement(locSubmitBtn);
				scrollInnerScrollBar(webelementFrame);
				
			    click(locSubmitBtn);
			    thread();

				try
			    {
					
					
					   File newFile = new File(xlFilesLocation);
						if (newFile.exists())
						            {
							            getObjectText(msgNotificationBar);
							            click(locClearBtn);
							            thread();
							            editLocationName();
						            }
					 }
				catch(Exception e)
				{
					e.printStackTrace();
					
				}
				Thread.sleep(1000);
				//		Verify location updated successfully message
				getObjectText(msgNotificationBar);
				takeScreenshot();
				verifyAssertEquals(adedLocationName+" Successfully Updated", getActualObjectTxt);
                thread();
                
				//		Verify edited details in list view
				getTotalValuesIndd(locLocationsLstViewTtl);
				for(int l=1; l< totalDDValCount; l++)
				{
					String locName = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody/tr["+l+"]/td[2]")).getText();
					if(locName.equals(adedLocationName))
					{
						for(int j=2; j<11; j++)
						{
							String editdDtls = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody/tr["+l+"]/td["+j+"]")).getText();
							
							if(j == 2)
								verifyAssertEquals(adedLocationName, editdDtls);		//	Location Name
							if(j == 3)
								verifyAssertEquals(adedStatus, editdDtls);	//	Location Status
							if(j == 4)
								verifyAssertEquals(adedType, editdDtls);	//	Location Type
							if(j == 5)
								verifyAssertEquals(adedManager, editdDtls);	//	Manager
							if(j == 7)
								verifyAssertEquals(adedAddress, editdDtls);	//	Address
							if(j == 8)
								verifyAssertEquals(adedCity, editdDtls);	//	City
							if(j == 9)
								verifyAssertEquals(adedState, editdDtls);	//	State
							if(j == 10)
							{
							/*	String editdCountryDtls = driver.findElement(By.xpath("//table[@id='facilities_table']//tbody/tr["+l+"]/td["+j+"]/div[1]")).getText();
								verifyAssertEquals(adedCountry, editdCountryDtls);	*/
								
								verifyAssertEquals(adedCountry, editdDtls);   //	Country		
							}
								
						}
					}
				}
			}
		}	
		Thread.sleep(1000);
	
	}
	
	
	     
	/*@Test(priority=31)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/

	
}


