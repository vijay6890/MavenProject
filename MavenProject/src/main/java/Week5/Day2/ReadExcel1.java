package Week5.Day2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel1 {

	public static String[][] captureData(String File) throws IOException
	{
		XSSFWorkbook wb=new XSSFWorkbook("./Data/"+File+".xlsx");
		XSSFSheet ws = wb.getSheet("sheet1");
		int rows = ws.getLastRowNum();
		 short cells = ws.getRow(0).getLastCellNum();   
		 String[][] data =new String[rows][cells];
		 for (int i = 1; i<=rows; i++) {
			 for (int j = 0; j < cells; j++) {
				String text = ws.getRow(i).getCell(j).getStringCellValue();	
				data[i-1][j]=text;
			}
			
		}
	wb.close();
	return data;

	}

	
	

	


}
