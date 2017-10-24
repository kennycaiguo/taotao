package com.taotao.service;
import com.taotao.common.pojo.CommonResult;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParamItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	EUDataGridResult<TbItem> listItem(int page, int rows);

	/**
	 * 添加商品
	 * @param item 商品实体
	 * @param desc 商品描述
	 * @param itemParams 商品规格参数
	 * @return code 200 成功
	 * @see com.taotao.pojo.TbItem
	 * @throws Exception where sql、Network is bad
	 */
	CommonResult addItem(TbItem item, String desc, String itemParams) throws Exception;

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

	/**
	 * 查询指定商品的规格参数
	 * @param itemId 商品 ID
	 * @return
	 */
	TbItemParamItem getItemParam(Long itemId);

	/**
	 * 查询指定商品描述
	 * @param itemId 商品 ID
	 * @return
	 */
	CommonResult getItemDescByItemId(Long itemId);

	/**
	 * 更新商品
	 * @param item
	 * @param desc
	 * @param itemParams
	 * @param itemParamId
	 * @return
	 */
	CommonResult updateItem(TbItem item, String desc, String itemParams, String itemParamId);
}
