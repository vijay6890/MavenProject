package Scripts;


import static UIWrappers.UIObjects.*;
import static ObjectRepository.Locations.msgNotificationBar;
import static ObjectRepository.Locations.viewLocAddr;
import static ObjectRepository.Locations.viewLocAltMngr;
import static ObjectRepository.Locations.viewLocBuilt;
import static ObjectRepository.Locations.viewLocCity;
import static ObjectRepository.Locations.viewLocCntry;
import static ObjectRepository.Locations.viewLocFax;
import static ObjectRepository.Locations.viewLocId;
import static ObjectRepository.Locations.viewLocManager;
import static ObjectRepository.Locations.viewLocName;
import static ObjectRepository.Locations.viewLocPh;
import static ObjectRepository.Locations.viewLocState;
import static ObjectRepository.Locations.viewLocStatus;
import static ObjectRepository.Locations.viewLocType;
import static ObjectRepository.Locations.viewLocZipCode;
import static ObjectRepository.Threats.*;

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

public class Thr_Export extends Page {	
	
     String totalEntries;
     public String downloadPath="D:\\SIB\\Downloads";
     int rVal,appRows,total=1,cnt,navCnt,navCnt1,j;
     
	 LoginLogout ll = new LoginLogout();	 

		@Test(priority=0)
		void login() throws BiffException, IOException, InterruptedException
		{
			ll.loginToSIB();
			
		}
	 
		@Test(priority=1)
		void verifyThreatsExportButton() throws InterruptedException, AWTException
		{
		    
			  click(threatsInMainMenu);	
			  thread();
			  
			  webElement(thrListViewRecsInfo);
				scrollInnerScrollBar(webelementFrame);
				
				getObjectText(thrListViewRecsInfo);
				thread();
				totalEntries = getActualObjectTxt.split(" ")[5];
				
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
			
			  click(thrExportBtn);
			  thread();
			  download();
			  thread();
		    
		   
		}
		
		@Test(priority=2,dependsOnMethods={"verifyThreatsExportButton"})
		void validateThreatsExportCSVFile() throws InterruptedException, IOException
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
	   		System.out.println("validation successfully completed");
	   		}
	   		catch(Error e)
	   		{
	   			System.out.println("validation unsuccessful "+e);
	   		}	
	   		
	   		
	   	/*	WebElement selectDD=driver.findElement(By.xpath("//div[@id='threat_table_length']/label/select"));
	   		Select sel=new Select(selectDD);
	   		sel.selectByValue("25");
	   		thread();*/
	   		
	   		getTotalValuesIndd(By.xpath("//table[@id='threat_table']/tbody/tr"));	   		
	   	    int pgCnt=Integer.parseInt(totalEntries);
	   	   
	   	    //pagination
	   	    webElement(By.xpath("//div[@id='threat_table_wrapper']/div[2]/div[2]/div/ul/li"));	
	   		scrollInnerScrollBar(webelementFrame);
	   		
	   	    getTotalValuesIndd(By.xpath("//div[@id='threat_table_wrapper']/div[2]/div[2]/div/ul/li"));
	   	   driver.findElement(By.xpath("//div[@id='threat_table_wrapper']/div[2]/div[2]/div/ul/li["+totalDDValCount+"]/a")).click();
			cnt=totalDDValCount-2;
			
   			String nav=driver.findElement(By.xpath("//div[@id='threat_table_wrapper']/div[2]/div[2]/div/ul/li["+cnt+"]/a")).getText();
	   	    navCnt=Integer.parseInt(nav);
   			
	   	    System.out.println("Navigation count "+cnt);
	   	    System.out.println("navCnt "+navCnt);
	   	    
	   	    navCnt1=1;
	   
	whileloop:   	   
	   	    while(total<=pgCnt&&navCnt1<=navCnt)
	   	    {
	   	   
	   	    
	   	    for(rVal=1;rVal<=25;rVal++)
	   		{
	   	  
	   	   // System.out.println("total \t"+total+"\t navCnt1 "+navCnt1);
	   	    
	   	    driver.findElement(By.xpath("//div[@id='threat_table_wrapper']/div[2]/div[2]/div/ul/li/a[text()='"+navCnt1+"']")).click();
	   		thread();
	   		webElement(By.xpath("//table[@id='threat_table']/tbody/tr["+rVal+"]/td[5]/a[3]"));	
	   		scrollInnerScrollBar(webelementFrame);
	   		
	   		click(By.xpath("//table[@id='threat_table']/tbody/tr["+rVal+"]/td[5]/a[3]"));
	   		thread();
	   		//System.out.println("Row Number "+rVal);
	   		   			   		
	   		//String[] val=ll.get(rVal).split(",");
	   		//String[] val=ll.get(rVal).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
	   		String[] val=ll.get(total).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
	   		
	   		for(int i=0;i<val.length;i++)
	   		{
	   			
	   			//System.out.println(" "+val[i]);	   			
	   			if(i==1)
	   			{
	   				try{
	   				getObjectText(viewThrNme);
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
	   				getObjectText(viewThrType);
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
	   	   			getObjectText(viewThrCmts);
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
	   			
	   			else{
	   				//
	   			}
	   			   			
	   		}//inner for loop
	   		
	   		thread();
	   		webElement(By.xpath("//div[@id='showdisplay_view']/fieldset/div[4]/button"));
	   		scrollInnerScrollBar(webelementFrame);
	   		
	   		click(By.xpath("//div[@id='showdisplay_view']/fieldset/div[4]/button"));  //close button in view page
	   		thread();
	   		
	   		
	   	    if(rVal==25)
	   		{
	   	       rVal=0;
	   			navCnt1++;     			
	   		}
	   	    total++;
	   	  	if(total>pgCnt)
	   	  	{
	   	  		break whileloop;
	   	  	}
	   		
		    }//outer for loop	   	    
	   	   
	   	    }// while
	   	   
			}//try
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		/* @Test(priority=2)
		  void logout() throws InterruptedException
		  {
			ll.logoutSession();
			thread();
			closeWindow();
		  }*/
		 

}
