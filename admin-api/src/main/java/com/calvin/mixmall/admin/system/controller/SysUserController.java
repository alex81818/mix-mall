package com.calvin.mixmall.admin.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.calvin.mixmall.admin.common.BaseController;
import com.calvin.mixmall.admin.common.Constants;
import com.calvin.mixmall.admin.interceptor.UserSession;
import com.calvin.mixmall.admin.system.entity.SysUser;
import com.calvin.mixmall.admin.system.service.ISysUserService;
import com.calvin.mixmall.admin.system.vo.LoginVO;
import com.calvin.mixmall.admin.system.vo.SysUserVO;
import com.calvin.mixmall.admin.util.RedisUtils;
import com.calvin.mixmall.support.RedisKey;
import com.calvin.mixmall.support.ResultDTO;
import com.calvin.mixmall.support.ResultEnum;
import com.calvin.mixmall.util.CommonUtil;
import com.calvin.mixmall.util.JsonUtil;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
@Api("管理员")
@RestController
@RequestMapping("/api/sysuser")
public class SysUserController extends BaseController{

    @Autowired
    private ISysUserService userService;
    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/login")
    @ApiOperation("登录")
    public ResultDTO login(@Valid @RequestBody LoginVO loginVO){
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername, loginVO.getUsername())
                .eq(SysUser::getPassword, DigestUtil.md5Hex(loginVO.getPassword()));
        List<SysUser> list = userService.list(queryWrapper);
        if(!list.isEmpty()){
            SysUser sysUser = list.get(0);
            SysUserVO sysUserVO = new SysUserVO();
            BeanUtil.copyProperties(sysUser, sysUserVO);
            String token = CommonUtil.getToken();
            redisUtils.set(RedisKey.SYSUSER_LOGIN_TOKEN+token, JsonUtil.toJsonString(sysUserVO));
            HashMap<Object, Object> map = Maps.newHashMap();
            map.put("token", token);
            map.put("name", sysUserVO.getName());
            return ResultDTO.success(map);
        }else{
            return ResultDTO.failure(ResultEnum.USERNAME_ERROR);
        }

    }

    @GetMapping("/logout")
    @ApiOperation("退出")
    public ResultDTO logout(HttpServletRequest request){
        String token = request.getHeader(Constants.X_USER_TOKEN);
        redisUtils.del(RedisKey.SYSUSER_LOGIN_TOKEN+token);
        UserSession.remove();
        return ResultDTO.success();
    }
}
