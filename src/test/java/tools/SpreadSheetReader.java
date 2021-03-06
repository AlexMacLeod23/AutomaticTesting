package tools;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpreadSheetReader {

    private Workbook workbook;

    public SpreadSheetReader(){
        try {
            FileInputStream excelFile = new FileInputStream(new File("C:\\Users\\Administrator\\Documents\\Automated Testing (Week 5)\\TestData.xlsx"));
            workbook = new XSSFWorkbook(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readRow(int rowNo, String sheetName){
        List<String> row = new ArrayList<String>();
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Row currentRow =  datatypeSheet.getRow(rowNo);
        for (Cell currentCell : currentRow) {
            switch (currentCell.getCellTypeEnum()) {
                case NUMERIC:
                    row.add(String.valueOf(currentCell.getNumericCellValue()));
                    break;
                case BOOLEAN:
                    row.add(String.valueOf(currentCell.getBooleanCellValue()));
                    break;
                case _NONE:
                    System.out.println("No Value in cell");
                    break;
                case ERROR:
                    System.out.println("Error in cell");
                    break;
                default:
                    row.add(currentCell.getStringCellValue());
                    break;
            }
        }
        return row;
    }
}
