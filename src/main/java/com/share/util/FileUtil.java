package com.share.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 文件转换工具类
 * */
public class FileUtil {
    /**
     * 将前端传入的multipartFile转换为byte类型，以供后续base64编码
     * */
    public static byte[] readFileByBytes(MultipartFile multipartFile) throws IOException {
        //根据传入的文件名来新建一个相同的文件
        String filename = multipartFile.getOriginalFilename();
        File file = new File(filename);
        //新建一个输出流
        OutputStream out = null;
        try {
            //以file新建一个文件输出流，并将其赋值给前面的输出流
            out = new FileOutputStream(file);
            //multipartFile转换为byte类型
            byte[] ss = multipartFile.getBytes();
            //将byte写入输出流，实现类型转换
            for(int i = 0; i < ss.length; i++){
                out.write(ss[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //将文件写在当前目录
        File f = new File(file.toURI());

        //以f的长度初始化一个字节输出流对象
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());
        BufferedInputStream in = null;

        try {
            //以f实例化一个文件输入流，并将其写入内存中
            in = new BufferedInputStream(new FileInputStream(f));
            int bufSize = 1024;
            byte[] buffer = new byte[bufSize];
            boolean var6 = false;

            //将文件转换成byte
            int len;
            while(-1 != (len = in.read(buffer, 0, bufSize))) {
                bos.write(buffer, 0, len);
            }


            byte[] var7 = bos.toByteArray();

            return var7;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException var14) {
                var14.printStackTrace();
            }
            if (file.exists()){
                file.delete();
            }

            bos.close();
        }
    }
}
