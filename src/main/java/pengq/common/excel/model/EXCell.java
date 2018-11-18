package pengq.common.excel.model;

import pengq.common.excel.utils.EXCellUtil;

/**
 * Created by pengq on 2018/10/18 8:59
 * Description:
 */

public enum EXCell {
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,AA,AB,AC,AD,AE,AF,AG,AH,AI,AJ,AK,AL,AM,AN,AO,AP,AQ,AR,AS,AT,AU,AV,AW,AX,AY,AZ;

    public static int compare(EXCell o1,EXCell o2){
        return Integer.compare(EXCellUtil.getCellNumber(o1),EXCellUtil.getCellNumber(o2));
    }
}
