package pengq.common.excel;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by pengq on 2018/10/11 8:50
 * Description:
 */

public class ExcelWriter {
    private Workbook workbook;
    private FileOutputStream outputStream;

    public ExcelWriter(String path) throws IOException {
        workbook = WorkbookFactory.create(new File(path));
    }


}
