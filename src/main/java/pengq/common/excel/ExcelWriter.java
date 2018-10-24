package pengq.common.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pengq.common.excel.model.FieldSummary;
import pengq.common.excel.utils.EXCellUtil;
import pengq.common.excel.utils.FieldParseUtil;
import pengq.common.excel.utils.FieldUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by pengq on 2018/10/11 8:50
 * Description: 不支持多sheet 写入
 */

public class ExcelWriter {
    private Workbook workbook = new XSSFWorkbook();
    private FileOutputStream outputStream;

    public ExcelWriter(String path) throws IOException {
        outputStream = new FileOutputStream(path);
    }

    public <T> boolean write(List<T> list, Class<T> clazz) throws IOException {
        return write(null, list, clazz);
    }

    public <T> boolean write(String sheetName, List<T> list, Class<T> clazz) throws IOException {
        if (list == null || list.isEmpty()) {
            return false;
        }

        // TODO: 2018/10/24 parse WorkBookWrite Annotation
        List<String> cellCache = new ArrayList<>();
        Map<String, FieldSummary> summaryMapper = FieldParseUtil.parseWriteCell(clazz);

        putCellCache(cellCache,summaryMapper);

        Sheet sheet = sheetName == null || sheetName.isEmpty() ?
                workbook.createSheet() : workbook.createSheet(sheetName);

        int length = list.size();
        for (int i = 0; i < length; i++) {
            Row row = sheet.createRow(i);
            T t = list.get(i);
            summaryMapper.forEach((key, value) -> {
                int cellPosition = EXCellUtil.getCellNumber(value.getExCell());
                Cell cell = row.createCell(cellPosition);
                addValueToCell(cell, t, value);
            });
        }

        sheetFormat(sheet,cellCache);

        workbook.write(outputStream);
        return true;
    }

    public void close(){
        if (workbook != null){
            try {
                workbook.close();
            } catch (IOException ignored) {
            }
        }

        if (outputStream != null){
            try {
                outputStream.close();
            } catch (IOException ignored) {
            }
        }
    }

    private String format(Date date, String regex) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(regex);
        return dateFormat.format(date);
    }

    private String format(Double value, String regex) {
        DecimalFormat decimalFormat = new DecimalFormat(regex);
        return decimalFormat.format(value);
    }

    private <T> void addValueToCell(Cell cell, T object, FieldSummary summary) {
        cell.setCellStyle(getCellStyle());
        Object value = FieldUtil.invoke(object, summary.getFieldName());
        if (summary.getFieldType() == Date.class) {
            if (summary.getDateFormat() != null && !summary.getDateFormat().isEmpty()) {
                cell.setCellType(CellType.STRING);
                cell.setCellValue(format((Date) value, summary.getDateFormat()));
            } else {
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue((Date) value);
            }
        } else if (summary.getFieldType() == Double.class) {
            if (summary.getDoubleFormat() != null && !summary.getDoubleFormat().isEmpty()) {
                cell.setCellType(CellType.STRING);
                cell.setCellValue(format(Double.valueOf(String.valueOf(value)), summary.getDoubleFormat()));
            } else {
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(Double.valueOf(String.valueOf(value)));
            }
        } else {
            cell.setCellType(CellType.STRING);
            cell.setCellValue(String.valueOf(value));
        }

    }

    private CellStyle getCellStyle() {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.LEFT); //水平布局：居中
        cellStyle.setWrapText(false);
        cellStyle.setFont(getFontStyle());
        return cellStyle;
    }

    private Font getFontStyle() {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12); //字体高度
        return font;
    }

    private void putCellCache(List<String> cellCache, Map<String,FieldSummary> map){
        List<FieldSummary> list = new ArrayList<>(map.values());
        for (FieldSummary summary: list) {
            String cellPosition = String.valueOf(EXCellUtil.getCellNumber(summary.getExCell()));
            if (!cellCache.contains(cellPosition)){
                cellCache.add(cellPosition);
            }
        }
    }

    private void sheetFormat(Sheet sheet,List<String> cells){
        for (String cell: cells) {
            sheet.autoSizeColumn(Integer.parseInt(cell));
        }
    }
}
