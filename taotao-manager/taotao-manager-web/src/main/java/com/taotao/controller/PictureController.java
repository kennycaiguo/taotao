package com.taotao.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;

/**
 * 
 * @Description:TODO 图片上传处理
 * @author: <a href="http://doctordeng.vip/">DoctorDeng</a> 
 * @date:   2017年8月13日 下午4:58:14   
 * @version:1.0
 */
@Controller
public class PictureController {
	@Resource
	private PictureService pictureService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadPicture(MultipartFile uploadFile) {
		Map<String, Object> result = pictureService.uploadPicture(uploadFile);
		// 为了保证 KindEditor 兼容性，需要把返回结果转换成 json 格式的字符串
		return JsonUtils.objectToJson(result);
	}
	
}
