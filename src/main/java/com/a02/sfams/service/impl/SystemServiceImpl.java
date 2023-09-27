package com.a02.sfams.service.impl;

import com.a02.sfams.service.SystemService;
import com.a02.sfams.utils.ConstantOSPropertiesUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
@Service
public class SystemServiceImpl implements SystemService {
    @Override
    public String upload(MultipartFile file) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = ConstantOSPropertiesUtil.ALIYUN_ENDPOINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantOSPropertiesUtil.ALIYUN_ACCESSKEYID;
        String accessKeySecret = ConstantOSPropertiesUtil.ALIYUN_SECRET;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = ConstantOSPropertiesUtil.ALIYUN_BUCKET;
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String fileName = file.getOriginalFilename();
        String timeUrl = new DateTime().toString("yyyy/MM/dd");
        fileName=timeUrl+"/"+fileName;
        // 填写网络流地址。
        //https://hospital-test1.oss-cn-hangzhou.aliyuncs.com/%E5%9B%BE%E7%89%871.png
        String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            InputStream inputStream = file.getInputStream();
            // 创建PutObject请求。
            ossClient.putObject(bucketName, fileName, inputStream);
            return url;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
}
