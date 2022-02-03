package Scripts;

import static UIWrappers.UIObjects.*;
import static ObjectRepository.TaskGroups.*;

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

public class TskGrp_Export extends Page {
	
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
		void verifyTaskGroupExportButton() throws InterruptedException, AWTException
		{
					
			click(taskGroupsInMainMenu);
			thread();		
			
			webElement(tskGrpListViewRecsInfo);
			scrollInnerScrollBar(webelementFrame);
			getObjectText(tskGrpListViewRecsInfo);
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
			
			click(tskGrpExportBtn);
			thread();
			download();
			thread();
		
		}
		
		
		@Test(priority=2,dependsOnMethods={"verifyTaskGroupExportButton"})
		void validateTaskGroupExportedCSVFile()
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
	       		
	       		WebElement selectDD=driver.findElement(By.xpath("//div[@id='task_group_table_length']/label/select"));
	       		Select sel=new Select(selectDD);
	       		sel.selectByValue("100");
	       		thread();
	       		
	       		getTotalValuesIndd(By.xpath("//table[@id='task_group_table']/tbody/tr"));	   		
	       	    int pgCnt=Integer.parseInt(totalEntries);
	       	    
	       	    
	       	    for(rVal=1;rVal<=pgCnt;rVal++)
	    		{
	    		thread();
	    		webElement(By.xpath("//table[@id='task_group_table']/tbody/tr["+rVal+"]/td[4]/a[3]"));	
	    		scrollInnerScrollBar(webelementFrame);
	    		
	    		click(By.xpath("//table[@id='task_group_table']/tbody/tr["+rVal+"]/td[4]/a[3]"));
	    		thread();
	    		//System.out.println("Row Number "+rVal);
	    		   			   		
	    		String[] val=ll.get(rVal).split(",");
	    		for(int i=0;i<val.length;i++)
	    		{
	    			
	    			//System.out.println(" "+val[i]);	   			
	    			if(i==1)
	    			{
	    				try{
	    				getObjectText(viewTskGrpName);
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
	    				getObjectText(viewTskGrpCmnts);
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
	   		webElement(viewTskGrpCloseBtn);
	   		scrollInnerScrollBar(webelementFrame);
	   		
	   		click(viewTskGrpCloseBtn);  //close button in view page
	   		thread();
	       	}
			}
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
