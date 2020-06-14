package com.calvin.mixmall.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.calvin.mixmall.user.dao.UserDao;
import com.calvin.mixmall.user.entity.User;
import com.calvin.mixmall.user.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guowei
 * @version 0.0.1
 * @date 2020-06-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {


    /**
     * 根据id查询
     *
     * @param id 传入的id
     * @return com.zl.goods.core.entity.User
     * @date 2020-06-08
     */
    @Override
    public User selectById(Long id) {
        return this.baseMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getId, id));
    }


    /**
     * 分页查询
     *
     * @param pagination 页数
     * @param count      显示条数
     * @return java.util.List<com.zl.goods.core.entity.User>
     * @date 2020-06-08
     */
    @Override
    public List<User> selectAllPage(Integer pagination, Integer count) {
        IPage<User> page = new Page<>(pagination, count);
        IPage<User> returns = this.baseMapper.selectPage(page, Wrappers.emptyWrapper());
        return returns.getRecords();
    }

    /**
     * 查询所有
     *
     * @return java.util.List<com.zl.goods.core.entity.User>
     * @date 2020-06-08
     */
    @Override
    public List<User> selectAll() {
        return this.baseMapper.selectList(Wrappers.emptyWrapper());
    }

    /**
     * 更新
     *
     * @param obj 传入的实体类
     * @return com.zl.goods.core.entity.User
     * @date 2020-06-08
     */
    @Override
    public int updateUser(User obj) {
        return this.baseMapper.updateById(obj);
    }

    /**
     * 插入
     *
     * @param obj 传入的实体类
     * @return com.zl.goods.core.entity.User
     * @date 2020-06-08
     */
    @Override
    public int insertUser(User obj) {
        return this.baseMapper.insert(obj);
    }

    /**
     * 根据id逻辑删除
     *
     * @param id 传入的id
     * @return com.zl.goods.core.entity.User
     * @date 2020-06-08
     */
    @Override
    public int deleteUser(Long id) {
        return this.baseMapper.deleteById(id);
    }
}
