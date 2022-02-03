package Scripts;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static ObjectRepository.Assets.*;
import static ObjectRepository.Threats.*;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Tasks.*;

import static Config.TakScreenshot.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**************************************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Add New Asset Details
 * Test 2: Verify Asset Added Successfully Message
 * Test 3: Check the Asset Name in Mapping pop up
 * Test 4: Do the Asset mapping for Locations, Asset Groups, Contacts, Insurance, Business Functions and Tasks
 * Test 5: Verify Asset Successfully mapped to Locations, Asset Groups, Contacts, Insurance, Business Functions and Tasks
 * Test 6: Verify the Asset Name in Relationship Title
 * Test 7: Check the 'Clear' button functionality
 * Test 8: Edit the Asset Details
 * Test 9: Verify AssetName Successfully Updated Message
 * Test 10: Verify Edited Asset Name in Assets List View
 * Test 11: Verify the Edited Name in Relationship title
 * Test 12: Search with Valid/Invalid Asset Name
 * Test 13: Verify the Pagination
 * Test 14: Check the Show Entries drop down
 * Test 15: Verify Select All check box
 * 
***************************************************************************************************************************/

public class Ast_AddNewAssets extends Page {

	LoginLogout ll = new LoginLogout();
	Thr_AddndDelete thr_AddndDelete = new Thr_AddndDelete();

	public String assetName,modifiedAssetName;
	public WebElement getAddedAssetName;
	public int a;
	private String assetNamePh = "Asset Name";
	private String assetStatusDefTxt = "Select Asset Status";
	private String assetTypeDefTxt = "Select Asset Type";
	private String modelNoPh = "Model No";
	private String assetIdPh = "Asset ID";
	private String manufacturerNamePh = "Manufacturer Name";
	private String serialNoPh = "Serial No";
	private String quantityPh = "Quantity";
	private String replacementValuePh = "Replacement Value";

   /* @Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException {
		ll.loginToSIB();
	}*/

	@Test(priority=1)
	void verifyAddNewAssetPage() throws InterruptedException
	{   Thread.sleep(1000);
		click(assetsInMainMenu);
		waitForPageLoad();
		Thread.sleep(1000);
		click(astOvrAddBtn);
		Thread.sleep(1000);
	}
	
	@Test(priority=2)
	void addNewAssetName() throws BiffException, IOException, InterruptedException 
	{
		getAssetsSheetFromExcel();		
		Random random = new Random();
		int rrow = random.nextInt(ast.getRows()-1)+1;
			
		// Asset Name
		assetName = ast.getCell(0, rrow).getContents();
		enterText(astAssetName, assetName);
		thread();
				
		// Asset Status
		click(astStatusArrow);
		getTotalValuesIndd(astStatusCnt);
		if(totalDDValCount > 1)
		{
			Random rAssetStatus = new Random();  
			int rstatus = rAssetStatus.nextInt(totalDDValCount - 1) + 1;
			enterText(astStatusSearchBox, driver.findElement(By.id("asset_status_chzn_o_"+rstatus)).getText());
			enterKeyInKyBord(astStatusSearchBox);
			thread();
		}		
		
		// Asset Type
		click(astAssetTypeArrow);
		getTotalValuesIndd(astAssetTypeCnt);
		if(totalDDValCount > 1)
		{
			Random rAsetType = new Random();
			int rassetType = rAsetType.nextInt(totalDDValCount - 1) + 1;
			enterText(astAssetTypeSearchBox, driver.findElement(By.id("asset_type_chzn_o_"+rassetType)).getText());
			enterKeyInKyBord(astAssetTypeSearchBox);
			thread();
		}	
		
		// Model No
		enterText(astModelNumber, ast.getCell(1, rrow).getContents());
		thread();                                                          
		
		// Asset Id
		enterText(astAssetId, ast.getCell(2, rrow).getContents());
		thread();
		
		//Manufacturer Name
		enterText(astManufacturerName,ast.getCell(3, rrow).getContents());
		thread();
		
		// Serial No
		enterText(astSerialNumber, ast.getCell(4, rrow).getContents());
		thread();
		
		// Quantity
		enterText(astQuantity, ast.getCell(5, rrow).getContents());
		thread();
		
		// Replacement Value
		enterText(astReplacementValue, ast.getCell(6, rrow).getContents());

		// Comments
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);
		enterText(scEdtrCommentsField, ast.getCell(7, rrow).getContents());
		thread();
		switchBackFromFrame();
		takeScreenshot();
		webElement(astSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		click(astSubmitBtn);
		Thread.sleep(1000);
		getObjectText(msgNotificationBar);
		verifyAssertEquals(assetName+" Successfully Added", getActualObjectTxt);
		takeScreenshot();
		thread();
	}

