package pengq.common.excel.utils;

import pengq.common.excel.annotation.ReadCell;
import pengq.common.excel.annotation.WriteCell;
import pengq.common.excel.model.EXCell;
import pengq.common.excel.model.FieldSummary;
import pengq.common.excel.model.Null;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by pengq on 2018/10/22 13:37
 * Description:
 */

public class FieldParseUtil {
    public static Map<String, FieldSummary> parseReadCell(Class<?> clazz) {
        Field[] fields = FieldUtil.getFields(clazz);

        Map<String, FieldSummary> fieldMapper = new HashMap<>(fields.length);

        for (Field field : fields) {
            ReadCell readCellAnnotation = field.getAnnotation(ReadCell.class);
            if (readCellAnnotation == null) {
                continue;
            }

            FieldSummary fieldSummary = new FieldSummary();
            Class<?> fieldType = readCellAnnotation.target();
            if (fieldType == Null.class) {
                fieldType = field.getType();
            }

            String stringToDate = readCellAnnotation.stringToDate();
            if (stringToDate.isEmpty()) {
                fieldSummary.setPattern(null);
            } else {
                fieldSummary.setPattern(stringToDate);
            }
            String fieldName = field.getName();
            EXCell exCell = readCellAnnotation.readCell();


            fieldSummary.setFieldType(fieldType);
            fieldSummary.setFieldName(fieldName);
            fieldSummary.setExCell(exCell);
            fieldMapper.put(exCell.name(), fieldSummary);
        }

        return fieldMapper;
    }

    public static Map<String, FieldSummary> parseWriteCell(Class<?> clazz) {
        Field[] fields = FieldUtil.getFields(clazz);

        Map<String, FieldSummary> fieldMapper = new HashMap<>(fields.length);

        for (Field field : fields) {
            WriteCell writeCellAnnotation = field.getAnnotation(WriteCell.class);
            if (writeCellAnnotation == null) {
                continue;
            }

            FieldSummary fieldSummary = new FieldSummary();
            Class<?> fieldType = writeCellAnnotation.target();
            if (fieldType == Null.class) {
                fieldType = field.getType();
            }

            String fieldName = field.getName();
            EXCell exCell = writeCellAnnotation.writeCell();

            String dateFormat = writeCellAnnotation.dateFormat();
            String doubleFormat = writeCellAnnotation.doubleFormat();

            fieldSummary.setFieldType(fieldType);
            fieldSummary.setFieldName(fieldName);
            fieldSummary.setExCell(exCell);
            fieldSummary.setDateFormat(dateFormat);
            fieldSummary.setDoubleFormat(doubleFormat);

            fieldMapper.put(fieldName, fieldSummary);
        }

        return fieldMapper;
    }

    public static List<String> parseHeader(Class<?> clazz) {
        Field[] fields = FieldUtil.getFields(clazz);
        String[] array = new String[fields.length];
        Arrays.fill(array, "");

        for (Field field : fields) {
            WriteCell writeCellAnnotation = field.getAnnotation(WriteCell.class);
            if (writeCellAnnotation == null) {
                continue;
            }
            EXCell exCell = writeCellAnnotation.writeCell();
            array[EXCellUtil.getCellNumber(exCell)]= writeCellAnnotation.header();
        }

        return  new ArrayList<>(Arrays.asList(array));
    }
}