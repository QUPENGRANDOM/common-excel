package pengq.common.excel.utils;

import java.util.regex.Pattern;

/**
 * Created By pengq On 2018/11/18 11:01
 */
public class NumberUtil {
    public static boolean isNumeric(Object obj) {
        Pattern pattern = Pattern.compile("^[-+]?//d+(//.//d+)?$");
        return pattern.matcher(String.valueOf(obj)).matches();
    }
}
