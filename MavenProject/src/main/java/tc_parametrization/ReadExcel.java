package tc_parametrization;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		XSSFWorkbook wb=new XSSFWorkbook("./Data/Createlead.xlsx");
	   XSSFSheet ws = wb.getSheet("Sheet1");
	   int rc = ws.getLastRowNum();
	        
	   for (int i = 1; i <=rc; i++) {
		 
		  
		     String stringCellValue = ws.getRow(i).getCell(0).getStringCellValue();
		     System.out.println(stringCellValue);
		
	}
	    
	}

}
