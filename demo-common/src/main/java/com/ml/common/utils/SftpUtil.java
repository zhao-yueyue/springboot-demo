package com.ml.common.utils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SftpUtil {
    private static final Logger logger = LogManager.getLogger(SftpUtil.class);

    /**
     * FTPClient对象
     **/
    private static ChannelSftp ftpClient = null;
    /**
     *
     */
    private static Session sshSession = null;

    /**
     * 连接服务器
     * @param host ip
     * @param port 端口
     * @param userName 用户
     * @param password 密码
     * @return FTPClient对象
     */
    public static ChannelSftp getConnect(String host, String port, String userName, String password) throws Exception {
        try {
            JSch jsch = new JSch();
            // 获取sshSession
            sshSession = jsch.getSession(userName, host, Integer.parseInt(port));
            // 添加s密码
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            // 开启sshSession链接
            sshSession.connect();
            // 获取sftp通道
            ftpClient = (ChannelSftp) sshSession.openChannel("sftp");
            // 开启
            ftpClient.connect();
            logger.debug("success ..........");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("连接sftp服务器异常。。。。。。。。");
        }
        return ftpClient;
    }

    /**
     * 下载文件
     * @param ftp_path	服务器文件路径
     * @param save_path	下载保存路径
     * @param oldFileName	服务器上文件名
     * @param newFileName	保存后新文件名
     */
    public static void download(String ftp_path, String save_path, String oldFileName, String newFileName) {
        FileOutputStream fos = null;
        try {
            ftpClient.cd(ftp_path);
//            File file = new File(save_path);
//            if (!file.exists()) {
//                file.mkdirs();
//            }

            File file = new File(save_path, newFileName);
            if (!file.getParentFile().exists()) {
                boolean mkdirsResult = file.getParentFile().mkdirs();
                if (!mkdirsResult) {
                    throw new RuntimeException("创建目录失败");
                }
            }
            if (!file.exists()) {
                boolean createFileResult = file.createNewFile();
                if (!createFileResult) {
                    throw new RuntimeException("创建文件失败");
                }
            }

            fos = new FileOutputStream(file);
            ftpClient.get(oldFileName, fos);
        } catch (Exception e) {
            /*if(Objects.nonNull(ftpClient)){
                close();
            }*/
            e.printStackTrace();
            logger.error("download file error............");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("close stream error............");
                }
            }
        }
    }

    /**
     * 上传
     * @param upload_path 上传文件路径
     * @param ftp_path	服务器保存路径
     * @param newFileName	新文件名
     */
    public static void uploadFile(String upload_path, String ftp_path, String newFileName) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(upload_path));
            ftpClient.cd(ftp_path);
            ftpClient.put(fis, newFileName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("Upload file error............");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.debug("close stream error............");
                }
            }
        }
    }

    /**
     * 关闭
     */
    public static void close() throws Exception {
        logger.debug("close............");
        try {
            ftpClient.disconnect();
            sshSession.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("close stream error.");
        }
    }

    public static void main(String[] args) throws Exception {
        try {
//            getConnect("180.76.176.197", "22", "root", "JY^Court)1025%");
//            download("/home/testuser/test/ceshi2/","C:/Users/Downloads/log/","38357_mediate_1591695318973.xml","38357_mediate_202006181529.xml");

            ChannelSftp sftp = getConnect("180.76.184.90", "22", "root", "Ebeatcourt@2018");
            System.out.println(sftp);
            download("/data/law_project/pics/2020/2020-07-01/applicant/","C:/Users/Administrator/Downloads/log/","2020_1593567738106_applicant_7022.jpg","2020_1593567738106_applicant_7022.jpg");

            uploadFile("C:/Users/Administrator/Downloads/log/","/data/law_project/pics/2020/2020-07-01/applicant/","2020_1593567738106_applicant_7022.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}