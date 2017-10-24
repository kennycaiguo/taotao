package com.taotao.controller;

import javax.annotation.Resource;

import com.taotao.pojo.TbItemParamItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.taotao.common.pojo.CommonResult;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	@Resource
	private ItemService itemService;

	@RequestMapping(value = "/rest/page/item-edit/{itemId}", method = RequestMethod.GET)
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
	public CommonResult addItem(TbItem item, String desc, String itemParams) throws Exception {
		return itemService.addItem(item, desc, itemParams);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/rest/item/delete")
	@ResponseBody
	public CommonResult batchDeleteItem(String ids) {
		return itemService.batchDeleteItem(ids);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/rest/item/instock")
	@ResponseBody
	public CommonResult batchUnderItem(String ids) {
		return itemService.batchUnderItem(ids);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/rest/item/reshelf")
	@ResponseBody
	public CommonResult batchGoupItem(String ids) {
		return itemService.batchGoupItem(ids);
	}

	@RequestMapping(method = RequestMethod.POST, value="/rest/item/update")
	@ResponseBody
	public CommonResult updateItem(TbItem item, String desc, String itemParams, String itemParamId) {

		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/rest/item/param/item/query/{itemId}")
	@ResponseBody
	public CommonResult queryItemParamItem(@PathVariable("itemId") Long itemId) {
		TbItemParamItem tbItemParamItem = itemService.getItemParam(itemId);
		if (null != tbItemParamItem) {
			return CommonResult.ok(tbItemParamItem);
		}
		return  CommonResult.build(400, "查询不到数据");
	}
	@RequestMapping(method = RequestMethod.GET, value = "/rest/item/query/item/desc/{itemId}")
	@ResponseBody
	public CommonResult queryItemDesc(@PathVariable("itemId") Long itemId) {
		return itemService.getItemDescByItemId(itemId);
	}
}
