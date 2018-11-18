package pengq.common.excel.utils;

import pengq.common.excel.model.EXCell;

/**
 * Created by pengq on 2018/10/22 19:51
 * Description:
 */

public class EXCellUtil {
    public static int getCellNumber(EXCell cell) {
        byte[] bytes = getAscii(cell);
        byte position = 0;
        for (int i = 0; i < bytes.length; i++) {
            position += (bytes[i] - 65 + (26 * i));
        }
        return position;
    }

    public static int getCellNumber(String cell) {
        EXCell exCell = EXCell.valueOf(cell);
        return getCellNumber(exCell);
    }

    private static byte[] getAscii(EXCell exCell) {
        String name = exCell.name();
        return name.getBytes();
    }
}
