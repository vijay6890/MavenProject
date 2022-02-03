package ReusableFunctions;

import static UIWrappers.UIObjects.*;
import static ObjectRepository.LoginPage.*;

import java.io.IOException;

import jxl.read.biff.BiffException;

public class DataFields {
	
	public static void sibLogin() throws BiffException, IOException
	{
		enterText(username,Config.XLReader.getXLFileValues("siblogin", 0, 0));
		enterText(password,Config.XLReader.getXLFileValues("siblogin", 1, 0));
	}

}
