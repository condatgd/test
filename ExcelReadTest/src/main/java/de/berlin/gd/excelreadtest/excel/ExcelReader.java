package de.berlin.gd.excelreadtest.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelReader {
    private String filename;
    public ExcelReader(String filename) {
        this.filename = filename;
    }

    public List<ExcelLine> readLinesFromSheetWithName(String sheetName) throws IOException, InvalidFormatException {
        File excelFile = new File(filename);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet workbookSheet = workbook.getSheet(sheetName);
        if(workbookSheet!=null) {
            List<ExcelLine> excelLines = new ArrayList<>();
            log.info("workbookSheet {} found: {}", sheetName, workbookSheet);
            int firstRowNum = workbookSheet.getFirstRowNum();
            int lastRowNum = workbookSheet.getLastRowNum();
            ExcelLine header = readExelLine(workbookSheet, null, firstRowNum);
            log.info("header: {}", header);
            for(int i=firstRowNum+1; i<=lastRowNum; i++) {
                ExcelLine row = readExelLine(workbookSheet, header, i);
                log.info("row: {}", row);
                excelLines.add(row);
            }
            return excelLines;
        } else {
            log.error("workbookSheet {} not found!", sheetName);
            return null;
        }
    }

    private ExcelLine readExelLine(Sheet workbookSheet, ExcelLine header, int rowNum) {
        ExcelLine excelLine = new ExcelLine();
        Row row = workbookSheet.getRow(rowNum);
        short firstCellNum = row.getFirstCellNum();
        short lastCellNum = row.getLastCellNum();
        for(int j=firstCellNum; j<=lastCellNum; j++) {
            Cell cell = row.getCell(j);
            if(cell!=null) {
                Object key;
                if(header==null) {
                    key = j;
                } else {
                    key = header.get(j).toString();
                }
                CellType cellType = cell.getCellType();
                if(cellType.equals(CellType.STRING)) {
                    excelLine.put(key, cell.getStringCellValue());
                } else
                if(cellType.equals(CellType.NUMERIC)) {
                    if(DateUtil.isCellDateFormatted(cell)) {
                        excelLine.put(key, cell.getDateCellValue());
                    } else {
                        excelLine.put(key, cell.getNumericCellValue());
                    }
                }
            }
        }
        return excelLine;
    }

}
