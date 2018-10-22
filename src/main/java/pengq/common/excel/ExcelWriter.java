package pengq.common.excel;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileName:     ExcelWriter
 *
 * @version V1.0
 * CreateDate:         2018/10/11 8:50
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By nuctech.ltd
 * Copyright:    Copyright(C) 2018-2028
 * Company       同方威视技术股份有限公司
 * @author: pengq
 */

public class ExcelWriter {
    private Workbook workbook;
    private FileOutputStream outputStream;

    public ExcelWriter(String path) throws IOException {
        workbook = WorkbookFactory.create(new File(path));
    }


}
