package Scripts;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.BusinessFunctions.*;
import static ObjectRepository.Employees.*;
import static ObjectRepository.Locations.*;
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

public class BFunc_ImportExport extends Page {
	
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
	void clickonBusinessFuncMainMenu() throws InterruptedException
	{
		click(businessFunctionsInMainMenu);
		  thread();
	}
	

	@Test(priority=2)
	void verifyBusiFunctionsImportBtn() throws InterruptedException, AWTException, IOException
	{
		click(bsfImportBtn);
		thread();
		
		click (bsfImportUploadBtn);
		thread();
	    //uploadFile("C:\\Users\\Tamizhselvi\\Desktop\\Import file\\BusinessFunctions.csv");
		uploadFile("D:\\SIB\\ImportFiles\\BusinessFunctions.csv");		
	    thread();
		click(bsfImportSubmitBtn);
	    verifyAssert(bsfBusinessFunctionsSearchBox);
		
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("18 Business Function(s) imported successfully" , getActualObjectTxt);	
		thread();
		
		
	}
	
	@Test(priority=3)
	void verifyBusiFunctionsCancelBtn() throws InterruptedException, IOException
	{
		click(bsfImportBtn);
		thread();
		click(bsfImportCancelBtn);
		verifyAssert(bsfBusinessFunctionsSearchBox);
		takeScreenshot();		
		
	}
	
	@Test(priority=4)
    void verifyBusiFunctionsImportHelpDoc() throws InterruptedException, AWTException
	
	{
	
		click(bsfImportBtn);
		click(bsfImportHelpDocBtn);
		thread();
		download();
		thread();
	}
	
	
	@Test(priority=5)
	void verifyBusiFunctionsExportBtn() throws AWTException, InterruptedException
	{
		click(businessFunctionsInMainMenu);
		thread();
		  
		webElement(bsfListViewRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		getObjectText(bsfListViewRecsInfo);
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
		
		  click(bsfExportBtn);
		  thread();
		  
		   download();
           thread();
           
	}
	
	@Test(priority=6,dependsOnMethods={"verifyBusiFunctionsExportBtn"})
	void validateBusiFunctionsExportedCsvFiles()
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
       		
       		WebElement selectDD=driver.findElement(By.xpath("//div[@id='process_table_length']/label/select"));
       		Select sel=new Select(selectDD);
       		sel.selectByValue("100");
       		thread();
       		
       		getTotalValuesIndd(By.xpath("//table[@id='process_table']/tbody/tr"));	   		
       	    int pgCnt=Integer.parseInt(totalEntries);
       	    
       	 for(rVal=1;rVal<=pgCnt;rVal++)
 		{
 		thread();
 		webElement(By.xpath("//table[@id='process_table']/tbody/tr["+rVal+"]/td[9]/a[3]"));	
 		scrollInnerScrollBar(webelementFrame);
 		
 		click(By.xpath("//table[@id='process_table']/tbody/tr["+rVal+"]/td[9]/a[3]"));
 		thread();
 		//System.out.println("Row Number "+rVal);
 		   			   		
 		String[] val=ll.get(rVal).split(",");
 		
 		for(int i=0;i<val.length;i++)
 		{
 			
 			//System.out.println(" "+val[i]);	   			
 			if(i==1)
 			{
 				try{
 				getObjectText(viewBsfName);
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
 				getObjectText(viewBsfId);
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
 				getObjectText(viewBsfprior);
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
 			else if(i==4)
 			{
 				try{
 				getObjectText(viewBsfDept);
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
 			else if(i==5)
 			{
 				try{
 				getObjectText(viewBsfRecTime);
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
 				getObjectText(viewBsfLossPerDay);
 				if(getActualObjectTxt.trim().equals("No Data"))
 				{
 					getActualObjectTxt="";
 				}
 		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.replaceAll(",", "").trim());
 				}catch(Error e)
 				{
 					System.out.println("No data "+e);
 				}
 			}
 			else if(i==7)
 			{
 				try{
 				getObjectText(viewBsfMinEmpReq);
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
 			else if(i==8)
 			{
 				try{
 				getObjectText(viewBsfObj);
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
 				getObjectText(viewBsfConseq);
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
 			else if(i==10)
 			{
 				try{
 				getObjectText(viewBsfCmnts);
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
 			else if(i==11)
 			{
 				try{
 				getObjectText(viewBsfOwnr);
 				if(getActualObjectTxt.trim().equals("No Data"))
 				{
 					getActualObjectTxt=""; 				
 				try
 				{
 		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.split(",")[0].trim());
 				}
 				catch(Error e)
 				{
 					e.printStackTrace();
 				}
 				}
 				else
	   			{
	   				click(employeesInMainMenu);
	   				thread();
	   				waitForPageLoad();
	   				enterText(empSearchBox, getActualObjectTxt.trim().split(",")[0]);
	   				enterKeyInKyBord(empSearchBox);
	   				thread();
	   				driver.findElement(By.xpath("//table[@id='resource_table']/tbody/tr/td[11]/a[3]")).click();//view button
	   				thread();
	   				webElement(By.xpath("//div[@id='v_res_email']"));	
	        		scrollInnerScrollBar(webelementFrame);
	        		
	   				String empEmail=driver.findElement(By.xpath("//div[@id='v_res_email']")).getText();//Email data
	   				
	   				thread();
	   				if(!empEmail.equals("No Data"))
	   				{
	        		try
    	   			{
    	   			verifyAssertEquals(val[i].replaceAll("\"", "").trim(),empEmail.trim());
    				}
    	   			catch(Error e)
    				{
    					System.out.println(e);
    				}
	   				}
	   			}
 				}catch(Error e)
 				{
 					System.out.println("No data "+e);
 				}
 				
 				click(businessFunctionsInMainMenu);
 				thread();
 				  
 				webElement(By.xpath("//table[@id='process_table']/tbody/tr["+rVal+"]/td[9]/a[3]"));	
 		 		scrollInnerScrollBar(webelementFrame);
 		 		
 		 		click(By.xpath("//table[@id='process_table']/tbody/tr["+rVal+"]/td[9]/a[3]"));
 		 		thread(); 				
 			}
 			else{
				//
			}
			   			
		}
		thread();
		webElement(viewBsfCloseBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(viewBsfCloseBtn);  //close button in view page
		thread();
    	}
       	    
       	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
