package com.taotao.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @Description:TODO
 * @author: <a href="http://doctordeng.vip/">DoctorDeng</a> 
 * @date:   2017年7月30日 下午8:38:20   
 * @version:1.0
 */
@Controller
public class PageController {
	private Logger log = org.slf4j.LoggerFactory.getLogger(PageController.class);
	/**
	 * @Description: TODO 打开首页
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	/**
	 *  展示各个页面
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable() String page) {
		return page;
	}
}
