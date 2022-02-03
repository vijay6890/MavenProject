package Scripts;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Locations.*;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.Contacts.*;
import static ObjectRepository.Insurance.*;
import static ObjectRepository.Employees.*;

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

public class Ins_ImportExport extends Page {
	
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
	void clickonInsuranceMainMenu() throws InterruptedException
	{
		click(insuranceInMainMenu);
		 thread();
	}
	
    @Test(priority=2)
	void verifyInsuranceImportButton() throws InterruptedException, AWTException, IOException
	{
		click(insImportBtn);
		thread();
		
		click(insImportUploadBtn);
		thread();
		//uploadFile("C:\\Users\\Tamizhselvi\\Desktop\\Import file\\Insurance.csv");
		uploadFile("D:\\SIB\\ImportFiles\\Insurance.csv");
		thread();
		webElement(insImportSubmitBtn);
		scrollInnerScrollBar(webelementFrame);
		click(insImportSubmitBtn);
		verifyAssert(insInsuranceSearchBox);
		
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("14 Insurance(s) imported successfully" , getActualObjectTxt);	
		thread();
	       
	}
	
	@Test(priority=3)
	void verifyInsuranceCancelBtn() throws InterruptedException
	{
		click(insImportBtn);
		thread();
		click(insImportCancelBtn);
		verifyAssert(insInsuranceSearchBox);
		thread();
	}
	
	@Test(priority=4)
	void verifyInsuranceImportHelpDoc() throws InterruptedException, AWTException
	
	{
	
		click(insImportBtn);
		click(insImportHelpDocBtn);
		thread();
		  download();
		 thread();
	}
	
	@Test(priority=5)
	void verifyInsuranceExportBtn() throws InterruptedException, AWTException
	{
		
		click(insuranceInMainMenu);
		thread();
		
		webElement(insListViewRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		getObjectText(insListViewRecsInfo);
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
			 
		click(insExportBtn);
		thread();
	    download();
	    thread();
		
	}
	
	@Test(priority=6,dependsOnMethods={"verifyInsuranceExportBtn"})
	void validateInsuranceExportedCsvFile()
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
	       		
	       		WebElement selectDD=driver.findElement(By.xpath("//div[@id='insurance_table_length']/label/select"));
	       		Select sel=new Select(selectDD);
	       		sel.selectByValue("100");
	       		thread();
	       		
	       		getTotalValuesIndd(By.xpath("//table[@id='insurance_table']/tbody/tr"));	   		
	       	    int pgCnt=Integer.parseInt(totalEntries);
	       	    
	        	 for(rVal=1;rVal<=pgCnt;rVal++)
	        		{
	        		thread();
	        		webElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+rVal+"]/td[10]/a[3]"));	
	        		scrollInnerScrollBar(webelementFrame);
	        		
	        		click(By.xpath("//table[@id='insurance_table']/tbody/tr["+rVal+"]/td[10]/a[3]"));
	        		thread();
	        		//System.out.println("Row Number "+rVal);
	        		   			   		
