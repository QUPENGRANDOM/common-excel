package pengq.common.excel.model;

import java.util.List;
import java.util.Map;

/**
 * FileName:     MyWorkbook
 *
 * @version V1.0
 * CreateDate:         2018/10/31 8:31
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By nuctech.ltd
 * Copyright:    Copyright(C) 2018-2028
 * Company       同方威视技术股份有限公司
 * @author: pengq
 */

public class MyWorkbook {
    private List<String> sheets;
    private Map<String,MySheet> sheetMapper;

    public List<String> getSheets() {
        return sheets;
    }

    public void setSheets(List<String> sheets) {
        this.sheets = sheets;
    }

    public Map<String, MySheet> getSheetMapper() {
        return sheetMapper;
    }

    public void setSheetMapper(Map<String, MySheet> sheetMapper) {
        this.sheetMapper = sheetMapper;
    }

    @Override
    public String toString() {
        return "MyWorkbook{" +
                "sheets=" + sheets +
                ", sheetMapper=" + sheetMapper +
                '}';
    }
}
