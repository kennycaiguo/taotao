package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ItemCatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * @Description:TODO 商品类目 Controller
 * @author: <a href="http://doctordeng.vip/">DoctorDeng</a> 
 * @date:   2017年8月3日 下午9:50:49   
 * @version:1.0
 */
@Controller
public class ItemCatController {
	@Resource
	private ItemCatService itemCatService;
	
	@RequestMapping(value="/item/cat/list")
	@ResponseBody
	public List<EUTreeNode> categoryList(@RequestParam(value="id", defaultValue="0") Long parentId) throws Exception {
		return itemCatService.listItemCat(parentId);
	}

}
