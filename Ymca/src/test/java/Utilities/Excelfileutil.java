package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excelfileutil {

	public static Workbook wb;
	public static String sheetname;
	public Excelfileutil() throws Throwable //creating of constructor
	{
	FileInputStream  fis = new FileInputStream("./Testinput/Inputsheet.xlsx");	
	wb = WorkbookFactory.create(fis);
	}
	public static  int rowcount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	public  int colcount(String sheetname ,int row)
	{
		return wb.getSheet(sheetname).getRow(row).getLastCellNum();
	}
	public String getdata(String sheetname,int row ,int col)
	{
		//creating a space for status writing bcz at first it was vacant.
		String data ="";
	
	    if(wb.getSheet(sheetname).getRow(row).getCell(col).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
		int celldata =(int) wb.getSheet(sheetname).getRow(row).getCell(col).getCellType();
		data = String.valueOf(celldata);
		}
		else 
		{
			data= wb.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
		}
		return data;
	}
	public static void setData(String sheetname, int row,int col,String status) throws Throwable
	{
		Sheet sh = wb.getSheet(sheetname);
		Row rownum = sh.getRow(row);
		Cell cell = rownum.createCell(col);// creating one row for result writing
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("passed"))
		{
		//create cell style
			
		CellStyle style = wb.createCellStyle();
		
		// create font
		Font font = wb.createFont();
		
		//apply colors to text
		font.setColor(IndexedColors.GREEN.getIndex());
		
		//apply bold to text
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		
		//set font
		style.setFont(font);
		
		rownum.getCell(col).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("failed"))
		{
			//create cell style
			
			CellStyle style = wb.createCellStyle();
			
			// create font
			Font font = wb.createFont();
			
			//apply colors to text
			font.setColor(IndexedColors.RED.getIndex());
			
			//apply bold to text
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			
			//set font
			style.setFont(font);
			
			rownum.getCell(col).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Not executed"))
		{
			//create cell style
			
			CellStyle style = wb.createCellStyle();
					
			// create font
			Font font = wb.createFont();
					
			//apply colors to text
			font.setColor(IndexedColors.BROWN.getIndex());
					
			//apply bold to text
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
					
			//set font
			style.setFont(font);
					
			rownum.getCell(col).setCellStyle(style);
		}
		FileOutputStream fos = new FileOutputStream("./TestOutput/Outputsheet.xlsx");
		wb.write(fos);
		fos.close();
	}
}
