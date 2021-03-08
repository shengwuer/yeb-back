package com.lypowernode.service;

public interface BuyGoodsService {

    // 购买商品的 方法 , goodsId : 购买商品的编号 , nums : 购买商品的数量
    void buy(Integer goodsId, Integer nums);
}
