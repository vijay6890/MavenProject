package Scripts;

import static ReusableFunctions.DataFields.*;
import static UIWrappers.UIObjects.*;

import java.io.IOException;

import static ObjectRepository.LoginPage.*;
import jxl.read.biff.BiffException;

public class LoginLogout {
	
	public void loginToSIB() throws BiffException, IOException, InterruptedException
	{
		sibLogin();
		click(loginBtn);
		thread();
	}
	
	public void logoutSession() throws InterruptedException
	{
		click(clkUserNameBtn);
		thread();
		click(logoutBtn);
		thread();
	}

}
