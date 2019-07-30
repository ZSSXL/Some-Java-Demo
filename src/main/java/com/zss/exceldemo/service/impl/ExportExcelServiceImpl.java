package com.zss.exceldemo.service.impl;

import com.zss.exceldemo.entity.Employee;
import com.zss.exceldemo.repostory.EmployeeRepostory;
import com.zss.exceldemo.service.ExportExcelService;
import com.zss.exceldemo.util.ExportUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ExportExcelServiceImpl implements ExportExcelService {

    @Autowired
    private EmployeeRepostory employeeRepostory;

    @Override
    public void exportExcel(OutputStream stream) {

        String[] titles = {"用户id","用户名","用户密码","电话","邮箱","员工工号","所属公司"};

        List<Employee> employeeList = employeeRepostory.findAll();
        // 创建一个workbook 对应一个excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在workbook中添加一个sheet，对应excel文件中的sheet
        XSSFSheet sheet = workbook.createSheet("员工表");
        ExportUtil exportUtil = new ExportUtil(workbook,sheet);
        XSSFCellStyle headStyle = exportUtil.getHeadStyle();
        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
        // 构建表头
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        for(int i = 0; i < titles.length;i++){
            cell = headRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(titles[i]);
        }

        // 构建表体数据
        int  i = 0;
        for(Employee emp : employeeList){

            XSSFRow bodyRow = sheet.createRow( i + 1);

            cell = bodyRow.createCell(0);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(emp.getUserId());
            sheet.autoSizeColumn(0);

            cell = bodyRow.createCell(1);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(emp.getUsername());
            sheet.autoSizeColumn(1);

            cell = bodyRow.createCell(2);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(emp.getPassword());
            sheet.autoSizeColumn(2);

            cell = bodyRow.createCell(3);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(emp.getPhone());
            sheet.autoSizeColumn(3);

            cell = bodyRow.createCell(4);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(emp.getEmail());
            sheet.autoSizeColumn(4);

            cell = bodyRow.createCell(5);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(emp.getEmployee_id());
            sheet.autoSizeColumn(5);

            cell = bodyRow.createCell(6);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(emp.getEnterprise());
            sheet.autoSizeColumn(6);

            i++;
        }

        try{
            workbook.write(stream);
            stream.flush();
            stream.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exportExcelToLocal() {

        String[] titles = {"用户id","用户名","用户密码","电话","邮箱","员工工号","所属公司"};


        // 创建一个workbook 对应一个excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在workbook中添加一个sheet，对应excel文件中的sheet
        XSSFSheet sheet = workbook.createSheet("员工表");
        ExportUtil exportUtil = new ExportUtil(workbook,sheet);
        XSSFCellStyle headStyle = exportUtil.getHeadStyle();
        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
        // 构建表头
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        for(int i = 0; i < titles.length;i++){
            cell = headRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(titles[i]);
        }

        List<Employee> employeeList = employeeRepostory.findAll();
        // 构建表体数据
        int  i = 0;
        for(Employee emp : employeeList){
            System.out.println("员工信息："+emp);

            XSSFRow bodyRow = sheet.createRow( i + 1);

            cell = bodyRow.createCell(0);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(emp.getUserId());
            sheet.autoSizeColumn(0);

            cell = bodyRow.createCell(1);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(emp.getUsername());
            sheet.autoSizeColumn(1);

            cell = bodyRow.createCell(2);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(emp.getPassword());
            sheet.autoSizeColumn(2);

            cell = bodyRow.createCell(3);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(emp.getPhone());
            sheet.autoSizeColumn(3);

            cell = bodyRow.createCell(4);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(emp.getEmail());
            sheet.autoSizeColumn(4);

            cell = bodyRow.createCell(5);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(emp.getEmployee_id());
            sheet.autoSizeColumn(5);

            cell = bodyRow.createCell(6);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(emp.getEnterprise());
            sheet.autoSizeColumn(6);

            i++;
        }

        OutputStream stream = null;
        try{
            File file = new File("F:\\poi\\");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
            Date date = new Date();
            String fileName = "员工表_"+sdf.format(date)+".xlsx";
            stream = new FileOutputStream(new File(file,fileName));
            workbook.write(stream);
            stream.flush();
            stream.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
