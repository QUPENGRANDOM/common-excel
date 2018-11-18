package pengq.common.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import pengq.common.excel.annotation.WorkBookReader;
import pengq.common.excel.model.FieldSummary;
import pengq.common.excel.model.MySheet;
import pengq.common.excel.model.MyWorkbook;
import pengq.common.excel.utils.FieldParseUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by pengq on 2018/10/18 8:53
 * Description:
 */

public class ExcelReader {
    private Workbook wb;

    public ExcelReader(String fullName) throws IOException {
        wb = WorkbookFactory.create(new FileInputStream(fullName));
    }

    public ExcelReader(InputStream in) throws IOException {
        wb = WorkbookFactory.create(in);
    }

    public <T> List<T> read(Class<T> clazz) {
        int startRow = 0;
        WorkBookReader reader = clazz.getAnnotation(WorkBookReader.class);
        if (reader != null) {
            startRow = reader.startRow();
        }
        Map<String, FieldSummary> map = FieldParseUtil.parseReadCell(clazz);

        int sheetNumber = wb.getNumberOfSheets();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < sheetNumber; i++) {
            Sheet sheet = wb.getSheetAt(i);
            int rowNum = sheet.getLastRowNum();
            for (int j = startRow; j < rowNum + 1; j++) {
                T t = buildFromRow(sheet.getRow(j), map, clazz);
                if (t != null) {
                    list.add(t);
                }
            }
        }

        return list;
    }


//    {
//      "sheets":["sheet1", "sheet2" ],
//      "sheetMapper":{
//          "sheet1":{
//              "cells":["a","b"],
//              "rows":[
//                   {
//                     "a":"123123",
//                     "b":1
//                   }
//                 ]
//        },
//        "sheet2":{
//
//        }
//    }
//    }

    public MyWorkbook read() {
        MyWorkbook myWorkbook = new MyWorkbook();
        List<String> sheets = new ArrayList<>();
        Map<String, MySheet> sheetMapper = new HashMap<>();

        int sheetNumber = wb.getNumberOfSheets();
        for (int i = 0; i < sheetNumber; i++) {
            Sheet sheet = wb.getSheetAt(i);

            sheets.add(sheet.getSheetName());

            MySheet mySheet = new MySheet();

            int rowNum = sheet.getLastRowNum();

            List<String> cells = new ArrayList<>();
            List<Map<String, Object>> rows = new ArrayList<>();

            for (int j = 0; j < rowNum + 1; j++) {
                Row row = sheet.getRow(j);
                Map<String, Object> myRow = buildFromRow(row, cells);

                rows.add(myRow);
            }

            mySheet.setCells(cells);
            mySheet.setRows(rows);
            sheetMapper.put(sheet.getSheetName(), mySheet);
        }

        myWorkbook.setSheetMapper(sheetMapper);
        myWorkbook.setSheets(sheets);
        return myWorkbook;
    }

    public void closeWorkbook() {
        if (wb != null) {
            try {
                wb.close();
            } catch (IOException ignored) {
            }
        }
    }

    private <T> T buildFromRow(Row row, Map<String, FieldSummary> fieldSummaryMap, Class<T> clazz) {
        if (clazz == null) {
            return null;
        }

        T t;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }

        for (int i = 0; i < row.getLastCellNum() + 1; i++) {
            Cell cell = row.getCell(i);
            if (cell == null) continue;

            CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
            String value = cellRef.formatAsString();
            int rowNum = cellRef.getRow();
            String cellName = value.substring(0, value.length() - String.valueOf(rowNum + 1).length());

            if (!fieldSummaryMap.containsKey(cellName)) {
                continue;
            }

            FieldSummary fieldSummary = fieldSummaryMap.get(cellName);

            try {
                Field field = clazz.getDeclaredField(fieldSummary.getFieldName());
                field.setAccessible(true);
                field.set(t, this.getCellValue(cell, fieldSummary));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return t;
    }

    private Map<String, Object> buildFromRow(Row row, List<String> cells) {
        Map<String, Object> myRow = new HashMap<>();
        for (int i = 0; i < row.getLastCellNum() + 1; i++) {
            Cell cell = row.getCell(i);
            if (cell == null) continue;

            CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
            String value = cellRef.formatAsString();
            int rowNum = cellRef.getRow();

            String cellName = value.substring(0, value.length() - String.valueOf(rowNum + 1).length());
            if (!cells.contains(cellName)) {
                cells.add(cellName);
            }

            myRow.put(cellName, getCellValue(cell));
        }

        return myRow;
    }

    private Object getCellValue(Cell cell, FieldSummary summary) {
        Class<?> clazz = summary.getFieldType();
        Object value = null;
        switch (cell.getCellType()) {
            case STRING:
                value = clazz.cast(cell.getRichStringCellValue().getString());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell) && clazz == Date.class) {
                    value = cell.getDateCellValue();
                } else if (clazz == Integer.class) {
                    value = clazz.cast((int) cell.getNumericCellValue());
                } else if (clazz == String.class) {
                    value = cell.getStringCellValue();
                } else {
                    value = clazz.cast(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case BLANK:
                break;
            default:
                System.out.println();
                break;
        }

        return value;
    }

    private Object getCellValue(Cell cell) {
        Object value = "";
        if (cell != null) {
            switch (cell.getCellType()) {

                case FORMULA:
                    value = cell.getCellFormula();
                    break;

                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        value = cell.getDateCellValue();
                    }else {
                        value = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case STRING:
                    value = cell.getStringCellValue();
                    break;

                case BLANK:
                    break;

                case BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;

                case ERROR:
                    value = String.valueOf(cell.getErrorCellValue());
                    break;

                default:
                    break;
            }
        }
        return value;
    }
}
