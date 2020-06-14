package com.calvin.mixmall.admin.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.calvin.mixmall.admin.goods.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author guowei
 * @version 0.0.1
 * @date 2020-06-07
 */
@Mapper
public interface GoodsDao extends BaseMapper<Goods> {
    //IPage<Goods> selectPageVo(Page<?> page, @Param("title") String title);
}
