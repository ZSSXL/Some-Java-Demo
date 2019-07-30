package com.zss.exceldemo.util;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.zss.exceldemo.entity.Employee;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成二维码工具
 */

@Slf4j
public class QRCodeUtil {

    private static final int size = 255; // 默认二维码长宽为255像素

    private static final String format = "png"; // 默认二维码文件格式

    private static final Map<EncodeHintType,Object> hints = new HashMap<>(); // 二维码参数


    static {
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
        hints.put(EncodeHintType.MARGIN,2); // 二维码与图片的边距
    }

    /**
     * 返回一个BufferedImage对象
     * @param content
     * @param size
     * @return
     */
    public static BufferedImage toBufferedImage(String content,int size){
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,size,size,hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }


    /**
     * 将二维码输出到一个流中
     * @param emp 二维码内容
     * @param stream 输出流
     * @param size 二维码size
     */
    public static void writeToStream(Employee emp, OutputStream stream ,int size) throws IOException {
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(emp.toString(), BarcodeFormat.QR_CODE,size,size,hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        MatrixToImageWriter.writeToStream(bitMatrix,format,stream);
    }

    /**
     * 生成二维码图片文件
     * @param emp 二维码内容
     * @param path 二维码保存路径
     * @param size 二维码size
     * @throws IOException
     */
    public static void createQRCode(Employee emp, String path, int size) throws IOException {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(emp.toString(), BarcodeFormat.QR_CODE, size, size, hints);

            path = path+emp.getUserId()+".png";
            // 得到文件对象
            File file = new File(path);
            // 判断目标文件所在的目录是否存在
            if(!file.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建父目录
                log.info("目标文件所在目录不存在，准备创建它！");
                if (!file.getParentFile().mkdirs()) {
                    log.info("创建目标文件所在目录失败！");
                    return;
                }
            }

            Path outputPath = Paths.get(path);
            MatrixToImageWriter.writeToPath(bitMatrix,format,outputPath);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

}
