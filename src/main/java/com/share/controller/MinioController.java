package com.share.controller;

import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.util.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@RestController
public class MinioController {
    @Autowired
    MinioUtil minioUtil;

    @PostMapping("/upload")
    public RestObject<String> MinIOUpload(MultipartFile file) {
        if (file.isEmpty() || file.getSize() == 0) {
            return RestResponse.makeErrRsp("文件为空");
        }
        try {
            if (!minioUtil.bucketExists("share")) {
                minioUtil.makeBucket("share");
            }

            String fileName = file.getOriginalFilename();
            String newName = "image/" + UUID.randomUUID().toString().replaceAll("-", "")
                    + fileName.substring(fileName.lastIndexOf("."));

            InputStream inputStream = file.getInputStream();
            minioUtil.putObject("share", newName, inputStream);
            inputStream.close();

            String url = minioUtil.getObjectUrl("share", newName);
            return RestResponse.makeErrRsp(url);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.makeErrRsp("上传失败");
        }
    }
}
