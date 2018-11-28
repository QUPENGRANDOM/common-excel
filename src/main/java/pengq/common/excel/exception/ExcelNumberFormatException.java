package pengq.common.excel.exception;

/**
 * Created By pengq On 2018/11/28 20:26
 */
public class ExcelNumberFormatException extends RuntimeException {
    public ExcelNumberFormatException() {
    }

    public ExcelNumberFormatException(String message) {
        super(message);
    }

    public ExcelNumberFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelNumberFormatException(Throwable cause) {
        super(cause);
    }

    public ExcelNumberFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
