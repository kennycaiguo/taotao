package com.taotao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
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
}
