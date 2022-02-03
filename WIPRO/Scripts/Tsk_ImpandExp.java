package Scripts;
/***************************************************************************************************
 * 
 * @author Tamizhselvi
 * 
 * 
 * 
****************************************************************************************************/
import static Config.TakScreenshot.takeScreenshot;

import static ObjectRepository.Locations.*;
import static ObjectRepository.Tasks.*;
import static UIWrappers.UIObjects.*;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class Tsk_ImpandExp extends Page {
	
	String totalEntries;
	public String downloadPath="D:\\SIB\\Downloads";
    int rVal,appRows;
	
	LoginLogout ll = new LoginLogout();
	
	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}
	
	
	@Test(priority=1)
	void clickonTaskMainMenu() throws InterruptedException
	{
		  click(tasksInMainMenu);
		  thread();
	}
	
   @Test(priority=2)
	void verifyTaskImportButton() throws InterruptedException, AWTException, IOException
	{
		click(tskImportBtn);
		thread();
		
		click(tskImportUploadBtn);
		thread();
		//uploadFile("C:\\Users\\Tamizhselvi\\Desktop\\Import file\\Tasks.csv");
		uploadFile("D:\\SIB\\ImportFiles\\Tasks.csv");
		click(tskImportSubmitBtn);
		verifyAssert(tskTasksSearchBox);
		
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("8 Task(s) imported successfully" , getActualObjectTxt);	
		thread();
	       
	}
	
	@Test(priority=3)
	void verifyTaskCancelBtn() throws InterruptedException
	{
		click(tskImportBtn);
		thread();
		click(tskImportCancelBtn);
		verifyAssert(tskTasksSearchBox);
		thread();
	}
	
	@Test(priority=4)
	void verifyTaskImportHelpDoc() throws InterruptedException, AWTException
	
	{
	
		click(tskImportBtn);
		click(tskImportHelpDocBtn);
		thread();
		  download();
		 thread();
	}
	
	@Test(priority=5)
	void verifyTaskExportBtn() throws InterruptedException, AWTException
	{
		click(tasksInMainMenu);
		thread();
		    		
		webElement(tskNameListViewRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		getObjectText(tskNameListViewRecsInfo);
		thread();
		totalEntries=getActualObjectTxt.split(" ")[5];
		
		if(totalEntries.contains(","))
		{
			totalEntries=totalEntries.replaceAll(",", "");
		}
		
		appRows=Integer.parseInt(totalEntries);			
		System.out.println("Total Entries in App "+totalEntries);
		
		if(appRows==0)
		{
			int c=1/0;
		}
		
		click(tskExportBtn);
		thread();
	    download();
	    thread();		
	}
	
	
	@Test(priority=6,dependsOnMethods={"verifyTaskExportBtn"})
	void validateTaskExportedCsvFile()
	{
		try
		{
			thread();			
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
       		
       		WebElement selectDD=driver.findElement(By.xpath("//div[@id='tasks_table_length']/label/select"));
       		Select sel=new Select(selectDD);
       		sel.selectByValue("100");
       		thread();
       		
       		getTotalValuesIndd(By.xpath("//table[@id='tasks_table']/tbody/tr"));	   		
       	    int pgCnt=Integer.parseInt(totalEntries);
       	    
			
       	 for(rVal=1;rVal<=pgCnt;rVal++)
    		{
    		thread();
    		webElement(By.xpath("//table[@id='tasks_table']/tbody/tr["+rVal+"]/td[9]/a[3]"));	
    		scrollInnerScrollBar(webelementFrame);
    		
    		click(By.xpath("//table[@id='tasks_table']/tbody/tr["+rVal+"]/td[9]/a[3]"));
    		thread();
    		//System.out.println("Row Number "+rVal);
    		   			   		
    		String[] val=ll.get(rVal).split(",");
    		for(int i=0;i<val.length;i++)
    		{
    			
    			//System.out.println(" "+val[i]);	   			
    			if(i==1)
    			{
    				try{
    				getObjectText(viewTskId);
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
    			else if(i==2)
    			{
    				try{
    				getObjectText(viewTskName);
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
    			else if(i==3)
    			{
    				try{	   					
    	   			getObjectText(viewTskType);
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
    			else if(i==9)
    			{
    				try{	   					
    	   			getObjectText(viewTskCmnts);
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
   		webElement(viewTskCloseBtn);
   		scrollInnerScrollBar(webelementFrame);
   		
   		click(viewTskCloseBtn);  //close button in view page
   		thread();
       	}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/* @Test(priority=6)
	  void logout() throws InterruptedException
	  {
		ll.logoutSession();
		thread();
		closeWindow();
	  }*/
			
}