	@Test(priority=3)
	void chkAssetNameInMapLocationsPp() throws InterruptedException 
	{
		click(rltnAddRemoveRltnsBtn);
		thread();
		try{
		verifyAssert(mapMappingPopup);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Locations To "+assetName, getActualObjectTxt);
	}

	@Test(priority=4)
	void mapAssetsToLocations() throws InterruptedException, IOException 
	{
		doMappingt();
		takeScreenshot();
		thread();
		click(mapSubmitBtn);
		thread();
	}

	@Test(priority=5)
	void verifyAssetSuccMapedToLocationsMsg() throws IOException, InterruptedException 
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(assetName+" successfully mapped to Locations", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=6)
	void searchMappedLocations() throws IOException, InterruptedException
	{
		getTotalValuesIndd(astRltnLocationsCnt);
		
		Random locName = new Random();
		int rlocName = locName.nextInt(totalDDValCount-1)+1;
		
		//	Search Valid Location Name
		String locatnName = driver.findElement(By.xpath("//table[@id='rel_facilities']//tbody/tr["+rlocName+"]/td")).getText();
		enterText(astRltnLocationsSearchBox, locatnName);
		getObjectText(astRltnLocationsSrchRslt);
		verifyAssertEquals(locatnName, getActualObjectTxt);
		takeScreenshot();
		clear(astRltnLocationsSearchBox);
		enterKeyInKyBord(astRltnLocationsSearchBox);
		
		//	Search Invalid Location Name
		enterText(astRltnLocationsSearchBox, "Inv Location Name");
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(astRltnLocationsSearchBox);
		enterKeyInKyBord(astRltnLocationsSearchBox);
		thread();
	}

	@Test(priority=7)
	void chkAssetNameInMapAssetGroupsPp() 
	{
		click(assetGroupsTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);

		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Asset Groups To "+assetName, getActualObjectTxt);
	}

	@Test(priority=8)
	void mapAssetToAssetGroups() throws InterruptedException, IOException 
	{
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
	}

	@Test(priority=9)
	void verifyAssetSuccMapedToAssetGrpsMsg() throws IOException, InterruptedException 
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(assetName+" successfully mapped to Asset Groups", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=10)
	void searchMappedAssetGrp() throws IOException, InterruptedException
	{
		getTotalValuesIndd(astRltnAssetGrpCnt);
		
		Random AssetGp = new Random();
		int rasstGp = AssetGp.nextInt(totalDDValCount);
		if(rasstGp==0)
		{
			rasstGp=+1;
		}
		
		//	Search Valid Asset Group 
		String AssetGrp= driver.findElement(By.xpath("//table[@id='rel_asset_grp']/tbody/tr["+rasstGp+"]/td")).getText();
		enterText(astRltnAssetGrpSearchBox, AssetGrp);
		getObjectText(astRltnAssetGrpSrchRslt);
		verifyAssertEquals(AssetGrp, getActualObjectTxt);
		clear(astRltnAssetGrpSearchBox);
		thread();
		enterKeyInKyBord(astRltnAssetGrpSearchBox);
		thread();
	
		//	Search Invalid Asset Group 
		enterText(astRltnAssetGrpSearchBox, "Inv Asset Group");
		thread();
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(astRltnAssetGrpSearchBox);
		enterKeyInKyBord(astRltnAssetGrpSearchBox);
		thread();
	}	

	@Test(priority=11)
	void chkAssetNameInMapContactsPp() 
	{
		click(astContactsTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);

		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Contacts To "+assetName, getActualObjectTxt);
	}

	@Test(priority=12)
	void mapAssetsToContacts() throws InterruptedException, IOException 
	{
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
	}

	@Test(priority=13)
	void verifyAssetSuccMapedToContactsMsg() throws IOException, InterruptedException 
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(assetName+" successfully mapped to Contacts", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=14)
	void searchMappedContacts() throws IOException, InterruptedException
	{
		getTotalValuesIndd(astRltnContactsCnt);
		
		//	Search Valid Contact Name
		Random cnt = new Random();
		int rcnt = cnt.nextInt(totalDDValCount-1)+1;		
		String Contacts = driver.findElement(By.xpath("//table[@id='rel_contacts']/tbody/tr["+rcnt+"]/td")).getText();
		//System.out.println("contact "+Contacts);
		enterText(astRltnContactsSearchBox, Contacts);
	
		getObjectText(astRltnContactsSrchRslt);
		
		//System.out.println("contact actual tect "+getActualObjectTxt.substring(4));
		
		verifyAssertEquals(Contacts, getActualObjectTxt);
		takeScreenshot();
		clear(astRltnContactsSearchBox);
		enterKeyInKyBord(astRltnContactsSearchBox);
		thread();
		
		//	Search Invalid Contact Name
		enterText(astRltnContactsSearchBox, "Inv Contact Name");
		thread();
		getObjectText(astContactsTbNoMatchngRecFound);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(astRltnContactsSearchBox);
		enterKeyInKyBord(astRltnContactsSearchBox);
		thread();
	}

	@Test(priority=15) 
	void mapAssetToInsurance() throws InterruptedException, IOException
	{
		click(insuranceTb); 
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);
		
		doMappingt(); 
		takeScreenshot(); 
		click(mapSubmitBtn); 
		thread();
	}
	  