	        		String[] val=ll.get(rVal).split(",");
	        		for(int i=0;i<val.length;i++)
	        		{
	        			
	        			//System.out.println(" "+val[i]);	   			
	        			if(i==1)
	        			{
	        				try{
	        				getObjectText(viewInsName);
	        		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.trim());
	        				}catch(Error e)
	        				{
	        					System.out.println("No data "+e);
	        				}
	        			}
	        			else if(i==2)
	        			{
	        				try{
	        				getObjectText(viewInsNo);
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
	        	   			getObjectText(viewInsType);
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
	        	   			getObjectText(viewInsInsurer);
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
	        	   			getObjectText(viewInsPre);
	        	   			if(getActualObjectTxt.trim().equals("No Data"))
	        				{
	        					getActualObjectTxt="";
	        				}
	        		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.replaceAll(",","").trim());
	        				}catch(Error e)
	        				{
	        					System.out.println("No data "+e);
	        				}
	        			}
	        			else if(i==7)
	        			{
	        				try{	   					
	        	   			getObjectText(viewInsExpDt);
	        	   			if(getActualObjectTxt.trim().equals("No Data"))
	        				{
	        					getActualObjectTxt="";
	        				}
	        		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.replaceAll(",","").trim());
	        				}catch(Error e)
	        				{
	        					System.out.println("No data "+e);
	        				}
	        			}
	        			else if(i==8)
	        			{
	        				try{	   					
	        	   			getObjectText(viewInsId);
	        	   			if(getActualObjectTxt.trim().equals("No Data"))
	        				{
	        					getActualObjectTxt="";
	        				}
	        		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.replaceAll(",","").trim());
	        				}catch(Error e)
	        				{
	        					System.out.println("No data "+e);
	        				}
	        			}
	        			else if(i==9)
	        			{
	        				try
	        				{
	        	   			getObjectText(viewIncAdmin);
	        	   			if(getActualObjectTxt.trim().equals("No Data"))
	        				{
	        					getActualObjectTxt="";
	        				
	        	   			try
	        	   			{
	        		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.replaceAll(",","").trim());
	        				}catch(Error e)
	        				{
	        					System.out.println("No data "+e);
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
	        				}
	        				catch(Exception e)
	        				{
	        					e.printStackTrace();
	        				}
	        	   			click(insuranceInMainMenu);
	        	   			thread();
	        	   			
	        	   			webElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+rVal+"]/td[10]/a[3]"));	
        	        		scrollInnerScrollBar(webelementFrame);
        	        		
        	        		click(By.xpath("//table[@id='insurance_table']/tbody/tr["+rVal+"]/td[10]/a[3]"));
        	        		thread();
	        			}
	        			
	        			else if(i==11)
	        			{
	        			   	try
	        			   	{
	        	   			getObjectText(viewIncCnt);
	        	   			if(getActualObjectTxt.trim().equals("No Data"))
	        				{
	        					getActualObjectTxt="";
	        				
	        	   			try
	        	   			{
	        	   			verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.replaceAll(",","").trim());
	        				}
	        	   			catch(Error e)
	        				{
	        					System.out.println("No data "+e);
	        				}
	        				}
	        	   			
	        	   		
	        	   			//if(!getActualObjectTxt.trim().equals("No Data"))
	        	   			else
	        	   			{
	        	   				thread();
	        	   				click(contactsInMainMenu);
	        	   				thread();
	        	   				waitForPageLoad();
	        	   				enterText(cntContactsSearchBox, getActualObjectTxt.trim().split(",")[0]);
	        	   				enterKeyInKyBord(cntContactsSearchBox);
	        	   				thread();
	        	   				driver.findElement(By.xpath("//table[@id='contacts_table']/tbody/tr/td[11]/a[3]")).click();//view button
	        	   				thread();
	        	   				webElement(By.xpath("//div[@id='v_cont_bemail']"));	
	        	        		scrollInnerScrollBar(webelementFrame);
	        	        		
	        	   				String cntEmail=driver.findElement(By.xpath("//div[@id='v_cont_bemail']")).getText();//Email data
	        	   				
	        	   				thread();
	        	   				if(!cntEmail.equals("No Data"))
	        	   				{
	        	        		try
		        	   			{
		        	   			verifyAssertEquals(val[i].replaceAll("\"", "").trim(),cntEmail.trim());
		        				}
		        	   			catch(Error e)
		        				{
		        					System.out.println(e);
		        				}
	        	   				}
	        	   			}
	        			   	}
	        			   	catch(Exception e)
	        			   	{
	        			   		e.printStackTrace();
	        			   	}
	        	   			click(insuranceInMainMenu);
	        	   			thread();
	        	   			
	        	   			webElement(By.xpath("//table[@id='insurance_table']/tbody/tr["+rVal+"]/td[10]/a[3]"));	
        	        		scrollInnerScrollBar(webelementFrame);
        	        		
        	        		click(By.xpath("//table[@id='insurance_table']/tbody/tr["+rVal+"]/td[10]/a[3]"));
        	        		thread();
	        		   		
	        			}
	        			else if(i==13)
	        			{
	        				try{	   					
	        	   			getObjectText(viewInsCmnts);
	        	   			if(getActualObjectTxt.trim().equals("No Data"))
	        				{
	        					getActualObjectTxt="";
	        				}
	        		   		verifyAssertEquals(val[i].replaceAll("\"", "").trim(),getActualObjectTxt.replaceAll(",","").trim());
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
	       		webElement(viewInsCloseBtn);
	       		scrollInnerScrollBar(webelementFrame);
	       		
	       		click(viewInsCloseBtn);  //close button in view page
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



