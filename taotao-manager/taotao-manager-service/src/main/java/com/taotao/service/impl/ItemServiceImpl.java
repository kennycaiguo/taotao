package com.taotao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.taotao.constant.ItemConst;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.CommonResult;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;
/**
 * 
 * @ClassName:  ItemServiceImpl   
 * @Description:TODO 商品管理 Service
 * @author: DoctorDeng
 * @date:   2017年7月27日 下午10:05:01   
 * @version: 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Resource
	private TbItemMapper itemMapper;
	@Resource
	private TbItemDescMapper itemDescMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		// TODO 
		/*TbItemExample item = itemMapper.selectByPrimaryKey(itemId);*/
		TbItemExample example = new TbItemExample();
		// 添加查询条件
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> items = itemMapper.selectByExample(example);
		if (items != null && items.size() > 0) {
			return items.get(0);
		}
		
		return null;
	}
	/**
	 * <p>Title: listItem</p>   
	 * <p>Description: TODO 分页查询商品列表信息</p>   
	 * @param page 页码
	 * @param rows 每页数据量
	 * @return  {@link  com.taotao.common.pojo.EUDataGridResult} 
	 * 		and {@link  com.taotao.pojo.TbItem}
	 * @see com.taotao.service.ItemService#listItem(long, long)
	 */
	@Override
	public EUDataGridResult<TbItem> listItem(int page, int rows) {
		TbItemExample itemExample = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> itemList = itemMapper.selectByExample(itemExample);
		
		EUDataGridResult<TbItem> result = new EUDataGridResult<>();
		result.setRows(itemList);
		// 取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	/**
	 * 
	 * <p>Title: addItem</p>   
	 * <p>Description: 添加商品 </p>   
	 * @param item
	 * @param desc 商品规格模板
	 * @return   
	 * @see com.taotao.service.ItemService#addItem(com.taotao.pojo.TbItem)
	 */
	@Override
	public CommonResult addItem(TbItem item, String desc) throws Exception {
		java.util.Date date = new java.util.Date();
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		// 商品状态 1 正常 2 下架  3 删除
		item.setStatus((byte) 1);
		item.setCreated(date);
		item.setUpdated(date);
		
		itemMapper.insert(item);
		CommonResult result = addItemDesc(itemId, desc);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		
		return CommonResult.ok();
	}

	@Override
	public CommonResult batchDeleteItem(String idsStr) {
		if (StringUtils.isEmpty(idsStr)) return CommonResult.build(400,"没有要删除的商品!");
		Long[] idsLong = (Long[]) ConvertUtils.convert(idsStr.split(","), Long.class);
		Map<String, Object> updateParam = new HashMap<>();
		updateParam.put("ids", idsLong);
		updateParam.put("status", ItemConst.ITEM_STATUS_DELETE);
		int successCount = itemMapper.batchUpdateItemStatus(updateParam);

		if (successCount > 0) return  CommonResult.ok();

		return CommonResult.build(500, "批量删除商品失败");
	}

	@Override
	public CommonResult batchUnderItem(String idsString) {
		if (StringUtils.isEmpty(idsString)) return CommonResult.build(400,"没有要下架的商品!");
		Long[] idsLong = (Long[]) ConvertUtils.convert(idsString.split(","), Long.class);
		Map<String, Object> updateParam = new HashMap<>();
		updateParam.put("ids", idsLong);
		updateParam.put("status", ItemConst.ITEM_STATUS_UNDER);

		int successCount = itemMapper.batchUpdateItemStatus(updateParam);

		if (successCount > 0) return  CommonResult.ok();

		return CommonResult.build(500, "批量下载商品失败");
	}

	@Override
	public CommonResult batchGoupItem(String idsString) {
		if (StringUtils.isEmpty(idsString)) return CommonResult.build(400,"没有要上架的商品!");
		Long[] idsLong = (Long[]) ConvertUtils.convert(idsString.split(","), Long.class);
		Map<String, Object> updateParam = new HashMap<>();
		updateParam.put("ids", idsLong);
		updateParam.put("status", ItemConst.ITEM_STATUS_NORMAL);

		int successCount = itemMapper.batchUpdateItemStatus(updateParam);

		if (successCount > 0) return  CommonResult.ok();

		return CommonResult.build(500, "批量上架商品失败");
	}

	private CommonResult addItemDesc(Long itemId, String desc) {
		java.util.Date nowDate = new java.util.Date();
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(nowDate);
		itemDesc.setUpdated(nowDate);
		
		int insertCount = itemDescMapper.insert(itemDesc);
		if (1 == insertCount) {
			return CommonResult.ok();
		}
		return CommonResult.build(500, "添加商品描述出错!");
	}
}