	@Test(priority=16) 
	void verifyAssetSuccMapedToInsuranceMsg() throws IOException, InterruptedException 
	{ 
		getObjectText(msgNotificationBar);
		verifyAssertEquals(assetName+" successfully mapped to Insurance",
		getActualObjectTxt); takeScreenshot(); thread(); 
	}
	
	@Test(priority=17)
	void searchMappedIns() throws IOException, InterruptedException
	{
		//		Search with Valid Details
		getTotalValuesIndd(astRltnInsCnt);
		Random random = new Random();
		int rIns = random.nextInt(totalDDValCount-1)+1;
		String getIns = driver.findElement(By.xpath("//table[@id='rel_insurances']/tbody/tr["+ rIns+"]/td")).getText();
		enterText(astRltnInsSearchBox , getIns);
	
		getObjectText(locRltnInsSrchRslt);
		verifyAssertEquals(getIns, getActualObjectTxt);
		takeScreenshot();
		clear(astRltnInsSearchBox );
		enterKeyInKyBord(astRltnInsSearchBox);
		thread();
		
		//		Search Invalid Insurance Name 
		enterText(astRltnInsSearchBox , "Inv Insurance Name");
		thread();
		getObjectText(astInsuranceTbNoMatchngRecFound);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(astRltnInsSearchBox );
		enterKeyInKyBord(astRltnInsSearchBox );
		thread();
	}	
	
	@Test(priority=18)
	void chkAssetNameInMapBusinessFunctionsPp() 
	{
		click(businessFunctionsTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);

		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Business Functions To "+assetName, getActualObjectTxt);
	}

	@Test(priority=19)
	void mapAssetToBusinessFunctions() throws InterruptedException, IOException 
	{
		doMappingt();
		takeScreenshot();
		click(mapSubmitBtn);
		thread();
	}

	@Test(priority=20)
	void verifyAssetSuccMapedToBusinessFuncMsg() throws IOException, InterruptedException 
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals(assetName+" successfully mapped to Business Functions", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=21)
	void searchMappedBusinessFunctions() throws IOException, InterruptedException
	{
		//		Search Valid BF Name
		getTotalValuesIndd(astRltnBusinesFuncCnt);
		Random random = new Random();
		int rBusiFunc = random.nextInt(totalDDValCount-1)+1;
		String getBusinessFunctionName = driver.findElement(By.xpath("//table[@id='rel_processes']/tbody/tr["+rBusiFunc+"]/td")).getText();
		enterText(astRltnBusinesFuncSearchBox, getBusinessFunctionName);
		
		getObjectText(astRltnBusinessFuncSearchRslt);
		verifyAssertEquals(getBusinessFunctionName, getActualObjectTxt);
		takeScreenshot();
		clear(astRltnBusinesFuncSearchBox);
		enterKeyInKyBord(astRltnBusinesFuncSearchBox);
		thread();
		
		//	Search Invalid BF Name
		enterText(astRltnBusinesFuncSearchBox, "Inv Bus Func Name");
		thread();
		getObjectText(astBusinessFuncTbNoMatchngRecFound);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(astRltnBusinesFuncSearchBox);
		enterKeyInKyBord(astRltnBusinesFuncSearchBox);
		thread();
	}

