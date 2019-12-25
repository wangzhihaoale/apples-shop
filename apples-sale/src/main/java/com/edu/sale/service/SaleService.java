package com.edu.sale.service;

import com.edu.wzh.entity.*;

import java.util.List;
import java.util.Map;

public interface SaleService {

    List<GoodsType> getGoodsType();

    List<Goods> getGoods(Goods goods);

    Customer login(Customer customer);

    Goods selectGoodByGid(String gid);

    Order createOrders(Map<String,Goods> shopcar, int addressId, String[] gids, int aid);

    boolean updateOrder(String oid);

    boolean updateOrderPay(String oid);

    List<Order> getOrders(int aid);

    Order getOrder(String oid);
}
