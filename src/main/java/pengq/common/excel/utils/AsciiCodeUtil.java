package pengq.common.excel.utils;

import pengq.common.excel.model.EXCell;

/**
 * FileName:     AscollCodeUtil
 *
 * @version V1.0
 * CreateDate:         2018/10/22 14:51
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By nuctech.ltd
 * Copyright:    Copyright(C) 2018-2028
 * Company       同方威视技术股份有限公司
 * @author: pengq
 */

public class AsciiCodeUtil {
    protected static int formater(EXCell exCell) {
        String name = exCell.name();
        byte[] bytes = name.getBytes();
        int sum = 0;
        for (byte b : bytes) {
            sum += b;
        }
        return sum;
    }
}
