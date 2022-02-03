package Scripts;

import static Config.TakScreenshot.*;
import static ObjectRepository.Contacts.*;
import static ObjectRepository.Threats.*;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Tasks.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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
import model.ContactDetails;

/*******************************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Add New Contact Details
 * Test 2: Verify Contact Name added successfully message
 * Test 3: Verify the added Contact Name in list view
 * Test 4: Verify Contact Name in mapping pop up
 * Test 5: Map Task & Task Groups to Contacts
 * Test 6: Verify mapped successfully message
 * Test 7: Search with Valid/Invalid mapped name in Relationship table
 * Test 8: Search with Valid/Invalid Contact Name
 * Test 9: Verify Select All check box
 * Test 10: Check Contacts list view pagination
 * Test 11: Verify Show Entries drop down in Contacts List view
 * Test 12: Check 'Clear' button functionality on Edit Contact Name screen
 * Test 13: Edit the Contact details
 * Test 14: Verify Contact Name updated successfully message
 * Test 15: Verify the edited details in List view
 * 
********************************************************************************************************************/

public class Cnt_AddNewContact extends Page {

	LoginLogout ll = new LoginLogout();
	
	public String companyName, contactType, contactAddress, contactStatus, contactPhoneNo, primaryExtn, primaryMobile, primaryBusinessMail;
	public String primaryFirstName, primaryLastName,primaryPhoneNo,secPhoneNo;
	private String modifiedCompanyName, modifiedContactType, modifiedContactStatus, modifiedContactAddress, modifiedContactPhoneNo, modifiedPrimaryFirstName, modifiedPrimaryLastName, modifiedPrimaryBusinessMail, modifiedPrimaryExtn, modifiedPrimaryMobile;        
	private String modifiedPmyPhoneNo,modifiedSecPhoneNo;
	private String phCompanyName = "Company Name";
	private String phContactId = "Contact ID";
	private String defContactType = "Select/Add New Contact Type";
	private String defContactStatus = "Select/Add New Contact Status";
	private String phAddress = "Address";
	private String defCountry = "Select Country";
	private String defState = "Select State";
	private String phCity = "City";
	private String phZipcode = "Zip Code";
	private String defServiceArea = "Select/Add New Service Area";
	private String phPhone = "Phone (O)";
	private String phFirstName = "First Name";
	private String phLastName = "Last Name";
	private String phTitle = "Title";
	private String phBusinessEmail = "Business Email";
	private String phAlternateEmail = "Alternate Email";
	private String phExtn = "Extn";
	private String phMobile = "Mobile Number";
	
	ArrayList<ContactDetails> cntList = new ArrayList<ContactDetails>();	
	
  /* @Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	} */ 
	
	@Test(priority=1)
	void verifyAddContactDtlsPage() throws InterruptedException
	{
		click(contactsInMainMenu);
        Thread.sleep(1000);
		click(cntOvrAddBtn);
		Thread.sleep(1000);
	}
	