	@Test(priority=22)
	void chkAssetNameInMapTaskPp() throws InterruptedException
	{
		click(tasksTb);
		click(rltnAddRemoveRltnsBtn);
		verifyAssert(mapMappingPopup);

		getObjectText(mapMappingPpTitleNm);
		verifyAssertEquals("Map Tasks To " + assetName, getActualObjectTxt);
		thread();
	}

	@Test(priority=23)
	void mapAssetToTasks() throws InterruptedException, IOException
	{		
		doMappingt();	
		takeScreenshot();
		thread();
		click(mapSubmitBtn);
		thread();
		getObjectText(msgNotificationBar);
		verifyAssertEquals(assetName+" successfully mapped to Tasks", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	@Test(priority=24)
	void searchMappedTasks() throws IOException, InterruptedException
	{
		getTotalValuesIndd(astRltnTasksCnt);
		
		//	Search Valid Task Group Name
		Random tskGrpNm = new Random();
		int rtskGrpNm = tskGrpNm.nextInt(totalDDValCount-1)+1;		
		String taskGroupNm = driver.findElement(By.xpath("//table[@id='rel_task']//tr["+rtskGrpNm+"]/td")).getText();
		enterText(astRltnTasksSearchBox, taskGroupNm);
		getObjectText(astRltnTasksSrchRslt);
		thread();
		try{
		verifyAssertEquals(taskGroupNm, getActualObjectTxt);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		takeScreenshot();
		clear(astRltnTasksSearchBox);
		enterKeyInKyBord(astRltnTasksSearchBox);
		thread();
		
		//	Search Invalid Contact Group Name
		enterText(astRltnTasksSearchBox, "Inv Task Name");
		thread();
		getObjectText(astTasksTbNoMatchngRecFound);
		verifyAssertEquals(noMatchingRecordsFound, getActualObjectTxt);
		takeScreenshot();
		clear(astRltnTasksSearchBox);
		enterKeyInKyBord(astRltnTasksSearchBox);
		thread();
	}	
	@Test(priority=25)
	void verifyAssetNameInRelationshipTitle()
	{
		getTotalValuesIndd(astAssetListViewTtl);
				
		for(a=1; a<totalDDValCount; a++)
		{
			getAddedAssetName = driver.findElement(By.xpath("//table[@id='assets_table']/tbody/tr["+a+"]/td[2]"));
			if(getAddedAssetName.getText().contains(assetName))
			{
				getAddedAssetName.click();
				
				//	Verify Asset Name In Relation Title
				getObjectText(selectdNmeInRltnBar);
				verifyAssertEquals(assetName, getActualObjectTxt.substring(4).trim());
				break;
			}
		}		
	}
	@Test(priority=26)
	void verifyAssetNameInEditScrnRelationshipTitle() throws InterruptedException
	{
		//	Click Edit button
		driver.findElement(By.xpath("//table[@id='assets_table']/tbody/tr["+a+"]/td[12]/a[1]")).click();
		thread();
		
		getObjectText(selectdNmeInRltnBar);
		verifyAssertEquals(assetName, getActualObjectTxt.substring(4).trim());
	}
	
	@Test(priority=27)
	void verifyClearButtonFunc() throws IOException, InterruptedException
	{
		webElement(astClearBtn);
		scrollInnerScrollBar(webelementFrame);
		thread();
		click(astClearBtn);
		takeScreenshot();
			
		//	Asset Name
		getAttributePh(astAssetName);
		verifyAssertEquals(assetNamePh, getAttribtePh);
		
		//	Status
		getObjectText(astStatusEdt);
		verifyAssertEquals(assetStatusDefTxt, getActualObjectTxt);
		
		//	Asset Type
		getObjectText(astAssetTypeEdt);
		verifyAssertEquals(assetTypeDefTxt, getActualObjectTxt);
		
		//	Model No
		getAttributePh(astModelNumber);
		verifyAssertEquals(modelNoPh, getAttribtePh);
		
		//	Asset Id
		getAttributePh(astAssetId);
		verifyAssertEquals(assetIdPh, getAttribtePh);
		
		//	Manufacturer Name
		getAttributePh(astManufacturerName);
		verifyAssertEquals(manufacturerNamePh, getAttribtePh);
		
		//	Serial No
		getAttributePh(astSerialNumber);
		verifyAssertEquals(serialNoPh, getAttribtePh);
		
		//	Quantity
		getAttributePh(astQuantity);
		verifyAssertEquals(quantityPh, getAttribtePh);
		
		//	Replacement Value
		getAttributePh(astReplacementValue);
		verifyAssertEquals(replacementValuePh, getAttribtePh);
		thread();
	}
	
	@Test(priority=28)
	void editAssetName() throws InterruptedException, IOException, BiffException
	{
		//addNewAssetName();
		//thread();
		click(assetsInMainMenu);
		
		click(astLstviewFstEdtBtn);
		thread();
		
		webElement(astClearBtn);
		scrollInnerScrollBar(webelementFrame);
		thread();
		
		click(astClearBtn);
		thread();
		    
		getAssetsSheetFromExcel();		
		Random random = new Random();

		// Asset Name
		modifiedAssetName = ast.getCell(0, random.nextInt(ast.getRows() - 1) + 1).getContents();
		enterText(astAssetName, modifiedAssetName);
		waitForTextPresent(astAssetName, modifiedAssetName);
		thread();

		// Status
		click(astStatusArrow);
		getTotalValuesIndd(astStatusCnt);
		if(totalDDValCount > 1)
		{
		Random random1 = new Random();
		int rstatus = random1.nextInt(totalDDValCount - 1) + 1;
		enterText(astStatusSearchBox, driver.findElement(By.id("asset_status_chzn_o_"+rstatus)).getText());
		enterKeyInKyBord(astStatusSearchBox);
		thread();
		}
		// Asset Type
		click(astAssetTypeArrow);
		getTotalValuesIndd(astAssetTypeCnt);
		if(totalDDValCount > 1)
		{
		Random random2 = new Random();
		int rassetType = random2.nextInt(totalDDValCount - 1) + 1;
		enterText(astAssetTypeSearchBox, driver.findElement(By.id("asset_type_chzn_o_"+rassetType)).getText());
		enterKeyInKyBord(astAssetTypeSearchBox);
		thread();
		}
		
		// Model No
		enterText(astModelNumber, ast.getCell(1, random.nextInt(ast.getRows() - 1) + 1).getContents());
		thread();
		
		// Asset Id
		enterText(astAssetId, ast.getCell(2, random.nextInt(ast.getRows() - 1) + 1).getContents());
		thread();
		
		//Manufacturer Name
				enterText(astManufacturerName,ast.getCell(3, random.nextInt(ast.getRows() - 1) + 1).getContents());
				thread();
		
		// Serial No
		enterText(astSerialNumber, ast.getCell(4, random.nextInt(ast.getRows() - 1) + 1).getContents());
		thread();
		
		// Quantity
		enterText(astQuantity, ast.getCell(5, random.nextInt(ast.getRows() - 1) + 1).getContents());
		thread();
		
		// Replacement Value
		enterText(astReplacementValue, ast.getCell(6, random.nextInt(ast.getRows() - 1) + 1).getContents());

		// Comments
		WebElement ifram = driver.findElement(scEdtrFrame);
		driver.switchTo().frame(ifram);

		enterText(scEdtrCommentsField, ast.getCell(7, 1).getContents());
		thread();
		switchBackFromFrame();
		takeScreenshot();
		webElement(astSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		click(astSubmitBtn);
        thread();
		getObjectText(msgNotificationBar);
		thread();
		verifyAssertEquals(modifiedAssetName+" Successfully Updated", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=29)
	void verifyEditedAssetNameInListView() throws IOException
	{
		getTotalValuesIndd(astAssetListViewTtl);
		
		for(a=1; a<totalDDValCount; a++)
		{
			WebElement getModifiedAssetName = driver.findElement(By.xpath("//table[@id='assets_table']/tbody/tr["+a+"]/td[2]"));
			if(getModifiedAssetName.getText().contains(modifiedAssetName))
			{
				getModifiedAssetName.click();
				takeScreenshot();
				
				//	Verify Asset Name In Relationship Title
				getObjectText(selectdNmeInRltnBar);
				verifyAssertEquals(modifiedAssetName, getActualObjectTxt.substring(4).trim());
				break;
			}
		}
	}	
	
	
	@Test(priority=30)
	void verifyModifiedAssetNmInEditScrRelationshipTitle() throws InterruptedException
	{
		//	Click Edit button
		driver.findElement(By.xpath("//table[@id='assets_table']/tbody/tr["+a+"]/td[12]/a[1]")).click();
		thread();
			
		getObjectText(selectdNmeInRltnBar);
		verifyAssertEquals(modifiedAssetName, getActualObjectTxt.substring(4).trim());
		Thread.sleep(1000);
		
	}
	
	@Test(priority=31)
	void verifyCancelBtnRedirectionFromEdtScrn() throws InterruptedException
	{
		webElement(astCancelBtn);
		scrollInnerScrollBar(webelementFrame);
		click(astCancelBtn);
		thread();
		verifyAssert(astAssetsSearchBox);
		Thread.sleep(1000);
	}
	
	@Test(priority=32)
	void searchWithValidAssetName() throws IOException, InterruptedException
	{
		getTotalValuesIndd(astAssetListViewTtl);
		Random random = new Random();
		int rn = random.nextInt(totalDDValCount-1)+1;
		String assetNameFrSearch = driver.findElement(By.xpath("//table[@id='assets_table']/tbody/tr["+rn+"]/td[2]")).getText();
		enterText(astAssetsSearchBox, assetNameFrSearch);
		thread();
		enterKeyInKyBord(astAssetsSearchBox);
		thread();
		takeScreenshot();
		try
		{
		getObjectText(astFstNameInLstView);
		thread();
		verifyAssertEquals(assetNameFrSearch.trim(), getActualObjectTxt);
		takeScreenshot();
		thread();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		clear(astAssetsSearchBox);
		enterKeyInKyBord(astAssetsSearchBox);
		thread();
	}
	
	@Test(priority=33)
	void searchWithInvalidAssetName() throws IOException, InterruptedException
	{
		getTotalValuesIndd(astAssetListViewTtl);	
		enterText(astAssetsSearchBox, "Invalid Asset Name");
		takeScreenshot();
		getObjectText(noRecordsFoundMsg);
		verifyAssertEquals(thr_AddndDelete.noMatchingRecsFound, getActualObjectTxt);
		clear(astAssetsSearchBox);
		enterKeyInKyBord(astAssetsSearchBox);
		thread();
	}
	
	@Test(priority=34)
	void verifySelectAllChkBoxFunc() throws IOException, InterruptedException
	{
		click(astSelectAllchkBox);
		takeScreenshot();
		
		getTotalValuesIndd(astAssetListViewTtl);
		for(int i=1; i<=totalDDValCount; i++)
		{
			boolean chkd = driver.findElement(By.xpath("//table[@id='assets_table']/tbody/tr["+i+"]/td/div/span")).isEnabled();
			Assert.assertEquals(true, chkd);					
		}		
		click(astSelectAllchkBox);
		thread();
	}
	
	@Test(priority=35)
	void assetLstViewPagination() throws IOException, InterruptedException
	{
		getTotalValuesIndd(astListViewPagination);
		
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
			driver.findElement(By.xpath("//div[3]/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/ul/li["+randomPge+"]/a")).click();
			thread();
			takeScreenshot();
			click(pagiStartArw);
			thread();
		}
	}
	
	@Test(priority=36)
	void verifyShowEntriesDropDown() throws IOException, InterruptedException
	{
		try
		{
			getObjectText(astListViewRecsInfo);
			
			if(Integer.parseInt(getActualObjectTxt.split("of ")[1].split(" ")[0].trim()) >10)
			{
				selectTextFromDropdown(astListViewLength, "25");
				thread();
				takeScreenshot();
				
				selectTextFromDropdown(astListViewLength, "10");
				thread();
			}
		}
		catch(WebDriverException e)
		{
			System.out.println("Pagination is not available in Asserts List View. It contains 10 or less than 10 records");
		}		
	}
	
	/* @Test(priority=37)
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

	 @Test(priority=38)
		void verifyViewPage()throws IOException, InterruptedException
		{
			click(astViewBtn);
			thread();
			takeScreenshot();
	     	getObjectText(astViewPopup);
	     	thread();
	     	click(astViewCloseBtn);
	     	thread();
		}
	
	/*@Test(priority=39)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/

}
