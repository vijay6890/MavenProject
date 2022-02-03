package Week5.Day2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public String[][] ReadData()throws IOException {
		// TODO Auto-generated method stub
		XSSFWorkbook wb=new XSSFWorkbook("./data/CreateLead.xlsx");
XSSFSheet ws=wb.getSheet("Sheet1");
int rows = ws.getLastRowNum();
System.out.println(rows);
int columns = ws.getRow(0).getLastCellNum();
System.out.println(columns);
String[][] data= new String[rows][columns];
for (int i = 1; i <=rows; i++) {
	for (int j = 0; j < columns; j++) {
		String text = ws.getRow(i).getCell(j).getStringCellValue();
		System.out.println(text);
		              data[i-1][j]=text;
		
		
	}
	
}
wb.close();
return data;
	}

}
