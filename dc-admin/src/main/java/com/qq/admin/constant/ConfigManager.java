package com.qq.admin.constant;

import com.qingqing.common.exception.QingQingRuntimeException;
import com.qq.admin.utils.QingqingFtpClient;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Properties;

/**
 * Created by wangyangyang on 2017/6/2.
 */
public class ConfigManager {
    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    private static final String TRUST_STORE_FILENAME = "changingedu.com-truststore.jks";

    private QingqingFtpClient qingqingFtpClient;

    public void init() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        logger.info("start to init Config");
        Field[] fields = ConfigManager.class.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
            }
        }
        downloadSsoFile();
        logger.info("finish init Config");
    }

    private void downloadSsoFile() {
        try {
            String outPutPath = ConfigManager.class.getClassLoader().getResource("").getPath()+TRUST_STORE_FILENAME;
            InputStream inputStream = qingqingFtpClient.downloadByFilePath(truststoreFilePath);
            FileUtils.copyInputStreamToFile(inputStream,new File(outPutPath));
            Properties systemProps = System.getProperties();
            systemProps.put( "javax.net.ssl.trustStore", outPutPath);
            systemProps.put( "javax.net.ssl.trustStorePassword",trustStorePassword);
            System.setProperties(systemProps);
        } catch (Exception e) {
            logger.error("download sso file failed.");
            throw new QingQingRuntimeException("download sso file failed.",e);
        }

    }

    public static String truststoreFilePath;
    public static String trustStorePassword;


    public static void setTruststoreFilePath(String truststoreFilePath) {
        ConfigManager.truststoreFilePath = truststoreFilePath;
    }

    public static void setTrustStorePassword(String trustStorePassword) {
        ConfigManager.trustStorePassword = trustStorePassword;
    }

    public QingqingFtpClient getQingqingFtpClient() {
        return qingqingFtpClient;
    }

    public void setQingqingFtpClient(QingqingFtpClient qingqingFtpClient) {
        this.qingqingFtpClient = qingqingFtpClient;
    }

}
