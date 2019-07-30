package com.zss.exceldemo.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

public interface ExportExcelService {

    void exportExcel(OutputStream stream);

    void exportExcelToLocal();

}
