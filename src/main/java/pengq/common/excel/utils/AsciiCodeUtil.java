package pengq.common.excel.utils;

import pengq.common.excel.model.EXCell;

/**
 * Created by pengq on 2018/10/22 14:51
 * Description:
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
