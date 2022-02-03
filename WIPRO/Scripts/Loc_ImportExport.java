package Scripts;

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

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class Loc_ImportExport extends Page {
	
	String totalEntries;
	public String downloadPath="D:\\SIB\\Downloads";
	public int rVal,appRows;
			
		LoginLogout ll = new LoginLogout();
		
		@Test(priority=0)
		void login() throws BiffException, IOException, InterruptedException
		{
			ll.loginToSIB();
		}
		
		@Test(priority=1)
		void clickonLocationMainMenu() throws InterruptedException
		{
			 click(locationsInMainMenu);
			  thread();
		}
		
	/*	@Test(priority=2)
		void verifyImportBtn() throws InterruptedException, AWTException, IOException
		{
			click(locImportBtn);
			thread();
			click (locImportUploadBtn);
			thread();
		    uploadFile("C:\\Users\\Tamizhselvi\\Desktop\\Import file\\Locations.csv");
			click(locImportSubmitBtn);
			verifyAssert(locLocationsSearchBox);
			
			getObjectText(msgNotificationBar);
			takeScreenshot();
			verifyAssertEquals("2 Locations imported successfully" , getActualObjectTxt);	
			thread();
		}
		
		@Test(priority=3)
		void verifyCancelBtn() throws InterruptedException, IOException
		{
			click(locImportBtn);
			thread();
			click(locImportCancelBtn);
			verifyAssert(locLocationsSearchBox);
			takeScreenshot();	
			thread();
			
		}
		
		@Test(priority=4)
	    void verifyImportHelpDoc() throws InterruptedException, AWTException
		
		{
		
			click(locImportBtn);
			click(locImportHelpDocBtn);
			download();
			thread();
		}*/
		
		@Test(priority=5)
		void verifyLocationsExportButton() throws InterruptedException, AWTException
		{
            
			click(locationsInMainMenu);
			  thread();
			  
			  webElement(locListViewRecsInfo);
			  scrollInnerScrollBar(webelementFrame);
							
			  getObjectText(locListViewRecsInfo);
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
				
					click(locExportBtn);
					thread();
					download();
					thread();
			                   
		}
		
		
		@Test(priority=6,dependsOnMethods={"verifyLocationsExportButton"})
		void validateLocationsExportedCSVFile() throws AWTException, InterruptedException, IOException, BiffException
		{
		
			try
			{			
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
	   		//System.out.println("validation successfully completed");
	   		}
	   		catch(Error e)
	   		{
	   			System.out.println("validation unsuccessful "+e);
	   		}	   		
	   	   		
	   		for(int i=0;i<ll.size();i++)
	   		{
	   		
	   		//	System.out.println(ll.get(i));
	   		}	   		
	 	   	   		
	/*   		String[] data=csvRead1(downloadPath+"\\"+csvFileName);
	   		System.out.println("row details \n"+data[0]);
	   		
	   		
	   		webElement(By.xpath("//div[@id='facilities_table_wrapper']/div[2]/div[2]/div/ul/li[8]/a"));
	   		scrollInnerScrollBar(webelementFrame);
	   		thread();
	   		
	   		click(By.xpath("//div[@id='facilities_table_wrapper']/div[2]/div[2]/div/ul/li[8]/a"));
	   		thread(); */	   	
	   			   		
	   		WebElement selectDD=driver.findElement(By.xpath("//div[@id='facilities_table_length']/label/select"));
	   		Select sel=new Select(selectDD);
	   		sel.selectByValue("100");
	   		thread();
	   		
	   		getTotalValuesIndd(By.xpath("//table[@id='facilities_table']/tbody/tr"));	   		
	   	    int pgCnt=Integer.parseInt(totalEntries);
	   	    
	   	    
	   	/*    webElement(By.xpath("//div[@id='facilities_table_wrapper']/div[2]/div[2]/div/ul/li"));	
	   		scrollInnerScrollBar(webelementFrame);
	   		
	   		//System.out.println("inside if for pagination more than 25");
			getTotalValuesIndd(By.xpath("//div[@id='facilities_table_wrapper']/div[2]/div[2]/div/ul/li"));
			
			click(By.xpath("//div[@id='facilities_table_wrapper']/div[2]/div[2]/div/ul/li["+totalDDValCount+"]/a"));
			thread();
			int ttlPgCnt=totalDDValCount;
			int iter=ttlPgCnt-2;
			
			getObjectText(By.xpath("//div[@id='facilities_table_wrapper']/div[2]/div[2]/div/ul/li["+iter+"]/a"));
			itr=Integer.parseInt(getActualObjectTxt);
			System.out.println("pagination text values "+itr);
			
			click(By.xpath("//div[@id='facilities_table_wrapper']/div[2]/div[2]/div/ul/li/a[text()='1']"));*/
	   		//for(int rVal=1;rVal<=totalDDValCount;rVal++)
			
	next:		
	   		for(rVal=1;rVal<=pgCnt;rVal++)
	   		{
	   		thread();
	   		
	   	/*	if(rVal%25==0)
	   		{
	   		System.out.println("pg value before increment "+pg);	
	   		pg=pg+1;	
	   		//	for(int i=2;i<=itr;i++)
	   			while(pg<=itr)
	   			{
	   				System.out.println("pg val inside while "+pg);
	   				click(By.xpath("//div[@id='facilities_table_wrapper']/div[2]/div[2]/div/ul/li/a[text()='"+pg+"']"));
	   				thread();
	   				rVal=1;
	   				continue next;
	   			}
    			////div[@id='facilities_table_wrapper']/div[2]/div[2]/div/ul/li/a[text()='2']
	   		//break;
	   			
	   		}*/
	   		
	   		webElement(By.xpath("//table[@id='facilities_table']/tbody/tr["+rVal+"]/td[12]/a[3]"));	
	   		scrollInnerScrollBar(webelementFrame);
	   		
	   		click(By.xpath("//table[@id='facilities_table']/tbody/tr["+rVal+"]/td[12]/a[3]"));
	   		thread();
	   		//System.out.println("Row Number "+rVal);
	   		   			   		
	   		String[] val=ll.get(rVal).split(",");
	   		
	   		//System.out.println("rand row details \n"+ll.get(rVal));
	   		for(int i=0;i<val.length;i++)
	   		{
	   			
	   			//System.out.println(" "+val[i]);	   			
	   			if(i==1)
	   			{
	   				try{
	   				getObjectText(viewLocName);
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
	   				getObjectText(viewLocStatus);
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
	   			else if(i==3)
	   			{
	   				try{
	   	   				getObjectText(viewLocType);
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
	   			else if(i==4)
	   			{
	   				try
	   				{
	   				getObjectText(viewLocId);
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
	   				try
	   				{
	   				getObjectText(viewLocPh);
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
	   			else if(i==6)
	   			{
	   				try{
	   				getObjectText(viewLocFax);
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
	   			else if(i==7)
	   			{
	   				try{
	   				getObjectText(viewLocCity);
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
	   			else if(i==8)
	   			{
	   				try{
	   				getObjectText(viewLocState);
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
	   			else if(i==9)
	   			{
	   				try{
	   				getObjectText(viewLocCntry);
	   				if(getActualObjectTxt.trim().equals("No Data"))
	   				{
	   					getActualObjectTxt="";
	   				}
	   		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
	   				}
	   				catch(Error e)
	   				{
	   					System.out.println("No data "+e);
	   				}
	   			}
	   			else if(i==10)
	   			{
	   				try{
	   				getObjectText(viewLocZipCode);
	   				if(getActualObjectTxt.trim().equals("No Data"))
	   				{
	   					getActualObjectTxt="";
	   				}
	   		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
	   				}
	   				catch(Error e)
	   				{
	   					System.out.println("No data "+e);
	   				}
	   			}
	   			else if(i==11)
	   			{
	   				try{
	   				getObjectText(viewLocAddr);
	   				if(getActualObjectTxt.trim().equals("No Data"))
	   				{
	   					getActualObjectTxt="";
	   				}
	   		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
	   				}
	   				catch(Error e)
	   				{
	   					System.out.println("No data "+e);
	   				}
	   			}
	   			else if(i==12)
	   			{
	   				try
	   				{
	   				getObjectText(viewLocBuilt);
	   				if(getActualObjectTxt.trim().equals("No Data"))
	   				{
	   					getActualObjectTxt="";
	   				}
	   								
	   		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
	   				}
	   				catch(Error e)
	   				{
	   					System.out.println("No data "+e);
	   				}
	   			}
	   			else if(i==13)
	   			{
	   				try{
	   				getObjectText(viewLocManager);
	   				if(getActualObjectTxt.trim().equals("No Data"))
	   				{
	   					getActualObjectTxt="";
	   				}
	   		   		verifyAssertEquals(val[i].replaceAll("\"", "").replaceAll(" ", "").trim(),getActualObjectTxt.split(",")[0].trim().replaceAll(" ", ""));
	   				}
	   				catch(Error e)
	   				{
	   					System.out.println("No data "+e);
	   				}
	   			}
	   			else if(i==16)
	   			{
	   			try{
	   				getObjectText(viewLocAltMngr);
	   				if(getActualObjectTxt.trim().equals("No Data"))
	   				{
	   					getActualObjectTxt="";
	   				}
	   			
	   		   		verifyAssertEquals(val[i].replaceAll("\"", "").replaceAll(" ", "").trim(),getActualObjectTxt.split(",")[0].trim().replaceAll(" ", ""));
	   			}
	   		   	catch(Error e)
   				{
   					System.out.println("No data "+e);
   				}
	   			}
	   			else{
	   				//
	   			}
	   			   			
	   		}
	   		thread();
	   		webElement(By.xpath("//div[@id='showdisplay_view']/fieldset/div[6]/button"));
	   		scrollInnerScrollBar(webelementFrame);
	   		
	   		click(By.xpath("//div[@id='showdisplay_view']/fieldset/div[6]/button"));  //close button in view page
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



