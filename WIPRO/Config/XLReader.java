package Config;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class XLReader extends Base{
	
	public static String getXLFileValues(String preg, int i, int j) throws BiffException, IOException
	{
		Workbook workbook = Workbook.getWorkbook(new File(xlFilesLocation));
		
		Sheet sheet = workbook.getSheet(preg);
		   
		Cell cell1 = sheet.getCell(i, j);
		   
		String value = cell1.getContents();
		   
		workbook.close();
		   
		return value;
		
	}

}


