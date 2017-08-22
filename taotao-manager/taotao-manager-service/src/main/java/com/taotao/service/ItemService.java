package com.taotao.service;
import com.taotao.common.pojo.CommonResult;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	EUDataGridResult<TbItem> listItem(int page, int rows);
	CommonResult addItem(TbItem item, String desc) throws Exception;

	/**
	 * 批量删除商品
	 * @param idsString 商品 ID 字符串形式，例如：1,88,33
	 * @return code 200 成功
	 * @see com.taotao.common.pojo.CommonResult
	 */
	CommonResult batchDeleteItem(String idsString);

	/**
	 * 批量下架商品
	 * @param idsString 商品 ID 字符串形式，例如：1,88,33
	 * @return code 200 成功
	 * @see com.taotao.common.pojo.CommonResult
	 */
	CommonResult batchUnderItem(String idsString);

	/**
	 * 批量上架商品
	 * @param idsString 商品 ID 字符串形式，例如：1,88,33
	 * @return code 200 成功
	 */
	CommonResult batchGoupItem(String idsString);
}
