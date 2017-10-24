package com.taotao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.CommonResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {
	@Resource
	private TbItemParamMapper itemParamMapper;
	@Resource
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public CommonResult getItemParamByItemCatId(long itemCatId) {
		TbItemParamExample itemParamExample = new TbItemParamExample();
		Criteria itemParamCriteria = itemParamExample.createCriteria();
		
		itemParamCriteria.andIdEqualTo(itemCatId);
		
		List<TbItemParam> itemParamList= itemParamMapper.selectByExampleWithBLOBs(itemParamExample);
		
		if (null != itemParamList && !itemParamList.isEmpty()) {
			TbItemParam tbItemParam = itemParamList.get(0);
			return CommonResult.ok(tbItemParam);
		}
		
		return CommonResult.ok();
	}



	@Override
	public CommonResult addItemCatParam(long itemCatid, String itemParam) {
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

	@Override
	public String getItemParamByItemId(Long itemId) {
		TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
		TbItemParamItemExample.Criteria criteria = tbItemParamItemExample.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> tbItemParamItemList = itemParamItemMapper.selectByExample(tbItemParamItemExample);
		if (null == tbItemParamItemList || tbItemParamItemList.isEmpty()) {
			return "";
		}
		//取出参数信息
		TbItemParamItem itemParamItem = tbItemParamItemList.get(0);
		String paramData = itemParamItem.getParamData();
		//把json数据转换成java对象
		List<Map> paramList = JsonUtils.jsonToList(paramData, Map.class);
		//将参数信息转换成html
		StringBuilder sb = new StringBuilder();
		//sb.append("<div>");
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
		sb.append("    <tbody>\n");
		for (Map map : paramList) {
			sb.append("        <tr>\n");
			sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
			sb.append("        </tr>\n");
			List<Map> params = (List<Map>) map.get("params");
			for (Map map2 : params) {
				sb.append("        <tr>\n");
				sb.append("            <td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
				sb.append("            <td>"+map2.get("v")+"</td>\n");
				sb.append("        </tr>\n");
			}
		}
		sb.append("    </tbody>\n");
		sb.append("</table>");
		//sb.append("</div>");
		return sb.toString();
	}

	@Override
	public CommonResult deleteItemParam(String ids) {
		if (StringUtils.isEmpty(ids)) return CommonResult.build(400,"没有要删除的商品规格!");
		Long[] idsLong = (Long[]) ConvertUtils.convert(ids.split(","), Long.class);
		int deleteCount = itemParamMapper.batchDeleteItemParamByPrimaryKeys(idsLong);
		if (idsLong.length != deleteCount) {
			CommonResult.build(500, "删除失败");
		}
		return CommonResult.ok("操作成功");
	}
}
