package Scripts;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.Assets.*;
import static ObjectRepository.Threats.*;
import static ObjectRepository.Locations.*;
import static Config.TakScreenshot.*;

import jxl.read.biff.BiffException;

/******************************************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Delete Single Asset Name
 * Test 2: Verify Asset Name Deleted Successfully message
 * Test 3: Check 'Select records to delete' message
 * Test 4: Delete Multiple Asset Name
 * Test 5: Do the Mass Edit
 * Test 6: Check Updated Successfully message for Mass Edit
 * Test 7: Verify Mass Edit details in List View
 * Test 8: Verify 'Select records to edit' message
 * Test 9: Check Mass Edit page validation message
 * Test 10: Check the 'Cancel' button redirection from Mass Edit page
 * Test 11: Verify Asset Help Documents pop up
 * 
*******************************************************************************************************************************/

public class Ast_DeleteMassEdt extends Page{
	
	private String assetTypeBfrMedt, assetStatusBfrMedt, manufactNmBfrMedt;
	private String none = "None";
	
	LoginLogout ll = new LoginLogout();
	Loc_AddnDeleteMultiple loc_AddnDeleteMultiple = new Loc_AddnDeleteMultiple();
	Ast_AddNewAssets ast_AddNewAssets = new Ast_AddNewAssets();
	
   /* @Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}*/
	
	@Test(priority=1)
	void addAsset() throws InterruptedException, BiffException, IOException
	{
		for(int j=1;j<3;j++)
			{
			ast_AddNewAssets.verifyAddNewAssetPage();
			ast_AddNewAssets.addNewAssetName();
			thread();
			}
	}
	
