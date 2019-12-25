package com.edu.sale.mapper;

import com.edu.wzh.entity.AdminEntity;
import com.edu.wzh.entity.Customer;
import com.edu.wzh.entity.GoodsType;

import java.util.List;

public interface SaleMapper {
    List<GoodsType> getGoodsType();

    Customer login(Customer customer);
}
