package com.calvin.mixmall.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.calvin.mixmall.user.entity.User;

import java.util.List;

/**
 * @author guowei
 * @version 0.0.1
 * @date 2020-06-08
 */
public interface IUserService extends IService<User> {
    User selectById(Long id);

    List<User> selectAllPage(Integer pagination, Integer count);

    List<User> selectAll();

    int updateUser(User obj);

    int insertUser(User obj);

    int deleteUser(Long id);
}
