package com.taotao.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @Description:TODO 图片上传服务
 * @author: <a href="http://doctordeng.vip/">DoctorDeng</a> 
 * @date:   2017年8月12日 下午5:46:28   
 * @version:1.0
 */
public interface PictureService {
	Map<String, Object> uploadPicture(MultipartFile uploadFile);
}
