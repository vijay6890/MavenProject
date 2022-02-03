package UIWrappers;

import static ObjectRepository.Locations.*;


import static ObjectRepository.Threats.pagiEndArw;
import static ObjectRepository.Threats.pagiNextArw;
import static ObjectRepository.Threats.pagiPreviousArw;
import static ObjectRepository.Threats.pagiStartArw;
import static UIWrappers.UIObjects.*;

import static Config.TakScreenshot.takeScreenshot;
import static ObjectRepository.Loc_SubModule_Mapping.*;
import static org.testng.AssertJUnit.assertEquals;
import static ObjectRepository.Admin.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.google.common.base.Function;
import com.opencsv.CSVReader;

import Config.Base;



public class UIObjects extends Base {

	public static Logger logger = Logger.getLogger(UIObjects.class);
	
	public static String getActualObjectTxt, getActualObjectTxt1, getActlAlertTxt, getAttribtePh, getAttribteVal, getHighlightdTxt,getVisibleTxt;
	public static String getPageSource, ttlWindowsCnt,verifyObjDisplay;

	public static Workbook wb;
	public static Sheet loc, setting, emp, thr, notifi, drpln,rdata, tms, ast, astGrp,app, cnt, cntGrp, ins, admn, tsk, tskGrp, bsFunc, alrt,inc,mgDisastr,otherRltTab;
	public static Sheet multiLoc, multiThr, multiEmp;
	public static Sheet secDesc, secParagh, mgeDisastr, hme, valMsg;
	public static Sheet loginVal,unplanTsk,endDisMsgs,drplnNewsc,drnsc1,drTemp,socInfo;
	public static int lowrLmt = 2;
	public static int totalDDValCount;
	
