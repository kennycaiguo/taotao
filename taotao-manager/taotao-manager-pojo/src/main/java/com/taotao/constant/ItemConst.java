package com.taotao.constant;

/**
 * @description 商品常量接口
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @version 1.0
 * @since 2017/8/15 21:43
 */
public interface ItemConst {
    /**
     * 商品状态：正常
     */
    Byte ITEM_STATUS_NORMAL = 1;
    /**
     * 商品状态：下架
     */
    Byte ITEM_STATUS_UNDER = 2;
    /**
     * 商品状态：删除
     */
    Byte ITEM_STATUS_DELETE = 3;
}
