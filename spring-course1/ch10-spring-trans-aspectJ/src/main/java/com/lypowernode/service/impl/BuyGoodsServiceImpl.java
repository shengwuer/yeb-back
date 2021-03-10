package com.lypowernode.service.impl;

import com.lypowernode.dao.GoodsDao;
import com.lypowernode.dao.SaleDao;
import com.lypowernode.domain.Goods;
import com.lypowernode.domain.Sale;
import com.lypowernode.exception.NotEnoughException;
import com.lypowernode.service.BuyGoodsService;

public class BuyGoodsServiceImpl implements BuyGoodsService {

     private SaleDao saleDao;
     private GoodsDao goodsDao;

    // 添加商品
    public void addSale(){


    }

    public void addGoods(){


    }

    public void modifyGoods(){


    }
    public void modifySale(){

    }

    public void removeGoods(){

    }
    public void removeSale(){

    }

    public void queryGoods(){

    }
    public void searchSale(){

    }





    @Override
    //                   购买的商品编号    购买的商品数量
    //                         ↓              ↓
    public void buy(Integer goodsId, Integer nums) {
        System.out.println("buy方法的开始 :");

        // 记录销售的信息 , 想sale表添加记录
        // 创建Sale类的对象
        Sale sale = new Sale();
        // 商品id
        sale.setGid(goodsId);
        // 购买的商品数量
        sale.setNums(nums);
        // 添加进销售对象sale
        saleDao.insertSale(sale);

        // 更新库存
        // 先查询库存 用goodsDao调用selectGoods()方法 , 得到返回值goods为Goods类
        Goods goods = goodsDao.selectGoods(goodsId);
        // 查询之后可能存在的情况
        if (goods == null) {
            // 商品不存在
            throw new NullPointerException("编号是 : " + goodsId + "商品不存在");
        } else if (goods.getAmount() < nums) {
            // 商品库存不足
            throw new NotEnoughException("编号是 : " + goodsId + "商品库存不足");
        }

        // 修改库存
        // 创建一个商品信息
        Goods buyGoods = new Goods();
        //         购买的商品编号
        //               ↓
        buyGoods.setId(goodsId);
        //             购买的商品数量
        //                  ↓
        buyGoods.setAmount(nums);
        // 调用updateGoods()更新库存参数是购买的商品信息(没有就创建一个)
        goodsDao.updateGoods(buyGoods);
        System.out.println("buy方法的完成!");

    }

    public SaleDao getSaleDao() {
        return saleDao;
    }

    public void setSaleDao(SaleDao saleDao) {
        this.saleDao = saleDao;
    }

    public GoodsDao getGoodsDao() {
        return goodsDao;
    }

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }
}
