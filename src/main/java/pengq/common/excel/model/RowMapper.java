package pengq.common.excel.model;

import com.sun.istack.internal.Nullable;

import java.sql.SQLException;
import java.util.Map;

/**
 * FileName:     RowMapper
 *
 * @version V1.0
 * CreateDate:         2018/11/28 17:08
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By nuctech.ltd
 * Copyright:    Copyright(C) 2018-2028
 * Company       同方威视技术股份有限公司
 * @author: pengq
 */
@FunctionalInterface
public interface RowMapper<T> {
    @Nullable
    T mapRow(ResultSet resultSet, int rowNum);
}
