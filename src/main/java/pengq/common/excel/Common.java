package pengq.common.excel;

import pengq.common.excel.annotation.Cell;
import pengq.common.excel.annotation.WorkBookReader;
import pengq.common.excel.model.EXCell;

/**
 * FileName:     Common
 *
 * @version V1.0
 * CreateDate:         2018/10/18 15:41
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By nuctech.ltd
 * Copyright:    Copyright(C) 2018-2028
 * Company       同方威视技术股份有限公司
 * @author: pengq
 */
@WorkBookReader
public class Common {
    @Cell(cell = EXCell.A, target = String.class)
    private String name;

    @Cell(cell = EXCell.B, target = Integer.class)
    private Integer value;

    @Cell(cell = EXCell.C, target = String.class)
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
