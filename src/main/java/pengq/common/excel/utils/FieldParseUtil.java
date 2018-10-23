package pengq.common.excel.utils;

import pengq.common.excel.annotation.ReadCell;
import pengq.common.excel.model.EXCell;
import pengq.common.excel.model.FieldSummary;
import pengq.common.excel.model.Null;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pengq on 2018/10/22 13:37
 * Description:
 */

public class FieldParseUtil {
    public static Map<String, FieldSummary> parse(Class<?> clazz){
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

            String fieldName = field.getName();
            EXCell exCell = readCellAnnotation.readCell();

            fieldSummary.setFieldType(fieldType);
            fieldSummary.setFieldName(fieldName);
            fieldSummary.setExCell(exCell);
            fieldMapper.put(exCell.name(), fieldSummary);
        }

        return fieldMapper;
    }
}
