package pengq.common.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileOutputStream;

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
    private HSSFWorkbook workbook = new HSSFWorkbook();

    private FileOutputStream outputStream;

    public void createSheet(){
        workbook.createSheet();
    }

    public void createSheet(String name){
        workbook.createSheet(name);

        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);

        cell.getCellStyle();
    }


}
