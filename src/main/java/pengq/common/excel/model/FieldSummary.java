package pengq.common.excel.model;

import java.util.regex.Pattern;

/**
 * Created by pengq on 2018/10/22 8:46
 * Description:
 */

public class FieldSummary {
    private String fieldName;
    private Class<?> fieldType;
    private EXCell exCell;
    private String dateFormat;
    private String doubleFormat;
    private String pattern;

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

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDoubleFormat() {
        return doubleFormat;
    }

    public void setDoubleFormat(String doubleFormat) {
        this.doubleFormat = doubleFormat;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
