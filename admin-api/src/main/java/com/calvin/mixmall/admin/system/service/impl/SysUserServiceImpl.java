package com.calvin.mixmall.admin.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.calvin.mixmall.admin.system.dao.SysUserDao;
import com.calvin.mixmall.admin.system.entity.SysUser;
import com.calvin.mixmall.admin.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guowei
 * @version 0.0.1
 * @date 2020-06-11
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements ISysUserService {


    /**
     * 根据id查询
     *
     * @param id 传入的id
     * @return com.zl.goods.core.entity.SysUser
     * @date 2020-06-11
     */
    @Override
    public SysUser selectById(Long id) {
        return this.baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery()
                .eq(SysUser::getUserId, id));
    }


    /**
     * 分页查询
     *
     * @param pagination 页数
     * @param count      显示条数
     * @return java.util.List<com.zl.goods.core.entity.SysUser>
     * @date 2020-06-11
     */
    @Override
    public List<SysUser> selectAllPage(Integer pagination, Integer count) {
        IPage<SysUser> page = new Page<>(pagination, count);
        IPage<SysUser> returns = this.baseMapper.selectPage(page, Wrappers.emptyWrapper());
        return returns.getRecords();
    }

    /**
     * 查询所有
     *
     * @return java.util.List<com.zl.goods.core.entity.SysUser>
     * @date 2020-06-11
     */
    @Override
    public List<SysUser> selectAll() {
        return this.baseMapper.selectList(Wrappers.emptyWrapper());
    }

    /**
     * 更新
     *
     * @param obj 传入的实体类
     * @return com.zl.goods.core.entity.SysUser
     * @date 2020-06-11
     */
    @Override
    public int updateSysUser(SysUser obj) {
        return this.baseMapper.updateById(obj);
    }

    /**
     * 插入
     *
     * @param obj 传入的实体类
     * @return com.zl.goods.core.entity.SysUser
     * @date 2020-06-11
     */
    @Override
    public int insertSysUser(SysUser obj) {
        return this.baseMapper.insert(obj);
    }

    /**
     * 根据id逻辑删除
     *
     * @param id 传入的id
     * @return com.zl.goods.core.entity.SysUser
     * @date 2020-06-11
     */
    @Override
    public int deleteSysUser(Long id) {
        return this.baseMapper.deleteById(id);
    }
}
