package cn.ming.smartmedic.Service.Impl;

import cn.ming.smartmedic.Service.CosService;
import cn.ming.smartmedic.config.CosClientFactory;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

import static com.qcloud.cos.demo.BucketRefererDemo.cosClient;

@Slf4j
@Service
public class CosServiceImpl implements CosService {

    @Autowired
    private CosClientFactory config;

    @Value("${smartMedicBucket}")
    private String bucketName;

    @Override
    public String uploadImage(File file) {
        String cosImagePath = file.getName() + "-" + System.currentTimeMillis();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, cosImagePath, file);
        PutObjectResult res = cosClient.putObject(putObjectRequest);
        log.info("upload image res:{}", res);

        return cosImagePath;
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