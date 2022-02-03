package Scripts;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Contacts.*;
import static ObjectRepository.Locations.*;
import static UIWrappers.UIObjects.*;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class Cnt_ImportAndExport extends Page{
	
	String totalEntries;
	public String downloadPath="D:\\SIB\\Downloads";
    int rVal;
    
    LoginLogout ll = new LoginLogout();	
	
	@Test(priority=0)
   void login() throws BiffException, IOException, InterruptedException
    {
	ll.loginToSIB();
    }
	
	@Test(priority=1)
	void importContactsCSVFile() throws InterruptedException, IOException
	{
		click(contactsInMainMenu);
		waitForPageLoad();
		click(cntImportBtn);
		thread();
		click(cntUploadFile);
		thread();
		uploadFile("D:\\SIB\\ImportFiles\\Contacts.csv");
		thread();
		click(cntImportSubmitBtn);
		thread();
		
        verifyAssert(cntSearchBox);
		
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("23 Contact(s) imported successfully" , getActualObjectTxt);	
		thread();
		
	}
	

	@Test(priority=2)
	void cntImportCancelBtn() throws InterruptedException
	{
		thread();
		thread();
		webElement(cntImportBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(cntImportBtn);
		thread();
		click(cntImportCancelBtn);
		thread();
		verifyAssert(cntSearchBox);
		thread();
	}
	
	@Test(priority=3)
	void cntImportHelpDoc() throws InterruptedException, AWTException
	{
		webElement(cntImportBtn);
		scrollInnerScrollBar(webelementFrame);
		click(cntImportBtn);
		thread();
		click(cntImpHelpDocBtn);
		thread();
		download();
		thread();
	
	}
	

    @Test(priority=4)
	void exportContactsCSVFiles() throws InterruptedException, AWTException
	{
		try
		{
    	click(contactsInMainMenu);
		waitForPageLoad();
		click(cntExportBtn);
		thread();
		download();
		thread();
		}
		catch(Exception e)
		{
			getObjectText(msgNotificationBar);
    		verifyAssertEquals("There are no records to export!",getActualObjectTxt);
		}
	}
    
    @Test(priority=5,dependsOnMethods={"exportContactsCSVFiles"})
    void validateExportedCsvFile()
    {
    	try
    	{
    		webElement(cntListViewRecsInfo);
    		scrollInnerScrollBar(webelementFrame);
    		
    		getObjectText(cntListViewRecsInfo);
    		thread();
    		totalEntries=getActualObjectTxt.split(" ")[5];
    		
    		if(totalEntries.contains(","))
    		{
    			totalEntries=totalEntries.replaceAll(",", "");
    		}
    		int appRows=Integer.parseInt(totalEntries);
    		
            System.out.println("Total Entries in App "+totalEntries);
    		
            File file = getLatestFilefromDir(downloadPath);
       		String csvFileName = file.getName();
       		System.out.println("CSV File Downloaded is :- "+csvFileName);
       		
            //Read csv file
       		
            LinkedList<String> ll=readCSVFileLines(downloadPath+"\\"+csvFileName);
       		
            int csvRows=ll.size()-1;
       		System.out.println("Total rows in CSV "+csvRows);
        	   		
       		try
       		{
       	    Assert.assertEquals(appRows,csvRows);	
       		thread();
       		System.out.println("Validation successful");
       		}
       		catch(Error e)
       		{
       			System.out.println("validation unsuccessful "+e);
       		}	
       		
       		WebElement selectDD=driver.findElement(By.xpath("//div[@id='contacts_table_length']/label/select"));
       		Select sel=new Select(selectDD);
       		sel.selectByValue("100");
       		thread();
       		
       		getTotalValuesIndd(By.xpath("//table[@id='contacts_table']/tbody/tr"));	   		
       	    int pgCnt=Integer.parseInt(totalEntries);
       	    
       	 for(rVal=1;rVal<=pgCnt;rVal++)
   		 {
   	    	thread();
   	    	webElement(By.xpath("//table[@id='contacts_table']/tbody/tr["+rVal+"]/td[11]/a[3]"));	
   		   scrollInnerScrollBar(webelementFrame);
   		
   	    	click(By.xpath("//table[@id='contacts_table']/tbody/tr["+rVal+"]/td[11]/a[3]"));
   	    	thread();
   	    	//System.out.println("Row Number "+rVal);
   		   			   		
      		String[] val=ll.get(rVal).split(",");
      		
      		
     	   for(int i=0;i<val.length;i++)   	    
     	   {
   			
   			//System.out.println(" "+val[i]);     		
   			if(i==3)
   			{
   				try{
   				getObjectText(viewCmpyNme);
   				if(getActualObjectTxt.trim().equals("No Data"))
				{
					getActualObjectTxt="";
				}
   		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
   				}catch(Error e)
   				{
   					System.out.println("No data "+e);
   				}
   			}
   			else if(i==5)
   			{
   				try{
   				getObjectText(viewCnttype);
   				if(getActualObjectTxt.trim().equals("No Data"))
   				{
   					getActualObjectTxt="";
   				}
   		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.split(",")[0].trim());
   				}catch(Error e)
   				{
   					System.out.println("No data "+e);
   				}
   			}
   			else if(i==6)
   			{
   				try{	   					
   	   			getObjectText(viewCntId);
   	   			if(getActualObjectTxt.trim().equals("No Data"))
   				{
   					getActualObjectTxt="";
   				}
   		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.split(",")[0].trim());
   				}catch(Error e)
   				{
   					System.out.println("No data "+e);
   				}
   			}
   			else if(i==18)
   			{
   				try{	   					
   	   			getObjectText(viewCntStatus);
   	   			if(getActualObjectTxt.trim().equals("No Data"))
   				{
   					getActualObjectTxt="";
   				}
   		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.split(",")[0].trim());
   				}catch(Error e)
   				{
   					System.out.println("No data "+e);
   				}
   			}
   			else if(i==28)
   			{
   				try{	   					
   	   			getObjectText(viewCntCmnts);
   	   			if(getActualObjectTxt.trim().equals("No Data"))
   				{
   					getActualObjectTxt="";
   				}
   		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.split(",")[0].trim());
   				}catch(Error e)
   				{
   					System.out.println("No data "+e);
   				}
   			}
   			else if(i==29)
   			{
   				try{	   					
   	   			getObjectText(viewCntLoc);
   	   			if(getActualObjectTxt.trim().equals("No Data"))
   				{
   					getActualObjectTxt="";
   				}
   		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.split(",")[0].trim());
   				}catch(Error e)
   				{
   					System.out.println("No data "+e);
   				}
   			}
   			
   			else{
  				//
  			}
  			   			
  		}
  		thread();
  		webElement(viewCntCloseBtn);
  		scrollInnerScrollBar(webelementFrame);
  		
  		click(viewCntCloseBtn);  //close button in view page
  		thread();
      	}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
	
	/* @Test(priority=5)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}  */




}
