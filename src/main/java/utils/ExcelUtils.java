package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils 
{

	//private static final String FILE_PATH="GeneratedEmails.xlsx";
	//private static final String SHEET_NAME="EmailID";
	
	public static void appendEmailIdToExcel(String FILE_PATH,String SHEET_NAME ,String val) 
	
	{
		Workbook workbook;
		Sheet sheet;
		
		//make a directory to place the file
		File folder = new File("test-data");
		folder.mkdirs();
		
		File file = new File(folder+"/"+FILE_PATH);
		try
		{
		if(file.exists())
		{
			FileInputStream fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			sheet=workbook.getSheet(SHEET_NAME);
			fis.close();
		}
	
		else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet(SHEET_NAME);
		}
		 // Find next available row
        int lastRowNum = sheet.getLastRowNum();
        Row newRow = sheet.createRow(lastRowNum + 1);
        newRow.createCell(0).setCellValue(val);
        newRow.createCell(1).setCellValue(LocalDateTime.now().toString());

        // Save changes
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        workbook.close();
        fos.close();

        System.out.println("📥 Value written to Excel: " + val);
    }
	catch(IOException e)
	{
		e.printStackTrace();
	}
}
	
}

