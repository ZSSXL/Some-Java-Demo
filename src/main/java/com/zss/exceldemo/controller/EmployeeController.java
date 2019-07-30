package com.zss.exceldemo.controller;

import com.zss.exceldemo.entity.Employee;
import com.zss.exceldemo.service.EmployeeService;
import com.zss.exceldemo.service.ExportExcelService;
import com.zss.exceldemo.util.QRCodeUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.ModalExclude;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class EmployeeController {

    @Autowired
    private ExportExcelService exportExcelServiceImpl;

    @Autowired
    private EmployeeService employeeServiceImpl;

    @GetMapping("/export_excel")
    public Object exportExcel(HttpServletResponse response){
        response.setContentType("application/binary;charset=utf-8");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH:mm");
            Date date = new Date();
            String fileName = new String((sdf.format(date)).getBytes(), "utf-8");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
            exportExcelServiceImpl.exportExcel(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }


    @GetMapping("/create_qr")
    public Object createQRCode(HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            Employee tom = this.employeeServiceImpl.createQRCode("Jarry");
            QRCodeUtil.writeToStream(tom,outputStream,300);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;

    }

}
