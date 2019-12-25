package com.edu.sale.mapper;

import com.edu.wzh.entity.Goods;

import java.util.List;

public interface GoodsMapper {
    List<Goods> getGoods(Goods goods);

    Goods selectGoodByGid(String gid);
}
