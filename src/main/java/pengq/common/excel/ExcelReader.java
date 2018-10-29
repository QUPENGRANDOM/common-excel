package pengq.common.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import pengq.common.excel.annotation.WorkBookReader;
import pengq.common.excel.model.FieldSummary;
import pengq.common.excel.utils.FieldParseUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
}
