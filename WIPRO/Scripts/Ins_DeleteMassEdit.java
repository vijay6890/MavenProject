package Scripts;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static UIWrappers.UIObjects.*;
import static ObjectRepository.Insurance.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;
import static Config.TakScreenshot.*;


/*************************************************************************************************************************
 * 
 * @author Sellamuthu
 * 
 * Test 1: Add and Delete Single Policy Name
 * Test 2: Add and Delete multiple policy names
 * Test 3: Verify deleted successfully message for Single & Multiple policy
 * Test 4: Do the Mass Edit
 * Test 5: Verify the Mass Edit details in List View
 * Test 6: Check Insurance Help pop up window
 * 
*************************************************************************************************************************/

public class Ins_DeleteMassEdit extends Page {
	
	LoginLogout ll = new LoginLogout();
	Ins_AddEdit ins_AddEdit = new Ins_AddEdit();
	Loc_AddnDeleteMultiple loc_AddnDeleteMultiple = new Loc_AddnDeleteMultiple();
	
	String mEdtPolicyType, mEdtInsurerAdmin, mEdtInsurerContact, mEdtInsurer;
	
	
/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}       */
	
	@Test(priority=1)
	void deleteInsurancePolicyName() throws BiffException, IOException, InterruptedException
	{
		ins_AddEdit.verifyInsurancePolicyAddPge();
		ins_AddEdit.addInsuranceName();
		
		getTotalValuesIndd(insInsuranceListViewTtl);
		
		for(int i=1; i<totalDDValCount; i++)
		{
			if(driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+i+"]/td[2]")).getText().contains(ins_AddEdit.policyName))
			{
				driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+i+"]/td[10]/a[2]")).click();
				verifyAssert(deleteConfPopup);
				
				//	Check Delete Confirmation pop up message
				getObjectText(deleteConfPpMessage);
				verifyAssertEquals("Confirm to remove Insurance "+ins_AddEdit.policyName+"?", getActualObjectTxt);
				
				click(delConfOkBtn);
				thread();
				
				getObjectText(msgNotificationBar);
				verifyAssertEquals(ins_AddEdit.policyName+" Is Deleted", getActualObjectTxt);
				takeScreenshot();
				thread();
			}
		}
	}
	
	@Test(priority=2, invocationCount=3)
	void addPolicyName() throws BiffException, IOException, InterruptedException
	{
		ins_AddEdit.verifyInsurancePolicyAddPge();
		ins_AddEdit.addInsuranceName();
		thread();
	}
	
	@Test(priority=3)
	void deleteMultiplePolicyName() throws InterruptedException, IOException
	{
		getTotalValuesIndd(insInsuranceListViewTtl);
		
		for(int i=1; i<4; i++)
		{
			driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+i+"]/td/div/span/input")).click();
			thread();
		}
		
		click(insOvrDeleteBtn);
		thread();
		verifyAssert(deleteConfPopup);
		
		//	Check Delete Confirmation pop up message
	
		getObjectText(deleteConfPpMessage);
		verifyAssertEquals("Confirm to remove 3 record(s) from Insurance?", getActualObjectTxt);
		
		click(delConfOkBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("3 records deleted successfully", getActualObjectTxt);		
		takeScreenshot();
		thread();
	}
	
	@Test(priority=4)
	void checkSelectRecordsToPerformEditMsg() throws InterruptedException, IOException
	{
		click(insOvrMassEditBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals(loc_AddnDeleteMultiple.selectRecordstoPerformEditOperation, getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=5)
	void massEdit() throws BiffException, IOException, InterruptedException
	{
		getInsuranceSheetFromExcel();
		
		click(insFirstNameInLstView);
		thread();
		click(insSecNameInLstView);
		
		click(insOvrMassEditBtn);
		thread();
		
		//	Policy Type
		click(insmEdtPolicyTypeDDArw);
		getTotalValuesIndd(insmEdtPolicyTypeCnt);
		Random getAlPolicyType = new Random();
		int rpolicyType = getAlPolicyType.nextInt(totalDDValCount-1)+1;
		mEdtPolicyType = driver.findElement(By.id("insurance_type_chzn_o_"+rpolicyType)).getText();
		enterText(insmEdtPolicyTypeSearchBox, mEdtPolicyType);
		enterKeyInKyBord(insmEdtPolicyTypeSearchBox);
		
		//	Insurer
		int ttlRow = ins.getRows();
		Random selectInsurerNm = new Random();
		int rrow = selectInsurerNm.nextInt(ttlRow-1)+1;
		mEdtInsurer = ins.getCell(2, rrow).getContents();
		enterText(insmEdtInsurer, mEdtInsurer);
		
		//	Insurer Admin
		click(insmEdtInsurerAdminDDArw);
		getTotalValuesIndd(insmEdtInsurerAdminCnt);
		Random getAlInsuranceAdmin = new Random();
		int rinsurerAdmin = getAlInsuranceAdmin.nextInt(totalDDValCount-1)+1;
		mEdtInsurerAdmin = driver.findElement(By.id("insurance_admin_chzn_o_"+rinsurerAdmin)).getText().split(",")[0];
		enterText(insmEdtInsurerAdminSearchBox, mEdtInsurerAdmin);
		enterKeyInKyBord(insmEdtInsurerAdminSearchBox);
		
		//	Insurer Contact
		click(insmEdtInsurerContactDDArw);
		getTotalValuesIndd(insmEdtInsurerContactCnt);
		Random getAlInsurerContact = new Random();
		int rinsurerContact = getAlInsurerContact.nextInt(totalDDValCount-1)+1;
		mEdtInsurerContact = driver.findElement(By.id("insurance_contact_chzn_o_"+rinsurerContact)).getText().split(",")[0];
		enterText(insmEdtInsurerContactSearchBox, mEdtInsurerContact);
		enterKeyInKyBord(insmEdtInsurerContactSearchBox);
		takeScreenshot();
		
		webElement(insmEdtSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(insmEdtSubmitBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Successfully Updated 2 Record(s)", getActualObjectTxt);
		takeScreenshot();
		thread();
	}
	
	@Test(priority=6)
	void verifyMassEditDtlsinListView() throws InterruptedException
	{
		for(int i=1; i<3; i++)
		{
			String insurerNmInLst = driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+i+"]/td[4]")).getText();
			
			if(insurerNmInLst.contains(mEdtInsurer))
			{
				for(int j=4; j<8; j++)
				{
					String mEdtdDtlsInLst = driver.findElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+i+"]/td["+j+"]")).getText();
					
					if(j == 4)
					{
						verifyAssertEquals(mEdtInsurer, mEdtdDtlsInLst);	//	Check Insurer
					}
					else if(j == 7)
					{
						verifyAssertEquals(mEdtInsurerContact.trim(), mEdtdDtlsInLst);	//	Check Insurer Contact
					}
				}
			}			
		}thread();
	}
	
	@Test(priority=7)
	void checkInsuranceHelpDocuments() throws InterruptedException, IOException
	{
		click(insOvrInfoBtn);
		thread();
		verifyAssert(insInsuranceHelpPp);
		thread();
		takeScreenshot();
		click(insInsuranceHelpPpCloseBtn);
		thread();		
	}

	/*@Test(priority=8)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/

}
