package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
public String getDataFromExcel(String sheetname,int row,int cell) throws Exception {
	FileInputStream fis=new FileInputStream("./configappdata/Vtiger.xlsx");
	Workbook wb=WorkbookFactory.create(fis);
	String value = wb.getSheet(sheetname).getRow(row).getCell(cell).toString();
	wb.close();
	return value;
}	

public int getRowCount(String sheetname) throws Exception {
	FileInputStream fis=new FileInputStream("./configappdata/Vtiger.xlsx");
	Workbook wb=WorkbookFactory.create(fis);
	int data = wb.getSheet(sheetname).getLastRowNum();
	wb.close();
	return data;
}

public void setDataIntoExcel(String sheetname,int row,int cell,String data) throws Exception {
	FileInputStream fis=new FileInputStream("./configappdata/Vtiger.xlsx");
	Workbook wb=WorkbookFactory.create(fis);
	Cell cel = wb.getSheet(sheetname).getRow(row).createCell(cell);
	cel.setCellValue(data);
	FileOutputStream fos=new FileOutputStream("./configappdata/Vtiger.xlsx");
	wb.write(fos);
	wb.close();
	}
}
