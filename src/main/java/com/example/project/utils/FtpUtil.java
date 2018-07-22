package com.example.project.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xuhan on 2018/6/20.
 */
public class FtpUtil {

    private final static Log logger = LogFactory.getLog(FtpUtil.class);
    private static String server;
    private static int port;
    private static String user;
    private static String passwd;
    private static String home;
    private static String http;
    private static boolean isInital = false;
    private static ReentrantLock lock = new ReentrantLock(true);

    public FtpUtil() {
    }

    public static String putFile(InputStream in, String materialName, String... childPathSections) throws IOException {
        initial();
        StringBuilder sb = new StringBuilder();
        FTPClient ftpClient = getFtpClient();
        gotoTargetDir(sb, ftpClient, childPathSections);
        putFile(ftpClient, materialName, in);
        sb.append(materialName);
        in.close();
        ftpClient.logout();
        ftpClient.disconnect();
        ftpClient = null;
        return sb.toString();
    }

    private static void gotoTargetDir(StringBuilder sb, FTPClient ftpClient, String... childPathSections) throws IOException {
        sb.append(http).append(home);
        boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory(home);
        if(!changeWorkingDirectory) {
            throw new IOException("无法抵达ftp根目录：" + home);
        } else {
            String[] arr$ = childPathSections;
            int len$ = childPathSections.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String childPath = arr$[i$];
                if(!sb.toString().endsWith("/") && !childPath.endsWith("/")) {
                    sb.append("/");
                }

                sb.append(childPath);
                ftpClient.makeDirectory(childPath);
                if(!ftpClient.changeWorkingDirectory(childPath)) {
                    throw new IOException("无法抵达ftp子目录:" + childPath + " 完整路径:" + Arrays.toString(childPathSections));
                }
            }

            sb.append("/");
        }
    }

    private static void initial() throws IOException {
        lock.lock();

        try {
            if(!isInital) {
                InputStream in = FtpUtil.class.getResourceAsStream("/ftp.properties");
                Properties properties = new Properties();
                properties.load(in);
                user = properties.getProperty("ftp.username");
                passwd = properties.getProperty("ftp.password");
                server = properties.getProperty("ftp.host");
                port = Integer.parseInt(properties.getProperty("ftp.port"));
                home = properties.getProperty("ftp.home", "");
                http = properties.getProperty("ftp.http", "");
                if(StringUtils.isNotEmpty(http)) {
                    if(!http.startsWith("http://")) {
                        http = "http://" + http;
                    }

                    if(!http.endsWith("/")) {
                        http = http + "/";
                    }
                }

                isInital = true;
            }
        } finally {
            lock.unlock();
        }

    }

    private static void putFile(FTPClient ftpClient, String fileFullName, InputStream in) throws IOException {
        ftpClient.dele(fileFullName);
        ftpClient.storeFile(fileFullName, in);
    }

    private static FTPClient getFtpClient() throws SocketException, IOException {
        FTPClient ftpClient = new FTPClient();
        configB4Connect(ftpClient);
        ftpClient.connect(server, port);
        ftpClient.login(user, passwd);
        configAfterLog(ftpClient);
        return ftpClient;
    }

    private static void configAfterLog(FTPClient ftpClient) throws IOException {
        ftpClient.setFileTransferMode(10);
        ftpClient.setFileType(2);
        ftpClient.setTcpNoDelay(true);
        ftpClient.enterLocalPassiveMode();
    }

    private static void configB4Connect(FTPClient ftpClient) throws SocketException {
        ftpClient.setControlEncoding("GBK");
        ftpClient.setAutodetectUTF8(true);
        ftpClient.setSendBufferSize(1024);
        ftpClient.setReceiveBufferSize(1024);
        ftpClient.setBufferSize(1024);
    }
}
