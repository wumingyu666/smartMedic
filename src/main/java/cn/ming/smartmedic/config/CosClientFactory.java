package cn.ming.smartmedic.config;

import cn.ming.smartmedic.domain.CosTmpSecretConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicSessionCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import com.tencent.cloud.CosStsClient;
import com.tencent.cloud.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.TreeMap;

@Component
public class CosClientFactory {

    @Value("${cosSecretId}")
    private String secretId;

    @Value("${cosSecretKey}")
    private String secretKey;

    private CosTmpSecretConfig cosTmpSecretConfig;

    public COSClient cosClient;

    public CosClientFactory() throws IOException {
        cosTmpSecretConfig = getTmpSecretConfig();

        BasicSessionCredentials cred = new BasicSessionCredentials(cosTmpSecretConfig.getSecretId(), cosTmpSecretConfig.getSecretKey(), cosTmpSecretConfig.getSecretToken());
        Region region = new Region("ap-shanghai");
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        cosClient = new COSClient(cred, clientConfig);
    }

    private CosTmpSecretConfig getTmpSecretConfig() throws IOException {
        TreeMap<String, Object> config = new TreeMap<String, Object>();

        // 云 api 密钥 SecretId
        config.put("secretId", "AKIDb3MYtO0b4Tkt4OaYIn4RPGVaQ3RaUAhz");
        // 云 api 密钥 SecretKey
        config.put("secretKey", "CQVPMHAyiMNaxWYXVft5KkGphnjZGDMR");

        // 临时密钥有效时长，单位是秒
        config.put("durationSeconds", 1800);

        // 换成你的 bucket
        config.put("bucket", "smart-medic-1302466894");
        // 换成 bucket 所在地区
        config.put("region", "ap-shanghai");

        // 可以通过 allowPrefixes 指定前缀数组, 例子： a.jpg 或者 a/* 或者 * (使用通配符*存在重大安全风险, 请谨慎评估使用)
        config.put("allowPrefixes", new String[]{
                "*"
        });

        // 密钥的权限列表。简单上传和分片需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
        String[] allowActions = new String[]{
                // 简单上传
                "name/cos:PutObject",
                "name/cos:PostObject",
                // 分片上传
                "name/cos:InitiateMultipartUpload",
                "name/cos:ListMultipartUploads",
                "name/cos:ListParts",
                "name/cos:UploadPart",
                "name/cos:CompleteMultipartUpload",
                "name/cos:GetBucket"
        };
        config.put("allowActions", allowActions);

        Response response = CosStsClient.getCredential(config);
        // 1 传入获取到的临时密钥 (tmpSecretId, tmpSecretKey, sessionToken)
        String tmpSecretId = response.credentials.tmpSecretId;
        String tmpSecretKey = response.credentials.tmpSecretKey;
        String sessionToken = response.credentials.sessionToken;

        CosTmpSecretConfig cosTmpSecretConfig = new CosTmpSecretConfig();
        cosTmpSecretConfig.setSecretId(tmpSecretId);
        cosTmpSecretConfig.setSecretKey(tmpSecretKey);
        cosTmpSecretConfig.setSecretToken(sessionToken);

        return cosTmpSecretConfig;
    }

}
