package utils;

import java.io.*;
import java.util.regex.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelURLManager {

    private static final String filePath = "test-output/TestData.xlsx";
    private static final String sheetName = "URLData";

    // Write URL to Excel (row 2)
    public static void writeURL(String url) {
        try {
            Workbook workbook;
            Sheet sheet;

            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    sheet = workbook.createSheet(sheetName);
                }
                fis.close();
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet(sheetName);
            }

            // Headers
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Label");
                headerRow.createCell(1).setCellValue("Value");
            }

            // Write URL in row 2
            Row urlRow = sheet.getRow(1);
            if (urlRow == null) urlRow = sheet.createRow(1);
            urlRow.createCell(0).setCellValue("CapturedURL");
            urlRow.createCell(1).setCellValue(url);

            try (FileOutputStream out = new FileOutputStream(filePath)) {
                workbook.write(out);
                workbook.close();
                System.out.println("✅ URL written to Excel: " + url);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Write last 4 digits of project name to Excel (row 3)
    public static void writeProjectCode(String fullProjectName) {
        String last4Digits = "";
        Matcher matcher = Pattern.compile("(\\d{4})$").matcher(fullProjectName);
        if (matcher.find()) {
            last4Digits = matcher.group(1);
        } else {
            last4Digits = "0000"; // fallback if not found
        }

        try {
            Workbook workbook;
            Sheet sheet;

            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    sheet = workbook.createSheet(sheetName);
                }
                fis.close();
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet(sheetName);
            }

            // Write project code in row 3
            Row codeRow = sheet.getRow(2);
            if (codeRow == null) codeRow = sheet.createRow(2);
            codeRow.createCell(0).setCellValue("ProjectCode");
            codeRow.createCell(1).setCellValue(last4Digits);

            try (FileOutputStream out = new FileOutputStream(filePath)) {
                workbook.write(out);
                workbook.close();
                System.out.println("✅ Project code written to Excel: " + last4Digits);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read URL (row 2, column 2)
    public static String readURL() {
        return readCell(1, 1); // row 1, col 1
    }

    // Read Project Code (row 3, column 2)
    public static String readProjectCode() {
        return readCell(2, 1); // row 2, col 1
    }

    // Generic cell reader
    private static String readCell(int rowIndex, int colIndex) {
        String value = null;

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    Cell cell = row.getCell(colIndex);
                    if (cell != null) {
                        value = cell.getStringCellValue();
                    }
                }
            }

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }
}