	@Test(priority=2)
	void addContactDetails() throws BiffException, IOException, InterruptedException
	{
		getContactsSheetFromExcel();	
		
		if(cntList.size() == 0) 
		{
			getContactsSheetFromExcel();
			
			for(int row = 1; row < cnt.getRows(); row++)
			{
				ContactDetails contactDetails = new ContactDetails();
				for(int col=0; col<cnt.getColumns();col++)
				{	
					if(col == 0) 
						contactDetails.setCompanyName(cnt.getCell(col, row).getContents());
					if(col == 1)
						contactDetails.setContactID(cnt.getCell(col, row).getContents());						
					if(col == 2)
						contactDetails.setAddress(cnt.getCell(col, row).getContents());
					if(col == 3) 
						contactDetails.setCountry(cnt.getCell(col, row).getContents());
					if(col == 4)
						contactDetails.setState(cnt.getCell(col, row).getContents());
					if(col == 5) 
						contactDetails.setCity(cnt.getCell(col, row).getContents());
					if(col == 6) 
						contactDetails.setZipCode(cnt.getCell(col, row).getContents());
					if(col == 7) 
						contactDetails.setPhone(cnt.getCell(col, row).getContents());
					if(col == 8) 
						contactDetails.setComments(cnt.getCell(col, row).getContents());
					if(col == 9) 
						contactDetails.setPrimaryFirstName(cnt.getCell(col, row).getContents());
					if(col == 10) 
						contactDetails.setPrimaryLastName(cnt.getCell(col, row).getContents());
					if(col == 11) 
						contactDetails.setPrimaryTitle(cnt.getCell(col, row).getContents());
					if(col == 12) 
						contactDetails.setPrimaryBusinessEmail(cnt.getCell(col, row).getContents());
					if(col == 13) 
						contactDetails.setPrimaryalternateEmail(cnt.getCell(col, row).getContents());
					if(col == 14) 
						contactDetails.setPrimaryExtn(cnt.getCell(col, row).getContents());
					if(col == 15) 
						contactDetails.setPrimaryMobile(cnt.getCell(col, row).getContents());
					if(col == 16) 
						contactDetails.setSecondaryFirstName(cnt.getCell(col, row).getContents());
					if(col == 17) 
						contactDetails.setSecondaryLastName(cnt.getCell(col, row).getContents());
					if(col == 18) 
						contactDetails.setSecondaryTitle(cnt.getCell(col, row).getContents());
					if(col == 19) 
						contactDetails.setSecondaryBusinessEmail(cnt.getCell(col, row).getContents());
					if(col == 20) 
						contactDetails.setSecondaryalternateEmail(cnt.getCell(col, row).getContents());
					if(col == 21) 
						contactDetails.setSecondaryExtn(cnt.getCell(col, row).getContents());
					if(col == 22)  
						contactDetails.setSecondaryMobile(cnt.getCell(col, row).getContents());						
					}
				cntList.add(contactDetails);
				}
			}
		
		Random random = new Random();
		int ranRow = random.nextInt(cnt.getRows()-1)+1; 
				
		if(cntList.size() > 0)
		{
			ContactDetails contactDetails = cntList.get(ranRow);
			
			//	Company Name
			companyName = contactDetails.getCompanyName();
			enterText(cntCompanyName, companyName);
			
			//	Contact Id
			enterText(cntContactId, contactDetails.getContactID());
			
			//	Contact Type
			click(cntContactTypeDDArrow);
			getTotalValuesIndd(cntContactTypeDDTtl);
			if(totalDDValCount > 1)
			{
				Random contType = new Random();
				int rcntTyp = contType.nextInt(totalDDValCount-1)+1;
				contactType = driver.findElement(By.id("cnt_type_chzn_o_"+rcntTyp)).getText();
				enterText(cntContactTypeSearchBox, contactType);
				enterKeyInKyBord(cntContactTypeSearchBox);
			}			
			
			//	Contact Status
			click(cntContactStatusDDArrow);
			getTotalValuesIndd(cntContactStatusDDTtl);
			if(totalDDValCount > 1)
			{
				Random contStatus = new Random();
				int rcntstat = contStatus.nextInt(totalDDValCount-1)+1;
				contactStatus = driver.findElement(By.id("contact_status_chzn_o_"+rcntstat)).getText();
				enterText(cntContactStatusSearchBox, contactStatus);
				enterKeyInKyBord(cntContactStatusSearchBox);
			}			
			
			//	Address
			contactAddress = contactDetails.getAddress();
			enterText(cntAddress, contactAddress);
			
			//	Country
			click(cntCountryDDArrow);
			enterText(cntCountrySearchBox, contactDetails.getCountry());
			enterKeyInKyBord(cntCountrySearchBox);
			
			//	State
			click(cntStateDDArrow);
			enterText(cntStateSearchBox, contactDetails.getState());
			enterKeyInKyBord(cntStateSearchBox);
			thread();
			
			//	City
			enterText(cntCity, contactDetails.getCity());
			
			//	Zip Code
			enterText(cntZipcode, contactDetails.getZipCode());
			
			//	Service Area
			click(cntServiceAreaDDArrow);
			getTotalValuesIndd(cntServiceAreaDDTtl);
			Random contServiceArea = new Random();
			if(totalDDValCount > 1)
			{
				int rcntserArea = contServiceArea.nextInt(totalDDValCount-1)+1;
				String contactServiceArea = driver.findElement(By.id("service_area_chzn_o_"+rcntserArea)).getText();
				enterText(cntServiceAreaSearchBox, contactServiceArea);
				enterKeyInKyBord(cntServiceAreaSearchBox);
			}			
								
			//	Phone
			contactPhoneNo = contactDetails.getPhone();
			enterText(cntPhone, contactPhoneNo);
				
			//Location 
			click(cntLocationDDArrow);
			thread();
			getTotalValuesIndd(cntLocationDDTtl);
			Random rndLoc=new Random();
			int rLoc=rndLoc.nextInt(totalDDValCount-1)+1;
			if(rLoc>0)
			{
				String contactLocation=driver.findElement(By.id("facility_id_chzn_o_"+rLoc)).getText();
				enterText(cntLocationSearchBox,contactLocation);
				enterKeyInKyBord(cntLocationSearchBox);
			}
						
			
			//	Comments
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);
				
			enterText(scEdtrCommentsField, contactDetails.getComments());
			thread();		
			switchBackFromFrame();
				
			//	Primary First Name
			primaryFirstName = contactDetails.getPrimaryFirstName();
			enterText(cntPmyFirstName, primaryFirstName);
			thread();
				
			//	Primary Last Name
			primaryLastName = contactDetails.getPrimaryLastName();
			enterText(cntPmyLastName, primaryLastName);
			thread();
				
			//	Primary Title
			enterText(cntPmyTitle, contactDetails.getPrimaryTitle());
					
			//	Primary Business Email
			primaryBusinessMail = contactDetails.getPrimaryBusinessEmail();
			enterText(cntPmyBusinessEmail, primaryBusinessMail);
							
			//	Primary Alternate Email
			enterText(cntPmyAlternateEmail, contactDetails.getPrimaryalternateEmail());
			thread();
			
			//
			primaryPhoneNo = contactDetails.getPhone();
			enterText(cntPmyPhone, primaryPhoneNo);
						
			//	Primary Extn
			primaryExtn = contactDetails.getPrimaryExtn();
			enterText(cntPmyPhoneExtn, primaryExtn);
							
			//	Primary Mobile No
			primaryMobile = contactDetails.getPrimaryMobile();
			enterText(cntPmyMobileNo, primaryMobile);
			thread();
				
				
			//	Secondary First Name
			enterText(cntSecFirstName, contactDetails.getSecondaryFirstName());
					
			//	Secondary Last Name
			enterText(cntSecLastName, contactDetails.getSecondaryLastName());
			thread();
				
			//	Secondary Title
			enterText(cntSecTitle, contactDetails.getSecondaryTitle());
							
			//	Secondary Business Email
			enterText(cntSecBusinessEmail, contactDetails.getSecondaryBusinessEmail());
							
			//	Secondary Alternate Email
			enterText(cntSecAlternateEmail, contactDetails.getSecondaryalternateEmail());
			thread();
			
			//Secondary Phone No
			secPhoneNo = contactDetails.getPhone();
			enterText(cntSecPhone, secPhoneNo);
						
			//	Secondary Extn
			enterText(cntSecPhoneExtn, contactDetails.getSecondaryExtn());
							
			//	Secondary Mobile No
			enterText(cntSecMobileNo, contactDetails.getSecondaryMobile());
			thread();
			cntList.remove(0);
	//	}
		
		click(cntSubmitBtn);
		thread();
	}
		try
		{
			getObjectText(msgNotificationBar);
			if(getActualObjectTxt.contains("already exist"))
			{
				click(cntClearBtn);
				thread();
				addContactDetails();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			getObjectText(stepValidatnMsg);
			if(getActualObjectTxt.contains("is required."))
			{
				click(cntClearBtn);
				thread();
				addContactDetails();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		//		DON'T UN COMMENT BELOW CODE		//
		/*ArrayList<Integer> cntList = new ArrayList<Integer>();
		
		for(int row=1; row<cnt.getRows(); row++)
		{
			cntList.add(row);
		}
		
		Collections.shuffle(cntList);
		
		for(int i=0; i<1; i++)
		{
			for(int col=0; col<cnt.getColumns(); col++)
			{
				//	Company Name
				companyName = cnt.getCell(col, cntList.get(i)).getContents();
				enterText(cntCompanyName, companyName);
				
				//	Contact Id
				enterText(cntContactId, cnt.getCell(col+1, cntList.get(i)).getContents());
				
				//	Contact Type
				click(cntContactTypeDDArrow);
				getTotalValuesIndd(cntContactTypeDDTtl);
				Random contType = new Random();
				int rcntTyp = contType.nextInt(totalDDValCount-1)+1;
				contactType = driver.findElement(By.id("cnt_type_chzn_o_"+rcntTyp)).getText();
				enterText(cntContactTypeSearchBox, contactType);
				enterKeyInKyBord(cntContactTypeSearchBox);
				
				//	Contact Status
				click(cntContactStatusDDArrow);
				getTotalValuesIndd(cntContactStatusDDTtl);
				Random contStatus = new Random();
				int rcntstat = contStatus.nextInt(totalDDValCount-1)+1;
				contactStatus = driver.findElement(By.id("contact_status_chzn_o_"+rcntstat)).getText();
				enterText(cntContactStatusSearchBox, contactStatus);
				enterKeyInKyBord(cntContactStatusSearchBox);
				
				//	Address
				contactAddress = cnt.getCell(col+2, cntList.get(i)).getContents();
				enterText(cntAddress, contactAddress);
				
				//	Country
				click(cntCountryDDArrow);
				enterText(cntCountrySearchBox, cnt.getCell(col+3, cntList.get(i)).getContents().trim());
				enterKeyInKyBord(cntCountrySearchBox);
				
				//	State
				click(cntStateDDArrow);
				enterText(cntStateSearchBox, cnt.getCell(col+4, cntList.get(i)).getContents().trim());
				enterKeyInKyBord(cntStateSearchBox);
				
				//	City
				enterText(cntCity, cnt.getCell(col+5, cntList.get(i)).getContents());
				
				//	Zip Code
				enterText(cntZipcode, cnt.getCell(col+6, cntList.get(i)).getContents());
				
				//	Service Area
				click(cntServiceAreaDDArrow);
				getTotalValuesIndd(cntServiceAreaDDTtl);
				Random contServiceArea = new Random();
				int rcntserArea = contServiceArea.nextInt(totalDDValCount-1)+1;
				String contactServiceArea = driver.findElement(By.id("service_area_chzn_o_"+rcntserArea)).getText();
				enterText(cntServiceAreaSearchBox, contactServiceArea);
				enterKeyInKyBord(cntServiceAreaSearchBox);
								
				//	Phone
				contactPhoneNo = cnt.getCell(col+7, cntList.get(i)).getContents();
				enterText(cntPhone, contactPhoneNo);
				
				//	Comments
				WebElement ifram = driver.findElement(scEdtrFrame);
				driver.switchTo().frame(ifram);
				
				enterText(scEdtrCommentsField, cnt.getCell(col+8, cntList.get(i)).getContents().trim());
				thread();		
				switchBackFromFrame();
				
				//	Primary First Name
				primaryFirstName = cnt.getCell(col+9, cntList.get(i)).getContents();
				enterText(cntPmyFirstName, primaryFirstName);
				
				//	Primary Last Name
				primaryLastName = cnt.getCell(col+10, cntList.get(i)).getContents();
				enterText(cntPmyLastName, primaryLastName);
				
				//	Primary Title
				enterText(cntPmyTitle, cnt.getCell(col+11, cntList.get(i)).getContents());
				
				//	Primary Business Email
				primaryBusinessMail = cnt.getCell(col+12, cntList.get(i)).getContents().trim();
				enterText(cntPmyBusinessEmail, primaryBusinessMail);
				
				//	Primary Alternate Email
				enterText(cntPmyAlternateEmail, cnt.getCell(col+13, cntList.get(i)).getContents().trim());
				
				//	Primary Extn
				primaryExtn = cnt.getCell(col+14, cntList.get(i)).getContents();
				enterText(cntPmyPhoneExtn, PrimaryExtn);
				
				//	Primary Mobile No
				primaryMobile = cnt.getCell(col+15, cntList.get(i)).getContents();
				enterText(cntPmyMobileNo, primaryMobile);
				
				
				//	Secondary First Name
				enterText(cntSecFirstName, cnt.getCell(col+16, cntList.get(i)).getContents());
				
				//	Secondary Last Name
				enterText(cntSecLastName, cnt.getCell(col+17, cntList.get(i)).getContents());
				
				//	Secondary Title
				enterText(cntSecTitle, cnt.getCell(col+18, cntList.get(i)).getContents());
				
				//	Secondary Business Email
				enterText(cntSecBusinessEmail, cnt.getCell(col+19, cntList.get(i)).getContents().trim());
				
				//	Secondary Alternate Email
				enterText(cntSecAlternateEmail, cnt.getCell(col+20, cntList.get(i)).getContents().trim());
				
				//	Secondary Extn
				enterText(cntSecPhoneExtn, cnt.getCell(col+21, cntList.get(i)).getContents());
				
				//	Secondary Mobile No
				enterText(cntSecMobileNo, cnt.getCell(col+22, cntList.get(i)).getContents());
				thread();
				takeScreenshot();
				break;
			}		
		}*/			
	}
	
	@Test(priority=3)
	void verifyContactAddedSuccessgullyMsg() throws IOException, InterruptedException
	{
	    Thread.sleep(1000);
		getObjectText(msgNotificationBar);
		takeScreenshot();
		Thread.sleep(1000);
		verifyAssertEquals(primaryFirstName.trim()+" "+primaryLastName.trim()+" Successfully Added", getActualObjectTxt);
		Thread.sleep(1000);
	}
	
	@Test(priority=4)
	void verifyAddedContactDetailsInListView() throws InterruptedException
	{
		getTotalValuesIndd(cntContactsListViewTtl);
		
		for(int i=1; i<totalDDValCount; i++)
		{
			String vfyAdedContName = driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+i+"]/td[2]")).getText();
			
			if(vfyAdedContName.contains(primaryFirstName+" "+primaryLastName))
			{
				for(int j=2; j<10; j++)
				{
					String getAddedContactDetails = driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+i+"]/td["+j+"]")).getText();
					
					if(j == 2)
						verifyAssertEquals(primaryFirstName+" "+primaryLastName, getAddedContactDetails);	//	Contact Person Name
					else if(j == 3)						
						verifyAssertEquals(companyName, getAddedContactDetails);	//	Company Name
					else if(j == 4)						
						verifyAssertEquals(contactType, getAddedContactDetails);		//	Contact Type
					else if(j == 5)						
						verifyAssertEquals(contactPhoneNo+" "+"["+primaryExtn+"]", getAddedContactDetails);		//	Phone & Extn
					else if(j == 6)						
						verifyAssertEquals("+1"+primaryMobile, getAddedContactDetails);		//	Mobile No
					else if(j  == 7)						
						verifyAssertEquals(primaryBusinessMail, getAddedContactDetails);		//	E-Mail
					else if(j == 8)						
						verifyAssertEquals(contactAddress, getAddedContactDetails);		//	Address
					else if(j == 9)						
						verifyAssertEquals(contactStatus, getAddedContactDetails);		//	Contact Status
				}
			}
		}			
		thread();
	}
	
	@Test(priority=5)
	void verifyContactNameInMapLocationsPp()
	{
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Locations To "+primaryFirstName.trim()+" "+primaryLastName.trim(), getActualObjectTxt);
	}
	
	@Test(priority=6)
	void mapLocationsToContacts() throws InterruptedException, IOException
	{
		doMappingt();
		takeScreenshot();
		
		click(mapSubmitBtn);
		thread();
	}
	
	@Test(priority=7)
	void verifyLocSuccessMsg() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(primaryFirstName.trim()+" "+primaryLastName.trim()+" successfully mapped to Locations", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=8)
	void searchForMappedLocations() throws IOException, InterruptedException
	{
getTotalValuesIndd(cntRltnLocCnt);
		
		//	Search Valid Contact Name
		Random loc = new Random();
		int rLoc = loc.nextInt(totalDDValCount-1)+1;
		
		if(rLoc==0)
		{
			rLoc=+1;
		}
		
		String location = driver.findElement(By.xpath("//table[@id='rel_facilities']/tbody/tr["+rLoc+"]/td")).getText().substring(4);
		enterText(cntRltnLocSearchBox, location);
		getObjectText(cntRltnLocSrchResult);
		verifyAssertEquals(location, getActualObjectTxt.substring(4));
		takeScreenshot();
		clear(cntRltnLocSearchBox);
		enterKeyInKyBord(cntRltnLocSearchBox);
		thread();
		
		//	Search Invalid Contact Name
		enterText(cntRltnLocSearchBox, "Inv contact Groups");
		getObjectText(cntRltnLocSrchResult);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(cntRltnLocSearchBox);
		enterKeyInKyBord(cntRltnLocSearchBox);
		thread();
	}
	
	
	@Test(priority=9)
	void verifyContactNameInMapTasksPp() throws InterruptedException
	{
		click(tasksTb);
		thread();
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Tasks To "+primaryFirstName.trim()+" "+primaryLastName.trim(), getActualObjectTxt);
	}
	
	@Test(priority=10)
	void mapTaskToContacts() throws InterruptedException, IOException
	{
		doMappingt();
		takeScreenshot();
		
		click(mapSubmitBtn);
		thread();
	}
	
	@Test(priority=11)
	void verifyTaskSuccMapedToContactsMsg() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(primaryFirstName.trim()+" "+primaryLastName.trim()+" successfully mapped to Tasks", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=12)
	void searchMappedTasks() throws IOException, InterruptedException
	{
		getTotalValuesIndd(cntRltnTasksCnt);
		
		//	Search Valid Task Name
		Random tskGrpNm = new Random();
		int rtskNm = tskGrpNm.nextInt(totalDDValCount-1)+1;		
		
		String taskNm = driver.findElement(By.xpath("//table[@id='rel_task']//tr["+rtskNm+"]/td")).getText().substring(4);
		enterText(cntRltnTasksSearchBox, taskNm);
		getObjectText(cntRltnTasksSrchRslt);
		thread();
		verifyAssertEquals(taskNm, getActualObjectTxt.substring(4));
		takeScreenshot();
		clear(cntRltnTasksSearchBox);
		enterKeyInKyBord(cntRltnTasksSearchBox);
		thread();
		
		//	Search Invalid Task Name
		enterText(cntRltnTasksSearchBox, "Inv Task Name");
		getObjectText(cntTskNoMatchngRecFound);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(cntRltnTasksSearchBox);
		enterKeyInKyBord(cntRltnTasksSearchBox);
		thread();
	}
	
	@Test(priority=13)
	void verifyContactNameInContactGroupsPp()
	{
		click(cntContactGroupsTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Contact Groups To "+primaryFirstName.trim()+" "+primaryLastName.trim(), getActualObjectTxt);
	}
	
	@Test(priority=14)
	void mapContactGroupsToContacts() throws InterruptedException, IOException
	{
		doMappingt();
		takeScreenshot();
		
		click(mapSubmitBtn);
		thread();
	}
	
	@Test(priority=15)
	void verifyContactGrpSuccMapedToContactsMsg() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(primaryFirstName.trim()+" "+primaryLastName.trim()+" successfully mapped to Contact Groups", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=16)
	void searchMappedContactGroups() throws IOException, InterruptedException
	{		
		getTotalValuesIndd(cntRltnContactGroupscnt);
		
		//	Search Valid Contact Group Name
		Random cntgrp = new Random();
		int rCntgrp = cntgrp.nextInt(totalDDValCount-1)+1;	
		
		String ContactGroups = driver.findElement(By.xpath("//table[@id='rel_contact_grp']/tbody/tr["+rCntgrp+"]/td")).getText().substring(4);
		enterText(cntRltnContactGroupsSearchBox, ContactGroups);
		getObjectText(cntRltnContactGroupsSrchRslt);
		verifyAssertEquals(ContactGroups, getActualObjectTxt.substring(4));
		takeScreenshot();
		clear(cntRltnContactGroupsSearchBox);
		enterKeyInKyBord(cntRltnContactGroupsSearchBox);
		thread();
		
		//	Search Invalid Contact Name
		enterText(cntRltnContactGroupsSearchBox, "Inv contact Groups");
		getObjectText(cntCntGrpNoMatchngRecFound);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(cntRltnContactGroupsSearchBox);
		enterKeyInKyBord(cntRltnContactGroupsSearchBox);
		thread();
	}
	
	/*@Test(priority=17)
    void Documentupload() throws IOException, InterruptedException, AWTException
    {
		
		webElement(docuemntTb);
		scrollInnerScrollBar(webelementFrame);
		
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
	
	@Test(priority=18)
	void verifyContactNameInListView() throws IOException, InterruptedException
	{
		getTotalValuesIndd(cntContactsListViewTtl);
		Random random = new Random();
		int getRandomCntNm = random.nextInt(totalDDValCount-1)+1;
		WebElement rcntName = driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+getRandomCntNm+"]/td[2]"));
		rcntName.click();
		String  cntFstName= rcntName.getText();
		String cntLName=driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+getRandomCntNm+"]/td[3]")).getText();
		
		String contactNameFrRltnTitle=cntFstName+" "+cntLName;
		getObjectText(cntRltnRelationshipTitle);
		String[] name=getActualObjectTxt.split("for ");		
		verifyAssertEquals(contactNameFrRltnTitle, name[1]);
		//verifyAssertEquals(contactNameFrRltnTitle,getActualObjectTxt);
		thread();	
		
	}
	
	@Test(priority=19)
	void searchValidContactName() throws IOException, InterruptedException
	{
		getObjectText(cntContactsListViewTtl);
		Random random = new Random();
		int rcntName = random.nextInt(totalDDValCount-1)+1;
		String getCntFstNameForSearch = driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+rcntName+"]/td[2]")).getText();
		String getCntLastNameForSearch=driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+rcntName+"]/td[3]")).getText();
		String getCntNameForSearch=getCntFstNameForSearch.concat(" ").concat(getCntLastNameForSearch);
		
		enterText(cntContactsSearchBox, getCntNameForSearch);
		enterKeyInKyBord(cntContactsSearchBox);
		thread();
		
		try
		{
			getObjectText(cntContactListViewFstName);
			String fstName=getActualObjectTxt;
			thread();
			getObjectText(cntContactListViewLstName);
			//System.out.println(getCntNameForSearch+" Expected----Actual "+getActualObjectTxt);
			verifyAssertEquals(getCntNameForSearch, fstName+" "+getActualObjectTxt);
			takeScreenshot();
					
			clear(cntContactsSearchBox);
			enterKeyInKyBord(cntContactsSearchBox);
			thread();
		}
		catch(Exception e)
		{
			System.out.println("***** For valid Contact Name search is not working in Contacts list view");
		}
		
	}
	
	@Test(priority=20)
	void searchInvalidContactName() throws IOException, InterruptedException
	{
		enterText(cntContactsSearchBox, "Invalid Contact Name");
		
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
				
		clear(cntContactsSearchBox);
		enterKeyInKyBord(cntContactsSearchBox);
		thread();
	}
	
	@Test(priority=21)
	void verifySelectAllChkBox() throws IOException, InterruptedException
	{
		click(cntSelectAllChkBox);
		takeScreenshot();
		
		getTotalValuesIndd(cntContactsListViewTtl);
		for(int i=1; i<=totalDDValCount; i++)
		{
			boolean chkd = driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+i+"]/td/div/span/input")).isEnabled();
			Assert.assertEquals(true, chkd);
		}		
		click(cntSelectAllChkBox);
	}
	
	@Test(priority=22)
	void verifyContactsPagination()
	{
		getTotalValuesIndd(cntContactsLstViewPagination);
		
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
				driver.findElement(By.xpath("//div[@id='contacts_table_wrapper']//div[@class='dataTables_paginate paging_bootstrap pagination pagination-right']/ul/li["+randomPge+"]/a")).click();
				thread();
				takeScreenshot();
				click(pagiStartArw);
				thread();
			}	
			else
			{
				System.out.println("*****Pagination is not available in Contacts List View. Table has 10 or less than 10 records*****");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=23)
	void verifyShowEntriesDropdown() throws InterruptedException, IOException
	{
		try
		{
			getObjectText(cntListViewRecsInfo);
			
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) > 10)
			{
				selectTextFromDropdown(cntListViewLength , "25");
				thread();
				takeScreenshot();
				
				selectTextFromDropdown(cntListViewLength , "10");
				thread();
			}
		}
		catch(WebDriverException e)
		{
			System.out.println("Contacts list view has 10 or less than 10 records. So values are not selected from show entries drop down");
		}
	}
	
	@Test(priority=24)
	void verifyClearBtnInEditContactNameScrn() throws InterruptedException, IOException
	{
		getTotalValuesIndd(cntContactsListViewTtl);
	//	Random random = new Random();
		//int rcntNm = random.nextInt(totalDDValCount-1)+1;
		
	//	driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+rcntNm+"]/td[2]")).click();
	//	driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+rcntNm+"]/td[11]/a[1]")).click();
		
		driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr[1]/td[2]")).click();
		driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr[1]/td[11]/a[1]")).click();
		thread();
		
		click(cntClearBtn);
		thread();
		takeScreenshot();
		
		//	Company Name
		getAttributePh(cntCompanyName);
		verifyAssertEquals(phCompanyName, getAttribtePh);
		
		//	Contact Id
		getAttributePh(cntContactId);
		verifyAssertEquals(phContactId, getAttribtePh);
		
		//	Contact Type
		getObjectText(cntContactTypeDefTxt);
		verifyAssertEquals(defContactType, getActualObjectTxt);
		
		//	Contact Status
		getObjectText(cntContactStatusDefTxt);
		verifyAssertEquals(defContactStatus, getActualObjectTxt);
		
		//	Address
		getAttributePh(cntAddress);
		verifyAssertEquals(phAddress, getAttribtePh);
		
		//	Country
		getObjectText(cntCountryDefTxt);
		verifyAssertEquals(defCountry, getActualObjectTxt);
		thread();
		
		//	State
		getObjectText(cntStateDefTxt);
		verifyAssertEquals(defState, getActualObjectTxt);
		
		//	City
		getAttributePh(cntCity);
		verifyAssertEquals(phCity, getAttribtePh);
		
		//	Zipcode
		getAttributePh(cntZipcode);
		verifyAssertEquals(phZipcode, getAttribtePh);
		
		//	Service Area
		getObjectText(cntServiceAreaDefTxt);
		verifyAssertEquals(defServiceArea, getActualObjectTxt);
		
		//	Phone
		getAttributePh(cntPhone);
		verifyAssertEquals(phPhone, getAttribtePh);
		
		//	Comments
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);
		
		getObjectText(scEdtrCommentsField);
		verifyAssertEquals("", getActualObjectTxt);
		thread();		
		switchBackFromFrame();
		
		//	Primary First Name
		getAttributePh(cntPmyFirstName);
		verifyAssertEquals(phFirstName, getAttribtePh);
		
		//	Primary Last Name
		getAttributePh(cntPmyLastName);
		verifyAssertEquals(phLastName, getAttribtePh);
		
		//	Primary Title
		getAttributePh(cntPmyTitle);
		verifyAssertEquals(phTitle, getAttribtePh);
		
		//	Primary Business Email
		getAttributePh(cntPmyBusinessEmail);
		verifyAssertEquals(phBusinessEmail, getAttribtePh);
		
		//	Primary Alternate Email
		getAttributePh(cntPmyAlternateEmail);
		verifyAssertEquals(phAlternateEmail, getAttribtePh);
		
		//	Primary Phone
		getAttributePh(cntPmyPhone);
		verifyAssertEquals(phPhone, getAttribtePh);
		
		//	Primary Phone Extn
		getAttributePh(cntPmyPhoneExtn);
		verifyAssertEquals(phExtn, getAttribtePh);
		
		//	Primary Mobile
		getAttributePh(cntPmyMobileNo);
		verifyAssertEquals(phMobile, getAttribtePh);
		
		//	Secondary First Name
		getAttributePh(cntSecFirstName);
		verifyAssertEquals(phFirstName, getAttribtePh);
			
		//	Secondary Last Name
		getAttributePh(cntSecLastName);
		verifyAssertEquals(phLastName, getAttribtePh);
			
		//	Secondary Title
		getAttributePh(cntSecTitle);
		verifyAssertEquals(phTitle, getAttribtePh);
			
		//	Secondary Business Email
		getAttributePh(cntSecBusinessEmail);
		verifyAssertEquals(phBusinessEmail, getAttribtePh);
		
		//	Secondary Alternate Email
		getAttributePh(cntSecAlternateEmail);
		verifyAssertEquals(phAlternateEmail, getAttribtePh);
			
		//	Secondary Phone
		getAttributePh(cntSecPhone);
		verifyAssertEquals(phPhone, getAttribtePh);
		
		//	Secondary Phone Extn
		getAttributePh(cntSecPhoneExtn);
		verifyAssertEquals(phExtn, getAttribtePh);
		
		//	Secondary Mobile
		getAttributePh(cntSecMobileNo);
		verifyAssertEquals(phMobile, getAttribtePh);
		
		thread();
	}
	
	@Test(priority=25)
	void editContactDetails() throws InterruptedException, IOException, BiffException
	{
		if(cntList.size() == 0) 
		{
			getContactsSheetFromExcel();
			
			for(int row = 1; row < cnt.getRows(); row++)
			{
				ContactDetails contactDetails = new ContactDetails();
				for(int col=0; col<cnt.getColumns();col++)
				{	
					if(col == 0) 
						contactDetails.setCompanyName(cnt.getCell(col, row).getContents());
					if(col == 1)
						contactDetails.setContactID(cnt.getCell(col, row).getContents());						
					if(col == 2)
						contactDetails.setAddress(cnt.getCell(col, row).getContents());
					if(col == 3) 
						contactDetails.setCountry(cnt.getCell(col, row).getContents());
					if(col == 4)
						contactDetails.setState(cnt.getCell(col, row).getContents());
					if(col == 5) 
						contactDetails.setCity(cnt.getCell(col, row).getContents());
					if(col == 6) 
						contactDetails.setZipCode(cnt.getCell(col, row).getContents());
					if(col == 7) 
						contactDetails.setPhone(cnt.getCell(col, row).getContents());
					if(col == 8) 
						contactDetails.setComments(cnt.getCell(col, row).getContents());
					if(col == 9) 
						contactDetails.setPrimaryFirstName(cnt.getCell(col, row).getContents());
					if(col == 10) 
						contactDetails.setPrimaryLastName(cnt.getCell(col, row).getContents());
					if(col == 11) 
						contactDetails.setPrimaryTitle(cnt.getCell(col, row).getContents());
					if(col == 12) 
						contactDetails.setPrimaryBusinessEmail(cnt.getCell(col, row).getContents());
					if(col == 13) 
						contactDetails.setPrimaryalternateEmail(cnt.getCell(col, row).getContents());
					if(col == 14) 
						contactDetails.setPrimaryExtn(cnt.getCell(col, row).getContents());
					if(col == 15) 
						contactDetails.setPrimaryMobile(cnt.getCell(col, row).getContents());
					if(col == 16) 
						contactDetails.setSecondaryFirstName(cnt.getCell(col, row).getContents());
					if(col == 17) 
						contactDetails.setSecondaryLastName(cnt.getCell(col, row).getContents());
					if(col == 18) 
						contactDetails.setSecondaryTitle(cnt.getCell(col, row).getContents());
					if(col == 19) 
						contactDetails.setSecondaryBusinessEmail(cnt.getCell(col, row).getContents());
					if(col == 20) 
						contactDetails.setSecondaryalternateEmail(cnt.getCell(col, row).getContents());
					if(col == 21) 
						contactDetails.setSecondaryExtn(cnt.getCell(col, row).getContents());
					if(col == 22)  
						contactDetails.setSecondaryMobile(cnt.getCell(col, row).getContents());						
					}
				cntList.add(contactDetails);
				}
			}
		
		Random random = new Random();
		int ranRow = random.nextInt(cnt.getRows()-1)+1; 
				
		if(cntList.size() > 0)
		{
			ContactDetails contactDetails = cntList.get(ranRow);
			
			//	Company Name
			modifiedCompanyName = contactDetails.getCompanyName();
			enterText(cntCompanyName, modifiedCompanyName);
			
			//	Contact Id
			enterText(cntContactId, contactDetails.getContactID());
			
			//	Contact Type
			click(cntContactTypeDDArrow);
			getTotalValuesIndd(cntContactTypeDDTtl);
			Random contType = new Random();
			int rcntTyp = contType.nextInt(totalDDValCount-1)+1;
			modifiedContactType = driver.findElement(By.id("cnt_type_chzn_o_"+rcntTyp)).getText();
			enterText(cntContactTypeSearchBox, modifiedContactType);
			enterKeyInKyBord(cntContactTypeSearchBox);
			
			//	Contact Status
			click(cntContactStatusDDArrow);
			getTotalValuesIndd(cntContactStatusDDTtl);
			Random contStatus = new Random();
			int rcntstat = contStatus.nextInt(totalDDValCount-1)+1;
			modifiedContactStatus = driver.findElement(By.id("contact_status_chzn_o_"+rcntstat)).getText();
			enterText(cntContactStatusSearchBox, modifiedContactStatus);
			enterKeyInKyBord(cntContactStatusSearchBox);
			
			//	Address
			modifiedContactAddress = contactDetails.getAddress();
			enterText(cntAddress, modifiedContactAddress);
			
			//	Country
			click(cntCountryDDArrow);
			enterText(cntCountrySearchBox, contactDetails.getCountry());
			enterKeyInKyBord(cntCountrySearchBox);
			Thread.sleep(100);
			
            //State
			click(cntStateDDArrow);
			getTotalValuesIndd(cntStateDDTtl);
			if(totalDDValCount > 1)
			{
			Random contState = new Random();
			int rcntstate = contState.nextInt(totalDDValCount-1)+1;
			String contactState = driver.findElement(By.id("state_chzn_o_"+rcntstate)).getText();
			enterText(cntStateSearchBox, contactState);
			enterKeyInKyBord(cntStateSearchBox);
			}
			
			//	City
			enterText(cntCity, contactDetails.getCity());
			
			//	Zip Code
			enterText(cntZipcode, contactDetails.getZipCode());
			
			//	Service Area
			click(cntServiceAreaDDArrow);
			getTotalValuesIndd(cntServiceAreaDDTtl);
			Random contServiceArea = new Random();
			int rcntserArea = contServiceArea.nextInt(totalDDValCount-1)+1;
			String contactServiceArea = driver.findElement(By.id("service_area_chzn_o_"+rcntserArea)).getText();
			enterText(cntServiceAreaSearchBox, contactServiceArea);
			enterKeyInKyBord(cntServiceAreaSearchBox);
								
			//	Phone
			modifiedContactPhoneNo = contactDetails.getPhone();
			enterText(cntPhone, modifiedContactPhoneNo);
			
			//Location 
			click(cntLocationDDArrow);
			thread();
			getTotalValuesIndd(cntLocationDDTtl);
			Random rndLoc=new Random();
			int rLoc=rndLoc.nextInt(totalDDValCount-1)+1;
			if(rLoc>0)
			{
				String contactLocation=driver.findElement(By.id("facility_id_chzn_o_"+rLoc)).getText();
				enterText(cntLocationSearchBox,contactLocation);
				enterKeyInKyBord(cntLocationSearchBox);
			}
			
				
			//	Comments
			WebElement ifram = driver.findElement(scEdtrFrame);
			driver.switchTo().frame(ifram);
				
			enterText(scEdtrCommentsField, contactDetails.getComments());
			thread();		
			switchBackFromFrame();
				
			//	Primary First Name
			modifiedPrimaryFirstName = contactDetails.getPrimaryFirstName();
			enterText(cntPmyFirstName, modifiedPrimaryFirstName);
				
			//	Primary Last Name
			modifiedPrimaryLastName = contactDetails.getPrimaryLastName();
			enterText(cntPmyLastName, modifiedPrimaryLastName);
				
			//	Primary Title
			enterText(cntPmyTitle, contactDetails.getPrimaryTitle());
			
			//	Primary Business Email
			modifiedPrimaryBusinessMail = contactDetails.getPrimaryBusinessEmail();
			enterText(cntPmyBusinessEmail, modifiedPrimaryBusinessMail);
				
			//	Primary Alternate Email
			enterText(cntPmyAlternateEmail, contactDetails.getPrimaryalternateEmail());
			
			//Primary Phone No
			modifiedPmyPhoneNo = contactDetails.getPhone();
			enterText(cntPmyPhone, modifiedPmyPhoneNo);
						
			//	Primary Extn
			modifiedPrimaryExtn = contactDetails.getPrimaryExtn();
			enterText(cntPmyPhoneExtn, modifiedPrimaryExtn);
							
			//	Primary Mobile No
			modifiedPrimaryMobile = contactDetails.getPrimaryMobile();
			enterText(cntPmyMobileNo, modifiedPrimaryMobile);
			thread();
				
				
			//	Secondary First Name
			enterText(cntSecFirstName, contactDetails.getSecondaryFirstName());
						
			//	Secondary Last Name
			enterText(cntSecLastName, contactDetails.getSecondaryLastName());
			
				
			//	Secondary Title
			enterText(cntSecTitle, contactDetails.getSecondaryTitle());
				
			//	Secondary Business Email
			enterText(cntSecBusinessEmail, contactDetails.getSecondaryBusinessEmail());
				
			//	Secondary Alternate Email
			enterText(cntSecAlternateEmail, contactDetails.getSecondaryalternateEmail());
			thread();
			
			//Secondary Phone No
			modifiedSecPhoneNo = contactDetails.getPhone();
			enterText(cntSecPhone, modifiedSecPhoneNo);
				
			//	Secondary Extn
			enterText(cntSecPhoneExtn, contactDetails.getSecondaryExtn());
				
			//	Secondary Mobile No
			enterText(cntSecMobileNo, contactDetails.getSecondaryMobile());
			cntList.remove(0);
		}	
		
		click(cntSubmitBtn);
		thread();
		
		try
		{
			getObjectText(msgNotificationBar);
			if(getActualObjectTxt.contains("already exist"))
			{
				click(cntClearBtn);
				editContactDetails();
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test(priority=26)
	void verifyContactNameUpdatedSuccMsg() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(modifiedPrimaryFirstName.trim()+" "+modifiedPrimaryLastName.trim()+" Successfully Updated", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=27)
	void verifyEditedContactDetailsInListView() throws InterruptedException
	{
		getTotalValuesIndd(cntContactsListViewTtl);
		
		for(int i=1; i<totalDDValCount; i++)
		{
			String vfyEditdContName = driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+i+"]/td[2]")).getText();
			
			if(vfyEditdContName.contains(modifiedPrimaryFirstName.trim()+" "+modifiedPrimaryLastName.trim()))
			{
				for(int j=2; j<10; j++)
				{
					String getmodifiedContactDetails = driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+i+"]/td["+j+"]")).getText();
					
					if(j == 2)
						verifyAssertEquals(modifiedPrimaryFirstName+" "+modifiedPrimaryLastName, getmodifiedContactDetails);	//	Contact Person Name
					else if(j == 3)
						verifyAssertEquals(modifiedCompanyName, getmodifiedContactDetails);		//	Company Name
					else if(j == 4)
						verifyAssertEquals(modifiedContactType, getmodifiedContactDetails);	//	Contact Type
					else if(j == 5)
						verifyAssertEquals(modifiedContactPhoneNo+" "+"["+modifiedPrimaryExtn+"]", getmodifiedContactDetails);	//	Phone & Extn					
					else if(j == 6)
						verifyAssertEquals("+1"+modifiedPrimaryMobile, getmodifiedContactDetails);	//	Mobile No
					else if(j  == 7)
						verifyAssertEquals(modifiedPrimaryBusinessMail, getmodifiedContactDetails);	//	E-Mail
					else if(j == 8)
						verifyAssertEquals(modifiedContactAddress, getmodifiedContactDetails);	//	Address
					else if(j == 9)
						verifyAssertEquals(modifiedContactStatus, getmodifiedContactDetails);		//	Contact Status
				}
			}
		}			
		thread();
		thread();
	}
	
	@Test(priority=28)
	void verifyViewPage()throws IOException, InterruptedException
	{
		click(cntViewBtn);
		takeScreenshot();
     	getObjectText(cntViewPopup);
     	thread();
     	webElement(cntViewCloseBtn);
		scrollInnerScrollBar(webelementFrame);     	
     	click(cntViewCloseBtn);
     	Thread.sleep(1000);
	}
	
	
	/*@Test(priority=29)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
}
