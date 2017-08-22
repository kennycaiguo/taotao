package com.taotao.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;
/**
 * 
 * @Description:TODO 图片上传实现类, 目前采用 FTP 方式上传
 * @author: <a href="http://doctordeng.vip/">DoctorDeng</a> 
 * @date:   2017年8月12日 下午5:48:35   
 * @version:1.0
 */
@Service
public class PictureServiceImpl implements PictureService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	// 使用 value 注解从 Context 中获取属性文件中的配置
	@Value("${ftp_host}")
	private String ftp_host;
	@Value("${ftp_port}")
	private Integer ftp_port;
	@Value("${ftp_username}")
	private String ftp_username;
	@Value("${ftp_password}")
	private String ftp_password;
	@Value("${ftp_base_path}")
	private String ftp_base_path;
	@Value("${server_image_url_base}")
	private String server_image_url_base;
	
	@Override
	public Map<String, Object> uploadPicture(MultipartFile uploadFile) {
		Map<String, Object> result = new HashMap<>();
		try {
			String oldName = uploadFile.getOriginalFilename();
			// UUID.randomUUID();
			String newName = IDUtils.genImageName() + oldName.substring(oldName.lastIndexOf("."));
			InputStream is = uploadFile.getInputStream();
			String filePath = new DateTime().toString("/yyyy/MM/dd");
			boolean uploadResult = FtpUtil.uploadFile(ftp_host, ftp_port, ftp_username, ftp_password, ftp_base_path,
					filePath, newName, is);
			
			if (uploadResult) {
				result.put("error", 0);
				result.put("url", server_image_url_base + filePath + "/" + newName);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("图片上传失败, FTP 服务器 RUL：{}:{}/{}, " , ftp_host, ftp_port, ftp_base_path);
		}
		
		result.put("error", 1);
		result.put("message", "图片上传失败");
		return result;
	}

}
