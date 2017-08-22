package com.taotao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.CommonResult;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	@Resource
	private ItemService itemService;
	
	@RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
	@ResponseBody
	public TbItem getItemById(@PathVariable("itemId") Long itemId) {
		return itemService.getItemById(itemId);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/item/list")
	@ResponseBody
	public EUDataGridResult<TbItem> listItem(Integer page, Integer rows) {
		return itemService.listItem(page, rows);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/item/save")
	@ResponseBody
	public CommonResult addItem(TbItem item, String desc, String itemParam) throws Exception {

		return itemService.addItem(item, desc);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/item")
	@ResponseBody
	public CommonResult batchUpdateItem( String ids, String type) {
		if ("delete".equals(type)) {
			return itemService.batchDeleteItem(ids);
		}

		if ("under".equals(type)) {
			return itemService.batchUnderItem(ids);
		}

		if ("goup".equals(type)) {
			return itemService.batchGoupItem(ids);
		}

		return CommonResult.build(403,"不合法的请求!");
	}

	@RequestMapping(method = RequestMethod.POST, value="/item/{itemId}")
	@ResponseBody
	public CommonResult updateItem() {
		return null;
	}
}
