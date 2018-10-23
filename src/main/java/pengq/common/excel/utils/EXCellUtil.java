package pengq.common.excel.utils;

import pengq.common.excel.model.EXCell;

/**
 * Created by pengq on 2018/10/22 19:51
 * Description:
 */

public class EXCellUtil {
    public static int getCellNumber(EXCell cell) {
        // TODO: 2018/10/23 目前该方式不支持超过EXCEL Z 的列

        byte[] bytes = getAscii(cell);

        int ascii = bytes[0];

        return ascii - 65;
    }

    private static byte[] getAscii(EXCell exCell) {
        String name = exCell.name();
        return name.getBytes();
    }
}
