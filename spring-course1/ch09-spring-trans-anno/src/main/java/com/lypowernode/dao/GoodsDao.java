package com.lypowernode.dao;

import com.lypowernode.domain.Goods;

public interface GoodsDao {

    // 更新库存
    // goods表示本次用用更新购买的商品信息 , id ,购买数量
    int updateGoods(Goods goods);

    // 补货添加商品
    int insertGoods(Goods goods);

    //采购新一批货
    int insertNewGoods(Goods goods);


    // 查询商品信息
    Goods selectGoods(Integer id);
}
