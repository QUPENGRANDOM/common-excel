package pengq.common.excel.model;

import pengq.common.excel.annotation.Cell;

import java.util.Arrays;
import java.util.List;

/**
 * FileName:     ReadResoponse
 *
 * @version V1.0
 * CreateDate:         2018/10/18 10:09
 * @Description: TODO(用一句话描述该文件做什么)
 * All rights Reserved, Designed By nuctech.ltd
 * Copyright:    Copyright(C) 2018-2028
 * Company       同方威视技术股份有限公司
 * @author: pengq
 */

public class ReadResoponse<T> {
    @Cell(cell = EXCell.A, target = Integer.class)
    List<Sheet> sheets;

    public List<Sheet> getSheets() {
        return sheets;
    }

    public void setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
    }

    public class Sheet {
        private String name;
        private String[] headerFields;
        private String[] headerNames;
        private List<T> data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String[] getHeaderNames() {
            return headerNames;
        }

        public void setHeaderNames(String[] headerNames) {
            this.headerNames = headerNames;
        }

        public String[] getHeaderFields() {
            return headerFields;
        }

        public void setHeaderFields(String[] headerFields) {
            this.headerFields = headerFields;
        }

        public List<T> getData() {
            return data;
        }

        public void setData(List<T> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Sheet{" +
                    "name='" + name + '\'' +
                    ", headerFields=" + Arrays.toString(headerFields) +
                    ", headerNames=" + Arrays.toString(headerNames) +
                    ", data=" + data +
                    '}';
        }
    }
}
