package pengq.common.excel.model;

/**
 * Created by pengq on 2018/10/22 13:46
 * Description:
 */

public class FieldSummary {
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