	@Test(priority=2)
	void deleteSingleAssetName() throws IOException, InterruptedException, BiffException
	{
		/*for(int j=1;j<3;j++)
		{
		ast_AddNewAssets.verifyAddNewAssetPage();
		ast_AddNewAssets.addNewAssetName();
		thread();
		}*/
		
		getTotalValuesIndd(astAssetListViewTtl);
		for(int i=1; i<3; i++)
		{
			String getAsetName = driver.findElement(By.xpath("//table[@id='assets_table']//tbody/tr["+i+"]/td[2]")).getText();
			//if(getAsetName.equals(ast_AddNewAssets.assetName))
		//	{
				driver.findElement(By.xpath("//table[@id='assets_table']//tbody/tr["+i+"]/td[12]/a[2]")).click(); 	//	Delete button
				
				verifyAssert(deleteConfPopup);
				try
				{
				getObjectText(deleteConfPpMessage);
				if(i==1)
				{
				//verifyAssertEquals("Confirm to remove Asset "+ast_AddNewAssets.assetName+"?", getActualObjectTxt);
					verifyAssertEquals("Confirm to remove Asset "+getAsetName+"?", getActualObjectTxt);
				}
				else
				{
					//verifyAssertEquals("Confirm to remove Asset  "+ast_AddNewAssets.assetName+" ?", getActualObjectTxt);
					verifyAssertEquals("Confirm to remove Asset   "+getAsetName+" ?", getActualObjectTxt);
				}
				takeScreenshot();
				thread();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				click(delConfOkBtn);
				thread();
			//}
			
//	@Test(priority=2)
//	void verifyAssetNameDeletedSuccMessage() throws IOException, InterruptedException
//	{
		getObjectText(msgNotificationBar);
		//verifyAssertEquals(ast_AddNewAssets.assetName+" is Deleted", getActualObjectTxt);
		if(i==1)
		{
		verifyAssertEquals(getAsetName+" is Deleted", getActualObjectTxt);
		}
		else
		{
			verifyAssertEquals("  "+getAsetName+" is Deleted", getActualObjectTxt);
		}
		takeScreenshot();
		thread();
		}	
		//}
	}
	
	@Test(priority=3)
	void verifySelectRecordsToDeleteMsg() throws InterruptedException, IOException
	{
		click(astOvrDeleteBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordsToPerformDelete, getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=4, invocationCount=2)
	void addMultipleAssetName() throws InterruptedException, BiffException, IOException
	{
		ast_AddNewAssets.verifyAddNewAssetPage();
		ast_AddNewAssets.addNewAssetName();
		thread();
	}
	
	@Test(priority=5)
	void deleteMultipleAssetName() throws IOException, InterruptedException
	{
		for(int i=1; i<3; i++)
		{
			driver.findElement(By.xpath("//table[@id='assets_table']//tbody/tr["+i+"]//input")).click();		//	Click check box	
		}
		
		takeScreenshot();
		click(astOvrDeleteBtn);
		
		waitForElement(deleteConfPopup);
		getObjectText(deleteConfPpMessage);
		verifyAssertEquals("Confirm to remove 2 record(s) from Assets?", getActualObjectTxt);
		takeScreenshot();
		thread();
		
		click(delConfOkBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("2 records deleted successfully", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=6)
	void assetMassEdit() throws InterruptedException, IOException
	{
		for(int i=1; i<3; i++)
		{
			driver.findElement(By.xpath("//table[@id='assets_table']//tbody/tr["+i+"]//input")).click();		//	Click check box	
		}
		
		click(astOvrMassEditBtn);
		thread();
		
		//	Asset Type
		click(astmEdtAssetTypeArw);
		getTotalValuesIndd(astmEdtAssetTypeCnt);
		
		Random random = new Random();
		int raNmMasEdtAT = random.nextInt(totalDDValCount-2)+2;
		assetTypeBfrMedt = driver.findElement(By.xpath("//div[@id='assets_type_chzn']//ul/li["+raNmMasEdtAT+"]")).getText();
		enterText(astmEdtAssetTypeSearchBox, assetTypeBfrMedt);
		enterKeyInKyBord(astmEdtAssetTypeSearchBox);
		thread();
		
		//	Manufacturer Name
		Random ranManufactNm = new Random();
		int rmnm = ranManufactNm.nextInt(ast.getRows());
		manufactNmBfrMedt = ast.getCell(3, rmnm).getContents();
		enterText(astmEdtManufacturerName, manufactNmBfrMedt);
		
		//	Asset Status
		click(astmEdtAssetStatusArw);
		getTotalValuesIndd(astmEdtAssetStatusCnt);
		
		Random random1 = new Random();
		int raNmMasEdtAS = random1.nextInt(totalDDValCount-2)+2;
		assetStatusBfrMedt = driver.findElement(By.xpath("//div[@id='assets_status_chzn']//ul/li["+raNmMasEdtAS+"]")).getText();
		enterText(astmEdtAssetStatusSearchBox, assetStatusBfrMedt);
		enterKeyInKyBord(astmEdtAssetStatusSearchBox);
		takeScreenshot();
		
		click(astmEdtSubmitBtn);
		thread();
	}
	
	@Test(priority=7)
	void verifyUpdatedSuccMsgForMassEdit() throws IOException, InterruptedException
	{
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Successfully Updated 2 Record(s)", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=8)
	void verifyMassEditDetailsInLstView()
	{
		for(int i=1; i<3; i++)
		{
			for(int j=3; j<7; j++)
			{
				String masEdit = driver.findElement(By.xpath("//table[@id='assets_table']//tbody/tr["+i+"]/td["+j+"]")).getText();
				//		First Row
				if(j == 3 && i == 1 && assetStatusBfrMedt.equals(none))
				{
					verifyAssertEquals("", masEdit);	//	Status
				}
				else if(j == 3 && i == 1 && !assetStatusBfrMedt.equals(none))
				{
					verifyAssertEquals(assetStatusBfrMedt, masEdit);	//	Status
				}
				else if(j == 4 && i == 1 && assetTypeBfrMedt.equals(none))
				{
					verifyAssertEquals("", masEdit);	//	Type
				}
				else if(j == 4 && i == 1 && !assetTypeBfrMedt.equals(none))
				{
					verifyAssertEquals(assetTypeBfrMedt, masEdit);	//	Type
				}
				/*else if(j == 6 && i == 1)
				{
					verifyAssertEquals(manufactNmBfrMedt, masEdit);
				}*/
				
				//		Second Row
				else if(j == 3 && i == 2 && assetStatusBfrMedt.equals(none))
				{
					verifyAssertEquals("", masEdit);	//	Status
				}
				else if(j == 3 && i == 2 && !assetStatusBfrMedt.equals(none))
				{
					verifyAssertEquals(assetStatusBfrMedt, masEdit);	//	Status
				}
				else if(j == 4 && i == 2 && assetTypeBfrMedt.equals(none))
				{
					verifyAssertEquals("", masEdit);	//	Type
				}
				else if(j == 4 && i == 2 && !assetTypeBfrMedt.equals(none))
				{
					verifyAssertEquals(assetTypeBfrMedt, masEdit);	//	Type
				}
				else if(j==6&&i==2 && !!manufactNmBfrMedt.equals(none))
				{
					verifyAssertEquals("", masEdit);
				}
				else if(j == 6 && i == 2 && !manufactNmBfrMedt.equals(none))
				{
					verifyAssertEquals(manufactNmBfrMedt, masEdit);
				}
				
			}
		}		
	}
	
	@Test(priority=9)
	void verifySelectRecordToEditMsg() throws InterruptedException, IOException
	{
		click(astOvrMassEditBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordstoPerformEditOperation, getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=10)
	void massEditPageValidation() throws InterruptedException, IOException
	{
		click(astLstFirstName);
		thread();
		click(astOvrMassEditBtn);
		thread();
		click(astmEdtSubmitBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.fillAtleastOneFieldToUpdate, getActualObjectTxt);
		takeScreenshot();
		thread();
		click(astLstFirstName);
		thread();
	}
	
	@Test(priority=11)
	void cancelBtnRedirectionFrmMasEdtPage() throws InterruptedException
	{
		click(astmEdtCancelBtn);
		thread();
		verifyAssert(astAssetsSearchBox);
		thread();
	}
	
	@Test(priority=12)
	void verifyAssetInfoPopup() throws InterruptedException
	{
		click(astOvrInfoBtn);
		thread();
		verifyAssert(astHelpDocPopup);
		thread();
		click(astHelpDocPopupCrosCloseBtn);
	}
	
	/*@Test(priority=13)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/
}
