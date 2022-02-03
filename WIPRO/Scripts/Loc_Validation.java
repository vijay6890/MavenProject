package Scripts;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Locations.*;
import static UIWrappers.UIObjects.*;

public class Loc_Validation extends Page
{
LoginLogout ll = new LoginLogout();
Loc_AddDeleteLocation la=new Loc_AddDeleteLocation();
	
/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException 
	{
		ll.loginToSIB();
	}*/
	
	@Test(priority=1)
	void verifyLocHighlightedInMainMenu() throws InterruptedException
	{
		click(locationsInMainMenu);
		thread();
		
		getHighlightOptn(mainLocationsOptn);
		verifyAssertEquals("active",getHighlightdTxt);
	}
	
	@Test(priority=2)
	void validateMandatoryFields() throws InterruptedException, IOException, BiffException
	{
		getLocSheetNameFromExcel();	
		
		click(locOvrAddBtn);
		thread();
		
		click(locSubmitBtn);
		thread();
		takeScreenshot();
		
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("Location Name is required.", getActualObjectTxt);		
		
		Random rand=new Random();
		int rLoc=rand.nextInt(loc.getRows()-1)+1;
		if(rLoc==0)
		{
			rLoc=1;
		}	
		
		// Location Name
		la.adedLocationName = loc.getCell(0, rLoc).getContents();
    	thread();
    	enterText(locLocationName, la.adedLocationName);
    	 
    	click(locSubmitBtn);
    	thread();
    	takeScreenshot();
    	
    	getObjectText(stepValidatnMsg);
		verifyAssertEquals("Address is required.", getActualObjectTxt);
		
		
        //		Address
    	la.adedAddress = loc.getCell(3, rLoc).getContents();
    	enterText(locAddress, la.adedAddress);
    	
    	click(locSubmitBtn);
    	takeScreenshot();
    	//thread();
    	
    	getObjectText(stepValidatnMsg);
		verifyAssertEquals("Country is required.", getActualObjectTxt);
		
	/*	//Country
		getTotalValuesIndd(locCountryTotalCnt);
		Random rCntry=new Random();
		int rCnt=rCntry.nextInt(totalDDValCount-1)+1;
		if(rCnt==0)
		{
			rCnt=1;
		}
		System.out.println("Rcountry "+rCnt);
		//String rCountry=driver.findElement(By.xpath("//li[@id='fac_country_chzn_o_"+rCnt+"']")).getText();
		String rCountry=driver.findElement(By.xpath("//div[@id='fac_country_chzn']/div/ul/li[3]")).getText();
		System.out.println("country "+rCountry);
		click(locCountryDDArw);
		thread();
		enterText(locCountrySearchBox,rCountry);
		enterKeyInKyBord(locCountrySearchBox);
		thread();
		
		click(locSubmitBtn);
    	takeScreenshot();
    	
    	getObjectText(stepValidatnMsg);
		verifyAssertEquals("State is required.", getActualObjectTxt);
		*/
		
       //		Country
    	click(locCountryDDArw);
    	la.adedCountry = loc.getCell(6, rLoc).getContents();
    	enterText(locCountrySearchBox, la.adedCountry);
    	enterKeyInKyBord(locCountrySearchBox);
    	thread();
    	
    	click(locSubmitBtn);
    	takeScreenshot();
    	
    	getObjectText(stepValidatnMsg);
		verifyAssertEquals("State is required.", getActualObjectTxt);
		
		
	/*	//         State
		getTotalValuesIndd(locStateTotalCnt);
		Random rState=new Random();
		int rSt=rState.nextInt(totalDDValCount-1)+1;
		if(rSt==0)
			{
				rSt=1;
			}
		String rSta=driver.findElement(By.id("fac_state_chzn_o_"+rSt)).getText();
		System.out.println("state "+rSta);
		click(locStateDDArw);
		thread();
		enterText(locStateSearchBox,rSta);
		enterKeyInKyBord(locStateSearchBox);
		thread();
				
		click(locSubmitBtn);
		takeScreenshot();
		
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("City is required.", getActualObjectTxt);
		*/
		
   	
    	//	State
    	click(locStateDDArw);
    	la.adedState = loc.getCell(7, rLoc).getContents();    	
    	enterText(locStateSearchBox, la.adedState);
    	enterKeyInKyBord(locStateSearchBox);
    	thread();
    	
    	click(locSubmitBtn);
		takeScreenshot();
		
		getObjectText(stepValidatnMsg);
		verifyAssertEquals("City is required.", getActualObjectTxt);
    	
				
		//City
    	la.adedCity = loc.getCell(8, rLoc).getContents();
    	enterText(locCity, la.adedCity);
    	thread();
    	
    	click(locSubmitBtn);
    	takeScreenshot();
    	
    	getObjectText(stepValidatnMsg);
		verifyAssertEquals("Zipcode is required.", getActualObjectTxt);
    	
    	//	Zip Code
    	la.adedZipcode = loc.getCell(9, rLoc).getContents();
    	enterText(locZipCode, la.adedZipcode);
    	thread();
    	
    	click(locSubmitBtn);
    	thread();
    	takeScreenshot();
    	thread();
    	
    	getObjectText(msgNotificationBar);
    	verifyAssertEquals(la.adedLocationName+" Successfully Added", getActualObjectTxt);
    	
		
    	
	}

}
