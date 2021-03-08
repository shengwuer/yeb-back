package com.lypowernode.service.impl;

import com.lypowernode.dao.GoodsDao;
import com.lypowernode.dao.SaleDao;
import com.lypowernode.domain.Goods;
import com.lypowernode.domain.Sale;
import com.lypowernode.exception.NotEnoughException;
import com.lypowernode.service.BuyGoodsService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BuyGoodsServiceImpl implements BuyGoodsService {

     private SaleDao saleDao;
     private GoodsDao goodsDao;

     /*
     * rollbackFor : 发生指定的异常一定回滚
     *      处理逻辑是 : 1). spring框架会首先检查方法抛出的异常是不是在rollbackFor的属性值中
     *                     如果异常在rollbackFor列表中 , 不管是什么类型的异常 , 一定回滚.
     *                 2). 如果你的抛出的异常不在rollbackFor列表中 , spring会判断异常是不是RuntimeException ,
     *                      如果是一定会回滚.
     */

  /*
   // 使用在公共的方法上
   @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            readOnly = false,
            //rollbackFor是数组 异常类的名字
            rollbackFor = {
                    NullPointerException.class,
                    NotEnoughException.class
            }
    )
    */
    // 不写值,使用的事务控制的默认值 , 默认的传播行为是REQUIRED , 默认的隔离级别DEFAULT , 默认抛出运行是异常 , 回滚事务.
    @Transactional
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
        // 先要查询库存有没有这个货才能知道能不能购买 用goodsDao调用selectGoods()方法 , 得到返回值goods为Goods类
        Goods goods = goodsDao.selectGoods(goodsId);
        // 查询之后可能存在的情况
        if (goods == null) {
            // 商品不存在
            throw new NullPointerException("编号是 : " + goodsId + "商品不存在");
        } else if (goods.getAmount() < nums) {
            // 商品库存不足
            throw new NotEnoughException("编号是 : " + goodsId + "商品库存不足");
        }

        // 卖出去以后 , 修改库存
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
    @Override
    public void addGoods(Integer goodsId, Integer nums){
        Goods goods = new Goods();
        goods.setId(goodsId);
        goods.setAmount(nums);
        goodsDao.insertGoods(goods);
        System.out.println("增补旧货成功!");

    }

    @Override
    public void addNewGoods(Integer id, String name,Integer amount,Integer price){
        Goods goods = new Goods();
        goods.setId(id);
        goods.setAmount(amount);
        goods.setName(name);
        goods.setPrice(price);

        goodsDao.insertNewGoods(goods);
        System.out.println("补新货成功!");

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
