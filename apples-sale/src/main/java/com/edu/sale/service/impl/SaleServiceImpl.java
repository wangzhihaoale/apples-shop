package com.edu.sale.service.impl;

import com.edu.sale.mapper.GoodsMapper;
import com.edu.sale.mapper.OrderInfoMapper;
import com.edu.sale.mapper.OrderMapper;
import com.edu.sale.mapper.SaleMapper;
import com.edu.sale.service.SaleService;
import com.edu.wzh.entity.*;
import com.edu.wzh.utils.ProduceUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private  SaleMapper saleMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderInfoMapper infoMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<GoodsType> getGoodsType() {
        return saleMapper.getGoodsType();
    }

    @Override
    public List<Goods> getGoods(Goods goods) {
        PageHelper.startPage(goods.getPageNum(),goods.getPageSize());
        return goodsMapper.getGoods(goods);
    }

    @Override
    public Customer login(Customer customer) {
        return saleMapper.login(customer);
    }

    @Override
    public Goods selectGoodByGid(String gid) {
        return goodsMapper.selectGoodByGid(gid);
    }

    @Override
    public Order createOrders
            (Map<String, Goods> shopcar, int addressId, String[] gids, int aid) {

        Order order=new Order();
        order.setAddressId(addressId);
        order.setAid(aid);
        order.setOid(ProduceUtils.produceUUID());
        float currentTotal=getCurrentTotal(shopcar,gids);
        order.setOtotal(currentTotal);
        boolean b = orderMapper.createOrder(order);
        if(b){
            List<OrderInfo> infos=getListInfos(gids,shopcar,order);
            boolean f=infoMapper.crateOrderInfo(infos);
            if(f){
                order.setOrderInfos(infos);
                for (String gid : gids) {
                    shopcar.remove(gid);
                }
                return order;
            }
        }

            return null;
    }

    @Override
    public boolean updateOrder(String oid) {
        return  orderMapper.updateOrder(oid);
    }

    @Override
    public boolean updateOrderPay(String oid) {
        return orderMapper.updateOrderPay(oid);
    }

    private List<OrderInfo> getListInfos(String[] gids, Map<String,Goods> shopcar, Order order) {
        List<OrderInfo> list=new ArrayList<>();
        Goods goods;
        OrderInfo  info;
        for (String gid : gids) {
            goods=shopcar.get(gid);
            info=new OrderInfo();
            info.setGid(gid);
            info.setOid(order.getOid());
            info.setOdid(ProduceUtils.produceUUID());
            info.setOdprice(goods.getGprice()*goods.getGdiscount());
            info.setOdnumber(goods.getNum());
            info.setGoods(goods);
            list.add(info);
        }
        return list;
    }

    private float getCurrentTotal(Map<String,Goods> shopcar, String[] gids) {
        float currentTotal=0.0f;
        for (String gid : gids) {
            Goods goods = shopcar.get(gid);
            currentTotal+=goods.getGprice()*goods.getGdiscount()*goods.getNum();
        }
        return currentTotal;
    }

    @Override
    public List<Order> getOrders(int aid) {
        return orderMapper.getOrders(aid);
    }

    @Override
    public Order getOrder(String oid) {
        return orderMapper.getOrder(oid);
    }
}

