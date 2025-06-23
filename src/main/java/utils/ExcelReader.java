package utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {
	
	//private static final String FILE_PATH = "test-data/GeneratedEmails.xlsx";
    //private static final String SHEET_NAME = "EmailID";
    
    public static String getLatestEmailID(String FILE_PATH,String SHEET_NAME,int columnIndex)
    
    {
    	File file = new File(FILE_PATH);
    	if(!file.exists())
    	{
    		System.out.println("Excel file not found at"+ FILE_PATH);
    		return null;
    	}
    	
    	try
    	{
    		FileInputStream fis = new FileInputStream(file);
    		Workbook workbook = new XSSFWorkbook(fis);
    		Sheet sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) 
            {
                System.out.println("Sheet '" + SHEET_NAME + "' not found.");
                return null;
            }
            
            int lastRowNum = sheet.getLastRowNum();
            if (sheet.getRow(lastRowNum) == null || 
                    sheet.getRow(lastRowNum).getCell(columnIndex) == null) {
                    System.out.println("❗ Data not found in row " + lastRowNum + ", column " + columnIndex);
                    return null;
                }
            String data = sheet.getRow(lastRowNum).getCell(columnIndex).getStringCellValue();
            return data;
        }
    	
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		return null;
    		
		}
    	
    }
    public static String[] getUserCredentialsByRow(String filePath, String sheetName, int rowIndex) {
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null || sheet.getRow(rowIndex) == null) {
                System.out.println("❌ Invalid row or sheet");
                return null;
            }

            String email = sheet.getRow(rowIndex).getCell(0).getStringCellValue();
            String password = sheet.getRow(rowIndex).getCell(1).getStringCellValue();

            workbook.close();
            return new String[] { email, password };

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    }
