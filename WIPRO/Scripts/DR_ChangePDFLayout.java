package Scripts;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.DRPlan.*;
import static UIWrappers.UIObjects.*;
import static ObjectRepository.Locations.*;
import static ObjectRepository.Threats.*;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import UIWrappers.Page;
import jxl.read.biff.BiffException;

public class DR_ChangePDFLayout extends Page
{
	LoginLogout ll = new LoginLogout();	
	
/*	@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}    */

	@Test(priority=1)
	void pdfLayoutSave() throws InterruptedException, IOException
	{
		click(drThreatsnDRPlanInMainMenu);
		thread();
		
		click(drChangPdfLayOutBtn);
		thread();
		
		getObjectText(pdfLayoutPageTitle);
		verifyAssertEquals("DR Plan PDF Front Cover",getActualObjectTxt);
		
		Actions act=new Actions(driver);		
	/*	//Logo size Slider
		WebElement logoSize=driver.findElement(logoSizeSlider);
		
		 Random rand=new Random();
		 int rl=rand.nextInt(100);
		 System.out.println("random value "+rl);
		 thread();
		 
		 act.moveToElement(logoSize).click().dragAndDropBy(logoSize,rl ,0 ).build().perform();*/
		 
		 //Border Width Slider
		 WebElement borderSize=driver.findElement(borderWidthSlider);
		 Random rand2=new Random();
		 int rb=rand2.nextInt(100);
		 System.out.println("random value "+rb);
		 thread();
		 act.moveToElement(borderSize).click().dragAndDropBy(borderSize,rb ,0 ).build().perform();
		 
		//Border Space Slider
	    Random rand1=new Random();
	    int rv=rand1.nextInt(100);
	    System.out.println("random value "+rv);
	    thread();
	    
	    WebElement BorderSpace=driver.findElement(borderSpaceSlider);	  
	    act.moveToElement(BorderSpace).click().dragAndDropBy(BorderSpace,rv ,0 ).build().perform();
	   thread();
	   
	   click(borderColour);
	   thread();
	   WebElement clr=driver.findElement(By.xpath("//div[@id='color_picker']/div/div/div[2]/div[1]/div[2]/div[3]"));
	   clr.sendKeys(Keys.ARROW_DOWN);
	   clr.click();
	   thread();
	   WebElement clrShade=driver.findElement(By.xpath("//div[@id='color_picker']/div/div/div[2]/div[1]/div[2]/div[1]/div/div"));
	   clrShade.sendKeys(Keys.ARROW_DOWN);
	   clrShade.click();
	   thread();
	   
	   click(pdfLayoutEditor);
	   
	   /* WebElement frame=driver.findElement(By.id("sceiframe"));
	   switchToWEFrame(frame);
	   thread();
	   WebElement text=driver.findElement(pdfLayoutEditorTextBox);
	   text.sendKeys(Keys.END);
	   thread();
	   enterText(pdfLayoutEditorTextBox,"text for edit operation");
	   text.sendKeys(Keys.ENTER);
	   thread();
	   text.sendKeys("Text for editing the pdf layout editor information");
	   thread();
	   switchBackFromFrame();*/
	   
	   webElement(drscEdtrFrame);
		switchToWEFrame(webelementFrame);
		
		driver.findElement(drscEdtrCommentsField).sendKeys(Keys.chord(Keys.CONTROL,"a"));
		thread();
		driver.findElement(drscEdtrCommentsField).sendKeys(Keys.ARROW_RIGHT);
		thread();
		driver.findElement(drscEdtrCommentsField).sendKeys(Keys.ENTER);
		thread();
		
		//useKeyFuncToRmvVal(drscEdtrCommentsField);
		enterText(drscEdtrCommentsField, "text");
		thread();
		// enterText(pdfLayoutEditorTextBox,"text for edit operation");
		switchBackFromFrame();
		takeScreenshot();
	   
	   click(pdfLayoutEditorSaveBtn);
	   
//	}
	    webElement(pdfLayoutSaveBtn);        	
		scrollInnerScrollBar(webelementFrame);
		thread();
		
		click(pdfLayoutSaveBtn);
		thread();
		
		getObjectText(msgNotificationBar);
		verifyAssertEquals("PDF layout has been saved successfully.",getActualObjectTxt);
		thread();
		Thread.sleep(5000);
		
	}
	
	@Test(priority=2)
	void pdfLayoutRestoreDefault() throws InterruptedException
	{
		click(drChangPdfLayOutBtn);
		thread();
		
		getObjectText(pdfLayoutPageTitle);
		verifyAssertEquals("DR Plan PDF Front Cover",getActualObjectTxt);
		
		 webElement(pdfLayoutSaveBtn);        	
			scrollInnerScrollBar(webelementFrame);
			thread();
		
		click(pdfLayoutRestoreBtn);
		thread();
		verifyAssert(deleteConfPopup);
		
		getObjectText(deleteConfPpMessage);      //pop up message				
		verifyAssertEquals("PDF layout will be restored to default, are you sure to proceed?",getActualObjectTxt);
		click(delConfOkBtn);
		thread();
				
		getObjectText(msgNotificationBar);
		thread();
		verifyAssertEquals("PDF layout has been restored to default successfully.", getActualObjectTxt);		
		thread();
	} 
	
	@Test(priority=3)
	void pdfLayoutCancel() throws InterruptedException
	{
	
	//click(drChangPdfLayOutBtn);
	//	thread();
		
		//getObjectText(pdfLayoutPageTitle);
	//	verifyAssertEquals("DR Plan PDF Front Cover",getActualObjectTxt);
		
		webElement(pdfLayoutSaveBtn);        	
		scrollInnerScrollBar(webelementFrame);
		thread();
		
		click(pdfLayoutCancelBtn);
		thread();
		verifyAssert(drDRPlanSearchBox);
		
	}
	
	/*@Test(priority=4)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		thread();
		driver.quit();
	}  */      

}
