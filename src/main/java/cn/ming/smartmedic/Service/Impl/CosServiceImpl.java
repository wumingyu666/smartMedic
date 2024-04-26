package cn.ming.smartmedic.Service.Impl;

import cn.ming.smartmedic.Service.CosService;
import cn.ming.smartmedic.config.CosClientFactory;
import cn.ming.smartmedic.utils.ImageUtils;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.qcloud.cos.demo.BucketRefererDemo.cosClient;

@Slf4j
@Service
public class CosServiceImpl implements CosService {

    @Autowired
    private CosClientFactory config;

    @Value("${smartMedicBucket}")
    private String bucketName;

    @Value("${smartMedicBucketUrl}")
    private String bucketUrl;

    @Override
    public String uploadImage(File file) {
        String cosImagePath = genUploadImagePath(file.getName());
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, cosImagePath, file);
        PutObjectResult res = config.cosClient.putObject(putObjectRequest);
        log.info("upload image res:{}", res);

        return bucketUrl + cosImagePath;
    }

    public static String genUploadImagePath(String fileName) {
        // 找到最后一个 . 的位置
        int lastIndex = fileName.lastIndexOf(".");

        // 截取不包含扩展名的部分
        String nameWithoutExtension = fileName.substring(0, lastIndex);
        String extension = fileName.substring(lastIndex + 1);

        // 获取当前时间的毫秒值
        long time = System.currentTimeMillis();

        // 格式化时间
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedTime = now.format(formatter);

        // 重新拼接文件名
        String newFileName = String.format("%s_%s.%s", nameWithoutExtension, formattedTime, extension);

        System.out.println("New file name: " + newFileName);
        return newFileName;
    }

    public static void main(String[] args) {
        CosServiceImpl cosService = new CosServiceImpl();
        CosServiceImpl.genUploadImagePath("tmp/image/1234.png");
    }

    public void listObjects() {
        String bucketName = "smart-medic-1302466894";
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(bucketName);
        listObjectsRequest.setPrefix("/");
        listObjectsRequest.setDelimiter("/");
        listObjectsRequest.setMaxKeys(1000);
        ObjectListing objectListing = null;
        do {
            try {
                objectListing = config.cosClient.listObjects(listObjectsRequest);
            } catch (CosServiceException e) {
                e.printStackTrace();
                return;
            } catch (CosClientException e) {
                e.printStackTrace();
                return;
            }
            // common prefix 表示被 delimiter 截断的路径, 如 delimter 设置为/, common prefix 则表示所有子目录的路径
            List<String> commonPrefixs = objectListing.getCommonPrefixes();

            // object summary 表示所有列出的 object 列表
            List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
            for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
                // 文件的路径 key
                String key = cosObjectSummary.getKey();
                // 文件的 etag
                String etag = cosObjectSummary.getETag();
                // 文件的长度
                long fileSize = cosObjectSummary.getSize();
                // 文件的存储类型
                String storageClasses = cosObjectSummary.getStorageClass();
                System.out.println("key:" + key);
            }

            String nextMarker = objectListing.getNextMarker();
            listObjectsRequest.setMarker(nextMarker);
        } while (objectListing.isTruncated());

    }
}
