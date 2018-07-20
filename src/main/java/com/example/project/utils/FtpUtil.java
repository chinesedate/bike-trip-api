package com.example.project.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * Created by xuhan on 2018/6/20.
 */
public class FtpUtil {

    private final static Log logger = LogFactory.getLog(FtpUtil.class);

    private final static String FTP_HOST = "127.0.0.1";
    private final static Integer FTP_PORT = 21;
    private final static String FTP_USER_NAME = "root";
    private final static String FTP_PASSWORD = "bike123";

    /**
     * 获取FTPClient对象
     *
     * @param ftpHost     FTP主机服务器
     * @param ftpPassword FTP 登录密码
     * @param ftpUserName FTP登录用户名
     * @param ftpPort     FTP端口 默认为21
     * @return
     */
    public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                         String ftpPassword, int ftpPort) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                logger.info("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                logger.info("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            logger.info("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }

    /**
     * 下载文件
     *
     * @param ftpPath     ftp文件存放物理路径
     * @param fileName    文件路径
     */
    public static void downloadFile(String ftpPath, String localPath,
                                    String fileName) {
        FTPClient ftpClient = null;

        try {
            ftpClient = getFTPClient(FTP_HOST, FTP_USER_NAME, FTP_PASSWORD, FTP_PORT);
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);

            File localFile = new File(localPath + File.separatorChar + fileName);
            OutputStream os = new FileOutputStream(localFile);
            ftpClient.retrieveFile(fileName, os);
            os.close();
            ftpClient.logout();

        } catch (FileNotFoundException e) {
            logger.error("没有找到" + ftpPath + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            logger.error("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件读取错误。");
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param ftpPath     ftp文件存放物理路径
     * @param fileName    文件路径
     * @param input       文件输入流，即从本地服务器读取文件的IO输入流
     */
    public static void uploadFile(String ftpPath,
                                  String fileName, InputStream input) {
        FTPClient ftp = null;
        try {
            ftp = getFTPClient(FTP_HOST, FTP_USER_NAME, FTP_PASSWORD, FTP_PORT);
            ftp.changeWorkingDirectory(ftpPath);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
            ftp.storeFile(fileName, input);
            input.close();
            ftp.logout();
            System.out.println("upload succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
