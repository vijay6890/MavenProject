package Scripts;


import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static UIWrappers.UIObjects.*;
import static ObjectRepository.BusinessFunctions.bsfSubmitBtn;
import static ObjectRepository.Contacts.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;
import static Config.TakScreenshot.*;

/********************************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Delete Single & Multiple Contact Name
 * Test 2: Verify Deleted successfully message for both
 * Test 3: Verify Select Records to Perform Edit message
 * Test 4: Check Select Records to Perform Delete message
 * Test 5: Perform Mass Edit
 * Test 6: Verify Mass Edit updated successfully message
 * Test 7: Check Mass Edit details in list view
 * Test 8: Check Contacts Help Document pop up
 * 
*********************************************************************************************************************/

public class Cnt_DeleteMassEdit extends Page{

	LoginLogout ll = new LoginLogout();
	Loc_AddnDeleteMultiple loc_AddnDeleteMultiple = new Loc_AddnDeleteMultiple();
	Cnt_AddNewContact cnt_AddNewContact = new Cnt_AddNewContact();
	
	private String masContactType, masContactStatus, masAddress, masCountry, masState, masZipcode, masServiceArea, masCity;
	private String none = "None";
	private String selectCountry = "Select Country";
	private String selectState = "Select State";
	private String selectAddNewServiceArea = "Select/Add New Service Area";
	
   /* @Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}  */
	
	@Test(priority=1)
	void deleteSingleContactName() throws InterruptedException, IOException, BiffException
	{
		cnt_AddNewContact.verifyAddContactDtlsPage();
		cnt_AddNewContact.addContactDetails();
		Thread.sleep(1000);
		
		getTotalValuesIndd(cntContactsListViewTtl);
		for(int i=1; i<2; i++)
		{
			String getAdedCntNm = driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+i+"]/td[2]")).getText();
			thread();
			
