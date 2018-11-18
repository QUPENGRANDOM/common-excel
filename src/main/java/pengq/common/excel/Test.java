package pengq.common.excel;

import pengq.common.excel.model.MyWorkbook;

import java.io.IOException;

/**
 * FileName:     Test
 *
 * @version V1.0
 * CreateDate:         2018/10/30 17:35
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By nuctech.ltd
 * Copyright:    Copyright(C) 2018-2028
 * Company       同方威视技术股份有限公司
 * @author: pengq
 */

public class Test {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        ExcelReader reader = new ExcelReader("C:\\Users\\99400\\Desktop\\新建 Microsoft Excel 工作表 (3).xlsx");

        MyWorkbook workbook = reader.read();

        ExcelWriter writer = new ExcelWriter("C:\\Users\\99400\\Desktop\\xxxSSS.xlsx");
        writer.write(workbook);
        System.out.println(System.currentTimeMillis() - start);
    }
}
