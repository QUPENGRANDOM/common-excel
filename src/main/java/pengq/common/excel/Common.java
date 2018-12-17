package pengq.common.excel;

import pengq.common.excel.annotation.*;
import pengq.common.excel.model.EXCell;
import pengq.common.excel.model.ResultSet;
import pengq.common.excel.model.RowMapper;

import java.util.Date;
import java.util.Map;

/**
 * FileName:     Common
 *
 * @version V1.0
 * CreateDate:         2018/10/18 12:41
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By nuctech.ltd
 * Copyright:    Copyright(C) 2018-2028
 * Company       同方威视技术股份有限公司
 * @author: pengq
 */
@WorkBookReader
@WorkBookWriter
@EnableHeader
public class Common {
    @ReadCell(readCell = EXCell.A, target = String.class)
    @WriteCell(writeCell = EXCell.A,header = "eee")
    private String name;

    @ReadCell(readCell = EXCell.B, target = Integer.class)
    private Integer value;

    @ReadCell(readCell = EXCell.C, target = String.class)
    @WriteCell(writeCell = EXCell.C,header = "aaa")
    private String location;

    @WriteCell(writeCell = EXCell.B,dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private RowMapper<Common> rowMapper = (resultSet, rowNum) -> {
        Common common = new Common();
        common.setCreateTime(resultSet.getDate("createTime"));
        common.setLocation(resultSet.getString("location"));
        common.setName(resultSet.getString("name"));
        common.setValue(resultSet.getInt("value"));
        return common;
    };
}
