package Scripts;

import java.io.IOException;
import java.util.Random;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import UIWrappers.Page;
import static ObjectRepository.Locations.*;
import static UIWrappers.UIObjects.*;
import static Config.TakScreenshot.*;

/*****************************************************************************************************************
 * Test 1: Add New Location
 * Test 2: Click Google Map Button for Location
 * Test 3: Verify Normal View Of Google Map
 * Test 4: Verify Satellite View Of Google Map
 * Test 5: Verify Labels Check box
 *  
******************************************************************************************************************/

public class Loc_GoogleMap extends Page{
	
	LoginLogout ll = new LoginLogout();
	Loc_AddDeleteLocation loc_AddDeleteLocation = new Loc_AddDeleteLocation();
	
	/*@Test(priority=0)
	void login() throws BiffException, IOException, InterruptedException
	{
		ll.loginToSIB();
	}*/
	
	@Test(priority=1)
	void showStreetViewGoglMap() throws BiffException, IOException, InterruptedException
	{
		click(locationsInMainMenu);
		waitForPageLoad();
		//click(locOvrAddBtn);
		//thread();
		
		//loc_AddDeleteLocation.addNewLocation();
		
		getTotalValuesIndd(locLocationsLstViewTtl);
		Random random = new Random();
		int rr = random.nextInt(totalDDValCount-1)+1;
		driver.findElement(By.xpath("//table[@id='facilities_table']/tbody/tr["+rr+"]/td[2]/b/span")).click();
		thread();
		takeScreenshot();	
		thread();
	}
	
	@Test(priority=2)
	void showSatelliteViewMap() throws InterruptedException, IOException
	{
		click(gogleSatelliteViewBtn);
		waitForPageLoad();
		takeScreenshot();
		
		click(goglMapZoomInBtn);
		thread();
		click(goglMapZoomInBtn);
		thread();
		takeScreenshot();
		
		click(goglMapZoomOutBtn);
		thread();
		click(goglMapZoomOutBtn);
		thread();
		click(goglMapZoomOutBtn);
		thread();
		takeScreenshot();
		
		click(goglMapZoomInBtn);
		thread();
		click(goglMapZoomInBtn);
		thread();
		click(goglMapZoomInBtn);
		thread();
		takeScreenshot();
		thread();
	}
	
	@Test(priority=3)
	void verifyLabelChkBox() throws InterruptedException
	{
		click(gogleSatelliteViewBtn);
		click(goglLabelsChkBox);
		thread();
		
		click(goglLabelsChkBox);
		
		click(goglMapCloseBtn);
		thread();
	}
	
	/*@Test(priority=4)
	void logout() throws InterruptedException
	{
		ll.logoutSession();
		closeWindow();
	}*/

}
