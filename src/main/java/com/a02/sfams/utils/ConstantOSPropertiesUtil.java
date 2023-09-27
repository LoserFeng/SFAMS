package com.a02.sfams.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantOSPropertiesUtil implements InitializingBean {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.secret}")
    private String secret;

    @Value("${aliyun.oss.bucket}")
    private String bucket;

    public static String ALIYUN_ENDPOINT;
    public static String ALIYUN_ACCESSKEYID;
    public static String ALIYUN_BUCKET;
    public static String ALIYUN_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
       ALIYUN_ACCESSKEYID=accessKeyId;
       ALIYUN_ENDPOINT=endpoint;
       ALIYUN_BUCKET=bucket;
       ALIYUN_SECRET=secret;
    }
}
