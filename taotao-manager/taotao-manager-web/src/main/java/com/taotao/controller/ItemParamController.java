package com.taotao.controller;

import javax.annotation.Resource;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItemParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.CommonResult;
import com.taotao.service.ItemParamService;

@Controller
public class ItemParamController {
	@Resource
	private ItemParamService itemParamService;
	
	@RequestMapping(method=RequestMethod.GET, value="/item/param/query/itemcatid/{itemCatId}")
	@ResponseBody
	public CommonResult getItemParam(@PathVariable("itemCatId")Long itemCatId) {
		return itemParamService.getItemParamByItemCatId(itemCatId);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/item/cat/{itemCatId}/param")
	@ResponseBody
	public CommonResult itemParam(@PathVariable("itemCatId") Long itemCatId, String paramData) {
		return itemParamService.addItemParam(itemCatId, paramData);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/item/param")
	@ResponseBody
	public EUDataGridResult<TbItemParam> queryItemParamByPage(Integer page, Integer rows) {

		return itemParamService.listItemParamByPage(page, rows);
	}
 }
