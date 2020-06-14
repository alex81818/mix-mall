package com.calvin.mixmall.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.calvin.mixmall.admin.system.entity.SysUser;

import java.util.List;

/**
* @author guowei
* @version 0.0.1
* @date 2020-06-11
*/
public interface ISysUserService extends IService<SysUser> {
    SysUser selectById(Long id);
    List<SysUser> selectAllPage(Integer pagination, Integer count);
    List<SysUser> selectAll();
    int updateSysUser(SysUser obj);
    int insertSysUser(SysUser obj);
    int deleteSysUser(Long id);
}
