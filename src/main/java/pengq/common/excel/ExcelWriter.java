package pengq.common.excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by pengq on 2018/10/11 8:50
 * Description: 不支持多sheet 写入
 */

public class ExcelWriter {
    private Workbook workbook;
    private FileOutputStream outputStream;

    public ExcelWriter(String path) throws IOException {
        workbook = WorkbookFactory.create(new File(path));
    }

    public <T> boolean write(List<T> list) {
        return write(null, list);
    }

    public <T> boolean write(String sheetName, List<T> list) {
        Sheet sheet = sheetName == null || sheetName.isEmpty() ?
                workbook.createSheet() : workbook.createSheet(sheetName);
        
        return false;
    }
}
