package pengq.common.excel.model;

import java.util.List;
import java.util.Map;

/**
 * FileName:     MySheet
 *
 * @version V1.0
 * CreateDate:         2018/10/31 8:34
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By nuctech.ltd
 * Copyright:    Copyright(C) 2018-2028
 * Company       同方威视技术股份有限公司
 * @author: pengq
 */

public class MySheet {
    private List<String> cells;

    private List<Map<String,Object>> rows;

    public List<String> getCells() {
        return cells;
    }

    public void setCells(List<String> cells) {
        this.cells = cells;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "MySheet{" +
                "cells=" + cells +
                ", rows=" + rows +
                '}';
    }
}
