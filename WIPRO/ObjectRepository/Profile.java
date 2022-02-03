package ObjectRepository;

import org.openqa.selenium.By;

public class Profile {
	
	public static By profileInDropDown = By.xpath("//a[contains(@href,'/acc')]");
	public static By clearbtn=By.cssSelector("input.btn");
	
	//	My Status
	public static By myStatusText=By.xpath("//h5[contains(text(),'My Status')]");
	public static By editOption=By.xpath("//a[@class='edit-avat'][@href='#']");
	public static By removeOption=By.xpath("//a[@class='delete-avat']");
	public static By updateProfilestatus = By.xpath("//div[@id='content']/div[2]/div[2]/div/div/ul/li[1]/a");
    //public static By msgNotificationBar1=By.cssSelector("div.notify-message-bar.notify_success.message-bar-locate");
	public static By msgNotificationBar1=By.xpath("html/body/div[8]");
	public static By stsSelectStatus=By.xpath("//select[@name='status_id']");
	public static By stsSelectStatusDDVal=By.xpath("//select[@name='status_id']/option");
	public static By stsUpdateBtn=By.xpath("//button[@name='update_status']");
	public static By profileStatus=By.xpath("//a[@href='/acc#/status']");
	
	//My Social Information
	public static By socialInfoTab=By.xpath("//a[contains(text(),'Social Information')]");
	public static By socInfoSelect=By.xpath("//select[@id='social']");
	public static By socInfoTotalddVal=By.xpath("//select[@id='social']/option");
	public static By socInfoTxtBox=By.xpath("//input[@id='value']");
	public static By socInfoTxtBoxFrWtsApp=By.xpath("//input[@id='value1']");
	public static By socInfoSaveBtn=By.xpath("//button[@name='update_social']");
	public static By socInfoTableValues=By.xpath("//table[@id='social_table']/tbody/tr");
	
	//Change Password
	public static By chngPwdTab=By.xpath("//a[@href='#/change_password']");
	public static By oldPwdTxtBox=By.xpath("//input[@id='old_password']");
	public static By newPwdTxtBox=By.xpath("//input[@id='new_password']");
	public static By cnfmPwdTxtBox=By.xpath("//input[@id='confirm_password']");
	public static By chngPwdSaveBtn=By.xpath("//button[@name='update_password']");
	
	//General configurations
	public static By genConfigTab=By.xpath("//a[@href='#/generalconfig']");
	public static By genConfSelect=By.xpath("//select[@name='ppr_id']");
	public static By genConfTotalddval=By.xpath("//select[@name='ppr_id']/option");
	public static By resetDefaultBtn=By.cssSelector("input[value='Reset Default']");
	
	
	}


