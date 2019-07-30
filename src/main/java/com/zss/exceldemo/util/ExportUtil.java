package com.zss.exceldemo.util;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.swing.*;

/**
 * 导出excel工具类
 * @Author ZSS
 */


public class ExportUtil {

    private XSSFWorkbook workbook = null;

    private XSSFSheet sheet = null;

    public ExportUtil(XSSFWorkbook workbook, XSSFSheet sheet) {
        this.workbook = workbook;
        this.sheet = sheet;
    }

    /**
     * 合并单元格后给合并后的单元格加边框
     * @param rangeAddress 范围
     * @param cellStyle 单元格
     */
    public void setRegionStyle(CellRangeAddress rangeAddress, XSSFCellStyle cellStyle){
        int toproNum = rangeAddress.getFirstColumn();
        for(int i = toproNum;i <= rangeAddress.getLastRow(); i++){
            XSSFRow row = sheet.getRow(i);
            for(int j = rangeAddress.getFirstColumn(); j <= rangeAddress.getLastColumn(); j++){
                XSSFCell cell = row.getCell(j);
                cell.setCellStyle(cellStyle);
            }
        }
    }

    /**
     * 设置表头单元格样式
     * @return
     */
    public XSSFCellStyle getHeadStyle(){
        // 创建单元格样式
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        // 设置单元格的背景颜色为淡蓝色
        cellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = workbook.createFont();
        // 设置字体加粗
        font.setBold(true);
        font.setFontName("宋体");
        font.setFontHeight((short) 300);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        return cellStyle;
    }

    /**
     * 设置表体的单元格样式
     * @return
     */
    public XSSFCellStyle getBodyStyle(){

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        // 设置单元格的背景颜色为淡蓝色
        cellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = workbook.createFont();
        // font.setBold(true);
        font.setFontName("宋体");
        font.setFontHeight((short)200);
        cellStyle.setFont(font);
        // 设置单元格边框
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        return cellStyle;
    }

}
