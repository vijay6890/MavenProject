package Scripts;

import java.io.IOException;
import java.util.Random;

import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;
import static ObjectRepository.Contacts.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.ContactGroups.*;
import static UIWrappers.UIObjects.*;
import static Config.TakScreenshot.*;

public class CntNCntGrp_Validation extends Page
{
	LoginLogout ll = new LoginLogout();
	Cnt_AddNewContact ca=new Cnt_AddNewContact();
	
/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}*/
	
	@Test(priority=1)
	void checkContactsHighlightedInMainMenu() throws InterruptedException
	{
		click(contactsInMainMenu);
		thread();
		getHighlightOptn(mainContactsOptn);
		verifyAssertEquals("active", getHighlightdTxt);	
	}
	
	@Test(priority=2)
	void contactMandatoryFieldValidation() throws InterruptedException, IOException, BiffException
	{
		getContactsSheetFromExcel();
		
		click(cntOvrAddBtn);
		thread();
		
		webElement(cntSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(cntSubmitBtn);		
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Company Name is required.",getActualObjectTxt);
		takeScreenshot();
		thread();
		
		Random rand=new Random();
		int rc=rand.nextInt(cnt.getRows()-1)+1;
		
		ca.companyName =cnt.getCell(0, rc).getContents();
		enterText(cntCompanyName, ca.companyName);
		thread();
		webElement(cntSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		click(cntSubmitBtn);		
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("country is required.",getActualObjectTxt);
		takeScreenshot();
		thread();
		
		click(cntCountryDDArrow);
		enterText(cntCountrySearchBox, cnt.getCell(3, rc).getContents());
		enterKeyInKyBord(cntCountrySearchBox);
		thread();
		webElement(cntSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		click(cntSubmitBtn);		
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("state is required.",getActualObjectTxt);
		takeScreenshot();
		thread();
		
		//	State
		click(cntStateDDArrow);
		enterText(cntStateSearchBox, cnt.getCell(4, rc).getContents());
		enterKeyInKyBord(cntStateSearchBox);
		thread();
		webElement(cntSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		click(cntSubmitBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Successfully Added",getActualObjectTxt);
		takeScreenshot();
		
	}
	
	@Test(priority=3)
	void checkContactGroupsHighlightedInMainMenu() throws InterruptedException
	{
		click(contactGroupsInMainMenu);
		thread();
		getHighlightOptn(mainContactGroupsOptn);
		verifyAssertEquals("active", getHighlightdTxt);	
	}
	
	@Test(priority=4)
	void contactGroupMandatoryFieldValidation() throws BiffException, IOException, InterruptedException
	{		
		getContactGroupsSheetFromExcel();
		
		click(cntGrpOvrAddBtn);
		thread();
		
		click(cntGrpSubmitBtn);
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Contact Group Name is required.",getActualObjectTxt);
		takeScreenshot();
		
	}

}
