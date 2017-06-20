package com.qq.admin.utils;

import com.qingqing.common.exception.QingQingRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * Created by wangyangyang on 2017/6/2.
 */
public class QingqingFtpClient {

    private static final Logger logger = LoggerFactory.getLogger(QingqingFtpClient.class);

    private FTPClient client ;
    private FTPClientConfig config;
    private String host;
    private int port = 21;
    /** ftp服务器用户名 */
    private String username;
    /** ftp服务器密码 */
    private String password;
    private String regEx = "*";

    private int timeoutMills =30000;


    public void connect(){
        client = new FTPClient();
        client.setConnectTimeout(timeoutMills);
        try {
            if (!client.isConnected()) {
                client.connect(host, port);
                client.login(username, password);
                int reply = client.getReplyCode();
                if (!FTPReply.isPositiveCompletion(reply)) {
                    logger.info("username:{} or password error", username);
                    client.disconnect();
                    throw new QingQingRuntimeException("user name or password error");
                } else {
                    logger.info("file ftp connected");
                }
                client.setControlEncoding("utf-8");
                client.setFileType(FTPClient.BINARY_FILE_TYPE);
                client.enterLocalPassiveMode();
            }

        }catch (Exception e){
            try {
                client.disconnect();
            } catch (Exception e1) {
                logger.error("connect error", e1);
            }
            throw new QingQingRuntimeException("failed to connect ftp server", e);
        }
    }

    /**
     * input: /1w_hash.txt
     */
    public InputStream downloadByFilePath(String filePath) {
        connect(); // double check
        filePath = com.qingqing.common.util.StringUtils.notNullTrim(filePath);
        int slashIndex = filePath.lastIndexOf("/");
        String remoteDir = filePath.substring(0, slashIndex  + 1);
        String downloadFileName = filePath.substring(slashIndex + 1, filePath.length());
        try {
            client.changeWorkingDirectory(remoteDir);
            logger.info("switching to ftp dir:{}", remoteDir);
            FTPFile[] ftpFiles = client.listFiles(regEx);
            if(ftpFiles.length == 0) {
                throw new QingQingRuntimeException("no files exist in " + remoteDir);
            }
            logger.info("{} files in dir:{}", ftpFiles.length, remoteDir);
            InputStream is = null;
            for (FTPFile file : ftpFiles) {
                if(StringUtils.isNotBlank(downloadFileName) && !file.getName().equals(downloadFileName)){
                    continue;
                }
                is = client.retrieveFileStream(file.getName());

            }
            if(is == null) throw new QingQingRuntimeException("download file " + downloadFileName + " failed");
            logger.info("{} downloaded succ", filePath);
            return is;
        } catch (Exception e) {
            throw new QingQingRuntimeException("download failed" + e.getMessage());
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
