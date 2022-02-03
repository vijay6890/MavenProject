package ObjectRepository;

import org.openqa.selenium.By;

public class LoginPage {
	
	public static By username = By.name("username");
	public static By password = By.name("password");
	public static By loginBtn = By.name("submit");
	
	public static By forgotPasswordLink = By.linkText("Forgot Password?");
	//public static By registrationLink = By.linkText("Registration"); 
	
	public static By clkUserNameBtn = By.xpath("//div[@class='navbar']//div[@class='btn-group pull-right']/a");	
	public static By logoutBtn = By.id("logout_clear");
	
	public static By invCredentialsMsg = By.xpath("//div[@class='alert-warning']/p/strong");
	public static By invMailFormatMsg1 = By.xpath("//div[@class='alert-warning']/p[1]/strong");
	public static By invMailFormatMsg2 = By.xpath("//div[@class='alert-warning']/p[2]/strong");

}
