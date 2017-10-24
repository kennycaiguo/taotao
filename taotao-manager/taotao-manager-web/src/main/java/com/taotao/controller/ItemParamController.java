package com.taotao.controller;

import javax.annotation.Resource;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItemParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(method = RequestMethod.POST, value = "/item/param/save/{itemCatId}")
	@ResponseBody
	public CommonResult itemParam(@PathVariable("itemCatId") Long itemCatId, String paramData) {
		return itemParamService.addItemCatParam(itemCatId, paramData);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/item/param/list")
	@ResponseBody
	public EUDataGridResult<TbItemParam> queryItemParamByPage(@RequestParam(defaultValue = "1")  Integer page,
															  @RequestParam(defaultValue = "30") Integer rows) {

		return itemParamService.listItemParamByPage(page, rows);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/item/param/delete")
	public CommonResult itemParamDelete(String ids){
		return itemParamService.deleteItemParam(ids);
	}
 }
