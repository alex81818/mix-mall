package com.calvin.mixmall.admin.goods.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.calvin.mixmall.admin.common.BaseController;
import com.calvin.mixmall.admin.goods.entity.Goods;
import com.calvin.mixmall.admin.goods.service.IGoodsService;
import com.calvin.mixmall.admin.goods.vo.GoodsVO;
import com.calvin.mixmall.support.ResultDTO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@Api("商品controller")
@RequestMapping("/api/goods")
public class GoodsController extends BaseController {

    @Autowired
    private IGoodsService goodsService;

    @ApiOperation("分页查询")
    @GetMapping("/pagelist")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "curPage", value = "当前页码", required = true),
        @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true),
        @ApiImplicitParam(name = "title", value = "商品名称")
    })
    public ResultDTO<IPage<Goods>> pagelist(Integer curPage, Integer pageSize, String title) {
        Page<Goods> page = new Page<>(curPage, pageSize);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Goods::getTitle, title);
        queryWrapper.orderByDesc("id");
        IPage<Goods> iPage = goodsService.page(page, queryWrapper);
        return ResultDTO.success(iPage);
    }

    @ApiOperation("去编辑页面")
    @GetMapping("/toEdit")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    public ResultDTO<Goods> toEdit(Integer id){
        Goods goods = goodsService.getById(id);
        return ResultDTO.success(goods);
    }


    @ApiOperation("新建或修改商品")
    @ApiImplicitParams({
    })
    @PostMapping("/saveOrUpdate")
    public ResultDTO saveOrUpdate(@Valid @RequestBody GoodsVO goodsVO){
        Goods goods = new Goods();
        BeanUtil.copyProperties(goodsVO, goods);
        if(goods.getId()==null){
            goods.setCreateTime(LocalDateTime.now());
            goods.setUpdateTime(LocalDateTime.now());
            goodsService.save(goods);
        }else{
            goods.setUpdateTime(LocalDateTime.now());
            goodsService.updateById(goods);
        }
        return ResultDTO.success();
    }

    @ApiOperation("删除")
    @GetMapping("/delete")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    public ResultDTO delete(Integer id){
        goodsService.removeById(id);
        return ResultDTO.success();
    }

}
