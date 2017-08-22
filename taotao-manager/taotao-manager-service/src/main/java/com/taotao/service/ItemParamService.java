package com.taotao.service;

import com.taotao.common.pojo.CommonResult;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItemParam;

/**
 * 
 * @Description:TODO 类目规则参数 Service
 * @author: <a href="http://doctordeng.vip/">DoctorDeng</a> 
 * @date:   2017年8月14日 下午9:18:25   
 * @version:1.0
 */
public interface ItemParamService {
	/**
	 * TODO 通过 ItemCatId (类目ID) 获取类目对应的规格参数模板
	 * @Title getItemParamByItemCatId   
	 * @param itemCatId 类目ID
	 * @return CommonResult {@link com.taotao.common.pojo.CommonResult}     
	 */
	CommonResult getItemParamByItemCatId(long itemCatId);

	CommonResult addItemParam(long itemCatid, String itemParam);

	EUDataGridResult<TbItemParam> listItemParamByPage(Integer page, Integer rows);
}
