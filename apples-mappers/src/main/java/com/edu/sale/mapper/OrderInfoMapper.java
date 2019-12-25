package com.edu.sale.mapper;

import com.edu.wzh.entity.OrderInfo;

import java.util.List;

public interface OrderInfoMapper {
    boolean crateOrderInfo(List<OrderInfo> infos);
}
