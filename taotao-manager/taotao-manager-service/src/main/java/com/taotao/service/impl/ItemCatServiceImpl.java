package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
/**
 * 
 * @Description:TODO 商品类目 Service
 * @author: <a href="http://doctordeng.vip/">DoctorDeng</a> 
 * @date:   2017年8月3日 下午10:11:52   
 * @version:1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Resource
	private TbItemCatMapper tbItemCatMapper;
	/**
	 * 
	 * <p>Title: getItemCatList</p>   
	 * <p>Description: 获取指定类目下的所有一级子类目</p>   
	 * @param parentId 类目 ID
	 * @return {@link  com.taotao.pojo.TbItemCat} 
	 * @throws Exception   
	 * @see com.taotao.service.ItemService#listItemCat(java.lang.Long)
	 */
	@Override
	public List<EUTreeNode> listItemCat(Long parentId) throws Exception {
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		com.taotao.pojo.TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByExample(tbItemCatExample);
		
		List<EUTreeNode> catList = new ArrayList<>();
		for (int i = 0, len = tbItemCatList.size(); i < len; i++) {
			EUTreeNode treeNode = new EUTreeNode();
			TbItemCat tbItemCat = tbItemCatList.get(i);
			
			treeNode.setId(tbItemCat.getId());
			treeNode.setText(tbItemCat.getName());
			treeNode.setState(tbItemCat.getIsParent() ? "closed" : "open");
			catList.add(treeNode);
		}
		return catList;
	}
}
