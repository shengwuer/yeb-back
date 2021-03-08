package com.lypowernode.service;

public interface BuyGoodsService {

    // 购买商品的 方法 , goodsId : 购买商品的编号 , nums : 购买商品的数量
    void buy(Integer goodsId, Integer nums);
    void addGoods(Integer goodsId, Integer nums);
    void addNewGoods(Integer Id, String name,Integer amount,Integer price);
}
