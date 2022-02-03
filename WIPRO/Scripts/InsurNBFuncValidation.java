package Scripts;

import java.io.IOException;
import java.util.Random;

import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;
import static ObjectRepository.Insurance.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.BusinessFunctions.*;
import static Config.TakScreenshot.*;
import static UIWrappers.UIObjects.*;

public class InsurNBFuncValidation extends Page
{
	LoginLogout ll = new LoginLogout();
	BFunc_AddEdit ba=new BFunc_AddEdit();
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}
	
	@Test(priority=1)
	void checkInsuranceHighlightedInMainMenu() throws InterruptedException
	{
		click(insuranceInMainMenu);
		thread();
		getHighlightOptn(mainInsuranceOptn);
		verifyAssertEquals("active", getHighlightdTxt);	
	}
	
	@Test(priority=2)
	void checkInsuranceMandatoryFieldValidations() throws InterruptedException, IOException
	{
		click(insOvrAddBtn);
		thread();
		
		click(insSubmitBtn);
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Policy Name is required.",getActualObjectTxt);
		takeScreenshot();		
	}
	
	@Test(priority=3)
	void checkBusFunctionHighlightedInMainMenu() throws InterruptedException
	{
		click(businessFunctionsInMainMenu);
		thread();
		getHighlightOptn(mainBusinessFunctionsOptn);
		verifyAssertEquals("active",getHighlightdTxt);
	}
	
	@Test(priority=4)
	void checkBusiFunctionsMandatoryFieldValidations() throws InterruptedException, BiffException, IOException
	{
		getBusinessFunctionsSheetFromExcel();
		
		click(bsfOvrAddBtn);
		thread();
		
		click(bsfSubmitBtn);
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Function Name is required.",getActualObjectTxt);
		
		Random rand=new Random();
		int rr=rand.nextInt(bsFunc.getRows()-1)+1;
		
		ba.functionName = bsFunc.getCell(0, rr).getContents();
		enterText(bsfFunctionName, ba.functionName);
		click(bsfSubmitBtn);
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Loss per Day is required.",getActualObjectTxt);
		takeScreenshot();
		
		ba.lossPerDay = bsFunc.getCell(3, rr).getContents();
		enterText(bsfLossPerDay, ba.lossPerDay);
		click(bsfSubmitBtn);
		getObjectText(msgNotificationBar);
		verifyAssertEquals("Recovery Time should be DD:HH:MM",getActualObjectTxt);
		takeScreenshot();
		
		
	}
	
	
	

}
