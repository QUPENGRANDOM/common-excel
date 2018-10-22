package pengq.common.excel.model;

/**
 * FileName:     FieldSummary
 *
 * @version V1.0
 * CreateDate:         2018/10/22 13:46
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By nuctech.ltd
 * Copyright:    Copyright(C) 2018-2028
 * Company       同方威视技术股份有限公司
 * @author: pengq
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
