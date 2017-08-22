package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.CommonResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {
	@Resource
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public CommonResult getItemParamByItemCatId(long itemCatId) {
		TbItemParamExample itemParamExample = new TbItemParamExample();
		Criteria itemParamCriteria = itemParamExample.createCriteria();
		
		itemParamCriteria.andIdEqualTo(itemCatId);
		
		List<TbItemParam> itemParamList= itemParamMapper.selectByExample(itemParamExample);
		
		if (null != itemParamList && !itemParamList.isEmpty()) {
			TbItemParam tbItemParam = itemParamList.get(0);
			return CommonResult.ok(tbItemParam);
		}
		
		return CommonResult.ok();
	}

	@Override
	public CommonResult addItemParam(long itemCatid, String itemParam) {
		TbItemParam tbItemParam = new TbItemParam();
		final Date date = new Date();
		tbItemParam.setCreated(date);
		tbItemParam.setItemCatId(itemCatid);
		tbItemParam.setUpdated(date);
		tbItemParam.setParamData(itemParam);
		int insertCount = itemParamMapper.insert(tbItemParam);
		if (insertCount == 1) {
			return CommonResult.ok();
		}

		return CommonResult.build(500, "添加类目规格参数出错!");
	}

	@Override
	public EUDataGridResult<TbItemParam> listItemParamByPage(Integer page, Integer rows) {
		if (null == page || page < 0) page = 1;
		if (null == rows || rows < 0) rows = 30;

		TbItemParamExample tbItemParamExample = new TbItemParamExample();
		PageHelper.startPage(page, rows);
		List<TbItemParam> itemParamList = itemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);

		EUDataGridResult<TbItemParam> result = new EUDataGridResult<>();
		result.setRows(itemParamList);
		// 取记录总条数
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(itemParamList);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

}
