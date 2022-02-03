package Scripts;
import UIWrappers.Page;
import jxl.read.biff.BiffException;

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

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Assets.*;
import static ObjectRepository.Locations.*;


public class Ast_ImportAndExport extends Page {
	
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
	void importAssetCSVFile() throws InterruptedException, IOException
	{
		click(assetsInMainMenu);
		waitForPageLoad();
		click(astImportBtn);
		thread();
		click(astUploadFile);
		thread();
		uploadFile("D:\\SIB\\ImportFiles\\Assets.csv");
		Thread.sleep(3000);;
		click(astImpSubmitBtn);
		
        verifyAssert(astSearchBox);
		
		getObjectText(msgNotificationBar);
		takeScreenshot();
		verifyAssertEquals("31 Asset(s) imported successfully" , getActualObjectTxt);	
		thread();
		thread();
		}
	
	@Test(priority=2)
	void verifyAssetImportCancelBtn() throws InterruptedException
	{
		thread();
		Thread.sleep(5000);
		WebElement imp=driver.findElement(astImportBtn);
		Assert.assertTrue(imp.isDisplayed());
		thread();
		webElement(astImportBtn);
		scrollInnerScrollBar(webelementFrame);
		thread();
		click(astImportBtn);
		thread();
		click(astImportCancelBtn);
		thread();
		verifyAssert(astSearchBox);
		thread();
	}
	
	@Test(priority=3)
	void assetImportHelpDoc() throws InterruptedException, AWTException
	{
		thread();
		webElement(astImportBtn);
		scrollInnerScrollBar(webelementFrame);
		thread();
		click(astImportBtn);
		thread();
		click(astHelpDocBtn);
		thread();
		download();
		thread();	
	}
	

    @Test(priority=4)
	void exportAssetCSVFile() throws InterruptedException, AWTException, IOException
	{
    	
		click(assetsInMainMenu);
		waitForPageLoad();
		
		webElement(astListViewRecsInfo);
		scrollInnerScrollBar(webelementFrame);
		
		getObjectText(astListViewRecsInfo);
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
		
		click(astExportBtn);
		thread();
		download();
		thread();
    	
    	    	
	}
    
    @Test(priority=5,dependsOnMethods={"exportAssetCSVFile"})
    void validateAssetsExportedCsvFile()
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
       		
       		
       		WebElement selectDD=driver.findElement(By.xpath("//div[@id='assets_table_length']/label/select"));
       		Select sel=new Select(selectDD);
       		sel.selectByValue("100");
       		thread();
       		
       		getTotalValuesIndd(By.xpath("//table[@id='assets_table']/tbody/tr"));	   		
       	    int pgCnt=Integer.parseInt(totalEntries);
       	    
       for(rVal=1;rVal<=pgCnt;rVal++)    	   
 		{
    	   
 		thread(); 		
 		String total=driver.findElement(By.xpath("//table[@id='assets_table']/tbody/tr["+rVal+"]/td[10]")).getText().replaceAll(",", "");//total val
 		
 		webElement(By.xpath("//table[@id='assets_table']/tbody/tr["+rVal+"]/td[12]/a[3]"));	
 		scrollInnerScrollBar(webelementFrame);
 		
 		click(By.xpath("//table[@id='assets_table']/tbody/tr["+rVal+"]/td[12]/a[3]"));
 		thread();
 		//System.out.println("Row Number "+rVal);
 		

 		String[] val=ll.get(rVal).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
 		
 		//String[] val=ll.get(rVal).split(",");
 		
 		
 		for(int i=0;i<val.length;i++)
 		{ 			
 			//System.out.println(" "+val[i]);	   			
 			if(i==1)
 			{
 				try{
 				getObjectText(viewAstName);
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
 				getObjectText(viewAstType);
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
 	   			getObjectText(viewAstStatus);
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
 	   			getObjectText(viewAstId);
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
 	   			getObjectText(viewAstMfdName);
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
 	   			getObjectText(viewAstSerialNo);
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
 			else if(i==7)
 			{
 				try{	   					
 	   			getObjectText(viewAstModlNo);
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
 	   			getObjectText(viewAstReplaceVal);
 	   			if(getActualObjectTxt.trim().equals("No Data"))
 				{
 					getActualObjectTxt="";
 				}
 		   		verifyAssertEquals(val[i].replaceAll("\"", "").replaceAll(",", "").trim(),getActualObjectTxt.replaceAll(",", "").trim());
 				}catch(Error e)
 				{
 					System.out.println("No data "+e);
 				}
 			}
 			else if(i==9)
 			{
 				try{	   					
 	   			getObjectText(viewAstQuant);
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
 	   			if(total.trim().equals("No Data"))
 				{
 	   			total="";
 				}
 		   		verifyAssertEquals(val[i].replaceAll("\"", "").replaceAll(",", "").trim(),total.replaceAll(",", "").trim());
 				}catch(Error e)
 				{
 					System.out.println("No data "+e);
 				}
 			}
 			else if(i==val.length-3)
 			{
 				//i=val.length-3;
 				try{	   					
 	   			getObjectText(viewAstCmnts);
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
		webElement(viewAstCloseBtn);
		scrollInnerScrollBar(webelementFrame);
		
		click(viewAstCloseBtn);  //close button in view page
		thread();
    	}
    	}
    	catch(Exception e)
    	{
    		
    	}
    }
	
	/*@Test(priority=5)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		closeWindow();
	}*/



}
