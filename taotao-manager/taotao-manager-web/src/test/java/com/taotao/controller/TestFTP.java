package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

/**
 * 
 * @Description:TODO 测试 commons-net 包中封装的 FTP 服务
 * @author: <a href="http://doctordeng.vip/">DoctorDeng</a> 
 * @date:   2017年8月12日 下午2:16:25   
 * @version:1.0
 */
public class TestFTP{
	@Test
	public void testFtpClient() throws Exception {
		//创建一个 FtpClient 对象
		FTPClient ftpClient = new FTPClient();
		// 创建 ftp 连接
		ftpClient.connect("192.168.32.131", 21);
		//登录 ftp 服务器, 使用用户名和密码
		ftpClient.login("doctordeng", "denghuajie123");
		ftpClient.setBufferSize(100000); 
		// 设置上传路径
		ftpClient.changeWorkingDirectory("/home/doctordeng/www/images");
		//修改上传文件格式，解决上传文件数据失真问题
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		// 设置 FTP 为 PASV (被动) 模式，解决上传自己总是为 0 的问题
		ftpClient.enterLocalPassiveMode();
		/**
		 * 上传文件
		 * ftpClient.storeFile(remote, local);
		 * remote: 服务器端文件名
		 * local: 上传文件的 inputStream
		 */
		System.out.println("开始上传");
		long t1 = System.currentTimeMillis();
		ftpClient.storeFile("testFtp.jpg", new FileInputStream(new File("G:\\图片\\16年9月\\1-140415151J5.jpg")));
		long t2 = System.currentTimeMillis();
		System.out.println("Time:" + (t2 - t1));
		System.out.println("上传完毕");
		//关闭连接
		ftpClient.logout();
	}
	@Test
	public void testFtpUtil() throws Exception {
		InputStream fileInStream = new FileInputStream(new File("G:\\图片\\16年9月\\1-140415151R0.jpg"));
		FtpUtil.uploadFile("192.168.32.131", FtpUtil.FTP_DEFAULT_PORT, "doctordeng", "denghuajie123", "/home/doctordeng/www/images", 
				"/2017/8/12", "hello.jpg", fileInStream);
	}
}
