package com.calvin.mixmall.admin.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.calvin.mixmall.admin.goods.dao.GoodsDao;
import com.calvin.mixmall.admin.goods.entity.Goods;
import com.calvin.mixmall.admin.goods.service.IGoodsService;
import org.springframework.stereotype.Service;

/**
 * @author guowei
 * @version 0.0.1
 * @date 2020-06-07
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements IGoodsService {

//    @Override
//    public IPage<Goods> selectPage(Page<Goods> page, String title){
//        return this.baseMapper.selectPageVo(page, title);
//    }

}
