package com.share.util;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.PutObjectOptions;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Component
public class MinioUtil {
    private static final Integer DEFAULT_EXPIRY_TIME = 7 * 24 * 3600;
    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

//    检测桶是否存在
    @SneakyThrows
    public boolean bucketExists(String bucketName){
        boolean flag = false;
        flag = minioClient.bucketExists(bucketName);
        if(flag){
            return true;
        }
        return false;
    }

    @SneakyThrows
    public boolean makeBucket(String bucketName) {
        boolean flag = bucketExists(bucketName);
        if (!flag) {
            minioClient.makeBucket(bucketName);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通过InputStream上传对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param stream     要上传的流
     * @return
     */
    @SneakyThrows
    public boolean putObject(String bucketName, String objectName, InputStream stream) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            minioClient.putObject(bucketName, objectName, stream, new PutObjectOptions(stream.available(), -1));
            ObjectStat statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.length() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 以流的形式获取一个文件对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     */
    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            ObjectStat statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.length() > 0) {
                InputStream stream = minioClient.getObject(bucketName, objectName);
                return stream;
            }
        }
        return null;
    }
    /**
     * 获取对象的元数据
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     */
    @SneakyThrows
    public ObjectStat statObject(String bucketName, String objectName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            ObjectStat statObject = minioClient.statObject(bucketName, objectName);
            return statObject;
        }
        return null;
    }

    /**
     * 文件访问路径
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     */
    @SneakyThrows
    public String getObjectUrl(String bucketName, String objectName) {
        boolean flag = bucketExists(bucketName);
        String url = "";
        if (flag) {
            url = minioClient.getObjectUrl(bucketName, objectName);
        }
        return url;
    }


    /**
     * 文件访问路径
     *
     * @param file 上传的文件
     * @param foldername 文件夹名字
     * @return 文件访问路径
     */
    public String upload(MultipartFile file,String foldername) {
        if (file.isEmpty() || file.getSize() == 0) {
            return "文件为空";
        }
        try {
            if (!bucketExists(bucketName)) {
                makeBucket(bucketName);
            }
            String fileName = file.getOriginalFilename();
            String newName = foldername + "/" + UUID.randomUUID().toString().replaceAll("-", "")
                    + fileName.substring(fileName.lastIndexOf("."));

            InputStream inputStream = file.getInputStream();
            putObject(bucketName, newName, inputStream);
            inputStream.close();

            String url = getObjectUrl(bucketName, newName);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
    }


}