			//if(getAdedCntNm.equals(cnt_AddNewContact.primaryFirstName+" "+cnt_AddNewContact.primaryLastName))
			if(getAdedCntNm.equals(cnt_AddNewContact.primaryFirstName))
			{
				driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+i+"]/td[11]/a[2]")).click();	//	Delete button
				thread();
				verifyAssert(deleteConfPopup);
				thread();
				getObjectText(deleteConfPpMessage);
				//verifyAssertEquals("Confirm to remove Contact "+cnt_AddNewContact.primaryFirstName+" "+cnt_AddNewContact.primaryLastName+"?", getActualObjectTxt);
				verifyAssertEquals("Confirm to remove Contact "+cnt_AddNewContact.primaryFirstName+" "+cnt_AddNewContact.primaryLastName+"?", getActualObjectTxt);
				
				takeScreenshot();
				thread();
				click(delConfOkBtn);
				thread();
			}
		}	
	}
	
	@Test(priority=2)
	void verifyContactNameDeletedSuccMsg() throws IOException, InterruptedException
	{
		Thread.sleep(1000);
		getObjectText(msgNotificationBar);
		//verifyAssertEquals(cnt_AddNewContact.primaryFirstName+" "+cnt_AddNewContact.primaryLastName+" Is Deleted", getActualObjectTxt);
		verifyAssertEquals(cnt_AddNewContact.primaryFirstName+" "+cnt_AddNewContact.primaryLastName+" Is Deleted", getActualObjectTxt);
		takeScreenshot();
		Thread.sleep(1000);
	}
	
	@Test(priority=3, invocationCount=3)
	void addMultipleContactName() throws InterruptedException, BiffException, IOException
	{
		Thread.sleep(1000);
		cnt_AddNewContact.verifyAddContactDtlsPage();
		cnt_AddNewContact.addContactDetails();
		Thread.sleep(1000);
	}
	
	@Test(priority=4)
	void deleteMultipleContactNames() throws IOException, InterruptedException
	{
		getTotalValuesIndd(cntContactsListViewTtl);
		
		for(int j=1; j<4; j++)
		{
			driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+j+"]/td//input")).click();
			thread();
		}
		
		click(cntOvrDeleteBtn);
		verifyAssert(deleteConfPopup);
		Thread.sleep(1000);
		getObjectText(deleteConfPpMessage);
		Thread.sleep(1000);
		verifyAssertEquals("Confirm to remove 3 record(s) from Contacts?", getActualObjectTxt);
		takeScreenshot();
		Thread.sleep(1000);
		click(delConfOkBtn);
		Thread.sleep(1000);
		getObjectText(msgNotificationBar);
		verifyAssertEquals("3 records deleted successfully", getActualObjectTxt);
		takeScreenshot();
		Thread.sleep(1000);
	}
	
	@Test(priority=5)
	void verifySelectRecordsToPerformEditMsg() throws InterruptedException, IOException
	{
		thread();
		click(cntOvrMassEditBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordstoPerformEditOperation, getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=6)
	void verifySelectRecordsToPerformDeleteMsg() throws InterruptedException, IOException
	{
		click(cntOvrDeleteBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordsToPerformDelete, getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=7)
	void massEdit() throws InterruptedException, BiffException, IOException
	{
		getContactsSheetFromExcel();
		
		for(int i=1; i<3; i++)
		{
			driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+i+"]/td//input")).click();
		}
		
		click(cntOvrMassEditBtn);
		
		//	Contact Type
		click(cntmEdtContactTypeDDArw);
		thread();
		getTotalValuesIndd(cntmEdtContactTypeCnt);
		Random r = new Random();
		int rcntTyp = r.nextInt(totalDDValCount-1)+1;
		
		masContactType = driver.findElement(By.id("contacts_type_chzn_o_"+rcntTyp)).getText();
		enterText(cntmEdtContactTypeSearchBox, masContactType);		
		enterKeyInKyBord(cntmEdtContactTypeSearchBox);
		thread();
		
		//	Contact Status
		click(cntmEdtContactStatusDDArw);
		thread();
		getTotalValuesIndd(cntmEdtContactStatusCnt);
		Random rContactStatus = new Random();
		int rcntStat = rContactStatus.nextInt(totalDDValCount-1)+1;
		
		masContactStatus = driver.findElement(By.id("contacts_status_chzn_o_"+rcntStat)).getText();
		enterText(cntmEdtContactStatusSearchBox, masContactStatus);
		enterKeyInKyBord(cntmEdtContactStatusSearchBox);
		thread();
		
		//	Country
		Random ranRow = new Random();
		int rrow = ranRow.nextInt(cnt.getRows()-1)+1;
		
		click(cntmEdtCountryDDArw);
		masCountry = cnt.getCell(3, rrow).getContents();
		enterText(cntmEdtCountrySearchBox, masCountry);
		enterKeyInKyBord(cntmEdtCountrySearchBox);
		
		//	State
		click(cntmEdtStateDDArw);
		masState = cnt.getCell(4, rrow).getContents();
		thread();
		enterText(cntmEdtStateSearchBox, masState);
		thread();
		enterKeyInKyBord(cntmEdtStateSearchBox);
		
		//	Service Area
		click(cntmEdtServiceAreaDDArw);
		
		getTotalValuesIndd(cntmEdtServiceAreaCnt);
		Random rServiceArea = new Random();
		int rcntSerArea = rServiceArea.nextInt(totalDDValCount-1)+1;
		
		masServiceArea = driver.findElement(By.id("contacts_service_chzn_o_"+rcntSerArea)).getText();
		enterText(cntmEdtServiceAreaSearchBox, masServiceArea);
		enterKeyInKyBord(cntmEdtServiceAreaSearchBox);
		thread();
		
		//	Address
		masAddress = cnt.getCell(2, rrow).getContents();
		enterText(cntmEdtAddress, masAddress);
				
		//	City
		masCity = cnt.getCell(5, rrow).getContents();
		enterText(cntmEdtCity, masCity);
				
		//	Zip code
		masZipcode = cnt.getCell(6, rrow).getContents();
		enterText(cntmEdtZipcode, masZipcode);
				
		webElement(cntmEdtSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		click(cntmEdtSubmitBtn);
		thread();
		
		try
		{
			getObjectText(msgNotificationBar);
			if(getActualObjectTxt.contains("Please select"))
			{
				click(cntmEdtCancelBtn);
				thread();
				
				for(int i=1; i<3; i++)
				{
					driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+i+"]/td//input")).click();
				}
				massEdit();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=8)
	void verifyContactUpdatedSuccMsgInMassEdit() throws IOException, InterruptedException
	{
		Thread.sleep(1000);
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Successfully Updated 2 Record(s)", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=9)
	void verifyMassUpdatedDetailsInListView() throws InterruptedException
	{
		for(int i=1; i<3; i++)
		{
			String getmUpdtTxt = driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+i+"]/td[4]")).getText();
			
			if(getmUpdtTxt.contains(masContactType))
			{
				for(int j=4; j<10; j++)
				{
					String getmUpdatdTxt = driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+i+"]/td["+j+"]")).getText();
					
					//		First Row
					if(i == 1 && j == 4 && masContactType.equals(none))
					{
						verifyAssertEquals("", getmUpdatdTxt);	//	Contact Type
					}
					else if(i == 1 && j == 4 && !masContactType.equals(none))
					{
						verifyAssertEquals(masContactType, getmUpdatdTxt);	//	Contact Type
					}	
					else if(i == 1 && j == 8)
					{
						verifyAssertEquals(masAddress, getmUpdatdTxt);	//	Address
					}
					else if(i == 1 && j == 9 && masContactStatus.equals(none))
					{
						verifyAssertEquals("", getmUpdatdTxt);		//	Status
					}
					else if(i == 1 && j == 9 && !masContactStatus.equals(none))
					{
						verifyAssertEquals(masContactStatus, getmUpdatdTxt);		//	Status
					}
					
					//		Second Row
					else if(i == 2 && j == 4 && masContactType.equals(none))
					{
						verifyAssertEquals("", getmUpdatdTxt);	//	Contact Type
					}
					else if(i == 2 && j == 4 && !masContactType.equals(none))
					{
						verifyAssertEquals(masContactType, getmUpdatdTxt);	//	Contact Type
					}
					else if(i == 2 && j == 8)
					{
						verifyAssertEquals(masAddress, getmUpdatdTxt);	//	Address
					}
					else if(i == 2 && j == 9 && masContactStatus.equals(none))
					{
						verifyAssertEquals("", getmUpdatdTxt);		//	Status
					}
					else if(i == 2 && j == 9 && !masContactStatus.equals(none))
					{
						verifyAssertEquals(masContactStatus, getmUpdatdTxt);		//	Status
					}
					else if(i == 2 && j == 8)
					{
						verifyAssertEquals(masAddress, getmUpdatdTxt);	//	Address
					}
				}
				
				for(int k=1; k<3; k++)
				{
					driver.findElement(By.xpath("//table[@id='contacts_table']//tbody/tr["+k+"]/td[11]/a[1]")).click();	//	Edit button
					thread();
					
					//		Country
					getObjectText(cntCountryDefTxt);
					if(masCountry.equals(none))
					{
						verifyAssertEquals(selectCountry, getActualObjectTxt);
					}
					else if(!masCountry.equals(none))
					{
						verifyAssertEquals(masCountry, getActualObjectTxt);
					}					
					
					//		State
					getObjectText(cntStateDefTxt);
					if(masState.equals(none))
					{
						verifyAssertEquals(selectState, getActualObjectTxt);
					}
					else if(!masState.equals(none))
					{
						verifyAssertEquals(masState, getActualObjectTxt);
					}
					
					//		Service Area
					getObjectText(cntServiceAreaDefTxt);
					if(masServiceArea.equals(none))
					{
						verifyAssertEquals(selectAddNewServiceArea, getActualObjectTxt);
					}
					else if(!masServiceArea.equals(none))
					{
						verifyAssertEquals(masServiceArea, getActualObjectTxt);
					}
					
					//		City
					getAttributeVal(cntCity);
					verifyAssertEquals(masCity, getAttribteVal);
					
					//		Zip code
					getAttributeVal(cntZipcode);
					verifyAssertEquals(masZipcode, getAttribteVal);	
					
					click(cntCancelBtn);
					thread();
				}
			}			
		}		
		thread();
	}
	
	@Test(priority=10)
	void chkHelpDocumentPopup() throws IOException, InterruptedException
	{
		click(cntOvrInfoBtn);
		thread();
		verifyAssert(cntHelpDocumentPp);
		takeScreenshot();
		thread();
		
		click(cntHelpDocumentPpCloseBtn);
		Thread.sleep(1000);
	}
	
	/*@Test(priority=11)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
}
