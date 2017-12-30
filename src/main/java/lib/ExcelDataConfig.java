package lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	FileOutputStream fout;
	FileInputStream fis;
	File src;
    public ExcelDataConfig(String excelPath) throws IOException {
    try {
	src= new File(excelPath);
    fis= new FileInputStream(src);
	wb=new XSSFWorkbook(fis);
			
			} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}finally{
			fis.close();
		}
}
 //=======Read Excel=========
 public String getData(int sheetnumber,int row,int column)
   {	
	 DataFormatter dataFormattter = new DataFormatter();
	 sheet1=wb.getSheetAt(sheetnumber);
	 //sheet1.getRow(row).getCell(column)
	 Cell Celldata= sheet1.getRow(row).getCell(column);
	 String data = dataFormattter.formatCellValue(Celldata);
	 return data;
	 }
 //=======Writing in Excel=========
 public String putData(int sheetnumber,int row,int column,String value) throws Exception
 {
	 fout= new FileOutputStream(src);
	 sheet1=wb.getSheetAt(sheetnumber);
	 sheet1.getRow(row).createCell(column).setCellValue(value);
	 try {
		wb.write(fout);
		fout.close();
	} catch (Exception e) {
		
		System.out.println(e.getMessage());
	}
	return value;
	
		}
}