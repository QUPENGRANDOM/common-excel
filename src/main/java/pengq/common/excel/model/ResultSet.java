package pengq.common.excel.model;

import pengq.common.excel.exception.ExcelNumberFormatException;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * Created By pengq On 2018/11/28 20:15
 */
public class ResultSet {
    private Map<String, Object> rowMapper;

    public ResultSet(Map<String, Object> rowMapper) {
        this.rowMapper = rowMapper;
    }

    public int getInt(String key) throws ExcelNumberFormatException {
        Object value = rowMapper.get(key);
        if (value == null) {
            return 0;
        }

        try {
            return Integer.valueOf(value.toString().trim());
        } catch (NumberFormatException e) {
            throw new ExcelNumberFormatException("number format exception");
        }
    }

    public long getLong(String key) throws ExcelNumberFormatException {
        Object value = rowMapper.get(key);
        if (value == null) {
            return 0L;
        }

        try {
            return Long.valueOf(value.toString().trim());
        } catch (NumberFormatException e) {
            throw new ExcelNumberFormatException("number format exception");
        }
    }

    public float getFloat(String key) throws ExcelNumberFormatException {
        Object value = rowMapper.get(key);
        if (value == null) {
            return 0.0F;
        }

        try {
            return Float.valueOf(value.toString().trim());
        } catch (NumberFormatException e) {
            throw new ExcelNumberFormatException("number format exception");
        }
    }

    public double getDouble(String key) throws ExcelNumberFormatException {
        Object value = rowMapper.get(key);
        if (value == null) {
            return 0.0D;
        }

        try {
            return Double.valueOf(value.toString().trim());
        } catch (NumberFormatException e) {
            throw new ExcelNumberFormatException("number format exception");
        }
    }

    public Date getDate(String key) throws ExcelNumberFormatException {
        Object value = rowMapper.get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof Date) {
            return (Date) value;
        } else if (value instanceof String) {
            try {
                DateFormat df = DateFormat.getDateInstance();
                return df.parse(value.toString());
            } catch (NumberFormatException | ParseException e) {
                throw new ExcelNumberFormatException("number format exception");
            }
        }
        throw new ExcelNumberFormatException("number format exception");
    }

    public short getShort(String key) throws ExcelNumberFormatException {
        Object value = rowMapper.get(key);
        if (value == null) {
            return 0;
        }
        try {
            return Short.valueOf(value.toString().trim());
        } catch (NumberFormatException e) {
            throw new ExcelNumberFormatException("number format exception");
        }
    }

    public boolean getBoolean(String key) throws ExcelNumberFormatException {
        Object value = rowMapper.get(key);
        if (value == null) {
            return false;
        }

        if (value instanceof Boolean) {
            return (Boolean) value;
        } else {
            try {
                return Double.compare(Double.parseDouble(value.toString().trim()), 0.0D) != 0;
            } catch (NumberFormatException e) {
                throw new ExcelNumberFormatException("number format exception");
            }
        }
    }

    public String getString(String key){
        Object value = rowMapper.get(key);
        return String.valueOf(value);
    }
}