	public static boolean chkObjDisplay, chkObjEnabled;
	public static WebElement webelementFrame, sourceObj, destinationObj;
	public static List<WebElement> getAllSelectedText;
	public static String testProject ,testPlan , testCase, testsuite, build, exception, result;
	
	
			////		OPEN URL		////
	public static void openUrl(String url)
	{
		driver.get(url);
		logger.info("Browser Opened");
	}
	
	
			////		ENTER A TEXT INTO INPUT FIELD		////	
	public static void enterText(By locator, String text)
	{
		driver.findElement(locator).sendKeys(text);
		logger.info("Entering "+locator+" value");
	}
	
	
			////		CLICK		////
	public static void click(By locator)
	{
		driver.findElement(locator).click();
		logger.info("Click on"+locator);
	}
	
	
			////		CLEAR		////
	public static void clear(By locator)
	{
		driver.findElement(locator).clear();		
	}	
	
	
			////		PRESS ENTER KEY IN KEYBOARD		////
	public static void enterKey()
	{
		Keyboard kb = ((Keyboard)driver);
		kb.pressKey(Keys.ENTER);
	}
	
	
			/////		ARROW DOWN		/////
	public static void arrowDown(By locator)
	{
		/*Keyboard arowDown = ((Keyboard)driver);
		arowDown.pressKey(Keys.ARROW_DOWN);*/
		driver.findElement(locator).sendKeys(Keys.ARROW_DOWN); 
	}
	
	
			////		USE KEYS TO CLEAR TEXTS		////
	public static void useKeyFuncToRmvVal(By locator) throws InterruptedException
	{
		click(locator);
		String sltAll = Keys.chord(Keys.CONTROL, "a");
			
		WebElement keys = driver.findElement(locator);
		thread();
		driver.findElement(locator).sendKeys(sltAll);
		thread();
		keys.sendKeys(Keys.DELETE);
	}
	
	
			////		WAIT		////
	public static void thread() throws InterruptedException
	{
		Thread.sleep(2000);
	}
	
	
			////		CLOSE CURRENT WINDOW		////
	public static void closeWindow()
	{
		driver.close();
	}
	
	
			////		CLOSE ALL WINDOWS		////
	public static void quitWindow()
	{
		driver.quit();
	}
	
	
			////		GET ALERT MESSAGE TEXT		////
	public static void getAlertMsgText()
	{
		getActlAlertTxt = driver.switchTo().alert().getText();
	}
	
	
			////		ACCEPT ALERT MESSAGE		////
	public static void acceptAlertMessage()
	{
		driver.switchTo().alert().accept();
	}
	
	
			/////		CHECK ALERT IS PRESENT		/////
	public static boolean isAlertPresent()
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}catch(NoAlertPresentException nalert)
		{
			return false;
		}
		
	}
	
	
			////		GET OBJECT TEXT		////
	public static void getObjectText(By locator)
	{
		getActualObjectTxt = driver.findElement(locator).getText();
	}
	
	
	public static void getObjectText1(By locator)
	{
		getActualObjectTxt1 = driver.findElement(locator).getText();
	}
	
	
			/////		VERIFY OBJECT DISPLAY		/////
	public static void verifyObjDisplay(By locator)
	{
		chkObjDisplay = driver.findElement(locator).isDisplayed();		
	}
	
	public static boolean verifyObjDisplay1(By locator)
	{
		return driver.findElement(locator).isDisplayed();		
	}
	
			/////		VERIFY OBJECT ENABLED		/////
	public static void verifyObjEnabled(By locator)
	{
		chkObjEnabled = driver.findElement(locator).isEnabled();
	}
	
	
			////	GET ATTRIBUTE VALUE		////
	public static void getAttributePh(By locator)
	{
		getAttribtePh = driver.findElement(locator).getAttribute("placeholder");
	}
	
	
	public static void getAttributeVal(By locator)
	{
		getAttribteVal = driver.findElement(locator).getAttribute("value");
	}
	
			
	public static void getHighlightOptn(By locator)
	{
		getHighlightdTxt = driver.findElement(locator).getAttribute("class");
	}
	
	
	
			////		WAIT FOR TEXT PRESENT		////
	public static void waitForTextPresent(By locator, String text)
	{
		try
		{
			WebDriverWait waitForTxt = new WebDriverWait(driver, 10);
			waitForTxt.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
			////		WAIT FOR PAGE LOAD		////
	public static void waitForPageLoad() 
	{
		Wait<WebDriver> wait = new WebDriverWait(driver, 10);
	    wait.until(new Function<WebDriver, Boolean>() {
	        public Boolean apply(WebDriver driver) {
	           /* System.out.println("Current Window State       : "
	                + */String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"));
	            return String
	                .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
	                .equals("complete");
	        }
	    });
	}
	
	
	
			////	GET PAGE SOURCE		////
	public static void pageSource()
	{
		getPageSource = driver.getPageSource();
	}
	
	
			////		SWITCH TO FRAME		////
	public static void switchToFrame(int locator)
	{
		driver.switchTo().frame(locator);
	}
	
	public static void switchToWEFrame(WebElement locator)
	{
		driver.switchTo().frame(locator);
	}
	
	
	
			////		SWITCH BACK FROM FRAME		////
	public static void switchBackFromFrame()
	{
		driver.switchTo().defaultContent();
	}
	
	
			/////		GET ALL WINDOWS		/////
	public static void getAllWindowsCnt()
	{
		Set<String> ttlWindowsCnt = driver.getWindowHandles();
	}
	
	
	
			//// 		SWITCH TO WINDOW		////
	public static void switchToWindow(String txt)
	{
		driver.switchTo().window(txt);
	}
	
	
			////		PRESS ENTER KEY FOR CHOOSING VALUES		////
	public static void enterKeyInKyBord(By locator)
	{
		driver.findElement(locator).sendKeys(Keys.ENTER);
	}
	
	
	
			////		GET TOTAL VALUES IN TABLE/DROPDOWN		////
	public static void getTotalValuesIndd(By locator)
	{
		totalDDValCount = driver.findElements(locator).size();
	}
	
	
	
			////		SELECT A TEXT FROM DROP DOWN		////
	public static void selectTextFromDropdown(By locator, String txt)
	{
		new Select(driver.findElement(locator)).selectByVisibleText(txt);
	}
	
	//// get  first selected text from drop down
	
	public static void getSelectedTxtFromDropdown(By locator)
	{
		getVisibleTxt=new Select(driver.findElement(locator)).getFirstSelectedOption().getText();
	}
	
	// get  all selected text from drop down
	
	public static void getAllSelectedTxtFromDropdown(By locator)
	{
		getAllSelectedText=new Select(driver.findElement(locator)).getAllSelectedOptions();
		
	}
	
	
	
			////		VERIFY TEXT/OBJECT PRESENT ON THE PAGE		////
	public static void verifyAssert(By locator)
	{
		try
		{
			Assert.assertEquals(driver.findElement(locator).isDisplayed(), true);
		}
		catch(Exception e)
		{
			System.out.println("\n*** "+locator+" Not Found...***");
		}
		/*try
		{
			assertTrue(actual);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/				
	}
	
	
	
			////		VERIFY TEXT EQUALS		////
	public static void verifyAssertEquals(String expected, String actual)
	{
		try
		{
			assertEquals(expected, actual);
		}
		catch(Exception e)
		{
			System.out.println("Verification Error: <"+expected+"> but was: <"+actual+">");
		}
	}	
	
	
	
			/////		PAGE REFRESH		/////
	public static void refreshThePage()
	{
		driver.navigate().refresh();
	}
	
	
	
			/////		WAIT FOR ELEMENT PRESENT		/////
	public static void waitForElement(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
	
			/////		GET TEST DATA		/////
	public static void getExcelFileLocationInLocal() throws BiffException, IOException
	{
		FileInputStream fis = new FileInputStream(xlFilesLocation);
		wb = Workbook.getWorkbook(fis);		
	}
	
	public static void getUnplannedTaskSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();		
		unplanTsk = wb.getSheet("UnplannedTask");
	}
	
	///            End Disaster Sheet
	public static void getEndDisasterSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();		
		endDisMsgs = wb.getSheet("EndDisaster");
	}
	
	
			/////		GET LOGIN PAGE VALIDATION SHEET		/////
	public static void getLoginSeetNameFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();		
		loginVal = wb.getSheet("loginPgValidation");
	}
	
	
			/////		GET LOCATION SHEET		/////
	public static void getLocSheetNameFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();		
		loc = wb.getSheet("Locations");
	}
	
	
			/////		GET EMPLOYEES SHEET		/////
	public static void getEmpSheetNameFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();		
		emp = wb.getSheet("Employees");
	}
	
	
	
			/////		GET THREATS SHEET		/////
	public static void getThreatsSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		thr = wb.getSheet("Threats");				
	}
	
	
			/////		TEAMS		/////
	public static void getTeamsSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		tms = wb.getSheet("Teams");				
	}
	
	
	
			/////		ASSETS		/////
	public static void getAssetsSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		ast = wb.getSheet("Assets");				
	}
	
	
	
			/////		ASSET GROUPS		/////
	public static void getAssetGroupsSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		astGrp = wb.getSheet("AssetGroups");				
	}
	

			/////		CONTACTS		/////
	public static void getContactsSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		cnt = wb.getSheet("Contacts");				
	}
	
	
	
			/////		CONTACT GROUPS		/////
	public static void getContactGroupsSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		cntGrp = wb.getSheet("ContactGroups");				
	}
	
	
	
			/////		INSURANCE		/////
	public static void getInsuranceSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		ins = wb.getSheet("Insurance");				
	}
	
	
			/////		TASKS		/////
	public static void getTasksSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		tsk = wb.getSheet("Tasks");				
	}
	
	
			/////		TASK GROUPS		/////
	public static void getTaskGroupsSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		tskGrp = wb.getSheet("TaskGroups");				
	}
	
		
			/////		BUSINESS FUNCTIONS		/////
	public static void getBusinessFunctionsSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		bsFunc = wb.getSheet("BusinessFunctions");				
	}	
	
	          //// Incidents ////
	public static void getIncidentsSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		inc= wb.getSheet("Incidents");
	}
	
	
			/////		ADMIN		/////
	public static void getAdminSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		admn= wb.getSheet("Admin");
	}
	
	public static void getNotificationMessageFromExcl() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		notifi = wb.getSheet("Notifications");
	}
	

	    ///  DR Plan Add and Edit
	public static void getDRPlanSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		drpln =wb.getSheet("DRPlan");
	}
	
	public static void getEmpOtherRltSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		otherRltTab =wb.getSheet("EmpOtherRltTab");
	}
	
	
	     // DR Plan Create New Section
	public static void getDRPlanCreateNewSectionSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		drplnNewsc =wb.getSheet("DRPlanSectionCreation");
	}
	
	
	    // Manage DR Template
	public static void getManageDRTemplateSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		drTemp =wb.getSheet("ManageDRTemplate");
	}
	
	
	public static void getValidationMsgSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();		
		valMsg =wb.getSheet("ValidationMsg");
	}
	
	
			/////		ALERTS		/////
	public static void getAlertsSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		alrt = wb.getSheet("Alerts");
	}
	
	
	      /////  ManageDisaster ////
	public static void getManageDisasterSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		mgDisastr = wb.getSheet("ManageDisaster");
	}
	
	
	///// Settings ///////
	public static void getSettingsSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		setting=wb.getSheet("Settings");
		
	}
	
	
	///// App Download Phone Number//
	public static void getAppPhoneNoSheetFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		app = wb.getSheet("AppDownload");
	}
	
	//// Profile_Social Information
	public static void getSocialInformationFromExcel() throws BiffException, IOException
	{
		getExcelFileLocationInLocal();
		socInfo = wb.getSheet("SocialInformation");
	}
	
	
	
		
	
			/////		MAPPING		/////
	public static void doMapping() throws InterruptedException
	{
		for(int i=0; i<3; i++)
		{
			getTotalValuesIndd(mapPopupLftTtlVal);
			Random random = new Random();
			int ranNm = random.nextInt(totalDDValCount-1)+1;
			String NameForSearch = driver.findElement(By.xpath("//select[@id='bootstrap-duallistbox-nonselected-list_map_list[]']/option["+ranNm+"]")).getText();
						
			//	Enter Name for Search
			enterText(mapPopupLftSearchBox, NameForSearch);
			getObjectText(mapPopupFirstNameInLftBox);
			//	Verify Search Result In List
			verifyAssertEquals(NameForSearch, getActualObjectTxt);
			click(mapMoveAllArrow);
			
			clear(mapPopupLftSearchBox);
			thread();
			
			//	Search Mapped Name
			enterText(mapPopupRgtSearchBox, NameForSearch);
			getObjectText(mapPopupFirstNameInRgtBox);
			//	Verify Mapped Name
			verifyAssertEquals(NameForSearch, getActualObjectTxt);
			clear(mapPopupRgtSearchBox);
		}
	}
	
	public static void doMappingt() throws InterruptedException
	{
		String NameForSearch;
		for(int i=0; i<2; i++)
		{
			getTotalValuesIndd(mapPopupLftTtlVal);
			try
			{
			if(totalDDValCount==1)
			{
				 NameForSearch = driver.findElement(By.xpath("//select[@id='bootstrap-duallistbox-nonselected-list_map_list[]']/option[1]")).getText();
				
			}
			else
			{
			Random random = new Random();
			int ranNm = random.nextInt(totalDDValCount-1)+1;
			NameForSearch = driver.findElement(By.xpath("//select[@id='bootstrap-duallistbox-nonselected-list_map_list[]']/option["+ranNm+"]")).getText();
			}			
			//	Enter Name for Search
			enterText(mapPopupLftSearchBox, NameForSearch);
			
			getObjectText(mapPopupFirstNameInLftBox);
			try
			{
			//	Verify Search Result In List
			//verifyAssertEquals(NameForSearch, getActualObjectTxt);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			click(mapMoveAllArrow);
			//click(mapMoveArrow);
			clear(mapPopupLftSearchBox);
			thread();
			
		
			
			//	Search Mapped Name
			enterText(mapPopupRgtSearchBox, NameForSearch);
			getObjectText(mapPopupFirstNameInRgtBox);
			try
			{
			//	Verify Mapped Name
		//	verifyAssertEquals(NameForSearch, getActualObjectTxt);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			clear(mapPopupRgtSearchBox);
			
			}
			catch(Exception e)
			{
				System.out.println("No records to map");
			
			}
		}
	}
	
	
	///     SUB MODULE MAPPING Employees To Teams     ///
	
	public static void subMapping() throws InterruptedException
	{
		for(int i=0; i<=5; i++)
		{
		// getTotalValuesIndd(subMapLftTtlVal);
		 Random random=new Random();
		 int ran = random.nextInt(totalDDValCount-1)+1;
		 String NameForSearch=driver.findElement(By.xpath("//select[@id='bootstrap-duallistbox-nonselected-list_map_list[]']/option["+ran+"]")).getText();
          click(subMapRemoveAllArrow);
		//	Enter Name for Search
			enterText(subLftSearchBox, NameForSearch);
			getObjectText(subMapPopupFirstNameInLftBox);
			verifyAssertEquals(NameForSearch, getActualObjectTxt);
            click(subMapMoveAllArrow);
			
            clear(subLftSearchBox);
			thread();
		//	Search Mapped Name
			enterText(subRgtSearchBox, NameForSearch);
			getObjectText(submapPopupFirstNameInRgtBox);
			//	Verify Mapped Name
			verifyAssertEquals(NameForSearch, getActualObjectTxt);
			clear(subRgtSearchBox);

		}
	}
	
		/////		MOUSE HOVER		/////
	public static void mouseHoverOn(WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	
	  /////		WEB ELEMENT		/////
	public static void webElement(By locator)
	{
		webelementFrame = driver.findElement(locator);
	}
	
	public static void sourceElement(By locator)
	{
		sourceObj = driver.findElement(locator);
	}
	
	public static void destinationElement(By locator)
	{
		destinationObj = driver.findElement(locator);
	}
	
	
			/////		DRAG AND DROP		/////
	public static void dragAndDrop(WebElement source, WebElement target)
	{
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).build().perform();
	}
	
	
	
		/////		SCROLL DOWN INNER SCROLL BAR		/////
	public static void scrollInnerScrollBar(WebElement locator)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", locator);
	}
	
	
			/////		SCROLL DOWN TO BOTTOM		/////
	public static void scrollDownToBottom(WebElement sd)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}	
	
	public static void scrollToBottom() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
	
	public static void scrollToTop(){
		WebElement element = driver.findElement(By.xpath("//span[@class='hidden-phone hidden-tablet' and contains(text(),'SIB Admin')]"));
		((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();", element);
	}
	
			

   ////   Import CSV File /////

public static void setClipboardData(String string) {
	  //StringSelection is a class that can be used for copy and paste operations.
	     StringSelection stringSelection = new StringSelection(string);
	     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	  }
	 
	 public static void uploadFile(String fileLocation) {
	        try {
	         //Setting clipboard with file location
	            setClipboardData(fileLocation);
	            //native key strokes for CTRL, V and ENTER keys
	            Robot robot=new Robot();
	 
	            robot.keyPress(KeyEvent.VK_CONTROL);                                       
	            robot.keyPress(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_CONTROL);
	           //thread();
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	        } catch (Exception exp) {
	         exp.printStackTrace();
	        }
	    }
	 
	 ///// Export CSV File ////
	 
	 public static void download() throws AWTException, InterruptedException
	 {
	  
	  Robot robot=new Robot();
	 // robot.keyPress(KeyEvent.VK_UP);
	//  robot.keyRelease(KeyEvent.VK_UP);
	//  thread();
	  
	  //press s key to save s
      robot.keyPress(KeyEvent.VK_ALT);
     
      robot.keyPress(KeyEvent.VK_S);
     
      robot.keyRelease(KeyEvent.VK_ALT);
     
      robot.keyRelease(KeyEvent.VK_S);
     
      thread();
      //press enter to save the file with default name and in default location
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
	 }
	 
	 ///       Pagination           /////////
	 
	public static void pagination() throws InterruptedException, IOException
	{
		click(pagiEndArw);
		takeScreenshot();
		thread();
		Thread.sleep(5000);
		
		click(pagiStartArw);
		takeScreenshot();
		thread();
		Thread.sleep(5000);
		
		if(totalDDValCount == 6)
		{
			click(pagiNextArw);
			takeScreenshot();
			thread();
			Thread.sleep(5000);
			
			click(pagiPreviousArw);
			takeScreenshot();
			thread();
			Thread.sleep(5000);
		}
	}
	
	///Tab key
	
public static void tabKey() throws AWTException
{
	Robot r=new Robot();
	 r.keyPress(KeyEvent.VK_TAB);
	 r.keyRelease(KeyEvent.VK_TAB);	
}
	  
	public static void splitString(String str)
	{
		String[] sStr=str.split(" ");
	}

	
	

	//get latest exported
	public static File getLatestFilefromDir(String dirPath)
	{
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) 
	    {
	        return null;
	    }
	
	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) 
	    {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) 
	       {
	           lastModifiedFile = files[i];
	       }
	    }
	  //  System.out.println("lastmodified file name "+lastModifiedFile);
	    return lastModifiedFile;
	}
	

	
	
	//prints all records in csv
	public static void csvRead(String path) throws FileNotFoundException, IOException 
	{
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
      
        String[] spmap = br.readLine().split("\t"); 
        System.out.println(spmap[0]);             //Header details
       
        while ((line = br.readLine()) != null)
        {
           String[] sp = line.split("\t");  
                       
               for (int x = 0; x < spmap.length; x++) 
                {
                       	System.out.println(sp[x]); //Records Data
                }      
        }
    }
		

	//returns all rows
	public static LinkedList<String> readCSVFileLines(String filename) throws IOException
	{
	    BufferedReader reader = new BufferedReader(new FileReader(filename));
	    LinkedList<String>linkedList = new LinkedList<>();
	    // do reading, usually loop until end of file reading
	    StringBuilder sb = new StringBuilder();
	    String mLine = reader.readLine();
	   // System.out.println("CSV File Details \n "+mLine);
	    while (mLine != null) {
	        linkedList.add(mLine);
	        sb.append(mLine);                     // process line
	     // System.out.println(mLine);           //print row
	        mLine = reader.readLine();
	       
	    }
	    reader.close();
	    return linkedList;
	}
	
	}
			




	



