package pengq.common.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import pengq.common.excel.annotation.WorkBookReader;
import pengq.common.excel.model.EXCell;
import pengq.common.excel.utils.FieldUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * FileName:     ExcelReader
 *
 * @version V1.0
 * CreateDate:         2018/10/18 8:53
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By nuctech.ltd
 * Copyright:    Copyright(C) 2018-2028
 * Company       同方威视技术股份有限公司
 * @author: pengq
 */

public class ExcelReader<T> {
    private Workbook wb;

    public ExcelReader(String fullName) throws IOException {
        wb = WorkbookFactory.create(new FileInputStream("D:\\logs\\黑龙江省红星合作店明细清单.xlsx"));
    }

    public List<T> read(Class<T> clazz) {
        int startRow = 0;
        WorkBookReader reader = clazz.getAnnotation(WorkBookReader.class);
        if (reader != null) {
            startRow = reader.startRow();
        }
        Map<String, FieldSummary> map = parseClassInfo(clazz);

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

    public Workbook getWorkbook() {
        return wb;
    }

    public void closeWorkbook() {
        if (wb != null) {
            try {
                wb.close();
            } catch (IOException ignored) {
            }
        }
    }

    private T buildFromRow(Row row, Map<String, FieldSummary> fieldSummaryMap, Class<T> clazz) {
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

    private Map<String, FieldSummary> parseClassInfo(Class<?> clazz) {
        Map<String, FieldSummary> fieldMapper = new HashMap<>();
        Field[] fields = FieldUtil.getFields(clazz);
        for (Field field : fields) {
            pengq.common.excel.annotation.Cell cellAnnotation = field.getAnnotation(pengq.common.excel.annotation.Cell.class);
            if (cellAnnotation == null) {
                continue;
            }

            FieldSummary fieldSummary = new FieldSummary();
            Class<?> fieldType = cellAnnotation.target();
            if (fieldType == pengq.common.excel.annotation.Cell.Null.class) {
                fieldType = field.getType();
            }

            String fieldName = field.getName();
            EXCell exCell = cellAnnotation.cell();

            fieldSummary.setFieldType(fieldType);
            fieldSummary.setFieldName(fieldName);
            fieldSummary.setExCell(exCell);
            fieldMapper.put(exCell.name(), fieldSummary);
        }

        return fieldMapper;
    }


    protected class FieldSummary {
        private String fieldName;
        private Class<?> fieldType;
        private EXCell exCell;

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public Class<?> getFieldType() {
            return fieldType;
        }

        public void setFieldType(Class<?> fieldType) {
            this.fieldType = fieldType;
        }

        public EXCell getExCell() {
            return exCell;
        }

        public void setExCell(EXCell exCell) {
            this.exCell = exCell;
        }
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        ExcelReader<Common> reader = new ExcelReader<>("D:\\logs\\黑龙江省红星合作店明细清单.xlsx");
        List<Common> list = reader.read(Common.class);
        System.out.println("end : " + (System.currentTimeMillis() - startTime));
    }

}
