package com.example.Services.others;
import org.apache.commons.lang.StringUtils;

import java.awt.image.BufferedImage;
import java.io.*;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;

public class PicToByte
{

    public  String ImageToBase64ByLocal(BufferedImage imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(imgFile, "jpg", bos);
            //data = new byte[in.available()];
            data = bos.toByteArray();
            bos.close();
//            in.read(data);
//            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        return "data:image/jpeg;base64,"+encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    public static boolean Base64ToImage(String imgStr,String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片

        if (StringUtils.isEmpty(imgStr)) // 图像数据为空
            return false;

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }
}